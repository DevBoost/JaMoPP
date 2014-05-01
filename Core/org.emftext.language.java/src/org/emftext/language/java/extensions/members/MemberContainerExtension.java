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
package org.emftext.language.java.extensions.members;

import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.ECollections;
import org.eclipse.emf.common.util.EList;
import org.emftext.language.java.classifiers.ConcreteClassifier;
import org.emftext.language.java.members.Constructor;
import org.emftext.language.java.members.Field;
import org.emftext.language.java.members.Member;
import org.emftext.language.java.members.MemberContainer;
import org.emftext.language.java.members.MembersFactory;
import org.emftext.language.java.members.Method;
import org.emftext.language.java.types.ClassifierReference;
import org.emftext.language.java.types.TypesFactory;

/**
 * A utility class that provides methods that belong to class MemberContainer,
 * but can not go there, because MemberContainer is generated.
 */
public class MemberContainerExtension {

	public static EList<Method> getMethods(MemberContainer me) {
		EList<Method> methodList = new BasicEList<Method>();

		for (Member member : me.getMembers()) {
			if (member instanceof Method) {
				methodList.add((Method) member);
			}
		}	
		return ECollections.unmodifiableEList(methodList);
	}
	
	public static EList<Field> getFields(MemberContainer me) {
		EList<Field> fieldList = new BasicEList<Field>();

		for (Member member : me.getMembers()) {
			if (member instanceof Field) {
				fieldList.add((Field) member);
			}
		}	
		return ECollections.unmodifiableEList(fieldList);
	}
	
	public static EList<Constructor> getConstructors(MemberContainer me) {
		EList<Constructor> constructorList = new BasicEList<Constructor>();

		for (Member member : me.getMembers()) {
			if (member instanceof Constructor) {
				constructorList.add((Constructor) member);
			}
		}	
		return ECollections.unmodifiableEList(constructorList);
	}
	
	public static EList<Member> getMembersByName(MemberContainer me, String name) {
		EList<Member> matchingMembers = new BasicEList<Member>();

		for (Member member : me.getMembers()) {
			if (name.equals(member.getName())) {
				matchingMembers.add(member);
			}
		}	
		return ECollections.unmodifiableEList(matchingMembers);
	}
	
	public static void removeMethods(MemberContainer me, String name) {
		EList<Method> methodsToRemove = new BasicEList<Method>();

		for (Member member : me.getMembers()) {
			if (member instanceof Method) {
				if (name.equals(member.getName())) {
					methodsToRemove.add((Method) member);
				}
			}
		}
		me.getMembers().removeAll(methodsToRemove);
	}
	
	/**
	 * @param name 
	 * @param name
	 * @return classifier with the given name defined in this member container
	 */
	public static ConcreteClassifier getContainedClassifier(MemberContainer me, String name) {
		for (Member member : me.getMembers()) {
			if (member instanceof ConcreteClassifier && name.equals(member.getName())) {
				return (ConcreteClassifier) member;
			}
 		}
		for (Member member : me.getDefaultMembers()) {
			if (member instanceof ConcreteClassifier && name.equals(member.getName())) {
				return (ConcreteClassifier) member;
			}
 		}
		return null;
	}
	
	/**
	 * @param name 
	 * @param name
	 * @return field with the given name defined in this member container
	 */
	public static Field getContainedField(MemberContainer me, String name) {
		for (Member member : me.getMembers()) {
			if (member instanceof Field && name.equals(member.getName())) {
				return (Field) member;
			}
 		}
		for (Member member : me.getDefaultMembers()) {
			if (member instanceof Field && name.equals(member.getName())) {
				return (Field) member;
			}
 		}
		return null;
	}

	/**
	 * @param name the method name to search for
	 * 
	 * @return method with the given name defined in this member container;
	 *         null, if there is no such method 
	 *         or if there are multiple methods with the same name
	 */
	public static Method getContainedMethod(MemberContainer me, String name) {
		Method found = null;
		for (Member member : me.getMembers()) {
			if (member instanceof Method && name.equals(member.getName())) {
				if (found != null) {
					return null;
				}
				else {
					found = (Method) member;
				}
			}
 		}
 		for (Member member : me.getDefaultMembers()) {
			if (member instanceof Method && name.equals(member.getName())) {
				if (found != null) {
					return null;
				}
				else {
					found = (Method) member;
				}
			}
 		}
		return found;
	}
	
	public static Field createField(MemberContainer me, String name, String qualifiedTypeName) {
		Field field = MembersFactory.eINSTANCE.createField();
		field.setName(name);
		ClassifierReference typeRef = TypesFactory.eINSTANCE.createClassifierReference();
		typeRef.setTarget(me.getConcreteClassifier(qualifiedTypeName));
		field.setTypeReference(typeRef);
		me.getMembers().add(field);
		return field;
	}
}
