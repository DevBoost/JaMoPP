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
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.emftext.language.java.JavaClasspath;
import org.emftext.language.java.annotations.AnnotationInstance;
import org.emftext.language.java.classifiers.Annotation;
import org.emftext.language.java.classifiers.Classifier;
import org.emftext.language.java.classifiers.ConcreteClassifier;
import org.emftext.language.java.resource.java.IJavaReferenceResolveResult;
import org.emftext.language.java.resource.java.IJavaReferenceResolver;
import org.emftext.language.java.resource.java.analysis.decider.ConcreteClassifierDecider;
import org.emftext.language.java.resource.java.analysis.decider.IResolutionTargetDecider;
import org.emftext.language.java.resource.java.analysis.helper.ScopedTreeWalker;

public class AnnotationInstanceAnnotationReferenceResolver implements
	IJavaReferenceResolver<org.emftext.language.java.annotations.AnnotationInstance, org.emftext.language.java.classifiers.Classifier> {

	JavaDefaultResolverDelegate<AnnotationInstance, Classifier> delegate =
		new JavaDefaultResolverDelegate<AnnotationInstance, Classifier>();

	public java.lang.String deResolve(org.emftext.language.java.classifiers.Classifier element, org.emftext.language.java.annotations.AnnotationInstance container, org.eclipse.emf.ecore.EReference reference) {
		if (element.eIsProxy()) {
			return delegate.deResolve(element, container, reference);
		}
		return element.getName();
	}

	public void resolve(java.lang.String identifier, org.emftext.language.java.annotations.AnnotationInstance annotationInstance, org.eclipse.emf.ecore.EReference reference, int position, boolean resolveFuzzy, IJavaReferenceResolveResult<org.emftext.language.java.classifiers.Classifier> result) {
		List<IResolutionTargetDecider> deciderList = new ArrayList<IResolutionTargetDecider>();
		EObject startingPoint = annotationInstance;
		EObject target =  null;

		if(annotationInstance.getNamespaces().size() > 0) {
			EObject lastClassInNS = ConcreteClassifierDecider.resolveRelativeNamespace(
					annotationInstance, 0, annotationInstance, annotationInstance, reference);
			if (lastClassInNS != null) {
				startingPoint = lastClassInNS;
			}
			else {
				//absolute class starting with package
				target = resolveFullQualifiedAnnotationReferences(identifier, annotationInstance);
			}
		}

		if (target == null) {
			deciderList.add(new ConcreteClassifierDecider());
			ScopedTreeWalker treeWalker = new ScopedTreeWalker(deciderList);
			target = treeWalker.walk(startingPoint, identifier, annotationInstance, reference);
		}

		if (target != null) {
			result.addMapping(identifier, (Classifier) target);
		}
	}

	private EObject resolveFullQualifiedAnnotationReferences(String identifier,
			AnnotationInstance annotationInstance) {

		if (annotationInstance.getNamespaces().size() > 0) {
			String containerName = annotationInstance.getNamespacesAsString();
			ConcreteClassifier target = (ConcreteClassifier) EcoreUtil.resolve(
					JavaClasspath.get(annotationInstance).getClassifier(containerName + identifier), annotationInstance.eResource());

			if (target instanceof Annotation) {
				return target;
			}
		}

		return null;
	}

	public void setOptions(Map<?, ?> options) {
	}
}
