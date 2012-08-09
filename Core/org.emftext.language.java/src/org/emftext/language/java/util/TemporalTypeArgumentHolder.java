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
package org.emftext.language.java.util;

import org.eclipse.emf.common.notify.impl.AdapterImpl;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.UniqueEList;
import org.emftext.language.java.generics.TypeArgument;


/**
 * This adapter is used to attach type arguments to a type when it is passed through
 * several getBoundType() calls recursively. A better solution might be
 * to pass type references along whenever possible.
 * <p>
 * This however will require major changes in the API.
 */
public class TemporalTypeArgumentHolder extends AdapterImpl {
	private EList<TypeArgument> typeArguments = new UniqueEList<TypeArgument>();

	public EList<TypeArgument> getTypeArguments() {
		return typeArguments;
	}
}
