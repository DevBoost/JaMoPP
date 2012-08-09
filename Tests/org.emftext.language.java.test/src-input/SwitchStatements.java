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
public class SwitchStatements {
	{
		switch (3) {
			case 1:
				break;
			case 2:
				break;
			case 3:
				break;
			default:
				break;
		}
	}
	public void switchStatement() {
		switch (4) {
			case 1:
				break;
			case 2:
				break;
			default:
				break;
		}
	}
	
	public void switchWithExpression() {
		switch (4 + 3) {
			case 1:
				break;
			case 2:
				break;
			default:
				break;
		}
	}
	
	public void switchWithExpression2() {
		int i = 4;
		switch (i + 3) {
			case 1:
				break;
			case 2:
				break;
			default:
				break;
		}
	}
	
	public void caseWithOneStatement() {
		switch (4) {
			case 1:
				switchStatement();
				break;
			default:
				break;
		}
	}
	
	public void caseWithTwoStatements() {
		switch (4) {
			case 2:
				switchStatement();
				switchStatement();
				break;
			default:
				break;
		}
	}
	
	public void caseWithBlockAsStatement() {
		switch (4) {
			case 1:
				switchStatement();
				break;
			case 2:
				{
					switchStatement();
				}
				break;
			default:
				break;
		}
	}
	
	public void defaultCaseWithMultipleStatements() {
		switch (4) {
			case 1:
				break;
			case 2:
				break;
			default:
				switchStatement();
				{
					switchStatement();
				}
				break;
		}
	}
	
	public void switchMultipleCases() {
		switch (4) {
			case 1:
				break;
			case 2:
			case 3:
			case 4:
				break;
			default:
				break;
		}
	}

	public void switchWithDefaultCaseFirst() {
		switch (3) {
			default: break;
			case 0: break;
		}
	}
	
	public void switchWithDefaultMiddle(){
		switch (2){
		case 0: break;
		default : break;
		case 1 : break;
		}
	}
	
	public void switchWithNoDefault(){
		switch(2){
		case 0 : break;
		case 1 : break;
		}
	}
}
