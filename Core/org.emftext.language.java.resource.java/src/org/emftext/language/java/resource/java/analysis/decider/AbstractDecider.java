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
package org.emftext.language.java.resource.java.analysis.decider;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;

/**
 * Default implementation of the interface that provides default behavior for some methods.
 *
 */
public abstract class AbstractDecider implements IResolutionTargetDecider {

	protected boolean active = true;

	/**
	 * @return true by default
	 */
	public boolean continueAfterReference() {
		return true;
	}

	/**
	 * @return false by default
	 */
	public boolean walkInto(EObject element) {
		return false;
	}

	/**
	 * @return null (corresponds to empty list) by default
	 */
	public EList<? extends EObject> getAdditionalCandidates(String identifier, EObject container)  {
		return null;
	}

	/**
	 * @return true by default
	 */
	public boolean isSure() {
		return true;
	}

	/**
	 * Implements the interface specification. Do not override.
	 */
	public void activate() {
		active = true;
	}

	/**
	 * Implements the interface specification. Do not override.
	 */
	public void deactivate() {
		active = false;
	}

	/**
	 * Implements the interface specification. Do not override.
	 */
	public boolean isActive() {
		return active;
	}
}
