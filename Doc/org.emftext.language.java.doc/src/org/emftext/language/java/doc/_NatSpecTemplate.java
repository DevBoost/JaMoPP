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
package org.emftext.language.java.doc;

import java.io.File;
import java.io.FileWriter;

import de.devboost.natspec.library.documentation.Documentation;
import de.devboost.natspec.library.documentation.DocumentationGenerator;
import de.devboost.natspec.library.documentation.DocumentationSupport;

public class _NatSpecTemplate {
	
	protected DocumentationSupport documentationSupport = new DocumentationSupport();
	
	public static void main(String[] args) throws Exception {
		_NatSpecTemplate template = new _NatSpecTemplate();
		template.save();
	}

	@SuppressWarnings("unused")
	public void generateDocumentation() throws Exception {
		int dummyVar;
		// generated code will be inserted here
		/* @MethodBody */
	}

	private void save() throws Exception {
		generateDocumentation();
		Documentation documentation = documentationSupport.getDocumentation();
		DocumentationGenerator generator = new DocumentationGenerator();
		String html = generator.getDocumentationAsString(documentation, "JaMoPPGuide.css");
		FileWriter writer = new FileWriter(new File("html" + File.separator + "JaMoPPGuide.html"));
		writer.write(html);
		writer.close();
	}
}
