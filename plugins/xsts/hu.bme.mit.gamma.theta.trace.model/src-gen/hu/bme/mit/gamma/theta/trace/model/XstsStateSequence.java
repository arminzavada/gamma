/**
 */
package hu.bme.mit.gamma.theta.trace.model;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Xsts State Sequence</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link hu.bme.mit.gamma.theta.trace.model.XstsStateSequence#getStates <em>States</em>}</li>
 * </ul>
 *
 * @see hu.bme.mit.gamma.theta.trace.model.TraceModelPackage#getXstsStateSequence()
 * @model
 * @generated
 */
public interface XstsStateSequence extends EObject {
	/**
	 * Returns the value of the '<em><b>States</b></em>' containment reference list.
	 * The list contents are of type {@link hu.bme.mit.gamma.theta.trace.model.XstsState}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>States</em>' containment reference list.
	 * @see hu.bme.mit.gamma.theta.trace.model.TraceModelPackage#getXstsStateSequence_States()
	 * @model containment="true"
	 * @generated
	 */
	EList<XstsState> getStates();

} // XstsStateSequence
