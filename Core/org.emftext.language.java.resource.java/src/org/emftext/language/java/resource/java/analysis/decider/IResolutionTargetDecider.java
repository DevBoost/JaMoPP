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
import org.eclipse.emf.ecore.EReference;

/**
 * A visitor that determines during a scoped tree traversal if an element
 * is the target. A decider can also influence the traversal itself at certain points
 * to adjust the scoping rules for its needs. A decider can maintain a state for one traversal
 * process.
 */
public interface IResolutionTargetDecider {

	/**
	 * Determines if the decider is of any use for the given resolving procedure.
	 *
	 * @param referenceContainer
	 * @param containingReference
	 * @return decision
	 */
	boolean canFindTargetsFor(EObject referenceContainer, EReference crossReference);

	/**
	 * Determines if the decider needs to look into the given reference.
	 *
	 * @param container
	 * @param containingReference
	 * @return decision
	 */
	boolean containsCandidates(EObject container, EReference containingReference);

	/**
	 * Determines if references given element, which is a child of the elements visited by default in
	 * the tree traversing, should also be considered.
	 *
	 * @param element
	 * @return decision
	 */
	boolean walkInto(EObject element);

	/**
	 * Decides if the given element is a (possibly the final) target.
	 *
	 * @param identifier
	 * @param element
	 * @return decision
	 */
	boolean isPossibleTarget(String identifier, EObject element);

	/**
	 * Should return true, if the last time <code>isPossibleTarget()</code> returned true
	 * was the final decision (i.e., traversal can be stopped).
	 *
	 * @return decision
	 */
	boolean isSure();

	/**
	 * Defines, if the scoping for this decider allows to look behind the point where
	 * the reference, for which the target is searched, was defined.
	 *
	 * @return decision
	 */
	boolean continueAfterReference();

	/**
	 * Allows for the decider to provide additional target candidates that are not
	 * directly contained in the traversed model tree. The additional candidates will be
	 * inserted into the scope behind the children of the given container.
	 *
	 * @param identifier
	 * @param container
	 * @return
	 */
	public EList<? extends EObject> getAdditionalCandidates(String identifier, EObject container) ;


	void activate();

	void deactivate();

	boolean isActive();
}
