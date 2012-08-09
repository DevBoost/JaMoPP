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
public class LoopStatements {
	{
		// for
		for (;condition;) {
		}
	}

	{
		// for
		for (;condition;) {
			continue;
		}
	}
	
	{
		// while
		while (condition) {
			break;
		}
	}
	
	{
		// empty statement
		;
	}
	
	{
		// do
		do {
			
		} while(condition);
	}

	{
        do break;
        while (condition);
	}
	
	public void loops() {
		// for
		for (;condition;) {
			continue;
		}
		
		for (;condition;) break;
		
		// while
		while (condition) {
			break;
		}
		
		while (condition) break;
		
		// empty statement
		;
		
		// do
		do {
			
		} while(condition);
	}
	
	{
		int x = 0;
		for(int i = 0;condition;){
			x = i;
		}
		for(int i = 0, j = 1;condition;){
			x = i;
			j = x;
			i = j;
		}
	}

	public void expressionListInit() {
		int i;
		int j;
		i=0;
		for(i=0,j=0;condition;){
			i = j;
			j = i;
		}
	}

	public void expressionListCondition() {
		int i = 0;
		int j = 0;
		for(;condition;i=j,j=i){}
	}

	private static boolean condition = false;
}
