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
import org.emftext.language.java.classifiers.ConcreteClassifier;
import org.emftext.language.java.classifiers.Interface;
import org.emftext.language.java.types.Type;
import org.emftext.language.java.types.TypeReference;
import org.emftext.language.java.util.UniqueEList;

public class InterfaceExtension {
	
	/**
	 * Returns all interfaces extended by this interface. The type of the
	 * objects in the returned list is {@link ConcreteClassifier}, because
	 * <code>java.lang.Object</code> is also extended although it is a Class.
	 */
	public static EList<ConcreteClassifier> getAllSuperClassifiers(Interface me) {
		EList<ConcreteClassifier> result = new UniqueEList<ConcreteClassifier>();
		
		EList<TypeReference> explicitExtends = me.getExtends();
		getAllSuperClassifiers(explicitExtends, result);
		
		EList<TypeReference> defaultExtends = me.getDefaultExtends();
		getAllSuperClassifiers(defaultExtends, result);
		
		return result;
	}

	private static void getAllSuperClassifiers(
			List<TypeReference> typeReferences, List<ConcreteClassifier> result) {
		
		for (TypeReference typeReference : typeReferences) {
			getAllSuperClassifiers(typeReference, result);
		}
	}

	private static void getAllSuperClassifiers(TypeReference typeReference,
			List<ConcreteClassifier> result) {
		
		// Use ConcreteClassifier instead of Interface because
		// java.lang.Object can also act as implemented interface
		Type target = typeReference.getTarget();
		ConcreteClassifier superInterface = (ConcreteClassifier) target;
		if (superInterface != null) {
			result.add(superInterface);
			if (superInterface instanceof Interface) {
				result.addAll(((Interface) superInterface).getAllSuperClassifiers());
			}
		}
	}
}
