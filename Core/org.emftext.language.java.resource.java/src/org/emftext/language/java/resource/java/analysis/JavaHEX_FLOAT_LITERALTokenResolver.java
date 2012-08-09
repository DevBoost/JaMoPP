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

import static org.emftext.language.java.resource.java.analysis.helper.LiteralConstants.FLOAT_SUFFIX;
import static org.emftext.language.java.resource.java.analysis.helper.LiteralConstants.HEX_EXPONENT;
import static org.emftext.language.java.resource.java.analysis.helper.LiteralConstants.HEX_PREFIX;

import java.util.Map;

import org.emftext.language.java.literals.HexFloatLiteral;
import org.emftext.language.java.literals.LiteralsPackage;
import org.emftext.language.java.resource.java.IJavaTokenResolveResult;
import org.emftext.language.java.resource.java.IJavaTokenResolver;

public class JavaHEX_FLOAT_LITERALTokenResolver implements IJavaTokenResolver {

	public java.lang.String deResolve(java.lang.Object value, org.eclipse.emf.ecore.EStructuralFeature feature, org.eclipse.emf.ecore.EObject container) {
		assert container == null || container instanceof HexFloatLiteral;
		assert value instanceof Float;
		return Float.toHexString((Float) value) + FLOAT_SUFFIX;
	}

	public void resolve(java.lang.String lexem, org.eclipse.emf.ecore.EStructuralFeature feature, IJavaTokenResolveResult result) {
		assert feature == null || feature.getEContainingClass().equals(LiteralsPackage.eINSTANCE.getHexFloatLiteral());

		assert lexem.toLowerCase().startsWith(HEX_PREFIX);
		assert lexem.toLowerCase().contains(HEX_EXPONENT) || lexem.contains(".");
		assert lexem.toLowerCase().endsWith(FLOAT_SUFFIX);

		lexem = lexem.substring(0, lexem.length() - 1);

		result.setResolvedToken(Float.parseFloat(lexem));
	}

	public void setOptions(Map<?, ?> options) {
	}
}
