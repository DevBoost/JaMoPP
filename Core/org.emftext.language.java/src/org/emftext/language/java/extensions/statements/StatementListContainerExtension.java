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

import org.eclipse.emf.common.util.EList;
import org.emftext.language.java.statements.StatementListContainer;
import org.emftext.language.java.variables.LocalVariable;

public class StatementListContainerExtension {

	public static LocalVariable getLocalVariable(StatementListContainer me, String name) {
		EList<LocalVariable> localVariables = me.getChildrenByType(LocalVariable.class);
		for (LocalVariable localVariable : localVariables) {
			if (localVariable.getName().equals(name)) {
				return localVariable;
			}
		}
		return null;
	}
}
