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
public class Casting {
	{
		Casting c;
		Casting[] d = new Casting[1];
		c = (Casting) null;
		Casting e = (Casting) null;
		c = (Casting) c;
		d = (Casting[]) d;
		d = (Casting[]) d.clone();
		((Casting[]) d).clone();
		Casting c2 = new Casting2();
		Casting c3 = ((Casting2) c2).m();
		((Casting2)c3).m().toString();
		try {
			((Casting[]) d)[0].clone();
		} catch (CloneNotSupportedException e1) {
			e1.printStackTrace();
		}
		c = (Casting) d[0];
		float f = (byte) 1;
		d = (Casting[]) new Casting[2];
		
		{}
	}
	
	public class Casting2 extends Casting {
		public Casting m() {
			return new Casting2();
		}
	}
}
