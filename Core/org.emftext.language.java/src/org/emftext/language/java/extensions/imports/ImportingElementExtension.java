/*******************************************************************************
 * Copyright (c) 2006-2014
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
package org.emftext.language.java.extensions.imports;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.emftext.language.java.JavaClasspath;
import org.emftext.language.java.classifiers.ConcreteClassifier;
import org.emftext.language.java.imports.ImportingElement;
import org.emftext.language.java.util.UniqueEList;

public class ImportingElementExtension {

	public static EList<ConcreteClassifier> getDefaultImports(ImportingElement me) {
		EList<ConcreteClassifier> result = new UniqueEList<ConcreteClassifier>();
		JavaClasspath javaClasspath = JavaClasspath.get(me);
		for (EObject classifier : javaClasspath.getDefaultImports()) {
			result.add((ConcreteClassifier) classifier);
		}
		return result;
	}
}
