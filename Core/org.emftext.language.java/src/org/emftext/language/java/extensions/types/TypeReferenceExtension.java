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
package org.emftext.language.java.extensions.types;

import org.emftext.language.java.arrays.ArrayTypeable;
import org.emftext.language.java.classifiers.Classifier;
import org.emftext.language.java.generics.TypeParameter;
import org.emftext.language.java.references.ElementReference;
import org.emftext.language.java.references.MethodCall;
import org.emftext.language.java.references.Reference;
import org.emftext.language.java.references.ReferenceableElement;
import org.emftext.language.java.types.ClassifierReference;
import org.emftext.language.java.types.NamespaceClassifierReference;
import org.emftext.language.java.types.PrimitiveType;
import org.emftext.language.java.types.Type;
import org.emftext.language.java.types.TypeReference;
import org.emftext.language.java.types.TypesFactory;

public class TypeReferenceExtension {

	/**
	 * Returns the type referenced by this <code>TypeReference</code>
	 * considering all concrete subclasses of <code>TypeReference</code> used
	 * by the Java metamodel.
	 * 
	 * @return the referenced type
	 */
	public static Type getTarget(TypeReference me) {
		return me.getBoundTarget(null);
	}
	
	/**
	 * Sets the type targeted by this type reference
	 * 
	 * @param type the new type to set as target
	 */
	public static void setTarget(TypeReference me, Classifier type) {
		if (type == null) {
			return;
		}
		
		if (type.eIsProxy()) {
			return;
		}
		
		if (me instanceof NamespaceClassifierReference) {
			NamespaceClassifierReference nsClassifierReference = (NamespaceClassifierReference) me;
			nsClassifierReference.getClassifierReferences().clear();
			nsClassifierReference.getNamespaces().clear();
			nsClassifierReference.getNamespaces().addAll(type.getContainingContainerName());
			ClassifierReference classifierRef = TypesFactory.eINSTANCE.createClassifierReference();
			classifierRef.setTarget(type);
			nsClassifierReference.getClassifierReferences().add(classifierRef);			
		}
	}
	
	/**
	 * Returns the type referenced by this <code>TypeReference</code>
	 * considering all concrete subclasses of <code>TypeReference</code> used by
	 * the Java metamodel. If type parameters are bound in the given reference,
	 * the bound type will be returned instead of the parameter.
	 * 
	 * @param reference
	 * 
	 * @return the referenced type
	 */
	public static Type getBoundTarget(TypeReference me, Reference reference) {
		Type type = null;
		if (me instanceof ClassifierReference || 
				me instanceof NamespaceClassifierReference) {
			ClassifierReference classifierRef = me.getPureClassifierReference();
			if (classifierRef != null) {
				type = classifierRef.getTarget();
			}
			
			if (reference instanceof MethodCall) {
				MethodCall potentialCloneCall = (MethodCall) reference;
				//clone returns the type of the cloned in the case of arrays
				ReferenceableElement potentialCloneCallTarget = potentialCloneCall.getTarget();
				if (potentialCloneCallTarget != null && 
						!potentialCloneCallTarget.eIsProxy() && 
						"clone".equals(potentialCloneCallTarget.getName()))  {
					if (potentialCloneCall.getPrevious() instanceof ElementReference) {
						ElementReference prevRef = (ElementReference) potentialCloneCall.getPrevious();
						if (prevRef.getTarget() instanceof ArrayTypeable && 
								((ArrayTypeable)prevRef.getTarget()).getArrayDimension() > 0) {
							type = prevRef.getReferencedType();
						}
					}
				}
			}
		}

		else if (me instanceof PrimitiveType) {
			return (PrimitiveType) me;
		}
		
		//resolve parameter to real type
		if (type instanceof TypeParameter) {
			type = ((TypeParameter) type).getBoundType(me, reference);
		}

		if (type != null && type.eIsProxy()) {
			//this may happen, when e.g. a super type is resolved. It is ok.
			return null;
		}
		
		return type;
	}
	
	/**
	 * Extracts the (possibly nested) classifier reference (if any) from this
	 * type references.
	 * 
	 * @return
	 */
	public static ClassifierReference getPureClassifierReference(TypeReference me) {
		ClassifierReference classifierReference = null;
		if (me instanceof ClassifierReference) {
			classifierReference = (ClassifierReference) me;
		}
		
		if (me instanceof NamespaceClassifierReference) {
			NamespaceClassifierReference nsClassifierReference = (NamespaceClassifierReference) me;
			if (!nsClassifierReference.getClassifierReferences().isEmpty()) {
				int lastIndex = nsClassifierReference.getClassifierReferences().size() - 1;
				classifierReference = nsClassifierReference.getClassifierReferences().get(lastIndex);
			}
		}
		return classifierReference;
	}
}
