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
package resolving_new.methodParameters_4;

public class LocalCalls {

	public void m // target:1
	() {}
	
	public void m // target:2
	(int a) {}
	
	public void m // target:3
	(int a, int b) {}
	
	public void m // target:4
	(Object a) {}
	
	public void call() {
		// source:1:target
		m();

		// source:2:target
		m(1);
		
		// source:3:target
		m(1, 2);

		// source:4:target
		m(new LocalCalls());
	}
}
