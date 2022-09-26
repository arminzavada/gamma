package hu.bme.mit.gamma.xsts.transformation

import hu.bme.mit.gamma.expression.model.DirectReferenceExpression
import hu.bme.mit.gamma.expression.model.Expression
import hu.bme.mit.gamma.expression.model.ExpressionModelFactory
import hu.bme.mit.gamma.expression.model.IntegerLiteralExpression
import hu.bme.mit.gamma.expression.model.VariableDeclaration
import hu.bme.mit.gamma.expression.util.ExpressionUtil
import hu.bme.mit.gamma.util.GammaEcoreUtil
import hu.bme.mit.gamma.xsts.derivedfeatures.XstsDerivedFeatures
import hu.bme.mit.gamma.xsts.model.Action
import hu.bme.mit.gamma.xsts.model.AssignmentAction
import hu.bme.mit.gamma.xsts.model.AtomicAction
import hu.bme.mit.gamma.xsts.model.CompositeAction
import hu.bme.mit.gamma.xsts.model.LoopAction
import hu.bme.mit.gamma.xsts.model.NonDeterministicAction
import hu.bme.mit.gamma.xsts.model.OrthogonalAction
import hu.bme.mit.gamma.xsts.model.ParallelAction
import hu.bme.mit.gamma.xsts.model.SequentialAction
import hu.bme.mit.gamma.xsts.model.VariableDeclarationAction
import hu.bme.mit.gamma.xsts.model.XSTS
import hu.bme.mit.gamma.xsts.model.XSTSModelFactory
import hu.bme.mit.gamma.xsts.model.XTransition
import hu.bme.mit.gamma.xsts.transformation.util.XstsNamings
import hu.bme.mit.gamma.xsts.util.XstsActionUtil
import java.math.BigInteger
import java.util.Collection
import java.util.List
import java.util.Map
import java.util.Set
import java.util.stream.Collectors
import hu.bme.mit.gamma.xsts.model.IfAction
import hu.bme.mit.gamma.xsts.model.EmptyAction
import hu.bme.mit.gamma.xsts.transformation.serializer.ActionSerializer
import hu.bme.mit.gamma.xsts.model.MultiaryAction

class XstsSplitter {
	static enum XstsTransitionOrigin {
		INIT, ENV, TRANS
	}
	
	static class XstsSlice {
		protected static final XstsSplitter xStsSplitter = XstsSplitter.INSTANCE;
		
		List<Action> actions = newArrayList
		val XstsTransitionOrigin origin
		
		new(XstsTransitionOrigin origin, int beginId) {
			this.origin = origin
	 		actions += xStsSplitter.create_pcAssumption(origin, beginId);
		}
		new(XstsTransitionOrigin origin, Action action, int beginId, int endId) {
			this(origin, beginId)
			add(action)
			end(endId)
		}
		def add(Action action) {
			actions += action
			xStsSplitter.registerVarSliceRelations(action, this)
		}
		def operator_add(Action action) {
			add(action)
		}
		def size() {
			return actions.size
		}
		def end(int endId) {
			actions += xStsSplitter.create_pcAssignment(endId)
		}
		def endChangeTranSet() {
			actions += xStsSplitter.createChangeTranSetActions(origin)
		}
		def toTransition() {
			val seq = xstsFactory.createSequentialAction
		 	seq.actions.addAll(actions)
		 	return actionUtil.wrap(seq)
		}
		def isEndSlice(int endId) {
			return (actions.last instanceof AssignmentAction &&
				(actions.last as AssignmentAction).lhs instanceof DirectReferenceExpression &&
				((actions.last as AssignmentAction).lhs as DirectReferenceExpression).declaration == xStsSplitter._pc &&
				(actions.last as AssignmentAction).rhs instanceof IntegerLiteralExpression &&
				((actions.last as AssignmentAction).rhs as IntegerLiteralExpression).value.intValue == endId)
		}
	}
	
	public static final XstsSplitter INSTANCE = new XstsSplitter
	
	protected static final GammaEcoreUtil ecoreUtil = GammaEcoreUtil.INSTANCE;
	protected static final XSTSModelFactory xstsFactory = XSTSModelFactory.eINSTANCE
 	protected static final ExpressionModelFactory exprFactory = ExpressionModelFactory.eINSTANCE
 	protected static final XstsActionUtil actionUtil = XstsActionUtil.INSTANCE
 	protected static final ExpressionUtil exprUtil = ExpressionUtil.INSTANCE
	
