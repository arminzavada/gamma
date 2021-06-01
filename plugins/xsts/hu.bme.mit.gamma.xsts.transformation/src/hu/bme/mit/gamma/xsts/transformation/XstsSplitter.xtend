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
import hu.bme.mit.gamma.xsts.util.XstsActionUtil
import java.math.BigInteger
import java.util.Collection
import java.util.List
import java.util.Map
import java.util.Set
import java.util.stream.Collectors

class XstsSplitter {
	static class XstsSlice {
		protected static final XstsSplitter xStsSplitter = XstsSplitter.INSTANCE;
		
		List<Action> actions = newArrayList
		
		new(int beginId) {
	 		actions += xStsSplitter.create_lastAssumption(beginId);
		}
		new(Action action, int beginId, int endId) {
			this(beginId)
			add(action)
			end(endId)
		}
		def add(Action action) {
			actions += action
		}
		def operator_add(Action action) {
			add(action)
		}
		def size() {
			return actions.size
		}
		def end(int endId) {
			actions += xStsSplitter.create_lastAssignment(endId)
		}
		def toTransition() {
			val seq = xstsFactory.createSequentialAction
		 	seq.actions.addAll(actions)
		 	return actionUtil.wrap(seq)
		}
		def isEndSlice(int endId) {
			return (actions.last instanceof AssignmentAction &&
				(actions.last as AssignmentAction).lhs instanceof DirectReferenceExpression &&
				((actions.last as AssignmentAction).lhs as DirectReferenceExpression).declaration == xStsSplitter._last &&
				(actions.last as AssignmentAction).rhs instanceof IntegerLiteralExpression &&
				((actions.last as AssignmentAction).rhs as IntegerLiteralExpression).value == endId)
		}
	}
	
	public static final XstsSplitter INSTANCE = new XstsSplitter
	
	protected static final GammaEcoreUtil ecoreUtil = GammaEcoreUtil.INSTANCE;
	protected static final XSTSModelFactory xstsFactory = XSTSModelFactory.eINSTANCE
 	protected static final ExpressionModelFactory exprFactory = ExpressionModelFactory.eINSTANCE
 	protected static final XstsActionUtil actionUtil = XstsActionUtil.INSTANCE
 	protected static final ExpressionUtil exprUtil = ExpressionUtil.INSTANCE
		
	var VariableDeclaration _last = null
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
	 	val result = ecoreUtil.clone(input)
	 	result.addLastVar
	 	
	 	init()
	 	
	 	val List<XTransition> toRemove = newArrayList
	 	val List<XstsSlice> toAdd = newArrayList
	 	
	 	for (tran : result.transitions) {
	 		// Every transition starts and ends with assuming/setting __last = 0
	 		val slices = tran.action.slice(0, 0)
	 		
	 		tranEndSlices += (tran -> slices.getEndSlices(0))
	 		for (slice : slices)
	 			sliceOriginalTranMap += (slice -> tran)
	 		
	 		toAdd += slices
	 		toRemove += tran
	 	}
	 	
	 	// Fix local variables
	 	for (localVarDecl : localVarDecls) {
	 		var localVar = localVarDecl.variableDeclaration
	 		if (varUseSlices.get(localVar).size > 1) {
	 			var declSlice = varDeclSlice.get(localVarDecl)
	 			// Replace with global var
	 			localVarDecl.variableDeclaration = null
	 			localVar.renameVarIfNecessary(result)
	 			result.variableDeclarations += localVar
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
	 	
	 	result.transitions += toAdd.toTransitions
	 	result.transitions -= toRemove
	 	
	 	return result
	 }
	 
	 def dispatch List<XstsSlice> slice(Action action, int beginId, int endId) {
	 	throw new IllegalArgumentException("Not known action: " + action)
	 }
	 
	 def dispatch List<XstsSlice> slice(AtomicAction atomic, int beginId, int endId) {
	 	return null
	 }
	 
	 def dispatch List<XstsSlice> slice(LoopAction loop, int beginId, int endId) {
	 	val slice = new XstsSlice(loop.action, beginId, endId)
	 	return newArrayList(slice)
	 }
	 
	 def dispatch List<XstsSlice> slice(SequentialAction seq, int beginId, int endId) {
	 	val List<XstsSlice> slices = newArrayList
	 	var XstsSlice slice = null
	 	var int sliceBeginId = beginId
	 	
	 	for (action : seq.actions) {
	 		resetIdRevert()
	 		val last = (action == seq.actions.last)
	 		if (slice !== null)
	 			sliceBeginId = nextId()
	 		val sliceEndId = last ? endId : nextId()
	 		val subslices = action.slice(sliceBeginId, sliceEndId)
	 		if (subslices === null) {// No slicing
	 			if (slice === null)
	 				slice = new XstsSlice(sliceBeginId)
 				
	 			slice += action
	 			registerVarSliceRelations(action, slice)
	 			
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
 		return slices
	 }
	 
	 def dispatch List<XstsSlice> slice(NonDeterministicAction choice, int beginId, int endId) {
	 	val List<XstsSlice> slices = newArrayList
	 	for (subaction : choice.actions) {
	 		val subslices = subaction.slice(beginId, endId)
	 		if (subslices === null)
	 			slices += new XstsSlice(subaction, beginId, endId)
	 		else
	 			slices += subslices
	 	}
	 	registerCompositeActionRelations(choice, slices, endId)
	 	return slices
	 }
	 //TODO Support ort and par
	 def dispatch List<XstsSlice> slice(OrthogonalAction ort, int beginId, int endId) {
	 	throw new IllegalArgumentException("ort is currently not supported")
	 }
	 
	 def dispatch List<XstsSlice> slice(ParallelAction par, int beginId, int endId) {
	 	throw new IllegalArgumentException("par is currently not supported")
	 }
	 
	 // Util
	 def void registerCompositeActionRelations(CompositeAction action, List<XstsSlice> slices, int endId) {
	 	compositeActionEndSlices += (action -> slices.getEndSlices(endId))
	 	for (slice : slices) {
	 		sliceCompositeActionMap += (slice -> action)
	 	}
	 }
	 
	 def void registerVarSliceRelations(Action action, XstsSlice slice) {
	 	if (action instanceof VariableDeclarationAction) {
	 		localVarDecls += action
			varDeclSlice += (action -> slice)
		}
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
	 def renameVarIfNecessary(VariableDeclaration varDecl, XSTS xSts) {
	 	if (xSts.hasGlobalVarWithName(varDecl.name)) {
	 		var cnt = 0
		 	while (xSts.hasGlobalVarWithName(varDecl.name + cnt)) {
		 		cnt++
		 	}
		 	varDecl.name = varDecl.name + cnt
	 	}
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
	
	def addLastVar(XSTS xSts) {
		_last = exprFactory.createVariableDeclaration => [
	 		name = "__last"
	 		type = exprFactory.createIntegerTypeDefinition
	 		expression = exprFactory.createIntegerLiteralExpression => [
	 			value = BigInteger.valueOf(0)
	 		]
	 	]
	 	while (xSts.hasGlobalVarWithName(_last.name)) {
	 		_last.name = "_" + _last.name
	 	}
	 	xSts.variableDeclarations.add(_last)
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
	 def create_lastAssumption(int value) {
	 	xstsFactory.createAssumeAction => [
			assumption = exprFactory.createEqualityExpression => [
				leftOperand = createDirectReference(_last)
				rightOperand = createIntegerLiteralExpr(value)
			]
		]
	 }
	 def create_lastAssignment(int value) {
	 	createAssignment(_last, value)
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