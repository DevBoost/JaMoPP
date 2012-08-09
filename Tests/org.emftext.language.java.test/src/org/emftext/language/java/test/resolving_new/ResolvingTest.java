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
package org.emftext.language.java.test.resolving_new;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.emftext.language.java.commons.Commentable;
import org.emftext.language.java.modifiers.AnnotationInstanceOrModifier;
import org.emftext.language.java.test.AbstractJavaParserTestCase;

/**
 * A test for the reference resolving mechanism. Each folder
 * contained INPUT_FOLDER is treated as a Java project and
 * parsed into one ResourceSet. Afterwards all references are
 * resolved and the annotations contained as comments in the
 * input files are checked.
 */
public class ResolvingTest extends TestCase {

	private static final String INPUT_FOLDER = "src-input" + File.separator + "resolving_new";

	/**
	 * A FileFilter that does not accept SVN metadata directories.
	 */
	private static class IgnoreSVNFilter implements FileFilter {

		public boolean accept(File file) {
			if (file.getName().equals(".svn")) {
				// ignore subversion directories
				return false;
			}
			return true;
		}
	}

	public static Test suite() throws CoreException {
		TestSuite suite = new TestSuite();
		File inputFolder = new File(INPUT_FOLDER);
		// iterate over all folders in the input folder
		File[] testDirectories = inputFolder.listFiles(new IgnoreSVNFilter());
		for (File nextDirectory : testDirectories) {
			suite.addTest(new DirectoryTest(nextDirectory));
		}

		return suite;
	}

	/**
	 * A test that is instantiated for a single directory. It collects all
	 * the files in the directory to build a classpath.
	 */
	private static class DirectoryTest extends AbstractJavaParserTestCase {

		private File directory;

		public DirectoryTest(File directory) {
			super(directory.getName());
			this.directory = directory;
		}

		public void runTest() {
			testDirectory(directory);
		}

		private void testDirectory(File testDirectory) {
			String directoryName = testDirectory.getName();
			String[] parts = directoryName.split("_");
			assertEquals("The directory name should contain two parts separated by an underscore.", 2, parts.length);
			String expectedTargetString = parts[1];
			int size;
			try {
				size = Integer.parseInt(expectedTargetString);
			} catch (NumberFormatException nfe) {
				fail("Can't parse expected number of targets in directory name.");
				return;
			}
			// collect all java files and parse them into one
			// resource set
			ResourceSet set = new ResourceSetImpl();
			set.getLoadOptions().putAll(getLoadOptions());
			parseAll(testDirectory, set);

			// resolve all references
			EcoreUtil.resolveAll(set);

			// find all commented EObjects that are sources or targets
			Map<String, Object> actualTargetsMap = new HashMap<String, Object>();
			Map<String, Object> expectedTargetsMap = new HashMap<String, Object>();
			findSourcesAndTargets(set, actualTargetsMap, expectedTargetsMap);

			// check whether the actual targets match the expected targets
			for (String actualID : actualTargetsMap.keySet()) {
				Object actualTarget = actualTargetsMap.get(actualID);
				Object expectedTarget = expectedTargetsMap.get(actualID);
				assertEquals("Target objects should match (ID " + actualID + ").", expectedTarget, actualTarget);
			}
			assertEquals("Number of expected and actual targets should match.", actualTargetsMap.keySet().size(), expectedTargetsMap.keySet().size());

			// check whether the expected number of targets is present
			assertEquals("Number of targets should match the expected number.", actualTargetsMap.keySet().size(), size);
		}

		private void findSourcesAndTargets(ResourceSet set,
				Map<String, Object> actualTargetsMap,
				Map<String, Object> expectedTargetsMap) {
			List<Resource> resources = set.getResources();
			for (Resource resource : resources) {
				TreeIterator<EObject> contentIterator = resource.getAllContents();
				while (contentIterator.hasNext()) {
					EObject next = contentIterator.next();
					if (next instanceof Commentable && !(next instanceof AnnotationInstanceOrModifier)) {
						Commentable commentable = (Commentable) next;
						List<String> commentList = commentable.getComments();
						String comments = collapse(commentList);
						if (comments.length() > 0) {
							if (comments.contains("source:")) {
								String[] parts = comments.split(":");
								assertEquals("Expected three parts in comment separated by double colon (source:<id>:nameOfReference).", 3, parts.length);
								String id = parts[1].replace('\n', ' ').replace('\r', ' ').trim();
								String referenceName = parts[2].replace('\n', ' ').replace('\r', ' ').trim();
								Object target = getTarget(commentable, referenceName);
								assertNotNull(target);
								actualTargetsMap.put(id, target);
							}
							if (comments.contains("target:")) {
								String[] parts = comments.split(":");
								assertEquals("Expected two parts in comment ("+comments+") separated by double colon (target:<id>).", 2, parts.length);
								String id = parts[1].replace('\n', ' ').replace('\r', ' ').trim();
								expectedTargetsMap.put(id, commentable);
							}
						}
					}
				}
			}
		}

		private Object getTarget(Commentable commentable, String referenceName) {
			String[] references = referenceName.split("\\.");
			EObject target = commentable;
			for (String reference : references) {
				if ("eContainer".equals(reference)) {
					target = target.eContainer();
					assertNotNull("Found null container.", target);
				} else {
					EStructuralFeature feature = target.eClass().getEStructuralFeature(reference);
					assertNotNull("Can't find feature \"" + reference + "\"", feature);
					target = (EObject) target.eGet(feature);
				}
			}
			return target;
		}

		private String collapse(List<String> commentList) {
			StringBuilder sb = new StringBuilder();
			for (String comment : commentList) {
				sb.append(comment);
				//only the first comment
				break;
			}
			return sb.toString();
		}

		private void parseAll(File testDirectory, ResourceSet set) {
			File[] files = testDirectory.listFiles(new IgnoreSVNFilter());
			for (File file : files) {
				if (file.isDirectory()) {
					parseAll(file, set);
				} else if (file.getName().endsWith(".java")) {
					// parse file into resource set
					URI uri = URI.createFileURI(file.getAbsolutePath());
					Resource resource = set.createResource(uri);
					try {
						resource.load(getLoadOptions());
					} catch (IOException e) {
						fail(e.getMessage());
					}
				} else {
					fail("Found unexpected unknown file " + file.getAbsolutePath());
				}
			}
		}

		@Override
		protected String getTestInputFolder() {
			return INPUT_FOLDER;
		}

		@Override
		protected boolean isExcludedFromReprintTest(String filename) {
			return true;
		}
	}
}
