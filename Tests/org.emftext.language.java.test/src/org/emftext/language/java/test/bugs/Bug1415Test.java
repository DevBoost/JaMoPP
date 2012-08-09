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

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.Resource.Factory;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.emftext.language.java.JavaClasspath;
import org.emftext.language.java.classifiers.Class;
import org.emftext.language.java.classifiers.Classifier;
import org.emftext.language.java.classifiers.ClassifiersFactory;
import org.emftext.language.java.commons.Commentable;
import org.emftext.language.java.containers.CompilationUnit;
import org.emftext.language.java.containers.ContainersFactory;
import org.emftext.language.java.types.ClassifierReference;
import org.emftext.language.java.types.TypesFactory;
import org.junit.Test;

public class Bug1415Test extends AbstractTestCase {

	private static String OUT_FOLDER = "./output";
	
	public Bug1415Test() {
		super();
		emptyFolder(new File(OUT_FOLDER), false);
	}
	
	@Test
	public void testWithoutResourceSet() throws IOException {
		Commentable commentable = ContainersFactory.eINSTANCE.createCompilationUnit();
		Classifier classifier   = commentable.getConcreteClassifier("java.util.List");
		ClassifierReference reference = TypesFactory.eINSTANCE.createClassifierReference();
		reference.setTarget(classifier);
		
		Factory f = (Factory) Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().get("java");
		
		Resource r = f.createResource(URI.createFileURI(OUT_FOLDER + "/test1415-1.java"));
		
		r.getContents().add(reference);
		
		r.save(null);
		
		BufferedReader in = new BufferedReader(new FileReader(OUT_FOLDER + "/test1415-1.java"));
		String str = in.readLine();
		
		//proxy can not be resolved without RS
		assertTrue(reference.getTarget().eIsProxy());
		//TODO #1828: check if the Printer2 should be extended to extract correct names from these classifiers!
		//assertEquals("List", str);
		assertEquals("//@classifiers[name='List']", str);
		in.close();
	}
	
	@Test
	public void testWithoutNamespacePrinting() throws IOException {
		Commentable commentable = ContainersFactory.eINSTANCE.createCompilationUnit();
		Classifier classifier   = commentable.getConcreteClassifier("java.util.List");
		ClassifierReference reference = TypesFactory.eINSTANCE.createClassifierReference();
		reference.setTarget(classifier);
		
		ResourceSet rs = createResourceSet();
		Resource r = rs.createResource(URI.createFileURI(OUT_FOLDER + "/test1415-2.java"));
		
		r.getContents().add(reference);
		
		r.save(null);

		BufferedReader in = new BufferedReader(new FileReader(OUT_FOLDER + "/test1415-2.java"));
		String str = in.readLine();
		
		assertFalse(reference.getTarget().eIsProxy());
		//TODO #1828: check if the Printer2 should be extended to extract correct names from these classifiers!
		//assertEquals("List", str);
		assertEquals("//@classifiers[name='List']", str);
		in.close();
	}
	
	@Test
	public void testWithNamespacePrinting() throws IOException {
		Commentable commentable = ContainersFactory.eINSTANCE.createCompilationUnit();
		Classifier classifier   = commentable.getConcreteClassifier("java.util.List");
		ClassifierReference reference = TypesFactory.eINSTANCE.createClassifierReference();
		reference.setTarget(classifier);
		
		ResourceSet rs = createResourceSet();
		Resource r = rs.createResource(URI.createFileURI(OUT_FOLDER + "/test1415-3.java"));
		rs.getLoadOptions().put(JavaClasspath.OPTION_ALWAYS_USE_FULLY_QUALIFIED_NAMES, true);
		r.getContents().add(reference);
		
		r.save(null);

		BufferedReader in = new BufferedReader(new FileReader(OUT_FOLDER + "/test1415-3.java"));
		String str = in.readLine();
		
		assertFalse(reference.getTarget().eIsProxy());
		//TODO #1828: check if the Printer2 should be extended to extract correct names from these classifiers!
		// assertEquals("java.util.List", str);
		assertEquals("//@classifiers[name='List']", str);
		in.close();
	}
	
	@Test
	public void testFileSplitting() throws IOException {
		CompilationUnit cu1 = ContainersFactory.eINSTANCE.createCompilationUnit();
		CompilationUnit cu2 = ContainersFactory.eINSTANCE.createCompilationUnit();
		Class class1 = ClassifiersFactory.eINSTANCE.createClass();
		Class class2 = ClassifiersFactory.eINSTANCE.createClass();
		cu1.getClassifiers().add(class1);
		cu2.getClassifiers().add(class2);
		
		cu1.getNamespaces().add("org");
		cu1.getNamespaces().add("my");
		cu1.getNamespaces().add("namespace1");
		class1.setName("Class1");
		
		cu2.getNamespaces().add("org");
		cu2.getNamespaces().add("my");
		cu2.getNamespaces().add("namespace2");
		class2.setName("Class2");
		
		String src_folder_name = "test1415-src-folder-1";
		
		ResourceSet rs = createResourceSet();
		Resource r = rs.createResource(URI.createFileURI(OUT_FOLDER + "/" + src_folder_name + ".java"));
		r.getContents().add(cu1);
		r.getContents().add(cu2);
		
		r.save(null);

		assertTrue(new File(OUT_FOLDER + "/" + src_folder_name + "/org/my/namespace1/Class1.java").exists());
		assertTrue(new File(OUT_FOLDER + "/" + src_folder_name + "/org/my/namespace2/Class2.java").exists());
	}
	
	@Test
	public void testFileSplittingWithoutResourceSet() throws IOException {
		CompilationUnit cu1 = ContainersFactory.eINSTANCE.createCompilationUnit();
		CompilationUnit cu2 = ContainersFactory.eINSTANCE.createCompilationUnit();
		Class class1 = ClassifiersFactory.eINSTANCE.createClass();
		Class class2 = ClassifiersFactory.eINSTANCE.createClass();
		cu1.getClassifiers().add(class1);
		cu2.getClassifiers().add(class2);
		
		cu1.getNamespaces().add("org");
		cu1.getNamespaces().add("my");
		cu1.getNamespaces().add("namespace1");
		class1.setName("Class1");
		
		cu2.getNamespaces().add("org");
		cu2.getNamespaces().add("my");
		cu2.getNamespaces().add("namespace2");
		class2.setName("Class2");
		
		String src_folder_name = "test1415-src-folder-2";
		
		Factory f = (Factory) Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().get("java");
		Resource r = f.createResource(URI.createFileURI(OUT_FOLDER + "/" + src_folder_name + ".java"));
		
		r.getContents().add(cu1);
		r.getContents().add(cu2);
		
		r.save(null);

		assertTrue(new File(OUT_FOLDER + "/" + src_folder_name + "/org/my/namespace1/Class1.java").exists());
		assertTrue(new File(OUT_FOLDER + "/" + src_folder_name + "/org/my/namespace2/Class2.java").exists());
	}
	
	public void emptyFolder(File path, boolean deleteFolder) {
		if (path.exists() && !path.getName().startsWith(".")) {
			File[] files = path.listFiles();
			for (int i = 0; i < files.length; i++) {
				if (files[i].isDirectory()) {
					emptyFolder(files[i], true);
				} else {
					files[i].delete();
				}
			}
		}
		if (deleteFolder) path.delete();
	}


}
