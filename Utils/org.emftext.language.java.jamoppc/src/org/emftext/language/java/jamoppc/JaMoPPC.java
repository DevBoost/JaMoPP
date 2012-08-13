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
package org.emftext.language.java.jamoppc;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;
import org.emftext.language.java.JavaClasspath;
import org.emftext.language.java.JavaPackage;
import org.emftext.language.java.resource.JavaSourceOrClassFileResourceFactoryImpl;

public class JaMoPPC {

	protected static final ResourceSet rs = new ResourceSetImpl();

	public static void main(String[] args) throws IOException {
		if (args.length < 2) {
			printUsageAndExit();
		}

		setUp();

		File srcFolder = new File(args[0]);
		if (!srcFolder.exists()) {
			System.out.println("not found: " + args[0]);
			return;
		}

		JavaClasspath cp = JavaClasspath.get(rs);

		// register jar files
		for (int i = 2; i < args.length; i++) {
			File jarPath = new File(args[i]);
			if (!jarPath.exists()) {
				System.out.println("not found: " + args[i]);
				return;
			}
			System.out.println("Registering JAR " + jarPath.getCanonicalPath());
			cp.registerClassifierJar(URI.createFileURI(jarPath
					.getCanonicalPath()));
		}

		// load all java files into resource set
		loadAllFilesInResourceSet(srcFolder, ".java");

		if (!resolveAllProxies(0)) {
			System.err.println("Resolution of some Proxies failed...");
			Iterator<Notifier> it = rs.getAllContents();
			while (it.hasNext()) {
				Notifier next = it.next();
				if (next instanceof EObject) {
					EObject o = (EObject) next;
					if (o.eIsProxy()) {
						try {
							it.remove();
						} catch (UnsupportedOperationException e) {
							e.printStackTrace();
						}
					}
				}
			}
			// return;
		}

		URI srcUri = URI.createFileURI(srcFolder.getCanonicalPath());

		String outFileOrDir = args[1];
		if (outFileOrDir.endsWith(".xmi")) {
			saveToSingleXMIFile(srcUri, new File(outFileOrDir));
		} else {
			saveToFolder(srcUri, new File(outFileOrDir));
		}

	}

	private static void saveToSingleXMIFile(URI srcUri, File file)
			throws IOException {
		File parentDir = file.getParentFile();
		if (parentDir == null) {
			parentDir = new File(System.getProperty("user.dir"));
		} else if (!parentDir.exists()) {
			parentDir.mkdirs();
		}
		URI outUri = URI.createFileURI(file.getCanonicalPath());
		Resource xmiResource = rs.createResource(outUri);
		for (Resource javaResource : new ArrayList<Resource>(rs.getResources())) {
			xmiResource.getContents().addAll(javaResource.getContents());
		}

		// save the metamodels (schemas) for dynamic loading
		serializeMetamodel(parentDir);

		saveXmiResource(xmiResource);
	}

	private static void saveToFolder(URI srcUri, File outFolder)
			throws IOException {
		List<Resource> result = new ArrayList<Resource>();
		URI outUri = URI.createFileURI(outFolder.getCanonicalPath());

		for (Resource javaResource : new ArrayList<Resource>(rs.getResources())) {
			URI srcURI = javaResource.getURI();
			srcURI = rs.getURIConverter().normalize(srcURI);
			URI outFileURI = outUri.appendSegments(
					srcURI.deresolve(srcUri.appendSegment("")).segments())
					.appendFileExtension("xmi");
			Resource xmiResource = rs.createResource(outFileURI);
			xmiResource.getContents().addAll(javaResource.getContents());
			result.add(xmiResource);
		}

		// save the metamodels (schemas) for dynamic loading
		serializeMetamodel(outFolder);

		for (Resource xmiResource : result) {
			saveXmiResource(xmiResource);
		}
	}

	private static void saveXmiResource(Resource xmiResource)
			throws IOException {
		Map<Object, Object> options = new HashMap<Object, Object>();
		options.put(XMLResource.OPTION_SCHEMA_LOCATION, Boolean.TRUE);
		xmiResource.save(options);
	}

