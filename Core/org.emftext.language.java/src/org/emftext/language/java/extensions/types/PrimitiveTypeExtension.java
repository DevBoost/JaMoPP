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
package org.emftext.language.java.extensions.types;

import org.eclipse.emf.common.util.EList;
import org.emftext.language.java.commons.Commentable;
import org.emftext.language.java.members.Member;
import org.emftext.language.java.types.PrimitiveType;

public class PrimitiveTypeExtension {

	/**
	 * @param context to check protected visibility
	 * @return all members (including super type members)
	 */
	public static EList<Member> getAllMembers(PrimitiveType me, Commentable context) {
		// TODO Call wrapPrimitiveType() directly
		org.emftext.language.java.classifiers.Class javaClass = me.wrapPrimitiveType();
		return javaClass.getAllMembers(context);
	}
	
	/**
	 * @return primitive type as a class representation
	 */
	public static org.emftext.language.java.classifiers.Class wrapPrimitiveType(PrimitiveType me) {
		org.emftext.language.java.classifiers.Class javaClass = null;
		
		if (me instanceof org.emftext.language.java.types.Boolean) {
			javaClass = me.getLibClass("Boolean");
		}
		if (me instanceof org.emftext.language.java.types.Byte) {
			javaClass = me.getLibClass("Byte");
		}
		if (me instanceof org.emftext.language.java.types.Char) {
			javaClass = me.getLibClass("Character");
		}
		if (me instanceof org.emftext.language.java.types.Double) {
			javaClass = me.getLibClass("Double");
		}
		if (me instanceof org.emftext.language.java.types.Float) {
			javaClass = me.getLibClass("Float");
		}
		if (me instanceof org.emftext.language.java.types.Int) {
			javaClass = me.getLibClass("Integer");
		}
		if (me instanceof org.emftext.language.java.types.Long) {
			javaClass = me.getLibClass("Long");
		}
		if (me instanceof org.emftext.language.java.types.Short) {
			javaClass = me.getLibClass("Short");
		}
		if (me instanceof org.emftext.language.java.types.Void) {
			javaClass = me.getLibClass("Void");
		}
		return javaClass;
	}
}
