/**
 */
package hu.bme.mit.gamma.theta.trace.model.impl;

import hu.bme.mit.gamma.theta.trace.model.ExplState;
import hu.bme.mit.gamma.theta.trace.model.TraceModelPackage;
import hu.bme.mit.gamma.theta.trace.model.VariableValuation;

import java.util.Collection;

import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Expl State</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link hu.bme.mit.gamma.theta.trace.model.impl.ExplStateImpl#getValuations <em>Valuations</em>}</li>
 * </ul>
 *
 * @generated
 */
public class ExplStateImpl extends MinimalEObjectImpl.Container implements ExplState {
	/**
	 * The cached value of the '{@link #getValuations() <em>Valuations</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getValuations()
	 * @generated
	 * @ordered
	 */
	protected EList<VariableValuation> valuations;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ExplStateImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return TraceModelPackage.Literals.EXPL_STATE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<VariableValuation> getValuations() {
		if (valuations == null) {
			valuations = new EObjectContainmentEList<VariableValuation>(VariableValuation.class, this, TraceModelPackage.EXPL_STATE__VALUATIONS);
		}
		return valuations;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case TraceModelPackage.EXPL_STATE__VALUATIONS:
				return ((InternalEList<?>)getValuations()).basicRemove(otherEnd, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case TraceModelPackage.EXPL_STATE__VALUATIONS:
				return getValuations();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case TraceModelPackage.EXPL_STATE__VALUATIONS:
				getValuations().clear();
				getValuations().addAll((Collection<? extends VariableValuation>)newValue);
				return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eUnset(int featureID) {
		switch (featureID) {
			case TraceModelPackage.EXPL_STATE__VALUATIONS:
				getValuations().clear();
				return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID) {
		switch (featureID) {
			case TraceModelPackage.EXPL_STATE__VALUATIONS:
				return valuations != null && !valuations.isEmpty();
		}
		return super.eIsSet(featureID);
	}

} //ExplStateImpl