	private static void printUsageAndExit() {
		System.out.println("JaMoPPC Usage:");
		System.out.println("==============");
		System.out.println();
		System.out
				.println("To parse all files in a source folder and produce one model file per\n"
						+ "parsed compilation unit in the target folder, use:");
		System.out.println();
		System.out
				.println("  jamoppc <source folder path> <target folder path> <jar file paths>*");
		System.out.println();
		System.out
				.println("To parse all files in a source folder and produce one XMI file\n"
						+ "with the complete syntax graph, use:");
		System.out.println();
		System.out
				.println("  jamoppc <source folder path> <target XMI file> <jar file paths>*");
		System.out.println();
		System.out
				.println("In the latter case, the second parameter has to end in \".xmi\".");
		System.exit(1);
	}

	protected static void setUp() {
		EPackage.Registry.INSTANCE.put("http://www.emftext.org/java",
				JavaPackage.eINSTANCE);
		Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().put(
				"java", new JavaSourceOrClassFileResourceFactoryImpl());
		Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().put(
				Resource.Factory.Registry.DEFAULT_EXTENSION,
				new XMIResourceFactoryImpl());
	}

	protected static void serializeMetamodel(File outFolder) throws IOException {
		URI outUri = URI.createFileURI(outFolder.getCanonicalPath());

		URI javaEcoreURI = outUri.appendSegment("java.ecore");
		Resource javaEcoreResource = rs.createResource(javaEcoreURI);
		javaEcoreResource.getContents().addAll(
				JavaPackage.eINSTANCE.getESubpackages());

		javaEcoreResource.save(null);
	}

	protected static void loadAllFilesInResourceSet(File startFolder,
			String extension) throws IOException {
		for (File member : startFolder.listFiles()) {
			if (member.isFile()) {
				if (member.getName().endsWith(extension)) {
					System.out.println("Parsing " + member);
					parseResource(member);
				} else {
					System.out.println("Skipping " + member);
				}
			}
			if (member.isDirectory()) {
				if (!member.getName().startsWith(".")) {
					System.out.println("Recursing into " + member);
					loadAllFilesInResourceSet(member, extension);
				} else {
					System.out.println("Skipping " + member);
				}
			}
		}
	}

	protected static boolean resolveAllProxies(int resourcesProcessedBefore) {
		boolean failure = false;
		List<EObject> eobjects = new LinkedList<EObject>();
		for (Iterator<Notifier> i = rs.getAllContents(); i.hasNext();) {
			Notifier next = i.next();
			if (next instanceof EObject) {
				eobjects.add((EObject) next);
			}
		}
		int resourcesProcessed = rs.getResources().size();
		if (resourcesProcessed == resourcesProcessedBefore) {
			return true;
		}

		System.out.println("Resolving cross-references of " + eobjects.size()
				+ " EObjects.");
		int resolved = 0;
		int notResolved = 0;
		int eobjectCnt = 0;
		for (EObject next : eobjects) {
			eobjectCnt++;
			if (eobjectCnt % 1000 == 0) {
				System.out.println(eobjectCnt + "/" + eobjects.size()
						+ " done: Resolved " + resolved + " crossrefs, "
						+ notResolved + " crossrefs could not be resolved.");
			}

			InternalEObject nextElement = (InternalEObject) next;
			for (EObject crElement : nextElement.eCrossReferences()) {
				crElement = EcoreUtil.resolve(crElement, rs);
				if (crElement.eIsProxy()) {
					failure = true;
					notResolved++;
					System.out
							.println("Can not find referenced element in classpath: "
									+ ((InternalEObject) crElement).eProxyURI());
				} else {
					resolved++;
				}
			}
		}

		System.out.println(eobjectCnt + "/" + eobjects.size()
				+ " done: Resolved " + resolved + " crossrefs, " + notResolved
				+ " crossrefs could not be resolved.");
		
		//call this method again, because the resolving might have triggered loading
		//of additional resources that may also contain references that need to be resolved.
		return !failure && resolveAllProxies(resourcesProcessed);
	}

	protected static void parseResource(File file) throws IOException {
		loadResource(file.getCanonicalPath());
	}

	protected static void parseResource(ZipFile file, ZipEntry entry)
			throws IOException {
		loadResource(URI.createURI("archive:file:///"
				+ new File(".").getAbsoluteFile().toURI().getRawPath()
				+ file.getName().replaceAll("\\\\", "/") + "!/"
				+ entry.getName()));
	}

	protected static void loadResource(String filePath) throws IOException {
		loadResource(URI.createFileURI(filePath));
	}

	protected static void loadResource(URI uri) throws IOException {
		rs.getResource(uri, true);
	}
}
