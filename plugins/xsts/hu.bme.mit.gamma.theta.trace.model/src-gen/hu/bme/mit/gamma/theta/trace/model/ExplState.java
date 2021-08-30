/**
 */
package hu.bme.mit.gamma.theta.trace.model;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Expl State</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link hu.bme.mit.gamma.theta.trace.model.ExplState#getValuations <em>Valuations</em>}</li>
 * </ul>
 *
 * @see hu.bme.mit.gamma.theta.trace.model.TraceModelPackage#getExplState()
 * @model
 * @generated
 */
public interface ExplState extends EObject {
	/**
	 * Returns the value of the '<em><b>Valuations</b></em>' containment reference list.
	 * The list contents are of type {@link hu.bme.mit.gamma.theta.trace.model.VariableValuation}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Valuations</em>' containment reference list.
	 * @see hu.bme.mit.gamma.theta.trace.model.TraceModelPackage#getExplState_Valuations()
	 * @model containment="true"
	 * @generated
	 */
	EList<VariableValuation> getValuations();

} // ExplState
