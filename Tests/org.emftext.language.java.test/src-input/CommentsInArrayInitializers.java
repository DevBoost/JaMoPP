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
public class CommentsInArrayInitializers {

	protected Integer[] m1() {
		return new Integer[] {
				new Integer(1), // some comment
		};
	}

	protected Integer[][] m2() {
		return new Integer[1][2// some comment
		                      ];
	}

	protected String[][] m3() {
		return new String[][] { {"s1"}, // comment1
				{"s2", // comment2 
				"s3"}, // comment3
		};
	}

	protected static String[] f1 = {
		"variable", // comment 1
		"value", // comment 2
		"description" // comment 3
	};
}
