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
public class VariableReferencing {
	public VariableReferencing var;
	
	public void method() {
		// a reference to a member variable
		var = null; // statement1
		
		VariableReferencing var; // statement2
		
		// a reference to a local variable
		var = null; // statement3
		
		{ // statement4 (a block)
			
			// a reference to a local variable from inside a block
			var = null;
			// a reference to a local variable from inside a conditional statement
			if (true) {
				var = null;
			}
		}
		var = new VariableReferencing();
		var.method();
			
		int i = new VariableReferencing() {
			public int x = 0;
		}.x;
	}
}
