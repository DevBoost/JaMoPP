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
package org.emftext.language.java.resource.java.analysis;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.ecore.EObject;
import org.emftext.language.java.annotations.AnnotationAttributeSetting;
import org.emftext.language.java.members.InterfaceMethod;
import org.emftext.language.java.resource.java.IJavaReferenceResolveResult;
import org.emftext.language.java.resource.java.IJavaReferenceResolver;
import org.emftext.language.java.resource.java.analysis.decider.IResolutionTargetDecider;
import org.emftext.language.java.resource.java.analysis.decider.InterfaceMethodDecider;
import org.emftext.language.java.resource.java.analysis.helper.ScopedTreeWalker;

public class AnnotationAttributeSettingAttributeReferenceResolver implements
	IJavaReferenceResolver<AnnotationAttributeSetting, InterfaceMethod> {

	JavaDefaultResolverDelegate<AnnotationAttributeSetting, InterfaceMethod> delegate =
		new JavaDefaultResolverDelegate<AnnotationAttributeSetting, InterfaceMethod>();

	public java.lang.String deResolve(InterfaceMethod element, AnnotationAttributeSetting container, org.eclipse.emf.ecore.EReference reference) {
		if (element.eIsProxy()) {
			return delegate.deResolve(element, container, reference);
		}
		return element.getName();
	}

	public void resolve(java.lang.String identifier, AnnotationAttributeSetting container, org.eclipse.emf.ecore.EReference reference, int position, boolean resolveFuzzy, IJavaReferenceResolveResult<InterfaceMethod> result) {
		List<IResolutionTargetDecider> deciderList = new ArrayList<IResolutionTargetDecider>();

		EObject startingPoint = container.getContainingAnnotationInstance().getAnnotation();

		deciderList.add(new InterfaceMethodDecider());

		ScopedTreeWalker treeWalker = new ScopedTreeWalker(deciderList);

		EObject target = treeWalker.walk(startingPoint, identifier, container, reference);

		if (target != null) {
			result.addMapping(identifier, (InterfaceMethod) target);
		}
	}

	public void setOptions(Map<?, ?> options) {
	}
}
