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
public class Expressions {
	public int i;
	{
		i = 1 + 2;
		i = 1 * 3;
		i = 1 - 2 + 3;
		i = 1 * 2 / 3 % 4;
		
		i = (3);
		i = ((3));
		i = ((4+5));
		
		i = 4 << 2;
		i = 4 >> 2;
		i = i >>> 2;
				
		i = 4 & 2;
		i = 4 | 2;
		
		i = (int) i;
	}	
}
