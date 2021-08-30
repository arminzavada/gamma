/**
 */
package hu.bme.mit.gamma.theta.trace.model;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see hu.bme.mit.gamma.theta.trace.model.TraceModelPackage
 * @generated
 */
public interface TraceModelFactory extends EFactory {
	/**
	 * The singleton instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	TraceModelFactory eINSTANCE = hu.bme.mit.gamma.theta.trace.model.impl.TraceModelFactoryImpl.init();

	/**
	 * Returns a new object of class '<em>Xsts State Sequence</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Xsts State Sequence</em>'.
	 * @generated
	 */
	XstsStateSequence createXstsStateSequence();

	/**
	 * Returns a new object of class '<em>Xsts State</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Xsts State</em>'.
	 * @generated
	 */
	XstsState createXstsState();

	/**
	 * Returns a new object of class '<em>Expl State</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Expl State</em>'.
	 * @generated
	 */
	ExplState createExplState();

	/**
	 * Returns a new object of class '<em>Variable Valuation</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Variable Valuation</em>'.
	 * @generated
	 */
	VariableValuation createVariableValuation();

	/**
	 * Returns the package supported by this factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the package supported by this factory.
	 * @generated
	 */
	TraceModelPackage getTraceModelPackage();

} //TraceModelFactory
