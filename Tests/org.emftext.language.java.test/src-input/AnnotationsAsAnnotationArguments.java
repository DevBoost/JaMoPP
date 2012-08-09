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
public class AnnotationsAsAnnotationArguments {
	
	public @interface A1 {
	}
	
	public @interface A2 {
		public String property();
	}
	
	public @interface B1 {
		// using an annotation as argument for an annotation instance
		@B1(m = @A1)
		public A1 m();
	}

	public @interface B2 {
		// using an annotation as argument for an annotation instance
		@B2(m = @A2(property = "something"))
		public A2 m();
	}

	public @interface C0 {
		// using an array of strings as argument for an annotation instance
		@C0(m = {"", "", ""})
		public String[] m();
	}

	public @interface C1 {
		// using an array of annotations as argument for an annotation instance
		@C1(m = {@A1, @A1, @A1})
		public A1[] m();
	}

	public @interface C2 {
		// using an array of annotations as argument for an annotation instance
		@C2(m = {@A2(property="value1"), @A2(property="value2"), @A2(property="value3")})
		public A2[] m();
	}

	public @interface C3 {
		// using an array of annotations as argument for an annotation instance
		@C3({@A1, @A1, @A1})
		public A1[] value();
	}
}
