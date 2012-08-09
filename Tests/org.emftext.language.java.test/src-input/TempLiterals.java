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
public class TempLiterals {
	public static long l1 = -9223372036854775808L;
	public static double d27 = -0Xf.aP1F;
	public static long long1 = -2147483648;
	public static float float8 = 0x1.fP+1f;
	public static float float11 = 0x1.fP+1F;
	public static double double8 = 0x1.fP+1D;
	public static double double11 = 0x1.fP+1D;
	public static float float13 = 0xA34P-9f;

	public static void main(String[] a) {
		System.out.println("TempLiterals.main() " + l1);
		System.out.println("TempLiterals.main() " + Long.MAX_VALUE);
	}
}
