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
package org.emftext.language.java.extensions.containers;

import org.eclipse.emf.common.util.EList;
import org.emftext.language.java.classifiers.Annotation;
import org.emftext.language.java.classifiers.ConcreteClassifier;
import org.emftext.language.java.classifiers.Enumeration;
import org.emftext.language.java.classifiers.Interface;
import org.emftext.language.java.containers.CompilationUnit;
import org.emftext.language.java.imports.ClassifierImport;
import org.emftext.language.java.imports.ImportsFactory;
import org.emftext.language.java.imports.PackageImport;
import org.emftext.language.java.util.UniqueEList;

public class CompilationUnitExtension {
	
	/**
	 * @param name name of the contained Classifier
	 * @return the Classifier
	 */
	public static ConcreteClassifier getContainedClassifier(CompilationUnit me, String name) {
		if (name == null) {
			return null;
		}
		for (ConcreteClassifier candidate : me.getClassifiers()) {
			if (name.equals(candidate.getName())) {
				return candidate;
			}
		}
		return null;
	}
	
	/**
	 * @return all classes in the same package imports
	 */
	public static EList<ConcreteClassifier> getClassifiersInSamePackage(CompilationUnit me) {
		EList<ConcreteClassifier> defaultImportList = new UniqueEList<ConcreteClassifier>();
		
		String packageName = me.getNamespacesAsString();

		//locally defined in this container
		defaultImportList.addAll(me.getClassifiers());
		
		defaultImportList.addAll(me.getConcreteClassifierProxies(
				packageName, "*"));

		return defaultImportList;
	}
	
	/**
	 * @return the only class contained directly in the compilation unit (if one exists)
	 */
	public static org.emftext.language.java.classifiers.Class getContainedClass(CompilationUnit me) {
		if (me.getClassifiers().size() != 1) {
			return null;
		}
		if (me.getClassifiers().get(0) instanceof org.emftext.language.java.classifiers.Class) {
			return (org.emftext.language.java.classifiers.Class) me.getClassifiers().get(0);
		}
		return null;
	}
	
	/**
	 * @return the only interface contained directly in the compilation unit (if one exists)
	 */
	public static Interface getContainedInterface(CompilationUnit me) {
		if (me.getClassifiers().size() != 1) {
			return null;
		}
		if (me.getClassifiers().get(0) instanceof Interface) {
			return (Interface) me.getClassifiers().get(0);
		}
		return null;
	}

	/**
	 * @return the only annotation contained directly in the compilation unit (if one exists)
	 */
	public static Annotation getContainedAnnotation(CompilationUnit me) {
		if (me.getClassifiers().size() != 1) {
			return null;
		}
		if (me.getClassifiers().get(0) instanceof Annotation) {
			return (Annotation) me.getClassifiers().get(0);
		}
		return null;
	}
	
	/**
	 * @return the only enumeration contained directly in the compilation unit (if one exists)
	 */
	public static Enumeration getContainedEnumeration(CompilationUnit me) {
		if (me.getClassifiers().size() != 1) {
			return null;
		}
		if (me.getClassifiers().get(0) instanceof Enumeration) {
			return (Enumeration) me.getClassifiers().get(0);
		}
		return null;
	}
	
	/**
	 * Adds an import of the given class to this compilation unit.
	 */
	public static void addImport(CompilationUnit me, String nameOfClassToImport) {
		ClassifierImport import_ = ImportsFactory.eINSTANCE.createClassifierImport();
		ConcreteClassifier classToImport = me.getConcreteClassifier(nameOfClassToImport);
		import_.setClassifier(classToImport);
		import_.getNamespaces().addAll(classToImport.getContainingCompilationUnit().getNamespaces());
		me.getImports().add(import_);
	}
	
	/**
	 * Adds an import of the given package to this compilation unit.
	 */
	public static void addPackageImport(CompilationUnit me, String packageName) {
		PackageImport nsImport = ImportsFactory.eINSTANCE.createPackageImport();
		for (String name : packageName.split("\\.")) {
			nsImport.getNamespaces().add(name);
		}
		me.getImports().add(nsImport);
	}
}
