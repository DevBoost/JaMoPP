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

import java.math.BigInteger;

import junit.framework.TestCase;

import org.emftext.language.java.resource.java.IJavaTokenResolveResult;
import org.emftext.language.java.resource.java.IJavaTokenResolver;
import org.emftext.language.java.resource.java.analysis.JavaDECIMAL_DOUBLE_LITERALTokenResolver;
import org.emftext.language.java.resource.java.analysis.JavaDECIMAL_FLOAT_LITERALTokenResolver;
import org.emftext.language.java.resource.java.analysis.JavaDECIMAL_INTEGER_LITERALTokenResolver;
import org.emftext.language.java.resource.java.analysis.JavaDECIMAL_LONG_LITERALTokenResolver;
import org.emftext.language.java.resource.java.analysis.JavaHEX_DOUBLE_LITERALTokenResolver;
import org.emftext.language.java.resource.java.analysis.JavaHEX_FLOAT_LITERALTokenResolver;
import org.emftext.language.java.resource.java.analysis.JavaHEX_INTEGER_LITERALTokenResolver;
import org.emftext.language.java.resource.java.analysis.JavaHEX_LONG_LITERALTokenResolver;
import org.emftext.language.java.resource.java.analysis.JavaOCTAL_INTEGER_LITERALTokenResolver;
import org.emftext.language.java.resource.java.analysis.JavaOCTAL_LONG_LITERALTokenResolver;
import org.emftext.language.java.resource.java.mopp.JavaTokenResolveResult;

public class NumberLiteralResolverTest extends TestCase {

	JavaDECIMAL_INTEGER_LITERALTokenResolver decInt = new JavaDECIMAL_INTEGER_LITERALTokenResolver();
	JavaDECIMAL_LONG_LITERALTokenResolver decLong = new JavaDECIMAL_LONG_LITERALTokenResolver();
	JavaDECIMAL_FLOAT_LITERALTokenResolver decFloat = new JavaDECIMAL_FLOAT_LITERALTokenResolver();
	JavaDECIMAL_DOUBLE_LITERALTokenResolver decDouble = new JavaDECIMAL_DOUBLE_LITERALTokenResolver();

	JavaHEX_INTEGER_LITERALTokenResolver hexInt = new JavaHEX_INTEGER_LITERALTokenResolver();
	JavaHEX_LONG_LITERALTokenResolver hexLong = new JavaHEX_LONG_LITERALTokenResolver();
	JavaHEX_FLOAT_LITERALTokenResolver hexFloat = new JavaHEX_FLOAT_LITERALTokenResolver();
	JavaHEX_DOUBLE_LITERALTokenResolver hexDouble = new JavaHEX_DOUBLE_LITERALTokenResolver();

	JavaOCTAL_INTEGER_LITERALTokenResolver octInt = new JavaOCTAL_INTEGER_LITERALTokenResolver();
	JavaOCTAL_LONG_LITERALTokenResolver octLong = new JavaOCTAL_LONG_LITERALTokenResolver();

	public void testResolving() {
		assertDecInt(new BigInteger("1"), "1");
		assertHexInt(new BigInteger("1"), "0x1");
		assertOctInt(new BigInteger("1"), "01");

		assertDecLong(new BigInteger("1"), "1l");
		assertHexLong(new BigInteger("1"), "0x1l");
		assertOctLong(new BigInteger("1"), "01l");

		assertDecFloat(new Float(1.0f), "1.0f");
		assertHexFloat(new Float(0x1.0p0f), "0x1.0p0f");
		assertHexFloat(new Float(0x1.1p0f), "0x1.1p0f");

		assertDecDouble(new Double(1.0), "1.0");
		assertEquals(new Double(1.0), resolveDecDouble("1.0d"));
		assertEquals(new Double(0x1.0p0d), resolveHexDouble("0x1.0p0d"));
		assertEquals(new Double(0x1.1p0d), resolveHexDouble("0x1.1p0d"));
		assertHexDouble(new Double(0x1.1p0), "0x1.1p0");
	}

	public void assertDecInt(BigInteger expectedResolved, String lexem) {
		Object resolved = resolveDecInt(lexem);
		assertEquals(expectedResolved, resolved);
		assertEquals(lexem, deResolveDecInt(resolved));
	}

	public void assertDecLong(BigInteger expectedResolved, String lexem) {
		Object resolved = resolveDecLong(lexem);
		assertEquals(expectedResolved, resolved);
		assertEquals(lexem, deResolveDecLong(resolved));
	}

	public void assertDecFloat(Float expectedResolved, String lexem) {
		Object resolved = resolveDecFloat(lexem);
		assertEquals(expectedResolved, resolved);
		assertEquals(lexem, deResolveDecFloat(resolved));
	}

