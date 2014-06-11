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
package org.emftext.language.java.resource;

import java.util.Map;

import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.Resource.Factory;
import org.eclipse.emf.ecore.resource.Resource.Factory.Registry;
import org.emftext.language.java.JavaPackage;

public class JaMoPPUtil {

	/**
	 * Globally registers the JaMoPP resource factories for Java source and
	 * class files. Also, the JaMoPP meta model for Java is added to the EMF
	 * package registry.
	 */
	public static void initialize() {
		EPackage.Registry.INSTANCE.put(JavaPackage.eNS_URI, JavaPackage.eINSTANCE);
		
		Registry registry = Resource.Factory.Registry.INSTANCE;
		Map<String, Object> extensionToFactoryMap = registry.getExtensionToFactoryMap();
		Factory factory = new JavaSourceOrClassFileResourceFactoryImpl();
		extensionToFactoryMap.put("java", factory);
		extensionToFactoryMap.put("class", factory); 
	}
}