	var VariableDeclaration _pc = null
	var VariableDeclaration _trans = null
	var VariableDeclaration _init = null
	int _id = 0
	int idShouldRevert = 0
	def resetIdRevert() {
		idShouldRevert = 0
	}
	def revertId() {
		_id -= idShouldRevert
		resetIdRevert()
	}
	def int nextId() {
		_id++
		idShouldRevert++
		return _id
	}
	
	val Set<VariableDeclarationAction> localVarDecls = newHashSet
 	val Map<XstsSlice, XTransition> sliceOriginalTranMap = newHashMap
 	val Map<XTransition, Set<XstsSlice>> tranEndSlices = newHashMap
 	val Map<XstsSlice, CompositeAction> sliceCompositeActionMap = newHashMap
 	val Map<CompositeAction, Set<XstsSlice>> compositeActionEndSlices = newHashMap
 	val Map<VariableDeclarationAction, XstsSlice> varDeclSlice = newHashMap
 	val Map<VariableDeclaration, Set<XstsSlice>> varUseSlices = newHashMap
	
	def XSTS split(XSTS input) {
		// Clone
 		val result = ecoreUtil.clone(input)
	 	// Add util vars
	 	result.addSplitUtilVars
	 	// Add annotations
	 	result.annotations += xstsFactory.createSplittedAnnotation
		result.annotations += xstsFactory.createNoEnvAnnotation
	 	// Split
	 	result.splitTransSet(result.transitions, XstsTransitionOrigin.TRANS)
	 	// Transform env into trans
	 	result.splitTransSet(#[xstsFactory.createXTransition => [
	 		action = XstsDerivedFeatures.getEnvironmentalAction(result)
	 	]], XstsTransitionOrigin.ENV)
	 	result.inEventTransition.action = xstsFactory.createEmptyAction
	 	result.outEventTransition.action = xstsFactory.createEmptyAction
	 	// Transform init into trans
	 	result.splitTransSet(#[xstsFactory.createXTransition => [
	 		action = XstsDerivedFeatures.getInitializingAction(result)
	 	]], XstsTransitionOrigin.INIT)
	 	result.variableInitializingTransition.action = xstsFactory.createEmptyAction
	 	result.configurationInitializingTransition.action = xstsFactory.createEmptyAction
	 	result.entryEventTransition.action = xstsFactory.createEmptyAction
	 	return result
	 }
	 
	 def splitTransSet(XSTS xSts, List<XTransition> transitionsToSplit, XstsTransitionOrigin origin) {
	 	init()
	 	val List<XTransition> toRemove = newArrayList
	 	val List<XstsSlice> toAdd = newArrayList
	 	
	 	for (tran : transitionsToSplit) {
	 		// Every transition starts and ends with assuming/setting __pc = 0
	 		val slices = tran.action.slice(origin, 0, 0)
	 		
	 		var endSlices = slices.getEndSlices(0)
	 		tranEndSlices += (tran -> endSlices)
	 		for (endSlice : endSlices) {
	 			endSlice.endChangeTranSet
	 		}
	 		for (slice : slices)
	 			sliceOriginalTranMap += (slice -> tran)
	 		
	 		toAdd += slices
	 		toRemove += tran
	 	}
	 	
	 	// Fix local variables
	 	for (localVarDecl : localVarDecls) {
	 		var localVar = localVarDecl.variableDeclaration
	 		var usingSlices = varUseSlices.get(localVar)
	 		if (usingSlices !== null && usingSlices.size > 1) {
	 			var declSlice = varDeclSlice.get(localVarDecl)
	 			// Replace with global var
	 			localVarDecl.variableDeclaration = null
	 			localVar.expression = null
	 			xSts.addGlobalVar(localVar)
	 			declSlice.actions -= localVarDecl
	 			val initialVal = exprUtil.getInitialValue(localVar)
	 			// Replace original declaration with initial value assignment
	 			declSlice.actions.add(1, createAssignment(localVar, initialVal))
	 			// Add initial value assignment to every end of its scope
	 			for (endSlice : declSlice.scopeEndSlices) {
	 				endSlice.actions.add(endSlice.actions.size - 2, createAssignment(localVar, ecoreUtil.clone(initialVal)))
	 			}
	 		}
	 	}
	 	
	 	xSts.transitions += toAdd.toTransitions
	 	xSts.transitions -= toRemove
	 }
	 
	 def dispatch List<XstsSlice> slice(Action action, XstsTransitionOrigin origin, int beginId, int endId) {
	 	throw new IllegalArgumentException("Not known action: " + action)
	 }
	 
	 def dispatch List<XstsSlice> slice(AtomicAction atomic, XstsTransitionOrigin origin, int beginId, int endId) {
	 	return null
	 }
	 
	 def dispatch List<XstsSlice> slice(LoopAction loop, XstsTransitionOrigin origin, int beginId, int endId) {
	 	val slice = new XstsSlice(origin, loop.action, beginId, endId)
	 	return newArrayList(slice)
	 }
	 
	 def dispatch List<XstsSlice> slice(SequentialAction seq, XstsTransitionOrigin origin, int beginId, int endId) {
	 	println("---seq:")
	 	seq.print
	 	val List<XstsSlice> slices = newArrayList
	 	var XstsSlice slice = null
	 	var int sliceBeginId = beginId
	 	
	 	for (action : seq.actions) {
	 		resetIdRevert()
	 		val last = (action == seq.actions.last)
	 		if (slice !== null)
	 			sliceBeginId = nextId()
	 		val sliceEndId = last ? endId : nextId()
	 		val subslices = action.slice(origin, sliceBeginId, sliceEndId)
	 		if (subslices === null) {// No slicing
	 			if (slice === null)
	 				slice = new XstsSlice(origin, sliceBeginId)
 				
	 			slice += action
//	 			registerVarSliceRelations(action, slice)
	 			
	 			if (last) {
	 				slice.end(endId)
	 				slices += slice
 				}
 				revertId()
	 		}
	 		else {// Slicing
	 			if (slice !== null) {
	 				slice.end(sliceBeginId)
 					slices += slice
 					slice = null
	 			}
 				slices += subslices
 				sliceBeginId = sliceEndId
	 		}
 		}
 		println("***slices:")
	 	slices.print
 		return slices
	 }
	 
	 def dispatch List<XstsSlice> slice(NonDeterministicAction choice, XstsTransitionOrigin origin, int beginId, int endId) {
	 	val List<XstsSlice> slices = newArrayList
	 	for (subaction : choice.actions) {
	 		slices += subaction.sliceOrWrapToSingleSlice(origin, beginId, endId)
	 	}
	 	registerCompositeActionRelations(choice, slices, endId)
	 	return slices
	 }
	 
	 def dispatch List<XstsSlice> slice(IfAction ifAction, XstsTransitionOrigin origin, int beginId, int endId) {
	 	println("---ifAction:")
	 	ifAction.print
	 	val List<XstsSlice> slices = newArrayList
	 	val thenId = nextId()
	 	val elseExists = ifAction.^else !== null && !(ifAction.^else instanceof EmptyAction)
	 	val elseId = elseExists ? nextId() : endId
	 	// Condition
	 	val conditionSlice = new XstsSlice(origin, beginId)
	 	slices += conditionSlice
	 	conditionSlice.add(createAssignment(_pc, exprFactory.createIfThenElseExpression => [
	 		it.condition = ifAction.condition
	 		it.then = createIntegerLiteralExpr(thenId)
	 		it.^else = createIntegerLiteralExpr(elseId) 
	 	]))
	 	// Handle if this IfAction is the last action of the transition set
	 	if (!elseExists && elseId == 0) {
	 		conditionSlice.add(xstsFactory.createIfAction => [
	 			condition = exprFactory.createEqualityExpression => [
					leftOperand = createDirectReference(_pc)
					rightOperand = createIntegerLiteralExpr(0)
				]
				then = xstsFactory.createSequentialAction => [
					actions += createChangeTranSetActions(origin)
				]
	 		])
	 	}
	 	// Then
	 	slices += ifAction.then.sliceOrWrapToSingleSlice(origin, thenId, endId)
	 	// Else
	 	if (elseExists) {
	 		slices += ifAction.^else.sliceOrWrapToSingleSlice(origin, elseId, endId)
	 	}
	 	//
	 	registerCompositeActionRelations(ifAction, slices, endId)
	 	println("***slices:")
	 	slices.print
	 	return slices
	 }
	 
	 //TODO Support ort and par
	 def dispatch List<XstsSlice> slice(OrthogonalAction ort, XstsTransitionOrigin origin, int beginId, int endId) {
	 	throw new IllegalArgumentException("ort is currently not supported")
	 }
	 
	 def dispatch List<XstsSlice> slice(ParallelAction par, XstsTransitionOrigin origin, int beginId, int endId) {
	 	throw new IllegalArgumentException("par is currently not supported")
	 }
	 
	 //TODO temp
	 def void print(Action action) {
	 	print(ActionSerializer.INSTANCE.serialize(action))
	 }
	 def void print(XstsSlice slice) {
	 	println("*Slice:")
	 	for (a : slice.actions)
	 		a.print
	 }
	 def void print(List<XstsSlice> slices) {
	 	for (s : slices)
	 		s.print
	 }
	 
	 // Util
	 def List<XstsSlice> sliceOrWrapToSingleSlice(Action action, XstsTransitionOrigin origin, int beginId, int endId) {
	 	val List<XstsSlice> slices = newArrayList
	 	val subslices = action.slice(origin, beginId, endId)
	 	if (subslices === null)
	 		slices += new XstsSlice(origin, action, beginId, endId)
 		else
 			slices += subslices
		return slices
	 }
	 
	 def void registerCompositeActionRelations(CompositeAction action, List<XstsSlice> slices, int endId) {
	 	compositeActionEndSlices += (action -> slices.getEndSlices(endId))
	 	for (slice : slices) {
	 		sliceCompositeActionMap += (slice -> action)
	 	}
	 }
	 
	 def void registerVarDeclSlice(Action action, XstsSlice slice) {
	 	if (action instanceof VariableDeclarationAction) {
	 		localVarDecls += action
			varDeclSlice += (action -> slice)
		} else if (action instanceof MultiaryAction) {
			for (subaction : action.actions) {
	 			subaction.registerVarDeclSlice(slice)
	 		}
		}
	 }
	 
	 def void registerVarSliceRelations(Action action, XstsSlice slice) {
	 	action.registerVarDeclSlice(slice)
	 	
		val usedVars = XstsDerivedFeatures.getReferredVariables(action)
		for (usedVar : usedVars) {
			if (XstsDerivedFeatures.isLocal(usedVar)) {
				if (varUseSlices.containsKey(usedVar)) {
 					varUseSlices.get(usedVar) += slice
 				}
 				else {
 					varUseSlices += (usedVar -> newHashSet(slice))
 				}
			}
		}
	 }
	 
 	 def hasGlobalVarWithName(XSTS xSts, String name) {
	 	return !(xSts.variableDeclarations.filter[v | v.name.equals(name)].isEmpty)
	 }
	 def addGlobalVar(XSTS xSts, VariableDeclaration varDecl) {
	 	if (xSts.hasGlobalVarWithName(varDecl.name)) {
	 		var cnt = 0
		 	while (xSts.hasGlobalVarWithName(varDecl.name + cnt)) {
		 		cnt++
		 	}
		 	varDecl.name = varDecl.name + cnt
	 	}
	 	xSts.variableDeclarations += varDecl
	 }
	 
	 def Set<XstsSlice> getEndSlices(Collection<XstsSlice> slices, int endId) {
	 	var Set<XstsSlice> ret = newHashSet
	 	for (var i = 0; i < slices.size; i++) {
	 		if (slices.get(i).isEndSlice(endId)) {
	 			ret += slices.get(i)
	 		}
	 	}
	 	
	 	return slices.stream
		 	.filter[s | s.isEndSlice(endId)]
		 	.collect(Collectors.toSet)
	 }
	 
	 def List<XTransition> toTransitions(List<XstsSlice> slices) {
	 	val List<XTransition> trans = newArrayList
	 	for (slice : slices) {
	 		trans += slice.toTransition
	 	}
	 	return trans
	 }
	 
 	def getScopeEndSlices(XstsSlice slice) {
		if (sliceCompositeActionMap.containsKey(slice)) {
			var scope = sliceCompositeActionMap.get(slice)
			return compositeActionEndSlices.get(scope)
		}
		else {
			var scope = sliceOriginalTranMap.get(slice)
			return tranEndSlices.get(scope)
		}
	}
	
	def addSplitUtilVars(XSTS xSts) {
		_pc = exprFactory.createVariableDeclaration => [
	 		name = XstsNamings.PC_VAR_NAME
	 		type = exprFactory.createIntegerTypeDefinition
	 		expression = exprFactory.createIntegerLiteralExpression => [
	 			value = BigInteger.valueOf(0)
	 		]
	 	]
	 	_trans = exprFactory.createVariableDeclaration => [
	 		name = XstsNamings.TRANS_VAR_NAME
	 		type = exprFactory.createBooleanTypeDefinition
	 		expression = exprFactory.createFalseExpression
	 	]
	 	_init = exprFactory.createVariableDeclaration => [
	 		name = XstsNamings.INIT_VAR_NAME
	 		type = exprFactory.createBooleanTypeDefinition
	 		expression = exprFactory.createTrueExpression
	 	]
	 	xSts.addUtilGlobalVar(_pc)
	 	xSts.addUtilGlobalVar(_trans)
	 	xSts.addUtilGlobalVar(_init)
	}
	def addUtilGlobalVar(XSTS xSts, VariableDeclaration varDecl) {
		/* TODO Handle possible name collision
		 * 		With the existing transformation conventions no name collision should occur
		 * 		but later renaming the existing variable can be necessary
		 */
		if (xSts.hasGlobalVarWithName(varDecl.name))
			throw new IllegalStateException("Global variable already exists with name " + varDecl.name)
	 	xSts.variableDeclarations += varDecl
	 }
	
	def init() {
		_id = 0
		localVarDecls.clear
		sliceOriginalTranMap.clear
		tranEndSlices.clear
		sliceCompositeActionMap.clear
		compositeActionEndSlices.clear
		varDeclSlice.clear
		varUseSlices.clear
	}
	 
	 // Create methods
	 def List<Action> createChangeTranSetActions(XstsTransitionOrigin origin) {
	 	val actions = <Action>newArrayList
	 	if (origin == XstsTransitionOrigin.INIT) {
 			actions += create_initAssignment(false)
	 	}
		actions += create_transAssignment(origin == XstsTransitionOrigin.ENV)
		return actions
 }
	 def create_pcAssumption(XstsTransitionOrigin origin, int value) {
	 	val init = origin == XstsTransitionOrigin.INIT
	 	val trans = origin == XstsTransitionOrigin.TRANS
	 	xstsFactory.createAssumeAction => [
	 		assumption = exprFactory.createAndExpression => [
	 			operands += exprFactory.createEqualityExpression => [
	 				leftOperand = createDirectReference(_init)
	 				rightOperand = createBooleanLiteralExpr(init)
	 			]
	 			if (!init) {
	 				operands += exprFactory.createEqualityExpression => [
		 				leftOperand = createDirectReference(_trans)
		 				rightOperand = createBooleanLiteralExpr(trans)
		 			]
	 			}
	 			operands += exprFactory.createEqualityExpression => [
					leftOperand = createDirectReference(_pc)
					rightOperand = createIntegerLiteralExpr(value)
				]
	 		]
		]
	 }
	 def create_pcAssignment(int value) {
	 	createAssignment(_pc, value)
	 }
	 def create_transAssignment(boolean value) {
	 	createAssignment(_trans, createBooleanLiteralExpr(value))
	 }
	 def create_initAssignment(boolean value) {
	 	createAssignment(_init, createBooleanLiteralExpr(value))
	 }
	 def createDirectReference(VariableDeclaration varDecl) {
	 	exprFactory.createDirectReferenceExpression => [
			declaration = varDecl
		]
	 }
	 def createIntegerLiteralExpr(int value) {
	 	exprFactory.createIntegerLiteralExpression => [
			value = BigInteger.valueOf(value)
		]
	 }
	 def createBooleanLiteralExpr(boolean value) {
	 	value ? exprFactory.createTrueExpression : exprFactory.createFalseExpression
	 }
	 def createAssignment(VariableDeclaration varDecl, int value) {
	 	xstsFactory.createAssignmentAction => [
	 		lhs = createDirectReference(varDecl)
	 		rhs = createIntegerLiteralExpr(value)
	 	]
	 }
	 def createAssignment(VariableDeclaration varDecl, Expression expr) {
	 	xstsFactory.createAssignmentAction => [
	 		lhs = createDirectReference(varDecl)
	 		rhs = expr
	 	]
	 }
 }