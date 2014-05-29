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

import java.util.List;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.emftext.language.java.JavaUniquePathConstructor;
import org.emftext.language.java.classifiers.ConcreteClassifier;
import org.emftext.language.java.classifiers.Interface;
import org.emftext.language.java.commons.Commentable;
import org.emftext.language.java.containers.CompilationUnit;
import org.emftext.language.java.members.Member;
import org.emftext.language.java.modifiers.AnnotableAndModifiable;
import org.emftext.language.java.types.ClassifierReference;
import org.emftext.language.java.types.TypeReference;
import org.emftext.language.java.util.UniqueEList;

public class ConcreteClassifierExtension {
	
	public static EList<ConcreteClassifier> getAllInnerClassifiers(ConcreteClassifier me) {	
		EList<ConcreteClassifier> innerClassifierList = new UniqueEList<ConcreteClassifier>();

		innerClassifierList.addAll(me.getInnerClassifiers());

		for (ConcreteClassifier superClassifier : me.getAllSuperClassifiers()) {
			EList<ConcreteClassifier> superInnerList = superClassifier
					.getInnerClassifiers();

			for (ConcreteClassifier superInner : superInnerList) {
				if (superInner.eIsProxy()) {
					superInner = (ConcreteClassifier) EcoreUtil.resolve(
							superInner, me);
				}
				if (!superInner.isHidden(me)) {
					innerClassifierList.add(superInner);
				}
			}
		}	
		return innerClassifierList;
	}
	
	public static EList<ConcreteClassifier> getInnerClassifiers(ConcreteClassifier me) {
		if (me.eIsProxy()) {
			 String uriString = ((InternalEObject) me).eProxyURI().trimFragment().toString();
			 String fullName = uriString.substring(JavaUniquePathConstructor.JAVA_CLASSIFIER_PATHMAP.length(), 
					 uriString.length() - ".java".length()) + "$";
			 return me.getConcreteClassifierProxies(fullName, "*");
		} else {
			String suffix = "";
			ConcreteClassifier containingClass = me;
			while (containingClass.eContainer() instanceof ConcreteClassifier) {
				containingClass = (ConcreteClassifier) containingClass.eContainer();
				suffix = containingClass.getName() + JavaUniquePathConstructor.CLASSIFIER_SEPARATOR + suffix;
			}
			if (containingClass.eContainer() instanceof CompilationUnit) {
				CompilationUnit compilationUnit = (CompilationUnit) containingClass.eContainer();
			    String fullName = compilationUnit.getNamespacesAsString() + suffix + 
			    		me.getName() + JavaUniquePathConstructor.CLASSIFIER_SEPARATOR;
			    return me.getConcreteClassifierProxies(fullName, "*");
			}
		}

		// For classes declared locally inside methods that are not registered
		// in the class path
		EList<ConcreteClassifier> result = new UniqueEList<ConcreteClassifier>();
		// Can not call ClassifierUtil.getAllMembers, because it will try to
		// call this method!
		for (Member member : me.getMembers()) {
			if (member instanceof ConcreteClassifier) {
				result.add((ConcreteClassifier) member);
			}
		}
		for (ConcreteClassifier superClassifier : me.getAllSuperClassifiers()) {
			for (Member member : superClassifier.getMembers()) {
				if (member instanceof ConcreteClassifier) {
					result.add((ConcreteClassifier) member);
				}
			}
		}
		
		return result;
	}
	
	public static EList<ClassifierReference> getSuperTypeReferences(ConcreteClassifier me) {
		EList<ClassifierReference> typeReferenceList = new UniqueEList<ClassifierReference>();
		if (me instanceof org.emftext.language.java.classifiers.Class) {
			org.emftext.language.java.classifiers.Class javaClass = (org.emftext.language.java.classifiers.Class) me;
			if (javaClass.getExtends() != null) {
				ClassifierReference classifierReference = javaClass.getExtends().getPureClassifierReference();
				typeReferenceList.add(classifierReference);
				ConcreteClassifier target = (ConcreteClassifier) classifierReference.getTarget();
				if (!me.isJavaLangObject(target)) {
					typeReferenceList.addAll(target.getSuperTypeReferences());
				}
			}	
			for (TypeReference interfaceReference : javaClass.getImplements()) {
				ClassifierReference classifierReference = interfaceReference.getPureClassifierReference();
				typeReferenceList.add(classifierReference);
				typeReferenceList.addAll(((ConcreteClassifier) classifierReference.getTarget()).getSuperTypeReferences());
			}
		} else if (me instanceof Interface) {
			Interface javaInterface = (Interface) me;
			for (TypeReference interfaceReference : javaInterface.getExtends()) {
				ClassifierReference classifierReference = interfaceReference.getPureClassifierReference();
				typeReferenceList.add(classifierReference);
				typeReferenceList.addAll(((ConcreteClassifier) classifierReference.getTarget()).getSuperTypeReferences());
			}
		}
		return typeReferenceList;
	}
	
	/**
	 * Returns all members of the given classifier including inner classes and 
	 * all members of super types (extended classes and implemented interfaces).
	 * 
	 * @param context to check protected visibility
	 * @return member list
	 */
	public static EList<Member> getAllMembers(ConcreteClassifier me, Commentable context) {
		EList<Member> memberList = new UniqueEList<Member>();

		ConcreteClassifier concreteClassifier = (ConcreteClassifier) me;
		memberList.addAll(concreteClassifier.getMembers());
		memberList.addAll(concreteClassifier.getDefaultMembers());
		// Because inner classes are found in separate class files
		memberList.addAll(concreteClassifier.getAllInnerClassifiers());
		
		for (ConcreteClassifier superClassifier : me.getAllSuperClassifiers()) {
			for(Member member : superClassifier.getMembers()) {
				if(member instanceof AnnotableAndModifiable) {
					AnnotableAndModifiable modifiable = (AnnotableAndModifiable) member;

					if(!modifiable.isHidden(context)) {
						memberList.add(member);
					}
				}
				else {
					memberList.add(member);
				}
			}
			memberList.addAll(superClassifier.getDefaultMembers());
		}
		return memberList;
	}

	/**
	 * Returns the qualified name of this concrete classifier.
	 */
	public static String getQualifiedName(ConcreteClassifier me) {
			
		StringBuilder qualifiedName = new StringBuilder();
		List<String> packageParts = me.getContainingPackageName();
		for (String packagePart : packageParts) {
			qualifiedName.append(packagePart);
			qualifiedName.append(".");
		}
		qualifiedName.append(me.getName());
		return qualifiedName.toString();
	}

	public static boolean isJavaLangObject(ConcreteClassifier clazz) {
		String name = clazz.getName();
		if (!"Object".equals(name)) {
			return false;
		}
		
		EList<String> packageName = clazz.getContainingPackageName();
		if (packageName.size() != 2) {
			return false;
		}
		if (!("java".equals(packageName.get(0)))) {
			return false;
		}
		if (!("lang".equals(packageName.get(1)))) {
			return false;
		}
		return true;
	}
}
