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
package org.emftext.language.java.test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.antlr.runtime3_4_0.ANTLRInputStream;
import org.emftext.language.java.resource.java.util.JavaUnicodeConverter;
import org.junit.Assert;
import org.junit.Test;

public class UnicodeConverterTest {

	@Test
	public void testUnsignedByteToInt() {
		System.out.println("UnicodeConverterTest.testUnsignedByteToInt() " +
				Assert.class.getProtectionDomain().getCodeSource().getLocation());
		assertEquals(255, JavaUnicodeConverter.unsignedByteToInt((byte) 0xFF));
		assertEquals(1, JavaUnicodeConverter.unsignedByteToInt((byte) 0x01));
	}

	@Test
	public void testConversion() throws IOException {
		assertConversion("\uabcd", "\\uuuuuuuuuuabcd");
		assertConversion("\u0001\u0101", "\\u0001\\u0101");
		assertConversion("\u0001\uFfFf", "\\u0001\\uFfFf");
		assertConversion("\\\\u0000\\\\uFfFf", "\\\\u0000\\\\uFfFf");
		assertConversion("\\377\\388", "\\377\\388");
		assertConversion("\u202a", "\\u202a");
	}
	
	@Test
	public void testNonConversion() throws IOException {
		//already converted unicode characters should not change
		assertConversion("\ud800\udc02", "\ud800\udc02");
	}
	

	private void assertConversion(String expectedOutput, String input) throws IOException {
		JavaUnicodeConverter converter = new JavaUnicodeConverter(new ByteArrayInputStream(input.getBytes()));
		byte[] bytes = new byte[100];
		int next;
		int i = 0;
		while ((next = converter.read()) >= 0) {
			System.out.println("UnicodeConverterTest.assertConversion() next = " + next);
			bytes[i++] = (byte) next;
		}
		byte[] usedBytes = new byte[i];
		for (i = 0; i < usedBytes.length; i++) {
			usedBytes[i] = bytes[i];
		}
		assertArrayEquals(expectedOutput.getBytes("UTF-8"), usedBytes);
		converter.close();
	}


	@Test
	public void testStreaming() {
		try {
			byte[] bs = JavaUnicodeConverter.encode(new int[] {0x202a});
			InputStream stream = new ByteArrayInputStream(bs);

			InputStreamReader reader = new InputStreamReader(stream, "UTF-8");
			assertEquals(0x202a, reader.read());
			assertEquals(-1, reader.read());

			bs = JavaUnicodeConverter.encode(new int[] {0x40, 0x202a});
			stream = new ByteArrayInputStream(bs);
			ANTLRInputStream antlrStream = new ANTLRInputStream(stream, "UTF-8");
			int byte1 = antlrStream.LT(2);
			assertEquals(0x202a, byte1);

			System.out.println("ANTLRStreamTest.testStreaming()");
			stream = new JavaUnicodeConverter(new ByteArrayInputStream("a\\u202a".getBytes()));
			antlrStream = new ANTLRInputStream(stream, "UTF-8");
			byte1 = antlrStream.LT(2);
			assertEquals(0x202a, byte1);
		} catch (IOException ioe) {
			ioe.printStackTrace();
			fail(ioe.getMessage());
		}
	}
}
