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

import java.util.Iterator;

import org.eclipse.emf.common.util.EList;
import org.emftext.language.java.JavaUniquePathConstructor;
import org.emftext.language.java.classifiers.ConcreteClassifier;
import org.emftext.language.java.util.UniqueEList;

public class PackageExtension {
	
	/**
	 * @return all classes in the same package
	 */
	public static EList<ConcreteClassifier> getClassifiersInSamePackage(org.emftext.language.java.containers.Package me) {
		EList<ConcreteClassifier> defaultImportList = new UniqueEList<ConcreteClassifier>();
		
		String packageName = me.getNamespacesAsString();
		
		defaultImportList.addAll(me.getConcreteClassifierProxies(
				packageName + me.getName(), "*"));

		return defaultImportList;
	}
	
	public static String getNamespacesAsString(org.emftext.language.java.containers.Package me) {
		String packageName = ""; 
		for (Iterator<String> it = me.getNamespaces().iterator(); it.hasNext(); ) {
			String namespaceFragment = it.next();
			packageName = packageName + namespaceFragment + JavaUniquePathConstructor.PACKAGE_SEPARATOR;
		}
		return packageName;
	}
}
