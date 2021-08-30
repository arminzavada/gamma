/**
 */
package hu.bme.mit.gamma.theta.trace.model.impl;

import hu.bme.mit.gamma.theta.trace.model.*;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.impl.EFactoryImpl;

import org.eclipse.emf.ecore.plugin.EcorePlugin;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class TraceModelFactoryImpl extends EFactoryImpl implements TraceModelFactory {
	/**
	 * Creates the default factory implementation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static TraceModelFactory init() {
		try {
			TraceModelFactory theTraceModelFactory = (TraceModelFactory)EPackage.Registry.INSTANCE.getEFactory(TraceModelPackage.eNS_URI);
			if (theTraceModelFactory != null) {
				return theTraceModelFactory;
			}
		}
		catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new TraceModelFactoryImpl();
	}

	/**
	 * Creates an instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TraceModelFactoryImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EObject create(EClass eClass) {
		switch (eClass.getClassifierID()) {
			case TraceModelPackage.XSTS_STATE_SEQUENCE: return createXstsStateSequence();
			case TraceModelPackage.XSTS_STATE: return createXstsState();
			case TraceModelPackage.EXPL_STATE: return createExplState();
			case TraceModelPackage.VARIABLE_VALUATION: return createVariableValuation();
			default:
				throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public XstsStateSequence createXstsStateSequence() {
		XstsStateSequenceImpl xstsStateSequence = new XstsStateSequenceImpl();
		return xstsStateSequence;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public XstsState createXstsState() {
		XstsStateImpl xstsState = new XstsStateImpl();
		return xstsState;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ExplState createExplState() {
		ExplStateImpl explState = new ExplStateImpl();
		return explState;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public VariableValuation createVariableValuation() {
		VariableValuationImpl variableValuation = new VariableValuationImpl();
		return variableValuation;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TraceModelPackage getTraceModelPackage() {
		return (TraceModelPackage)getEPackage();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @deprecated
	 * @generated
	 */
	@Deprecated
	public static TraceModelPackage getPackage() {
		return TraceModelPackage.eINSTANCE;
	}

} //TraceModelFactoryImpl
