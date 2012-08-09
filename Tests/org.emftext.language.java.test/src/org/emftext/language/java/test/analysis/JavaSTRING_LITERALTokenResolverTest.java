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
package org.emftext.language.java.test.analysis;

import static org.junit.Assert.assertEquals;

import org.emftext.language.java.resource.java.IJavaTokenResolveResult;
import org.emftext.language.java.resource.java.analysis.JavaSTRING_LITERALTokenResolver;
import org.emftext.language.java.resource.java.mopp.JavaTokenResolveResult;
import org.junit.Test;

/**
 * Tests the JavaSTRING_LITERALTokenResolver class.
 */
public class JavaSTRING_LITERALTokenResolverTest {

	@Test
	public void testUnicodeEscaping() {
		JavaSTRING_LITERALTokenResolver resolver = new JavaSTRING_LITERALTokenResolver();
		assertEquals("\0\07\007\0007", resolve(resolver, "\\0\\07\\007\\0007"));
		assertEquals("\07", resolve(resolver, "\\07"));
		assertEquals("\007", resolve(resolver, "\\007"));
		assertEquals("\0007", resolve(resolver, "\\0007"));

		// Ordinary unicode escape sequence:
		// The JavaSTRING_LITERALTokenResolver does not touch this escaped unicode characters
		// since they are already converted by the JavaUnicodeConverter.
		assertEquals("\\u0020", resolve(resolver, "\\u0020"));
		// test escaped backslash (not a unicode sequence)
		assertEquals("\\u0020", resolve(resolver, "\\\\u0020"));

		assertEquals("\\", resolve(resolver, "\\\\"));
		assertEquals("\n", resolve(resolver, "\\n"));
		assertEquals("\b", resolve(resolver, "\\b"));
		assertEquals("\f", resolve(resolver, "\\f"));
		assertEquals("\t", resolve(resolver, "\\t"));
		assertEquals("\r", resolve(resolver, "\\r"));
		assertEquals("\"", resolve(resolver, "\\\""));
		assertEquals("\'", resolve(resolver, "\\\'"));

		assertEquals("\0", resolve(resolver, "\\0"));
		assertEquals("\10", resolve(resolver, "\\10"));
		assertEquals("\377", resolve(resolver, "\\377"));
	}

	private String resolve(JavaSTRING_LITERALTokenResolver resolver, String lexem) {
		IJavaTokenResolveResult result = new JavaTokenResolveResult();
		resolver.resolve("\"" + lexem + "\"", null, result);
		return (String) result.getResolvedToken();
	}
}
