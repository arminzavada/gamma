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
import hu.bme.mit.gamma.expression.model.ReferenceExpression
import hu.bme.mit.gamma.expression.model.Declaration

class XstsSplitter {
	static enum XstsTransitionOrigin {
		INIT, ENV, TRANS
	}
	
	static class XstsSlice {
		protected static final XstsSplitter xStsSplitter = XstsSplitter.INSTANCE;
		
		List<Action> actions = newArrayList
		val XstsTransitionOrigin origin
		val VariableDeclaration ownPc
		
		new(XstsTransitionOrigin origin, Map<VariableDeclaration, Integer> beginIds,
			VariableDeclaration ownPc, int ownPcEndId, Action action) {
			this(origin, beginIds, ownPc)
			add(action)
			end(ownPcEndId)
		}
		new(XstsTransitionOrigin origin, Map<VariableDeclaration, Integer> beginIds, VariableDeclaration ownPc) {
			this.origin = origin
			this.ownPc = ownPc
	 		actions += xStsSplitter.createComplexAssumption(origin, beginIds);
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
			actions += xStsSplitter.createAssignment(ownPc, endId)
		}
		def endChangeTranSet() {
			actions += xStsSplitter.createChangeTranSetActions(origin)
		}
		def toTransition() {
			val seq = xstsFactory.createSequentialAction
		 	seq.actions.addAll(actions)
		 	return actionUtil.wrap(seq)
		}
		def isEndSlice(VariableDeclaration pc, int endId) {
			// It returns false for 'conditionally' end slices, e.g.: __pc := if (...) then 0 else ...
			return (actions.last instanceof AssignmentAction &&
				(actions.last as AssignmentAction).lhs instanceof DirectReferenceExpression &&
				((actions.last as AssignmentAction).lhs as DirectReferenceExpression).declaration == pc &&
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
	
	var XSTS xSts = null
	
	var VariableDeclaration _pc = null
	var VariableDeclaration _trans = null
	var VariableDeclaration _init = null
	
	val _idMap = <VariableDeclaration, Integer>newHashMap
	val idShouldRevertMap = <VariableDeclaration, Integer>newHashMap
	def resetIdRevert(VariableDeclaration v) {
		idShouldRevertMap += v -> 0
	}
	def revertId(VariableDeclaration v) {
		_idMap += v -> (_idMap.get(v) - idShouldRevertMap.get(v))
		resetIdRevert(v)
	}
	def int nextId(VariableDeclaration v) {
		val oldId = _idMap.containsKey(v) ? _idMap.get(v) : 0
		val newId = oldId + 1
		_idMap += v -> newId
		
		val oldRevert = idShouldRevertMap.containsKey(v) ? idShouldRevertMap.get(v) : 0
		val newRevert = oldRevert + 1
		idShouldRevertMap += v -> newRevert
		
		return newId
	}
	def void resetId(VariableDeclaration v) {
		_idMap.remove(v)
		idShouldRevertMap.remove(v)
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
 		this.xSts = result
	 	// Add util vars
	 	result.addSplitUtilVars
	 	resetBranchPcVars()
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
	 		val slices = tran.action.slice(origin, newHashMap(_pc -> 0), _pc, 0)
	 		
	 		var endSlices = slices.getEndSlices(_pc, 0)
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
	 			val initialVal = exprUtil.getInitialValue(localVar)
	 			localVarDecl.variableDeclaration = null
	 			localVar.expression = null
	 			xSts.addGlobalVar(localVar)
	 			declSlice.actions -= localVarDecl
	 			// Replace original declaration with initial value assignment
	 			declSlice.actions.add(1, createAssignment(localVar, initialVal))
	 			// Add initial value assignment to every end of its scope
	 			/*for (endSlice : declSlice.scopeEndSlices) {
	 				endSlice.actions.add(endSlice.actions.size - 1, createAssignment(localVar, ecoreUtil.clone(initialVal)))
	 			}*/
	 		}
	 	}
	 	
	 	xSts.transitions += toAdd.toTransitions
	 	xSts.transitions -= toRemove
	 }
	 
