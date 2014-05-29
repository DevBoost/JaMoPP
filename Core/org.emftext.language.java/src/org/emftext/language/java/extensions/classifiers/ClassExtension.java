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
import org.emftext.language.java.classifiers.Interface;
import org.emftext.language.java.types.PrimitiveType;
import org.emftext.language.java.types.Type;
import org.emftext.language.java.types.TypeReference;
import org.emftext.language.java.types.TypesFactory;
import org.emftext.language.java.util.UniqueEList;

public class ClassExtension {
	
	/**
	 * Recursively collects all super types (extended classes and implemented
	 * interfaces) of the given class.
	 */
	public static EList<ConcreteClassifier> getAllSuperClassifiers(
			org.emftext.language.java.classifiers.Class me) {
		
		EList<ConcreteClassifier> result = new UniqueEList<ConcreteClassifier>();
					
		// Collects all super classes first
		org.emftext.language.java.classifiers.Class superClass = me;
		while (superClass != null && !superClass.eIsProxy() && 
				!me.isJavaLangObject(superClass)) {
			superClass = superClass.getSuperClass();
			if (superClass != null) {
				result.add(superClass);
			}
		}
		
		// Collect all implemented interfaces
		for (TypeReference typeArg : me.getImplements()) {
			ConcreteClassifier superInterface = (ConcreteClassifier) typeArg.getTarget();
			if (superInterface != null) {
				result.add(superInterface);
				if (superInterface instanceof Interface) {
					result.addAll(((Interface) superInterface).getAllSuperClassifiers());
				}
			}
		}
		
		// Collect all implemented interfaces of super classes
		superClass = me.getSuperClass();	
		if (superClass != null && !superClass.eIsProxy() && 
				!me.isJavaLangObject(superClass)) {
			result.addAll(superClass.getAllSuperClassifiers());
		}	

		return result;
	}
	
	/**
	 * @return the direct super class
	 */
	public static org.emftext.language.java.classifiers.Class getSuperClass(
			org.emftext.language.java.classifiers.Class me) {
		
		TypeReference superClassReference = me.getExtends();
		if (superClassReference == null) {
			superClassReference = me.getDefaultExtends();
		}
		
		if (superClassReference == null) {
			return null;
		}
		
		Type result = superClassReference.getTarget();
		if (result instanceof org.emftext.language.java.classifiers.Class) {
			return (org.emftext.language.java.classifiers.Class) result;
		}
		return null;
	}
	
	/**
	 * @return primitive type, if the class can be wrapped
	 */
	public static PrimitiveType unWrapPrimitiveType(
			org.emftext.language.java.classifiers.Class me) {
		
		if (me.getLibClass("Boolean").equals(me)) {
			return TypesFactory.eINSTANCE.createBoolean();
		}
		if (me.getLibClass("Byte").equals(me)) {
			return TypesFactory.eINSTANCE.createByte();
		}
		if (me.getLibClass("Character").equals(me)) {
			return TypesFactory.eINSTANCE.createChar();
		}
		if (me.getLibClass("Float").equals(me)) {
			return TypesFactory.eINSTANCE.createFloat();
		}
		if (me.getLibClass("Double").equals(me)) {
			return TypesFactory.eINSTANCE.createDouble();
		}
		if (me.getLibClass("Integer").equals(me)) {
			return TypesFactory.eINSTANCE.createInt();
		}
		if (me.getLibClass("Long").equals(me)) {
			return TypesFactory.eINSTANCE.createLong();
		}
		if (me.getLibClass("Short").equals(me)) {
			return TypesFactory.eINSTANCE.createShort();
		}
		if (me.getLibClass("Void").equals(me)) {
			return TypesFactory.eINSTANCE.createVoid();
		}
		return null;
	}
}
