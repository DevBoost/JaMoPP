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
public class ArrayInitializers {
	public ArrayInitializers[] field1 = new ArrayInitializers[2];
	public ArrayInitializers[] field2 = new ArrayInitializers[] {};
	public ArrayInitializers[] fieldWithCommaAtEnd = new ArrayInitializers[] {
			new ArrayInitializers(), 
			new ArrayInitializers(), 
			new ArrayInitializers(), 
	};
	public ArrayInitializers[] fieldWithoutCommaAtEnd = new ArrayInitializers[] {
			new ArrayInitializers(), 
			new ArrayInitializers(), 
			new ArrayInitializers()
	};
	public ArrayInitializers[][] fieldWithTwoDimensions = new ArrayInitializers[][] {
			new ArrayInitializers[] {
					new ArrayInitializers(), 
					new ArrayInitializers(),
			},
			new ArrayInitializers[] {
					new ArrayInitializers(), 
					new ArrayInitializers()
			},
	};

	public void m1() {
		ArrayInitializers[] variable = new ArrayInitializers[2341];
		variable.toString();
	}

	public void m2() {
		ArrayInitializers[][] variable2 = new ArrayInitializers[2341][];
		variable2.toString();
	}

	public void m3() {
		ArrayInitializers[] variableWithCommaAtEnd = new ArrayInitializers[] {
				new ArrayInitializers(), 
				new ArrayInitializers(), 
				new ArrayInitializers(), 
		};
		ArrayInitializers[] variableWithoutCommaAtEnd = new ArrayInitializers[] {
				new ArrayInitializers(), 
				new ArrayInitializers(), 
				new ArrayInitializers()
		};
		variableWithCommaAtEnd.toString();
		variableWithoutCommaAtEnd.toString();
	}
	
	public void m4() {
		int[] ia = { , };
	}
	
	public void m5() {
		new int[]{1}[0] = 1;
		int[][][] f = new int[1][][].clone();
		int i2 = new int[]{1,2,}[0];
	}
}
