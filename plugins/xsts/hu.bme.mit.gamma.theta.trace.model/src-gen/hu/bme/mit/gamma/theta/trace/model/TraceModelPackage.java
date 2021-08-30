/**
 */
package hu.bme.mit.gamma.theta.trace.model;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

/**
 * <!-- begin-user-doc -->
 * The <b>Package</b> for the model.
 * It contains accessors for the meta objects to represent
 * <ul>
 *   <li>each class,</li>
 *   <li>each feature of each class,</li>
 *   <li>each operation of each class,</li>
 *   <li>each enum,</li>
 *   <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 * @see hu.bme.mit.gamma.theta.trace.model.TraceModelFactory
 * @model kind="package"
 * @generated
 */
public interface TraceModelPackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "model";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http://www.mit.bme.hu/gamma/theta/trace/Model";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "hu.bme.mit.gamma.theta.trace.model";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	TraceModelPackage eINSTANCE = hu.bme.mit.gamma.theta.trace.model.impl.TraceModelPackageImpl.init();

	/**
	 * The meta object id for the '{@link hu.bme.mit.gamma.theta.trace.model.impl.XstsStateSequenceImpl <em>Xsts State Sequence</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see hu.bme.mit.gamma.theta.trace.model.impl.XstsStateSequenceImpl
	 * @see hu.bme.mit.gamma.theta.trace.model.impl.TraceModelPackageImpl#getXstsStateSequence()
	 * @generated
	 */
	int XSTS_STATE_SEQUENCE = 0;

	/**
	 * The feature id for the '<em><b>States</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int XSTS_STATE_SEQUENCE__STATES = 0;

	/**
	 * The number of structural features of the '<em>Xsts State Sequence</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int XSTS_STATE_SEQUENCE_FEATURE_COUNT = 1;

	/**
	 * The number of operations of the '<em>Xsts State Sequence</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int XSTS_STATE_SEQUENCE_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link hu.bme.mit.gamma.theta.trace.model.impl.XstsStateImpl <em>Xsts State</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see hu.bme.mit.gamma.theta.trace.model.impl.XstsStateImpl
	 * @see hu.bme.mit.gamma.theta.trace.model.impl.TraceModelPackageImpl#getXstsState()
	 * @generated
	 */
	int XSTS_STATE = 1;

	/**
	 * The feature id for the '<em><b>State</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int XSTS_STATE__STATE = 0;

	/**
	 * The feature id for the '<em><b>Annotations</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int XSTS_STATE__ANNOTATIONS = 1;

	/**
	 * The number of structural features of the '<em>Xsts State</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int XSTS_STATE_FEATURE_COUNT = 2;

	/**
	 * The number of operations of the '<em>Xsts State</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int XSTS_STATE_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link hu.bme.mit.gamma.theta.trace.model.impl.ExplStateImpl <em>Expl State</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see hu.bme.mit.gamma.theta.trace.model.impl.ExplStateImpl
	 * @see hu.bme.mit.gamma.theta.trace.model.impl.TraceModelPackageImpl#getExplState()
	 * @generated
	 */
	int EXPL_STATE = 2;

	/**
	 * The feature id for the '<em><b>Valuations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXPL_STATE__VALUATIONS = 0;

	/**
	 * The number of structural features of the '<em>Expl State</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXPL_STATE_FEATURE_COUNT = 1;

	/**
	 * The number of operations of the '<em>Expl State</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXPL_STATE_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link hu.bme.mit.gamma.theta.trace.model.impl.VariableValuationImpl <em>Variable Valuation</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see hu.bme.mit.gamma.theta.trace.model.impl.VariableValuationImpl
	 * @see hu.bme.mit.gamma.theta.trace.model.impl.TraceModelPackageImpl#getVariableValuation()
	 * @generated
	 */
	int VARIABLE_VALUATION = 3;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VARIABLE_VALUATION__NAME = 0;

	/**
	 * The feature id for the '<em><b>Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VARIABLE_VALUATION__VALUE = 1;

	/**
	 * The number of structural features of the '<em>Variable Valuation</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VARIABLE_VALUATION_FEATURE_COUNT = 2;

	/**
	 * The number of operations of the '<em>Variable Valuation</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VARIABLE_VALUATION_OPERATION_COUNT = 0;


	/**
	 * Returns the meta object for class '{@link hu.bme.mit.gamma.theta.trace.model.XstsStateSequence <em>Xsts State Sequence</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Xsts State Sequence</em>'.
	 * @see hu.bme.mit.gamma.theta.trace.model.XstsStateSequence
	 * @generated
	 */
	EClass getXstsStateSequence();

	/**
	 * Returns the meta object for the containment reference list '{@link hu.bme.mit.gamma.theta.trace.model.XstsStateSequence#getStates <em>States</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>States</em>'.
	 * @see hu.bme.mit.gamma.theta.trace.model.XstsStateSequence#getStates()
	 * @see #getXstsStateSequence()
	 * @generated
	 */
	EReference getXstsStateSequence_States();

	/**
	 * Returns the meta object for class '{@link hu.bme.mit.gamma.theta.trace.model.XstsState <em>Xsts State</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Xsts State</em>'.
	 * @see hu.bme.mit.gamma.theta.trace.model.XstsState
	 * @generated
	 */
	EClass getXstsState();

	/**
	 * Returns the meta object for the containment reference '{@link hu.bme.mit.gamma.theta.trace.model.XstsState#getState <em>State</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>State</em>'.
	 * @see hu.bme.mit.gamma.theta.trace.model.XstsState#getState()
	 * @see #getXstsState()
	 * @generated
	 */
	EReference getXstsState_State();

	/**
	 * Returns the meta object for the attribute list '{@link hu.bme.mit.gamma.theta.trace.model.XstsState#getAnnotations <em>Annotations</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Annotations</em>'.
	 * @see hu.bme.mit.gamma.theta.trace.model.XstsState#getAnnotations()
	 * @see #getXstsState()
	 * @generated
	 */
	EAttribute getXstsState_Annotations();

	/**
	 * Returns the meta object for class '{@link hu.bme.mit.gamma.theta.trace.model.ExplState <em>Expl State</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Expl State</em>'.
	 * @see hu.bme.mit.gamma.theta.trace.model.ExplState
	 * @generated
	 */
	EClass getExplState();

	/**
	 * Returns the meta object for the containment reference list '{@link hu.bme.mit.gamma.theta.trace.model.ExplState#getValuations <em>Valuations</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Valuations</em>'.
	 * @see hu.bme.mit.gamma.theta.trace.model.ExplState#getValuations()
	 * @see #getExplState()
	 * @generated
	 */
	EReference getExplState_Valuations();

	/**
	 * Returns the meta object for class '{@link hu.bme.mit.gamma.theta.trace.model.VariableValuation <em>Variable Valuation</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Variable Valuation</em>'.
	 * @see hu.bme.mit.gamma.theta.trace.model.VariableValuation
	 * @generated
	 */
	EClass getVariableValuation();

	/**
	 * Returns the meta object for the attribute '{@link hu.bme.mit.gamma.theta.trace.model.VariableValuation#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see hu.bme.mit.gamma.theta.trace.model.VariableValuation#getName()
	 * @see #getVariableValuation()
	 * @generated
	 */
	EAttribute getVariableValuation_Name();

	/**
	 * Returns the meta object for the attribute '{@link hu.bme.mit.gamma.theta.trace.model.VariableValuation#getValue <em>Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Value</em>'.
	 * @see hu.bme.mit.gamma.theta.trace.model.VariableValuation#getValue()
	 * @see #getVariableValuation()
	 * @generated
	 */
	EAttribute getVariableValuation_Value();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	TraceModelFactory getTraceModelFactory();

	/**
	 * <!-- begin-user-doc -->
	 * Defines literals for the meta objects that represent
	 * <ul>
	 *   <li>each class,</li>
	 *   <li>each feature of each class,</li>
	 *   <li>each operation of each class,</li>
	 *   <li>each enum,</li>
	 *   <li>and each data type</li>
	 * </ul>
	 * <!-- end-user-doc -->
	 * @generated
	 */
	interface Literals {
		/**
		 * The meta object literal for the '{@link hu.bme.mit.gamma.theta.trace.model.impl.XstsStateSequenceImpl <em>Xsts State Sequence</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see hu.bme.mit.gamma.theta.trace.model.impl.XstsStateSequenceImpl
		 * @see hu.bme.mit.gamma.theta.trace.model.impl.TraceModelPackageImpl#getXstsStateSequence()
		 * @generated
		 */
		EClass XSTS_STATE_SEQUENCE = eINSTANCE.getXstsStateSequence();

		/**
		 * The meta object literal for the '<em><b>States</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference XSTS_STATE_SEQUENCE__STATES = eINSTANCE.getXstsStateSequence_States();

		/**
		 * The meta object literal for the '{@link hu.bme.mit.gamma.theta.trace.model.impl.XstsStateImpl <em>Xsts State</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see hu.bme.mit.gamma.theta.trace.model.impl.XstsStateImpl
		 * @see hu.bme.mit.gamma.theta.trace.model.impl.TraceModelPackageImpl#getXstsState()
		 * @generated
		 */
		EClass XSTS_STATE = eINSTANCE.getXstsState();

		/**
		 * The meta object literal for the '<em><b>State</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference XSTS_STATE__STATE = eINSTANCE.getXstsState_State();

		/**
		 * The meta object literal for the '<em><b>Annotations</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute XSTS_STATE__ANNOTATIONS = eINSTANCE.getXstsState_Annotations();

		/**
		 * The meta object literal for the '{@link hu.bme.mit.gamma.theta.trace.model.impl.ExplStateImpl <em>Expl State</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see hu.bme.mit.gamma.theta.trace.model.impl.ExplStateImpl
		 * @see hu.bme.mit.gamma.theta.trace.model.impl.TraceModelPackageImpl#getExplState()
		 * @generated
		 */
		EClass EXPL_STATE = eINSTANCE.getExplState();

		/**
		 * The meta object literal for the '<em><b>Valuations</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference EXPL_STATE__VALUATIONS = eINSTANCE.getExplState_Valuations();

		/**
		 * The meta object literal for the '{@link hu.bme.mit.gamma.theta.trace.model.impl.VariableValuationImpl <em>Variable Valuation</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see hu.bme.mit.gamma.theta.trace.model.impl.VariableValuationImpl
		 * @see hu.bme.mit.gamma.theta.trace.model.impl.TraceModelPackageImpl#getVariableValuation()
		 * @generated
		 */
		EClass VARIABLE_VALUATION = eINSTANCE.getVariableValuation();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute VARIABLE_VALUATION__NAME = eINSTANCE.getVariableValuation_Name();

		/**
		 * The meta object literal for the '<em><b>Value</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute VARIABLE_VALUATION__VALUE = eINSTANCE.getVariableValuation_Value();

	}

} //TraceModelPackage
