/*******************************************************************************
 * Copyright (c) 2006-2012
 * Software Technology Group, Dresden University of Technology
 * DevBoost GmbH, Berlin, Amtsgericht Charlottenburg, HRB 140026
 * 
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *   Software Technology Group - TU Dresden, Germany;
 *   DevBoost GmbH - Berlin, Germany
 *      - initial API and implementation
 ******************************************************************************/
package org.emftext.language.java.resource.java.analysis.decider;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.emftext.language.java.commons.NamedElement;
import org.emftext.language.java.references.MethodCall;
import org.emftext.language.java.references.Reference;
import org.emftext.language.java.references.ReflectiveClassReference;
import org.emftext.language.java.references.SelfReference;
import org.emftext.language.java.statements.ForLoop;
import org.emftext.language.java.statements.LocalVariableStatement;
import org.emftext.language.java.statements.StatementsPackage;
import org.emftext.language.java.statements.Switch;
import org.emftext.language.java.statements.SwitchCase;
import org.emftext.language.java.variables.AdditionalLocalVariable;
import org.emftext.language.java.variables.LocalVariable;
import org.emftext.language.java.variables.VariablesPackage;

/**
 * A decider that looks for local variable declarations.
 */
public class LocalVariableDecider extends AbstractDecider {

	public boolean continueAfterReference() {
		return false;
	}

	public boolean isPossibleTarget(String id, EObject element) {
		if (element instanceof LocalVariable || element instanceof AdditionalLocalVariable) {
			NamedElement ne = (NamedElement) element;
			return id.equals(ne.getName());
		}
		return false;
	}

	public boolean containsCandidates(EObject container, EReference containingReference) {
		if (StatementsPackage.Literals.LOCAL_VARIABLE_STATEMENT__VARIABLE.equals(containingReference)) {
			return true;
		}
		if (VariablesPackage.Literals.LOCAL_VARIABLE__ADDITIONAL_LOCAL_VARIABLES.equals(containingReference)) {
			return true;
		}
		if (StatementsPackage.Literals.FOR_LOOP__INIT.equals(containingReference)) {
			return true;
		}
		return false;
	}

	public boolean walkInto(EObject element) {
		if (element instanceof LocalVariableStatement) {
			if (StatementsPackage.Literals.STATEMENT_CONTAINER__STATEMENT.equals(element.eContainmentFeature())) {
				return true;
			}
			if (StatementsPackage.Literals.STATEMENT_LIST_CONTAINER__STATEMENTS.equals(element.eContainmentFeature())) {
				return true;
			}
		}
		if (element instanceof LocalVariable) {
			if (StatementsPackage.Literals.LOCAL_VARIABLE_STATEMENT__VARIABLE.equals(element.eContainmentFeature())
					|| StatementsPackage.Literals.FOR_LOOP__INIT.equals(element.eContainmentFeature())) {
				return true;
			}
		}
		if (element instanceof ForLoop) {
			return true;
		}
		if (element instanceof Switch) {
			return true;
		}
		if (element instanceof SwitchCase) {
			return true;
		}
		return false;
	}

	public boolean canFindTargetsFor(EObject referenceContainer,
			EReference containingReference) {
		if (referenceContainer instanceof MethodCall) {
			return false;
		}
		if (!(referenceContainer instanceof Reference)) {
			return false;
		}
		Reference reference = (Reference) referenceContainer;
		if (reference.getNext() instanceof ReflectiveClassReference) {
			return false;
		}
		if (reference.getNext() instanceof SelfReference) {
			return false;
		}
		return true;
	}

}
