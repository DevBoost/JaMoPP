/*******************************************************************************
 * Copyright (c) 2006-2012
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
package org.emftext.language.java.util;

import java.util.Iterator;

import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.emftext.language.java.annotations.AnnotationInstance;
import org.emftext.language.java.classifiers.Annotation;
import org.emftext.language.java.classifiers.Class;
import org.emftext.language.java.classifiers.Enumeration;
import org.emftext.language.java.classifiers.Interface;
import org.emftext.language.java.expressions.Expression;
import org.emftext.language.java.expressions.PrimaryExpression;
import org.emftext.language.java.members.EmptyMember;
import org.emftext.language.java.members.MemberContainer;
import org.emftext.language.java.members.MembersFactory;
import org.emftext.language.java.members.Method;
import org.emftext.language.java.parameters.Parameter;
import org.emftext.language.java.parameters.ParametersFactory;
import org.emftext.language.java.statements.Block;
import org.emftext.language.java.types.ClassifierReference;
import org.emftext.language.java.types.TypesFactory;

/**
 * Utility class that enhances and simplifies a Java model based on
 * Java language specifics.
 */
public class JavaModelCompletion {

	/**
	 * Main method to perform the completion for the given resource.
	 *
	 * @param resource
	 */
	public static void complete(Resource resource) {
		for(Iterator<EObject> contentIterator = resource.getAllContents(); contentIterator.hasNext(); ) {
			EObject element = contentIterator.next();
			if (element instanceof Class) {
				addDefaultSuperClass((Class) element);
			}
			if (element instanceof Interface) {
				addDefaultSuperInterface((Interface) element);
			}
			if (element instanceof Enumeration) {
				addMissingEnumerationMembers((Enumeration) element);
			}
			if (element instanceof Annotation) {
				addMissingAnnotationMembers((Annotation) element);
			}
			if (element instanceof EmptyMember) {
				setEmptyMemberName((EmptyMember) element);
			}
			if (element instanceof Block) {
				setBlockName((Block) element);
			}
		}
		simplifyExpressions(resource);
	}

	/**
	 * Adds <code>java.lang.Object</code> as default super class if non is specified.
	 *
	 * @param javaClass
	 */
	public static void addDefaultSuperClass(Class javaClass) {
		if (javaClass.getExtends() == null && javaClass.getDefaultExtends() == null) {
			Class objectClass = javaClass.getObjectClass();
			ClassifierReference classifierReference = TypesFactory.eINSTANCE.createClassifierReference();
			classifierReference.setTarget(objectClass);
			javaClass.setDefaultExtends(classifierReference);
		}
	}

	/**
	 * Adds <code>java.lang.Object</code> as a default super interface to an interface
	 * that implements no other interface.
	 *
	 * @param javaClass
	 */
	public static void addDefaultSuperInterface(Interface javaInterface) {
		if (javaInterface.getExtends().isEmpty() && javaInterface.getDefaultExtends().isEmpty()) {
			Class objectClass = javaInterface.getObjectClass();
			ClassifierReference classifierReference = TypesFactory.eINSTANCE.createClassifierReference();
			classifierReference.setTarget(objectClass);
			javaInterface.getDefaultExtends().add(classifierReference);
		}
	}

	public static void addMissingAnnotationMembers(Annotation annotation) {
		String valueMethodName = "value";
		Method valueMethod = annotation.getContainedMethod(valueMethodName);
		if (valueMethod == null) {
			valueMethod = MembersFactory.eINSTANCE.createInterfaceMethod();
			valueMethod.setName(valueMethodName);
			ClassifierReference type = TypesFactory.eINSTANCE.createClassifierReference();
			type.setTarget(annotation.getConcreteClassifierProxy("java.lang.String"));
			valueMethod.setTypeReference(type);
			annotation.getDefaultMembers().add(valueMethod);
		}
	}

	/**
	 * Adds the additional methods <code>value()</code> and <code>calueOf()</code>
	 * to the given enumeration.
	 *
	 * @param enumeration the enumeration to complete
	 */
	public static void addMissingEnumerationMembers(Enumeration enumeration) {

		//add the values
		String valuesMethodName = "values";
		Method valuesMethod = enumeration.getContainedMethod(valuesMethodName);

		if (valuesMethod == null) {
			valuesMethod = MembersFactory.eINSTANCE.createInterfaceMethod();
			valuesMethod.setName(valuesMethodName);

			ClassifierReference type = TypesFactory.eINSTANCE.createClassifierReference();
			type.setTarget(enumeration);
			valuesMethod.setTypeReference(type);
			enumeration.getDefaultMembers().add(valuesMethod);
		}

		//add the value of method
		String valueOfMethodName = "valueOf";
		Method valueOfMethod = enumeration.getContainedMethod(valueOfMethodName);

		if (valueOfMethod == null) {
			valueOfMethod = MembersFactory.eINSTANCE.createInterfaceMethod();
			valueOfMethod.setName(valueOfMethodName);

			ClassifierReference type = TypesFactory.eINSTANCE.createClassifierReference();
			type.setTarget(enumeration);
			valueOfMethod.setTypeReference(type);

			Parameter strParameter = ParametersFactory.eINSTANCE.createOrdinaryParameter();
			strParameter.setName("str");
			type = TypesFactory.eINSTANCE.createClassifierReference();
			type.setTarget(enumeration.getConcreteClassifierProxy("java.lang.String"));
			strParameter.setTypeReference(type);

			valueOfMethod.getParameters().add(strParameter);
			enumeration.getDefaultMembers().add(valueOfMethod);
		}
	}

	public static void setEmptyMemberName(EmptyMember emptyMember) {
		if (emptyMember.getName() != null) {
			return;
		}
		EObject container = emptyMember.eContainer();
		if (!(container instanceof MemberContainer)) {
			return;
		}
		int idx = ((MemberContainer) container).getMembers().indexOf(emptyMember);
		String name = "Member" + idx;
		emptyMember.setName(name);
	}

	public static void setBlockName(Block block) {
		if (block.getName() != null) {
			return;
		}
		EObject container = block.eContainer();
		if (container instanceof MemberContainer) {
			int idx = ((MemberContainer) container).getMembers().indexOf(block);
			String name = "Member" + idx;
			block.setName(name);
			return;
		}
		block.setName("Block");	
	}

	/**
	 * Simplifies all expression in the given resource by removing empty containers
	 * in all expression trees.
	 *
	 * @param resource
	 */
	public static void simplifyExpressions(Resource resource) {
		simplifyDown(resource.getContents());
	}

	private static void simplifyDown(EList<EObject> parentList) {
		for(EObject child : new BasicEList<EObject>(parentList)) {
			EObject singleContained = getSingleContained(child);
			EObject next = singleContained;
			while (next != null) {
				next = getSingleContained(singleContained);
				if (next != null) {
					singleContained = next;
				}
			}
			if (singleContained != null) {
				EcoreUtil.replace(child, singleContained);
				child = singleContained;
			}
			simplifyDown(child.eContents());
		}
	}

	private static EObject getSingleContained(EObject parent) {
		if (parent.eContainer() instanceof AnnotationInstance) {
			//special case. Might be changed in the future.
			return null;
		}
		if (!(parent instanceof Expression)) {
			return null;
		}
		//never kill a primary
		if (parent instanceof PrimaryExpression) {
			return null;
		}

		EObject singleContained = null;
		for(EObject contained : parent.eContents()) {
			if (singleContained != null) {
				return null;
			}
			singleContained = contained;
		}
		if (!(singleContained instanceof Expression)) {
			return null;
		}

		return singleContained;
	}

}