	public void assertDecDouble(Double expectedResolved, String lexem) {
		Object resolved = resolveDecDouble(lexem);
		assertEquals(expectedResolved, resolved);
		assertEquals(lexem, deResolveDecDouble(resolved));
	}

	public void assertHexInt(BigInteger expectedResolved, String lexem) {
		Object resolved = resolveHexInt(lexem);
		assertEquals(expectedResolved, resolved);
		assertEquals(lexem, deResolveHexInt(resolved));
	}

	public void assertHexLong(BigInteger expectedResolved, String lexem) {
		Object resolved = resolveHexLong(lexem);
		assertEquals(expectedResolved, resolved);
		assertEquals(lexem, deResolveHexLong(resolved));
	}

	public void assertHexFloat(Float expectedResolved, String lexem) {
		Object resolved = resolveHexFloat(lexem);
		assertEquals(expectedResolved, resolved);
		assertEquals(lexem, deResolveHexFloat(resolved));
	}

	public void assertHexDouble(Double expectedResolved, String lexem) {
		Object resolved = resolveHexDouble(lexem);
		assertEquals(expectedResolved, resolved);
		assertEquals(lexem, deResolveHexDouble(resolved));
	}

	public void assertOctInt(BigInteger expectedResolved, String lexem) {
		Object resolved = resolveOctInt(lexem);
		assertEquals(expectedResolved, resolved);
		assertEquals(lexem, deResolveOctInt(resolved));
	}

	public void assertOctLong(BigInteger expectedResolved, String lexem) {
		Object resolved = resolveOctLong(lexem);
		assertEquals(expectedResolved, resolved);
		assertEquals(lexem, deResolveOctLong(resolved));
	}

	private Object resolveDecInt(String lexem) {
		return resolve(decInt, lexem);
	}

	private String deResolveDecInt(Object resolved) {
		return deResolve(decInt, resolved);
	}

	private Object resolveDecLong(String lexem) {
		return resolve(decLong, lexem);
	}

	private String deResolveDecLong(Object resolved) {
		return deResolve(decLong, resolved);
	}

	private Object resolveDecFloat(String lexem) {
		return resolve(decFloat, lexem);
	}

	private String deResolveDecFloat(Object resolved) {
		return deResolve(decFloat, resolved);
	}

	private Object resolveDecDouble(String lexem) {
		return resolve(decDouble, lexem);
	}

	private String deResolveDecDouble(Object resolved) {
		return deResolve(decDouble, resolved);
	}

	private Object resolveHexInt(String lexem) {
		return resolve(hexInt, lexem);
	}

	private String deResolveHexInt(Object resolved) {
		return deResolve(hexInt, resolved);
	}

	private Object resolveHexLong(String lexem) {
		return resolve(hexLong, lexem);
	}

	private String deResolveHexLong(Object resolved) {
		return deResolve(hexLong, resolved);
	}

	private Object resolveHexFloat(String lexem) {
		return resolve(hexFloat, lexem);
	}

	private String deResolveHexFloat(Object resolved) {
		return deResolve(hexFloat, resolved);
	}

	private Object resolveHexDouble(String lexem) {
		return resolve(hexDouble, lexem);
	}

	private String deResolveHexDouble(Object resolved) {
		return deResolve(hexDouble, resolved);
	}

	private Object resolveOctInt(String lexem) {
		return resolve(octInt, lexem);
	}

	private String deResolveOctInt(Object resolved) {
		return deResolve(octInt, resolved);
	}

	private Object resolveOctLong(String lexem) {
		return resolve(octLong, lexem);
	}

	private String deResolveOctLong(Object resolved) {
		return deResolve(octLong, resolved);
	}

	private Object resolve(IJavaTokenResolver resolver, String lexem) {
		IJavaTokenResolveResult resolveResult = new JavaTokenResolveResult();
		resolver.resolve(lexem, null, resolveResult);
		Object result = resolveResult.getResolvedToken();
		assertNotNull(result);

		boolean isInteger = result instanceof Integer;
		boolean isBigInteger = result instanceof BigInteger;
		boolean isLong = result instanceof Long;
		boolean isFloat = result instanceof Float;
		boolean isDouble = result instanceof Double;
		boolean isNumber = isInteger || isBigInteger || isLong || isFloat || isDouble;
		assertTrue(isNumber);

		return result;
	}

	private String deResolve(IJavaTokenResolver resolver,
			Object resolved) {
		String result = resolver.deResolve(resolved, null, null);
		assertNotNull(result);
		return result;
	}
}
