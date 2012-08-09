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
/**
 * A multi-line javadoc comment.
 */
/*
 * A multi-line class comment.
 */
public class Comments {
	/** A single-line javadoc comment. */
	/* A single-line method comment. */
	// A real single line comment.
	public void method1() {
		// another comment inside a method
	}

	/*****
	 * A comment with lots of stars.
	 *****/
	public void method2() {
		/* comment */
		// single before declaration
		Comments declaration = new Comments();
		Comments assignment;
		// single after declaration and before assignment
		assignment = new Comments();
	}
	
	public void method3() {
		int a = 2 + // a comment within an expression 
			3;
	}
}
