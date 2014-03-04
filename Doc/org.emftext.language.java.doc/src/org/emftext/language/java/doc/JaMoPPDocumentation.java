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
		// Change _NatSpecTemplate.java instead.
		
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
		// Paragraph PackageReference
		de.devboost.natspec.library.documentation.Paragraph paragraph_PackageReference = documentationSupport.createParagraphWithHeading(java.util.Arrays.asList(new java.lang.String[] {"PackageReference"}), subsection_Model_API);
		// Model elements of type 'PackageReference' are used as target elements for
		de.devboost.natspec.library.documentation.Line line_Model_elements_of_type__PackageReference__are_used_as_target_elements_for = documentationSupport.createPlainContents(java.util.Arrays.asList(new String[] {"Model", "elements", "of", "type", "'PackageReference'", "are", "used", "as", "target", "elements", "for"}), paragraph_PackageReference);
		// references that point to packages. For example, consider the following code:
		de.devboost.natspec.library.documentation.Line line_references_that_point_to_packages__For_example__consider_the_following_code_ = documentationSupport.createPlainContents(java.util.Arrays.asList(new String[] {"references", "that", "point", "to", "packages.", "For", "example,", "consider", "the", "following", "code:"}), paragraph_PackageReference);
		// Code new com.ClassOrPackage.ClassOrInnerClass();
		de.devboost.natspec.library.documentation.Code code_new_com_ClassOrPackage_ClassOrInnerClass___ = documentationSupport.code(new java.lang.StringBuilder().append("new").append(" ").append("com.ClassOrPackage.ClassOrInnerClass();").toString(), paragraph_PackageReference);
		// While parsing the code, it is not known whether 'ClassOrPackage' is a class or
		de.devboost.natspec.library.documentation.Line line_While_parsing_the_code__it_is_not_known_whether__ClassOrPackage__is_a_class_or = documentationSupport.createPlainContents(java.util.Arrays.asList(new String[] {"While", "parsing", "the", "code,", "it", "is", "not", "known", "whether", "'ClassOrPackage'", "is", "a", "class", "or"}), paragraph_PackageReference);
		// a package. Therefore, the parser creates an element of type 'ElementReference'
		de.devboost.natspec.library.documentation.Line line_a_package__Therefore__the_parser_creates_an_element_of_type__ElementReference_ = documentationSupport.createPlainContents(java.util.Arrays.asList(new String[] {"a", "package.", "Therefore,", "the", "parser", "creates", "an", "element", "of", "type", "'ElementReference'"}), paragraph_PackageReference);
		// for each of the parts of the fully qualified name.
		de.devboost.natspec.library.documentation.Line line_for_each_of_the_parts_of_the_fully_qualified_name_ = documentationSupport.createPlainContents(java.util.Arrays.asList(new String[] {"for", "each", "of", "the", "parts", "of", "the", "fully", "qualified", "name."}), paragraph_PackageReference);
		// Paragraph
		de.devboost.natspec.library.documentation.Paragraph paragraph_ = documentationSupport.createParagraphWithHeading(java.util.Arrays.asList(new java.lang.String[] {}), paragraph_PackageReference);
		// While resolving references, the information whether such an element is a class
		de.devboost.natspec.library.documentation.Line line_While_resolving_references__the_information_whether_such_an_element_is_a_class = documentationSupport.createPlainContents(java.util.Arrays.asList(new String[] {"While", "resolving", "references,", "the", "information", "whether", "such", "an", "element", "is", "a", "class"}), paragraph_);
		// or a package is available. If it turns out that that elements are packages
		de.devboost.natspec.library.documentation.Line line_or_a_package_is_available__If_it_turns_out_that_that_elements_are_packages = documentationSupport.createPlainContents(java.util.Arrays.asList(new String[] {"or", "a", "package", "is", "available.", "If", "it", "turns", "out", "that", "that", "elements", "are", "packages"}), paragraph_);
		// (because there is a class 'ClassOrInnerClass' within package
		de.devboost.natspec.library.documentation.Line line__because_there_is_a_class__ClassOrInnerClass__within_package = documentationSupport.createPlainContents(java.util.Arrays.asList(new String[] {"(because", "there", "is", "a", "class", "'ClassOrInnerClass'", "within", "package"}), paragraph_);
		// 'com.ClassOrPackage' on the class path) the reference 'target' of
		de.devboost.natspec.library.documentation.Line line__com_ClassOrPackage__on_the_class_path__the_reference__target__of = documentationSupport.createPlainContents(java.util.Arrays.asList(new String[] {"'com.ClassOrPackage'", "on", "the", "class", "path)", "the", "reference", "'target'", "of"}), paragraph_);
		// 'ElementReference' must point to a 'PackageReference'. In this case,
		de.devboost.natspec.library.documentation.Line line__ElementReference__must_point_to_a__PackageReference___In_this_case_ = documentationSupport.createPlainContents(java.util.Arrays.asList(new String[] {"'ElementReference'", "must", "point", "to", "a", "'PackageReference'.", "In", "this", "case,"}), paragraph_);
		// 'PackageReferences' are created on demand and they are stored in the root of the
		de.devboost.natspec.library.documentation.Line line__PackageReferences__are_created_on_demand_and_they_are_stored_in_the_root_of_the = documentationSupport.createPlainContents(java.util.Arrays.asList(new String[] {"'PackageReferences'", "are", "created", "on", "demand", "and", "they", "are", "stored", "in", "the", "root", "of", "the"}), paragraph_);
		// respective resource.
		de.devboost.natspec.library.documentation.Line line_respective_resource_ = documentationSupport.createPlainContents(java.util.Arrays.asList(new String[] {"respective", "resource."}), paragraph_);
		// Paragraph
		de.devboost.natspec.library.documentation.Paragraph paragraph_0 = documentationSupport.createParagraphWithHeading(java.util.Arrays.asList(new java.lang.String[] {}), paragraph_);
		// Note that there is no model element 'Package' because Java package do not
		de.devboost.natspec.library.documentation.Line line_Note_that_there_is_no_model_element__Package__because_Java_package_do_not = documentationSupport.createPlainContents(java.util.Arrays.asList(new String[] {"Note", "that", "there", "is", "no", "model", "element", "'Package'", "because", "Java", "package", "do", "not"}), paragraph_0);
		// correspond to resource (i.e, files).
		de.devboost.natspec.library.documentation.Line line_correspond_to_resource__i_e__files__ = documentationSupport.createPlainContents(java.util.Arrays.asList(new String[] {"correspond", "to", "resource", "(i.e,", "files)."}), paragraph_0);
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
