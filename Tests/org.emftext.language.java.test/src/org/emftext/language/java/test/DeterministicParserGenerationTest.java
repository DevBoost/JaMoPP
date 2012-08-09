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

import static org.emftext.test.ConcreteSyntaxTestHelper.generateANTLRGrammarToTempFile;
import static org.emftext.test.ConcreteSyntaxTestHelper.registerResourceFactories;

import java.io.File;
import java.io.IOException;

import junit.framework.TestCase;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.emf.common.util.URI;
import org.emftext.language.java.JavaFactory;
import org.emftext.test.GrammarGenerationTestUtil;
import org.emftext.test.PluginTestHelper;
import org.emftext.test.TestProblemCollector;
import org.junit.Before;
import org.junit.Test;

/**
 * This test checks whether regenerating the parser with EMFText
 * results in the same ANTLR grammar.
 *
 * This test runs as JUnit test.
 */
public class DeterministicParserGenerationTest extends TestCase {

	private final static GrammarGenerationTestUtil genUtil = new GrammarGenerationTestUtil();

	@Before
	public void setUp() {
		registerResourceFactories();
	}

	@Test
	public void testDeterministicParserGeneration() throws IOException, CoreException, InterruptedException {
		String javaPluginRootPath = new PluginTestHelper().getPluginRootPath(JavaFactory.class);
		String path = javaPluginRootPath + File.separator + "metamodel" + File.separator + "java.cs";
		String absolutePath = new File(path).getAbsolutePath();
		System.out.println(absolutePath);
		URI fileURI = URI.createFileURI(absolutePath);

		File result1 = generateANTLRGrammarToTempFile(fileURI, new TestProblemCollector());
		String content1 = genUtil.getContent(result1);
		File result2 = generateANTLRGrammarToTempFile(fileURI, new TestProblemCollector());
		String content2 = genUtil.getContent(result2);
		assertEquals(content1, content2);

		String grammar1 = genUtil.getGrammar(fileURI);
		String grammar2 = genUtil.getGrammar(fileURI);
		assertEquals(grammar1, grammar2);
	}
}
