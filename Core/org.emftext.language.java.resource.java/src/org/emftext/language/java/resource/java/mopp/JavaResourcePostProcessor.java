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
package org.emftext.language.java.resource.java.mopp;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.resource.Resource;
import org.emftext.language.java.references.ElementReference;
import org.emftext.language.java.references.IdentifierReference;
import org.emftext.language.java.references.ReferenceableElement;
import org.emftext.language.java.resource.java.IJavaResourcePostProcessor;
import org.emftext.language.java.util.JavaModelCompletion;
import org.emftext.language.java.util.JavaModelRepairer;

public class JavaResourcePostProcessor implements IJavaResourcePostProcessor {
	
	public void process(JavaResource resource) {
		repairAndComplete(resource);
	}
	
	public void terminate() {
		// To signal termination to the process() method, setting a boolean field is
		// recommended. Depending on the value of this field process() can stop its
		// computation. However, this is only required for computation intensive
		// post-processors.
	}
	
	protected void repairAndComplete(JavaResource resource) {
		new JavaModelRepairer() {
			protected void registerContextDependentProxy(
					Resource resource,
					IdentifierReference mainIdReference, EReference targetReference,
					String id, EObject proxy) {
				assert !targetReference.isMany();

				((JavaResource)resource).registerContextDependentProxy(
						new JavaContextDependentURIFragmentFactory<ElementReference, ReferenceableElement>(
								new JavaReferenceResolverSwitch().getElementReferenceTargetReferenceResolver()),
						mainIdReference,
						targetReference,
						id,
						proxy,
						-1);
			}
		}.repair(resource);

		JavaModelCompletion.complete(resource);
	}
}
