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
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.emftext.language.java.JavaUniquePathConstructor;
import org.emftext.language.java.classifiers.Classifier;
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
			List<ConcreteClassifier> superInnerList = superClassifier
					.getInnerClassifiers();

			for (ConcreteClassifier superInner : superInnerList) {
				if (superInner.eIsProxy()) {
					EObject resolved = EcoreUtil.resolve(superInner, me);
					superInner = (ConcreteClassifier) resolved;
				}
				
				boolean isVisible = !superInner.isHidden(me);
				if (isVisible) {
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
			
			// Add super type of class to super type list
			TypeReference superClass = javaClass.getExtends();
			if (superClass != null) {
				ClassifierReference classifierReference = superClass.getPureClassifierReference();
				typeReferenceList.add(classifierReference);
				Classifier target = classifierReference.getTarget();
				ConcreteClassifier concreteTarget = (ConcreteClassifier) target;
				if (!me.isJavaLangObject(concreteTarget)) {
					typeReferenceList.addAll(concreteTarget.getSuperTypeReferences());
				}
			}
			
			// Add all implemented interfaces to super type list
			addSuperTypes(javaClass.getImplements(), typeReferenceList);
			
		} else if (me instanceof Interface) {
			Interface javaInterface = (Interface) me;
			
			// Add all super interfaces to super type list
			addSuperTypes(javaInterface.getExtends(), typeReferenceList);
		}
		return typeReferenceList;
	}

	private static void addSuperTypes(List<TypeReference> typeReferences,
			List<ClassifierReference> superTypeReferences) {
		
		for (TypeReference interfaceReference : typeReferences) {
			addSuperType(interfaceReference, superTypeReferences);
		}
	}

	private static void addSuperType(TypeReference typeReference,
			List<ClassifierReference> superTypeReferences) {
		
		ClassifierReference classifierReference = typeReference.getPureClassifierReference();
		superTypeReferences.add(classifierReference);
		Classifier target = classifierReference.getTarget();
		ConcreteClassifier concreteTarget = (ConcreteClassifier) target;
		superTypeReferences.addAll(concreteTarget.getSuperTypeReferences());
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
			for (Member member : superClassifier.getMembers()) {
				if (member instanceof AnnotableAndModifiable) {
					AnnotableAndModifiable modifiable = (AnnotableAndModifiable) member;

					boolean isVisible = !modifiable.isHidden(context);
					if (isVisible) {
						memberList.add(member);
					}
				} else {
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

	/**
	 * Returns <code>true</code> if the given {@link ConcreteClassifier} is
	 * <code>java.lang.Object</code>. Attention: This method does not take the
	 * {@link ConcreteClassifier} on which the method is called (<code>me</code>
	 * ) as argument as this is not used in the methods implementation.
	 * 
	 * @param clazz
	 *            the class to check
	 * @return <code>true</code> if <code>clazz</code> represents
	 *         <code>java.lang.Object</code>, otherwise <code>false</code>
	 */
	public static boolean isJavaLangObject(ConcreteClassifier clazz) {
		String name = clazz.getName();
		if (!"Object".equals(name)) {
			return false;
		}
		
		List<String> packageName = clazz.getContainingPackageName();
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
