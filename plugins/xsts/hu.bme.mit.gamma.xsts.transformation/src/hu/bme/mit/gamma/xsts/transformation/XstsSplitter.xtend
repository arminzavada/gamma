package hu.bme.mit.gamma.xsts.transformation

import hu.bme.mit.gamma.expression.model.DirectReferenceExpression
import hu.bme.mit.gamma.expression.model.ExpressionModelFactory
import hu.bme.mit.gamma.expression.model.IntegerLiteralExpression
import hu.bme.mit.gamma.expression.model.VariableDeclaration
import hu.bme.mit.gamma.util.GammaEcoreUtil
import hu.bme.mit.gamma.xsts.derivedfeatures.XstsDerivedFeatures
import hu.bme.mit.gamma.xsts.model.Action
import hu.bme.mit.gamma.xsts.model.AssignmentAction
import hu.bme.mit.gamma.xsts.model.NonDeterministicAction
import hu.bme.mit.gamma.xsts.model.SequentialAction
import hu.bme.mit.gamma.xsts.model.VariableDeclarationAction
import hu.bme.mit.gamma.xsts.model.XSTS
import hu.bme.mit.gamma.xsts.model.XSTSModelFactory
import hu.bme.mit.gamma.xsts.model.XTransition
import java.math.BigInteger
import java.util.Collection
import java.util.List
import java.util.Map
import java.util.Set
import java.util.stream.Collectors
import org.eclipse.emf.ecore.util.EcoreUtil
import hu.bme.mit.gamma.xsts.util.XstsActionUtil
import hu.bme.mit.gamma.expression.util.ExpressionUtil
import hu.bme.mit.gamma.expression.model.Expression
import hu.bme.mit.gamma.xsts.model.CompositeAction

class XstsSplitter {
	static class XstsSlice {
		protected static final XstsSplitter xStsSplitter = XstsSplitter.INSTANCE;
		
		List<Action> actions = newArrayList
		
