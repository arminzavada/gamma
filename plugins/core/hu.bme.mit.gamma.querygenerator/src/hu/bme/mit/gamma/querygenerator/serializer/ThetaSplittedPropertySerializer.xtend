package hu.bme.mit.gamma.querygenerator.serializer

import hu.bme.mit.gamma.property.model.QuantifiedFormula

class ThetaSplittedPropertySerializer extends ThetaPropertySerializer {
	
	protected String pcVarName
	protected String transVarName
	protected String isStable
	
	new(String pcVarName) {
		super()
		this.pcVarName = pcVarName
		this.transVarName = null
		this.isStable = '''«pcVarName» == 0'''
	}
	
	new(String pcVarName, String transVarName) {
		super()
		this.pcVarName = pcVarName
		this.transVarName = transVarName
		this.isStable = '''«pcVarName» == 0 && «transVarName» == false'''
	}
	
	protected override dispatch String serializeFormula(QuantifiedFormula formula) {
		val original = super.serializeFormula(formula)
		switch (formula.quantifier) {
			case EXISTS: {
				return '''(«original») && «isStable»'''
			}
			case FORALL: {
				return '''(«original») || !(«isStable»)'''
			}
			default: 
				throw new IllegalArgumentException("Not supported quantifier: " + formula.quantifier)
		}
	}
}