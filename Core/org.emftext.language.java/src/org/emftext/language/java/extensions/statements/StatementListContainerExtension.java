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
package org.emftext.language.java.extensions.statements;

import java.util.List;

import org.emftext.language.java.statements.StatementListContainer;
import org.emftext.language.java.variables.LocalVariable;

public class StatementListContainerExtension {

	/**
	 * Returns the first local variable in the given
	 * {@link StatementListContainer} with the specified name.
	 * 
	 * @param me
	 *            the {@link StatementListContainer} to search in
	 * @param name
	 *            the name of the variable to search for
	 * @return a local variable with the given name or <code>null</code> if no
	 *         such variable was found
	 */
	public static LocalVariable getLocalVariable(StatementListContainer me, String name) {
		List<LocalVariable> localVariables = me.getChildrenByType(LocalVariable.class);
		for (LocalVariable localVariable : localVariables) {
			if (localVariable.getName().equals(name)) {
				return localVariable;
			}
		}
		
		// Found no matching variable
		return null;
	}
}
