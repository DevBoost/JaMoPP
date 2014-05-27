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
package org.emftext.language.java.extensions.commons;

import java.util.Iterator;

import org.eclipse.emf.ecore.util.EcoreUtil;
import org.emftext.language.java.JavaClasspath;
import org.emftext.language.java.JavaUniquePathConstructor;
import org.emftext.language.java.classifiers.ConcreteClassifier;
import org.emftext.language.java.commons.NamespaceAwareElement;

public class NamespaceAwareElementExtension {

	/**
	 * Converts the namespaces array of the given namespace aware element into a
	 * String representation using package (.) and class ($) delimiters. The
	 * method uses the classpath to determine for each element of the namespace
	 * if it identifies a package or a class.
	 * 
	 * @param naElement
	 * @return single string representation of namespace
	 */
	public static String getNamespacesAsString(NamespaceAwareElement me) {
		
		JavaClasspath javaClasspath = JavaClasspath.get(me);
		String containerName = "";
		
		Iterator<String> it = me.getNamespaces().iterator();
		while (it.hasNext()) {
			String namespaceFragment = it.next();
			// Does it point at a classifier or a package as container?
			String assumedPackageName    = containerName + namespaceFragment + JavaUniquePathConstructor.PACKAGE_SEPARATOR;
			String assumedClassifierName = containerName + namespaceFragment + JavaUniquePathConstructor.CLASSIFIER_SEPARATOR;
			
			if (it.hasNext()) {
				if (javaClasspath.existsPackage(assumedClassifierName)) {
					containerName = assumedClassifierName;
				} else {
					// Assume package
					containerName = assumedPackageName;
				}
			} else {
				if (javaClasspath.existsPackage(assumedPackageName)) {
					// A package is always available as key
					containerName = assumedPackageName;
				} else {
					// Assume classifier that is not key, but value in the map
					containerName = assumedClassifierName;
				}
			}
		}
		
		return containerName;
	}
	
	/**
	 * Assuming the namespace identifies a classifier, that classifier is
	 * returned.
	 * 
	 * @return classifier at namespace
	 */
	public static ConcreteClassifier getClassifierAtNamespaces(NamespaceAwareElement me) {
		
		String fullQualifiedName = me.getNamespacesAsString();
		if (fullQualifiedName == null || fullQualifiedName.endsWith(JavaUniquePathConstructor.PACKAGE_SEPARATOR)) {
			return null;
		}
		
		// Cut the trailing separator
		fullQualifiedName = fullQualifiedName.substring(0, fullQualifiedName.length() - 1);
		
		ConcreteClassifier proxy = me.getConcreteClassifierProxy(fullQualifiedName);
		return (ConcreteClassifier) EcoreUtil.resolve(proxy, me);
	}
}
