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
package org.emftext.language.java.extensions.classifiers;

import org.eclipse.emf.common.util.EList;
import org.emftext.language.java.classifiers.Classifier;
import org.emftext.language.java.classifiers.ConcreteClassifier;
import org.emftext.language.java.containers.CompilationUnit;

public class ClassifierExtension {
	
	public static EList<ConcreteClassifier> getAllSuperClassifiers(Classifier me) {
		// Method has to be specified in subclasses
		throw new UnsupportedOperationException();
	}

	/**
	 * Adds an import of the given class to the compilation unit that contains
	 * this classifier.
	 */
	public static void addImport(Classifier me, String nameOfClassToImport) {
		CompilationUnit compilationUnit = me.getParentByType(CompilationUnit.class);
		compilationUnit.addImport(nameOfClassToImport);
	}
	
	/**
	 * Adds an import of the given package to the compilation unit that contains
	 * this classifier.
	 */
	public static void addPackageImport(Classifier me, String packageName) {
		CompilationUnit compilationUnit = me.getParentByType(CompilationUnit.class);
		compilationUnit.addPackageImport(packageName);
	}
}
