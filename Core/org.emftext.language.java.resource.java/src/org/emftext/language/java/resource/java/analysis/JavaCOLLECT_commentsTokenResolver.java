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
package org.emftext.language.java.resource.java.analysis;

import java.util.Map;

import org.emftext.language.java.resource.java.IJavaTokenResolveResult;
import org.emftext.language.java.resource.java.IJavaTokenResolver;

public class JavaCOLLECT_commentsTokenResolver implements IJavaTokenResolver {

	public java.lang.String deResolve(java.lang.Object value, org.eclipse.emf.ecore.EStructuralFeature feature, org.eclipse.emf.ecore.EObject container) {
		String comment = (String) value;
		if (comment.startsWith("//")) {
			comment = comment + "\n";
		}
		return comment;
	}

	public void resolve(java.lang.String lexem, org.eclipse.emf.ecore.EStructuralFeature feature, IJavaTokenResolveResult result) {
		//do nothing for now
		result.setResolvedToken(lexem);
	}

	public void setOptions(Map<?, ?> options) {
	}
}
