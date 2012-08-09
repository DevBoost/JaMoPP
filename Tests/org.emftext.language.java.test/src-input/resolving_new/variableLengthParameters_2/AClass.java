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
package resolving_new.variableLengthParameters_2;

public class AClass {

	public void m // target:1
		(String string) {
		System.out.println("m(String)");
	}
	
	public void m // target:2
		(String... strings) {
		System.out.println("m(String...)");
	}
	
	public static void main(String[] args) {
		AClass a = new AClass();
		
		a.// source:1:target
		m("");
		
		a.// source:2:target
		m("", "");

		a.// source:2:target
		m(new String[] {"", ""});
	}
}
