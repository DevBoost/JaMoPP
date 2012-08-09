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
@Annotations(t1 = 0, t2 = 1)
@SuppressWarnings(value = "")
public @interface Annotations {
	
	public int t2();

	public int t1() default 1;
	
	public @interface A1 {
	}
	
	public @interface A2 {
		int[] value();
	}
	
	public @interface A3 {
		A4 t();
	}
	
	public @interface A4 {
		String[] value();
	}
	
	@Annotations(t1 = 0, t2 = 3)
	public int t2 = 0;

	@A1
	public int t3 = 1;

	@A1()
	public int t6 = 1;

	@A2({1,2,3})
	public int t4 = 1;

	@A3(t = @A4({"a","b","c"}))
	public int t5 = 1;
}
