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
public class LocalVariableDeclarations {
	// multiple variables in one declaration
	{
		int i,j;
		j = 0;
		// some assignments to avoid warnings
		i = j;
		j = i;
	}

	// multiple variables in one declaration with initialization
	{
		int i,j = 0;
		// some assignments to avoid warnings
		i = j;
		j = i;
	}
}
