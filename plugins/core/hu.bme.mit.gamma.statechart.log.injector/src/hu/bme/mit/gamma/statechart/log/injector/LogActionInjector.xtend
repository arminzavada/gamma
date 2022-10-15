/********************************************************************************
 * Copyright (c) 2018-2022 Contributors to the Gamma project
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * SPDX-License-Identifier: EPL-1.0
 ********************************************************************************/
package hu.bme.mit.gamma.statechart.log.injector

import hu.bme.mit.gamma.action.model.ActionModelFactory
import hu.bme.mit.gamma.statechart.statechart.State
import hu.bme.mit.gamma.statechart.statechart.StatechartDefinition
import hu.bme.mit.gamma.statechart.statechart.Transition
import hu.bme.mit.gamma.statechart.util.StatechartUtil
import hu.bme.mit.gamma.util.GammaEcoreUtil

import static extension hu.bme.mit.gamma.statechart.derivedfeatures.StatechartModelDerivedFeatures.*

class LogActionInjector {
	
	protected final StatechartDefinition statechart
	
	protected final extension GammaEcoreUtil ecoreUtil = GammaEcoreUtil.INSTANCE
	protected final extension ActionModelFactory actionModelFactory = ActionModelFactory.eINSTANCE
	protected final extension StatechartUtil statechartUtil = StatechartUtil.INSTANCE
	
	new(StatechartDefinition statechart) {
		// No cloning to save resources, we process the original model
		this.statechart = statechart
	}
	
	def execute() {
		statechart.allStates.forEach[
			it.injectLogAction
		]
		statechart.transitions.forEach[
			it.injectLogAction
		]
	}
	
	private dispatch def injectLogAction(Transition transition) {
		transition.effects.add(0, createLogStatement => [
			it.text = (transition.name ?: ("t_from_" + transition.sourceState.name + "_to_" + transition.targetState.name)) + "_effect"
		])
	}
	
	private dispatch def injectLogAction(State state) {
		state.entryActions.add(0, createLogStatement => [
			it.text = state.name + "_entry"
		])
		state.exitActions.add(0, createLogStatement => [
			it.text = state.name + "_exit"
		])
	}

}
