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
import java.util.Formatter.BigDecimalLayoutForm;

import pkg.inner.Inner.ExternalInnerInner;

public class TypeReferencingExternal {

	
	public void method() {
		TypeReferencing variable0;
		TypeReferencing.InnerType variable1;
		TypeReferencing.InnerType.InnerInnerType variable2;
		TypeReferencing.InnerGenericType<TypeReferencing.InnerType> variable3;
		TypeReferencing.InnerGenericType<TypeReferencing.InnerType>.InnerInnerGenericType<TypeReferencing.InnerType> variable4;
		
		pkg.EmptyClass variable5;
		pkg.inner.Inner variable6;
		
		ExternalInnerInner variable7;
		
		// reference to an inner class in a binary
		BigDecimalLayoutForm variable8;
		
		// some accesses to avoid warnings
		variable0 = new TypeReferencing();
		variable0.toString();
		variable1 = new TypeReferencing().new InnerType();
		variable1.toString();
		variable2 = variable1.new InnerInnerType();
		variable2.toString();
		variable3 = new TypeReferencing().new InnerGenericType<TypeReferencing.InnerType>();
		variable3.toString();
		variable4 = variable3.new InnerInnerGenericType<TypeReferencing.InnerType>();
		variable4.toString();
		variable5 = new pkg.EmptyClass();
		variable5.toString();
		variable6 = new pkg.inner.Inner();
		variable6.toString();
		
		variable7 = variable6.new ExternalInnerInner();
		variable7.notify();
		
		variable8 = BigDecimalLayoutForm.SCIENTIFIC;
		//this should be parsed to the method "ordinal()" and not the (private) local field "ordinal"
		variable8.ordinal();
	}
}
