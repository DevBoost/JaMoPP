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
package resolving_new.fields_2;

public class AClass {

	private AClass field1 // target:1
	;
	
	private AClass field2 // target:2
	;
	
	public void m() {
		// source:1:target
		field1.m();

		field1.
		// source:1:target
		field1.m();

		// source:2:target
		field2.m();

		field2.
		// source:2:target
		field2.m();

		field1.
		// source:2:target
		field2.m();

		field2.
		// source:1:target
		field1.m();
	}
}
