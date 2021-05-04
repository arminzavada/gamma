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
		 	return xstsFactory.createXTransition => [
		 		action = seq
		 	]
		}
		def isTranEndSlice() {
			return (actions.last instanceof AssignmentAction &&
				(actions.last as AssignmentAction).lhs instanceof DirectReferenceExpression &&
				((actions.last as AssignmentAction).lhs as DirectReferenceExpression).declaration == xStsSplitter._last &&
				(actions.last as AssignmentAction).rhs instanceof IntegerLiteralExpression &&
				((actions.last as AssignmentAction).rhs as IntegerLiteralExpression).value == 0);
		}
	}
	
	public static final XstsSplitter INSTANCE = new XstsSplitter
	
	protected static final GammaEcoreUtil ecoreUtil = GammaEcoreUtil.INSTANCE;
	protected static final XSTSModelFactory xstsFactory = XSTSModelFactory.eINSTANCE
 	protected static final ExpressionModelFactory exprFactory = ExpressionModelFactory.eINSTANCE
		
	var VariableDeclaration _last = null
	int _id = 0
	def int nextId() {
		_id++
		return _id
	}
	
	val Set<VariableDeclarationAction> localVarDecls = newHashSet
 	val Map<XTransition, Set<XstsSlice>> tranEndSlices = newHashMap
 	val Map<XstsSlice, XTransition> sliceOriginalTranMap = newHashMap
 	val Map<VariableDeclarationAction, XstsSlice> varDeclSlice = newHashMap
 	val Map<VariableDeclaration, Set<XstsSlice>> varUseSlices = newHashMap
	
	def init() {
		_id = 0
		localVarDecls.clear
		tranEndSlices.clear
		sliceOriginalTranMap.clear
		varDeclSlice.clear
		varUseSlices.clear
	}
	
	 def XSTS split(XSTS input) {
	 	val result = ecoreUtil.clone(input)
	 	//TODO Check existence of this variable name
	 	_last = exprFactory.createVariableDeclaration => [
	 		name = "__last"
	 		type = exprFactory.createIntegerTypeDefinition
	 		expression = exprFactory.createIntegerLiteralExpression => [
	 			value = BigInteger.valueOf(0)
	 		]
	 	]
	 	result.variableDeclarations.add(_last)
	 	
	 	init()
	 	
	 	val List<XTransition> toRemove = newArrayList
	 	val List<XTransition> toAdd = newArrayList
	 	
	 	for (tran : result.transitions) {
	 		// Every transition starts and ends with assuming 0
	 		val slices = tran.action.slice(0, 0)
	 		
	 		tranEndSlices += (tran -> slices.getEndSlices)
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
	 			var declOriginalTran = sliceOriginalTranMap.get(declSlice)
	 			// Replace with global var
	 			result.variableDeclarations += localVar
	 			localVarDecl.variableDeclaration = null
	 			EcoreUtil.delete(localVarDecl) //TODO ez így jó?
	 			// Reset
	 			declSlice.actions.add(0, createAssignment(localVar, 0))
	 			for (endSlice : tranEndSlices.get(declOriginalTran)) {
	 				endSlice.actions.add(createAssignment(localVar, 0))
	 			}
	 		}
	 	}
	 	
	 	return result
	 }
	 
	 def Set<XstsSlice> getEndSlices(Collection<XstsSlice> slices) {
	 	return slices.stream
		 	.filter[s | s.isTranEndSlice]
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
	 		if (action instanceof NonDeterministicAction) {
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
	 			
	 			if (last) {
	 				slices += action.slice(sliceSeparatorId, endId)
	 			}
	 			else {
	 				val choiceEndId = nextId()
	 				slices += action.slice(sliceSeparatorId, choiceEndId)
	 				slice = new XstsSlice(choiceEndId)
	 			}
	 		}
	 		else {// Non-choice
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
 }