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
import org.emftext.language.java.classifiers.ConcreteClassifier;
import org.emftext.language.java.classifiers.Enumeration;
import org.emftext.language.java.classifiers.Interface;
import org.emftext.language.java.members.EnumConstant;
import org.emftext.language.java.types.TypeReference;
import org.emftext.language.java.util.UniqueEList;

public class EnumerationExtension {
	
	/**
	 * @return all interfaces extended by this enumeration.
	 */
	public static EList<ConcreteClassifier> getAllSuperClassifiers(Enumeration me) {
		EList<ConcreteClassifier> result = new UniqueEList<ConcreteClassifier>();
		
		// Enumerations inherit from java.lang.Enum
		org.emftext.language.java.classifiers.Class enumClass = me.getLibClass("Enum");
		result.add(enumClass);
		result.addAll(enumClass.getAllSuperClassifiers());
		
		// Collect all implemented interfaces
		for (TypeReference typeArg : me.getImplements()) {
			ConcreteClassifier superInterface = (ConcreteClassifier) typeArg.getTarget();
			if (superInterface != null) {
				result.add(superInterface);
				if (superInterface instanceof Interface) {
					result.addAll(((Interface)superInterface).getAllSuperClassifiers());
				}
			}
		}
		
		return result;
	}
	
	public static EnumConstant getContainedConstant(Enumeration me, String name) {
		for (EnumConstant constant : me.getConstants()) {
			if (name.equals(constant.getName())) {
				return constant;
			}
 		}
		return null;
	}
}
