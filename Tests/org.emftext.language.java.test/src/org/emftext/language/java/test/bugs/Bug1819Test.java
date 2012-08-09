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
package org.emftext.language.java.test.bugs;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.emftext.language.java.JavaUniquePathConstructor;
import org.emftext.language.java.classifiers.ClassifiersFactory;
import org.emftext.language.java.containers.CompilationUnit;
import org.emftext.language.java.containers.ContainersFactory;
import org.junit.Test;

public class Bug1819Test extends AbstractTestCase {

	public Bug1819Test() {
		super();
	}
	
	@Test
	public void testDefaultPackage() throws IOException {
		String className = "TestName";
		
		ResourceSet rs = createResourceSet();
		Resource r = rs.createResource(JavaUniquePathConstructor.getJavaFileResourceURI(className));
		CompilationUnit cu = ContainersFactory.eINSTANCE.createCompilationUnit();
		org.emftext.language.java.classifiers.Class clazz = ClassifiersFactory.eINSTANCE.createClass();
		clazz.setName(className);
		cu.getClassifiers().add(clazz);
		
		r.getContents().add(cu);
		
		ByteArrayOutputStream os = new ByteArrayOutputStream();
		r.save(os , null);
		String result = new String(os.toByteArray()).trim();
	
		assertEquals("class " + className + " {\n}", result);
	}
	
	@Test
	public void testMissingDeclaredPackage() throws IOException {
		String className = "TestName";
		String packageName = "a.b.c";
		
		ResourceSet rs = createResourceSet();
		Resource r = rs.createResource(JavaUniquePathConstructor.getJavaFileResourceURI(packageName + "." + className));
		CompilationUnit cu = ContainersFactory.eINSTANCE.createCompilationUnit();
		org.emftext.language.java.classifiers.Class clazz = ClassifiersFactory.eINSTANCE.createClass();
		clazz.setName(className);
		cu.getClassifiers().add(clazz);
		
		r.getContents().add(cu);
		
		ByteArrayOutputStream os = new ByteArrayOutputStream();
		r.save(os , null);
		String result = new String(os.toByteArray()).trim();
		
		assertEquals("package " + packageName + ";\n\n\nclass " + className + " {\n}", result);		
	}

}
