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
package org.emftext.language.java.extensions.references;

import org.emftext.language.java.classifiers.AnonymousClass;
import org.emftext.language.java.classifiers.Enumeration;
import org.emftext.language.java.expressions.NestedExpression;
import org.emftext.language.java.literals.Literal;
import org.emftext.language.java.literals.Super;
import org.emftext.language.java.members.AdditionalField;
import org.emftext.language.java.members.EnumConstant;
import org.emftext.language.java.references.ElementReference;
import org.emftext.language.java.references.PrimitiveTypeReference;
import org.emftext.language.java.references.Reference;
import org.emftext.language.java.references.ReferenceableElement;
import org.emftext.language.java.references.ReflectiveClassReference;
import org.emftext.language.java.references.SelfReference;
import org.emftext.language.java.references.StringReference;
import org.emftext.language.java.types.Type;
import org.emftext.language.java.types.TypeReference;
import org.emftext.language.java.types.TypedElement;
import org.emftext.language.java.variables.AdditionalLocalVariable;

public class ReferenceExtension {

	public static Reference getPrevious(Reference me) {
		if (me.eContainer() instanceof Reference) { 
			Reference container = (Reference) me.eContainer(); 
			if (me.equals(container.getNext())) {
				return container;
			}
		}
		return null;
	}
	
	/**
	 * Determines the {@link Type} of the reference. That is, either the type to
	 * which the reference points directly, or the type of the element to which
	 * the reference points.
	 * 
	 * @return the determined type
	 */
	public static Type getReferencedType(Reference me) {
		if (me instanceof Literal) {
			return ((Literal) me).getType();
		}

		Type type = null;

		// Referenced element points to a type
		if (me instanceof TypedElement) {
			TypeReference typeRef = ((TypedElement) me).getTypeReference();
			type = typeRef.getBoundTarget(me);
		}
		// Element points to this or super
		else if (me instanceof SelfReference) {
			Type thisClass = null;
			if (me.getPrevious() != null) {
				thisClass = me.getPrevious().getReferencedType();
			}
			else {
				AnonymousClass anonymousContainer = me.getContainingAnonymousClass();
				if (anonymousContainer != null) {
					thisClass = anonymousContainer;
				}
				else {
					thisClass = me.getContainingConcreteClassifier();	
				}
			}
			
			// Find super class if "self" is "super"
			if (((SelfReference) me).getSelf() instanceof Super) {
				if (thisClass instanceof org.emftext.language.java.classifiers.Class) {
					return ((org.emftext.language.java.classifiers.Class) thisClass).getSuperClass();
				}
				if (thisClass instanceof AnonymousClass) {
					return ((AnonymousClass)thisClass).getSuperClassifier();
				}
			}
			
			return thisClass;
		}
		// Element points to the object's class object
		else if (me instanceof ReflectiveClassReference) {
			return me.getClassClass();
		}
		// Referenced element points to an element with a type
		else if (me instanceof ElementReference) {
			ReferenceableElement target = 
				(ReferenceableElement) ((ElementReference) me).getTarget();
			
			if (target == null) {
				return null;
			}
			if (target.eIsProxy()) {
				type = null;
			}
			
			// Navigate through AdditionalLocalVariable or Field
			if (target instanceof AdditionalLocalVariable) {
				target = (ReferenceableElement) target.eContainer();
			}
			if (target instanceof AdditionalField) {
				target = (ReferenceableElement) target.eContainer();
			}
			if (target instanceof TypedElement) {
				TypeReference typeRef = ((TypedElement) target).getTypeReference();
				if (typeRef != null) {
					type = typeRef.getBoundTarget(me);
				}
			}
			else if (target instanceof Type /*e.g. Annotation*/ ) {
				return (Type) target;
			}
			else if (target instanceof EnumConstant) {
				type = (Enumeration)target.eContainer();
			}	
		}
		//Strings may also appear as reference
		else if (me instanceof StringReference) {
			return me.getStringClass();
		}
		else if (me instanceof NestedExpression) {
			type = ((NestedExpression) me).getExpression().getType();
		}
		else if (me instanceof PrimitiveTypeReference) {
			type = ((PrimitiveTypeReference) me).getPrimitiveType();
		}
		else {
			assert(false);
		}
		return type;
	}
}
