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
import org.emftext.language.java.statements.JumpLabel;
import org.emftext.language.java.statements.StatementsPackage;

/**
 * A decider that looks for jump labels declared in a classifier.
 */
public class JumpLabelDecider extends AbstractDecider {

	public boolean canFindTargetsFor(EObject referenceContainer,
			EReference containingReference) {
		return true;
	}

	public boolean continueAfterReference() {
		return false;
	}

	public boolean isPossibleTarget(String id, EObject element) {
		if (element instanceof JumpLabel) {
			NamedElement ne = (NamedElement) element;
			return id.equals(ne.getName());
		}
		return false;
	}

	public boolean containsCandidates(EObject container, EReference containingReference) {
		if (StatementsPackage.Literals.STATEMENT_CONTAINER__STATEMENT.equals(containingReference)) {
			return true;
		}
		if (StatementsPackage.Literals.STATEMENT_LIST_CONTAINER__STATEMENTS.equals(containingReference)) {
			return true;
		}
		return false;
	}

}
