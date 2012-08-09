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
package org.emftext.language.java;

public class IdentifierRegexGenerator {

	public interface Check {
		public boolean check(int x);
	}

	public static void main(String[] args) {
		System.out.print("$(");
		iterate(new Check() {
			public boolean check(int x) {
				return Character.isJavaIdentifierStart(x);
			}
		});
		System.out.print(")(");
		iterate(new Check() {
			public boolean check(int x) {
				return Character.isJavaIdentifierPart(x);
			}
		});
		System.out.print(")*$;");
	}

	private static void iterate(Check check) {
		boolean needsOption = false;
		boolean wasTrue = false;
		int lastChar = 0;
		for (int x = 0; x <= 0xFFFF; x++) {
			if (check.check(x)) {
				if (!wasTrue) {
					if (needsOption) {
						System.out.print("|");
					}
					System.out.print("'\\u" + format(Integer.toHexString(x)) + "'");
					lastChar = x;
				}
				wasTrue = true;
			} else {
				if (wasTrue) {
					needsOption = true;
					if (x - 1 != lastChar) {
						System.out.print("..'\\u" + format(Integer.toHexString(x - 1)) + "'");
					}
				}
				wasTrue = false;
			}
		}
	}

	private static String format(String hexString) {
		int length = hexString.length();
		int missingLeft = 4 -length;
		for (int i = 0; i < missingLeft; i++) {
			hexString = "0" + hexString;
		}
		return hexString;
	}
}
