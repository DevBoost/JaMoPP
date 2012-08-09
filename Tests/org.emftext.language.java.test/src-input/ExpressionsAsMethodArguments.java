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
public class ExpressionsAsMethodArguments {
	public void method1(int p1, int p2) {
		
	}
	
	public void method2(String s1) {
		
	}
	
	public void callee() {
		int i = 0;
		method1(i, i + 2);
		method1(3, 1 - 2);
		method2(3 + "1");
		i = i + 2;
	}
}
