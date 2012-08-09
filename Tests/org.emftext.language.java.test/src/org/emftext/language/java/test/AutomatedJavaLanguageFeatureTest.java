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
package org.emftext.language.java.test;

import java.io.File;
import java.util.List;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import org.eclipse.emf.ecore.resource.ResourceSet;

/**
 * A test that read all Java files from the input directory and parses and
 * prints them.
 */
public class AutomatedJavaLanguageFeatureTest extends AbstractJavaParserTestCase {

	protected static final String TEST_INPUT_FOLDER_NAME = "src-input";
	protected static final String TEST_OUTPUT_FOLDER_NAME = "output";

	public static Test suite() throws Exception {
		final AutomatedJavaLanguageFeatureTest test = new AutomatedJavaLanguageFeatureTest();

		TestSuite suite = new TestSuite(
				"Suite testing all files in the input directory automatically");
		File inputFolder = new File("./" + TEST_INPUT_FOLDER_NAME);
		List<File> allTestFiles = collectAllFilesRecursive(inputFolder, "java");
		File last = null;
		for (final File file : allTestFiles) {
			test.addFileToClasspath(file, test.getResourceSet());
			if (file.getName().equals("TypeReferencing.java")) {
				last = file; 
				continue;
			}
		}
		if (last != null) {
			//put the "TypeReferencing.java" file last, because it contains inner
			//types referenced by other files. If these types are not registered
			//before proxy resolving, the test will fail.
			allTestFiles.remove(last);
			allTestFiles.add(last);
		}
		
		//first do all parse tests (will register inner types in classpath)
		for (final File file : allTestFiles) {
			addParseTest(test, suite, file);		
		}
		
		//second do resolving and printing test
		for (final File file : allTestFiles) {
			addParseAndReprintTest(test, suite, file);			
		}
		
		return suite;
	}

	private static void addParseTest(final AutomatedJavaLanguageFeatureTest test,
			TestSuite suite, final File file) {
		suite.addTest(new TestCase("Parse " + file.getName()) {
			public void runTest() {
				try {
					test.parseResource(file.getPath(), "./");
				}
				catch (Exception e) {
					fail(e.getClass() +  ": " + e.getMessage());
					e.printStackTrace();
				}
			}
		});
	}

	private static void addParseAndReprintTest(
			final AutomatedJavaLanguageFeatureTest test, TestSuite suite, final File file) {
		suite.addTest(new TestCase("Parse and Reprint " + file.getName()) {
			public void runTest() {
				try {
					test.parseAndReprint(file, TEST_INPUT_FOLDER_NAME, TEST_OUTPUT_FOLDER_NAME);
				}
				catch (Exception e) {
					fail(e.getClass() +  ": " + e.getMessage());
					e.printStackTrace();
				}

			}
		});
	}
	
	private ResourceSet sharedTestResourceSet = null;
	
	@Override
	protected ResourceSet getResourceSet() {
		if (sharedTestResourceSet == null) {
			sharedTestResourceSet = super.getResourceSet();
		}
		return sharedTestResourceSet;
	}

	@Override
	protected boolean isExcludedFromReprintTest(String filename) {
		return false;
	}

	@Override
	protected String getTestInputFolder() {
		return TEST_INPUT_FOLDER_NAME;
	}
}
