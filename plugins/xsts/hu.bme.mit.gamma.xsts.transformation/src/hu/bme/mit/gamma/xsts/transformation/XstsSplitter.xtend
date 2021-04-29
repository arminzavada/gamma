package hu.bme.mit.gamma.xsts.transformation

import hu.bme.mit.gamma.expression.model.ExpressionModelFactory
import hu.bme.mit.gamma.expression.model.VariableDeclaration
import hu.bme.mit.gamma.util.GammaEcoreUtil
import hu.bme.mit.gamma.xsts.model.Action
import hu.bme.mit.gamma.xsts.model.NonDeterministicAction
import hu.bme.mit.gamma.xsts.model.SequentialAction
import hu.bme.mit.gamma.xsts.model.XSTS
import hu.bme.mit.gamma.xsts.model.XSTSModelFactory
import hu.bme.mit.gamma.xsts.model.XTransition
import java.math.BigInteger
import java.util.List

class XstsSplitter {
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
	
	 def XSTS split(XSTS input) {
	 	val result = ecoreUtil.clone(input)
	 	
	 	_last = exprFactory.createVariableDeclaration => [
	 		name = "__last"
	 		type = exprFactory.createIntegerTypeDefinition
	 		expression = exprFactory.createIntegerLiteralExpression => [
	 			value = BigInteger.valueOf(0)
	 		]
	 	]
	 	result.variableDeclarations.add(_last)
	 	
	 	_id = 0;
	 	
	 	val List<XTransition> toRemove = newArrayList
	 	val List<XTransition> toAdd = newArrayList
	 	
	 	//TODO slice class: actions(, local variable reads/writes)
	 	//map: local variables -> slices (read/write)
	 	//ahol eredetileg deklarálnánk a lokális változót, ott kell neki default értéket adni
	 	//ahol véget érne az eredeti scope-ja, ott kell kinullázni
	 	
	 	for (tran : result.transitions) {
	 		//every transition starts with assuming 0
			//				   ends with assigning 0
	 		val slices = tran.action.slice(0, 0)
	 		
	 		toAdd += slices.toTransitions
	 		toRemove += tran
	 	}
	 	result.transitions += toAdd
	 	result.transitions -= toRemove
	 	
	 	//check for every transition...
	 	
	 	return result
	 }
	 
	 def List<XTransition> toTransitions(List<List<Action>> slices) {
	 	val List<XTransition> trans = newArrayList
	 	for (slice : slices) {
	 		trans += slice.toTransition
	 	}
	 	return trans
	 }
	 def XTransition toTransition(List<Action> slice) {
	 	val seq = xstsFactory.createSequentialAction
	 	seq.actions.addAll(slice)
	 	return xstsFactory.createXTransition => [
	 		action = seq
	 	]
	 }
	 
	 def List<Action> beginSlice(int beginId) {
	 	var List<Action> result = newArrayList
	 	result += create_lastAssumption(beginId);
	 	return result
	 }
	 def endSlice(List<Action> slice, int endId) {
	 	slice += create_lastAssignment(endId)
	 }
	 
	 def dispatch List<List<Action>> slice(Action action, int beginId, int endId) {
	 	//just a single non-choice action
	 	val slice = beginSlice(beginId)
	 	slice += action
	 	slice.endSlice(endId)
	 	return newArrayList(slice)
	 }
	 
	 def dispatch List<List<Action>> slice(SequentialAction seq, int beginId, int endId) {
	 	val List<List<Action>> slices = newArrayList
	 	var List<Action> slice = beginSlice(beginId)
	 	
	 	for (action : seq.actions) {
	 		val last = (action == seq.actions.last)
	 		if (action instanceof NonDeterministicAction) {
	 			var int sliceSeparatorId
	 			
	 			//to avoid "empty" slice between consecutive choices
	 			if (slice.size > 1) {
	 				sliceSeparatorId = nextId()
		 			slice.endSlice(sliceSeparatorId)
		 			slices += slice
	 			} else {
	 				sliceSeparatorId = _id
	 			}
	 			
	 			if (last) {
	 				slices += action.slice(sliceSeparatorId, endId)
	 			} else {
	 				val choiceEndId = nextId()
	 				slices += action.slice(sliceSeparatorId, choiceEndId)
	 				slice = beginSlice(choiceEndId)
	 			}
	 		} else {//non-choice
	 			slice += action
	 			if (last) {
	 				slice.endSlice(endId)
	 				slices += slice
	 			}
	 		}
	 	}
	 	return slices
	 }
	 def dispatch List<List<Action>> slice(NonDeterministicAction choice, int beginId, int endId) {
	 	val List<List<Action>> slices = newArrayList
	 	for (subaction : choice.actions) {
	 		val subslices = subaction.slice(beginId, endId)
	 		slices += subslices
	 	}
	 	return slices
	 }
	 
	 def create_lastReference() {
	 	exprFactory.createDirectReferenceExpression => [
			declaration = _last
		]
	 }
	 def createIntegerLiteralExpr(int value) {
	 	exprFactory.createIntegerLiteralExpression => [
			value = BigInteger.valueOf(value)
		]
	 }
	 def create_lastAssumption(int value) {
	 	xstsFactory.createAssumeAction => [
			assumption = exprFactory.createEqualityExpression => [
				leftOperand = create_lastReference
				rightOperand = createIntegerLiteralExpr(value)
			]
		]
	 }
	 def create_lastAssignment(int value) {
	 	xstsFactory.createAssignmentAction => [
	 		lhs = create_lastReference
	 		rhs = createIntegerLiteralExpr(value)
	 	]
	 }
 }