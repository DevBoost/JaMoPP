/*******************************************************************************
 * Copyright (c) 2006-2014
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
package org.emftext.language.java.extensions.literals;

import org.emftext.language.java.literals.BooleanLiteral;
import org.emftext.language.java.literals.CharacterLiteral;
import org.emftext.language.java.literals.DoubleLiteral;
import org.emftext.language.java.literals.FloatLiteral;
import org.emftext.language.java.literals.IntegerLiteral;
import org.emftext.language.java.literals.Literal;
import org.emftext.language.java.literals.LongLiteral;
import org.emftext.language.java.literals.NullLiteral;

public class LiteralExtension {
	
	/**
	 * @return type of the literal
	 */
	public static org.emftext.language.java.classifiers.Class getOneType(Literal me) {
		//Overrides implementation in Expression
		org.emftext.language.java.classifiers.Class javaClass = null;
		
		if (me instanceof NullLiteral) {
			javaClass = me.getLibClass("Void");
		}
		else if (me instanceof BooleanLiteral) {
			javaClass = me.getLibClass("Boolean");
		}
		else if (me instanceof DoubleLiteral) {
			javaClass = me.getLibClass("Double");
		}
		else if (me instanceof FloatLiteral) {
			javaClass = me.getLibClass("Float");
		}
		else if (me instanceof IntegerLiteral) {
			javaClass = me.getLibClass("Integer");
		}
		else if (me instanceof LongLiteral) {
			javaClass = me.getLibClass("Long");
		}
		else if (me instanceof CharacterLiteral) {
			javaClass = me.getLibClass("Character");
		}

		return javaClass;
	}
}
