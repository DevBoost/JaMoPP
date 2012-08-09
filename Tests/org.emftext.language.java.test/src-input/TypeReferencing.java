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
public class TypeReferencing {
	public class InnerType {
		public class InnerInnerType {
		}
	}
	public class InnerGenericType<T> {
		public class InnerInnerGenericType<S> {
		}
	}
	
	public void method() {
		TypeReferencing variable0;
		TypeReferencing.InnerType variable1;
		TypeReferencing.InnerType.InnerInnerType variable2;
		TypeReferencing.InnerGenericType<InnerType> variable3;
		TypeReferencing.InnerGenericType<InnerType>.InnerInnerGenericType<InnerType> variable4;
		
		pkg.EmptyClass variable5;
		pkg.inner.Inner variable6;
		
		// some accesses to avoid warnings
		variable0 = new TypeReferencing();
		variable0.toString();
		variable1 = new TypeReferencing.InnerType();
		variable1.toString();
		variable2 = variable1.new InnerInnerType();
		variable2.toString();
		variable3 = new TypeReferencing.InnerGenericType<InnerType>();
		variable3.toString();
		variable4 = variable3.new InnerInnerGenericType<InnerType>();
		variable4.toString();
		variable5 = new pkg.EmptyClass();
		variable5.toString();
		variable6 = new pkg.inner.Inner();
		variable6.toString();
	}
}