		new(int beginId) {
	 		actions += xStsSplitter.create_lastAssumption(beginId);
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
		def isTranEndSlice(int endId) {
			return (actions.last instanceof AssignmentAction &&
				(actions.last as AssignmentAction).lhs instanceof DirectReferenceExpression &&
				((actions.last as AssignmentAction).lhs as DirectReferenceExpression).declaration == xStsSplitter._last &&
				(actions.last as AssignmentAction).rhs instanceof IntegerLiteralExpression &&
				((actions.last as AssignmentAction).rhs as IntegerLiteralExpression).value == endId);
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
	def int nextId() {
		_id++
		return _id
	}
	
	val Set<VariableDeclarationAction> localVarDecls = newHashSet
 	val Map<XstsSlice, XTransition> sliceOriginalTranMap = newHashMap
 	val Map<XTransition, Set<XstsSlice>> tranEndSlices = newHashMap
 	val Map<XstsSlice, CompositeAction> sliceCompositeActionMap = newHashMap
 	val Map<CompositeAction, Set<XstsSlice>> compositeActionEndSlices = newHashMap
 	val Map<VariableDeclarationAction, XstsSlice> varDeclSlice = newHashMap
 	val Map<VariableDeclaration, Set<XstsSlice>> varUseSlices = newHashMap
	
	def getScopeEndSlices(XstsSlice slice) {
		if (sliceCompositeActionMap.containsKey(slice)) {
			var scope = sliceCompositeActionMap.get(slice)
			return compositeActionEndSlices.get(scope)
		} else {
			var scope = sliceOriginalTranMap.get(slice)
			return tranEndSlices.get(scope)
		}
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
	
	 def XSTS split(XSTS input) {
	 	val result = ecoreUtil.clone(input)
	 	_last = exprFactory.createVariableDeclaration => [
	 		name = "__last"
	 		type = exprFactory.createIntegerTypeDefinition
	 		expression = exprFactory.createIntegerLiteralExpression => [
	 			value = BigInteger.valueOf(0)
	 		]
	 	]
	 	while (result.hasGlobalVarWithName(_last.name)) {
	 		_last.name = "_" + _last.name
	 	}
	 	result.variableDeclarations.add(_last)
	 	
	 	init()
	 	
	 	val List<XTransition> toRemove = newArrayList
	 	val List<XTransition> toAdd = newArrayList
	 	
	 	for (tran : result.transitions) {
	 		// Every transition starts and ends with assuming 0
	 		val slices = tran.action.slice(0, 0)
	 		
	 		tranEndSlices += (tran -> slices.getEndSlices(0))
	 		for (slice : slices)
	 			sliceOriginalTranMap += (slice -> tran)
	 		
	 		toAdd += slices.toTransitions
	 		toRemove += tran
	 	}
	 	result.transitions += toAdd
	 	result.transitions -= toRemove
	 	
	 	// Fix local variables
	 	for (localVarDecl : localVarDecls) {
	 		var localVar = localVarDecl.variableDeclaration
	 		if (varUseSlices.get(localVar).size > 1) {
	 			var declSlice = varDeclSlice.get(localVarDecl)
	 			// Replace with global var
	 			localVarDecl.variableDeclaration = null
	 			localVar.renameVarIfNecessary(result)
	 			result.variableDeclarations += localVar
	 			EcoreUtil.delete(localVarDecl)
	 			val initialVal = exprUtil.getInitialValue(localVar)
	 			// Replace original declaration with initial value assignment
	 			declSlice.actions.add(1, createAssignment(localVar, initialVal))
	 			// Add initial value assignment to every end of its scope
	 			for (endSlice : declSlice.scopeEndSlices) {
	 				endSlice.actions.add(createAssignment(localVar, initialVal))
	 			}
	 		}
	 	}
	 	
	 	return result
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
	 	return slices.stream
		 	.filter[s | s.isTranEndSlice(endId)]
		 	.collect(Collectors.toSet)
	 }
	 
	 def List<XTransition> toTransitions(List<XstsSlice> slices) {
	 	val List<XTransition> trans = newArrayList
	 	for (slice : slices) {
	 		trans += slice.toTransition
	 	}
	 	return trans
	 }
	 
	 def dispatch List<XstsSlice> slice(Action action, int beginId, int endId) {
	 	// Just a single non-choice action
	 	val slice = new XstsSlice(beginId)
	 	slice += action
	 	slice.end(endId)
	 	return newArrayList(slice)
	 }
	 
	 def dispatch List<XstsSlice> slice(SequentialAction seq, int beginId, int endId) {
	 	val List<XstsSlice> slices = newArrayList
	 	var slice = new XstsSlice(beginId)
	 	
	 	for (action : seq.actions) {
	 		val last = (action == seq.actions.last)
	 		//TODO Every CompositeAction (not only choice)
	 		if (action instanceof NonDeterministicAction) {// Should slice
	 			var int sliceSeparatorId
	 			
	 			// To avoid 'empty' slice between consecutive choices
	 			if (slice.size > 1) {
	 				sliceSeparatorId = nextId()
		 			slice.end(sliceSeparatorId)
		 			slices += slice
	 			}
	 			else {
	 				sliceSeparatorId = _id
	 			}
	 			
	 			val sliceEndId = last ? endId : nextId()
	 			var newSlices = action.slice(sliceSeparatorId, sliceEndId)
	 			compositeActionEndSlices += (action -> newSlices.getEndSlices(endId))
	 			if (!last) {
	 				slice = new XstsSlice(sliceEndId)
	 			}
	 			slices += newSlices
	 			for (newSlice : newSlices) {
	 				sliceCompositeActionMap += (newSlice -> action)
	 			}
	 		}
	 		else {// Should not slice
	 			slice += action
	 			
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
	 			
	 			if (last) {
	 				slice.end(endId)
	 				slices += slice
	 			}
	 		}
	 	}
	 	return slices
	 }
	 //TODO For every CompositeAction (LoopAction should just return itself in one slice)
	 def dispatch List<XstsSlice> slice(NonDeterministicAction choice, int beginId, int endId) {
	 	val List<XstsSlice> slices = newArrayList
	 	for (subaction : choice.actions) {
	 		val subslices = subaction.slice(beginId, endId)
	 		slices += subslices
	 	}
	 	return slices
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