	 def dispatch List<XstsSlice> slice(Action action, XstsTransitionOrigin origin,
	 	Map<VariableDeclaration, Integer> beginIds, VariableDeclaration ownPc, int ownPcEndId
	 ) {
	 	throw new IllegalArgumentException("Not known action: " + action)
	 }
	 
	 def dispatch List<XstsSlice> slice(AtomicAction atomic, XstsTransitionOrigin origin,
	 	Map<VariableDeclaration, Integer> beginIds, VariableDeclaration ownPc, int ownPcEndId
	 ) {
	 	return null
	 }
	 
	 def dispatch List<XstsSlice> slice(AssignmentAction atomic, XstsTransitionOrigin origin,
	 	Map<VariableDeclaration, Integer> beginIds, VariableDeclaration ownPc, int ownPcEndId
	 ) {
	 	if (atomic.lhs.pointsToLog) {
		 	val slice = new XstsSlice(origin, beginIds, ownPc, ownPcEndId, atomic)
		 	return newArrayList(slice)
	 	}
	 	
	 	return null
	 }
	 
	 def dispatch List<XstsSlice> slice(LoopAction loop, XstsTransitionOrigin origin,
	 	Map<VariableDeclaration, Integer> beginIds, VariableDeclaration ownPc, int ownPcEndId
	 ) {
	 	val slice = new XstsSlice(origin, beginIds, ownPc, ownPcEndId, loop.action)
	 	return newArrayList(slice)
	 }
	 
