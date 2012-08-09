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
package org.emftext.language.java.resource.java.analysis;

import java.util.Map;

import org.eclipse.emf.ecore.util.EcoreUtil;
import org.emftext.language.java.classifiers.ConcreteClassifier;
import org.emftext.language.java.imports.ClassifierImport;
import org.emftext.language.java.resource.java.IJavaReferenceResolveResult;
import org.emftext.language.java.resource.java.IJavaReferenceResolver;

public class ClassifierImportClassifierReferenceResolver implements
	IJavaReferenceResolver<ClassifierImport, ConcreteClassifier> {

	JavaDefaultResolverDelegate<ClassifierImport, ConcreteClassifier> delegate =
		new JavaDefaultResolverDelegate<ClassifierImport, ConcreteClassifier>();

	public java.lang.String deResolve(ConcreteClassifier element, ClassifierImport container, org.eclipse.emf.ecore.EReference reference) {
		if (element.eIsProxy()) {
			return delegate.deResolve(element, container, reference);
		}
		return element.getName();
	}

	public void resolve(java.lang.String identifier, ClassifierImport theImport, org.eclipse.emf.ecore.EReference reference, int position, boolean resolveFuzzy, IJavaReferenceResolveResult<ConcreteClassifier> result) {
		ConcreteClassifier importedClassifier = theImport.getImportedClassifier(identifier);
		if (importedClassifier != null) {
			importedClassifier = (ConcreteClassifier) EcoreUtil.resolve(importedClassifier, theImport.eResource());
			if (!importedClassifier.eIsProxy()) {
				result.addMapping(identifier, importedClassifier);
			}
		}
	}

	public void setOptions(Map<?, ?> options) {
	}
}
