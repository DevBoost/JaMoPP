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
public class ChainedCalls {
	public class Inner {

	}
	public class InnerGeneric<T> {
		public <S> InnerGeneric () {
			ChainedCalls.this.toString();
			ChainedCalls.super.hashCode();
		}
	}
	public int a;
	public ChainedCalls ref;
	public ChainedCalls[] refArray;
	public ChainedCalls[][] twoDimensionalArray;
	
	public ChainedCalls m() {
		return null;
	}
	
	public static ChainedCalls staticM() {
		return null;
	}
	
	public ChainedCalls m(ChainedCalls cc) {
		return null;
	}
	
	{
		m();
	}
	{
		a = m().a;
	}
	{
		m().a = 1;
	}
	{
		m().m().m();
	}
	{
		m(null).m(null).m(this);
	}
	{
		this.m();
	}
	{
		ref.m();
	}
	{
		ref.m().ref.m();
	}
	{
		java.lang.String.class.toString();
	}
	{
		ChainedCalls.staticM();
	}
	{
		ref = ChainedCalls.this;
		ChainedCalls.this.m();
	}
	{
		new ChainedCalls().m();
	}
	{
		ChainedCalls[].class.toString();
	}
	{
		ChainedCalls[][].class.toString();
	}
	{
		refArray[1].toString();
	}
	{
		twoDimensionalArray[1][2].toString();
	}
	{
		ChainedCalls.Inner.class.toString();
	}
	{
		this.new Inner();
	}
}
