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

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.emftext.language.java.annotations.AnnotationAttributeSetting;
import org.emftext.language.java.classifiers.Classifier;
import org.emftext.language.java.commons.NamedElement;
import org.emftext.language.java.members.InterfaceMethod;
import org.emftext.language.java.members.MemberContainer;
import org.emftext.language.java.members.MembersPackage;
import org.emftext.language.java.members.Method;

/**
 * To resolve annotation attributes.
 */
public class InterfaceMethodDecider extends AbstractDecider {


	public boolean canFindTargetsFor(EObject referenceContainer,
			EReference containingReference) {
		if (referenceContainer instanceof AnnotationAttributeSetting) {
			return true;
		}
		return false;
	}

	public EList<? extends EObject> getAdditionalCandidates(String identifier, EObject container) {
		if (container instanceof Classifier) {
			return ((Classifier)container).getAllMembers((Classifier)container);
		}
		return null;
	}

	public boolean isPossibleTarget(String id, EObject element) {
		if (element instanceof InterfaceMethod) {
			Method method = (Method) element;
			if (id.equals(method.getName())) {
				NamedElement ne = (NamedElement) element;
				return id.equals(ne.getName());
			}
		}
		return false;
	}

	public boolean containsCandidates(EObject container, EReference containingReference) {
		if (container instanceof MemberContainer) {
			if (MembersPackage.Literals.MEMBER_CONTAINER__MEMBERS.equals(containingReference)) {
				return  true;
			}
		}
		return false;
	}


}
