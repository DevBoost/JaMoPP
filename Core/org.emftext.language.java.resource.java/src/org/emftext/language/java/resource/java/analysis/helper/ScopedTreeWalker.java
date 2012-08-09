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
package org.emftext.language.java.resource.java.analysis.helper;

import java.util.List;

import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.resource.Resource;
import org.emftext.language.java.commons.NamedElement;
import org.emftext.language.java.resource.java.analysis.decider.IResolutionTargetDecider;

/**
 * This class can be used to traverse a model tree after parsing for reference resolving. It follows
 * scoping rules common to textual languages.
 * <p>
 * It starts at the point of the reference, walking <i>up</i> the tree but visiting, for each step up,
 * the children of the new parent. On each visited element it applies a set of deciders with which
 * it is initialized.
 */
public class ScopedTreeWalker {

	protected List<IResolutionTargetDecider> deciderList;

	private EObject currentBestResult = null;
	private boolean finished = false;

	/**
	 * Initializes a new walker with a list of deciders.
	 *
	 * @param deciderList
	 */
	public ScopedTreeWalker(List<IResolutionTargetDecider> deciderList) {
		this.deciderList = deciderList;
	}

	/**
	 * Main method.
	 *
	 * @param startingPoint
	 * @param identifier
	 * @param container
	 * @param crossReference
	 * @return the target if one was found.
	 */
	public EObject walk(EObject startingPoint,
			String identifier, EObject container,
			EReference crossReference) {

		if (startingPoint == null) {
			return null;
		}

		//deactivate deciders not suited here at all
		for(IResolutionTargetDecider decider : deciderList) {
			if (!decider.canFindTargetsFor(container, crossReference)) {
				decider.deactivate();
			}
		}

		doWalk(identifier, startingPoint, null, -1);

		for(IResolutionTargetDecider decider : deciderList) {
			decider.activate();
		}

		return currentBestResult;

	}

	private void doWalk(String identifier, EObject startingPoint, EReference navOrigin, int posInNavOrigin) {

		searchInDirectChildren(identifier, startingPoint, navOrigin, posInNavOrigin);
		if(finished) {
			return;
		}

		searchInAdditionalContent(identifier, startingPoint, navOrigin, posInNavOrigin);
		if(finished) {
			return;
		}

		for(IResolutionTargetDecider decider : deciderList) {
			if (decider.isActive()) {
				walkDown(decider, identifier, startingPoint, navOrigin, posInNavOrigin);
			}
		}
		if(finished) {
			return;
		}

		walkUp(identifier, startingPoint);
	}

	private void walkUp(String identifier, EObject container) {
		//walk up
		if (container.eContainer() != null) {
			EReference navOrigin = container.eContainmentFeature();
			int posInNavOrigin = 0;
			if (navOrigin.isMany()) {
				EList<?> value = (EList<?>)container.eContainer().eGet(navOrigin);
				posInNavOrigin = value.indexOf(container);
			}

			doWalk(identifier, container.eContainer(), navOrigin, posInNavOrigin);
		}
	}

	private void walkDown(IResolutionTargetDecider decider, String identifier, EObject container, EReference navOrigin, int posInNavOrigin) {
		EClass containerClass = container.eClass();

		for(EReference reference : getReferences(containerClass)) {
			if(reference.isContainment()) {
				EList<EObject> contentList = null;
				if (decider.continueAfterReference()) {
					contentList = getContentList(container, reference, null, -1);
				}
				else {
					contentList = getContentList(container, reference, navOrigin, posInNavOrigin);
				}
				for(EObject element : contentList) {
					if(decider.walkInto(element)) {
						searchInDirectChildren(identifier, element, null, -1);
						//walk further down
						walkDown(decider, identifier, element, null, -1);
					}
				}
			}
		}
	}

	/**
	 * Looks for last best fit.
	 *
	 * @param identifier
	 * @param container
	 * @param navOrigin
	 * @param posInNavOrigin
	 */
	private void searchInDirectChildren(String identifier, EObject container, EReference navOrigin, int posInNavOrigin) {

		EClass containerClass = container.eClass();

		for(IResolutionTargetDecider decider : deciderList) {
			if (decider.isActive()) {
				for(EReference reference : getReferences(containerClass)) {
					if(reference.isContainment()) {
						if(decider.containsCandidates(container, reference)) {
							EList<EObject> contentList = null;
							if (decider.continueAfterReference()) {
								contentList = getContentList(container, reference, null, -1);
							}
							else {
								contentList = getContentList(container, reference, navOrigin, posInNavOrigin);
							}
							for(EObject element : contentList) {
								if(decider.isPossibleTarget(identifier, element)) {
									currentBestResult = element;
								}
							}
							if (currentBestResult != null && decider.isSure()) {
								finished = true;
								return;
							}

						}
					}
				}
			}
		}
	}

	private EList<EReference> getReferences(EClass containerClass) {
		EList<EReference> references =  containerClass.getEAllReferences();
		return references;
	}

	/**
	 * Looks for first best fit.
	 *
	 * @param identifier
	 * @param container
	 * @param navOrigin
	 * @param posInNavOrigin
	 */
	private void searchInAdditionalContent(String identifier, EObject container, EReference navOrigin, int posInNavOrigin) {
		for(IResolutionTargetDecider decider : deciderList) {
			if (decider.isActive()) {
				EList<? extends EObject> additionalCandidates = decider.getAdditionalCandidates(identifier, container);

				if (additionalCandidates != null) {
					for(EObject element : additionalCandidates) {
						if(decider.isPossibleTarget(identifier, element)) {
							currentBestResult = element;
							if (decider.isSure()) {
								if (!currentBestResult.eIsProxy() && currentBestResult.eResource() == null) {
									Resource containerResource = container.eResource();
									if (containerResource != null) {
										//for package references and the array length field: 
										//check if there already is a suitable element in 
										//the resource, or create one if not.
										for (EObject content : container.eResource().getContents()) {
											if (content.eClass().equals(currentBestResult.eClass())) {
												if (content instanceof NamedElement) {
													NamedElement cand = (NamedElement) content;
													if (((NamedElement) content).getName().equals(((NamedElement)currentBestResult).getName())) {
														currentBestResult = cand;
													}
												}
											}
										}
										if (currentBestResult.eResource() == null) {
											containerResource.getContents().add(currentBestResult);
										}
									}
								}
								finished = true;
								return;
							}
						}
					}
				}
			}
		}
	}

	private EList<EObject> getContentList(EObject container, EReference reference, EReference navOrigin, int posInNavOrigin) {
		EList<EObject> contentList = new BasicEList<EObject>();

		if(!reference.isMany()) {
			EObject value = (EObject)container.eGet(reference);
			contentList.add(value);
		}
		else {
			@SuppressWarnings("unchecked")
			EList<EObject> value = (EList<EObject>)container.eGet(reference);
			if (!reference.equals(navOrigin)) {
				contentList.addAll(value);
			}
			else {
				contentList.addAll(value.subList(0, posInNavOrigin + 1));
			}
		}

		return contentList;
	}
}
