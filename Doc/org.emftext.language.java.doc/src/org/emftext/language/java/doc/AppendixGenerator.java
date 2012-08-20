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
package org.emftext.language.java.doc;

import java.io.File;
import java.io.FileFilter;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.codegen.ecore.genmodel.GenClass;
import org.eclipse.emf.codegen.ecore.genmodel.GenModelPackage;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.plugin.EcorePlugin;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.Resource.Diagnostic;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.xmi.impl.EcoreResourceFactoryImpl;
import org.emftext.language.java.JavaClasspath;
import org.emftext.language.java.resource.JaMoPPUtil;

/**
 * A test that loads all EJava files and checks that they do not contain errors.
 */
public class AppendixGenerator {
	
	private final String GENMODEL_GENMODEL_URI = "platform:/plugin/org.eclipse.emf.codegen.ecore/";
	private final String ECORE_GENMODEL_URI = "platform:/plugin/org.eclipse.emf.ecore/";
	
	private Map<URI, URI> uriMap = new LinkedHashMap<URI, URI>();
	
	public static void main(String[] args) {
		new AppendixGenerator().generateAppendix();
	}
	
	public void generateAppendix() {
		setUp();
		
		//TODO do we really need this? Can't we get the information from the Ecore model?
		List<File> files = findEJavaFiles(new File("../org.emftext.language.java/metamodel").getAbsoluteFile());
		System.out.println("Found files: " + files.size());
		
		for (File file : files) {
			URI uri = URI.createFileURI(file.getAbsolutePath());
			
			//ATTENTION!
			//each file needs to be loaded in a separate resource set, since 
			//eJava creates "virtual" resource inside the set when wrapping 
			//Ecore into Java types. If more that one eJava file is loaded,
			//the real resources overlap with the virtual ones.
			ResourceSet rs = createNewResourceSet();
			Resource resource = rs.getResource(uri, true);
			EcoreUtil.resolveAll(resource);
			List<Diagnostic> errors = resource.getErrors();
			for (Diagnostic error : errors) {
				System.out.println("Found error in " + uri.toString() + ": " + error);
			}
			//EPackageWrapper packageWrapper = (EPackageWrapper) resource.getContents().get(0);
		}
	}
	
	private void setUp() {
		map(ECORE_GENMODEL_URI, EClass.class);
		map(GENMODEL_GENMODEL_URI, GenClass.class);
		Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().put(
				"*", new EcoreResourceFactoryImpl());

		//initialize JaMoPP to load normal java files
		JaMoPPUtil.initialize();
		// initialize packages
		EcorePackage.eINSTANCE.getEClass();
		GenModelPackage.eINSTANCE.getGenClass();
		// register generator models
		registerEcoreGenModel();
		registerGenModelGenModel();

		// configure classpath
		JavaClasspath javaClasspath = JavaClasspath.get();
		javaClasspath.registerClassifierJar(URI.createURI(getJarPath(EClass.class)));
		javaClasspath.registerClassifierJar(URI.createURI(getJarPath(EList.class)));
		javaClasspath.registerClassifierJar(URI.createURI(getJarPath(GenClass.class)));
		javaClasspath.registerSourceOrClassFileFolder(URI.createFileURI("../org.emftext.language.java/src"));
	}
	
	private void registerEcoreGenModel() {
		String genModelPath = "/model/Ecore.genmodel";
		String nsURI = "http://www.eclipse.org/emf/2002/Ecore";
		Class<EClass> clazz = EClass.class;

		registerGenModel(genModelPath, nsURI, clazz);
	}

	private void registerGenModelGenModel() {
		String genModelPath = "/model/GenModel.genmodel";
		String nsURI = "http://www.eclipse.org/emf/2002/GenModel";
		Class<GenClass> clazz = GenClass.class;

		registerGenModel(genModelPath, nsURI, clazz);
	}

	private void registerGenModel(String genModelPath, String nsURI, Class<?> clazz) {
		final Map<String, URI> packageNsURIToGenModelLocationMap = EcorePlugin.getEPackageNsURIToGenModelLocationMap();
		String path = clazz.getResource(genModelPath).getFile();
		path = path.replace("file:/", "archive:file:/");
		URI uri = URI.createURI(path);
		packageNsURIToGenModelLocationMap.put(nsURI, uri);
	}
	
	private String getJarPath(Class<?> clazz) {
		String path = getClassFileLocation(clazz);
		path = path.replace("!/", "");
		return path;
	}

	private void map(String baseURI, Class<?> clazz) {
		URI from = URI.createURI(baseURI);
		URI to = getURI(clazz);
		uriMap.put(from, to);
	}

	private URI getURI(Class<?> clazz) {
		String path = getClassFileLocation(clazz);
		path = path.replace("file:/", "archive:file:/");
		URI uri = URI.createURI(path);
		return uri;
	}

	private String getClassFileLocation(Class<?> clazz) {
		String path = clazz.getResource(clazz.getSimpleName() + ".class").getFile();
		path = path.replaceAll("!.*", "!/");
		return path;
	}

	private ResourceSet createNewResourceSet() {
		ResourceSetImpl rs = new ResourceSetImpl();
		
		// configure URI map
		rs.getURIConverter().getURIMap().putAll(uriMap);
		return rs;
	}

	private List<File> findEJavaFiles(File dir) {
		List<File> foundFiles = new ArrayList<File>();
		File[] eJavaFiles = dir.listFiles(new FileFilter() {
			
			@Override
			public boolean accept(File file) {
				return file.isFile() && 
						file.getName().endsWith(".ejava");
			}
		});
		if (eJavaFiles != null) {
			for (File file : eJavaFiles) {
				foundFiles.add(file);
			}
		}
		File[] subDirs = dir.listFiles(new FileFilter() {
			
			@Override
			public boolean accept(File file) {
				return file.isDirectory() && 
						!file.getName().startsWith(".");
			}
		});
		if (subDirs == null) {
			return foundFiles;
		}
		for (File subDir : subDirs) {
			foundFiles.addAll(findEJavaFiles(subDir));
		}
		return foundFiles;
	}
}
