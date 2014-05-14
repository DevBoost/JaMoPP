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
package org.emftext.language.java.extensions.references;

import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;
import org.emftext.language.java.expressions.Expression;
import org.emftext.language.java.references.Argumentable;
import org.emftext.language.java.types.Type;

public class ArgumentableExtension {
	
	/**
	 * Returns a list containing the types of the given {@link Argumentable}.
	 * 
	 * @return list of types of 'me'
	 */
	public static EList<Type> getArgumentTypes(Argumentable me) {
		EList<Type> resultList = new BasicEList<Type>();

		for (Expression argument : me.getArguments()) {
			Type type = argument.getType();
			resultList.add(type);
		}
		return resultList;
	}
}
