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
import java.util.Collections;
import java.util.Map;

import org.eclipse.emf.common.util.URI;
import org.emftext.language.java.resource.java.IJavaOptions;
import org.emftext.language.java.resource.java.mopp.JavaResource;
import org.junit.Test;

/**
 * A separate test case for the input files that contain Unicode escape
 * sequences.
 */
public class UnicodeTest extends AbstractJavaParserTestCase {

	@Test
	public void testUnicodeInput() {
		try {
			assertParsesToClass("ControlZ");
			assertParsesToClass("Unicode");
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}
	
	@Test
	public void testUnicodeConverterDeactivated() {
		try {
			Map<String, Object> loadOptions = Collections.singletonMap(
					IJavaOptions.INPUT_STREAM_PREPROCESSOR_PROVIDER, null);
			
			assertParsesWithErrorsClass("ControlZ", loadOptions);
			assertParsesWithErrorsClass("Unicode", loadOptions);
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

	protected void assertParsesWithErrorsClass(String typename, 
			Map<?, ?> loadOptions) throws Exception {
		String filename = File.separator + typename + ".java";
		File inputFolder = new File("./" + getTestInputFolder());
		File file = new File(inputFolder, filename);
		assertTrue("File " + file + " should exist.", file.exists());
		URI fileURI = URI.createFileURI(file.getAbsolutePath());
		JavaResource resource = (JavaResource) getResourceSet().createResource(fileURI);
		resource.load(loadOptions);
		
		assertTrue(!resource.getErrors().isEmpty());
		assertTrue(resource.getErrors().get(0).getMessage().startsWith("Syntax error on"));
	}
	
	@Override
	protected boolean isExcludedFromReprintTest(String filename) {
		return true;
	}

	@Override
	protected String getTestInputFolder() {
		return "src-input" + File.separator + "unicode";
	}

}
