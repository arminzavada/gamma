/**
 */
package hu.bme.mit.gamma.theta.trace.model;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Xsts State</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link hu.bme.mit.gamma.theta.trace.model.XstsState#getState <em>State</em>}</li>
 *   <li>{@link hu.bme.mit.gamma.theta.trace.model.XstsState#getAnnotations <em>Annotations</em>}</li>
 * </ul>
 *
 * @see hu.bme.mit.gamma.theta.trace.model.TraceModelPackage#getXstsState()
 * @model
 * @generated
 */
public interface XstsState extends EObject {
	/**
	 * Returns the value of the '<em><b>State</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>State</em>' containment reference.
	 * @see #setState(ExplState)
	 * @see hu.bme.mit.gamma.theta.trace.model.TraceModelPackage#getXstsState_State()
	 * @model containment="true" required="true"
	 * @generated
	 */
	ExplState getState();

	/**
	 * Sets the value of the '{@link hu.bme.mit.gamma.theta.trace.model.XstsState#getState <em>State</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>State</em>' containment reference.
	 * @see #getState()
	 * @generated
	 */
	void setState(ExplState value);

	/**
	 * Returns the value of the '<em><b>Annotations</b></em>' attribute list.
	 * The list contents are of type {@link java.lang.String}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Annotations</em>' attribute list.
	 * @see hu.bme.mit.gamma.theta.trace.model.TraceModelPackage#getXstsState_Annotations()
	 * @model
	 * @generated
	 */
	EList<String> getAnnotations();

} // XstsState
