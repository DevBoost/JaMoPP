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
import org.emftext.language.java.classifiers.Annotation;
import org.emftext.language.java.classifiers.ConcreteClassifier;
import org.emftext.language.java.util.UniqueEList;

public class AnnotationExtension {
	
	public static EList<ConcreteClassifier> getAllSuperClassifiers(Annotation me) {
		// TODO Use ECollections.singletonEList() instead?
		EList<ConcreteClassifier> result = new UniqueEList<ConcreteClassifier>();
		result.add(me.getAnnotationInterface());
		return result;
	}		
}
