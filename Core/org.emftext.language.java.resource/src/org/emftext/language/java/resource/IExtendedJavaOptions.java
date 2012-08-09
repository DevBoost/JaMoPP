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
package org.emftext.language.java.resource;

import org.emftext.language.java.resource.java.IJavaOptions;

/**
 * This interface provides additional options that can be used to tailor the
 * behavior of JaMoPP resources.
 */
public interface IExtendedJavaOptions extends IJavaOptions {

	/**
	 * The key for the option to disable on demand proxy resolving, which is
	 * default.
	 */
	public String DISABLE_ON_DEMAND_PROXY_RESOLVING = "DISABLE_ON_DEMAND_PROXY_RESOLVING";

}
