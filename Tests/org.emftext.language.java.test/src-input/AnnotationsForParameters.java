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
public class AnnotationsForParameters {

	public @interface A {
		String value();
	}
	@A("a") public int i = 1;
	
	@A("a") public void m(@A("something") int x) {
		@A("a") 
		AnnotationsForParameters a = new AnnotationsForParameters();
	}
	
	public void m2(final @A("foo") int x) {
		
	}

	public void m3(final @A("foo") int... x) {
		
	}

	public void m4() {
		final @A("foo") int x;
	}
	
	public @A("foo") int x;
	
	public @A("foo") class Inner1 {
	};

	public @A("foo") interface Inner2 {
	};

	public @A("foo") enum Inner3 {
	};

	public @A("foo") @interface Inner4 {
	};

}
