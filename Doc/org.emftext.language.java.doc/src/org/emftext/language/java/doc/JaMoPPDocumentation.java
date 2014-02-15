/*******************************************************************************
 * Copyright (c) 2006-2013
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

public class JaMoPPDocumentation {
	
	protected DocumentationSupport documentationSupport = new DocumentationSupport(getClass());
	
	public static void main(String[] args) throws Exception {
		JaMoPPDocumentation template = new JaMoPPDocumentation();
		template.save();
	}

	@SuppressWarnings("unused")
	public void generateDocumentation() throws Exception {
		int dummyVar;
		// generated code will be inserted here
		// The code in this method is generated from: /org.emftext.language.java.doc/src/org/emftext/language/java/doc/JaMoPPDocumentation.natspec
		// Never change this method or any contents of this file, all local changes will be overwritten.
		
		// Documentation - JaMoPP User Guide
		de.devboost.natspec.library.documentation.Documentation documentation_JaMoPP_User_Guide = documentationSupport.initDocumentation(java.util.Arrays.asList(new java.lang.String[] {"JaMoPP", "User", "Guide"}));
		// Section - Overview
		de.devboost.natspec.library.documentation.Section section_Overview = documentationSupport.addSection(java.util.Arrays.asList(new java.lang.String[] {"Overview"}), documentation_JaMoPP_User_Guide);
		// JaMoPP is a tool...
		de.devboost.natspec.library.documentation.Line line_JaMoPP_is_a_tool___ = documentationSupport.createPlainContents(java.util.Arrays.asList(new String[] {"JaMoPP", "is", "a", "tool..."}), section_Overview);
		// Subsection - Java API
		de.devboost.natspec.library.documentation.Subsection subsection_Java_API = documentationSupport.addSubsection(java.util.Arrays.asList(new java.lang.String[] {"Java", "API"}), section_Overview);
		// Subsection - Model API
		de.devboost.natspec.library.documentation.Subsection subsection_Model_API = documentationSupport.addSubsection(java.util.Arrays.asList(new java.lang.String[] {"Model", "API"}), section_Overview);
		// EMOF / Ecore
		de.devboost.natspec.library.documentation.Line line_EMOF___Ecore = documentationSupport.createPlainContents(java.util.Arrays.asList(new String[] {"EMOF", "/", "Ecore"}), subsection_Model_API);
		// Subsection - XML API
		de.devboost.natspec.library.documentation.Subsection subsection_XML_API = documentationSupport.addSubsection(java.util.Arrays.asList(new java.lang.String[] {"XML", "API"}), section_Overview);
		// Subsection - Building Java Extensions
		de.devboost.natspec.library.documentation.Subsection subsection_Building_Java_Extensions = documentationSupport.addSubsection(java.util.Arrays.asList(new java.lang.String[] {"Building", "Java", "Extensions"}), section_Overview);
		// Section - Setup
		de.devboost.natspec.library.documentation.Section section_Setup = documentationSupport.addSection(java.util.Arrays.asList(new java.lang.String[] {"Setup"}), documentation_JaMoPP_User_Guide);
		// Subsection - Setting up Stand-alone Applications
		de.devboost.natspec.library.documentation.Subsection subsection_Setting_up_Stand_alone_Applications = documentationSupport.addSubsection(java.util.Arrays.asList(new java.lang.String[] {"Setting", "up", "Stand-alone", "Applications"}), section_Setup);
		// Subsection - Loading and Saving Java Models
		de.devboost.natspec.library.documentation.Subsection subsection_Loading_and_Saving_Java_Models = documentationSupport.addSubsection(java.util.Arrays.asList(new java.lang.String[] {"Loading", "and", "Saving", "Java", "Models"}), section_Setup);
		// Subsection - Integrating into the Eclipse IDE
		de.devboost.natspec.library.documentation.Subsection subsection_Integrating_into_the_Eclipse_IDE = documentationSupport.addSubsection(java.util.Arrays.asList(new java.lang.String[] {"Integrating", "into", "the", "Eclipse", "IDE"}), section_Setup);
		// Subsection - Integrating into Continuous Integration Systems
		de.devboost.natspec.library.documentation.Subsection subsection_Integrating_into_Continuous_Integration_Systems = documentationSupport.addSubsection(java.util.Arrays.asList(new java.lang.String[] {"Integrating", "into", "Continuous", "Integration", "Systems"}), section_Setup);
		// Section - XMI
		de.devboost.natspec.library.documentation.Section section_XMI = documentationSupport.addSection(java.util.Arrays.asList(new java.lang.String[] {"XMI"}), documentation_JaMoPP_User_Guide);
		// Subsection - Translating Java Code to XMI/XML
		de.devboost.natspec.library.documentation.Subsection subsection_Translating_Java_Code_to_XMI_XML = documentationSupport.addSubsection(java.util.Arrays.asList(new java.lang.String[] {"Translating", "Java", "Code", "to", "XMI/XML"}), section_XMI);
		// Section - Base API
		de.devboost.natspec.library.documentation.Section section_Base_API = documentationSupport.addSection(java.util.Arrays.asList(new java.lang.String[] {"Base", "API"}), documentation_JaMoPP_User_Guide);
		// Section - Utility API
		de.devboost.natspec.library.documentation.Section section_Utility_API = documentationSupport.addSection(java.util.Arrays.asList(new java.lang.String[] {"Utility", "API"}), documentation_JaMoPP_User_Guide);
		// Section - Extended Navigation API
		de.devboost.natspec.library.documentation.Section section_Extended_Navigation_API = documentationSupport.addSection(java.util.Arrays.asList(new java.lang.String[] {"Extended", "Navigation", "API"}), documentation_JaMoPP_User_Guide);
		// Section - Extended Modification API
		de.devboost.natspec.library.documentation.Section section_Extended_Modification_API = documentationSupport.addSection(java.util.Arrays.asList(new java.lang.String[] {"Extended", "Modification", "API"}), documentation_JaMoPP_User_Guide);
		
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
