package hu.bme.mit.gamma.querygenerator.serializer

import hu.bme.mit.gamma.property.model.QuantifiedFormula
import hu.bme.mit.gamma.xsts.transformation.util.XstsNamings
import hu.bme.mit.gamma.property.model.UnaryOperandPathFormula

class ThetaSplittedPropertySerializer extends ThetaPropertySerializer {
	// Singleton
	public static final ThetaSplittedPropertySerializer INSTANCE = new ThetaSplittedPropertySerializer
	
	protected static final String isStable = '''«XstsNamings.PC_VAR_NAME» == 0 && «XstsNamings.TRANS_VAR_NAME» == false'''
	
	protected override dispatch String serializeFormula(QuantifiedFormula formula) {
		val quantifier = formula.quantifier
		val pathFormula = formula.formula
		val original = '''«quantifier.transform»«pathFormula.serializeFormula»'''
		switch (formula.quantifier) {
			case EXISTS: {
				return '''«original» && «isStable»'''
			}
			case FORALL: {
				return '''«original» || !(«isStable»)'''
			}
			default: 
				throw new IllegalArgumentException("Not supported quantifier: " + formula.quantifier)
		}
	}
	
	protected override dispatch String serializeFormula(UnaryOperandPathFormula formula) {
		val operator = formula.operator
		val operand = formula.operand
		return '''«operator.transform» («operand.serializeFormula»)'''
	}
}