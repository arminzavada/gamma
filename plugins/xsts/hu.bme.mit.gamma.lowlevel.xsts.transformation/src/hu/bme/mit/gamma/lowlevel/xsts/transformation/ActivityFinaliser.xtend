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

import hu.bme.mit.gamma.expression.model.ExpressionModelFactory
import hu.bme.mit.gamma.xsts.model.XSTSModelFactory
import hu.bme.mit.gamma.xsts.util.XstsActionUtil
import hu.bme.mit.gamma.statechart.lowlevel.model.InitialNode
import hu.bme.mit.gamma.statechart.lowlevel.model.ActivityNode
import hu.bme.mit.gamma.statechart.lowlevel.model.ActivityDefinition
import hu.bme.mit.gamma.statechart.lowlevel.model.Succession

import static extension hu.bme.mit.gamma.statechart.lowlevel.derivedfeatures.LowlevelStatechartModelDerivedFeatures.*

class ActivityFinaliser {
	// Model factories
	protected final extension XSTSModelFactory factory = XSTSModelFactory.eINSTANCE
	protected final extension ExpressionModelFactory expressionFactory = ExpressionModelFactory.eINSTANCE
	// Action utility
	protected final extension XstsActionUtil xStsActionUtil = XstsActionUtil.INSTANCE

	protected final extension ActivityLiterals activityLiterals = ActivityLiterals.INSTANCE 
	// Trace
	protected final Trace trace
	
	new(Trace trace) {
		this.trace = trace
	}
	
	private def finaliseSuccession(Succession succession) {
		val successionVariable = trace.getXStsVariable(succession)
		return createAssignmentAction(successionVariable, createEnumerationLiteralExpression => [
				reference = emptyFlowStateEnumLiteral
			]
		)
	}
	
	private def finaliseNode(ActivityNode node) {
		val nodeVariable = trace.getXStsVariable(node)
		return createAssignmentAction(nodeVariable, createEnumerationLiteralExpression => [
				reference = idleNodeStateEnumLiteral
			]
		)
	}
	
	private def sendCompletionEvent(ActivityDefinition activity) {
		val controller = activity.activityControllerEvent
	}
	
	def createFinalisationAction(ActivityDefinition activity) {
		val action = createSequentialAction
		
		for (flow : activity.flows) {
			action.actions += flow.finaliseSuccession
		}
		for (node : activity.activityNodes) {
			action.actions += node.finaliseNode
		}
		
		return action
	}
	
}