	 def dispatch List<XstsSlice> slice(SequentialAction seq, XstsTransitionOrigin origin,
	 	Map<VariableDeclaration, Integer> beginIds, VariableDeclaration ownPc, int ownPcEndId
	 ) {
	 	val List<XstsSlice> slices = newArrayList
	 	var XstsSlice slice = null
	 	var int sliceBeginId = beginIds.get(ownPc)
	 	
	 	for (action : seq.actions) {
	 		resetIdRevert(ownPc)
	 		val last = (action == seq.actions.last)
	 		if (slice !== null)
	 			sliceBeginId = nextId(ownPc)
	 		val sliceEndId = last ? ownPcEndId : nextId(ownPc)
	 		
	 		val subsliceBeginIds = getNewBeginIds(beginIds, ownPc, sliceBeginId)
	 		val subslices = action.slice(origin, subsliceBeginIds, ownPc, sliceEndId)
	 		if (subslices === null) {// No slicing
	 			if (slice === null)
	 				slice = new XstsSlice(origin, subsliceBeginIds, ownPc)
 				
	 			slice += action
//	 			registerVarSliceRelations(action, slice)
	 			
	 			if (last) {
	 				slice.end(ownPcEndId)
	 				slices += slice
 				}
 				revertId(ownPc)
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
	 
	 def dispatch List<XstsSlice> slice(NonDeterministicAction choice, XstsTransitionOrigin origin,
	 	Map<VariableDeclaration, Integer> beginIds, VariableDeclaration ownPc, int ownPcEndId
	 ) {
	 	val List<XstsSlice> slices = newArrayList
	 	for (subaction : choice.actions) {
	 		slices += subaction.sliceOrWrapToSingleSlice(origin, beginIds, ownPc, ownPcEndId)
	 	}
	 	registerCompositeActionRelations(choice, slices, ownPc, ownPcEndId)
	 	return slices
	 }
	 
	 def dispatch List<XstsSlice> slice(IfAction ifAction, XstsTransitionOrigin origin,
	 	Map<VariableDeclaration, Integer> beginIds, VariableDeclaration ownPc, int ownPcEndId
	 ) {
	 	val List<XstsSlice> slices = newArrayList
	 	val thenId = nextId(ownPc)
	 	val elseExists = ifAction.^else !== null && !(ifAction.^else instanceof EmptyAction)
	 	val elseId = elseExists ? nextId(ownPc) : ownPcEndId
	 	// Condition
	 	val conditionSlice = new XstsSlice(origin, beginIds, ownPc)
	 	slices += conditionSlice
	 	conditionSlice.add(createAssignment(ownPc, exprFactory.createIfThenElseExpression => [
	 		it.condition = ifAction.condition
	 		it.then = createIntegerLiteralExpr(thenId)
	 		it.^else = createIntegerLiteralExpr(elseId) 
	 	]))
	 	// Handle if this IfAction is the last action of the transition set
	 	if (!elseExists && ownPc == _pc && elseId == 0) {
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
	 	val thenBeginIds = getNewBeginIds(beginIds, ownPc, thenId)
	 	slices += ifAction.then.sliceOrWrapToSingleSlice(origin, thenBeginIds, ownPc, ownPcEndId)
	 	// Else
	 	if (elseExists) {
	 		val elseBeginIds = getNewBeginIds(beginIds, ownPc, elseId)
	 		slices += ifAction.^else.sliceOrWrapToSingleSlice(origin, elseBeginIds, ownPc, ownPcEndId)
	 	}
	 	//
	 	registerCompositeActionRelations(ifAction, slices, ownPc, ownPcEndId)
	 	return slices
	 }
	 
	 //TODO Support ort
	 def dispatch List<XstsSlice> slice(OrthogonalAction ort, XstsTransitionOrigin origin,
	 	Map<VariableDeclaration, Integer> beginIds, VariableDeclaration ownPc, int ownPcEndId
	 ) {
	 	throw new IllegalArgumentException("ort is currently not supported")
	 }
	 
	 def dispatch List<XstsSlice> slice(ParallelAction par, XstsTransitionOrigin origin,
	 	Map<VariableDeclaration, Integer> beginIds, VariableDeclaration ownPc, int ownPcEndId
	 ) {
	 	// TODO Remove empty branches, if any exists
	 	val List<XstsSlice> slices = newArrayList
	 	val numberOfBranches = par.actions.size
	 	val branchPcVars = getBranchPcVars(numberOfBranches)
	 	val forkEndId = nextId(ownPc)
	 	
	 	val forkSlice = new XstsSlice(origin, beginIds, ownPc)
	 	// Set all used branch pc vars to 1
	 	for (branchPcVar : branchPcVars) {
	 		nextId(branchPcVar) // 1 must not be used later
	 		forkSlice.actions += createAssignment(branchPcVar, 1)
	 	}
	 	forkSlice.end(forkEndId)
	 	slices += forkSlice
	 	
	 	// Slice every branch
	 	for (var i = 0; i < numberOfBranches; i++) {
	 		val branch = par.actions.get(i)
	 		val branchPcVar = branchPcVars.get(i)
	 		val branchBeginIds = getNewBeginIds(beginIds, ownPc, forkEndId)
	 		branchBeginIds += branchPcVar -> 1
	 		slices += branch.sliceOrWrapToSingleSlice(origin, branchBeginIds, branchPcVar, 0)
	 	}
	 	
	 	// Assume all used branch pc vars == 0
	 	val joinBeginIds = getNewBeginIds(beginIds, ownPc, forkEndId)
	 	for (branchPcVar : branchPcVars) {
	 		joinBeginIds += branchPcVar -> 0
	 	}
	 	val joinSlice = new XstsSlice(origin, joinBeginIds, ownPc)
	 	// Release all used branch pc vars
	 	for (branchPcVar : branchPcVars) {
	 		releaseBranchPcVar(branchPcVar)
	 	}
	 	joinSlice.end(ownPcEndId)
	 	slices += joinSlice
	 	
	 	return slices
	 }
	 	 
	 // Util
	 def Map<VariableDeclaration, Integer> getNewBeginIds(
	 	Map<VariableDeclaration, Integer> beginIds, VariableDeclaration ownPc, int newBeginId
	 ) {
	 	val newBeginIds = newHashMap
	 	newBeginIds += beginIds
	 	newBeginIds += ownPc -> newBeginId
	 	return newBeginIds
	 }
	 
	 var branchPcVarsCnt = 0
	 val usableBranchPcVars = <VariableDeclaration>newLinkedHashSet
	 
	 def void resetBranchPcVars() {
	 	branchPcVarsCnt = 0
	 	usableBranchPcVars.clear
	 }
	 
	 def void releaseBranchPcVar(VariableDeclaration branchPcVar) {
	 	usableBranchPcVars += branchPcVar
	 	branchPcVar.resetId
	 }
	 def List<VariableDeclaration> getBranchPcVars(int num) {
	 	val branchPcVars = <VariableDeclaration>newArrayList
	 	for (var i = 0; i < num; i++) {
	 		if (usableBranchPcVars.isNullOrEmpty) {
	 			val newBranchPcVar = exprFactory.createVariableDeclaration => [
			 		name = XstsNamings.BRANCH_PC_VAR_NAME + branchPcVarsCnt++
			 		type = exprFactory.createIntegerTypeDefinition
			 		expression = exprFactory.createIntegerLiteralExpression => [
			 			value = BigInteger.valueOf(0)
			 		]
		 		]
		 		addUtilGlobalVar(xSts, newBranchPcVar)
		 		branchPcVars += newBranchPcVar
	 		}
	 		else {
	 			val usableBranchPcVar = usableBranchPcVars.get(0)
	 			branchPcVars += usableBranchPcVar
	 			usableBranchPcVars -= usableBranchPcVar
	 		}
	 	}
	 	return branchPcVars
	 }
	 
	 def List<XstsSlice> sliceOrWrapToSingleSlice(Action action, XstsTransitionOrigin origin,
	 	Map<VariableDeclaration, Integer> beginIds, VariableDeclaration ownPc, int ownPcEndId
	 ) {
	 	val List<XstsSlice> slices = newArrayList
	 	val subslices = action.slice(origin, beginIds, ownPc, ownPcEndId)
	 	if (subslices === null)
	 		slices += new XstsSlice(origin, beginIds, ownPc, ownPcEndId, action)
 		else
 			slices += subslices
		return slices
	 }
	 
	 def void registerCompositeActionRelations(CompositeAction action, List<XstsSlice> slices,
	 	VariableDeclaration ownPc, int ownPcEndId
	 ) {
	 	compositeActionEndSlices += (action -> slices.getEndSlices(ownPc, ownPcEndId))
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
	 
	 def Set<XstsSlice> getEndSlices(Collection<XstsSlice> slices, VariableDeclaration ownPc, int ownPcEndId) {
	 	var Set<XstsSlice> ret = newHashSet
	 	for (var i = 0; i < slices.size; i++) {
	 		if (slices.get(i).isEndSlice(ownPc, ownPcEndId)) {
	 			ret += slices.get(i)
	 		}
	 	}
	 	
	 	return slices.stream
		 	.filter[s | s.isEndSlice(ownPc, ownPcEndId)]
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
		_idMap.clear
		idShouldRevertMap.clear
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
 	def createComplexAssumption(XstsTransitionOrigin origin, Map<VariableDeclaration, Integer> ids) {
 		return xstsFactory.createAssumeAction => [
	 		assumption = exprFactory.createAndExpression => [
	 			operands += getTransSetAssumptionExpr(origin)
	 			for (entry : ids.entrySet) {
	 				operands += exprFactory.createEqualityExpression => [
		 				leftOperand = createDirectReference(entry.key)
		 				rightOperand = createIntegerLiteralExpr(entry.value)
		 			]
	 			}
 			]
		]
 	}
	 def getTransSetAssumptionExpr(XstsTransitionOrigin origin) {
	 	val init = origin == XstsTransitionOrigin.INIT
	 	val trans = origin == XstsTransitionOrigin.TRANS
	 	return exprFactory.createAndExpression => [
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
		]
	 }
	 /*def create_pcAssignment(int value) {
	 	createAssignment(_pc, value)
	 }*/
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
	 
	 def getPointsToLog(ReferenceExpression expression) {
	 	if (expression instanceof DirectReferenceExpression) {
		 	val expr = expression as DirectReferenceExpression
		 	return expr?.declaration?.name?.startsWith("logs_")
	 	}
		
	 	return false
	 }
 }