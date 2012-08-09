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
public class TypeParameters {
	
	public class Inner<T> {
	}
	public class Inner2<T extends TypeParameters> {
	}
	
	public class Inner3<T extends TypeParameters & IOneMethod> {
	}
	
	public Inner<TypeParameters> field0;
	public Inner<?> field1;
	public Inner<? extends TypeParameters> field2;
	public Inner<? extends Inner<TypeParameters>> field3;
	public Inner<? super TypeParameters> field4;
	
	public Inner<TypeParameters[]> field5;
	public Inner<? super TypeParameters[]> field6;
	public Inner<? extends Inner<TypeParameters>[]> field7;

	public <T> void method1() {}
	public <T extends TypeParameters> void method2() {}
	public <T> void method4() {}
}
