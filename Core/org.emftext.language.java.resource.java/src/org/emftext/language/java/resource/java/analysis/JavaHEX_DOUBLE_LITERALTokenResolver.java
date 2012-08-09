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

import static org.emftext.language.java.resource.java.analysis.helper.LiteralConstants.DOUBLE_SUFFIX;
import static org.emftext.language.java.resource.java.analysis.helper.LiteralConstants.HEX_PREFIX;

import java.util.Map;

import org.emftext.language.java.literals.HexDoubleLiteral;
import org.emftext.language.java.literals.LiteralsPackage;
import org.emftext.language.java.resource.java.IJavaTokenResolveResult;
import org.emftext.language.java.resource.java.IJavaTokenResolver;

public class JavaHEX_DOUBLE_LITERALTokenResolver implements IJavaTokenResolver {

	public java.lang.String deResolve(java.lang.Object value, org.eclipse.emf.ecore.EStructuralFeature feature, org.eclipse.emf.ecore.EObject container) {
		assert container == null || container instanceof HexDoubleLiteral;
		assert value instanceof Double;
		return Double.toHexString((Double) value);
	}

	public void resolve(java.lang.String lexem, org.eclipse.emf.ecore.EStructuralFeature feature, IJavaTokenResolveResult result) {
		assert feature == null || feature.getEContainingClass().equals(LiteralsPackage.eINSTANCE.getHexDoubleLiteral());
		// this assertion is wrong, because hex literals of the form 0x1P10 are also valid
		//assert lexem.contains(".");
		assert lexem.toLowerCase().startsWith(HEX_PREFIX);
		if (lexem.toLowerCase().endsWith(DOUBLE_SUFFIX)) {
			lexem = lexem.substring(0, lexem.length() - 1);
		}

		result.setResolvedToken(Double.parseDouble(lexem));
	}

	public void setOptions(Map<?, ?> options) {
	}
}
