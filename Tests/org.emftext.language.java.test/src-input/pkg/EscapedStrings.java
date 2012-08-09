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
package pkg;
public class EscapedStrings {
	public static String theEscapeAsUnicode = "\u00E9";
	
	public static String unicode = "\u0001\u0202";
	public static String notUnicode = "\\u0000\\uFfFf";
	public static String octal1 = "\0\07\007\0007";
	public static String octal2 = "\377\388";
	public static String escapes1 = "\b\n\t\r\n\\";
	public static String escapes2 = "\"'\'";
	
	public static void m1() {}
	
	public static class C1 <T>{}
}
