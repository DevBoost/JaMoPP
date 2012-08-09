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
package org.emftext.language.java.resource.jdt;

import java.util.List;

import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.jdt.core.IJavaProject;
import org.emftext.commons.jdt.JDTJavaClassifier;
import org.emftext.commons.jdt.resolve.JDTClassifierResolver;
import org.emftext.language.java.JavaClasspath;

public class JDTConnector implements JavaClasspath.Initializer {

	public void initialize(Resource resource) {
		initializeResourceSet(resource.getResourceSet(), resource.getURI());
	}
	
	public boolean requiresLocalClasspath() {
		return true;
	}
	
	public boolean requiresStdLib() {
		return false;
	}
	
	private void initializeResourceSet(ResourceSet resourceSet, URI resourceURI) {
		if (resourceSet == null) {
			return;
		}
		if (resourceSet.getURIConverter() == null) {
			return;
		}
		if (!resourceSet.getURIConverter().normalize(resourceURI)
				.isPlatformResource()) {
			return;
		}

		if (resourceURI != null) {
			JDTClassifierResolver jdtClassResolver = new JDTClassifierResolver();
			IJavaProject javaProject = jdtClassResolver.getJavaProject(resourceURI);
			List<JDTJavaClassifier> classifiersInClassPath = jdtClassResolver.getAllClassifiersInClassPath(javaProject);
			registerJavaProjectInClassPath(resourceSet, classifiersInClassPath);
		}
	}

	private void registerJavaProjectInClassPath(ResourceSet resourceSet,
			List<JDTJavaClassifier> classifiersInClassPath) {

		JavaClasspath javaClasspath = JavaClasspath.get(resourceSet);
		for (JDTJavaClassifier jdtClassifier : classifiersInClassPath) {
			registerClassifier(javaClasspath, jdtClassifier);
		}
	}

	private void registerClassifier(JavaClasspath javaClasspath, JDTJavaClassifier jdtClass) {
		String path = jdtClass.getPath();
		String filePath = null;
		String inArchivePath = null;
		if (path.contains(".jar|")) {
			String[] split = path.split("\\|");
			filePath = split[0];
			inArchivePath = split[1];
		} else {
			filePath = path;
		}
		
		URI uri;
		if (isInWorkspace(filePath)) {
			if (inArchivePath != null) {
				uri = URI.createURI("archive:" + URI.createPlatformResourceURI(filePath, true) + "!/" + inArchivePath);
			} else {
				uri = URI.createPlatformResourceURI(filePath, true);
			}		
		} else {
			if (inArchivePath != null) {
				uri = URI.createURI("archive:" + URI.createFileURI(filePath).toString() + "!/" + inArchivePath);
			} else {
				uri = URI.createFileURI(filePath);
			}
		}

		String fullContainerName = jdtClass.getPackageName() + ".";
		for (String enclosingType : jdtClass.getEnclosingTypeNames()) {
			fullContainerName = fullContainerName + enclosingType + "$";
		}
		
		javaClasspath.registerClassifier(
				fullContainerName, 
				jdtClass.getSimpleName(), uri);			
	}
		
	private boolean isInWorkspace(String path) {
		IWorkspaceRoot wsRoot = ResourcesPlugin.getWorkspace().getRoot();
		return wsRoot.findMember(path) != null;
	}
}
