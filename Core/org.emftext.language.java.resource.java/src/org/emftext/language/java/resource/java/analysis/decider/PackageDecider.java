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

import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.emftext.language.java.commons.NamedElement;
import org.emftext.language.java.containers.JavaRoot;
import org.emftext.language.java.references.IdentifierReference;
import org.emftext.language.java.references.PackageReference;
import org.emftext.language.java.references.ReferencesFactory;
import org.emftext.language.java.references.ReferencesPackage;

/**
 * A decider that assumes that a package is referenced if the context of the reference
 * allows for a package reference at that position. The decider creates a package
 * element as additional candidate in that case.
 */
public class PackageDecider extends AbstractDecider {

	public boolean canFindTargetsFor(EObject referenceContainer,
			EReference crossReference) {

		if (referenceContainer instanceof IdentifierReference) {
			IdentifierReference idReference = (IdentifierReference) referenceContainer;
			 //a classifier must follow
			if(!(idReference.getNext() instanceof IdentifierReference)) {
				return false;
			}
			if (!referenceContainer.eContainingFeature().equals(ReferencesPackage.Literals.REFERENCE__NEXT)) {
				//maybe the root package
				return true;
			}
			if (referenceContainer.eContainingFeature().equals(ReferencesPackage.Literals.REFERENCE__NEXT) &&
					idReference.eContainer() instanceof IdentifierReference &&
					((IdentifierReference)idReference.eContainer()).getTarget() instanceof PackageReference) {
				//maybe the next sub package
				return true;
			}

		}
		return false;
	}

	public EList<? extends EObject> getAdditionalCandidates(String identifier, EObject container)  {
		if (container instanceof PackageReference) {
			EList<EObject> resultList = new BasicEList<EObject>();
			PackageReference parentPackage = (PackageReference) container;

			PackageReference p = null;
			for (PackageReference sub : parentPackage.getSubpackages()) {
				if (identifier.equals(sub.getName())) {
					p = sub;
					break;
				}
			}
			if (p == null) {
				p = ReferencesFactory.eINSTANCE.createPackageReference();
				p.setName(identifier);
				parentPackage.getSubpackages().add(p);
			}
			resultList.add(p);

			return resultList;
		}
		if (container instanceof JavaRoot && container.eResource() != null) {
			EList<EObject> resultList = new BasicEList<EObject>();

			PackageReference p = ReferencesFactory.eINSTANCE.createPackageReference();
			p.setName(identifier);
			resultList.add(p);

			return resultList;
		}
		return null;
	}

	public boolean containsCandidates(EObject container,
			EReference containingReference) {
		return false;
	}

	public boolean isPossibleTarget(String id, EObject element) {
		if (element instanceof PackageReference) {
			NamedElement ne = (NamedElement) element;
			return id.equals(ne.getName());
		}
		return false;
	}


}
