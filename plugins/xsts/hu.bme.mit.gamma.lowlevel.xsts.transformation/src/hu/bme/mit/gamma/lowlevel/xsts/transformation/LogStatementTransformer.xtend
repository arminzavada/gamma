/********************************************************************************
 * Copyright (c) 2018-2020 Contributors to the Gamma project
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * SPDX-License-Identifier: EPL-1.0
 ********************************************************************************/
package hu.bme.mit.gamma.lowlevel.xsts.transformation

import hu.bme.mit.gamma.action.model.LogStatement
import hu.bme.mit.gamma.expression.model.EnumerationTypeDefinition
import hu.bme.mit.gamma.expression.model.ExpressionModelFactory
import hu.bme.mit.gamma.statechart.lowlevel.model.Component
import hu.bme.mit.gamma.util.GammaEcoreUtil
import hu.bme.mit.gamma.xsts.model.XSTSModelFactory
import hu.bme.mit.gamma.xsts.util.XstsActionUtil

import static extension hu.bme.mit.gamma.statechart.lowlevel.derivedfeatures.LowlevelStatechartModelDerivedFeatures.*

class LogStatementTransformer {
	// Model factories
	protected final extension XSTSModelFactory factory = XSTSModelFactory.eINSTANCE
	protected final extension ExpressionModelFactory expressionFactory = ExpressionModelFactory.eINSTANCE
	// Action utility
	protected final extension XstsActionUtil xStsActionUtil = XstsActionUtil.INSTANCE
	protected final extension GammaEcoreUtil gammaEcoreUtil = GammaEcoreUtil.INSTANCE
	// Trace 
	protected final Trace trace
	
	new(Trace trace) {
		this.trace = trace
	}
	 
	def transformLogStatement(LogStatement logStatement) {
		val component = logStatement.component
		return transformLogStatement(logStatement, component)
	}
	
	
	def transformLogStatement(LogStatement logStatement, Component component) {
		val literal = logStatement.getLogLiteral(component)
		val type = component.getTypeDefinition
		type.literals += literal
		return createAssignmentAction(component.logVariable, literal.createEnumerationLiteralExpression)
	}
	
	private def getLogVariable(Component component) {
		if (trace.isTraced(component)) {
			return trace.getXStsVariable(component)
		}
		
		val varDecl = createVariableDeclaration => [
			it.name = "logs"
		]
		
		trace.put(component, varDecl)
		
		varDecl.type = createTypeReference => [
			reference = component.getTypeDeclaration
		]
		varDecl.expression = component.getTypeDefinition.literals.head.createEnumerationLiteralExpression 
		
		return varDecl
	}
	
	private def getTypeDeclaration(Component component) {
		if (trace.isTypeTraced(component)) {
			return trace.getType(component)
		}
		
		val typeDecl = createTypeDeclaration => [
			it.name = component.name + "Logs"
			it.type = createEnumerationTypeDefinition => [ defi |
				defi.literals += createLogLiteral("None")
			]
		]
		
		trace.put(component, typeDecl)
		
		return typeDecl
	}
	
	private def getTypeDefinition(Component component) {
		return component.getTypeDeclaration.type as EnumerationTypeDefinition
	}
	
	private def getLogLiteral(LogStatement logStatement, Component component) {
		val type = component.getTypeDefinition
		
		var literal = type.literals.findFirst[it.name == logStatement.text]
		
		if (literal === null) {
			literal = createLogLiteral(logStatement.text)
		}
		
		return literal
	}
	
	private def createLogLiteral(String text) {
		return createEnumerationLiteralDefinition => [
			it.name = text
		]
	}
	
}
