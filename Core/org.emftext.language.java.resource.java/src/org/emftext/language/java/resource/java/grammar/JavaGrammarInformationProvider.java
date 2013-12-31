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
 *   Benjamin Klatt - Performance improvement
 ******************************************************************************/
package org.emftext.language.java.resource.java.grammar;

import java.lang.reflect.Field;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Set;

import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.EcoreFactory;

public class JavaGrammarInformationProvider {

	public final static EStructuralFeature ANONYMOUS_FEATURE = EcoreFactory.eINSTANCE.createEAttribute();
	static {
		ANONYMOUS_FEATURE.setName("_");
	}

	public final static JavaGrammarInformationProvider INSTANCE = new JavaGrammarInformationProvider();

	private java.util.Set<String> keywords;

	public final static JavaContainment JAVA_0_0_0_0_0_0_0 = new JavaContainment(org.emftext.language.java.containers.ContainersPackage.eINSTANCE.getEmptyModel().getEStructuralFeature(org.emftext.language.java.containers.ContainersPackage.EMPTY_MODEL__IMPORTS), JavaCardinality.ONE, new org.eclipse.emf.ecore.EClass[] {org.emftext.language.java.imports.ImportsPackage.eINSTANCE.getImport(), }, 0);
	public final static JavaKeyword JAVA_0_0_0_0_0_0_1 = new JavaKeyword(";", JavaCardinality.ONE);
	public final static JavaLineBreak JAVA_0_0_0_0_0_0_2 = new JavaLineBreak(JavaCardinality.ONE, 0);
	public final static JavaSequence JAVA_0_0_0_0_0_0 = new JavaSequence(JavaCardinality.ONE, JAVA_0_0_0_0_0_0_0, JAVA_0_0_0_0_0_0_1, JAVA_0_0_0_0_0_0_2);
	public final static JavaChoice JAVA_0_0_0_0_0 = new JavaChoice(JavaCardinality.ONE, JAVA_0_0_0_0_0_0);
	public final static JavaCompound JAVA_0_0_0_0 = new JavaCompound(JAVA_0_0_0_0_0, JavaCardinality.STAR);
	public final static JavaKeyword JAVA_0_0_0_1_0_0_0 = new JavaKeyword(";", JavaCardinality.ONE);
	public final static JavaSequence JAVA_0_0_0_1_0_0 = new JavaSequence(JavaCardinality.ONE, JAVA_0_0_0_1_0_0_0);
	public final static JavaChoice JAVA_0_0_0_1_0 = new JavaChoice(JavaCardinality.ONE, JAVA_0_0_0_1_0_0);
	public final static JavaCompound JAVA_0_0_0_1 = new JavaCompound(JAVA_0_0_0_1_0, JavaCardinality.STAR);
	public final static JavaSequence JAVA_0_0_0 = new JavaSequence(JavaCardinality.ONE, JAVA_0_0_0_0, JAVA_0_0_0_1);
	public final static JavaChoice JAVA_0_0 = new JavaChoice(JavaCardinality.ONE, JAVA_0_0_0);
	public final static JavaRule JAVA_0 = new JavaRule(org.emftext.language.java.containers.ContainersPackage.eINSTANCE.getEmptyModel(), JAVA_0_0, JavaCardinality.ONE);
	public final static JavaContainment JAVA_1_0_0_0 = new JavaContainment(org.emftext.language.java.containers.ContainersPackage.eINSTANCE.getPackage().getEStructuralFeature(org.emftext.language.java.containers.ContainersPackage.PACKAGE__ANNOTATIONS), JavaCardinality.STAR, new org.eclipse.emf.ecore.EClass[] {org.emftext.language.java.annotations.AnnotationsPackage.eINSTANCE.getAnnotationInstance(), }, 0);
	public final static JavaKeyword JAVA_1_0_0_1 = new JavaKeyword("package", JavaCardinality.ONE);
	public final static JavaPlaceholder JAVA_1_0_0_2_0_0_0 = new JavaPlaceholder(org.emftext.language.java.containers.ContainersPackage.eINSTANCE.getPackage().getEStructuralFeature(org.emftext.language.java.containers.ContainersPackage.PACKAGE__NAMESPACES), "IDENTIFIER", JavaCardinality.ONE, 0);
	public final static JavaKeyword JAVA_1_0_0_2_0_0_1 = new JavaKeyword(".", JavaCardinality.ONE);
	public final static JavaSequence JAVA_1_0_0_2_0_0 = new JavaSequence(JavaCardinality.ONE, JAVA_1_0_0_2_0_0_0, JAVA_1_0_0_2_0_0_1);
	public final static JavaChoice JAVA_1_0_0_2_0 = new JavaChoice(JavaCardinality.ONE, JAVA_1_0_0_2_0_0);
	public final static JavaCompound JAVA_1_0_0_2 = new JavaCompound(JAVA_1_0_0_2_0, JavaCardinality.STAR);
	public final static JavaPlaceholder JAVA_1_0_0_3 = new JavaPlaceholder(org.emftext.language.java.containers.ContainersPackage.eINSTANCE.getPackage().getEStructuralFeature(org.emftext.language.java.containers.ContainersPackage.PACKAGE__NAME), "IDENTIFIER", JavaCardinality.ONE, 0);
	public final static JavaKeyword JAVA_1_0_0_4 = new JavaKeyword(";", JavaCardinality.ONE);
	public final static JavaKeyword JAVA_1_0_0_5_0_0_0 = new JavaKeyword(";", JavaCardinality.ONE);
	public final static JavaSequence JAVA_1_0_0_5_0_0 = new JavaSequence(JavaCardinality.ONE, JAVA_1_0_0_5_0_0_0);
	public final static JavaChoice JAVA_1_0_0_5_0 = new JavaChoice(JavaCardinality.ONE, JAVA_1_0_0_5_0_0);
	public final static JavaCompound JAVA_1_0_0_5 = new JavaCompound(JAVA_1_0_0_5_0, JavaCardinality.QUESTIONMARK);
	public final static JavaLineBreak JAVA_1_0_0_6 = new JavaLineBreak(JavaCardinality.ONE, 0);
	public final static JavaLineBreak JAVA_1_0_0_7 = new JavaLineBreak(JavaCardinality.ONE, 0);
	public final static JavaContainment JAVA_1_0_0_8_0_0_0 = new JavaContainment(org.emftext.language.java.containers.ContainersPackage.eINSTANCE.getPackage().getEStructuralFeature(org.emftext.language.java.containers.ContainersPackage.PACKAGE__IMPORTS), JavaCardinality.ONE, new org.eclipse.emf.ecore.EClass[] {org.emftext.language.java.imports.ImportsPackage.eINSTANCE.getImport(), }, 0);
	public final static JavaLineBreak JAVA_1_0_0_8_0_0_1 = new JavaLineBreak(JavaCardinality.ONE, 0);
	public final static JavaSequence JAVA_1_0_0_8_0_0 = new JavaSequence(JavaCardinality.ONE, JAVA_1_0_0_8_0_0_0, JAVA_1_0_0_8_0_0_1);
	public final static JavaChoice JAVA_1_0_0_8_0 = new JavaChoice(JavaCardinality.ONE, JAVA_1_0_0_8_0_0);
	public final static JavaCompound JAVA_1_0_0_8 = new JavaCompound(JAVA_1_0_0_8_0, JavaCardinality.STAR);
	public final static JavaKeyword JAVA_1_0_0_9_0_0_0 = new JavaKeyword(";", JavaCardinality.ONE);
	public final static JavaSequence JAVA_1_0_0_9_0_0 = new JavaSequence(JavaCardinality.ONE, JAVA_1_0_0_9_0_0_0);
	public final static JavaChoice JAVA_1_0_0_9_0 = new JavaChoice(JavaCardinality.ONE, JAVA_1_0_0_9_0_0);
	public final static JavaCompound JAVA_1_0_0_9 = new JavaCompound(JAVA_1_0_0_9_0, JavaCardinality.STAR);
	public final static JavaSequence JAVA_1_0_0 = new JavaSequence(JavaCardinality.ONE, JAVA_1_0_0_0, JAVA_1_0_0_1, JAVA_1_0_0_2, JAVA_1_0_0_3, JAVA_1_0_0_4, JAVA_1_0_0_5, JAVA_1_0_0_6, JAVA_1_0_0_7, JAVA_1_0_0_8, JAVA_1_0_0_9);
	public final static JavaChoice JAVA_1_0 = new JavaChoice(JavaCardinality.ONE, JAVA_1_0_0);
	public final static JavaRule JAVA_1 = new JavaRule(org.emftext.language.java.containers.ContainersPackage.eINSTANCE.getPackage(), JAVA_1_0, JavaCardinality.ONE);
	public final static JavaKeyword JAVA_2_0_0_0_0_0_0 = new JavaKeyword("package", JavaCardinality.ONE);
	public final static JavaPlaceholder JAVA_2_0_0_0_0_0_1 = new JavaPlaceholder(org.emftext.language.java.containers.ContainersPackage.eINSTANCE.getCompilationUnit().getEStructuralFeature(org.emftext.language.java.containers.ContainersPackage.COMPILATION_UNIT__NAMESPACES), "IDENTIFIER", JavaCardinality.ONE, 0);
	public final static JavaKeyword JAVA_2_0_0_0_0_0_2_0_0_0 = new JavaKeyword(".", JavaCardinality.ONE);
	public final static JavaPlaceholder JAVA_2_0_0_0_0_0_2_0_0_1 = new JavaPlaceholder(org.emftext.language.java.containers.ContainersPackage.eINSTANCE.getCompilationUnit().getEStructuralFeature(org.emftext.language.java.containers.ContainersPackage.COMPILATION_UNIT__NAMESPACES), "IDENTIFIER", JavaCardinality.ONE, 0);
	public final static JavaSequence JAVA_2_0_0_0_0_0_2_0_0 = new JavaSequence(JavaCardinality.ONE, JAVA_2_0_0_0_0_0_2_0_0_0, JAVA_2_0_0_0_0_0_2_0_0_1);
	public final static JavaChoice JAVA_2_0_0_0_0_0_2_0 = new JavaChoice(JavaCardinality.ONE, JAVA_2_0_0_0_0_0_2_0_0);
	public final static JavaCompound JAVA_2_0_0_0_0_0_2 = new JavaCompound(JAVA_2_0_0_0_0_0_2_0, JavaCardinality.STAR);
	public final static JavaKeyword JAVA_2_0_0_0_0_0_3 = new JavaKeyword(";", JavaCardinality.ONE);
	public final static JavaSequence JAVA_2_0_0_0_0_0 = new JavaSequence(JavaCardinality.ONE, JAVA_2_0_0_0_0_0_0, JAVA_2_0_0_0_0_0_1, JAVA_2_0_0_0_0_0_2, JAVA_2_0_0_0_0_0_3);
	public final static JavaChoice JAVA_2_0_0_0_0 = new JavaChoice(JavaCardinality.ONE, JAVA_2_0_0_0_0_0);
	public final static JavaCompound JAVA_2_0_0_0 = new JavaCompound(JAVA_2_0_0_0_0, JavaCardinality.QUESTIONMARK);
	public final static JavaLineBreak JAVA_2_0_0_1 = new JavaLineBreak(JavaCardinality.ONE, 0);
	public final static JavaLineBreak JAVA_2_0_0_2 = new JavaLineBreak(JavaCardinality.ONE, 0);
	public final static JavaContainment JAVA_2_0_0_3_0_0_0 = new JavaContainment(org.emftext.language.java.containers.ContainersPackage.eINSTANCE.getCompilationUnit().getEStructuralFeature(org.emftext.language.java.containers.ContainersPackage.COMPILATION_UNIT__IMPORTS), JavaCardinality.ONE, new org.eclipse.emf.ecore.EClass[] {org.emftext.language.java.imports.ImportsPackage.eINSTANCE.getImport(), }, 0);
	public final static JavaLineBreak JAVA_2_0_0_3_0_0_1 = new JavaLineBreak(JavaCardinality.ONE, 0);
	public final static JavaSequence JAVA_2_0_0_3_0_0 = new JavaSequence(JavaCardinality.ONE, JAVA_2_0_0_3_0_0_0, JAVA_2_0_0_3_0_0_1);
	public final static JavaChoice JAVA_2_0_0_3_0 = new JavaChoice(JavaCardinality.ONE, JAVA_2_0_0_3_0_0);
	public final static JavaCompound JAVA_2_0_0_3 = new JavaCompound(JAVA_2_0_0_3_0, JavaCardinality.STAR);
	public final static JavaKeyword JAVA_2_0_0_4_0_0_0 = new JavaKeyword(";", JavaCardinality.ONE);
	public final static JavaLineBreak JAVA_2_0_0_4_0_0_1 = new JavaLineBreak(JavaCardinality.ONE, 0);
	public final static JavaSequence JAVA_2_0_0_4_0_0 = new JavaSequence(JavaCardinality.ONE, JAVA_2_0_0_4_0_0_0, JAVA_2_0_0_4_0_0_1);
	public final static JavaChoice JAVA_2_0_0_4_0 = new JavaChoice(JavaCardinality.ONE, JAVA_2_0_0_4_0_0);
	public final static JavaCompound JAVA_2_0_0_4 = new JavaCompound(JAVA_2_0_0_4_0, JavaCardinality.STAR);
	public final static JavaLineBreak JAVA_2_0_0_5 = new JavaLineBreak(JavaCardinality.ONE, 0);
	public final static JavaContainment JAVA_2_0_0_6_0_0_0 = new JavaContainment(org.emftext.language.java.containers.ContainersPackage.eINSTANCE.getCompilationUnit().getEStructuralFeature(org.emftext.language.java.containers.ContainersPackage.COMPILATION_UNIT__CLASSIFIERS), JavaCardinality.ONE, new org.eclipse.emf.ecore.EClass[] {org.emftext.language.java.classifiers.ClassifiersPackage.eINSTANCE.getConcreteClassifier(), }, 0);
	public final static JavaKeyword JAVA_2_0_0_6_0_0_1_0_0_0 = new JavaKeyword(";", JavaCardinality.ONE);
	public final static JavaSequence JAVA_2_0_0_6_0_0_1_0_0 = new JavaSequence(JavaCardinality.ONE, JAVA_2_0_0_6_0_0_1_0_0_0);
	public final static JavaChoice JAVA_2_0_0_6_0_0_1_0 = new JavaChoice(JavaCardinality.ONE, JAVA_2_0_0_6_0_0_1_0_0);
	public final static JavaCompound JAVA_2_0_0_6_0_0_1 = new JavaCompound(JAVA_2_0_0_6_0_0_1_0, JavaCardinality.STAR);
	public final static JavaLineBreak JAVA_2_0_0_6_0_0_2 = new JavaLineBreak(JavaCardinality.ONE, 0);
	public final static JavaLineBreak JAVA_2_0_0_6_0_0_3 = new JavaLineBreak(JavaCardinality.ONE, 0);
	public final static JavaSequence JAVA_2_0_0_6_0_0 = new JavaSequence(JavaCardinality.ONE, JAVA_2_0_0_6_0_0_0, JAVA_2_0_0_6_0_0_1, JAVA_2_0_0_6_0_0_2, JAVA_2_0_0_6_0_0_3);
	public final static JavaChoice JAVA_2_0_0_6_0 = new JavaChoice(JavaCardinality.ONE, JAVA_2_0_0_6_0_0);
	public final static JavaCompound JAVA_2_0_0_6 = new JavaCompound(JAVA_2_0_0_6_0, JavaCardinality.PLUS);
	public final static JavaKeyword JAVA_2_0_0_7_0_0_0 = new JavaKeyword("\u001a", JavaCardinality.ONE);
	public final static JavaSequence JAVA_2_0_0_7_0_0 = new JavaSequence(JavaCardinality.ONE, JAVA_2_0_0_7_0_0_0);
	public final static JavaChoice JAVA_2_0_0_7_0 = new JavaChoice(JavaCardinality.ONE, JAVA_2_0_0_7_0_0);
	public final static JavaCompound JAVA_2_0_0_7 = new JavaCompound(JAVA_2_0_0_7_0, JavaCardinality.QUESTIONMARK);
	public final static JavaSequence JAVA_2_0_0 = new JavaSequence(JavaCardinality.ONE, JAVA_2_0_0_0, JAVA_2_0_0_1, JAVA_2_0_0_2, JAVA_2_0_0_3, JAVA_2_0_0_4, JAVA_2_0_0_5, JAVA_2_0_0_6, JAVA_2_0_0_7);
	public final static JavaChoice JAVA_2_0 = new JavaChoice(JavaCardinality.ONE, JAVA_2_0_0);
	public final static JavaRule JAVA_2 = new JavaRule(org.emftext.language.java.containers.ContainersPackage.eINSTANCE.getCompilationUnit(), JAVA_2_0, JavaCardinality.ONE);
	public final static JavaKeyword JAVA_3_0_0_0 = new JavaKeyword("import", JavaCardinality.ONE);
	public final static JavaPlaceholder JAVA_3_0_0_1_0_0_0 = new JavaPlaceholder(org.emftext.language.java.imports.ImportsPackage.eINSTANCE.getClassifierImport().getEStructuralFeature(org.emftext.language.java.imports.ImportsPackage.CLASSIFIER_IMPORT__NAMESPACES), "IDENTIFIER", JavaCardinality.ONE, 0);
	public final static JavaKeyword JAVA_3_0_0_1_0_0_1 = new JavaKeyword(".", JavaCardinality.ONE);
	public final static JavaSequence JAVA_3_0_0_1_0_0 = new JavaSequence(JavaCardinality.ONE, JAVA_3_0_0_1_0_0_0, JAVA_3_0_0_1_0_0_1);
	public final static JavaChoice JAVA_3_0_0_1_0 = new JavaChoice(JavaCardinality.ONE, JAVA_3_0_0_1_0_0);
	public final static JavaCompound JAVA_3_0_0_1 = new JavaCompound(JAVA_3_0_0_1_0, JavaCardinality.STAR);
	public final static JavaPlaceholder JAVA_3_0_0_2 = new JavaPlaceholder(org.emftext.language.java.imports.ImportsPackage.eINSTANCE.getClassifierImport().getEStructuralFeature(org.emftext.language.java.imports.ImportsPackage.CLASSIFIER_IMPORT__CLASSIFIER), "IDENTIFIER", JavaCardinality.ONE, 0);
	public final static JavaKeyword JAVA_3_0_0_3 = new JavaKeyword(";", JavaCardinality.ONE);
	public final static JavaSequence JAVA_3_0_0 = new JavaSequence(JavaCardinality.ONE, JAVA_3_0_0_0, JAVA_3_0_0_1, JAVA_3_0_0_2, JAVA_3_0_0_3);
	public final static JavaChoice JAVA_3_0 = new JavaChoice(JavaCardinality.ONE, JAVA_3_0_0);
	public final static JavaRule JAVA_3 = new JavaRule(org.emftext.language.java.imports.ImportsPackage.eINSTANCE.getClassifierImport(), JAVA_3_0, JavaCardinality.ONE);
	public final static JavaKeyword JAVA_4_0_0_0 = new JavaKeyword("import", JavaCardinality.ONE);
	public final static JavaPlaceholder JAVA_4_0_0_1_0_0_0 = new JavaPlaceholder(org.emftext.language.java.imports.ImportsPackage.eINSTANCE.getPackageImport().getEStructuralFeature(org.emftext.language.java.imports.ImportsPackage.PACKAGE_IMPORT__NAMESPACES), "IDENTIFIER", JavaCardinality.ONE, 0);
	public final static JavaKeyword JAVA_4_0_0_1_0_0_1 = new JavaKeyword(".", JavaCardinality.ONE);
	public final static JavaSequence JAVA_4_0_0_1_0_0 = new JavaSequence(JavaCardinality.ONE, JAVA_4_0_0_1_0_0_0, JAVA_4_0_0_1_0_0_1);
	public final static JavaChoice JAVA_4_0_0_1_0 = new JavaChoice(JavaCardinality.ONE, JAVA_4_0_0_1_0_0);
	public final static JavaCompound JAVA_4_0_0_1 = new JavaCompound(JAVA_4_0_0_1_0, JavaCardinality.PLUS);
	public final static JavaKeyword JAVA_4_0_0_2 = new JavaKeyword("*", JavaCardinality.ONE);
	public final static JavaKeyword JAVA_4_0_0_3 = new JavaKeyword(";", JavaCardinality.ONE);
	public final static JavaSequence JAVA_4_0_0 = new JavaSequence(JavaCardinality.ONE, JAVA_4_0_0_0, JAVA_4_0_0_1, JAVA_4_0_0_2, JAVA_4_0_0_3);
	public final static JavaChoice JAVA_4_0 = new JavaChoice(JavaCardinality.ONE, JAVA_4_0_0);
	public final static JavaRule JAVA_4 = new JavaRule(org.emftext.language.java.imports.ImportsPackage.eINSTANCE.getPackageImport(), JAVA_4_0, JavaCardinality.ONE);
	public final static JavaKeyword JAVA_5_0_0_0 = new JavaKeyword("import", JavaCardinality.ONE);
	public final static JavaContainment JAVA_5_0_0_1 = new JavaContainment(org.emftext.language.java.imports.ImportsPackage.eINSTANCE.getStaticMemberImport().getEStructuralFeature(org.emftext.language.java.imports.ImportsPackage.STATIC_MEMBER_IMPORT__STATIC), JavaCardinality.ONE, new org.eclipse.emf.ecore.EClass[] {org.emftext.language.java.modifiers.ModifiersPackage.eINSTANCE.getStatic(), }, 0);
	public final static JavaPlaceholder JAVA_5_0_0_2_0_0_0 = new JavaPlaceholder(org.emftext.language.java.imports.ImportsPackage.eINSTANCE.getStaticMemberImport().getEStructuralFeature(org.emftext.language.java.imports.ImportsPackage.STATIC_MEMBER_IMPORT__NAMESPACES), "IDENTIFIER", JavaCardinality.ONE, 0);
	public final static JavaKeyword JAVA_5_0_0_2_0_0_1 = new JavaKeyword(".", JavaCardinality.ONE);
	public final static JavaSequence JAVA_5_0_0_2_0_0 = new JavaSequence(JavaCardinality.ONE, JAVA_5_0_0_2_0_0_0, JAVA_5_0_0_2_0_0_1);
	public final static JavaChoice JAVA_5_0_0_2_0 = new JavaChoice(JavaCardinality.ONE, JAVA_5_0_0_2_0_0);
	public final static JavaCompound JAVA_5_0_0_2 = new JavaCompound(JAVA_5_0_0_2_0, JavaCardinality.STAR);
	public final static JavaPlaceholder JAVA_5_0_0_3 = new JavaPlaceholder(org.emftext.language.java.imports.ImportsPackage.eINSTANCE.getStaticMemberImport().getEStructuralFeature(org.emftext.language.java.imports.ImportsPackage.STATIC_MEMBER_IMPORT__STATIC_MEMBERS), "IDENTIFIER", JavaCardinality.ONE, 0);
	public final static JavaKeyword JAVA_5_0_0_4 = new JavaKeyword(";", JavaCardinality.ONE);
	public final static JavaSequence JAVA_5_0_0 = new JavaSequence(JavaCardinality.ONE, JAVA_5_0_0_0, JAVA_5_0_0_1, JAVA_5_0_0_2, JAVA_5_0_0_3, JAVA_5_0_0_4);
	public final static JavaChoice JAVA_5_0 = new JavaChoice(JavaCardinality.ONE, JAVA_5_0_0);
	public final static JavaRule JAVA_5 = new JavaRule(org.emftext.language.java.imports.ImportsPackage.eINSTANCE.getStaticMemberImport(), JAVA_5_0, JavaCardinality.ONE);
	public final static JavaKeyword JAVA_6_0_0_0 = new JavaKeyword("import", JavaCardinality.ONE);
	public final static JavaContainment JAVA_6_0_0_1 = new JavaContainment(org.emftext.language.java.imports.ImportsPackage.eINSTANCE.getStaticClassifierImport().getEStructuralFeature(org.emftext.language.java.imports.ImportsPackage.STATIC_CLASSIFIER_IMPORT__STATIC), JavaCardinality.ONE, new org.eclipse.emf.ecore.EClass[] {org.emftext.language.java.modifiers.ModifiersPackage.eINSTANCE.getStatic(), }, 0);
	public final static JavaPlaceholder JAVA_6_0_0_2_0_0_0 = new JavaPlaceholder(org.emftext.language.java.imports.ImportsPackage.eINSTANCE.getStaticClassifierImport().getEStructuralFeature(org.emftext.language.java.imports.ImportsPackage.STATIC_CLASSIFIER_IMPORT__NAMESPACES), "IDENTIFIER", JavaCardinality.ONE, 0);
	public final static JavaKeyword JAVA_6_0_0_2_0_0_1 = new JavaKeyword(".", JavaCardinality.ONE);
	public final static JavaSequence JAVA_6_0_0_2_0_0 = new JavaSequence(JavaCardinality.ONE, JAVA_6_0_0_2_0_0_0, JAVA_6_0_0_2_0_0_1);
	public final static JavaChoice JAVA_6_0_0_2_0 = new JavaChoice(JavaCardinality.ONE, JAVA_6_0_0_2_0_0);
	public final static JavaCompound JAVA_6_0_0_2 = new JavaCompound(JAVA_6_0_0_2_0, JavaCardinality.PLUS);
	public final static JavaKeyword JAVA_6_0_0_3 = new JavaKeyword("*", JavaCardinality.ONE);
	public final static JavaKeyword JAVA_6_0_0_4 = new JavaKeyword(";", JavaCardinality.ONE);
	public final static JavaSequence JAVA_6_0_0 = new JavaSequence(JavaCardinality.ONE, JAVA_6_0_0_0, JAVA_6_0_0_1, JAVA_6_0_0_2, JAVA_6_0_0_3, JAVA_6_0_0_4);
	public final static JavaChoice JAVA_6_0 = new JavaChoice(JavaCardinality.ONE, JAVA_6_0_0);
	public final static JavaRule JAVA_6 = new JavaRule(org.emftext.language.java.imports.ImportsPackage.eINSTANCE.getStaticClassifierImport(), JAVA_6_0, JavaCardinality.ONE);
	public final static JavaContainment JAVA_7_0_0_0 = new JavaContainment(org.emftext.language.java.classifiers.ClassifiersPackage.eINSTANCE.getClass_().getEStructuralFeature(org.emftext.language.java.classifiers.ClassifiersPackage.CLASS__ANNOTATIONS_AND_MODIFIERS), JavaCardinality.STAR, new org.eclipse.emf.ecore.EClass[] {org.emftext.language.java.modifiers.ModifiersPackage.eINSTANCE.getAnnotationInstanceOrModifier(), }, 0);
	public final static JavaKeyword JAVA_7_0_0_1 = new JavaKeyword("class", JavaCardinality.ONE);
	public final static JavaPlaceholder JAVA_7_0_0_2 = new JavaPlaceholder(org.emftext.language.java.classifiers.ClassifiersPackage.eINSTANCE.getClass_().getEStructuralFeature(org.emftext.language.java.classifiers.ClassifiersPackage.CLASS__NAME), "IDENTIFIER", JavaCardinality.ONE, 0);
	public final static JavaKeyword JAVA_7_0_0_3_0_0_0 = new JavaKeyword("<", JavaCardinality.ONE);
	public final static JavaContainment JAVA_7_0_0_3_0_0_1 = new JavaContainment(org.emftext.language.java.classifiers.ClassifiersPackage.eINSTANCE.getClass_().getEStructuralFeature(org.emftext.language.java.classifiers.ClassifiersPackage.CLASS__TYPE_PARAMETERS), JavaCardinality.ONE, new org.eclipse.emf.ecore.EClass[] {org.emftext.language.java.generics.GenericsPackage.eINSTANCE.getTypeParameter(), }, 0);
	public final static JavaKeyword JAVA_7_0_0_3_0_0_2_0_0_0 = new JavaKeyword(",", JavaCardinality.ONE);
	public final static JavaContainment JAVA_7_0_0_3_0_0_2_0_0_1 = new JavaContainment(org.emftext.language.java.classifiers.ClassifiersPackage.eINSTANCE.getClass_().getEStructuralFeature(org.emftext.language.java.classifiers.ClassifiersPackage.CLASS__TYPE_PARAMETERS), JavaCardinality.ONE, new org.eclipse.emf.ecore.EClass[] {org.emftext.language.java.generics.GenericsPackage.eINSTANCE.getTypeParameter(), }, 0);
	public final static JavaSequence JAVA_7_0_0_3_0_0_2_0_0 = new JavaSequence(JavaCardinality.ONE, JAVA_7_0_0_3_0_0_2_0_0_0, JAVA_7_0_0_3_0_0_2_0_0_1);
	public final static JavaChoice JAVA_7_0_0_3_0_0_2_0 = new JavaChoice(JavaCardinality.ONE, JAVA_7_0_0_3_0_0_2_0_0);
	public final static JavaCompound JAVA_7_0_0_3_0_0_2 = new JavaCompound(JAVA_7_0_0_3_0_0_2_0, JavaCardinality.STAR);
	public final static JavaKeyword JAVA_7_0_0_3_0_0_3 = new JavaKeyword(">", JavaCardinality.ONE);
	public final static JavaSequence JAVA_7_0_0_3_0_0 = new JavaSequence(JavaCardinality.ONE, JAVA_7_0_0_3_0_0_0, JAVA_7_0_0_3_0_0_1, JAVA_7_0_0_3_0_0_2, JAVA_7_0_0_3_0_0_3);
	public final static JavaChoice JAVA_7_0_0_3_0 = new JavaChoice(JavaCardinality.ONE, JAVA_7_0_0_3_0_0);
	public final static JavaCompound JAVA_7_0_0_3 = new JavaCompound(JAVA_7_0_0_3_0, JavaCardinality.QUESTIONMARK);
	public final static JavaKeyword JAVA_7_0_0_4_0_0_0 = new JavaKeyword("extends", JavaCardinality.ONE);
	public final static JavaContainment JAVA_7_0_0_4_0_0_1 = new JavaContainment(org.emftext.language.java.classifiers.ClassifiersPackage.eINSTANCE.getClass_().getEStructuralFeature(org.emftext.language.java.classifiers.ClassifiersPackage.CLASS__EXTENDS), JavaCardinality.ONE, new org.eclipse.emf.ecore.EClass[] {org.emftext.language.java.types.TypesPackage.eINSTANCE.getTypeReference(), }, 0);
	public final static JavaSequence JAVA_7_0_0_4_0_0 = new JavaSequence(JavaCardinality.ONE, JAVA_7_0_0_4_0_0_0, JAVA_7_0_0_4_0_0_1);
	public final static JavaChoice JAVA_7_0_0_4_0 = new JavaChoice(JavaCardinality.ONE, JAVA_7_0_0_4_0_0);
	public final static JavaCompound JAVA_7_0_0_4 = new JavaCompound(JAVA_7_0_0_4_0, JavaCardinality.QUESTIONMARK);
	public final static JavaKeyword JAVA_7_0_0_5_0_0_0 = new JavaKeyword("implements", JavaCardinality.ONE);
	public final static JavaContainment JAVA_7_0_0_5_0_0_1_0_0_0 = new JavaContainment(org.emftext.language.java.classifiers.ClassifiersPackage.eINSTANCE.getClass_().getEStructuralFeature(org.emftext.language.java.classifiers.ClassifiersPackage.CLASS__IMPLEMENTS), JavaCardinality.ONE, new org.eclipse.emf.ecore.EClass[] {org.emftext.language.java.types.TypesPackage.eINSTANCE.getTypeReference(), }, 0);
	public final static JavaKeyword JAVA_7_0_0_5_0_0_1_0_0_1_0_0_0 = new JavaKeyword(",", JavaCardinality.ONE);
	public final static JavaContainment JAVA_7_0_0_5_0_0_1_0_0_1_0_0_1 = new JavaContainment(org.emftext.language.java.classifiers.ClassifiersPackage.eINSTANCE.getClass_().getEStructuralFeature(org.emftext.language.java.classifiers.ClassifiersPackage.CLASS__IMPLEMENTS), JavaCardinality.ONE, new org.eclipse.emf.ecore.EClass[] {org.emftext.language.java.types.TypesPackage.eINSTANCE.getTypeReference(), }, 0);
	public final static JavaSequence JAVA_7_0_0_5_0_0_1_0_0_1_0_0 = new JavaSequence(JavaCardinality.ONE, JAVA_7_0_0_5_0_0_1_0_0_1_0_0_0, JAVA_7_0_0_5_0_0_1_0_0_1_0_0_1);
	public final static JavaChoice JAVA_7_0_0_5_0_0_1_0_0_1_0 = new JavaChoice(JavaCardinality.ONE, JAVA_7_0_0_5_0_0_1_0_0_1_0_0);
	public final static JavaCompound JAVA_7_0_0_5_0_0_1_0_0_1 = new JavaCompound(JAVA_7_0_0_5_0_0_1_0_0_1_0, JavaCardinality.STAR);
	public final static JavaSequence JAVA_7_0_0_5_0_0_1_0_0 = new JavaSequence(JavaCardinality.ONE, JAVA_7_0_0_5_0_0_1_0_0_0, JAVA_7_0_0_5_0_0_1_0_0_1);
	public final static JavaChoice JAVA_7_0_0_5_0_0_1_0 = new JavaChoice(JavaCardinality.ONE, JAVA_7_0_0_5_0_0_1_0_0);
	public final static JavaCompound JAVA_7_0_0_5_0_0_1 = new JavaCompound(JAVA_7_0_0_5_0_0_1_0, JavaCardinality.ONE);
	public final static JavaSequence JAVA_7_0_0_5_0_0 = new JavaSequence(JavaCardinality.ONE, JAVA_7_0_0_5_0_0_0, JAVA_7_0_0_5_0_0_1);
	public final static JavaChoice JAVA_7_0_0_5_0 = new JavaChoice(JavaCardinality.ONE, JAVA_7_0_0_5_0_0);
	public final static JavaCompound JAVA_7_0_0_5 = new JavaCompound(JAVA_7_0_0_5_0, JavaCardinality.QUESTIONMARK);
	public final static JavaWhiteSpace JAVA_7_0_0_6 = new JavaWhiteSpace(1, JavaCardinality.ONE);
	public final static JavaKeyword JAVA_7_0_0_7 = new JavaKeyword("{", JavaCardinality.ONE);
	public final static JavaLineBreak JAVA_7_0_0_8_0_0_0 = new JavaLineBreak(JavaCardinality.ONE, 1);
	public final static JavaContainment JAVA_7_0_0_8_0_0_1 = new JavaContainment(org.emftext.language.java.classifiers.ClassifiersPackage.eINSTANCE.getClass_().getEStructuralFeature(org.emftext.language.java.classifiers.ClassifiersPackage.CLASS__MEMBERS), JavaCardinality.ONE, new org.eclipse.emf.ecore.EClass[] {org.emftext.language.java.members.MembersPackage.eINSTANCE.getMember(), }, 0);
	public final static JavaSequence JAVA_7_0_0_8_0_0 = new JavaSequence(JavaCardinality.ONE, JAVA_7_0_0_8_0_0_0, JAVA_7_0_0_8_0_0_1);
	public final static JavaChoice JAVA_7_0_0_8_0 = new JavaChoice(JavaCardinality.ONE, JAVA_7_0_0_8_0_0);
	public final static JavaCompound JAVA_7_0_0_8 = new JavaCompound(JAVA_7_0_0_8_0, JavaCardinality.STAR);
	public final static JavaLineBreak JAVA_7_0_0_9 = new JavaLineBreak(JavaCardinality.ONE, 0);
	public final static JavaKeyword JAVA_7_0_0_10 = new JavaKeyword("}", JavaCardinality.ONE);
	public final static JavaSequence JAVA_7_0_0 = new JavaSequence(JavaCardinality.ONE, JAVA_7_0_0_0, JAVA_7_0_0_1, JAVA_7_0_0_2, JAVA_7_0_0_3, JAVA_7_0_0_4, JAVA_7_0_0_5, JAVA_7_0_0_6, JAVA_7_0_0_7, JAVA_7_0_0_8, JAVA_7_0_0_9, JAVA_7_0_0_10);
	public final static JavaChoice JAVA_7_0 = new JavaChoice(JavaCardinality.ONE, JAVA_7_0_0);
	public final static JavaRule JAVA_7 = new JavaRule(org.emftext.language.java.classifiers.ClassifiersPackage.eINSTANCE.getClass_(), JAVA_7_0, JavaCardinality.ONE);
	public final static JavaWhiteSpace JAVA_8_0_0_0 = new JavaWhiteSpace(1, JavaCardinality.ONE);
	public final static JavaKeyword JAVA_8_0_0_1 = new JavaKeyword("{", JavaCardinality.ONE);
	public final static JavaLineBreak JAVA_8_0_0_2_0_0_0 = new JavaLineBreak(JavaCardinality.ONE, 1);
	public final static JavaContainment JAVA_8_0_0_2_0_0_1 = new JavaContainment(org.emftext.language.java.classifiers.ClassifiersPackage.eINSTANCE.getAnonymousClass().getEStructuralFeature(org.emftext.language.java.classifiers.ClassifiersPackage.ANONYMOUS_CLASS__MEMBERS), JavaCardinality.ONE, new org.eclipse.emf.ecore.EClass[] {org.emftext.language.java.members.MembersPackage.eINSTANCE.getMember(), }, 0);
	public final static JavaSequence JAVA_8_0_0_2_0_0 = new JavaSequence(JavaCardinality.ONE, JAVA_8_0_0_2_0_0_0, JAVA_8_0_0_2_0_0_1);
	public final static JavaChoice JAVA_8_0_0_2_0 = new JavaChoice(JavaCardinality.ONE, JAVA_8_0_0_2_0_0);
	public final static JavaCompound JAVA_8_0_0_2 = new JavaCompound(JAVA_8_0_0_2_0, JavaCardinality.STAR);
	public final static JavaLineBreak JAVA_8_0_0_3 = new JavaLineBreak(JavaCardinality.ONE, 0);
	public final static JavaKeyword JAVA_8_0_0_4 = new JavaKeyword("}", JavaCardinality.ONE);
	public final static JavaSequence JAVA_8_0_0 = new JavaSequence(JavaCardinality.ONE, JAVA_8_0_0_0, JAVA_8_0_0_1, JAVA_8_0_0_2, JAVA_8_0_0_3, JAVA_8_0_0_4);
	public final static JavaChoice JAVA_8_0 = new JavaChoice(JavaCardinality.ONE, JAVA_8_0_0);
	public final static JavaRule JAVA_8 = new JavaRule(org.emftext.language.java.classifiers.ClassifiersPackage.eINSTANCE.getAnonymousClass(), JAVA_8_0, JavaCardinality.ONE);
	public final static JavaContainment JAVA_9_0_0_0 = new JavaContainment(org.emftext.language.java.classifiers.ClassifiersPackage.eINSTANCE.getInterface().getEStructuralFeature(org.emftext.language.java.classifiers.ClassifiersPackage.INTERFACE__ANNOTATIONS_AND_MODIFIERS), JavaCardinality.STAR, new org.eclipse.emf.ecore.EClass[] {org.emftext.language.java.modifiers.ModifiersPackage.eINSTANCE.getAnnotationInstanceOrModifier(), }, 0);
	public final static JavaKeyword JAVA_9_0_0_1 = new JavaKeyword("interface", JavaCardinality.ONE);
	public final static JavaPlaceholder JAVA_9_0_0_2 = new JavaPlaceholder(org.emftext.language.java.classifiers.ClassifiersPackage.eINSTANCE.getInterface().getEStructuralFeature(org.emftext.language.java.classifiers.ClassifiersPackage.INTERFACE__NAME), "IDENTIFIER", JavaCardinality.ONE, 0);
	public final static JavaKeyword JAVA_9_0_0_3_0_0_0 = new JavaKeyword("<", JavaCardinality.ONE);
	public final static JavaContainment JAVA_9_0_0_3_0_0_1 = new JavaContainment(org.emftext.language.java.classifiers.ClassifiersPackage.eINSTANCE.getInterface().getEStructuralFeature(org.emftext.language.java.classifiers.ClassifiersPackage.INTERFACE__TYPE_PARAMETERS), JavaCardinality.ONE, new org.eclipse.emf.ecore.EClass[] {org.emftext.language.java.generics.GenericsPackage.eINSTANCE.getTypeParameter(), }, 0);
	public final static JavaKeyword JAVA_9_0_0_3_0_0_2_0_0_0 = new JavaKeyword(",", JavaCardinality.ONE);
	public final static JavaContainment JAVA_9_0_0_3_0_0_2_0_0_1 = new JavaContainment(org.emftext.language.java.classifiers.ClassifiersPackage.eINSTANCE.getInterface().getEStructuralFeature(org.emftext.language.java.classifiers.ClassifiersPackage.INTERFACE__TYPE_PARAMETERS), JavaCardinality.ONE, new org.eclipse.emf.ecore.EClass[] {org.emftext.language.java.generics.GenericsPackage.eINSTANCE.getTypeParameter(), }, 0);
	public final static JavaSequence JAVA_9_0_0_3_0_0_2_0_0 = new JavaSequence(JavaCardinality.ONE, JAVA_9_0_0_3_0_0_2_0_0_0, JAVA_9_0_0_3_0_0_2_0_0_1);
	public final static JavaChoice JAVA_9_0_0_3_0_0_2_0 = new JavaChoice(JavaCardinality.ONE, JAVA_9_0_0_3_0_0_2_0_0);
	public final static JavaCompound JAVA_9_0_0_3_0_0_2 = new JavaCompound(JAVA_9_0_0_3_0_0_2_0, JavaCardinality.STAR);
	public final static JavaKeyword JAVA_9_0_0_3_0_0_3 = new JavaKeyword(">", JavaCardinality.ONE);
	public final static JavaSequence JAVA_9_0_0_3_0_0 = new JavaSequence(JavaCardinality.ONE, JAVA_9_0_0_3_0_0_0, JAVA_9_0_0_3_0_0_1, JAVA_9_0_0_3_0_0_2, JAVA_9_0_0_3_0_0_3);
	public final static JavaChoice JAVA_9_0_0_3_0 = new JavaChoice(JavaCardinality.ONE, JAVA_9_0_0_3_0_0);
	public final static JavaCompound JAVA_9_0_0_3 = new JavaCompound(JAVA_9_0_0_3_0, JavaCardinality.QUESTIONMARK);
	public final static JavaKeyword JAVA_9_0_0_4_0_0_0 = new JavaKeyword("extends", JavaCardinality.ONE);
	public final static JavaContainment JAVA_9_0_0_4_0_0_1_0_0_0 = new JavaContainment(org.emftext.language.java.classifiers.ClassifiersPackage.eINSTANCE.getInterface().getEStructuralFeature(org.emftext.language.java.classifiers.ClassifiersPackage.INTERFACE__EXTENDS), JavaCardinality.ONE, new org.eclipse.emf.ecore.EClass[] {org.emftext.language.java.types.TypesPackage.eINSTANCE.getTypeReference(), }, 0);
	public final static JavaKeyword JAVA_9_0_0_4_0_0_1_0_0_1_0_0_0 = new JavaKeyword(",", JavaCardinality.ONE);
	public final static JavaContainment JAVA_9_0_0_4_0_0_1_0_0_1_0_0_1 = new JavaContainment(org.emftext.language.java.classifiers.ClassifiersPackage.eINSTANCE.getInterface().getEStructuralFeature(org.emftext.language.java.classifiers.ClassifiersPackage.INTERFACE__EXTENDS), JavaCardinality.ONE, new org.eclipse.emf.ecore.EClass[] {org.emftext.language.java.types.TypesPackage.eINSTANCE.getTypeReference(), }, 0);
	public final static JavaSequence JAVA_9_0_0_4_0_0_1_0_0_1_0_0 = new JavaSequence(JavaCardinality.ONE, JAVA_9_0_0_4_0_0_1_0_0_1_0_0_0, JAVA_9_0_0_4_0_0_1_0_0_1_0_0_1);
	public final static JavaChoice JAVA_9_0_0_4_0_0_1_0_0_1_0 = new JavaChoice(JavaCardinality.ONE, JAVA_9_0_0_4_0_0_1_0_0_1_0_0);
	public final static JavaCompound JAVA_9_0_0_4_0_0_1_0_0_1 = new JavaCompound(JAVA_9_0_0_4_0_0_1_0_0_1_0, JavaCardinality.STAR);
	public final static JavaSequence JAVA_9_0_0_4_0_0_1_0_0 = new JavaSequence(JavaCardinality.ONE, JAVA_9_0_0_4_0_0_1_0_0_0, JAVA_9_0_0_4_0_0_1_0_0_1);
	public final static JavaChoice JAVA_9_0_0_4_0_0_1_0 = new JavaChoice(JavaCardinality.ONE, JAVA_9_0_0_4_0_0_1_0_0);
	public final static JavaCompound JAVA_9_0_0_4_0_0_1 = new JavaCompound(JAVA_9_0_0_4_0_0_1_0, JavaCardinality.ONE);
	public final static JavaSequence JAVA_9_0_0_4_0_0 = new JavaSequence(JavaCardinality.ONE, JAVA_9_0_0_4_0_0_0, JAVA_9_0_0_4_0_0_1);
	public final static JavaChoice JAVA_9_0_0_4_0 = new JavaChoice(JavaCardinality.ONE, JAVA_9_0_0_4_0_0);
	public final static JavaCompound JAVA_9_0_0_4 = new JavaCompound(JAVA_9_0_0_4_0, JavaCardinality.QUESTIONMARK);
	public final static JavaWhiteSpace JAVA_9_0_0_5 = new JavaWhiteSpace(1, JavaCardinality.ONE);
	public final static JavaKeyword JAVA_9_0_0_6 = new JavaKeyword("{", JavaCardinality.ONE);
	public final static JavaLineBreak JAVA_9_0_0_7_0_0_0 = new JavaLineBreak(JavaCardinality.ONE, 1);
	public final static JavaContainment JAVA_9_0_0_7_0_0_1 = new JavaContainment(org.emftext.language.java.classifiers.ClassifiersPackage.eINSTANCE.getInterface().getEStructuralFeature(org.emftext.language.java.classifiers.ClassifiersPackage.INTERFACE__MEMBERS), JavaCardinality.ONE, new org.eclipse.emf.ecore.EClass[] {org.emftext.language.java.members.MembersPackage.eINSTANCE.getMember(), }, 0);
	public final static JavaSequence JAVA_9_0_0_7_0_0 = new JavaSequence(JavaCardinality.ONE, JAVA_9_0_0_7_0_0_0, JAVA_9_0_0_7_0_0_1);
	public final static JavaChoice JAVA_9_0_0_7_0 = new JavaChoice(JavaCardinality.ONE, JAVA_9_0_0_7_0_0);
	public final static JavaCompound JAVA_9_0_0_7 = new JavaCompound(JAVA_9_0_0_7_0, JavaCardinality.STAR);
	public final static JavaLineBreak JAVA_9_0_0_8 = new JavaLineBreak(JavaCardinality.ONE, 0);
	public final static JavaKeyword JAVA_9_0_0_9 = new JavaKeyword("}", JavaCardinality.ONE);
	public final static JavaSequence JAVA_9_0_0 = new JavaSequence(JavaCardinality.ONE, JAVA_9_0_0_0, JAVA_9_0_0_1, JAVA_9_0_0_2, JAVA_9_0_0_3, JAVA_9_0_0_4, JAVA_9_0_0_5, JAVA_9_0_0_6, JAVA_9_0_0_7, JAVA_9_0_0_8, JAVA_9_0_0_9);
	public final static JavaChoice JAVA_9_0 = new JavaChoice(JavaCardinality.ONE, JAVA_9_0_0);
	public final static JavaRule JAVA_9 = new JavaRule(org.emftext.language.java.classifiers.ClassifiersPackage.eINSTANCE.getInterface(), JAVA_9_0, JavaCardinality.ONE);
	public final static JavaContainment JAVA_10_0_0_0 = new JavaContainment(org.emftext.language.java.classifiers.ClassifiersPackage.eINSTANCE.getEnumeration().getEStructuralFeature(org.emftext.language.java.classifiers.ClassifiersPackage.ENUMERATION__ANNOTATIONS_AND_MODIFIERS), JavaCardinality.STAR, new org.eclipse.emf.ecore.EClass[] {org.emftext.language.java.modifiers.ModifiersPackage.eINSTANCE.getAnnotationInstanceOrModifier(), }, 0);
	public final static JavaKeyword JAVA_10_0_0_1 = new JavaKeyword("enum", JavaCardinality.ONE);
	public final static JavaPlaceholder JAVA_10_0_0_2 = new JavaPlaceholder(org.emftext.language.java.classifiers.ClassifiersPackage.eINSTANCE.getEnumeration().getEStructuralFeature(org.emftext.language.java.classifiers.ClassifiersPackage.ENUMERATION__NAME), "IDENTIFIER", JavaCardinality.ONE, 0);
	public final static JavaKeyword JAVA_10_0_0_3_0_0_0 = new JavaKeyword("implements", JavaCardinality.ONE);
	public final static JavaContainment JAVA_10_0_0_3_0_0_1_0_0_0 = new JavaContainment(org.emftext.language.java.classifiers.ClassifiersPackage.eINSTANCE.getEnumeration().getEStructuralFeature(org.emftext.language.java.classifiers.ClassifiersPackage.ENUMERATION__IMPLEMENTS), JavaCardinality.ONE, new org.eclipse.emf.ecore.EClass[] {org.emftext.language.java.types.TypesPackage.eINSTANCE.getTypeReference(), }, 0);
	public final static JavaKeyword JAVA_10_0_0_3_0_0_1_0_0_1_0_0_0 = new JavaKeyword(",", JavaCardinality.ONE);
	public final static JavaContainment JAVA_10_0_0_3_0_0_1_0_0_1_0_0_1 = new JavaContainment(org.emftext.language.java.classifiers.ClassifiersPackage.eINSTANCE.getEnumeration().getEStructuralFeature(org.emftext.language.java.classifiers.ClassifiersPackage.ENUMERATION__IMPLEMENTS), JavaCardinality.ONE, new org.eclipse.emf.ecore.EClass[] {org.emftext.language.java.types.TypesPackage.eINSTANCE.getTypeReference(), }, 0);
	public final static JavaSequence JAVA_10_0_0_3_0_0_1_0_0_1_0_0 = new JavaSequence(JavaCardinality.ONE, JAVA_10_0_0_3_0_0_1_0_0_1_0_0_0, JAVA_10_0_0_3_0_0_1_0_0_1_0_0_1);
	public final static JavaChoice JAVA_10_0_0_3_0_0_1_0_0_1_0 = new JavaChoice(JavaCardinality.ONE, JAVA_10_0_0_3_0_0_1_0_0_1_0_0);
	public final static JavaCompound JAVA_10_0_0_3_0_0_1_0_0_1 = new JavaCompound(JAVA_10_0_0_3_0_0_1_0_0_1_0, JavaCardinality.STAR);
	public final static JavaSequence JAVA_10_0_0_3_0_0_1_0_0 = new JavaSequence(JavaCardinality.ONE, JAVA_10_0_0_3_0_0_1_0_0_0, JAVA_10_0_0_3_0_0_1_0_0_1);
	public final static JavaChoice JAVA_10_0_0_3_0_0_1_0 = new JavaChoice(JavaCardinality.ONE, JAVA_10_0_0_3_0_0_1_0_0);
	public final static JavaCompound JAVA_10_0_0_3_0_0_1 = new JavaCompound(JAVA_10_0_0_3_0_0_1_0, JavaCardinality.ONE);
	public final static JavaSequence JAVA_10_0_0_3_0_0 = new JavaSequence(JavaCardinality.ONE, JAVA_10_0_0_3_0_0_0, JAVA_10_0_0_3_0_0_1);
	public final static JavaChoice JAVA_10_0_0_3_0 = new JavaChoice(JavaCardinality.ONE, JAVA_10_0_0_3_0_0);
	public final static JavaCompound JAVA_10_0_0_3 = new JavaCompound(JAVA_10_0_0_3_0, JavaCardinality.QUESTIONMARK);
	public final static JavaWhiteSpace JAVA_10_0_0_4 = new JavaWhiteSpace(1, JavaCardinality.ONE);
	public final static JavaKeyword JAVA_10_0_0_5 = new JavaKeyword("{", JavaCardinality.ONE);
	public final static JavaLineBreak JAVA_10_0_0_6_0_0_0 = new JavaLineBreak(JavaCardinality.ONE, 1);
	public final static JavaContainment JAVA_10_0_0_6_0_0_1 = new JavaContainment(org.emftext.language.java.classifiers.ClassifiersPackage.eINSTANCE.getEnumeration().getEStructuralFeature(org.emftext.language.java.classifiers.ClassifiersPackage.ENUMERATION__CONSTANTS), JavaCardinality.ONE, new org.eclipse.emf.ecore.EClass[] {org.emftext.language.java.members.MembersPackage.eINSTANCE.getEnumConstant(), }, 0);
	public final static JavaKeyword JAVA_10_0_0_6_0_0_2_0_0_0 = new JavaKeyword(",", JavaCardinality.ONE);
	public final static JavaLineBreak JAVA_10_0_0_6_0_0_2_0_0_1 = new JavaLineBreak(JavaCardinality.ONE, 1);
	public final static JavaContainment JAVA_10_0_0_6_0_0_2_0_0_2 = new JavaContainment(org.emftext.language.java.classifiers.ClassifiersPackage.eINSTANCE.getEnumeration().getEStructuralFeature(org.emftext.language.java.classifiers.ClassifiersPackage.ENUMERATION__CONSTANTS), JavaCardinality.ONE, new org.eclipse.emf.ecore.EClass[] {org.emftext.language.java.members.MembersPackage.eINSTANCE.getEnumConstant(), }, 0);
	public final static JavaSequence JAVA_10_0_0_6_0_0_2_0_0 = new JavaSequence(JavaCardinality.ONE, JAVA_10_0_0_6_0_0_2_0_0_0, JAVA_10_0_0_6_0_0_2_0_0_1, JAVA_10_0_0_6_0_0_2_0_0_2);
	public final static JavaChoice JAVA_10_0_0_6_0_0_2_0 = new JavaChoice(JavaCardinality.ONE, JAVA_10_0_0_6_0_0_2_0_0);
	public final static JavaCompound JAVA_10_0_0_6_0_0_2 = new JavaCompound(JAVA_10_0_0_6_0_0_2_0, JavaCardinality.STAR);
	public final static JavaSequence JAVA_10_0_0_6_0_0 = new JavaSequence(JavaCardinality.ONE, JAVA_10_0_0_6_0_0_0, JAVA_10_0_0_6_0_0_1, JAVA_10_0_0_6_0_0_2);
	public final static JavaChoice JAVA_10_0_0_6_0 = new JavaChoice(JavaCardinality.ONE, JAVA_10_0_0_6_0_0);
	public final static JavaCompound JAVA_10_0_0_6 = new JavaCompound(JAVA_10_0_0_6_0, JavaCardinality.QUESTIONMARK);
	public final static JavaKeyword JAVA_10_0_0_7_0_0_0 = new JavaKeyword(",", JavaCardinality.ONE);
	public final static JavaSequence JAVA_10_0_0_7_0_0 = new JavaSequence(JavaCardinality.ONE, JAVA_10_0_0_7_0_0_0);
	public final static JavaChoice JAVA_10_0_0_7_0 = new JavaChoice(JavaCardinality.ONE, JAVA_10_0_0_7_0_0);
	public final static JavaCompound JAVA_10_0_0_7 = new JavaCompound(JAVA_10_0_0_7_0, JavaCardinality.QUESTIONMARK);
	public final static JavaKeyword JAVA_10_0_0_8_0_0_0 = new JavaKeyword(";", JavaCardinality.ONE);
	public final static JavaLineBreak JAVA_10_0_0_8_0_0_1_0_0_0 = new JavaLineBreak(JavaCardinality.ONE, 1);
	public final static JavaContainment JAVA_10_0_0_8_0_0_1_0_0_1 = new JavaContainment(org.emftext.language.java.classifiers.ClassifiersPackage.eINSTANCE.getEnumeration().getEStructuralFeature(org.emftext.language.java.classifiers.ClassifiersPackage.ENUMERATION__MEMBERS), JavaCardinality.ONE, new org.eclipse.emf.ecore.EClass[] {org.emftext.language.java.members.MembersPackage.eINSTANCE.getMember(), }, 0);
	public final static JavaSequence JAVA_10_0_0_8_0_0_1_0_0 = new JavaSequence(JavaCardinality.ONE, JAVA_10_0_0_8_0_0_1_0_0_0, JAVA_10_0_0_8_0_0_1_0_0_1);
	public final static JavaChoice JAVA_10_0_0_8_0_0_1_0 = new JavaChoice(JavaCardinality.ONE, JAVA_10_0_0_8_0_0_1_0_0);
	public final static JavaCompound JAVA_10_0_0_8_0_0_1 = new JavaCompound(JAVA_10_0_0_8_0_0_1_0, JavaCardinality.STAR);
	public final static JavaLineBreak JAVA_10_0_0_8_0_0_2 = new JavaLineBreak(JavaCardinality.ONE, 0);
	public final static JavaSequence JAVA_10_0_0_8_0_0 = new JavaSequence(JavaCardinality.ONE, JAVA_10_0_0_8_0_0_0, JAVA_10_0_0_8_0_0_1, JAVA_10_0_0_8_0_0_2);
	public final static JavaChoice JAVA_10_0_0_8_0 = new JavaChoice(JavaCardinality.ONE, JAVA_10_0_0_8_0_0);
	public final static JavaCompound JAVA_10_0_0_8 = new JavaCompound(JAVA_10_0_0_8_0, JavaCardinality.QUESTIONMARK);
	public final static JavaKeyword JAVA_10_0_0_9 = new JavaKeyword("}", JavaCardinality.ONE);
	public final static JavaSequence JAVA_10_0_0 = new JavaSequence(JavaCardinality.ONE, JAVA_10_0_0_0, JAVA_10_0_0_1, JAVA_10_0_0_2, JAVA_10_0_0_3, JAVA_10_0_0_4, JAVA_10_0_0_5, JAVA_10_0_0_6, JAVA_10_0_0_7, JAVA_10_0_0_8, JAVA_10_0_0_9);
	public final static JavaChoice JAVA_10_0 = new JavaChoice(JavaCardinality.ONE, JAVA_10_0_0);
	public final static JavaRule JAVA_10 = new JavaRule(org.emftext.language.java.classifiers.ClassifiersPackage.eINSTANCE.getEnumeration(), JAVA_10_0, JavaCardinality.ONE);
	public final static JavaContainment JAVA_11_0_0_0 = new JavaContainment(org.emftext.language.java.classifiers.ClassifiersPackage.eINSTANCE.getAnnotation().getEStructuralFeature(org.emftext.language.java.classifiers.ClassifiersPackage.ANNOTATION__ANNOTATIONS_AND_MODIFIERS), JavaCardinality.STAR, new org.eclipse.emf.ecore.EClass[] {org.emftext.language.java.modifiers.ModifiersPackage.eINSTANCE.getAnnotationInstanceOrModifier(), }, 0);
	public final static JavaKeyword JAVA_11_0_0_1 = new JavaKeyword("@", JavaCardinality.ONE);
	public final static JavaKeyword JAVA_11_0_0_2 = new JavaKeyword("interface", JavaCardinality.ONE);
	public final static JavaPlaceholder JAVA_11_0_0_3 = new JavaPlaceholder(org.emftext.language.java.classifiers.ClassifiersPackage.eINSTANCE.getAnnotation().getEStructuralFeature(org.emftext.language.java.classifiers.ClassifiersPackage.ANNOTATION__NAME), "IDENTIFIER", JavaCardinality.ONE, 0);
	public final static JavaWhiteSpace JAVA_11_0_0_4 = new JavaWhiteSpace(1, JavaCardinality.ONE);
	public final static JavaKeyword JAVA_11_0_0_5 = new JavaKeyword("{", JavaCardinality.ONE);
	public final static JavaLineBreak JAVA_11_0_0_6_0_0_0 = new JavaLineBreak(JavaCardinality.ONE, 1);
	public final static JavaContainment JAVA_11_0_0_6_0_0_1 = new JavaContainment(org.emftext.language.java.classifiers.ClassifiersPackage.eINSTANCE.getAnnotation().getEStructuralFeature(org.emftext.language.java.classifiers.ClassifiersPackage.ANNOTATION__MEMBERS), JavaCardinality.ONE, new org.eclipse.emf.ecore.EClass[] {org.emftext.language.java.members.MembersPackage.eINSTANCE.getMember(), }, 0);
	public final static JavaSequence JAVA_11_0_0_6_0_0 = new JavaSequence(JavaCardinality.ONE, JAVA_11_0_0_6_0_0_0, JAVA_11_0_0_6_0_0_1);
	public final static JavaChoice JAVA_11_0_0_6_0 = new JavaChoice(JavaCardinality.ONE, JAVA_11_0_0_6_0_0);
	public final static JavaCompound JAVA_11_0_0_6 = new JavaCompound(JAVA_11_0_0_6_0, JavaCardinality.STAR);
	public final static JavaLineBreak JAVA_11_0_0_7 = new JavaLineBreak(JavaCardinality.ONE, 0);
	public final static JavaKeyword JAVA_11_0_0_8 = new JavaKeyword("}", JavaCardinality.ONE);
	public final static JavaSequence JAVA_11_0_0 = new JavaSequence(JavaCardinality.ONE, JAVA_11_0_0_0, JAVA_11_0_0_1, JAVA_11_0_0_2, JAVA_11_0_0_3, JAVA_11_0_0_4, JAVA_11_0_0_5, JAVA_11_0_0_6, JAVA_11_0_0_7, JAVA_11_0_0_8);
	public final static JavaChoice JAVA_11_0 = new JavaChoice(JavaCardinality.ONE, JAVA_11_0_0);
	public final static JavaRule JAVA_11 = new JavaRule(org.emftext.language.java.classifiers.ClassifiersPackage.eINSTANCE.getAnnotation(), JAVA_11_0, JavaCardinality.ONE);
	public final static JavaKeyword JAVA_12_0_0_0 = new JavaKeyword("@", JavaCardinality.ONE);
	public final static JavaPlaceholder JAVA_12_0_0_1_0_0_0 = new JavaPlaceholder(org.emftext.language.java.annotations.AnnotationsPackage.eINSTANCE.getAnnotationInstance().getEStructuralFeature(org.emftext.language.java.annotations.AnnotationsPackage.ANNOTATION_INSTANCE__NAMESPACES), "IDENTIFIER", JavaCardinality.ONE, 0);
	public final static JavaKeyword JAVA_12_0_0_1_0_0_1 = new JavaKeyword(".", JavaCardinality.ONE);
	public final static JavaSequence JAVA_12_0_0_1_0_0 = new JavaSequence(JavaCardinality.ONE, JAVA_12_0_0_1_0_0_0, JAVA_12_0_0_1_0_0_1);
	public final static JavaChoice JAVA_12_0_0_1_0 = new JavaChoice(JavaCardinality.ONE, JAVA_12_0_0_1_0_0);
	public final static JavaCompound JAVA_12_0_0_1 = new JavaCompound(JAVA_12_0_0_1_0, JavaCardinality.STAR);
	public final static JavaPlaceholder JAVA_12_0_0_2 = new JavaPlaceholder(org.emftext.language.java.annotations.AnnotationsPackage.eINSTANCE.getAnnotationInstance().getEStructuralFeature(org.emftext.language.java.annotations.AnnotationsPackage.ANNOTATION_INSTANCE__ANNOTATION), "IDENTIFIER", JavaCardinality.ONE, 0);
	public final static JavaContainment JAVA_12_0_0_3_0_0_0 = new JavaContainment(org.emftext.language.java.annotations.AnnotationsPackage.eINSTANCE.getAnnotationInstance().getEStructuralFeature(org.emftext.language.java.annotations.AnnotationsPackage.ANNOTATION_INSTANCE__PARAMETER), JavaCardinality.ONE, new org.eclipse.emf.ecore.EClass[] {org.emftext.language.java.annotations.AnnotationsPackage.eINSTANCE.getAnnotationParameter(), }, 0);
	public final static JavaSequence JAVA_12_0_0_3_0_0 = new JavaSequence(JavaCardinality.ONE, JAVA_12_0_0_3_0_0_0);
	public final static JavaChoice JAVA_12_0_0_3_0 = new JavaChoice(JavaCardinality.ONE, JAVA_12_0_0_3_0_0);
	public final static JavaCompound JAVA_12_0_0_3 = new JavaCompound(JAVA_12_0_0_3_0, JavaCardinality.QUESTIONMARK);
	public final static JavaSequence JAVA_12_0_0 = new JavaSequence(JavaCardinality.ONE, JAVA_12_0_0_0, JAVA_12_0_0_1, JAVA_12_0_0_2, JAVA_12_0_0_3);
	public final static JavaChoice JAVA_12_0 = new JavaChoice(JavaCardinality.ONE, JAVA_12_0_0);
	public final static JavaRule JAVA_12 = new JavaRule(org.emftext.language.java.annotations.AnnotationsPackage.eINSTANCE.getAnnotationInstance(), JAVA_12_0, JavaCardinality.ONE);
	public final static JavaKeyword JAVA_13_0_0_0 = new JavaKeyword("(", JavaCardinality.ONE);
	public final static JavaContainment JAVA_13_0_0_1 = new JavaContainment(org.emftext.language.java.annotations.AnnotationsPackage.eINSTANCE.getSingleAnnotationParameter().getEStructuralFeature(org.emftext.language.java.annotations.AnnotationsPackage.SINGLE_ANNOTATION_PARAMETER__VALUE), JavaCardinality.ONE, new org.eclipse.emf.ecore.EClass[] {org.emftext.language.java.arrays.ArraysPackage.eINSTANCE.getArrayInitializer(), org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getConditionalExpression(), }, 0);
	public final static JavaKeyword JAVA_13_0_0_2 = new JavaKeyword(")", JavaCardinality.ONE);
	public final static JavaSequence JAVA_13_0_0 = new JavaSequence(JavaCardinality.ONE, JAVA_13_0_0_0, JAVA_13_0_0_1, JAVA_13_0_0_2);
	public final static JavaChoice JAVA_13_0 = new JavaChoice(JavaCardinality.ONE, JAVA_13_0_0);
	public final static JavaRule JAVA_13 = new JavaRule(org.emftext.language.java.annotations.AnnotationsPackage.eINSTANCE.getSingleAnnotationParameter(), JAVA_13_0, JavaCardinality.ONE);
	public final static JavaKeyword JAVA_14_0_0_0 = new JavaKeyword("(", JavaCardinality.ONE);
	public final static JavaContainment JAVA_14_0_0_1_0_0_0 = new JavaContainment(org.emftext.language.java.annotations.AnnotationsPackage.eINSTANCE.getAnnotationParameterList().getEStructuralFeature(org.emftext.language.java.annotations.AnnotationsPackage.ANNOTATION_PARAMETER_LIST__SETTINGS), JavaCardinality.ONE, new org.eclipse.emf.ecore.EClass[] {org.emftext.language.java.annotations.AnnotationsPackage.eINSTANCE.getAnnotationAttributeSetting(), }, 0);
	public final static JavaKeyword JAVA_14_0_0_1_0_0_1_0_0_0 = new JavaKeyword(",", JavaCardinality.ONE);
	public final static JavaContainment JAVA_14_0_0_1_0_0_1_0_0_1 = new JavaContainment(org.emftext.language.java.annotations.AnnotationsPackage.eINSTANCE.getAnnotationParameterList().getEStructuralFeature(org.emftext.language.java.annotations.AnnotationsPackage.ANNOTATION_PARAMETER_LIST__SETTINGS), JavaCardinality.ONE, new org.eclipse.emf.ecore.EClass[] {org.emftext.language.java.annotations.AnnotationsPackage.eINSTANCE.getAnnotationAttributeSetting(), }, 0);
	public final static JavaSequence JAVA_14_0_0_1_0_0_1_0_0 = new JavaSequence(JavaCardinality.ONE, JAVA_14_0_0_1_0_0_1_0_0_0, JAVA_14_0_0_1_0_0_1_0_0_1);
	public final static JavaChoice JAVA_14_0_0_1_0_0_1_0 = new JavaChoice(JavaCardinality.ONE, JAVA_14_0_0_1_0_0_1_0_0);
	public final static JavaCompound JAVA_14_0_0_1_0_0_1 = new JavaCompound(JAVA_14_0_0_1_0_0_1_0, JavaCardinality.STAR);
	public final static JavaSequence JAVA_14_0_0_1_0_0 = new JavaSequence(JavaCardinality.ONE, JAVA_14_0_0_1_0_0_0, JAVA_14_0_0_1_0_0_1);
	public final static JavaChoice JAVA_14_0_0_1_0 = new JavaChoice(JavaCardinality.ONE, JAVA_14_0_0_1_0_0);
	public final static JavaCompound JAVA_14_0_0_1 = new JavaCompound(JAVA_14_0_0_1_0, JavaCardinality.QUESTIONMARK);
	public final static JavaKeyword JAVA_14_0_0_2 = new JavaKeyword(")", JavaCardinality.ONE);
	public final static JavaSequence JAVA_14_0_0 = new JavaSequence(JavaCardinality.ONE, JAVA_14_0_0_0, JAVA_14_0_0_1, JAVA_14_0_0_2);
	public final static JavaChoice JAVA_14_0 = new JavaChoice(JavaCardinality.ONE, JAVA_14_0_0);
	public final static JavaRule JAVA_14 = new JavaRule(org.emftext.language.java.annotations.AnnotationsPackage.eINSTANCE.getAnnotationParameterList(), JAVA_14_0, JavaCardinality.ONE);
	public final static JavaPlaceholder JAVA_15_0_0_0 = new JavaPlaceholder(org.emftext.language.java.annotations.AnnotationsPackage.eINSTANCE.getAnnotationAttributeSetting().getEStructuralFeature(org.emftext.language.java.annotations.AnnotationsPackage.ANNOTATION_ATTRIBUTE_SETTING__ATTRIBUTE), "IDENTIFIER", JavaCardinality.ONE, 0);
	public final static JavaWhiteSpace JAVA_15_0_0_1 = new JavaWhiteSpace(1, JavaCardinality.ONE);
	public final static JavaKeyword JAVA_15_0_0_2 = new JavaKeyword("=", JavaCardinality.ONE);
	public final static JavaWhiteSpace JAVA_15_0_0_3 = new JavaWhiteSpace(1, JavaCardinality.ONE);
	public final static JavaContainment JAVA_15_0_0_4 = new JavaContainment(org.emftext.language.java.annotations.AnnotationsPackage.eINSTANCE.getAnnotationAttributeSetting().getEStructuralFeature(org.emftext.language.java.annotations.AnnotationsPackage.ANNOTATION_ATTRIBUTE_SETTING__VALUE), JavaCardinality.ONE, new org.eclipse.emf.ecore.EClass[] {org.emftext.language.java.arrays.ArraysPackage.eINSTANCE.getArrayInitializer(), org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getConditionalExpression(), }, 0);
	public final static JavaSequence JAVA_15_0_0 = new JavaSequence(JavaCardinality.ONE, JAVA_15_0_0_0, JAVA_15_0_0_1, JAVA_15_0_0_2, JAVA_15_0_0_3, JAVA_15_0_0_4);
	public final static JavaChoice JAVA_15_0 = new JavaChoice(JavaCardinality.ONE, JAVA_15_0_0);
	public final static JavaRule JAVA_15 = new JavaRule(org.emftext.language.java.annotations.AnnotationsPackage.eINSTANCE.getAnnotationAttributeSetting(), JAVA_15_0, JavaCardinality.ONE);
	public final static JavaPlaceholder JAVA_16_0_0_0 = new JavaPlaceholder(org.emftext.language.java.generics.GenericsPackage.eINSTANCE.getTypeParameter().getEStructuralFeature(org.emftext.language.java.generics.GenericsPackage.TYPE_PARAMETER__NAME), "IDENTIFIER", JavaCardinality.ONE, 0);
	public final static JavaKeyword JAVA_16_0_0_1_0_0_0 = new JavaKeyword("extends", JavaCardinality.ONE);
	public final static JavaContainment JAVA_16_0_0_1_0_0_1 = new JavaContainment(org.emftext.language.java.generics.GenericsPackage.eINSTANCE.getTypeParameter().getEStructuralFeature(org.emftext.language.java.generics.GenericsPackage.TYPE_PARAMETER__EXTEND_TYPES), JavaCardinality.ONE, new org.eclipse.emf.ecore.EClass[] {org.emftext.language.java.types.TypesPackage.eINSTANCE.getTypeReference(), }, 0);
	public final static JavaKeyword JAVA_16_0_0_1_0_0_2_0_0_0 = new JavaKeyword("&", JavaCardinality.ONE);
	public final static JavaContainment JAVA_16_0_0_1_0_0_2_0_0_1 = new JavaContainment(org.emftext.language.java.generics.GenericsPackage.eINSTANCE.getTypeParameter().getEStructuralFeature(org.emftext.language.java.generics.GenericsPackage.TYPE_PARAMETER__EXTEND_TYPES), JavaCardinality.ONE, new org.eclipse.emf.ecore.EClass[] {org.emftext.language.java.types.TypesPackage.eINSTANCE.getTypeReference(), }, 0);
	public final static JavaSequence JAVA_16_0_0_1_0_0_2_0_0 = new JavaSequence(JavaCardinality.ONE, JAVA_16_0_0_1_0_0_2_0_0_0, JAVA_16_0_0_1_0_0_2_0_0_1);
	public final static JavaChoice JAVA_16_0_0_1_0_0_2_0 = new JavaChoice(JavaCardinality.ONE, JAVA_16_0_0_1_0_0_2_0_0);
	public final static JavaCompound JAVA_16_0_0_1_0_0_2 = new JavaCompound(JAVA_16_0_0_1_0_0_2_0, JavaCardinality.STAR);
	public final static JavaSequence JAVA_16_0_0_1_0_0 = new JavaSequence(JavaCardinality.ONE, JAVA_16_0_0_1_0_0_0, JAVA_16_0_0_1_0_0_1, JAVA_16_0_0_1_0_0_2);
	public final static JavaChoice JAVA_16_0_0_1_0 = new JavaChoice(JavaCardinality.ONE, JAVA_16_0_0_1_0_0);
	public final static JavaCompound JAVA_16_0_0_1 = new JavaCompound(JAVA_16_0_0_1_0, JavaCardinality.QUESTIONMARK);
	public final static JavaSequence JAVA_16_0_0 = new JavaSequence(JavaCardinality.ONE, JAVA_16_0_0_0, JAVA_16_0_0_1);
	public final static JavaChoice JAVA_16_0 = new JavaChoice(JavaCardinality.ONE, JAVA_16_0_0);
	public final static JavaRule JAVA_16 = new JavaRule(org.emftext.language.java.generics.GenericsPackage.eINSTANCE.getTypeParameter(), JAVA_16_0, JavaCardinality.ONE);
	public final static JavaContainment JAVA_17_0_0_0 = new JavaContainment(org.emftext.language.java.members.MembersPackage.eINSTANCE.getEnumConstant().getEStructuralFeature(org.emftext.language.java.members.MembersPackage.ENUM_CONSTANT__ANNOTATIONS), JavaCardinality.STAR, new org.eclipse.emf.ecore.EClass[] {org.emftext.language.java.annotations.AnnotationsPackage.eINSTANCE.getAnnotationInstance(), }, 0);
	public final static JavaPlaceholder JAVA_17_0_0_1 = new JavaPlaceholder(org.emftext.language.java.members.MembersPackage.eINSTANCE.getEnumConstant().getEStructuralFeature(org.emftext.language.java.members.MembersPackage.ENUM_CONSTANT__NAME), "IDENTIFIER", JavaCardinality.ONE, 0);
	public final static JavaWhiteSpace JAVA_17_0_0_2_0_0_0 = new JavaWhiteSpace(1, JavaCardinality.ONE);
	public final static JavaKeyword JAVA_17_0_0_2_0_0_1 = new JavaKeyword("(", JavaCardinality.ONE);
	public final static JavaContainment JAVA_17_0_0_2_0_0_2_0_0_0 = new JavaContainment(org.emftext.language.java.members.MembersPackage.eINSTANCE.getEnumConstant().getEStructuralFeature(org.emftext.language.java.members.MembersPackage.ENUM_CONSTANT__ARGUMENTS), JavaCardinality.ONE, new org.eclipse.emf.ecore.EClass[] {org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getAssignmentExpression(), }, 0);
	public final static JavaKeyword JAVA_17_0_0_2_0_0_2_0_0_1_0_0_0 = new JavaKeyword(",", JavaCardinality.ONE);
	public final static JavaContainment JAVA_17_0_0_2_0_0_2_0_0_1_0_0_1 = new JavaContainment(org.emftext.language.java.members.MembersPackage.eINSTANCE.getEnumConstant().getEStructuralFeature(org.emftext.language.java.members.MembersPackage.ENUM_CONSTANT__ARGUMENTS), JavaCardinality.ONE, new org.eclipse.emf.ecore.EClass[] {org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getAssignmentExpression(), }, 0);
	public final static JavaSequence JAVA_17_0_0_2_0_0_2_0_0_1_0_0 = new JavaSequence(JavaCardinality.ONE, JAVA_17_0_0_2_0_0_2_0_0_1_0_0_0, JAVA_17_0_0_2_0_0_2_0_0_1_0_0_1);
	public final static JavaChoice JAVA_17_0_0_2_0_0_2_0_0_1_0 = new JavaChoice(JavaCardinality.ONE, JAVA_17_0_0_2_0_0_2_0_0_1_0_0);
	public final static JavaCompound JAVA_17_0_0_2_0_0_2_0_0_1 = new JavaCompound(JAVA_17_0_0_2_0_0_2_0_0_1_0, JavaCardinality.STAR);
	public final static JavaSequence JAVA_17_0_0_2_0_0_2_0_0 = new JavaSequence(JavaCardinality.ONE, JAVA_17_0_0_2_0_0_2_0_0_0, JAVA_17_0_0_2_0_0_2_0_0_1);
	public final static JavaChoice JAVA_17_0_0_2_0_0_2_0 = new JavaChoice(JavaCardinality.ONE, JAVA_17_0_0_2_0_0_2_0_0);
	public final static JavaCompound JAVA_17_0_0_2_0_0_2 = new JavaCompound(JAVA_17_0_0_2_0_0_2_0, JavaCardinality.QUESTIONMARK);
	public final static JavaKeyword JAVA_17_0_0_2_0_0_3 = new JavaKeyword(")", JavaCardinality.ONE);
	public final static JavaSequence JAVA_17_0_0_2_0_0 = new JavaSequence(JavaCardinality.ONE, JAVA_17_0_0_2_0_0_0, JAVA_17_0_0_2_0_0_1, JAVA_17_0_0_2_0_0_2, JAVA_17_0_0_2_0_0_3);
	public final static JavaChoice JAVA_17_0_0_2_0 = new JavaChoice(JavaCardinality.ONE, JAVA_17_0_0_2_0_0);
	public final static JavaCompound JAVA_17_0_0_2 = new JavaCompound(JAVA_17_0_0_2_0, JavaCardinality.QUESTIONMARK);
	public final static JavaContainment JAVA_17_0_0_3_0_0_0 = new JavaContainment(org.emftext.language.java.members.MembersPackage.eINSTANCE.getEnumConstant().getEStructuralFeature(org.emftext.language.java.members.MembersPackage.ENUM_CONSTANT__ANONYMOUS_CLASS), JavaCardinality.ONE, new org.eclipse.emf.ecore.EClass[] {org.emftext.language.java.classifiers.ClassifiersPackage.eINSTANCE.getAnonymousClass(), }, 0);
	public final static JavaSequence JAVA_17_0_0_3_0_0 = new JavaSequence(JavaCardinality.ONE, JAVA_17_0_0_3_0_0_0);
	public final static JavaChoice JAVA_17_0_0_3_0 = new JavaChoice(JavaCardinality.ONE, JAVA_17_0_0_3_0_0);
	public final static JavaCompound JAVA_17_0_0_3 = new JavaCompound(JAVA_17_0_0_3_0, JavaCardinality.QUESTIONMARK);
	public final static JavaSequence JAVA_17_0_0 = new JavaSequence(JavaCardinality.ONE, JAVA_17_0_0_0, JAVA_17_0_0_1, JAVA_17_0_0_2, JAVA_17_0_0_3);
	public final static JavaChoice JAVA_17_0 = new JavaChoice(JavaCardinality.ONE, JAVA_17_0_0);
	public final static JavaRule JAVA_17 = new JavaRule(org.emftext.language.java.members.MembersPackage.eINSTANCE.getEnumConstant(), JAVA_17_0, JavaCardinality.ONE);
	public final static JavaContainment JAVA_18_0_0_0 = new JavaContainment(org.emftext.language.java.statements.StatementsPackage.eINSTANCE.getBlock().getEStructuralFeature(org.emftext.language.java.statements.StatementsPackage.BLOCK__MODIFIERS), JavaCardinality.STAR, new org.eclipse.emf.ecore.EClass[] {org.emftext.language.java.modifiers.ModifiersPackage.eINSTANCE.getModifier(), }, 0);
	public final static JavaWhiteSpace JAVA_18_0_0_1 = new JavaWhiteSpace(1, JavaCardinality.ONE);
	public final static JavaKeyword JAVA_18_0_0_2 = new JavaKeyword("{", JavaCardinality.ONE);
	public final static JavaLineBreak JAVA_18_0_0_3_0_0_0 = new JavaLineBreak(JavaCardinality.ONE, 1);
	public final static JavaContainment JAVA_18_0_0_3_0_0_1 = new JavaContainment(org.emftext.language.java.statements.StatementsPackage.eINSTANCE.getBlock().getEStructuralFeature(org.emftext.language.java.statements.StatementsPackage.BLOCK__STATEMENTS), JavaCardinality.ONE, new org.eclipse.emf.ecore.EClass[] {org.emftext.language.java.statements.StatementsPackage.eINSTANCE.getStatement(), }, 0);
	public final static JavaSequence JAVA_18_0_0_3_0_0 = new JavaSequence(JavaCardinality.ONE, JAVA_18_0_0_3_0_0_0, JAVA_18_0_0_3_0_0_1);
	public final static JavaChoice JAVA_18_0_0_3_0 = new JavaChoice(JavaCardinality.ONE, JAVA_18_0_0_3_0_0);
	public final static JavaCompound JAVA_18_0_0_3 = new JavaCompound(JAVA_18_0_0_3_0, JavaCardinality.STAR);
	public final static JavaLineBreak JAVA_18_0_0_4 = new JavaLineBreak(JavaCardinality.ONE, 0);
	public final static JavaKeyword JAVA_18_0_0_5 = new JavaKeyword("}", JavaCardinality.ONE);
	public final static JavaSequence JAVA_18_0_0 = new JavaSequence(JavaCardinality.ONE, JAVA_18_0_0_0, JAVA_18_0_0_1, JAVA_18_0_0_2, JAVA_18_0_0_3, JAVA_18_0_0_4, JAVA_18_0_0_5);
	public final static JavaChoice JAVA_18_0 = new JavaChoice(JavaCardinality.ONE, JAVA_18_0_0);
	public final static JavaRule JAVA_18 = new JavaRule(org.emftext.language.java.statements.StatementsPackage.eINSTANCE.getBlock(), JAVA_18_0, JavaCardinality.ONE);
	public final static JavaContainment JAVA_19_0_0_0 = new JavaContainment(org.emftext.language.java.members.MembersPackage.eINSTANCE.getConstructor().getEStructuralFeature(org.emftext.language.java.members.MembersPackage.CONSTRUCTOR__ANNOTATIONS_AND_MODIFIERS), JavaCardinality.STAR, new org.eclipse.emf.ecore.EClass[] {org.emftext.language.java.modifiers.ModifiersPackage.eINSTANCE.getAnnotationInstanceOrModifier(), }, 0);
	public final static JavaKeyword JAVA_19_0_0_1_0_0_0 = new JavaKeyword("<", JavaCardinality.ONE);
	public final static JavaContainment JAVA_19_0_0_1_0_0_1 = new JavaContainment(org.emftext.language.java.members.MembersPackage.eINSTANCE.getConstructor().getEStructuralFeature(org.emftext.language.java.members.MembersPackage.CONSTRUCTOR__TYPE_PARAMETERS), JavaCardinality.ONE, new org.eclipse.emf.ecore.EClass[] {org.emftext.language.java.generics.GenericsPackage.eINSTANCE.getTypeParameter(), }, 0);
	public final static JavaKeyword JAVA_19_0_0_1_0_0_2_0_0_0 = new JavaKeyword(",", JavaCardinality.ONE);
	public final static JavaContainment JAVA_19_0_0_1_0_0_2_0_0_1 = new JavaContainment(org.emftext.language.java.members.MembersPackage.eINSTANCE.getConstructor().getEStructuralFeature(org.emftext.language.java.members.MembersPackage.CONSTRUCTOR__TYPE_PARAMETERS), JavaCardinality.ONE, new org.eclipse.emf.ecore.EClass[] {org.emftext.language.java.generics.GenericsPackage.eINSTANCE.getTypeParameter(), }, 0);
	public final static JavaSequence JAVA_19_0_0_1_0_0_2_0_0 = new JavaSequence(JavaCardinality.ONE, JAVA_19_0_0_1_0_0_2_0_0_0, JAVA_19_0_0_1_0_0_2_0_0_1);
	public final static JavaChoice JAVA_19_0_0_1_0_0_2_0 = new JavaChoice(JavaCardinality.ONE, JAVA_19_0_0_1_0_0_2_0_0);
	public final static JavaCompound JAVA_19_0_0_1_0_0_2 = new JavaCompound(JAVA_19_0_0_1_0_0_2_0, JavaCardinality.STAR);
	public final static JavaKeyword JAVA_19_0_0_1_0_0_3 = new JavaKeyword(">", JavaCardinality.ONE);
	public final static JavaSequence JAVA_19_0_0_1_0_0 = new JavaSequence(JavaCardinality.ONE, JAVA_19_0_0_1_0_0_0, JAVA_19_0_0_1_0_0_1, JAVA_19_0_0_1_0_0_2, JAVA_19_0_0_1_0_0_3);
	public final static JavaChoice JAVA_19_0_0_1_0 = new JavaChoice(JavaCardinality.ONE, JAVA_19_0_0_1_0_0);
	public final static JavaCompound JAVA_19_0_0_1 = new JavaCompound(JAVA_19_0_0_1_0, JavaCardinality.QUESTIONMARK);
	public final static JavaPlaceholder JAVA_19_0_0_2 = new JavaPlaceholder(org.emftext.language.java.members.MembersPackage.eINSTANCE.getConstructor().getEStructuralFeature(org.emftext.language.java.members.MembersPackage.CONSTRUCTOR__NAME), "IDENTIFIER", JavaCardinality.ONE, 0);
	public final static JavaKeyword JAVA_19_0_0_3 = new JavaKeyword("(", JavaCardinality.ONE);
	public final static JavaContainment JAVA_19_0_0_4_0_0_0 = new JavaContainment(org.emftext.language.java.members.MembersPackage.eINSTANCE.getConstructor().getEStructuralFeature(org.emftext.language.java.members.MembersPackage.CONSTRUCTOR__PARAMETERS), JavaCardinality.ONE, new org.eclipse.emf.ecore.EClass[] {org.emftext.language.java.parameters.ParametersPackage.eINSTANCE.getParameter(), }, 0);
	public final static JavaKeyword JAVA_19_0_0_4_0_0_1_0_0_0 = new JavaKeyword(",", JavaCardinality.ONE);
	public final static JavaContainment JAVA_19_0_0_4_0_0_1_0_0_1 = new JavaContainment(org.emftext.language.java.members.MembersPackage.eINSTANCE.getConstructor().getEStructuralFeature(org.emftext.language.java.members.MembersPackage.CONSTRUCTOR__PARAMETERS), JavaCardinality.ONE, new org.eclipse.emf.ecore.EClass[] {org.emftext.language.java.parameters.ParametersPackage.eINSTANCE.getParameter(), }, 0);
	public final static JavaSequence JAVA_19_0_0_4_0_0_1_0_0 = new JavaSequence(JavaCardinality.ONE, JAVA_19_0_0_4_0_0_1_0_0_0, JAVA_19_0_0_4_0_0_1_0_0_1);
	public final static JavaChoice JAVA_19_0_0_4_0_0_1_0 = new JavaChoice(JavaCardinality.ONE, JAVA_19_0_0_4_0_0_1_0_0);
	public final static JavaCompound JAVA_19_0_0_4_0_0_1 = new JavaCompound(JAVA_19_0_0_4_0_0_1_0, JavaCardinality.STAR);
	public final static JavaSequence JAVA_19_0_0_4_0_0 = new JavaSequence(JavaCardinality.ONE, JAVA_19_0_0_4_0_0_0, JAVA_19_0_0_4_0_0_1);
	public final static JavaChoice JAVA_19_0_0_4_0 = new JavaChoice(JavaCardinality.ONE, JAVA_19_0_0_4_0_0);
	public final static JavaCompound JAVA_19_0_0_4 = new JavaCompound(JAVA_19_0_0_4_0, JavaCardinality.QUESTIONMARK);
	public final static JavaKeyword JAVA_19_0_0_5 = new JavaKeyword(")", JavaCardinality.ONE);
	public final static JavaKeyword JAVA_19_0_0_6_0_0_0 = new JavaKeyword("throws", JavaCardinality.ONE);
	public final static JavaContainment JAVA_19_0_0_6_0_0_1 = new JavaContainment(org.emftext.language.java.members.MembersPackage.eINSTANCE.getConstructor().getEStructuralFeature(org.emftext.language.java.members.MembersPackage.CONSTRUCTOR__EXCEPTIONS), JavaCardinality.ONE, new org.eclipse.emf.ecore.EClass[] {org.emftext.language.java.types.TypesPackage.eINSTANCE.getNamespaceClassifierReference(), }, 0);
	public final static JavaKeyword JAVA_19_0_0_6_0_0_2_0_0_0 = new JavaKeyword(",", JavaCardinality.ONE);
	public final static JavaContainment JAVA_19_0_0_6_0_0_2_0_0_1 = new JavaContainment(org.emftext.language.java.members.MembersPackage.eINSTANCE.getConstructor().getEStructuralFeature(org.emftext.language.java.members.MembersPackage.CONSTRUCTOR__EXCEPTIONS), JavaCardinality.ONE, new org.eclipse.emf.ecore.EClass[] {org.emftext.language.java.types.TypesPackage.eINSTANCE.getNamespaceClassifierReference(), }, 0);
	public final static JavaSequence JAVA_19_0_0_6_0_0_2_0_0 = new JavaSequence(JavaCardinality.ONE, JAVA_19_0_0_6_0_0_2_0_0_0, JAVA_19_0_0_6_0_0_2_0_0_1);
	public final static JavaChoice JAVA_19_0_0_6_0_0_2_0 = new JavaChoice(JavaCardinality.ONE, JAVA_19_0_0_6_0_0_2_0_0);
	public final static JavaCompound JAVA_19_0_0_6_0_0_2 = new JavaCompound(JAVA_19_0_0_6_0_0_2_0, JavaCardinality.STAR);
	public final static JavaSequence JAVA_19_0_0_6_0_0 = new JavaSequence(JavaCardinality.ONE, JAVA_19_0_0_6_0_0_0, JAVA_19_0_0_6_0_0_1, JAVA_19_0_0_6_0_0_2);
	public final static JavaChoice JAVA_19_0_0_6_0 = new JavaChoice(JavaCardinality.ONE, JAVA_19_0_0_6_0_0);
	public final static JavaCompound JAVA_19_0_0_6 = new JavaCompound(JAVA_19_0_0_6_0, JavaCardinality.QUESTIONMARK);
	public final static JavaWhiteSpace JAVA_19_0_0_7 = new JavaWhiteSpace(1, JavaCardinality.ONE);
	public final static JavaKeyword JAVA_19_0_0_8 = new JavaKeyword("{", JavaCardinality.ONE);
	public final static JavaLineBreak JAVA_19_0_0_9_0_0_0 = new JavaLineBreak(JavaCardinality.ONE, 2);
	public final static JavaContainment JAVA_19_0_0_9_0_0_1 = new JavaContainment(org.emftext.language.java.members.MembersPackage.eINSTANCE.getConstructor().getEStructuralFeature(org.emftext.language.java.members.MembersPackage.CONSTRUCTOR__STATEMENTS), JavaCardinality.ONE, new org.eclipse.emf.ecore.EClass[] {org.emftext.language.java.statements.StatementsPackage.eINSTANCE.getStatement(), }, 0);
	public final static JavaSequence JAVA_19_0_0_9_0_0 = new JavaSequence(JavaCardinality.ONE, JAVA_19_0_0_9_0_0_0, JAVA_19_0_0_9_0_0_1);
	public final static JavaChoice JAVA_19_0_0_9_0 = new JavaChoice(JavaCardinality.ONE, JAVA_19_0_0_9_0_0);
	public final static JavaCompound JAVA_19_0_0_9 = new JavaCompound(JAVA_19_0_0_9_0, JavaCardinality.STAR);
	public final static JavaLineBreak JAVA_19_0_0_10 = new JavaLineBreak(JavaCardinality.ONE, 1);
	public final static JavaKeyword JAVA_19_0_0_11 = new JavaKeyword("}", JavaCardinality.ONE);
	public final static JavaSequence JAVA_19_0_0 = new JavaSequence(JavaCardinality.ONE, JAVA_19_0_0_0, JAVA_19_0_0_1, JAVA_19_0_0_2, JAVA_19_0_0_3, JAVA_19_0_0_4, JAVA_19_0_0_5, JAVA_19_0_0_6, JAVA_19_0_0_7, JAVA_19_0_0_8, JAVA_19_0_0_9, JAVA_19_0_0_10, JAVA_19_0_0_11);
	public final static JavaChoice JAVA_19_0 = new JavaChoice(JavaCardinality.ONE, JAVA_19_0_0);
	public final static JavaRule JAVA_19 = new JavaRule(org.emftext.language.java.members.MembersPackage.eINSTANCE.getConstructor(), JAVA_19_0, JavaCardinality.ONE);
	public final static JavaContainment JAVA_20_0_0_0 = new JavaContainment(org.emftext.language.java.members.MembersPackage.eINSTANCE.getInterfaceMethod().getEStructuralFeature(org.emftext.language.java.members.MembersPackage.INTERFACE_METHOD__ANNOTATIONS_AND_MODIFIERS), JavaCardinality.STAR, new org.eclipse.emf.ecore.EClass[] {org.emftext.language.java.modifiers.ModifiersPackage.eINSTANCE.getAnnotationInstanceOrModifier(), }, 0);
	public final static JavaKeyword JAVA_20_0_0_1_0_0_0 = new JavaKeyword("<", JavaCardinality.ONE);
	public final static JavaContainment JAVA_20_0_0_1_0_0_1 = new JavaContainment(org.emftext.language.java.members.MembersPackage.eINSTANCE.getInterfaceMethod().getEStructuralFeature(org.emftext.language.java.members.MembersPackage.INTERFACE_METHOD__TYPE_PARAMETERS), JavaCardinality.ONE, new org.eclipse.emf.ecore.EClass[] {org.emftext.language.java.generics.GenericsPackage.eINSTANCE.getTypeParameter(), }, 0);
	public final static JavaKeyword JAVA_20_0_0_1_0_0_2_0_0_0 = new JavaKeyword(",", JavaCardinality.ONE);
	public final static JavaContainment JAVA_20_0_0_1_0_0_2_0_0_1 = new JavaContainment(org.emftext.language.java.members.MembersPackage.eINSTANCE.getInterfaceMethod().getEStructuralFeature(org.emftext.language.java.members.MembersPackage.INTERFACE_METHOD__TYPE_PARAMETERS), JavaCardinality.ONE, new org.eclipse.emf.ecore.EClass[] {org.emftext.language.java.generics.GenericsPackage.eINSTANCE.getTypeParameter(), }, 0);
	public final static JavaSequence JAVA_20_0_0_1_0_0_2_0_0 = new JavaSequence(JavaCardinality.ONE, JAVA_20_0_0_1_0_0_2_0_0_0, JAVA_20_0_0_1_0_0_2_0_0_1);
	public final static JavaChoice JAVA_20_0_0_1_0_0_2_0 = new JavaChoice(JavaCardinality.ONE, JAVA_20_0_0_1_0_0_2_0_0);
	public final static JavaCompound JAVA_20_0_0_1_0_0_2 = new JavaCompound(JAVA_20_0_0_1_0_0_2_0, JavaCardinality.STAR);
	public final static JavaKeyword JAVA_20_0_0_1_0_0_3 = new JavaKeyword(">", JavaCardinality.ONE);
	public final static JavaSequence JAVA_20_0_0_1_0_0 = new JavaSequence(JavaCardinality.ONE, JAVA_20_0_0_1_0_0_0, JAVA_20_0_0_1_0_0_1, JAVA_20_0_0_1_0_0_2, JAVA_20_0_0_1_0_0_3);
	public final static JavaChoice JAVA_20_0_0_1_0 = new JavaChoice(JavaCardinality.ONE, JAVA_20_0_0_1_0_0);
	public final static JavaCompound JAVA_20_0_0_1 = new JavaCompound(JAVA_20_0_0_1_0, JavaCardinality.QUESTIONMARK);
	public final static JavaContainment JAVA_20_0_0_2_0_0_0 = new JavaContainment(org.emftext.language.java.members.MembersPackage.eINSTANCE.getInterfaceMethod().getEStructuralFeature(org.emftext.language.java.members.MembersPackage.INTERFACE_METHOD__TYPE_REFERENCE), JavaCardinality.ONE, new org.eclipse.emf.ecore.EClass[] {org.emftext.language.java.types.TypesPackage.eINSTANCE.getTypeReference(), }, 0);
	public final static JavaContainment JAVA_20_0_0_2_0_0_1 = new JavaContainment(org.emftext.language.java.members.MembersPackage.eINSTANCE.getInterfaceMethod().getEStructuralFeature(org.emftext.language.java.members.MembersPackage.INTERFACE_METHOD__ARRAY_DIMENSIONS_BEFORE), JavaCardinality.STAR, new org.eclipse.emf.ecore.EClass[] {org.emftext.language.java.arrays.ArraysPackage.eINSTANCE.getArrayDimension(), }, 0);
	public final static JavaSequence JAVA_20_0_0_2_0_0 = new JavaSequence(JavaCardinality.ONE, JAVA_20_0_0_2_0_0_0, JAVA_20_0_0_2_0_0_1);
	public final static JavaChoice JAVA_20_0_0_2_0 = new JavaChoice(JavaCardinality.ONE, JAVA_20_0_0_2_0_0);
	public final static JavaCompound JAVA_20_0_0_2 = new JavaCompound(JAVA_20_0_0_2_0, JavaCardinality.ONE);
	public final static JavaPlaceholder JAVA_20_0_0_3 = new JavaPlaceholder(org.emftext.language.java.members.MembersPackage.eINSTANCE.getInterfaceMethod().getEStructuralFeature(org.emftext.language.java.members.MembersPackage.INTERFACE_METHOD__NAME), "IDENTIFIER", JavaCardinality.ONE, 0);
	public final static JavaKeyword JAVA_20_0_0_4 = new JavaKeyword("(", JavaCardinality.ONE);
	public final static JavaContainment JAVA_20_0_0_5_0_0_0 = new JavaContainment(org.emftext.language.java.members.MembersPackage.eINSTANCE.getInterfaceMethod().getEStructuralFeature(org.emftext.language.java.members.MembersPackage.INTERFACE_METHOD__PARAMETERS), JavaCardinality.ONE, new org.eclipse.emf.ecore.EClass[] {org.emftext.language.java.parameters.ParametersPackage.eINSTANCE.getParameter(), }, 0);
	public final static JavaKeyword JAVA_20_0_0_5_0_0_1_0_0_0 = new JavaKeyword(",", JavaCardinality.ONE);
	public final static JavaContainment JAVA_20_0_0_5_0_0_1_0_0_1 = new JavaContainment(org.emftext.language.java.members.MembersPackage.eINSTANCE.getInterfaceMethod().getEStructuralFeature(org.emftext.language.java.members.MembersPackage.INTERFACE_METHOD__PARAMETERS), JavaCardinality.ONE, new org.eclipse.emf.ecore.EClass[] {org.emftext.language.java.parameters.ParametersPackage.eINSTANCE.getParameter(), }, 0);
	public final static JavaSequence JAVA_20_0_0_5_0_0_1_0_0 = new JavaSequence(JavaCardinality.ONE, JAVA_20_0_0_5_0_0_1_0_0_0, JAVA_20_0_0_5_0_0_1_0_0_1);
	public final static JavaChoice JAVA_20_0_0_5_0_0_1_0 = new JavaChoice(JavaCardinality.ONE, JAVA_20_0_0_5_0_0_1_0_0);
	public final static JavaCompound JAVA_20_0_0_5_0_0_1 = new JavaCompound(JAVA_20_0_0_5_0_0_1_0, JavaCardinality.STAR);
	public final static JavaSequence JAVA_20_0_0_5_0_0 = new JavaSequence(JavaCardinality.ONE, JAVA_20_0_0_5_0_0_0, JAVA_20_0_0_5_0_0_1);
	public final static JavaChoice JAVA_20_0_0_5_0 = new JavaChoice(JavaCardinality.ONE, JAVA_20_0_0_5_0_0);
	public final static JavaCompound JAVA_20_0_0_5 = new JavaCompound(JAVA_20_0_0_5_0, JavaCardinality.QUESTIONMARK);
	public final static JavaKeyword JAVA_20_0_0_6 = new JavaKeyword(")", JavaCardinality.ONE);
	public final static JavaContainment JAVA_20_0_0_7 = new JavaContainment(org.emftext.language.java.members.MembersPackage.eINSTANCE.getInterfaceMethod().getEStructuralFeature(org.emftext.language.java.members.MembersPackage.INTERFACE_METHOD__ARRAY_DIMENSIONS_AFTER), JavaCardinality.STAR, new org.eclipse.emf.ecore.EClass[] {org.emftext.language.java.arrays.ArraysPackage.eINSTANCE.getArrayDimension(), }, 0);
	public final static JavaKeyword JAVA_20_0_0_8_0_0_0 = new JavaKeyword("throws", JavaCardinality.ONE);
	public final static JavaContainment JAVA_20_0_0_8_0_0_1 = new JavaContainment(org.emftext.language.java.members.MembersPackage.eINSTANCE.getInterfaceMethod().getEStructuralFeature(org.emftext.language.java.members.MembersPackage.INTERFACE_METHOD__EXCEPTIONS), JavaCardinality.ONE, new org.eclipse.emf.ecore.EClass[] {org.emftext.language.java.types.TypesPackage.eINSTANCE.getNamespaceClassifierReference(), }, 0);
	public final static JavaKeyword JAVA_20_0_0_8_0_0_2_0_0_0 = new JavaKeyword(",", JavaCardinality.ONE);
	public final static JavaContainment JAVA_20_0_0_8_0_0_2_0_0_1 = new JavaContainment(org.emftext.language.java.members.MembersPackage.eINSTANCE.getInterfaceMethod().getEStructuralFeature(org.emftext.language.java.members.MembersPackage.INTERFACE_METHOD__EXCEPTIONS), JavaCardinality.ONE, new org.eclipse.emf.ecore.EClass[] {org.emftext.language.java.types.TypesPackage.eINSTANCE.getNamespaceClassifierReference(), }, 0);
	public final static JavaSequence JAVA_20_0_0_8_0_0_2_0_0 = new JavaSequence(JavaCardinality.ONE, JAVA_20_0_0_8_0_0_2_0_0_0, JAVA_20_0_0_8_0_0_2_0_0_1);
	public final static JavaChoice JAVA_20_0_0_8_0_0_2_0 = new JavaChoice(JavaCardinality.ONE, JAVA_20_0_0_8_0_0_2_0_0);
	public final static JavaCompound JAVA_20_0_0_8_0_0_2 = new JavaCompound(JAVA_20_0_0_8_0_0_2_0, JavaCardinality.STAR);
	public final static JavaSequence JAVA_20_0_0_8_0_0 = new JavaSequence(JavaCardinality.ONE, JAVA_20_0_0_8_0_0_0, JAVA_20_0_0_8_0_0_1, JAVA_20_0_0_8_0_0_2);
	public final static JavaChoice JAVA_20_0_0_8_0 = new JavaChoice(JavaCardinality.ONE, JAVA_20_0_0_8_0_0);
	public final static JavaCompound JAVA_20_0_0_8 = new JavaCompound(JAVA_20_0_0_8_0, JavaCardinality.QUESTIONMARK);
	public final static JavaKeyword JAVA_20_0_0_9 = new JavaKeyword(";", JavaCardinality.ONE);
	public final static JavaSequence JAVA_20_0_0 = new JavaSequence(JavaCardinality.ONE, JAVA_20_0_0_0, JAVA_20_0_0_1, JAVA_20_0_0_2, JAVA_20_0_0_3, JAVA_20_0_0_4, JAVA_20_0_0_5, JAVA_20_0_0_6, JAVA_20_0_0_7, JAVA_20_0_0_8, JAVA_20_0_0_9);
	public final static JavaChoice JAVA_20_0 = new JavaChoice(JavaCardinality.ONE, JAVA_20_0_0);
	public final static JavaRule JAVA_20 = new JavaRule(org.emftext.language.java.members.MembersPackage.eINSTANCE.getInterfaceMethod(), JAVA_20_0, JavaCardinality.ONE);
	public final static JavaContainment JAVA_21_0_0_0 = new JavaContainment(org.emftext.language.java.members.MembersPackage.eINSTANCE.getClassMethod().getEStructuralFeature(org.emftext.language.java.members.MembersPackage.CLASS_METHOD__ANNOTATIONS_AND_MODIFIERS), JavaCardinality.STAR, new org.eclipse.emf.ecore.EClass[] {org.emftext.language.java.modifiers.ModifiersPackage.eINSTANCE.getAnnotationInstanceOrModifier(), }, 0);
	public final static JavaKeyword JAVA_21_0_0_1_0_0_0 = new JavaKeyword("<", JavaCardinality.ONE);
	public final static JavaContainment JAVA_21_0_0_1_0_0_1 = new JavaContainment(org.emftext.language.java.members.MembersPackage.eINSTANCE.getClassMethod().getEStructuralFeature(org.emftext.language.java.members.MembersPackage.CLASS_METHOD__TYPE_PARAMETERS), JavaCardinality.ONE, new org.eclipse.emf.ecore.EClass[] {org.emftext.language.java.generics.GenericsPackage.eINSTANCE.getTypeParameter(), }, 0);
	public final static JavaKeyword JAVA_21_0_0_1_0_0_2_0_0_0 = new JavaKeyword(",", JavaCardinality.ONE);
	public final static JavaContainment JAVA_21_0_0_1_0_0_2_0_0_1 = new JavaContainment(org.emftext.language.java.members.MembersPackage.eINSTANCE.getClassMethod().getEStructuralFeature(org.emftext.language.java.members.MembersPackage.CLASS_METHOD__TYPE_PARAMETERS), JavaCardinality.ONE, new org.eclipse.emf.ecore.EClass[] {org.emftext.language.java.generics.GenericsPackage.eINSTANCE.getTypeParameter(), }, 0);
	public final static JavaSequence JAVA_21_0_0_1_0_0_2_0_0 = new JavaSequence(JavaCardinality.ONE, JAVA_21_0_0_1_0_0_2_0_0_0, JAVA_21_0_0_1_0_0_2_0_0_1);
	public final static JavaChoice JAVA_21_0_0_1_0_0_2_0 = new JavaChoice(JavaCardinality.ONE, JAVA_21_0_0_1_0_0_2_0_0);
	public final static JavaCompound JAVA_21_0_0_1_0_0_2 = new JavaCompound(JAVA_21_0_0_1_0_0_2_0, JavaCardinality.STAR);
	public final static JavaKeyword JAVA_21_0_0_1_0_0_3 = new JavaKeyword(">", JavaCardinality.ONE);
	public final static JavaSequence JAVA_21_0_0_1_0_0 = new JavaSequence(JavaCardinality.ONE, JAVA_21_0_0_1_0_0_0, JAVA_21_0_0_1_0_0_1, JAVA_21_0_0_1_0_0_2, JAVA_21_0_0_1_0_0_3);
	public final static JavaChoice JAVA_21_0_0_1_0 = new JavaChoice(JavaCardinality.ONE, JAVA_21_0_0_1_0_0);
	public final static JavaCompound JAVA_21_0_0_1 = new JavaCompound(JAVA_21_0_0_1_0, JavaCardinality.QUESTIONMARK);
	public final static JavaContainment JAVA_21_0_0_2_0_0_0 = new JavaContainment(org.emftext.language.java.members.MembersPackage.eINSTANCE.getClassMethod().getEStructuralFeature(org.emftext.language.java.members.MembersPackage.CLASS_METHOD__TYPE_REFERENCE), JavaCardinality.ONE, new org.eclipse.emf.ecore.EClass[] {org.emftext.language.java.types.TypesPackage.eINSTANCE.getTypeReference(), }, 0);
	public final static JavaContainment JAVA_21_0_0_2_0_0_1 = new JavaContainment(org.emftext.language.java.members.MembersPackage.eINSTANCE.getClassMethod().getEStructuralFeature(org.emftext.language.java.members.MembersPackage.CLASS_METHOD__ARRAY_DIMENSIONS_BEFORE), JavaCardinality.STAR, new org.eclipse.emf.ecore.EClass[] {org.emftext.language.java.arrays.ArraysPackage.eINSTANCE.getArrayDimension(), }, 0);
	public final static JavaSequence JAVA_21_0_0_2_0_0 = new JavaSequence(JavaCardinality.ONE, JAVA_21_0_0_2_0_0_0, JAVA_21_0_0_2_0_0_1);
	public final static JavaChoice JAVA_21_0_0_2_0 = new JavaChoice(JavaCardinality.ONE, JAVA_21_0_0_2_0_0);
	public final static JavaCompound JAVA_21_0_0_2 = new JavaCompound(JAVA_21_0_0_2_0, JavaCardinality.ONE);
	public final static JavaPlaceholder JAVA_21_0_0_3 = new JavaPlaceholder(org.emftext.language.java.members.MembersPackage.eINSTANCE.getClassMethod().getEStructuralFeature(org.emftext.language.java.members.MembersPackage.CLASS_METHOD__NAME), "IDENTIFIER", JavaCardinality.ONE, 0);
	public final static JavaKeyword JAVA_21_0_0_4 = new JavaKeyword("(", JavaCardinality.ONE);
	public final static JavaContainment JAVA_21_0_0_5_0_0_0 = new JavaContainment(org.emftext.language.java.members.MembersPackage.eINSTANCE.getClassMethod().getEStructuralFeature(org.emftext.language.java.members.MembersPackage.CLASS_METHOD__PARAMETERS), JavaCardinality.ONE, new org.eclipse.emf.ecore.EClass[] {org.emftext.language.java.parameters.ParametersPackage.eINSTANCE.getParameter(), }, 0);
	public final static JavaKeyword JAVA_21_0_0_5_0_0_1_0_0_0 = new JavaKeyword(",", JavaCardinality.ONE);
	public final static JavaContainment JAVA_21_0_0_5_0_0_1_0_0_1 = new JavaContainment(org.emftext.language.java.members.MembersPackage.eINSTANCE.getClassMethod().getEStructuralFeature(org.emftext.language.java.members.MembersPackage.CLASS_METHOD__PARAMETERS), JavaCardinality.ONE, new org.eclipse.emf.ecore.EClass[] {org.emftext.language.java.parameters.ParametersPackage.eINSTANCE.getParameter(), }, 0);
	public final static JavaSequence JAVA_21_0_0_5_0_0_1_0_0 = new JavaSequence(JavaCardinality.ONE, JAVA_21_0_0_5_0_0_1_0_0_0, JAVA_21_0_0_5_0_0_1_0_0_1);
	public final static JavaChoice JAVA_21_0_0_5_0_0_1_0 = new JavaChoice(JavaCardinality.ONE, JAVA_21_0_0_5_0_0_1_0_0);
	public final static JavaCompound JAVA_21_0_0_5_0_0_1 = new JavaCompound(JAVA_21_0_0_5_0_0_1_0, JavaCardinality.STAR);
	public final static JavaSequence JAVA_21_0_0_5_0_0 = new JavaSequence(JavaCardinality.ONE, JAVA_21_0_0_5_0_0_0, JAVA_21_0_0_5_0_0_1);
	public final static JavaChoice JAVA_21_0_0_5_0 = new JavaChoice(JavaCardinality.ONE, JAVA_21_0_0_5_0_0);
	public final static JavaCompound JAVA_21_0_0_5 = new JavaCompound(JAVA_21_0_0_5_0, JavaCardinality.QUESTIONMARK);
	public final static JavaKeyword JAVA_21_0_0_6 = new JavaKeyword(")", JavaCardinality.ONE);
	public final static JavaContainment JAVA_21_0_0_7 = new JavaContainment(org.emftext.language.java.members.MembersPackage.eINSTANCE.getClassMethod().getEStructuralFeature(org.emftext.language.java.members.MembersPackage.CLASS_METHOD__ARRAY_DIMENSIONS_AFTER), JavaCardinality.STAR, new org.eclipse.emf.ecore.EClass[] {org.emftext.language.java.arrays.ArraysPackage.eINSTANCE.getArrayDimension(), }, 0);
	public final static JavaKeyword JAVA_21_0_0_8_0_0_0 = new JavaKeyword("throws", JavaCardinality.ONE);
	public final static JavaContainment JAVA_21_0_0_8_0_0_1 = new JavaContainment(org.emftext.language.java.members.MembersPackage.eINSTANCE.getClassMethod().getEStructuralFeature(org.emftext.language.java.members.MembersPackage.CLASS_METHOD__EXCEPTIONS), JavaCardinality.ONE, new org.eclipse.emf.ecore.EClass[] {org.emftext.language.java.types.TypesPackage.eINSTANCE.getNamespaceClassifierReference(), }, 0);
	public final static JavaKeyword JAVA_21_0_0_8_0_0_2_0_0_0 = new JavaKeyword(",", JavaCardinality.ONE);
	public final static JavaContainment JAVA_21_0_0_8_0_0_2_0_0_1 = new JavaContainment(org.emftext.language.java.members.MembersPackage.eINSTANCE.getClassMethod().getEStructuralFeature(org.emftext.language.java.members.MembersPackage.CLASS_METHOD__EXCEPTIONS), JavaCardinality.ONE, new org.eclipse.emf.ecore.EClass[] {org.emftext.language.java.types.TypesPackage.eINSTANCE.getNamespaceClassifierReference(), }, 0);
	public final static JavaSequence JAVA_21_0_0_8_0_0_2_0_0 = new JavaSequence(JavaCardinality.ONE, JAVA_21_0_0_8_0_0_2_0_0_0, JAVA_21_0_0_8_0_0_2_0_0_1);
	public final static JavaChoice JAVA_21_0_0_8_0_0_2_0 = new JavaChoice(JavaCardinality.ONE, JAVA_21_0_0_8_0_0_2_0_0);
	public final static JavaCompound JAVA_21_0_0_8_0_0_2 = new JavaCompound(JAVA_21_0_0_8_0_0_2_0, JavaCardinality.STAR);
	public final static JavaSequence JAVA_21_0_0_8_0_0 = new JavaSequence(JavaCardinality.ONE, JAVA_21_0_0_8_0_0_0, JAVA_21_0_0_8_0_0_1, JAVA_21_0_0_8_0_0_2);
	public final static JavaChoice JAVA_21_0_0_8_0 = new JavaChoice(JavaCardinality.ONE, JAVA_21_0_0_8_0_0);
	public final static JavaCompound JAVA_21_0_0_8 = new JavaCompound(JAVA_21_0_0_8_0, JavaCardinality.QUESTIONMARK);
	public final static JavaWhiteSpace JAVA_21_0_0_9 = new JavaWhiteSpace(1, JavaCardinality.ONE);
	public final static JavaKeyword JAVA_21_0_0_10 = new JavaKeyword("{", JavaCardinality.ONE);
	public final static JavaLineBreak JAVA_21_0_0_11_0_0_0 = new JavaLineBreak(JavaCardinality.ONE, 2);
	public final static JavaContainment JAVA_21_0_0_11_0_0_1 = new JavaContainment(org.emftext.language.java.members.MembersPackage.eINSTANCE.getClassMethod().getEStructuralFeature(org.emftext.language.java.members.MembersPackage.CLASS_METHOD__STATEMENTS), JavaCardinality.ONE, new org.eclipse.emf.ecore.EClass[] {org.emftext.language.java.statements.StatementsPackage.eINSTANCE.getStatement(), }, 0);
	public final static JavaSequence JAVA_21_0_0_11_0_0 = new JavaSequence(JavaCardinality.ONE, JAVA_21_0_0_11_0_0_0, JAVA_21_0_0_11_0_0_1);
	public final static JavaChoice JAVA_21_0_0_11_0 = new JavaChoice(JavaCardinality.ONE, JAVA_21_0_0_11_0_0);
	public final static JavaCompound JAVA_21_0_0_11 = new JavaCompound(JAVA_21_0_0_11_0, JavaCardinality.STAR);
	public final static JavaLineBreak JAVA_21_0_0_12 = new JavaLineBreak(JavaCardinality.ONE, 1);
	public final static JavaKeyword JAVA_21_0_0_13 = new JavaKeyword("}", JavaCardinality.ONE);
	public final static JavaSequence JAVA_21_0_0 = new JavaSequence(JavaCardinality.ONE, JAVA_21_0_0_0, JAVA_21_0_0_1, JAVA_21_0_0_2, JAVA_21_0_0_3, JAVA_21_0_0_4, JAVA_21_0_0_5, JAVA_21_0_0_6, JAVA_21_0_0_7, JAVA_21_0_0_8, JAVA_21_0_0_9, JAVA_21_0_0_10, JAVA_21_0_0_11, JAVA_21_0_0_12, JAVA_21_0_0_13);
	public final static JavaChoice JAVA_21_0 = new JavaChoice(JavaCardinality.ONE, JAVA_21_0_0);
	public final static JavaRule JAVA_21 = new JavaRule(org.emftext.language.java.members.MembersPackage.eINSTANCE.getClassMethod(), JAVA_21_0, JavaCardinality.ONE);
	public final static JavaContainment JAVA_22_0_0_0 = new JavaContainment(org.emftext.language.java.annotations.AnnotationsPackage.eINSTANCE.getAnnotationAttribute().getEStructuralFeature(org.emftext.language.java.annotations.AnnotationsPackage.ANNOTATION_ATTRIBUTE__ANNOTATIONS_AND_MODIFIERS), JavaCardinality.STAR, new org.eclipse.emf.ecore.EClass[] {org.emftext.language.java.modifiers.ModifiersPackage.eINSTANCE.getAnnotationInstanceOrModifier(), }, 0);
	public final static JavaKeyword JAVA_22_0_0_1_0_0_0 = new JavaKeyword("<", JavaCardinality.ONE);
	public final static JavaContainment JAVA_22_0_0_1_0_0_1 = new JavaContainment(org.emftext.language.java.annotations.AnnotationsPackage.eINSTANCE.getAnnotationAttribute().getEStructuralFeature(org.emftext.language.java.annotations.AnnotationsPackage.ANNOTATION_ATTRIBUTE__TYPE_PARAMETERS), JavaCardinality.ONE, new org.eclipse.emf.ecore.EClass[] {org.emftext.language.java.generics.GenericsPackage.eINSTANCE.getTypeParameter(), }, 0);
	public final static JavaKeyword JAVA_22_0_0_1_0_0_2_0_0_0 = new JavaKeyword(",", JavaCardinality.ONE);
	public final static JavaContainment JAVA_22_0_0_1_0_0_2_0_0_1 = new JavaContainment(org.emftext.language.java.annotations.AnnotationsPackage.eINSTANCE.getAnnotationAttribute().getEStructuralFeature(org.emftext.language.java.annotations.AnnotationsPackage.ANNOTATION_ATTRIBUTE__TYPE_PARAMETERS), JavaCardinality.ONE, new org.eclipse.emf.ecore.EClass[] {org.emftext.language.java.generics.GenericsPackage.eINSTANCE.getTypeParameter(), }, 0);
	public final static JavaSequence JAVA_22_0_0_1_0_0_2_0_0 = new JavaSequence(JavaCardinality.ONE, JAVA_22_0_0_1_0_0_2_0_0_0, JAVA_22_0_0_1_0_0_2_0_0_1);
	public final static JavaChoice JAVA_22_0_0_1_0_0_2_0 = new JavaChoice(JavaCardinality.ONE, JAVA_22_0_0_1_0_0_2_0_0);
	public final static JavaCompound JAVA_22_0_0_1_0_0_2 = new JavaCompound(JAVA_22_0_0_1_0_0_2_0, JavaCardinality.STAR);
	public final static JavaKeyword JAVA_22_0_0_1_0_0_3 = new JavaKeyword(">", JavaCardinality.ONE);
	public final static JavaSequence JAVA_22_0_0_1_0_0 = new JavaSequence(JavaCardinality.ONE, JAVA_22_0_0_1_0_0_0, JAVA_22_0_0_1_0_0_1, JAVA_22_0_0_1_0_0_2, JAVA_22_0_0_1_0_0_3);
	public final static JavaChoice JAVA_22_0_0_1_0 = new JavaChoice(JavaCardinality.ONE, JAVA_22_0_0_1_0_0);
	public final static JavaCompound JAVA_22_0_0_1 = new JavaCompound(JAVA_22_0_0_1_0, JavaCardinality.QUESTIONMARK);
	public final static JavaContainment JAVA_22_0_0_2_0_0_0 = new JavaContainment(org.emftext.language.java.annotations.AnnotationsPackage.eINSTANCE.getAnnotationAttribute().getEStructuralFeature(org.emftext.language.java.annotations.AnnotationsPackage.ANNOTATION_ATTRIBUTE__TYPE_REFERENCE), JavaCardinality.ONE, new org.eclipse.emf.ecore.EClass[] {org.emftext.language.java.types.TypesPackage.eINSTANCE.getTypeReference(), }, 0);
	public final static JavaContainment JAVA_22_0_0_2_0_0_1 = new JavaContainment(org.emftext.language.java.annotations.AnnotationsPackage.eINSTANCE.getAnnotationAttribute().getEStructuralFeature(org.emftext.language.java.annotations.AnnotationsPackage.ANNOTATION_ATTRIBUTE__ARRAY_DIMENSIONS_BEFORE), JavaCardinality.STAR, new org.eclipse.emf.ecore.EClass[] {org.emftext.language.java.arrays.ArraysPackage.eINSTANCE.getArrayDimension(), }, 0);
	public final static JavaSequence JAVA_22_0_0_2_0_0 = new JavaSequence(JavaCardinality.ONE, JAVA_22_0_0_2_0_0_0, JAVA_22_0_0_2_0_0_1);
	public final static JavaChoice JAVA_22_0_0_2_0 = new JavaChoice(JavaCardinality.ONE, JAVA_22_0_0_2_0_0);
	public final static JavaCompound JAVA_22_0_0_2 = new JavaCompound(JAVA_22_0_0_2_0, JavaCardinality.ONE);
	public final static JavaPlaceholder JAVA_22_0_0_3 = new JavaPlaceholder(org.emftext.language.java.annotations.AnnotationsPackage.eINSTANCE.getAnnotationAttribute().getEStructuralFeature(org.emftext.language.java.annotations.AnnotationsPackage.ANNOTATION_ATTRIBUTE__NAME), "IDENTIFIER", JavaCardinality.ONE, 0);
	public final static JavaKeyword JAVA_22_0_0_4 = new JavaKeyword("(", JavaCardinality.ONE);
	public final static JavaContainment JAVA_22_0_0_5_0_0_0 = new JavaContainment(org.emftext.language.java.annotations.AnnotationsPackage.eINSTANCE.getAnnotationAttribute().getEStructuralFeature(org.emftext.language.java.annotations.AnnotationsPackage.ANNOTATION_ATTRIBUTE__PARAMETERS), JavaCardinality.ONE, new org.eclipse.emf.ecore.EClass[] {org.emftext.language.java.parameters.ParametersPackage.eINSTANCE.getParameter(), }, 0);
	public final static JavaKeyword JAVA_22_0_0_5_0_0_1_0_0_0 = new JavaKeyword(",", JavaCardinality.ONE);
	public final static JavaContainment JAVA_22_0_0_5_0_0_1_0_0_1 = new JavaContainment(org.emftext.language.java.annotations.AnnotationsPackage.eINSTANCE.getAnnotationAttribute().getEStructuralFeature(org.emftext.language.java.annotations.AnnotationsPackage.ANNOTATION_ATTRIBUTE__PARAMETERS), JavaCardinality.ONE, new org.eclipse.emf.ecore.EClass[] {org.emftext.language.java.parameters.ParametersPackage.eINSTANCE.getParameter(), }, 0);
	public final static JavaSequence JAVA_22_0_0_5_0_0_1_0_0 = new JavaSequence(JavaCardinality.ONE, JAVA_22_0_0_5_0_0_1_0_0_0, JAVA_22_0_0_5_0_0_1_0_0_1);
	public final static JavaChoice JAVA_22_0_0_5_0_0_1_0 = new JavaChoice(JavaCardinality.ONE, JAVA_22_0_0_5_0_0_1_0_0);
	public final static JavaCompound JAVA_22_0_0_5_0_0_1 = new JavaCompound(JAVA_22_0_0_5_0_0_1_0, JavaCardinality.STAR);
	public final static JavaSequence JAVA_22_0_0_5_0_0 = new JavaSequence(JavaCardinality.ONE, JAVA_22_0_0_5_0_0_0, JAVA_22_0_0_5_0_0_1);
	public final static JavaChoice JAVA_22_0_0_5_0 = new JavaChoice(JavaCardinality.ONE, JAVA_22_0_0_5_0_0);
	public final static JavaCompound JAVA_22_0_0_5 = new JavaCompound(JAVA_22_0_0_5_0, JavaCardinality.QUESTIONMARK);
	public final static JavaKeyword JAVA_22_0_0_6 = new JavaKeyword(")", JavaCardinality.ONE);
	public final static JavaContainment JAVA_22_0_0_7 = new JavaContainment(org.emftext.language.java.annotations.AnnotationsPackage.eINSTANCE.getAnnotationAttribute().getEStructuralFeature(org.emftext.language.java.annotations.AnnotationsPackage.ANNOTATION_ATTRIBUTE__ARRAY_DIMENSIONS_AFTER), JavaCardinality.STAR, new org.eclipse.emf.ecore.EClass[] {org.emftext.language.java.arrays.ArraysPackage.eINSTANCE.getArrayDimension(), }, 0);
	public final static JavaKeyword JAVA_22_0_0_8_0_0_0 = new JavaKeyword("throws", JavaCardinality.ONE);
	public final static JavaContainment JAVA_22_0_0_8_0_0_1 = new JavaContainment(org.emftext.language.java.annotations.AnnotationsPackage.eINSTANCE.getAnnotationAttribute().getEStructuralFeature(org.emftext.language.java.annotations.AnnotationsPackage.ANNOTATION_ATTRIBUTE__EXCEPTIONS), JavaCardinality.ONE, new org.eclipse.emf.ecore.EClass[] {org.emftext.language.java.types.TypesPackage.eINSTANCE.getNamespaceClassifierReference(), }, 0);
	public final static JavaKeyword JAVA_22_0_0_8_0_0_2_0_0_0 = new JavaKeyword(",", JavaCardinality.ONE);
	public final static JavaContainment JAVA_22_0_0_8_0_0_2_0_0_1 = new JavaContainment(org.emftext.language.java.annotations.AnnotationsPackage.eINSTANCE.getAnnotationAttribute().getEStructuralFeature(org.emftext.language.java.annotations.AnnotationsPackage.ANNOTATION_ATTRIBUTE__EXCEPTIONS), JavaCardinality.ONE, new org.eclipse.emf.ecore.EClass[] {org.emftext.language.java.types.TypesPackage.eINSTANCE.getNamespaceClassifierReference(), }, 0);
	public final static JavaSequence JAVA_22_0_0_8_0_0_2_0_0 = new JavaSequence(JavaCardinality.ONE, JAVA_22_0_0_8_0_0_2_0_0_0, JAVA_22_0_0_8_0_0_2_0_0_1);
	public final static JavaChoice JAVA_22_0_0_8_0_0_2_0 = new JavaChoice(JavaCardinality.ONE, JAVA_22_0_0_8_0_0_2_0_0);
	public final static JavaCompound JAVA_22_0_0_8_0_0_2 = new JavaCompound(JAVA_22_0_0_8_0_0_2_0, JavaCardinality.STAR);
	public final static JavaSequence JAVA_22_0_0_8_0_0 = new JavaSequence(JavaCardinality.ONE, JAVA_22_0_0_8_0_0_0, JAVA_22_0_0_8_0_0_1, JAVA_22_0_0_8_0_0_2);
	public final static JavaChoice JAVA_22_0_0_8_0 = new JavaChoice(JavaCardinality.ONE, JAVA_22_0_0_8_0_0);
	public final static JavaCompound JAVA_22_0_0_8 = new JavaCompound(JAVA_22_0_0_8_0, JavaCardinality.QUESTIONMARK);
	public final static JavaKeyword JAVA_22_0_0_9 = new JavaKeyword("default", JavaCardinality.ONE);
	public final static JavaContainment JAVA_22_0_0_10 = new JavaContainment(org.emftext.language.java.annotations.AnnotationsPackage.eINSTANCE.getAnnotationAttribute().getEStructuralFeature(org.emftext.language.java.annotations.AnnotationsPackage.ANNOTATION_ATTRIBUTE__DEFAULT_VALUE), JavaCardinality.ONE, new org.eclipse.emf.ecore.EClass[] {org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getAssignmentExpression(), }, 0);
	public final static JavaKeyword JAVA_22_0_0_11 = new JavaKeyword(";", JavaCardinality.ONE);
	public final static JavaSequence JAVA_22_0_0 = new JavaSequence(JavaCardinality.ONE, JAVA_22_0_0_0, JAVA_22_0_0_1, JAVA_22_0_0_2, JAVA_22_0_0_3, JAVA_22_0_0_4, JAVA_22_0_0_5, JAVA_22_0_0_6, JAVA_22_0_0_7, JAVA_22_0_0_8, JAVA_22_0_0_9, JAVA_22_0_0_10, JAVA_22_0_0_11);
	public final static JavaChoice JAVA_22_0 = new JavaChoice(JavaCardinality.ONE, JAVA_22_0_0);
	public final static JavaRule JAVA_22 = new JavaRule(org.emftext.language.java.annotations.AnnotationsPackage.eINSTANCE.getAnnotationAttribute(), JAVA_22_0, JavaCardinality.ONE);
	public final static JavaContainment JAVA_23_0_0_0 = new JavaContainment(org.emftext.language.java.parameters.ParametersPackage.eINSTANCE.getOrdinaryParameter().getEStructuralFeature(org.emftext.language.java.parameters.ParametersPackage.ORDINARY_PARAMETER__ANNOTATIONS_AND_MODIFIERS), JavaCardinality.STAR, new org.eclipse.emf.ecore.EClass[] {org.emftext.language.java.modifiers.ModifiersPackage.eINSTANCE.getAnnotationInstanceOrModifier(), }, 0);
	public final static JavaContainment JAVA_23_0_0_1 = new JavaContainment(org.emftext.language.java.parameters.ParametersPackage.eINSTANCE.getOrdinaryParameter().getEStructuralFeature(org.emftext.language.java.parameters.ParametersPackage.ORDINARY_PARAMETER__TYPE_REFERENCE), JavaCardinality.ONE, new org.eclipse.emf.ecore.EClass[] {org.emftext.language.java.types.TypesPackage.eINSTANCE.getTypeReference(), }, 0);
	public final static JavaContainment JAVA_23_0_0_2 = new JavaContainment(org.emftext.language.java.parameters.ParametersPackage.eINSTANCE.getOrdinaryParameter().getEStructuralFeature(org.emftext.language.java.parameters.ParametersPackage.ORDINARY_PARAMETER__ARRAY_DIMENSIONS_BEFORE), JavaCardinality.STAR, new org.eclipse.emf.ecore.EClass[] {org.emftext.language.java.arrays.ArraysPackage.eINSTANCE.getArrayDimension(), }, 0);
	public final static JavaKeyword JAVA_23_0_0_3_0_0_0 = new JavaKeyword("<", JavaCardinality.ONE);
	public final static JavaContainment JAVA_23_0_0_3_0_0_1 = new JavaContainment(org.emftext.language.java.parameters.ParametersPackage.eINSTANCE.getOrdinaryParameter().getEStructuralFeature(org.emftext.language.java.parameters.ParametersPackage.ORDINARY_PARAMETER__TYPE_ARGUMENTS), JavaCardinality.ONE, new org.eclipse.emf.ecore.EClass[] {org.emftext.language.java.generics.GenericsPackage.eINSTANCE.getTypeArgument(), }, 0);
	public final static JavaKeyword JAVA_23_0_0_3_0_0_2_0_0_0 = new JavaKeyword(",", JavaCardinality.ONE);
	public final static JavaContainment JAVA_23_0_0_3_0_0_2_0_0_1 = new JavaContainment(org.emftext.language.java.parameters.ParametersPackage.eINSTANCE.getOrdinaryParameter().getEStructuralFeature(org.emftext.language.java.parameters.ParametersPackage.ORDINARY_PARAMETER__TYPE_ARGUMENTS), JavaCardinality.ONE, new org.eclipse.emf.ecore.EClass[] {org.emftext.language.java.generics.GenericsPackage.eINSTANCE.getTypeArgument(), }, 0);
	public final static JavaSequence JAVA_23_0_0_3_0_0_2_0_0 = new JavaSequence(JavaCardinality.ONE, JAVA_23_0_0_3_0_0_2_0_0_0, JAVA_23_0_0_3_0_0_2_0_0_1);
	public final static JavaChoice JAVA_23_0_0_3_0_0_2_0 = new JavaChoice(JavaCardinality.ONE, JAVA_23_0_0_3_0_0_2_0_0);
	public final static JavaCompound JAVA_23_0_0_3_0_0_2 = new JavaCompound(JAVA_23_0_0_3_0_0_2_0, JavaCardinality.STAR);
	public final static JavaKeyword JAVA_23_0_0_3_0_0_3 = new JavaKeyword(">", JavaCardinality.ONE);
	public final static JavaSequence JAVA_23_0_0_3_0_0 = new JavaSequence(JavaCardinality.ONE, JAVA_23_0_0_3_0_0_0, JAVA_23_0_0_3_0_0_1, JAVA_23_0_0_3_0_0_2, JAVA_23_0_0_3_0_0_3);
	public final static JavaChoice JAVA_23_0_0_3_0 = new JavaChoice(JavaCardinality.ONE, JAVA_23_0_0_3_0_0);
	public final static JavaCompound JAVA_23_0_0_3 = new JavaCompound(JAVA_23_0_0_3_0, JavaCardinality.QUESTIONMARK);
	public final static JavaPlaceholder JAVA_23_0_0_4 = new JavaPlaceholder(org.emftext.language.java.parameters.ParametersPackage.eINSTANCE.getOrdinaryParameter().getEStructuralFeature(org.emftext.language.java.parameters.ParametersPackage.ORDINARY_PARAMETER__NAME), "IDENTIFIER", JavaCardinality.ONE, 0);
	public final static JavaContainment JAVA_23_0_0_5 = new JavaContainment(org.emftext.language.java.parameters.ParametersPackage.eINSTANCE.getOrdinaryParameter().getEStructuralFeature(org.emftext.language.java.parameters.ParametersPackage.ORDINARY_PARAMETER__ARRAY_DIMENSIONS_AFTER), JavaCardinality.STAR, new org.eclipse.emf.ecore.EClass[] {org.emftext.language.java.arrays.ArraysPackage.eINSTANCE.getArrayDimension(), }, 0);
	public final static JavaSequence JAVA_23_0_0 = new JavaSequence(JavaCardinality.ONE, JAVA_23_0_0_0, JAVA_23_0_0_1, JAVA_23_0_0_2, JAVA_23_0_0_3, JAVA_23_0_0_4, JAVA_23_0_0_5);
	public final static JavaChoice JAVA_23_0 = new JavaChoice(JavaCardinality.ONE, JAVA_23_0_0);
	public final static JavaRule JAVA_23 = new JavaRule(org.emftext.language.java.parameters.ParametersPackage.eINSTANCE.getOrdinaryParameter(), JAVA_23_0, JavaCardinality.ONE);
	public final static JavaContainment JAVA_24_0_0_0 = new JavaContainment(org.emftext.language.java.parameters.ParametersPackage.eINSTANCE.getVariableLengthParameter().getEStructuralFeature(org.emftext.language.java.parameters.ParametersPackage.VARIABLE_LENGTH_PARAMETER__ANNOTATIONS_AND_MODIFIERS), JavaCardinality.STAR, new org.eclipse.emf.ecore.EClass[] {org.emftext.language.java.modifiers.ModifiersPackage.eINSTANCE.getAnnotationInstanceOrModifier(), }, 0);
	public final static JavaContainment JAVA_24_0_0_1 = new JavaContainment(org.emftext.language.java.parameters.ParametersPackage.eINSTANCE.getVariableLengthParameter().getEStructuralFeature(org.emftext.language.java.parameters.ParametersPackage.VARIABLE_LENGTH_PARAMETER__TYPE_REFERENCE), JavaCardinality.ONE, new org.eclipse.emf.ecore.EClass[] {org.emftext.language.java.types.TypesPackage.eINSTANCE.getTypeReference(), }, 0);
	public final static JavaContainment JAVA_24_0_0_2 = new JavaContainment(org.emftext.language.java.parameters.ParametersPackage.eINSTANCE.getVariableLengthParameter().getEStructuralFeature(org.emftext.language.java.parameters.ParametersPackage.VARIABLE_LENGTH_PARAMETER__ARRAY_DIMENSIONS_BEFORE), JavaCardinality.STAR, new org.eclipse.emf.ecore.EClass[] {org.emftext.language.java.arrays.ArraysPackage.eINSTANCE.getArrayDimension(), }, 0);
	public final static JavaKeyword JAVA_24_0_0_3_0_0_0 = new JavaKeyword("<", JavaCardinality.ONE);
	public final static JavaContainment JAVA_24_0_0_3_0_0_1 = new JavaContainment(org.emftext.language.java.parameters.ParametersPackage.eINSTANCE.getVariableLengthParameter().getEStructuralFeature(org.emftext.language.java.parameters.ParametersPackage.VARIABLE_LENGTH_PARAMETER__TYPE_ARGUMENTS), JavaCardinality.ONE, new org.eclipse.emf.ecore.EClass[] {org.emftext.language.java.generics.GenericsPackage.eINSTANCE.getTypeArgument(), }, 0);
	public final static JavaKeyword JAVA_24_0_0_3_0_0_2_0_0_0 = new JavaKeyword(",", JavaCardinality.ONE);
	public final static JavaContainment JAVA_24_0_0_3_0_0_2_0_0_1 = new JavaContainment(org.emftext.language.java.parameters.ParametersPackage.eINSTANCE.getVariableLengthParameter().getEStructuralFeature(org.emftext.language.java.parameters.ParametersPackage.VARIABLE_LENGTH_PARAMETER__TYPE_ARGUMENTS), JavaCardinality.ONE, new org.eclipse.emf.ecore.EClass[] {org.emftext.language.java.generics.GenericsPackage.eINSTANCE.getTypeArgument(), }, 0);
	public final static JavaSequence JAVA_24_0_0_3_0_0_2_0_0 = new JavaSequence(JavaCardinality.ONE, JAVA_24_0_0_3_0_0_2_0_0_0, JAVA_24_0_0_3_0_0_2_0_0_1);
	public final static JavaChoice JAVA_24_0_0_3_0_0_2_0 = new JavaChoice(JavaCardinality.ONE, JAVA_24_0_0_3_0_0_2_0_0);
	public final static JavaCompound JAVA_24_0_0_3_0_0_2 = new JavaCompound(JAVA_24_0_0_3_0_0_2_0, JavaCardinality.STAR);
	public final static JavaKeyword JAVA_24_0_0_3_0_0_3 = new JavaKeyword(">", JavaCardinality.ONE);
	public final static JavaSequence JAVA_24_0_0_3_0_0 = new JavaSequence(JavaCardinality.ONE, JAVA_24_0_0_3_0_0_0, JAVA_24_0_0_3_0_0_1, JAVA_24_0_0_3_0_0_2, JAVA_24_0_0_3_0_0_3);
	public final static JavaChoice JAVA_24_0_0_3_0 = new JavaChoice(JavaCardinality.ONE, JAVA_24_0_0_3_0_0);
	public final static JavaCompound JAVA_24_0_0_3 = new JavaCompound(JAVA_24_0_0_3_0, JavaCardinality.QUESTIONMARK);
	public final static JavaKeyword JAVA_24_0_0_4 = new JavaKeyword("...", JavaCardinality.ONE);
	public final static JavaPlaceholder JAVA_24_0_0_5 = new JavaPlaceholder(org.emftext.language.java.parameters.ParametersPackage.eINSTANCE.getVariableLengthParameter().getEStructuralFeature(org.emftext.language.java.parameters.ParametersPackage.VARIABLE_LENGTH_PARAMETER__NAME), "IDENTIFIER", JavaCardinality.ONE, 0);
	public final static JavaSequence JAVA_24_0_0 = new JavaSequence(JavaCardinality.ONE, JAVA_24_0_0_0, JAVA_24_0_0_1, JAVA_24_0_0_2, JAVA_24_0_0_3, JAVA_24_0_0_4, JAVA_24_0_0_5);
	public final static JavaChoice JAVA_24_0 = new JavaChoice(JavaCardinality.ONE, JAVA_24_0_0);
	public final static JavaRule JAVA_24 = new JavaRule(org.emftext.language.java.parameters.ParametersPackage.eINSTANCE.getVariableLengthParameter(), JAVA_24_0, JavaCardinality.ONE);
	public final static JavaContainment JAVA_25_0_0_0 = new JavaContainment(org.emftext.language.java.variables.VariablesPackage.eINSTANCE.getLocalVariable().getEStructuralFeature(org.emftext.language.java.variables.VariablesPackage.LOCAL_VARIABLE__ANNOTATIONS_AND_MODIFIERS), JavaCardinality.STAR, new org.eclipse.emf.ecore.EClass[] {org.emftext.language.java.modifiers.ModifiersPackage.eINSTANCE.getAnnotationInstanceOrModifier(), }, 0);
	public final static JavaContainment JAVA_25_0_0_1 = new JavaContainment(org.emftext.language.java.variables.VariablesPackage.eINSTANCE.getLocalVariable().getEStructuralFeature(org.emftext.language.java.variables.VariablesPackage.LOCAL_VARIABLE__TYPE_REFERENCE), JavaCardinality.ONE, new org.eclipse.emf.ecore.EClass[] {org.emftext.language.java.types.TypesPackage.eINSTANCE.getTypeReference(), }, 0);
	public final static JavaContainment JAVA_25_0_0_2 = new JavaContainment(org.emftext.language.java.variables.VariablesPackage.eINSTANCE.getLocalVariable().getEStructuralFeature(org.emftext.language.java.variables.VariablesPackage.LOCAL_VARIABLE__ARRAY_DIMENSIONS_BEFORE), JavaCardinality.STAR, new org.eclipse.emf.ecore.EClass[] {org.emftext.language.java.arrays.ArraysPackage.eINSTANCE.getArrayDimension(), }, 0);
	public final static JavaKeyword JAVA_25_0_0_3_0_0_0 = new JavaKeyword("<", JavaCardinality.ONE);
	public final static JavaContainment JAVA_25_0_0_3_0_0_1 = new JavaContainment(org.emftext.language.java.variables.VariablesPackage.eINSTANCE.getLocalVariable().getEStructuralFeature(org.emftext.language.java.variables.VariablesPackage.LOCAL_VARIABLE__TYPE_ARGUMENTS), JavaCardinality.ONE, new org.eclipse.emf.ecore.EClass[] {org.emftext.language.java.generics.GenericsPackage.eINSTANCE.getTypeArgument(), }, 0);
	public final static JavaKeyword JAVA_25_0_0_3_0_0_2_0_0_0 = new JavaKeyword(",", JavaCardinality.ONE);
	public final static JavaContainment JAVA_25_0_0_3_0_0_2_0_0_1 = new JavaContainment(org.emftext.language.java.variables.VariablesPackage.eINSTANCE.getLocalVariable().getEStructuralFeature(org.emftext.language.java.variables.VariablesPackage.LOCAL_VARIABLE__TYPE_ARGUMENTS), JavaCardinality.ONE, new org.eclipse.emf.ecore.EClass[] {org.emftext.language.java.generics.GenericsPackage.eINSTANCE.getTypeArgument(), }, 0);
	public final static JavaSequence JAVA_25_0_0_3_0_0_2_0_0 = new JavaSequence(JavaCardinality.ONE, JAVA_25_0_0_3_0_0_2_0_0_0, JAVA_25_0_0_3_0_0_2_0_0_1);
	public final static JavaChoice JAVA_25_0_0_3_0_0_2_0 = new JavaChoice(JavaCardinality.ONE, JAVA_25_0_0_3_0_0_2_0_0);
	public final static JavaCompound JAVA_25_0_0_3_0_0_2 = new JavaCompound(JAVA_25_0_0_3_0_0_2_0, JavaCardinality.STAR);
	public final static JavaKeyword JAVA_25_0_0_3_0_0_3 = new JavaKeyword(">", JavaCardinality.ONE);
	public final static JavaSequence JAVA_25_0_0_3_0_0 = new JavaSequence(JavaCardinality.ONE, JAVA_25_0_0_3_0_0_0, JAVA_25_0_0_3_0_0_1, JAVA_25_0_0_3_0_0_2, JAVA_25_0_0_3_0_0_3);
	public final static JavaChoice JAVA_25_0_0_3_0 = new JavaChoice(JavaCardinality.ONE, JAVA_25_0_0_3_0_0);
	public final static JavaCompound JAVA_25_0_0_3 = new JavaCompound(JAVA_25_0_0_3_0, JavaCardinality.QUESTIONMARK);
	public final static JavaPlaceholder JAVA_25_0_0_4 = new JavaPlaceholder(org.emftext.language.java.variables.VariablesPackage.eINSTANCE.getLocalVariable().getEStructuralFeature(org.emftext.language.java.variables.VariablesPackage.LOCAL_VARIABLE__NAME), "IDENTIFIER", JavaCardinality.ONE, 0);
	public final static JavaContainment JAVA_25_0_0_5 = new JavaContainment(org.emftext.language.java.variables.VariablesPackage.eINSTANCE.getLocalVariable().getEStructuralFeature(org.emftext.language.java.variables.VariablesPackage.LOCAL_VARIABLE__ARRAY_DIMENSIONS_AFTER), JavaCardinality.STAR, new org.eclipse.emf.ecore.EClass[] {org.emftext.language.java.arrays.ArraysPackage.eINSTANCE.getArrayDimension(), }, 0);
	public final static JavaWhiteSpace JAVA_25_0_0_6_0_0_0 = new JavaWhiteSpace(1, JavaCardinality.ONE);
	public final static JavaKeyword JAVA_25_0_0_6_0_0_1 = new JavaKeyword("=", JavaCardinality.ONE);
	public final static JavaWhiteSpace JAVA_25_0_0_6_0_0_2 = new JavaWhiteSpace(1, JavaCardinality.ONE);
	public final static JavaContainment JAVA_25_0_0_6_0_0_3 = new JavaContainment(org.emftext.language.java.variables.VariablesPackage.eINSTANCE.getLocalVariable().getEStructuralFeature(org.emftext.language.java.variables.VariablesPackage.LOCAL_VARIABLE__INITIAL_VALUE), JavaCardinality.ONE, new org.eclipse.emf.ecore.EClass[] {org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getAssignmentExpression(), }, 0);
	public final static JavaSequence JAVA_25_0_0_6_0_0 = new JavaSequence(JavaCardinality.ONE, JAVA_25_0_0_6_0_0_0, JAVA_25_0_0_6_0_0_1, JAVA_25_0_0_6_0_0_2, JAVA_25_0_0_6_0_0_3);
	public final static JavaChoice JAVA_25_0_0_6_0 = new JavaChoice(JavaCardinality.ONE, JAVA_25_0_0_6_0_0);
	public final static JavaCompound JAVA_25_0_0_6 = new JavaCompound(JAVA_25_0_0_6_0, JavaCardinality.QUESTIONMARK);
	public final static JavaKeyword JAVA_25_0_0_7_0_0_0 = new JavaKeyword(",", JavaCardinality.ONE);
	public final static JavaContainment JAVA_25_0_0_7_0_0_1 = new JavaContainment(org.emftext.language.java.variables.VariablesPackage.eINSTANCE.getLocalVariable().getEStructuralFeature(org.emftext.language.java.variables.VariablesPackage.LOCAL_VARIABLE__ADDITIONAL_LOCAL_VARIABLES), JavaCardinality.ONE, new org.eclipse.emf.ecore.EClass[] {org.emftext.language.java.variables.VariablesPackage.eINSTANCE.getAdditionalLocalVariable(), }, 0);
	public final static JavaSequence JAVA_25_0_0_7_0_0 = new JavaSequence(JavaCardinality.ONE, JAVA_25_0_0_7_0_0_0, JAVA_25_0_0_7_0_0_1);
	public final static JavaChoice JAVA_25_0_0_7_0 = new JavaChoice(JavaCardinality.ONE, JAVA_25_0_0_7_0_0);
	public final static JavaCompound JAVA_25_0_0_7 = new JavaCompound(JAVA_25_0_0_7_0, JavaCardinality.STAR);
	public final static JavaSequence JAVA_25_0_0 = new JavaSequence(JavaCardinality.ONE, JAVA_25_0_0_0, JAVA_25_0_0_1, JAVA_25_0_0_2, JAVA_25_0_0_3, JAVA_25_0_0_4, JAVA_25_0_0_5, JAVA_25_0_0_6, JAVA_25_0_0_7);
	public final static JavaChoice JAVA_25_0 = new JavaChoice(JavaCardinality.ONE, JAVA_25_0_0);
	public final static JavaRule JAVA_25 = new JavaRule(org.emftext.language.java.variables.VariablesPackage.eINSTANCE.getLocalVariable(), JAVA_25_0, JavaCardinality.ONE);
	public final static JavaContainment JAVA_26_0_0_0 = new JavaContainment(org.emftext.language.java.statements.StatementsPackage.eINSTANCE.getLocalVariableStatement().getEStructuralFeature(org.emftext.language.java.statements.StatementsPackage.LOCAL_VARIABLE_STATEMENT__VARIABLE), JavaCardinality.ONE, new org.eclipse.emf.ecore.EClass[] {org.emftext.language.java.variables.VariablesPackage.eINSTANCE.getLocalVariable(), }, 0);
	public final static JavaKeyword JAVA_26_0_0_1 = new JavaKeyword(";", JavaCardinality.ONE);
	public final static JavaSequence JAVA_26_0_0 = new JavaSequence(JavaCardinality.ONE, JAVA_26_0_0_0, JAVA_26_0_0_1);
	public final static JavaChoice JAVA_26_0 = new JavaChoice(JavaCardinality.ONE, JAVA_26_0_0);
	public final static JavaRule JAVA_26 = new JavaRule(org.emftext.language.java.statements.StatementsPackage.eINSTANCE.getLocalVariableStatement(), JAVA_26_0, JavaCardinality.ONE);
	public final static JavaPlaceholder JAVA_27_0_0_0 = new JavaPlaceholder(org.emftext.language.java.variables.VariablesPackage.eINSTANCE.getAdditionalLocalVariable().getEStructuralFeature(org.emftext.language.java.variables.VariablesPackage.ADDITIONAL_LOCAL_VARIABLE__NAME), "IDENTIFIER", JavaCardinality.ONE, 0);
	public final static JavaContainment JAVA_27_0_0_1 = new JavaContainment(org.emftext.language.java.variables.VariablesPackage.eINSTANCE.getAdditionalLocalVariable().getEStructuralFeature(org.emftext.language.java.variables.VariablesPackage.ADDITIONAL_LOCAL_VARIABLE__ARRAY_DIMENSIONS_AFTER), JavaCardinality.STAR, new org.eclipse.emf.ecore.EClass[] {org.emftext.language.java.arrays.ArraysPackage.eINSTANCE.getArrayDimension(), }, 0);
	public final static JavaWhiteSpace JAVA_27_0_0_2_0_0_0 = new JavaWhiteSpace(1, JavaCardinality.ONE);
	public final static JavaKeyword JAVA_27_0_0_2_0_0_1 = new JavaKeyword("=", JavaCardinality.ONE);
	public final static JavaWhiteSpace JAVA_27_0_0_2_0_0_2 = new JavaWhiteSpace(1, JavaCardinality.ONE);
	public final static JavaContainment JAVA_27_0_0_2_0_0_3 = new JavaContainment(org.emftext.language.java.variables.VariablesPackage.eINSTANCE.getAdditionalLocalVariable().getEStructuralFeature(org.emftext.language.java.variables.VariablesPackage.ADDITIONAL_LOCAL_VARIABLE__INITIAL_VALUE), JavaCardinality.ONE, new org.eclipse.emf.ecore.EClass[] {org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getAssignmentExpression(), }, 0);
	public final static JavaSequence JAVA_27_0_0_2_0_0 = new JavaSequence(JavaCardinality.ONE, JAVA_27_0_0_2_0_0_0, JAVA_27_0_0_2_0_0_1, JAVA_27_0_0_2_0_0_2, JAVA_27_0_0_2_0_0_3);
	public final static JavaChoice JAVA_27_0_0_2_0 = new JavaChoice(JavaCardinality.ONE, JAVA_27_0_0_2_0_0);
	public final static JavaCompound JAVA_27_0_0_2 = new JavaCompound(JAVA_27_0_0_2_0, JavaCardinality.QUESTIONMARK);
	public final static JavaSequence JAVA_27_0_0 = new JavaSequence(JavaCardinality.ONE, JAVA_27_0_0_0, JAVA_27_0_0_1, JAVA_27_0_0_2);
	public final static JavaChoice JAVA_27_0 = new JavaChoice(JavaCardinality.ONE, JAVA_27_0_0);
	public final static JavaRule JAVA_27 = new JavaRule(org.emftext.language.java.variables.VariablesPackage.eINSTANCE.getAdditionalLocalVariable(), JAVA_27_0, JavaCardinality.ONE);
	public final static JavaContainment JAVA_28_0_0_0 = new JavaContainment(org.emftext.language.java.members.MembersPackage.eINSTANCE.getField().getEStructuralFeature(org.emftext.language.java.members.MembersPackage.FIELD__ANNOTATIONS_AND_MODIFIERS), JavaCardinality.STAR, new org.eclipse.emf.ecore.EClass[] {org.emftext.language.java.modifiers.ModifiersPackage.eINSTANCE.getAnnotationInstanceOrModifier(), }, 0);
	public final static JavaContainment JAVA_28_0_0_1 = new JavaContainment(org.emftext.language.java.members.MembersPackage.eINSTANCE.getField().getEStructuralFeature(org.emftext.language.java.members.MembersPackage.FIELD__TYPE_REFERENCE), JavaCardinality.ONE, new org.eclipse.emf.ecore.EClass[] {org.emftext.language.java.types.TypesPackage.eINSTANCE.getTypeReference(), }, 0);
	public final static JavaContainment JAVA_28_0_0_2 = new JavaContainment(org.emftext.language.java.members.MembersPackage.eINSTANCE.getField().getEStructuralFeature(org.emftext.language.java.members.MembersPackage.FIELD__ARRAY_DIMENSIONS_BEFORE), JavaCardinality.STAR, new org.eclipse.emf.ecore.EClass[] {org.emftext.language.java.arrays.ArraysPackage.eINSTANCE.getArrayDimension(), }, 0);
	public final static JavaKeyword JAVA_28_0_0_3_0_0_0 = new JavaKeyword("<", JavaCardinality.ONE);
	public final static JavaContainment JAVA_28_0_0_3_0_0_1 = new JavaContainment(org.emftext.language.java.members.MembersPackage.eINSTANCE.getField().getEStructuralFeature(org.emftext.language.java.members.MembersPackage.FIELD__TYPE_ARGUMENTS), JavaCardinality.ONE, new org.eclipse.emf.ecore.EClass[] {org.emftext.language.java.generics.GenericsPackage.eINSTANCE.getTypeArgument(), }, 0);
	public final static JavaKeyword JAVA_28_0_0_3_0_0_2_0_0_0 = new JavaKeyword(",", JavaCardinality.ONE);
	public final static JavaContainment JAVA_28_0_0_3_0_0_2_0_0_1 = new JavaContainment(org.emftext.language.java.members.MembersPackage.eINSTANCE.getField().getEStructuralFeature(org.emftext.language.java.members.MembersPackage.FIELD__TYPE_ARGUMENTS), JavaCardinality.ONE, new org.eclipse.emf.ecore.EClass[] {org.emftext.language.java.generics.GenericsPackage.eINSTANCE.getTypeArgument(), }, 0);
	public final static JavaSequence JAVA_28_0_0_3_0_0_2_0_0 = new JavaSequence(JavaCardinality.ONE, JAVA_28_0_0_3_0_0_2_0_0_0, JAVA_28_0_0_3_0_0_2_0_0_1);
	public final static JavaChoice JAVA_28_0_0_3_0_0_2_0 = new JavaChoice(JavaCardinality.ONE, JAVA_28_0_0_3_0_0_2_0_0);
	public final static JavaCompound JAVA_28_0_0_3_0_0_2 = new JavaCompound(JAVA_28_0_0_3_0_0_2_0, JavaCardinality.STAR);
	public final static JavaKeyword JAVA_28_0_0_3_0_0_3 = new JavaKeyword(">", JavaCardinality.ONE);
	public final static JavaSequence JAVA_28_0_0_3_0_0 = new JavaSequence(JavaCardinality.ONE, JAVA_28_0_0_3_0_0_0, JAVA_28_0_0_3_0_0_1, JAVA_28_0_0_3_0_0_2, JAVA_28_0_0_3_0_0_3);
	public final static JavaChoice JAVA_28_0_0_3_0 = new JavaChoice(JavaCardinality.ONE, JAVA_28_0_0_3_0_0);
	public final static JavaCompound JAVA_28_0_0_3 = new JavaCompound(JAVA_28_0_0_3_0, JavaCardinality.QUESTIONMARK);
	public final static JavaPlaceholder JAVA_28_0_0_4 = new JavaPlaceholder(org.emftext.language.java.members.MembersPackage.eINSTANCE.getField().getEStructuralFeature(org.emftext.language.java.members.MembersPackage.FIELD__NAME), "IDENTIFIER", JavaCardinality.ONE, 0);
	public final static JavaContainment JAVA_28_0_0_5 = new JavaContainment(org.emftext.language.java.members.MembersPackage.eINSTANCE.getField().getEStructuralFeature(org.emftext.language.java.members.MembersPackage.FIELD__ARRAY_DIMENSIONS_AFTER), JavaCardinality.STAR, new org.eclipse.emf.ecore.EClass[] {org.emftext.language.java.arrays.ArraysPackage.eINSTANCE.getArrayDimension(), }, 0);
	public final static JavaWhiteSpace JAVA_28_0_0_6_0_0_0 = new JavaWhiteSpace(1, JavaCardinality.ONE);
	public final static JavaKeyword JAVA_28_0_0_6_0_0_1 = new JavaKeyword("=", JavaCardinality.ONE);
	public final static JavaWhiteSpace JAVA_28_0_0_6_0_0_2 = new JavaWhiteSpace(1, JavaCardinality.ONE);
	public final static JavaContainment JAVA_28_0_0_6_0_0_3 = new JavaContainment(org.emftext.language.java.members.MembersPackage.eINSTANCE.getField().getEStructuralFeature(org.emftext.language.java.members.MembersPackage.FIELD__INITIAL_VALUE), JavaCardinality.ONE, new org.eclipse.emf.ecore.EClass[] {org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getAssignmentExpression(), }, 0);
	public final static JavaSequence JAVA_28_0_0_6_0_0 = new JavaSequence(JavaCardinality.ONE, JAVA_28_0_0_6_0_0_0, JAVA_28_0_0_6_0_0_1, JAVA_28_0_0_6_0_0_2, JAVA_28_0_0_6_0_0_3);
	public final static JavaChoice JAVA_28_0_0_6_0 = new JavaChoice(JavaCardinality.ONE, JAVA_28_0_0_6_0_0);
	public final static JavaCompound JAVA_28_0_0_6 = new JavaCompound(JAVA_28_0_0_6_0, JavaCardinality.QUESTIONMARK);
	public final static JavaKeyword JAVA_28_0_0_7_0_0_0 = new JavaKeyword(",", JavaCardinality.ONE);
	public final static JavaContainment JAVA_28_0_0_7_0_0_1 = new JavaContainment(org.emftext.language.java.members.MembersPackage.eINSTANCE.getField().getEStructuralFeature(org.emftext.language.java.members.MembersPackage.FIELD__ADDITIONAL_FIELDS), JavaCardinality.ONE, new org.eclipse.emf.ecore.EClass[] {org.emftext.language.java.members.MembersPackage.eINSTANCE.getAdditionalField(), }, 0);
	public final static JavaSequence JAVA_28_0_0_7_0_0 = new JavaSequence(JavaCardinality.ONE, JAVA_28_0_0_7_0_0_0, JAVA_28_0_0_7_0_0_1);
	public final static JavaChoice JAVA_28_0_0_7_0 = new JavaChoice(JavaCardinality.ONE, JAVA_28_0_0_7_0_0);
	public final static JavaCompound JAVA_28_0_0_7 = new JavaCompound(JAVA_28_0_0_7_0, JavaCardinality.STAR);
	public final static JavaKeyword JAVA_28_0_0_8 = new JavaKeyword(";", JavaCardinality.ONE);
	public final static JavaSequence JAVA_28_0_0 = new JavaSequence(JavaCardinality.ONE, JAVA_28_0_0_0, JAVA_28_0_0_1, JAVA_28_0_0_2, JAVA_28_0_0_3, JAVA_28_0_0_4, JAVA_28_0_0_5, JAVA_28_0_0_6, JAVA_28_0_0_7, JAVA_28_0_0_8);
	public final static JavaChoice JAVA_28_0 = new JavaChoice(JavaCardinality.ONE, JAVA_28_0_0);
	public final static JavaRule JAVA_28 = new JavaRule(org.emftext.language.java.members.MembersPackage.eINSTANCE.getField(), JAVA_28_0, JavaCardinality.ONE);
	public final static JavaPlaceholder JAVA_29_0_0_0 = new JavaPlaceholder(org.emftext.language.java.members.MembersPackage.eINSTANCE.getAdditionalField().getEStructuralFeature(org.emftext.language.java.members.MembersPackage.ADDITIONAL_FIELD__NAME), "IDENTIFIER", JavaCardinality.ONE, 0);
	public final static JavaContainment JAVA_29_0_0_1 = new JavaContainment(org.emftext.language.java.members.MembersPackage.eINSTANCE.getAdditionalField().getEStructuralFeature(org.emftext.language.java.members.MembersPackage.ADDITIONAL_FIELD__ARRAY_DIMENSIONS_AFTER), JavaCardinality.STAR, new org.eclipse.emf.ecore.EClass[] {org.emftext.language.java.arrays.ArraysPackage.eINSTANCE.getArrayDimension(), }, 0);
	public final static JavaWhiteSpace JAVA_29_0_0_2_0_0_0 = new JavaWhiteSpace(1, JavaCardinality.ONE);
	public final static JavaKeyword JAVA_29_0_0_2_0_0_1 = new JavaKeyword("=", JavaCardinality.ONE);
	public final static JavaWhiteSpace JAVA_29_0_0_2_0_0_2 = new JavaWhiteSpace(1, JavaCardinality.ONE);
	public final static JavaContainment JAVA_29_0_0_2_0_0_3 = new JavaContainment(org.emftext.language.java.members.MembersPackage.eINSTANCE.getAdditionalField().getEStructuralFeature(org.emftext.language.java.members.MembersPackage.ADDITIONAL_FIELD__INITIAL_VALUE), JavaCardinality.ONE, new org.eclipse.emf.ecore.EClass[] {org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getAssignmentExpression(), }, 0);
	public final static JavaSequence JAVA_29_0_0_2_0_0 = new JavaSequence(JavaCardinality.ONE, JAVA_29_0_0_2_0_0_0, JAVA_29_0_0_2_0_0_1, JAVA_29_0_0_2_0_0_2, JAVA_29_0_0_2_0_0_3);
	public final static JavaChoice JAVA_29_0_0_2_0 = new JavaChoice(JavaCardinality.ONE, JAVA_29_0_0_2_0_0);
	public final static JavaCompound JAVA_29_0_0_2 = new JavaCompound(JAVA_29_0_0_2_0, JavaCardinality.QUESTIONMARK);
	public final static JavaSequence JAVA_29_0_0 = new JavaSequence(JavaCardinality.ONE, JAVA_29_0_0_0, JAVA_29_0_0_1, JAVA_29_0_0_2);
	public final static JavaChoice JAVA_29_0 = new JavaChoice(JavaCardinality.ONE, JAVA_29_0_0);
	public final static JavaRule JAVA_29 = new JavaRule(org.emftext.language.java.members.MembersPackage.eINSTANCE.getAdditionalField(), JAVA_29_0, JavaCardinality.ONE);
	public final static JavaKeyword JAVA_30_0_0_0 = new JavaKeyword(";", JavaCardinality.ONE);
	public final static JavaSequence JAVA_30_0_0 = new JavaSequence(JavaCardinality.ONE, JAVA_30_0_0_0);
	public final static JavaChoice JAVA_30_0 = new JavaChoice(JavaCardinality.ONE, JAVA_30_0_0);
	public final static JavaRule JAVA_30 = new JavaRule(org.emftext.language.java.members.MembersPackage.eINSTANCE.getEmptyMember(), JAVA_30_0, JavaCardinality.ONE);
	public final static JavaKeyword JAVA_31_0_0_0 = new JavaKeyword("new", JavaCardinality.ONE);
	public final static JavaKeyword JAVA_31_0_0_1_0_0_0 = new JavaKeyword("<", JavaCardinality.ONE);
	public final static JavaContainment JAVA_31_0_0_1_0_0_1 = new JavaContainment(org.emftext.language.java.instantiations.InstantiationsPackage.eINSTANCE.getNewConstructorCall().getEStructuralFeature(org.emftext.language.java.instantiations.InstantiationsPackage.NEW_CONSTRUCTOR_CALL__TYPE_ARGUMENTS), JavaCardinality.ONE, new org.eclipse.emf.ecore.EClass[] {org.emftext.language.java.generics.GenericsPackage.eINSTANCE.getTypeArgument(), }, 0);
	public final static JavaKeyword JAVA_31_0_0_1_0_0_2_0_0_0 = new JavaKeyword(",", JavaCardinality.ONE);
	public final static JavaContainment JAVA_31_0_0_1_0_0_2_0_0_1 = new JavaContainment(org.emftext.language.java.instantiations.InstantiationsPackage.eINSTANCE.getNewConstructorCall().getEStructuralFeature(org.emftext.language.java.instantiations.InstantiationsPackage.NEW_CONSTRUCTOR_CALL__TYPE_ARGUMENTS), JavaCardinality.ONE, new org.eclipse.emf.ecore.EClass[] {org.emftext.language.java.generics.GenericsPackage.eINSTANCE.getTypeArgument(), }, 0);
	public final static JavaSequence JAVA_31_0_0_1_0_0_2_0_0 = new JavaSequence(JavaCardinality.ONE, JAVA_31_0_0_1_0_0_2_0_0_0, JAVA_31_0_0_1_0_0_2_0_0_1);
	public final static JavaChoice JAVA_31_0_0_1_0_0_2_0 = new JavaChoice(JavaCardinality.ONE, JAVA_31_0_0_1_0_0_2_0_0);
	public final static JavaCompound JAVA_31_0_0_1_0_0_2 = new JavaCompound(JAVA_31_0_0_1_0_0_2_0, JavaCardinality.STAR);
	public final static JavaKeyword JAVA_31_0_0_1_0_0_3 = new JavaKeyword(">", JavaCardinality.ONE);
	public final static JavaSequence JAVA_31_0_0_1_0_0 = new JavaSequence(JavaCardinality.ONE, JAVA_31_0_0_1_0_0_0, JAVA_31_0_0_1_0_0_1, JAVA_31_0_0_1_0_0_2, JAVA_31_0_0_1_0_0_3);
	public final static JavaChoice JAVA_31_0_0_1_0 = new JavaChoice(JavaCardinality.ONE, JAVA_31_0_0_1_0_0);
	public final static JavaCompound JAVA_31_0_0_1 = new JavaCompound(JAVA_31_0_0_1_0, JavaCardinality.QUESTIONMARK);
	public final static JavaContainment JAVA_31_0_0_2 = new JavaContainment(org.emftext.language.java.instantiations.InstantiationsPackage.eINSTANCE.getNewConstructorCall().getEStructuralFeature(org.emftext.language.java.instantiations.InstantiationsPackage.NEW_CONSTRUCTOR_CALL__TYPE_REFERENCE), JavaCardinality.ONE, new org.eclipse.emf.ecore.EClass[] {org.emftext.language.java.types.TypesPackage.eINSTANCE.getTypeReference(), }, 0);
	public final static JavaKeyword JAVA_31_0_0_3_0_0_0 = new JavaKeyword("<", JavaCardinality.ONE);
	public final static JavaContainment JAVA_31_0_0_3_0_0_1 = new JavaContainment(org.emftext.language.java.instantiations.InstantiationsPackage.eINSTANCE.getNewConstructorCall().getEStructuralFeature(org.emftext.language.java.instantiations.InstantiationsPackage.NEW_CONSTRUCTOR_CALL__CALL_TYPE_ARGUMENTS), JavaCardinality.ONE, new org.eclipse.emf.ecore.EClass[] {org.emftext.language.java.generics.GenericsPackage.eINSTANCE.getTypeArgument(), }, 0);
	public final static JavaKeyword JAVA_31_0_0_3_0_0_2_0_0_0 = new JavaKeyword(",", JavaCardinality.ONE);
	public final static JavaContainment JAVA_31_0_0_3_0_0_2_0_0_1 = new JavaContainment(org.emftext.language.java.instantiations.InstantiationsPackage.eINSTANCE.getNewConstructorCall().getEStructuralFeature(org.emftext.language.java.instantiations.InstantiationsPackage.NEW_CONSTRUCTOR_CALL__CALL_TYPE_ARGUMENTS), JavaCardinality.ONE, new org.eclipse.emf.ecore.EClass[] {org.emftext.language.java.generics.GenericsPackage.eINSTANCE.getTypeArgument(), }, 0);
	public final static JavaSequence JAVA_31_0_0_3_0_0_2_0_0 = new JavaSequence(JavaCardinality.ONE, JAVA_31_0_0_3_0_0_2_0_0_0, JAVA_31_0_0_3_0_0_2_0_0_1);
	public final static JavaChoice JAVA_31_0_0_3_0_0_2_0 = new JavaChoice(JavaCardinality.ONE, JAVA_31_0_0_3_0_0_2_0_0);
	public final static JavaCompound JAVA_31_0_0_3_0_0_2 = new JavaCompound(JAVA_31_0_0_3_0_0_2_0, JavaCardinality.STAR);
	public final static JavaKeyword JAVA_31_0_0_3_0_0_3 = new JavaKeyword(">", JavaCardinality.ONE);
	public final static JavaSequence JAVA_31_0_0_3_0_0 = new JavaSequence(JavaCardinality.ONE, JAVA_31_0_0_3_0_0_0, JAVA_31_0_0_3_0_0_1, JAVA_31_0_0_3_0_0_2, JAVA_31_0_0_3_0_0_3);
	public final static JavaChoice JAVA_31_0_0_3_0 = new JavaChoice(JavaCardinality.ONE, JAVA_31_0_0_3_0_0);
	public final static JavaCompound JAVA_31_0_0_3 = new JavaCompound(JAVA_31_0_0_3_0, JavaCardinality.QUESTIONMARK);
	public final static JavaKeyword JAVA_31_0_0_4 = new JavaKeyword("(", JavaCardinality.ONE);
	public final static JavaContainment JAVA_31_0_0_5_0_0_0 = new JavaContainment(org.emftext.language.java.instantiations.InstantiationsPackage.eINSTANCE.getNewConstructorCall().getEStructuralFeature(org.emftext.language.java.instantiations.InstantiationsPackage.NEW_CONSTRUCTOR_CALL__ARGUMENTS), JavaCardinality.ONE, new org.eclipse.emf.ecore.EClass[] {org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getAssignmentExpression(), }, 0);
	public final static JavaKeyword JAVA_31_0_0_5_0_0_1_0_0_0 = new JavaKeyword(",", JavaCardinality.ONE);
	public final static JavaContainment JAVA_31_0_0_5_0_0_1_0_0_1 = new JavaContainment(org.emftext.language.java.instantiations.InstantiationsPackage.eINSTANCE.getNewConstructorCall().getEStructuralFeature(org.emftext.language.java.instantiations.InstantiationsPackage.NEW_CONSTRUCTOR_CALL__ARGUMENTS), JavaCardinality.ONE, new org.eclipse.emf.ecore.EClass[] {org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getAssignmentExpression(), }, 0);
	public final static JavaSequence JAVA_31_0_0_5_0_0_1_0_0 = new JavaSequence(JavaCardinality.ONE, JAVA_31_0_0_5_0_0_1_0_0_0, JAVA_31_0_0_5_0_0_1_0_0_1);
	public final static JavaChoice JAVA_31_0_0_5_0_0_1_0 = new JavaChoice(JavaCardinality.ONE, JAVA_31_0_0_5_0_0_1_0_0);
	public final static JavaCompound JAVA_31_0_0_5_0_0_1 = new JavaCompound(JAVA_31_0_0_5_0_0_1_0, JavaCardinality.STAR);
	public final static JavaSequence JAVA_31_0_0_5_0_0 = new JavaSequence(JavaCardinality.ONE, JAVA_31_0_0_5_0_0_0, JAVA_31_0_0_5_0_0_1);
	public final static JavaChoice JAVA_31_0_0_5_0 = new JavaChoice(JavaCardinality.ONE, JAVA_31_0_0_5_0_0);
	public final static JavaCompound JAVA_31_0_0_5 = new JavaCompound(JAVA_31_0_0_5_0, JavaCardinality.QUESTIONMARK);
	public final static JavaKeyword JAVA_31_0_0_6 = new JavaKeyword(")", JavaCardinality.ONE);
	public final static JavaContainment JAVA_31_0_0_7 = new JavaContainment(org.emftext.language.java.instantiations.InstantiationsPackage.eINSTANCE.getNewConstructorCall().getEStructuralFeature(org.emftext.language.java.instantiations.InstantiationsPackage.NEW_CONSTRUCTOR_CALL__ANONYMOUS_CLASS), JavaCardinality.QUESTIONMARK, new org.eclipse.emf.ecore.EClass[] {org.emftext.language.java.classifiers.ClassifiersPackage.eINSTANCE.getAnonymousClass(), }, 0);
	public final static JavaKeyword JAVA_31_0_0_8_0_0_0 = new JavaKeyword(".", JavaCardinality.ONE);
	public final static JavaContainment JAVA_31_0_0_8_0_0_1 = new JavaContainment(org.emftext.language.java.instantiations.InstantiationsPackage.eINSTANCE.getNewConstructorCall().getEStructuralFeature(org.emftext.language.java.instantiations.InstantiationsPackage.NEW_CONSTRUCTOR_CALL__NEXT), JavaCardinality.ONE, new org.eclipse.emf.ecore.EClass[] {org.emftext.language.java.references.ReferencesPackage.eINSTANCE.getReference(), }, 0);
	public final static JavaSequence JAVA_31_0_0_8_0_0 = new JavaSequence(JavaCardinality.ONE, JAVA_31_0_0_8_0_0_0, JAVA_31_0_0_8_0_0_1);
	public final static JavaChoice JAVA_31_0_0_8_0 = new JavaChoice(JavaCardinality.ONE, JAVA_31_0_0_8_0_0);
	public final static JavaCompound JAVA_31_0_0_8 = new JavaCompound(JAVA_31_0_0_8_0, JavaCardinality.QUESTIONMARK);
	public final static JavaSequence JAVA_31_0_0 = new JavaSequence(JavaCardinality.ONE, JAVA_31_0_0_0, JAVA_31_0_0_1, JAVA_31_0_0_2, JAVA_31_0_0_3, JAVA_31_0_0_4, JAVA_31_0_0_5, JAVA_31_0_0_6, JAVA_31_0_0_7, JAVA_31_0_0_8);
	public final static JavaChoice JAVA_31_0 = new JavaChoice(JavaCardinality.ONE, JAVA_31_0_0);
	public final static JavaRule JAVA_31 = new JavaRule(org.emftext.language.java.instantiations.InstantiationsPackage.eINSTANCE.getNewConstructorCall(), JAVA_31_0, JavaCardinality.ONE);
	public final static JavaKeyword JAVA_32_0_0_0_0_0_0 = new JavaKeyword("<", JavaCardinality.ONE);
	public final static JavaContainment JAVA_32_0_0_0_0_0_1 = new JavaContainment(org.emftext.language.java.instantiations.InstantiationsPackage.eINSTANCE.getExplicitConstructorCall().getEStructuralFeature(org.emftext.language.java.instantiations.InstantiationsPackage.EXPLICIT_CONSTRUCTOR_CALL__TYPE_ARGUMENTS), JavaCardinality.ONE, new org.eclipse.emf.ecore.EClass[] {org.emftext.language.java.generics.GenericsPackage.eINSTANCE.getTypeArgument(), }, 0);
	public final static JavaKeyword JAVA_32_0_0_0_0_0_2_0_0_0 = new JavaKeyword(",", JavaCardinality.ONE);
	public final static JavaContainment JAVA_32_0_0_0_0_0_2_0_0_1 = new JavaContainment(org.emftext.language.java.instantiations.InstantiationsPackage.eINSTANCE.getExplicitConstructorCall().getEStructuralFeature(org.emftext.language.java.instantiations.InstantiationsPackage.EXPLICIT_CONSTRUCTOR_CALL__TYPE_ARGUMENTS), JavaCardinality.ONE, new org.eclipse.emf.ecore.EClass[] {org.emftext.language.java.generics.GenericsPackage.eINSTANCE.getTypeArgument(), }, 0);
	public final static JavaSequence JAVA_32_0_0_0_0_0_2_0_0 = new JavaSequence(JavaCardinality.ONE, JAVA_32_0_0_0_0_0_2_0_0_0, JAVA_32_0_0_0_0_0_2_0_0_1);
	public final static JavaChoice JAVA_32_0_0_0_0_0_2_0 = new JavaChoice(JavaCardinality.ONE, JAVA_32_0_0_0_0_0_2_0_0);
	public final static JavaCompound JAVA_32_0_0_0_0_0_2 = new JavaCompound(JAVA_32_0_0_0_0_0_2_0, JavaCardinality.STAR);
	public final static JavaKeyword JAVA_32_0_0_0_0_0_3 = new JavaKeyword(">", JavaCardinality.ONE);
	public final static JavaSequence JAVA_32_0_0_0_0_0 = new JavaSequence(JavaCardinality.ONE, JAVA_32_0_0_0_0_0_0, JAVA_32_0_0_0_0_0_1, JAVA_32_0_0_0_0_0_2, JAVA_32_0_0_0_0_0_3);
	public final static JavaChoice JAVA_32_0_0_0_0 = new JavaChoice(JavaCardinality.ONE, JAVA_32_0_0_0_0_0);
	public final static JavaCompound JAVA_32_0_0_0 = new JavaCompound(JAVA_32_0_0_0_0, JavaCardinality.QUESTIONMARK);
	public final static JavaContainment JAVA_32_0_0_1 = new JavaContainment(org.emftext.language.java.instantiations.InstantiationsPackage.eINSTANCE.getExplicitConstructorCall().getEStructuralFeature(org.emftext.language.java.instantiations.InstantiationsPackage.EXPLICIT_CONSTRUCTOR_CALL__CALL_TARGET), JavaCardinality.ONE, new org.eclipse.emf.ecore.EClass[] {org.emftext.language.java.literals.LiteralsPackage.eINSTANCE.getSelf(), }, 0);
	public final static JavaKeyword JAVA_32_0_0_2 = new JavaKeyword("(", JavaCardinality.ONE);
	public final static JavaContainment JAVA_32_0_0_3_0_0_0 = new JavaContainment(org.emftext.language.java.instantiations.InstantiationsPackage.eINSTANCE.getExplicitConstructorCall().getEStructuralFeature(org.emftext.language.java.instantiations.InstantiationsPackage.EXPLICIT_CONSTRUCTOR_CALL__ARGUMENTS), JavaCardinality.ONE, new org.eclipse.emf.ecore.EClass[] {org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getAssignmentExpression(), }, 0);
	public final static JavaKeyword JAVA_32_0_0_3_0_0_1_0_0_0 = new JavaKeyword(",", JavaCardinality.ONE);
	public final static JavaContainment JAVA_32_0_0_3_0_0_1_0_0_1 = new JavaContainment(org.emftext.language.java.instantiations.InstantiationsPackage.eINSTANCE.getExplicitConstructorCall().getEStructuralFeature(org.emftext.language.java.instantiations.InstantiationsPackage.EXPLICIT_CONSTRUCTOR_CALL__ARGUMENTS), JavaCardinality.ONE, new org.eclipse.emf.ecore.EClass[] {org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getAssignmentExpression(), }, 0);
	public final static JavaSequence JAVA_32_0_0_3_0_0_1_0_0 = new JavaSequence(JavaCardinality.ONE, JAVA_32_0_0_3_0_0_1_0_0_0, JAVA_32_0_0_3_0_0_1_0_0_1);
	public final static JavaChoice JAVA_32_0_0_3_0_0_1_0 = new JavaChoice(JavaCardinality.ONE, JAVA_32_0_0_3_0_0_1_0_0);
	public final static JavaCompound JAVA_32_0_0_3_0_0_1 = new JavaCompound(JAVA_32_0_0_3_0_0_1_0, JavaCardinality.STAR);
	public final static JavaSequence JAVA_32_0_0_3_0_0 = new JavaSequence(JavaCardinality.ONE, JAVA_32_0_0_3_0_0_0, JAVA_32_0_0_3_0_0_1);
	public final static JavaChoice JAVA_32_0_0_3_0 = new JavaChoice(JavaCardinality.ONE, JAVA_32_0_0_3_0_0);
	public final static JavaCompound JAVA_32_0_0_3 = new JavaCompound(JAVA_32_0_0_3_0, JavaCardinality.QUESTIONMARK);
	public final static JavaKeyword JAVA_32_0_0_4 = new JavaKeyword(")", JavaCardinality.ONE);
	public final static JavaKeyword JAVA_32_0_0_5_0_0_0 = new JavaKeyword(".", JavaCardinality.ONE);
	public final static JavaContainment JAVA_32_0_0_5_0_0_1 = new JavaContainment(org.emftext.language.java.instantiations.InstantiationsPackage.eINSTANCE.getExplicitConstructorCall().getEStructuralFeature(org.emftext.language.java.instantiations.InstantiationsPackage.EXPLICIT_CONSTRUCTOR_CALL__NEXT), JavaCardinality.ONE, new org.eclipse.emf.ecore.EClass[] {org.emftext.language.java.references.ReferencesPackage.eINSTANCE.getReference(), }, 0);
	public final static JavaSequence JAVA_32_0_0_5_0_0 = new JavaSequence(JavaCardinality.ONE, JAVA_32_0_0_5_0_0_0, JAVA_32_0_0_5_0_0_1);
	public final static JavaChoice JAVA_32_0_0_5_0 = new JavaChoice(JavaCardinality.ONE, JAVA_32_0_0_5_0_0);
	public final static JavaCompound JAVA_32_0_0_5 = new JavaCompound(JAVA_32_0_0_5_0, JavaCardinality.QUESTIONMARK);
	public final static JavaSequence JAVA_32_0_0 = new JavaSequence(JavaCardinality.ONE, JAVA_32_0_0_0, JAVA_32_0_0_1, JAVA_32_0_0_2, JAVA_32_0_0_3, JAVA_32_0_0_4, JAVA_32_0_0_5);
	public final static JavaChoice JAVA_32_0 = new JavaChoice(JavaCardinality.ONE, JAVA_32_0_0);
	public final static JavaRule JAVA_32 = new JavaRule(org.emftext.language.java.instantiations.InstantiationsPackage.eINSTANCE.getExplicitConstructorCall(), JAVA_32_0, JavaCardinality.ONE);
	public final static JavaKeyword JAVA_33_0_0_0 = new JavaKeyword("new", JavaCardinality.ONE);
	public final static JavaContainment JAVA_33_0_0_1 = new JavaContainment(org.emftext.language.java.arrays.ArraysPackage.eINSTANCE.getArrayInstantiationByValuesTyped().getEStructuralFeature(org.emftext.language.java.arrays.ArraysPackage.ARRAY_INSTANTIATION_BY_VALUES_TYPED__TYPE_REFERENCE), JavaCardinality.ONE, new org.eclipse.emf.ecore.EClass[] {org.emftext.language.java.types.TypesPackage.eINSTANCE.getTypeReference(), }, 0);
	public final static JavaContainment JAVA_33_0_0_2 = new JavaContainment(org.emftext.language.java.arrays.ArraysPackage.eINSTANCE.getArrayInstantiationByValuesTyped().getEStructuralFeature(org.emftext.language.java.arrays.ArraysPackage.ARRAY_INSTANTIATION_BY_VALUES_TYPED__ARRAY_DIMENSIONS_BEFORE), JavaCardinality.PLUS, new org.eclipse.emf.ecore.EClass[] {org.emftext.language.java.arrays.ArraysPackage.eINSTANCE.getArrayDimension(), }, 0);
	public final static JavaContainment JAVA_33_0_0_3 = new JavaContainment(org.emftext.language.java.arrays.ArraysPackage.eINSTANCE.getArrayInstantiationByValuesTyped().getEStructuralFeature(org.emftext.language.java.arrays.ArraysPackage.ARRAY_INSTANTIATION_BY_VALUES_TYPED__ARRAY_INITIALIZER), JavaCardinality.ONE, new org.eclipse.emf.ecore.EClass[] {org.emftext.language.java.arrays.ArraysPackage.eINSTANCE.getArrayInitializer(), }, 0);
	public final static JavaContainment JAVA_33_0_0_4 = new JavaContainment(org.emftext.language.java.arrays.ArraysPackage.eINSTANCE.getArrayInstantiationByValuesTyped().getEStructuralFeature(org.emftext.language.java.arrays.ArraysPackage.ARRAY_INSTANTIATION_BY_VALUES_TYPED__ARRAY_SELECTORS), JavaCardinality.STAR, new org.eclipse.emf.ecore.EClass[] {org.emftext.language.java.arrays.ArraysPackage.eINSTANCE.getArraySelector(), }, 0);
	public final static JavaKeyword JAVA_33_0_0_5_0_0_0 = new JavaKeyword(".", JavaCardinality.ONE);
	public final static JavaContainment JAVA_33_0_0_5_0_0_1 = new JavaContainment(org.emftext.language.java.arrays.ArraysPackage.eINSTANCE.getArrayInstantiationByValuesTyped().getEStructuralFeature(org.emftext.language.java.arrays.ArraysPackage.ARRAY_INSTANTIATION_BY_VALUES_TYPED__NEXT), JavaCardinality.ONE, new org.eclipse.emf.ecore.EClass[] {org.emftext.language.java.references.ReferencesPackage.eINSTANCE.getReference(), }, 0);
	public final static JavaSequence JAVA_33_0_0_5_0_0 = new JavaSequence(JavaCardinality.ONE, JAVA_33_0_0_5_0_0_0, JAVA_33_0_0_5_0_0_1);
	public final static JavaChoice JAVA_33_0_0_5_0 = new JavaChoice(JavaCardinality.ONE, JAVA_33_0_0_5_0_0);
	public final static JavaCompound JAVA_33_0_0_5 = new JavaCompound(JAVA_33_0_0_5_0, JavaCardinality.QUESTIONMARK);
	public final static JavaSequence JAVA_33_0_0 = new JavaSequence(JavaCardinality.ONE, JAVA_33_0_0_0, JAVA_33_0_0_1, JAVA_33_0_0_2, JAVA_33_0_0_3, JAVA_33_0_0_4, JAVA_33_0_0_5);
	public final static JavaChoice JAVA_33_0 = new JavaChoice(JavaCardinality.ONE, JAVA_33_0_0);
	public final static JavaRule JAVA_33 = new JavaRule(org.emftext.language.java.arrays.ArraysPackage.eINSTANCE.getArrayInstantiationByValuesTyped(), JAVA_33_0, JavaCardinality.ONE);
	public final static JavaContainment JAVA_34_0_0_0 = new JavaContainment(org.emftext.language.java.arrays.ArraysPackage.eINSTANCE.getArrayInstantiationByValuesUntyped().getEStructuralFeature(org.emftext.language.java.arrays.ArraysPackage.ARRAY_INSTANTIATION_BY_VALUES_UNTYPED__ARRAY_INITIALIZER), JavaCardinality.ONE, new org.eclipse.emf.ecore.EClass[] {org.emftext.language.java.arrays.ArraysPackage.eINSTANCE.getArrayInitializer(), }, 0);
	public final static JavaContainment JAVA_34_0_0_1 = new JavaContainment(org.emftext.language.java.arrays.ArraysPackage.eINSTANCE.getArrayInstantiationByValuesUntyped().getEStructuralFeature(org.emftext.language.java.arrays.ArraysPackage.ARRAY_INSTANTIATION_BY_VALUES_UNTYPED__ARRAY_SELECTORS), JavaCardinality.STAR, new org.eclipse.emf.ecore.EClass[] {org.emftext.language.java.arrays.ArraysPackage.eINSTANCE.getArraySelector(), }, 0);
	public final static JavaKeyword JAVA_34_0_0_2_0_0_0 = new JavaKeyword(".", JavaCardinality.ONE);
	public final static JavaContainment JAVA_34_0_0_2_0_0_1 = new JavaContainment(org.emftext.language.java.arrays.ArraysPackage.eINSTANCE.getArrayInstantiationByValuesUntyped().getEStructuralFeature(org.emftext.language.java.arrays.ArraysPackage.ARRAY_INSTANTIATION_BY_VALUES_UNTYPED__NEXT), JavaCardinality.ONE, new org.eclipse.emf.ecore.EClass[] {org.emftext.language.java.references.ReferencesPackage.eINSTANCE.getReference(), }, 0);
	public final static JavaSequence JAVA_34_0_0_2_0_0 = new JavaSequence(JavaCardinality.ONE, JAVA_34_0_0_2_0_0_0, JAVA_34_0_0_2_0_0_1);
	public final static JavaChoice JAVA_34_0_0_2_0 = new JavaChoice(JavaCardinality.ONE, JAVA_34_0_0_2_0_0);
	public final static JavaCompound JAVA_34_0_0_2 = new JavaCompound(JAVA_34_0_0_2_0, JavaCardinality.QUESTIONMARK);
	public final static JavaSequence JAVA_34_0_0 = new JavaSequence(JavaCardinality.ONE, JAVA_34_0_0_0, JAVA_34_0_0_1, JAVA_34_0_0_2);
	public final static JavaChoice JAVA_34_0 = new JavaChoice(JavaCardinality.ONE, JAVA_34_0_0);
	public final static JavaRule JAVA_34 = new JavaRule(org.emftext.language.java.arrays.ArraysPackage.eINSTANCE.getArrayInstantiationByValuesUntyped(), JAVA_34_0, JavaCardinality.ONE);
	public final static JavaKeyword JAVA_35_0_0_0 = new JavaKeyword("new", JavaCardinality.ONE);
	public final static JavaContainment JAVA_35_0_0_1 = new JavaContainment(org.emftext.language.java.arrays.ArraysPackage.eINSTANCE.getArrayInstantiationBySize().getEStructuralFeature(org.emftext.language.java.arrays.ArraysPackage.ARRAY_INSTANTIATION_BY_SIZE__TYPE_REFERENCE), JavaCardinality.ONE, new org.eclipse.emf.ecore.EClass[] {org.emftext.language.java.types.TypesPackage.eINSTANCE.getTypeReference(), }, 0);
	public final static JavaKeyword JAVA_35_0_0_2_0_0_0 = new JavaKeyword("[", JavaCardinality.ONE);
	public final static JavaContainment JAVA_35_0_0_2_0_0_1 = new JavaContainment(org.emftext.language.java.arrays.ArraysPackage.eINSTANCE.getArrayInstantiationBySize().getEStructuralFeature(org.emftext.language.java.arrays.ArraysPackage.ARRAY_INSTANTIATION_BY_SIZE__SIZES), JavaCardinality.ONE, new org.eclipse.emf.ecore.EClass[] {org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getAssignmentExpression(), }, 0);
	public final static JavaKeyword JAVA_35_0_0_2_0_0_2 = new JavaKeyword("]", JavaCardinality.ONE);
	public final static JavaSequence JAVA_35_0_0_2_0_0 = new JavaSequence(JavaCardinality.ONE, JAVA_35_0_0_2_0_0_0, JAVA_35_0_0_2_0_0_1, JAVA_35_0_0_2_0_0_2);
	public final static JavaChoice JAVA_35_0_0_2_0 = new JavaChoice(JavaCardinality.ONE, JAVA_35_0_0_2_0_0);
	public final static JavaCompound JAVA_35_0_0_2 = new JavaCompound(JAVA_35_0_0_2_0, JavaCardinality.PLUS);
	public final static JavaContainment JAVA_35_0_0_3 = new JavaContainment(org.emftext.language.java.arrays.ArraysPackage.eINSTANCE.getArrayInstantiationBySize().getEStructuralFeature(org.emftext.language.java.arrays.ArraysPackage.ARRAY_INSTANTIATION_BY_SIZE__ARRAY_DIMENSIONS_BEFORE), JavaCardinality.STAR, new org.eclipse.emf.ecore.EClass[] {org.emftext.language.java.arrays.ArraysPackage.eINSTANCE.getArrayDimension(), }, 0);
	public final static JavaKeyword JAVA_35_0_0_4_0_0_0 = new JavaKeyword(".", JavaCardinality.ONE);
	public final static JavaContainment JAVA_35_0_0_4_0_0_1 = new JavaContainment(org.emftext.language.java.arrays.ArraysPackage.eINSTANCE.getArrayInstantiationBySize().getEStructuralFeature(org.emftext.language.java.arrays.ArraysPackage.ARRAY_INSTANTIATION_BY_SIZE__NEXT), JavaCardinality.ONE, new org.eclipse.emf.ecore.EClass[] {org.emftext.language.java.references.ReferencesPackage.eINSTANCE.getReference(), }, 0);
	public final static JavaSequence JAVA_35_0_0_4_0_0 = new JavaSequence(JavaCardinality.ONE, JAVA_35_0_0_4_0_0_0, JAVA_35_0_0_4_0_0_1);
	public final static JavaChoice JAVA_35_0_0_4_0 = new JavaChoice(JavaCardinality.ONE, JAVA_35_0_0_4_0_0);
	public final static JavaCompound JAVA_35_0_0_4 = new JavaCompound(JAVA_35_0_0_4_0, JavaCardinality.QUESTIONMARK);
	public final static JavaSequence JAVA_35_0_0 = new JavaSequence(JavaCardinality.ONE, JAVA_35_0_0_0, JAVA_35_0_0_1, JAVA_35_0_0_2, JAVA_35_0_0_3, JAVA_35_0_0_4);
	public final static JavaChoice JAVA_35_0 = new JavaChoice(JavaCardinality.ONE, JAVA_35_0_0);
	public final static JavaRule JAVA_35 = new JavaRule(org.emftext.language.java.arrays.ArraysPackage.eINSTANCE.getArrayInstantiationBySize(), JAVA_35_0, JavaCardinality.ONE);
	public final static JavaWhiteSpace JAVA_36_0_0_0 = new JavaWhiteSpace(1, JavaCardinality.ONE);
	public final static JavaKeyword JAVA_36_0_0_1 = new JavaKeyword("{", JavaCardinality.ONE);
	public final static JavaContainment JAVA_36_0_0_2_0_0_0 = new JavaContainment(org.emftext.language.java.arrays.ArraysPackage.eINSTANCE.getArrayInitializer().getEStructuralFeature(org.emftext.language.java.arrays.ArraysPackage.ARRAY_INITIALIZER__INITIAL_VALUES), JavaCardinality.ONE, new org.eclipse.emf.ecore.EClass[] {org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getAssignmentExpression(), org.emftext.language.java.arrays.ArraysPackage.eINSTANCE.getArrayInitializer(), }, 0);
	public final static JavaKeyword JAVA_36_0_0_2_0_0_1_0_0_0 = new JavaKeyword(",", JavaCardinality.ONE);
	public final static JavaContainment JAVA_36_0_0_2_0_0_1_0_0_1 = new JavaContainment(org.emftext.language.java.arrays.ArraysPackage.eINSTANCE.getArrayInitializer().getEStructuralFeature(org.emftext.language.java.arrays.ArraysPackage.ARRAY_INITIALIZER__INITIAL_VALUES), JavaCardinality.ONE, new org.eclipse.emf.ecore.EClass[] {org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getAssignmentExpression(), org.emftext.language.java.arrays.ArraysPackage.eINSTANCE.getArrayInitializer(), }, 0);
	public final static JavaSequence JAVA_36_0_0_2_0_0_1_0_0 = new JavaSequence(JavaCardinality.ONE, JAVA_36_0_0_2_0_0_1_0_0_0, JAVA_36_0_0_2_0_0_1_0_0_1);
	public final static JavaChoice JAVA_36_0_0_2_0_0_1_0 = new JavaChoice(JavaCardinality.ONE, JAVA_36_0_0_2_0_0_1_0_0);
	public final static JavaCompound JAVA_36_0_0_2_0_0_1 = new JavaCompound(JAVA_36_0_0_2_0_0_1_0, JavaCardinality.STAR);
	public final static JavaSequence JAVA_36_0_0_2_0_0 = new JavaSequence(JavaCardinality.ONE, JAVA_36_0_0_2_0_0_0, JAVA_36_0_0_2_0_0_1);
	public final static JavaChoice JAVA_36_0_0_2_0 = new JavaChoice(JavaCardinality.ONE, JAVA_36_0_0_2_0_0);
	public final static JavaCompound JAVA_36_0_0_2 = new JavaCompound(JAVA_36_0_0_2_0, JavaCardinality.QUESTIONMARK);
	public final static JavaKeyword JAVA_36_0_0_3_0_0_0 = new JavaKeyword(",", JavaCardinality.ONE);
	public final static JavaSequence JAVA_36_0_0_3_0_0 = new JavaSequence(JavaCardinality.ONE, JAVA_36_0_0_3_0_0_0);
	public final static JavaChoice JAVA_36_0_0_3_0 = new JavaChoice(JavaCardinality.ONE, JAVA_36_0_0_3_0_0);
	public final static JavaCompound JAVA_36_0_0_3 = new JavaCompound(JAVA_36_0_0_3_0, JavaCardinality.QUESTIONMARK);
	public final static JavaKeyword JAVA_36_0_0_4 = new JavaKeyword("}", JavaCardinality.ONE);
	public final static JavaSequence JAVA_36_0_0 = new JavaSequence(JavaCardinality.ONE, JAVA_36_0_0_0, JAVA_36_0_0_1, JAVA_36_0_0_2, JAVA_36_0_0_3, JAVA_36_0_0_4);
	public final static JavaChoice JAVA_36_0 = new JavaChoice(JavaCardinality.ONE, JAVA_36_0_0);
	public final static JavaRule JAVA_36 = new JavaRule(org.emftext.language.java.arrays.ArraysPackage.eINSTANCE.getArrayInitializer(), JAVA_36_0, JavaCardinality.ONE);
	public final static JavaKeyword JAVA_37_0_0_0 = new JavaKeyword("[", JavaCardinality.ONE);
	public final static JavaContainment JAVA_37_0_0_1 = new JavaContainment(org.emftext.language.java.arrays.ArraysPackage.eINSTANCE.getArraySelector().getEStructuralFeature(org.emftext.language.java.arrays.ArraysPackage.ARRAY_SELECTOR__POSITION), JavaCardinality.QUESTIONMARK, new org.eclipse.emf.ecore.EClass[] {org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getAssignmentExpression(), }, 0);
	public final static JavaKeyword JAVA_37_0_0_2 = new JavaKeyword("]", JavaCardinality.ONE);
	public final static JavaSequence JAVA_37_0_0 = new JavaSequence(JavaCardinality.ONE, JAVA_37_0_0_0, JAVA_37_0_0_1, JAVA_37_0_0_2);
	public final static JavaChoice JAVA_37_0 = new JavaChoice(JavaCardinality.ONE, JAVA_37_0_0);
	public final static JavaRule JAVA_37 = new JavaRule(org.emftext.language.java.arrays.ArraysPackage.eINSTANCE.getArraySelector(), JAVA_37_0, JavaCardinality.ONE);
	public final static JavaPlaceholder JAVA_38_0_0_0_0_0_0 = new JavaPlaceholder(org.emftext.language.java.types.TypesPackage.eINSTANCE.getNamespaceClassifierReference().getEStructuralFeature(org.emftext.language.java.types.TypesPackage.NAMESPACE_CLASSIFIER_REFERENCE__NAMESPACES), "IDENTIFIER", JavaCardinality.ONE, 0);
	public final static JavaKeyword JAVA_38_0_0_0_0_0_1 = new JavaKeyword(".", JavaCardinality.ONE);
	public final static JavaSequence JAVA_38_0_0_0_0_0 = new JavaSequence(JavaCardinality.ONE, JAVA_38_0_0_0_0_0_0, JAVA_38_0_0_0_0_0_1);
	public final static JavaChoice JAVA_38_0_0_0_0 = new JavaChoice(JavaCardinality.ONE, JAVA_38_0_0_0_0_0);
	public final static JavaCompound JAVA_38_0_0_0 = new JavaCompound(JAVA_38_0_0_0_0, JavaCardinality.STAR);
	public final static JavaContainment JAVA_38_0_0_1_0_0_0 = new JavaContainment(org.emftext.language.java.types.TypesPackage.eINSTANCE.getNamespaceClassifierReference().getEStructuralFeature(org.emftext.language.java.types.TypesPackage.NAMESPACE_CLASSIFIER_REFERENCE__CLASSIFIER_REFERENCES), JavaCardinality.ONE, new org.eclipse.emf.ecore.EClass[] {org.emftext.language.java.types.TypesPackage.eINSTANCE.getClassifierReference(), }, 1);
	public final static JavaKeyword JAVA_38_0_0_1_0_0_1 = new JavaKeyword(".", JavaCardinality.ONE);
	public final static JavaSequence JAVA_38_0_0_1_0_0 = new JavaSequence(JavaCardinality.ONE, JAVA_38_0_0_1_0_0_0, JAVA_38_0_0_1_0_0_1);
	public final static JavaChoice JAVA_38_0_0_1_0 = new JavaChoice(JavaCardinality.ONE, JAVA_38_0_0_1_0_0);
	public final static JavaCompound JAVA_38_0_0_1 = new JavaCompound(JAVA_38_0_0_1_0, JavaCardinality.STAR);
	public final static JavaContainment JAVA_38_0_0_2 = new JavaContainment(org.emftext.language.java.types.TypesPackage.eINSTANCE.getNamespaceClassifierReference().getEStructuralFeature(org.emftext.language.java.types.TypesPackage.NAMESPACE_CLASSIFIER_REFERENCE__CLASSIFIER_REFERENCES), JavaCardinality.ONE, new org.eclipse.emf.ecore.EClass[] {org.emftext.language.java.types.TypesPackage.eINSTANCE.getClassifierReference(), }, 0);
	public final static JavaSequence JAVA_38_0_0 = new JavaSequence(JavaCardinality.ONE, JAVA_38_0_0_0, JAVA_38_0_0_1, JAVA_38_0_0_2);
	public final static JavaChoice JAVA_38_0 = new JavaChoice(JavaCardinality.ONE, JAVA_38_0_0);
	public final static JavaRule JAVA_38 = new JavaRule(org.emftext.language.java.types.TypesPackage.eINSTANCE.getNamespaceClassifierReference(), JAVA_38_0, JavaCardinality.ONE);
	public final static JavaPlaceholder JAVA_39_0_0_0 = new JavaPlaceholder(org.emftext.language.java.types.TypesPackage.eINSTANCE.getClassifierReference().getEStructuralFeature(org.emftext.language.java.types.TypesPackage.CLASSIFIER_REFERENCE__TARGET), "IDENTIFIER", JavaCardinality.ONE, 0);
	public final static JavaKeyword JAVA_39_0_0_1_0_0_0 = new JavaKeyword("<", JavaCardinality.ONE);
	public final static JavaContainment JAVA_39_0_0_1_0_0_1 = new JavaContainment(org.emftext.language.java.types.TypesPackage.eINSTANCE.getClassifierReference().getEStructuralFeature(org.emftext.language.java.types.TypesPackage.CLASSIFIER_REFERENCE__TYPE_ARGUMENTS), JavaCardinality.ONE, new org.eclipse.emf.ecore.EClass[] {org.emftext.language.java.generics.GenericsPackage.eINSTANCE.getTypeArgument(), }, 0);
	public final static JavaKeyword JAVA_39_0_0_1_0_0_2_0_0_0 = new JavaKeyword(",", JavaCardinality.ONE);
	public final static JavaContainment JAVA_39_0_0_1_0_0_2_0_0_1 = new JavaContainment(org.emftext.language.java.types.TypesPackage.eINSTANCE.getClassifierReference().getEStructuralFeature(org.emftext.language.java.types.TypesPackage.CLASSIFIER_REFERENCE__TYPE_ARGUMENTS), JavaCardinality.ONE, new org.eclipse.emf.ecore.EClass[] {org.emftext.language.java.generics.GenericsPackage.eINSTANCE.getTypeArgument(), }, 0);
	public final static JavaSequence JAVA_39_0_0_1_0_0_2_0_0 = new JavaSequence(JavaCardinality.ONE, JAVA_39_0_0_1_0_0_2_0_0_0, JAVA_39_0_0_1_0_0_2_0_0_1);
	public final static JavaChoice JAVA_39_0_0_1_0_0_2_0 = new JavaChoice(JavaCardinality.ONE, JAVA_39_0_0_1_0_0_2_0_0);
	public final static JavaCompound JAVA_39_0_0_1_0_0_2 = new JavaCompound(JAVA_39_0_0_1_0_0_2_0, JavaCardinality.STAR);
	public final static JavaKeyword JAVA_39_0_0_1_0_0_3 = new JavaKeyword(">", JavaCardinality.ONE);
	public final static JavaSequence JAVA_39_0_0_1_0_0 = new JavaSequence(JavaCardinality.ONE, JAVA_39_0_0_1_0_0_0, JAVA_39_0_0_1_0_0_1, JAVA_39_0_0_1_0_0_2, JAVA_39_0_0_1_0_0_3);
	public final static JavaChoice JAVA_39_0_0_1_0 = new JavaChoice(JavaCardinality.ONE, JAVA_39_0_0_1_0_0);
	public final static JavaCompound JAVA_39_0_0_1 = new JavaCompound(JAVA_39_0_0_1_0, JavaCardinality.QUESTIONMARK);
	public final static JavaSequence JAVA_39_0_0 = new JavaSequence(JavaCardinality.ONE, JAVA_39_0_0_0, JAVA_39_0_0_1);
	public final static JavaChoice JAVA_39_0 = new JavaChoice(JavaCardinality.ONE, JAVA_39_0_0);
	public final static JavaRule JAVA_39 = new JavaRule(org.emftext.language.java.types.TypesPackage.eINSTANCE.getClassifierReference(), JAVA_39_0, JavaCardinality.ONE);
	public final static JavaKeyword JAVA_40_0_0_0_0_0_0 = new JavaKeyword("<", JavaCardinality.ONE);
	public final static JavaContainment JAVA_40_0_0_0_0_0_1 = new JavaContainment(org.emftext.language.java.references.ReferencesPackage.eINSTANCE.getMethodCall().getEStructuralFeature(org.emftext.language.java.references.ReferencesPackage.METHOD_CALL__CALL_TYPE_ARGUMENTS), JavaCardinality.ONE, new org.eclipse.emf.ecore.EClass[] {org.emftext.language.java.generics.GenericsPackage.eINSTANCE.getTypeArgument(), }, 0);
	public final static JavaKeyword JAVA_40_0_0_0_0_0_2_0_0_0 = new JavaKeyword(",", JavaCardinality.ONE);
	public final static JavaContainment JAVA_40_0_0_0_0_0_2_0_0_1 = new JavaContainment(org.emftext.language.java.references.ReferencesPackage.eINSTANCE.getMethodCall().getEStructuralFeature(org.emftext.language.java.references.ReferencesPackage.METHOD_CALL__CALL_TYPE_ARGUMENTS), JavaCardinality.ONE, new org.eclipse.emf.ecore.EClass[] {org.emftext.language.java.generics.GenericsPackage.eINSTANCE.getTypeArgument(), }, 0);
	public final static JavaSequence JAVA_40_0_0_0_0_0_2_0_0 = new JavaSequence(JavaCardinality.ONE, JAVA_40_0_0_0_0_0_2_0_0_0, JAVA_40_0_0_0_0_0_2_0_0_1);
	public final static JavaChoice JAVA_40_0_0_0_0_0_2_0 = new JavaChoice(JavaCardinality.ONE, JAVA_40_0_0_0_0_0_2_0_0);
	public final static JavaCompound JAVA_40_0_0_0_0_0_2 = new JavaCompound(JAVA_40_0_0_0_0_0_2_0, JavaCardinality.STAR);
	public final static JavaKeyword JAVA_40_0_0_0_0_0_3 = new JavaKeyword(">", JavaCardinality.ONE);
	public final static JavaSequence JAVA_40_0_0_0_0_0 = new JavaSequence(JavaCardinality.ONE, JAVA_40_0_0_0_0_0_0, JAVA_40_0_0_0_0_0_1, JAVA_40_0_0_0_0_0_2, JAVA_40_0_0_0_0_0_3);
	public final static JavaChoice JAVA_40_0_0_0_0 = new JavaChoice(JavaCardinality.ONE, JAVA_40_0_0_0_0_0);
	public final static JavaCompound JAVA_40_0_0_0 = new JavaCompound(JAVA_40_0_0_0_0, JavaCardinality.QUESTIONMARK);
	public final static JavaPlaceholder JAVA_40_0_0_1 = new JavaPlaceholder(org.emftext.language.java.references.ReferencesPackage.eINSTANCE.getMethodCall().getEStructuralFeature(org.emftext.language.java.references.ReferencesPackage.METHOD_CALL__TARGET), "IDENTIFIER", JavaCardinality.ONE, 0);
	public final static JavaKeyword JAVA_40_0_0_2_0_0_0 = new JavaKeyword("<", JavaCardinality.ONE);
	public final static JavaContainment JAVA_40_0_0_2_0_0_1 = new JavaContainment(org.emftext.language.java.references.ReferencesPackage.eINSTANCE.getMethodCall().getEStructuralFeature(org.emftext.language.java.references.ReferencesPackage.METHOD_CALL__TYPE_ARGUMENTS), JavaCardinality.ONE, new org.eclipse.emf.ecore.EClass[] {org.emftext.language.java.generics.GenericsPackage.eINSTANCE.getTypeArgument(), }, 0);
	public final static JavaKeyword JAVA_40_0_0_2_0_0_2_0_0_0 = new JavaKeyword(",", JavaCardinality.ONE);
	public final static JavaContainment JAVA_40_0_0_2_0_0_2_0_0_1 = new JavaContainment(org.emftext.language.java.references.ReferencesPackage.eINSTANCE.getMethodCall().getEStructuralFeature(org.emftext.language.java.references.ReferencesPackage.METHOD_CALL__TYPE_ARGUMENTS), JavaCardinality.ONE, new org.eclipse.emf.ecore.EClass[] {org.emftext.language.java.generics.GenericsPackage.eINSTANCE.getTypeArgument(), }, 0);
	public final static JavaSequence JAVA_40_0_0_2_0_0_2_0_0 = new JavaSequence(JavaCardinality.ONE, JAVA_40_0_0_2_0_0_2_0_0_0, JAVA_40_0_0_2_0_0_2_0_0_1);
	public final static JavaChoice JAVA_40_0_0_2_0_0_2_0 = new JavaChoice(JavaCardinality.ONE, JAVA_40_0_0_2_0_0_2_0_0);
	public final static JavaCompound JAVA_40_0_0_2_0_0_2 = new JavaCompound(JAVA_40_0_0_2_0_0_2_0, JavaCardinality.STAR);
	public final static JavaKeyword JAVA_40_0_0_2_0_0_3 = new JavaKeyword(">", JavaCardinality.ONE);
	public final static JavaSequence JAVA_40_0_0_2_0_0 = new JavaSequence(JavaCardinality.ONE, JAVA_40_0_0_2_0_0_0, JAVA_40_0_0_2_0_0_1, JAVA_40_0_0_2_0_0_2, JAVA_40_0_0_2_0_0_3);
	public final static JavaChoice JAVA_40_0_0_2_0 = new JavaChoice(JavaCardinality.ONE, JAVA_40_0_0_2_0_0);
	public final static JavaCompound JAVA_40_0_0_2 = new JavaCompound(JAVA_40_0_0_2_0, JavaCardinality.QUESTIONMARK);
	public final static JavaKeyword JAVA_40_0_0_3 = new JavaKeyword("(", JavaCardinality.ONE);
	public final static JavaContainment JAVA_40_0_0_4_0_0_0 = new JavaContainment(org.emftext.language.java.references.ReferencesPackage.eINSTANCE.getMethodCall().getEStructuralFeature(org.emftext.language.java.references.ReferencesPackage.METHOD_CALL__ARGUMENTS), JavaCardinality.ONE, new org.eclipse.emf.ecore.EClass[] {org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getAssignmentExpression(), }, 0);
	public final static JavaKeyword JAVA_40_0_0_4_0_0_1_0_0_0 = new JavaKeyword(",", JavaCardinality.ONE);
	public final static JavaContainment JAVA_40_0_0_4_0_0_1_0_0_1 = new JavaContainment(org.emftext.language.java.references.ReferencesPackage.eINSTANCE.getMethodCall().getEStructuralFeature(org.emftext.language.java.references.ReferencesPackage.METHOD_CALL__ARGUMENTS), JavaCardinality.ONE, new org.eclipse.emf.ecore.EClass[] {org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getAssignmentExpression(), }, 0);
	public final static JavaSequence JAVA_40_0_0_4_0_0_1_0_0 = new JavaSequence(JavaCardinality.ONE, JAVA_40_0_0_4_0_0_1_0_0_0, JAVA_40_0_0_4_0_0_1_0_0_1);
	public final static JavaChoice JAVA_40_0_0_4_0_0_1_0 = new JavaChoice(JavaCardinality.ONE, JAVA_40_0_0_4_0_0_1_0_0);
	public final static JavaCompound JAVA_40_0_0_4_0_0_1 = new JavaCompound(JAVA_40_0_0_4_0_0_1_0, JavaCardinality.STAR);
	public final static JavaSequence JAVA_40_0_0_4_0_0 = new JavaSequence(JavaCardinality.ONE, JAVA_40_0_0_4_0_0_0, JAVA_40_0_0_4_0_0_1);
	public final static JavaChoice JAVA_40_0_0_4_0 = new JavaChoice(JavaCardinality.ONE, JAVA_40_0_0_4_0_0);
	public final static JavaCompound JAVA_40_0_0_4 = new JavaCompound(JAVA_40_0_0_4_0, JavaCardinality.QUESTIONMARK);
	public final static JavaKeyword JAVA_40_0_0_5 = new JavaKeyword(")", JavaCardinality.ONE);
	public final static JavaContainment JAVA_40_0_0_6 = new JavaContainment(org.emftext.language.java.references.ReferencesPackage.eINSTANCE.getMethodCall().getEStructuralFeature(org.emftext.language.java.references.ReferencesPackage.METHOD_CALL__ARRAY_SELECTORS), JavaCardinality.STAR, new org.eclipse.emf.ecore.EClass[] {org.emftext.language.java.arrays.ArraysPackage.eINSTANCE.getArraySelector(), }, 0);
	public final static JavaKeyword JAVA_40_0_0_7_0_0_0 = new JavaKeyword(".", JavaCardinality.ONE);
	public final static JavaContainment JAVA_40_0_0_7_0_0_1 = new JavaContainment(org.emftext.language.java.references.ReferencesPackage.eINSTANCE.getMethodCall().getEStructuralFeature(org.emftext.language.java.references.ReferencesPackage.METHOD_CALL__NEXT), JavaCardinality.ONE, new org.eclipse.emf.ecore.EClass[] {org.emftext.language.java.references.ReferencesPackage.eINSTANCE.getReference(), }, 0);
	public final static JavaSequence JAVA_40_0_0_7_0_0 = new JavaSequence(JavaCardinality.ONE, JAVA_40_0_0_7_0_0_0, JAVA_40_0_0_7_0_0_1);
	public final static JavaChoice JAVA_40_0_0_7_0 = new JavaChoice(JavaCardinality.ONE, JAVA_40_0_0_7_0_0);
	public final static JavaCompound JAVA_40_0_0_7 = new JavaCompound(JAVA_40_0_0_7_0, JavaCardinality.QUESTIONMARK);
	public final static JavaSequence JAVA_40_0_0 = new JavaSequence(JavaCardinality.ONE, JAVA_40_0_0_0, JAVA_40_0_0_1, JAVA_40_0_0_2, JAVA_40_0_0_3, JAVA_40_0_0_4, JAVA_40_0_0_5, JAVA_40_0_0_6, JAVA_40_0_0_7);
	public final static JavaChoice JAVA_40_0 = new JavaChoice(JavaCardinality.ONE, JAVA_40_0_0);
	public final static JavaRule JAVA_40 = new JavaRule(org.emftext.language.java.references.ReferencesPackage.eINSTANCE.getMethodCall(), JAVA_40_0, JavaCardinality.ONE);
	public final static JavaPlaceholder JAVA_41_0_0_0 = new JavaPlaceholder(org.emftext.language.java.references.ReferencesPackage.eINSTANCE.getIdentifierReference().getEStructuralFeature(org.emftext.language.java.references.ReferencesPackage.IDENTIFIER_REFERENCE__TARGET), "IDENTIFIER", JavaCardinality.ONE, 0);
	public final static JavaKeyword JAVA_41_0_0_1_0_0_0 = new JavaKeyword("<", JavaCardinality.ONE);
	public final static JavaContainment JAVA_41_0_0_1_0_0_1 = new JavaContainment(org.emftext.language.java.references.ReferencesPackage.eINSTANCE.getIdentifierReference().getEStructuralFeature(org.emftext.language.java.references.ReferencesPackage.IDENTIFIER_REFERENCE__TYPE_ARGUMENTS), JavaCardinality.ONE, new org.eclipse.emf.ecore.EClass[] {org.emftext.language.java.generics.GenericsPackage.eINSTANCE.getTypeArgument(), }, 0);
	public final static JavaKeyword JAVA_41_0_0_1_0_0_2_0_0_0 = new JavaKeyword(",", JavaCardinality.ONE);
	public final static JavaContainment JAVA_41_0_0_1_0_0_2_0_0_1 = new JavaContainment(org.emftext.language.java.references.ReferencesPackage.eINSTANCE.getIdentifierReference().getEStructuralFeature(org.emftext.language.java.references.ReferencesPackage.IDENTIFIER_REFERENCE__TYPE_ARGUMENTS), JavaCardinality.ONE, new org.eclipse.emf.ecore.EClass[] {org.emftext.language.java.generics.GenericsPackage.eINSTANCE.getTypeArgument(), }, 0);
	public final static JavaSequence JAVA_41_0_0_1_0_0_2_0_0 = new JavaSequence(JavaCardinality.ONE, JAVA_41_0_0_1_0_0_2_0_0_0, JAVA_41_0_0_1_0_0_2_0_0_1);
	public final static JavaChoice JAVA_41_0_0_1_0_0_2_0 = new JavaChoice(JavaCardinality.ONE, JAVA_41_0_0_1_0_0_2_0_0);
	public final static JavaCompound JAVA_41_0_0_1_0_0_2 = new JavaCompound(JAVA_41_0_0_1_0_0_2_0, JavaCardinality.STAR);
	public final static JavaKeyword JAVA_41_0_0_1_0_0_3 = new JavaKeyword(">", JavaCardinality.ONE);
	public final static JavaSequence JAVA_41_0_0_1_0_0 = new JavaSequence(JavaCardinality.ONE, JAVA_41_0_0_1_0_0_0, JAVA_41_0_0_1_0_0_1, JAVA_41_0_0_1_0_0_2, JAVA_41_0_0_1_0_0_3);
	public final static JavaChoice JAVA_41_0_0_1_0 = new JavaChoice(JavaCardinality.ONE, JAVA_41_0_0_1_0_0);
	public final static JavaCompound JAVA_41_0_0_1 = new JavaCompound(JAVA_41_0_0_1_0, JavaCardinality.QUESTIONMARK);
	public final static JavaContainment JAVA_41_0_0_2 = new JavaContainment(org.emftext.language.java.references.ReferencesPackage.eINSTANCE.getIdentifierReference().getEStructuralFeature(org.emftext.language.java.references.ReferencesPackage.IDENTIFIER_REFERENCE__ARRAY_SELECTORS), JavaCardinality.STAR, new org.eclipse.emf.ecore.EClass[] {org.emftext.language.java.arrays.ArraysPackage.eINSTANCE.getArraySelector(), }, 0);
	public final static JavaKeyword JAVA_41_0_0_3_0_0_0 = new JavaKeyword(".", JavaCardinality.ONE);
	public final static JavaContainment JAVA_41_0_0_3_0_0_1 = new JavaContainment(org.emftext.language.java.references.ReferencesPackage.eINSTANCE.getIdentifierReference().getEStructuralFeature(org.emftext.language.java.references.ReferencesPackage.IDENTIFIER_REFERENCE__NEXT), JavaCardinality.ONE, new org.eclipse.emf.ecore.EClass[] {org.emftext.language.java.references.ReferencesPackage.eINSTANCE.getReference(), }, 0);
	public final static JavaSequence JAVA_41_0_0_3_0_0 = new JavaSequence(JavaCardinality.ONE, JAVA_41_0_0_3_0_0_0, JAVA_41_0_0_3_0_0_1);
	public final static JavaChoice JAVA_41_0_0_3_0 = new JavaChoice(JavaCardinality.ONE, JAVA_41_0_0_3_0_0);
	public final static JavaCompound JAVA_41_0_0_3 = new JavaCompound(JAVA_41_0_0_3_0, JavaCardinality.QUESTIONMARK);
	public final static JavaSequence JAVA_41_0_0 = new JavaSequence(JavaCardinality.ONE, JAVA_41_0_0_0, JAVA_41_0_0_1, JAVA_41_0_0_2, JAVA_41_0_0_3);
	public final static JavaChoice JAVA_41_0 = new JavaChoice(JavaCardinality.ONE, JAVA_41_0_0);
	public final static JavaRule JAVA_41 = new JavaRule(org.emftext.language.java.references.ReferencesPackage.eINSTANCE.getIdentifierReference(), JAVA_41_0, JavaCardinality.ONE);
	public final static JavaKeyword JAVA_42_0_0_0 = new JavaKeyword("class", JavaCardinality.ONE);
	public final static JavaKeyword JAVA_42_0_0_1_0_0_0 = new JavaKeyword(".", JavaCardinality.ONE);
	public final static JavaContainment JAVA_42_0_0_1_0_0_1 = new JavaContainment(org.emftext.language.java.references.ReferencesPackage.eINSTANCE.getReflectiveClassReference().getEStructuralFeature(org.emftext.language.java.references.ReferencesPackage.REFLECTIVE_CLASS_REFERENCE__NEXT), JavaCardinality.ONE, new org.eclipse.emf.ecore.EClass[] {org.emftext.language.java.references.ReferencesPackage.eINSTANCE.getReference(), }, 0);
	public final static JavaSequence JAVA_42_0_0_1_0_0 = new JavaSequence(JavaCardinality.ONE, JAVA_42_0_0_1_0_0_0, JAVA_42_0_0_1_0_0_1);
	public final static JavaChoice JAVA_42_0_0_1_0 = new JavaChoice(JavaCardinality.ONE, JAVA_42_0_0_1_0_0);
	public final static JavaCompound JAVA_42_0_0_1 = new JavaCompound(JAVA_42_0_0_1_0, JavaCardinality.QUESTIONMARK);
	public final static JavaSequence JAVA_42_0_0 = new JavaSequence(JavaCardinality.ONE, JAVA_42_0_0_0, JAVA_42_0_0_1);
	public final static JavaChoice JAVA_42_0 = new JavaChoice(JavaCardinality.ONE, JAVA_42_0_0);
	public final static JavaRule JAVA_42 = new JavaRule(org.emftext.language.java.references.ReferencesPackage.eINSTANCE.getReflectiveClassReference(), JAVA_42_0, JavaCardinality.ONE);
	public final static JavaContainment JAVA_43_0_0_0 = new JavaContainment(org.emftext.language.java.references.ReferencesPackage.eINSTANCE.getSelfReference().getEStructuralFeature(org.emftext.language.java.references.ReferencesPackage.SELF_REFERENCE__SELF), JavaCardinality.ONE, new org.eclipse.emf.ecore.EClass[] {org.emftext.language.java.literals.LiteralsPackage.eINSTANCE.getSelf(), }, 0);
	public final static JavaKeyword JAVA_43_0_0_1_0_0_0 = new JavaKeyword(".", JavaCardinality.ONE);
	public final static JavaContainment JAVA_43_0_0_1_0_0_1 = new JavaContainment(org.emftext.language.java.references.ReferencesPackage.eINSTANCE.getSelfReference().getEStructuralFeature(org.emftext.language.java.references.ReferencesPackage.SELF_REFERENCE__NEXT), JavaCardinality.ONE, new org.eclipse.emf.ecore.EClass[] {org.emftext.language.java.references.ReferencesPackage.eINSTANCE.getReference(), }, 0);
	public final static JavaSequence JAVA_43_0_0_1_0_0 = new JavaSequence(JavaCardinality.ONE, JAVA_43_0_0_1_0_0_0, JAVA_43_0_0_1_0_0_1);
	public final static JavaChoice JAVA_43_0_0_1_0 = new JavaChoice(JavaCardinality.ONE, JAVA_43_0_0_1_0_0);
	public final static JavaCompound JAVA_43_0_0_1 = new JavaCompound(JAVA_43_0_0_1_0, JavaCardinality.QUESTIONMARK);
	public final static JavaSequence JAVA_43_0_0 = new JavaSequence(JavaCardinality.ONE, JAVA_43_0_0_0, JAVA_43_0_0_1);
	public final static JavaChoice JAVA_43_0 = new JavaChoice(JavaCardinality.ONE, JAVA_43_0_0);
	public final static JavaRule JAVA_43 = new JavaRule(org.emftext.language.java.references.ReferencesPackage.eINSTANCE.getSelfReference(), JAVA_43_0, JavaCardinality.ONE);
	public final static JavaContainment JAVA_44_0_0_0 = new JavaContainment(org.emftext.language.java.references.ReferencesPackage.eINSTANCE.getPrimitiveTypeReference().getEStructuralFeature(org.emftext.language.java.references.ReferencesPackage.PRIMITIVE_TYPE_REFERENCE__PRIMITIVE_TYPE), JavaCardinality.ONE, new org.eclipse.emf.ecore.EClass[] {org.emftext.language.java.types.TypesPackage.eINSTANCE.getPrimitiveType(), }, 0);
	public final static JavaContainment JAVA_44_0_0_1 = new JavaContainment(org.emftext.language.java.references.ReferencesPackage.eINSTANCE.getPrimitiveTypeReference().getEStructuralFeature(org.emftext.language.java.references.ReferencesPackage.PRIMITIVE_TYPE_REFERENCE__ARRAY_SELECTORS), JavaCardinality.STAR, new org.eclipse.emf.ecore.EClass[] {org.emftext.language.java.arrays.ArraysPackage.eINSTANCE.getArraySelector(), }, 0);
	public final static JavaKeyword JAVA_44_0_0_2_0_0_0 = new JavaKeyword(".", JavaCardinality.ONE);
	public final static JavaContainment JAVA_44_0_0_2_0_0_1 = new JavaContainment(org.emftext.language.java.references.ReferencesPackage.eINSTANCE.getPrimitiveTypeReference().getEStructuralFeature(org.emftext.language.java.references.ReferencesPackage.PRIMITIVE_TYPE_REFERENCE__NEXT), JavaCardinality.ONE, new org.eclipse.emf.ecore.EClass[] {org.emftext.language.java.references.ReferencesPackage.eINSTANCE.getReference(), }, 0);
	public final static JavaSequence JAVA_44_0_0_2_0_0 = new JavaSequence(JavaCardinality.ONE, JAVA_44_0_0_2_0_0_0, JAVA_44_0_0_2_0_0_1);
	public final static JavaChoice JAVA_44_0_0_2_0 = new JavaChoice(JavaCardinality.ONE, JAVA_44_0_0_2_0_0);
	public final static JavaCompound JAVA_44_0_0_2 = new JavaCompound(JAVA_44_0_0_2_0, JavaCardinality.QUESTIONMARK);
	public final static JavaSequence JAVA_44_0_0 = new JavaSequence(JavaCardinality.ONE, JAVA_44_0_0_0, JAVA_44_0_0_1, JAVA_44_0_0_2);
	public final static JavaChoice JAVA_44_0 = new JavaChoice(JavaCardinality.ONE, JAVA_44_0_0);
	public final static JavaRule JAVA_44 = new JavaRule(org.emftext.language.java.references.ReferencesPackage.eINSTANCE.getPrimitiveTypeReference(), JAVA_44_0, JavaCardinality.ONE);
	public final static JavaKeyword JAVA_45_0_0_0 = new JavaKeyword("this", JavaCardinality.ONE);
	public final static JavaSequence JAVA_45_0_0 = new JavaSequence(JavaCardinality.ONE, JAVA_45_0_0_0);
	public final static JavaChoice JAVA_45_0 = new JavaChoice(JavaCardinality.ONE, JAVA_45_0_0);
	public final static JavaRule JAVA_45 = new JavaRule(org.emftext.language.java.literals.LiteralsPackage.eINSTANCE.getThis(), JAVA_45_0, JavaCardinality.ONE);
	public final static JavaKeyword JAVA_46_0_0_0 = new JavaKeyword("super", JavaCardinality.ONE);
	public final static JavaSequence JAVA_46_0_0 = new JavaSequence(JavaCardinality.ONE, JAVA_46_0_0_0);
	public final static JavaChoice JAVA_46_0 = new JavaChoice(JavaCardinality.ONE, JAVA_46_0_0);
	public final static JavaRule JAVA_46 = new JavaRule(org.emftext.language.java.literals.LiteralsPackage.eINSTANCE.getSuper(), JAVA_46_0, JavaCardinality.ONE);
	public final static JavaPlaceholder JAVA_47_0_0_0 = new JavaPlaceholder(org.emftext.language.java.references.ReferencesPackage.eINSTANCE.getStringReference().getEStructuralFeature(org.emftext.language.java.references.ReferencesPackage.STRING_REFERENCE__VALUE), "STRING_LITERAL", JavaCardinality.ONE, 0);
	public final static JavaKeyword JAVA_47_0_0_1_0_0_0 = new JavaKeyword(".", JavaCardinality.ONE);
	public final static JavaContainment JAVA_47_0_0_1_0_0_1 = new JavaContainment(org.emftext.language.java.references.ReferencesPackage.eINSTANCE.getStringReference().getEStructuralFeature(org.emftext.language.java.references.ReferencesPackage.STRING_REFERENCE__NEXT), JavaCardinality.ONE, new org.eclipse.emf.ecore.EClass[] {org.emftext.language.java.references.ReferencesPackage.eINSTANCE.getReference(), }, 0);
	public final static JavaSequence JAVA_47_0_0_1_0_0 = new JavaSequence(JavaCardinality.ONE, JAVA_47_0_0_1_0_0_0, JAVA_47_0_0_1_0_0_1);
	public final static JavaChoice JAVA_47_0_0_1_0 = new JavaChoice(JavaCardinality.ONE, JAVA_47_0_0_1_0_0);
	public final static JavaCompound JAVA_47_0_0_1 = new JavaCompound(JAVA_47_0_0_1_0, JavaCardinality.QUESTIONMARK);
	public final static JavaSequence JAVA_47_0_0 = new JavaSequence(JavaCardinality.ONE, JAVA_47_0_0_0, JAVA_47_0_0_1);
	public final static JavaChoice JAVA_47_0 = new JavaChoice(JavaCardinality.ONE, JAVA_47_0_0);
	public final static JavaRule JAVA_47 = new JavaRule(org.emftext.language.java.references.ReferencesPackage.eINSTANCE.getStringReference(), JAVA_47_0, JavaCardinality.ONE);
	public final static JavaContainment JAVA_48_0_0_0 = new JavaContainment(org.emftext.language.java.generics.GenericsPackage.eINSTANCE.getQualifiedTypeArgument().getEStructuralFeature(org.emftext.language.java.generics.GenericsPackage.QUALIFIED_TYPE_ARGUMENT__TYPE_REFERENCE), JavaCardinality.ONE, new org.eclipse.emf.ecore.EClass[] {org.emftext.language.java.types.TypesPackage.eINSTANCE.getTypeReference(), }, 0);
	public final static JavaContainment JAVA_48_0_0_1 = new JavaContainment(org.emftext.language.java.generics.GenericsPackage.eINSTANCE.getQualifiedTypeArgument().getEStructuralFeature(org.emftext.language.java.generics.GenericsPackage.QUALIFIED_TYPE_ARGUMENT__ARRAY_DIMENSIONS_BEFORE), JavaCardinality.STAR, new org.eclipse.emf.ecore.EClass[] {org.emftext.language.java.arrays.ArraysPackage.eINSTANCE.getArrayDimension(), }, 0);
	public final static JavaSequence JAVA_48_0_0 = new JavaSequence(JavaCardinality.ONE, JAVA_48_0_0_0, JAVA_48_0_0_1);
	public final static JavaChoice JAVA_48_0 = new JavaChoice(JavaCardinality.ONE, JAVA_48_0_0);
	public final static JavaRule JAVA_48 = new JavaRule(org.emftext.language.java.generics.GenericsPackage.eINSTANCE.getQualifiedTypeArgument(), JAVA_48_0, JavaCardinality.ONE);
	public final static JavaKeyword JAVA_49_0_0_0 = new JavaKeyword("?", JavaCardinality.ONE);
	public final static JavaSequence JAVA_49_0_0 = new JavaSequence(JavaCardinality.ONE, JAVA_49_0_0_0);
	public final static JavaChoice JAVA_49_0 = new JavaChoice(JavaCardinality.ONE, JAVA_49_0_0);
	public final static JavaRule JAVA_49 = new JavaRule(org.emftext.language.java.generics.GenericsPackage.eINSTANCE.getUnknownTypeArgument(), JAVA_49_0, JavaCardinality.ONE);
	public final static JavaKeyword JAVA_50_0_0_0 = new JavaKeyword("?", JavaCardinality.ONE);
	public final static JavaKeyword JAVA_50_0_0_1 = new JavaKeyword("extends", JavaCardinality.ONE);
	public final static JavaContainment JAVA_50_0_0_2 = new JavaContainment(org.emftext.language.java.generics.GenericsPackage.eINSTANCE.getExtendsTypeArgument().getEStructuralFeature(org.emftext.language.java.generics.GenericsPackage.EXTENDS_TYPE_ARGUMENT__EXTEND_TYPES), JavaCardinality.ONE, new org.eclipse.emf.ecore.EClass[] {org.emftext.language.java.types.TypesPackage.eINSTANCE.getTypeReference(), }, 0);
	public final static JavaKeyword JAVA_50_0_0_3_0_0_0 = new JavaKeyword("&", JavaCardinality.ONE);
	public final static JavaContainment JAVA_50_0_0_3_0_0_1 = new JavaContainment(org.emftext.language.java.generics.GenericsPackage.eINSTANCE.getExtendsTypeArgument().getEStructuralFeature(org.emftext.language.java.generics.GenericsPackage.EXTENDS_TYPE_ARGUMENT__EXTEND_TYPES), JavaCardinality.ONE, new org.eclipse.emf.ecore.EClass[] {org.emftext.language.java.types.TypesPackage.eINSTANCE.getTypeReference(), }, 0);
	public final static JavaSequence JAVA_50_0_0_3_0_0 = new JavaSequence(JavaCardinality.ONE, JAVA_50_0_0_3_0_0_0, JAVA_50_0_0_3_0_0_1);
	public final static JavaChoice JAVA_50_0_0_3_0 = new JavaChoice(JavaCardinality.ONE, JAVA_50_0_0_3_0_0);
	public final static JavaCompound JAVA_50_0_0_3 = new JavaCompound(JAVA_50_0_0_3_0, JavaCardinality.STAR);
	public final static JavaContainment JAVA_50_0_0_4 = new JavaContainment(org.emftext.language.java.generics.GenericsPackage.eINSTANCE.getExtendsTypeArgument().getEStructuralFeature(org.emftext.language.java.generics.GenericsPackage.EXTENDS_TYPE_ARGUMENT__ARRAY_DIMENSIONS_BEFORE), JavaCardinality.STAR, new org.eclipse.emf.ecore.EClass[] {org.emftext.language.java.arrays.ArraysPackage.eINSTANCE.getArrayDimension(), }, 0);
	public final static JavaSequence JAVA_50_0_0 = new JavaSequence(JavaCardinality.ONE, JAVA_50_0_0_0, JAVA_50_0_0_1, JAVA_50_0_0_2, JAVA_50_0_0_3, JAVA_50_0_0_4);
	public final static JavaChoice JAVA_50_0 = new JavaChoice(JavaCardinality.ONE, JAVA_50_0_0);
	public final static JavaRule JAVA_50 = new JavaRule(org.emftext.language.java.generics.GenericsPackage.eINSTANCE.getExtendsTypeArgument(), JAVA_50_0, JavaCardinality.ONE);
	public final static JavaKeyword JAVA_51_0_0_0 = new JavaKeyword("?", JavaCardinality.ONE);
	public final static JavaKeyword JAVA_51_0_0_1 = new JavaKeyword("super", JavaCardinality.ONE);
	public final static JavaContainment JAVA_51_0_0_2 = new JavaContainment(org.emftext.language.java.generics.GenericsPackage.eINSTANCE.getSuperTypeArgument().getEStructuralFeature(org.emftext.language.java.generics.GenericsPackage.SUPER_TYPE_ARGUMENT__SUPER_TYPE), JavaCardinality.ONE, new org.eclipse.emf.ecore.EClass[] {org.emftext.language.java.types.TypesPackage.eINSTANCE.getTypeReference(), }, 0);
	public final static JavaContainment JAVA_51_0_0_3 = new JavaContainment(org.emftext.language.java.generics.GenericsPackage.eINSTANCE.getSuperTypeArgument().getEStructuralFeature(org.emftext.language.java.generics.GenericsPackage.SUPER_TYPE_ARGUMENT__ARRAY_DIMENSIONS_BEFORE), JavaCardinality.STAR, new org.eclipse.emf.ecore.EClass[] {org.emftext.language.java.arrays.ArraysPackage.eINSTANCE.getArrayDimension(), }, 0);
	public final static JavaSequence JAVA_51_0_0 = new JavaSequence(JavaCardinality.ONE, JAVA_51_0_0_0, JAVA_51_0_0_1, JAVA_51_0_0_2, JAVA_51_0_0_3);
	public final static JavaChoice JAVA_51_0 = new JavaChoice(JavaCardinality.ONE, JAVA_51_0_0);
	public final static JavaRule JAVA_51 = new JavaRule(org.emftext.language.java.generics.GenericsPackage.eINSTANCE.getSuperTypeArgument(), JAVA_51_0, JavaCardinality.ONE);
	public final static JavaKeyword JAVA_52_0_0_0 = new JavaKeyword("assert", JavaCardinality.ONE);
	public final static JavaContainment JAVA_52_0_0_1 = new JavaContainment(org.emftext.language.java.statements.StatementsPackage.eINSTANCE.getAssert().getEStructuralFeature(org.emftext.language.java.statements.StatementsPackage.ASSERT__CONDITION), JavaCardinality.ONE, new org.eclipse.emf.ecore.EClass[] {org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getAssignmentExpression(), }, 0);
	public final static JavaKeyword JAVA_52_0_0_2_0_0_0 = new JavaKeyword(":", JavaCardinality.ONE);
	public final static JavaContainment JAVA_52_0_0_2_0_0_1 = new JavaContainment(org.emftext.language.java.statements.StatementsPackage.eINSTANCE.getAssert().getEStructuralFeature(org.emftext.language.java.statements.StatementsPackage.ASSERT__ERROR_MESSAGE), JavaCardinality.ONE, new org.eclipse.emf.ecore.EClass[] {org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getAssignmentExpression(), }, 0);
	public final static JavaSequence JAVA_52_0_0_2_0_0 = new JavaSequence(JavaCardinality.ONE, JAVA_52_0_0_2_0_0_0, JAVA_52_0_0_2_0_0_1);
	public final static JavaChoice JAVA_52_0_0_2_0 = new JavaChoice(JavaCardinality.ONE, JAVA_52_0_0_2_0_0);
	public final static JavaCompound JAVA_52_0_0_2 = new JavaCompound(JAVA_52_0_0_2_0, JavaCardinality.QUESTIONMARK);
	public final static JavaKeyword JAVA_52_0_0_3 = new JavaKeyword(";", JavaCardinality.ONE);
	public final static JavaSequence JAVA_52_0_0 = new JavaSequence(JavaCardinality.ONE, JAVA_52_0_0_0, JAVA_52_0_0_1, JAVA_52_0_0_2, JAVA_52_0_0_3);
	public final static JavaChoice JAVA_52_0 = new JavaChoice(JavaCardinality.ONE, JAVA_52_0_0);
	public final static JavaRule JAVA_52 = new JavaRule(org.emftext.language.java.statements.StatementsPackage.eINSTANCE.getAssert(), JAVA_52_0, JavaCardinality.ONE);
	public final static JavaKeyword JAVA_53_0_0_0 = new JavaKeyword("if", JavaCardinality.ONE);
	public final static JavaWhiteSpace JAVA_53_0_0_1 = new JavaWhiteSpace(1, JavaCardinality.ONE);
	public final static JavaKeyword JAVA_53_0_0_2 = new JavaKeyword("(", JavaCardinality.ONE);
	public final static JavaContainment JAVA_53_0_0_3 = new JavaContainment(org.emftext.language.java.statements.StatementsPackage.eINSTANCE.getCondition().getEStructuralFeature(org.emftext.language.java.statements.StatementsPackage.CONDITION__CONDITION), JavaCardinality.ONE, new org.eclipse.emf.ecore.EClass[] {org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getAssignmentExpression(), }, 0);
	public final static JavaKeyword JAVA_53_0_0_4 = new JavaKeyword(")", JavaCardinality.ONE);
	public final static JavaContainment JAVA_53_0_0_5 = new JavaContainment(org.emftext.language.java.statements.StatementsPackage.eINSTANCE.getCondition().getEStructuralFeature(org.emftext.language.java.statements.StatementsPackage.CONDITION__STATEMENT), JavaCardinality.ONE, new org.eclipse.emf.ecore.EClass[] {org.emftext.language.java.statements.StatementsPackage.eINSTANCE.getStatement(), }, 0);
	public final static JavaKeyword JAVA_53_0_0_6_0_0_0 = new JavaKeyword("else", JavaCardinality.ONE);
	public final static JavaContainment JAVA_53_0_0_6_0_0_1 = new JavaContainment(org.emftext.language.java.statements.StatementsPackage.eINSTANCE.getCondition().getEStructuralFeature(org.emftext.language.java.statements.StatementsPackage.CONDITION__ELSE_STATEMENT), JavaCardinality.ONE, new org.eclipse.emf.ecore.EClass[] {org.emftext.language.java.statements.StatementsPackage.eINSTANCE.getStatement(), }, 0);
	public final static JavaSequence JAVA_53_0_0_6_0_0 = new JavaSequence(JavaCardinality.ONE, JAVA_53_0_0_6_0_0_0, JAVA_53_0_0_6_0_0_1);
	public final static JavaChoice JAVA_53_0_0_6_0 = new JavaChoice(JavaCardinality.ONE, JAVA_53_0_0_6_0_0);
	public final static JavaCompound JAVA_53_0_0_6 = new JavaCompound(JAVA_53_0_0_6_0, JavaCardinality.QUESTIONMARK);
	public final static JavaSequence JAVA_53_0_0 = new JavaSequence(JavaCardinality.ONE, JAVA_53_0_0_0, JAVA_53_0_0_1, JAVA_53_0_0_2, JAVA_53_0_0_3, JAVA_53_0_0_4, JAVA_53_0_0_5, JAVA_53_0_0_6);
	public final static JavaChoice JAVA_53_0 = new JavaChoice(JavaCardinality.ONE, JAVA_53_0_0);
	public final static JavaRule JAVA_53 = new JavaRule(org.emftext.language.java.statements.StatementsPackage.eINSTANCE.getCondition(), JAVA_53_0, JavaCardinality.ONE);
	public final static JavaKeyword JAVA_54_0_0_0 = new JavaKeyword("for", JavaCardinality.ONE);
	public final static JavaWhiteSpace JAVA_54_0_0_1 = new JavaWhiteSpace(1, JavaCardinality.ONE);
	public final static JavaKeyword JAVA_54_0_0_2 = new JavaKeyword("(", JavaCardinality.ONE);
	public final static JavaContainment JAVA_54_0_0_3 = new JavaContainment(org.emftext.language.java.statements.StatementsPackage.eINSTANCE.getForLoop().getEStructuralFeature(org.emftext.language.java.statements.StatementsPackage.FOR_LOOP__INIT), JavaCardinality.QUESTIONMARK, new org.eclipse.emf.ecore.EClass[] {org.emftext.language.java.statements.StatementsPackage.eINSTANCE.getForLoopInitializer(), }, 0);
	public final static JavaKeyword JAVA_54_0_0_4 = new JavaKeyword(";", JavaCardinality.ONE);
	public final static JavaContainment JAVA_54_0_0_5 = new JavaContainment(org.emftext.language.java.statements.StatementsPackage.eINSTANCE.getForLoop().getEStructuralFeature(org.emftext.language.java.statements.StatementsPackage.FOR_LOOP__CONDITION), JavaCardinality.QUESTIONMARK, new org.eclipse.emf.ecore.EClass[] {org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getAssignmentExpression(), }, 0);
	public final static JavaKeyword JAVA_54_0_0_6 = new JavaKeyword(";", JavaCardinality.ONE);
	public final static JavaContainment JAVA_54_0_0_7_0_0_0 = new JavaContainment(org.emftext.language.java.statements.StatementsPackage.eINSTANCE.getForLoop().getEStructuralFeature(org.emftext.language.java.statements.StatementsPackage.FOR_LOOP__UPDATES), JavaCardinality.ONE, new org.eclipse.emf.ecore.EClass[] {org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getAssignmentExpression(), }, 0);
	public final static JavaKeyword JAVA_54_0_0_7_0_0_1_0_0_0 = new JavaKeyword(",", JavaCardinality.ONE);
	public final static JavaContainment JAVA_54_0_0_7_0_0_1_0_0_1 = new JavaContainment(org.emftext.language.java.statements.StatementsPackage.eINSTANCE.getForLoop().getEStructuralFeature(org.emftext.language.java.statements.StatementsPackage.FOR_LOOP__UPDATES), JavaCardinality.ONE, new org.eclipse.emf.ecore.EClass[] {org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getAssignmentExpression(), }, 0);
	public final static JavaSequence JAVA_54_0_0_7_0_0_1_0_0 = new JavaSequence(JavaCardinality.ONE, JAVA_54_0_0_7_0_0_1_0_0_0, JAVA_54_0_0_7_0_0_1_0_0_1);
	public final static JavaChoice JAVA_54_0_0_7_0_0_1_0 = new JavaChoice(JavaCardinality.ONE, JAVA_54_0_0_7_0_0_1_0_0);
	public final static JavaCompound JAVA_54_0_0_7_0_0_1 = new JavaCompound(JAVA_54_0_0_7_0_0_1_0, JavaCardinality.STAR);
	public final static JavaSequence JAVA_54_0_0_7_0_0 = new JavaSequence(JavaCardinality.ONE, JAVA_54_0_0_7_0_0_0, JAVA_54_0_0_7_0_0_1);
	public final static JavaChoice JAVA_54_0_0_7_0 = new JavaChoice(JavaCardinality.ONE, JAVA_54_0_0_7_0_0);
	public final static JavaCompound JAVA_54_0_0_7 = new JavaCompound(JAVA_54_0_0_7_0, JavaCardinality.QUESTIONMARK);
	public final static JavaKeyword JAVA_54_0_0_8 = new JavaKeyword(")", JavaCardinality.ONE);
	public final static JavaContainment JAVA_54_0_0_9 = new JavaContainment(org.emftext.language.java.statements.StatementsPackage.eINSTANCE.getForLoop().getEStructuralFeature(org.emftext.language.java.statements.StatementsPackage.FOR_LOOP__STATEMENT), JavaCardinality.ONE, new org.eclipse.emf.ecore.EClass[] {org.emftext.language.java.statements.StatementsPackage.eINSTANCE.getStatement(), }, 0);
	public final static JavaSequence JAVA_54_0_0 = new JavaSequence(JavaCardinality.ONE, JAVA_54_0_0_0, JAVA_54_0_0_1, JAVA_54_0_0_2, JAVA_54_0_0_3, JAVA_54_0_0_4, JAVA_54_0_0_5, JAVA_54_0_0_6, JAVA_54_0_0_7, JAVA_54_0_0_8, JAVA_54_0_0_9);
	public final static JavaChoice JAVA_54_0 = new JavaChoice(JavaCardinality.ONE, JAVA_54_0_0);
	public final static JavaRule JAVA_54 = new JavaRule(org.emftext.language.java.statements.StatementsPackage.eINSTANCE.getForLoop(), JAVA_54_0, JavaCardinality.ONE);
	public final static JavaKeyword JAVA_55_0_0_0 = new JavaKeyword("for", JavaCardinality.ONE);
	public final static JavaWhiteSpace JAVA_55_0_0_1 = new JavaWhiteSpace(1, JavaCardinality.ONE);
	public final static JavaKeyword JAVA_55_0_0_2 = new JavaKeyword("(", JavaCardinality.ONE);
	public final static JavaContainment JAVA_55_0_0_3 = new JavaContainment(org.emftext.language.java.statements.StatementsPackage.eINSTANCE.getForEachLoop().getEStructuralFeature(org.emftext.language.java.statements.StatementsPackage.FOR_EACH_LOOP__NEXT), JavaCardinality.ONE, new org.eclipse.emf.ecore.EClass[] {org.emftext.language.java.parameters.ParametersPackage.eINSTANCE.getOrdinaryParameter(), }, 0);
	public final static JavaKeyword JAVA_55_0_0_4 = new JavaKeyword(":", JavaCardinality.ONE);
	public final static JavaContainment JAVA_55_0_0_5 = new JavaContainment(org.emftext.language.java.statements.StatementsPackage.eINSTANCE.getForEachLoop().getEStructuralFeature(org.emftext.language.java.statements.StatementsPackage.FOR_EACH_LOOP__COLLECTION), JavaCardinality.ONE, new org.eclipse.emf.ecore.EClass[] {org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getAssignmentExpression(), }, 0);
	public final static JavaKeyword JAVA_55_0_0_6 = new JavaKeyword(")", JavaCardinality.ONE);
	public final static JavaContainment JAVA_55_0_0_7 = new JavaContainment(org.emftext.language.java.statements.StatementsPackage.eINSTANCE.getForEachLoop().getEStructuralFeature(org.emftext.language.java.statements.StatementsPackage.FOR_EACH_LOOP__STATEMENT), JavaCardinality.ONE, new org.eclipse.emf.ecore.EClass[] {org.emftext.language.java.statements.StatementsPackage.eINSTANCE.getStatement(), }, 0);
	public final static JavaSequence JAVA_55_0_0 = new JavaSequence(JavaCardinality.ONE, JAVA_55_0_0_0, JAVA_55_0_0_1, JAVA_55_0_0_2, JAVA_55_0_0_3, JAVA_55_0_0_4, JAVA_55_0_0_5, JAVA_55_0_0_6, JAVA_55_0_0_7);
	public final static JavaChoice JAVA_55_0 = new JavaChoice(JavaCardinality.ONE, JAVA_55_0_0);
	public final static JavaRule JAVA_55 = new JavaRule(org.emftext.language.java.statements.StatementsPackage.eINSTANCE.getForEachLoop(), JAVA_55_0, JavaCardinality.ONE);
	public final static JavaKeyword JAVA_56_0_0_0 = new JavaKeyword("while", JavaCardinality.ONE);
	public final static JavaWhiteSpace JAVA_56_0_0_1 = new JavaWhiteSpace(1, JavaCardinality.ONE);
	public final static JavaKeyword JAVA_56_0_0_2 = new JavaKeyword("(", JavaCardinality.ONE);
	public final static JavaContainment JAVA_56_0_0_3 = new JavaContainment(org.emftext.language.java.statements.StatementsPackage.eINSTANCE.getWhileLoop().getEStructuralFeature(org.emftext.language.java.statements.StatementsPackage.WHILE_LOOP__CONDITION), JavaCardinality.ONE, new org.eclipse.emf.ecore.EClass[] {org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getAssignmentExpression(), }, 0);
	public final static JavaKeyword JAVA_56_0_0_4 = new JavaKeyword(")", JavaCardinality.ONE);
	public final static JavaContainment JAVA_56_0_0_5 = new JavaContainment(org.emftext.language.java.statements.StatementsPackage.eINSTANCE.getWhileLoop().getEStructuralFeature(org.emftext.language.java.statements.StatementsPackage.WHILE_LOOP__STATEMENT), JavaCardinality.ONE, new org.eclipse.emf.ecore.EClass[] {org.emftext.language.java.statements.StatementsPackage.eINSTANCE.getStatement(), }, 0);
	public final static JavaSequence JAVA_56_0_0 = new JavaSequence(JavaCardinality.ONE, JAVA_56_0_0_0, JAVA_56_0_0_1, JAVA_56_0_0_2, JAVA_56_0_0_3, JAVA_56_0_0_4, JAVA_56_0_0_5);
	public final static JavaChoice JAVA_56_0 = new JavaChoice(JavaCardinality.ONE, JAVA_56_0_0);
	public final static JavaRule JAVA_56 = new JavaRule(org.emftext.language.java.statements.StatementsPackage.eINSTANCE.getWhileLoop(), JAVA_56_0, JavaCardinality.ONE);
	public final static JavaKeyword JAVA_57_0_0_0 = new JavaKeyword("do", JavaCardinality.ONE);
	public final static JavaContainment JAVA_57_0_0_1 = new JavaContainment(org.emftext.language.java.statements.StatementsPackage.eINSTANCE.getDoWhileLoop().getEStructuralFeature(org.emftext.language.java.statements.StatementsPackage.DO_WHILE_LOOP__STATEMENT), JavaCardinality.ONE, new org.eclipse.emf.ecore.EClass[] {org.emftext.language.java.statements.StatementsPackage.eINSTANCE.getStatement(), }, 0);
	public final static JavaKeyword JAVA_57_0_0_2 = new JavaKeyword("while", JavaCardinality.ONE);
	public final static JavaWhiteSpace JAVA_57_0_0_3 = new JavaWhiteSpace(1, JavaCardinality.ONE);
	public final static JavaKeyword JAVA_57_0_0_4 = new JavaKeyword("(", JavaCardinality.ONE);
	public final static JavaContainment JAVA_57_0_0_5 = new JavaContainment(org.emftext.language.java.statements.StatementsPackage.eINSTANCE.getDoWhileLoop().getEStructuralFeature(org.emftext.language.java.statements.StatementsPackage.DO_WHILE_LOOP__CONDITION), JavaCardinality.ONE, new org.eclipse.emf.ecore.EClass[] {org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getAssignmentExpression(), }, 0);
	public final static JavaKeyword JAVA_57_0_0_6 = new JavaKeyword(")", JavaCardinality.ONE);
	public final static JavaKeyword JAVA_57_0_0_7 = new JavaKeyword(";", JavaCardinality.ONE);
	public final static JavaSequence JAVA_57_0_0 = new JavaSequence(JavaCardinality.ONE, JAVA_57_0_0_0, JAVA_57_0_0_1, JAVA_57_0_0_2, JAVA_57_0_0_3, JAVA_57_0_0_4, JAVA_57_0_0_5, JAVA_57_0_0_6, JAVA_57_0_0_7);
	public final static JavaChoice JAVA_57_0 = new JavaChoice(JavaCardinality.ONE, JAVA_57_0_0);
	public final static JavaRule JAVA_57 = new JavaRule(org.emftext.language.java.statements.StatementsPackage.eINSTANCE.getDoWhileLoop(), JAVA_57_0, JavaCardinality.ONE);
	public final static JavaKeyword JAVA_58_0_0_0 = new JavaKeyword(";", JavaCardinality.ONE);
	public final static JavaSequence JAVA_58_0_0 = new JavaSequence(JavaCardinality.ONE, JAVA_58_0_0_0);
	public final static JavaChoice JAVA_58_0 = new JavaChoice(JavaCardinality.ONE, JAVA_58_0_0);
	public final static JavaRule JAVA_58 = new JavaRule(org.emftext.language.java.statements.StatementsPackage.eINSTANCE.getEmptyStatement(), JAVA_58_0, JavaCardinality.ONE);
	public final static JavaKeyword JAVA_59_0_0_0 = new JavaKeyword("synchronized", JavaCardinality.ONE);
	public final static JavaWhiteSpace JAVA_59_0_0_1 = new JavaWhiteSpace(1, JavaCardinality.ONE);
	public final static JavaKeyword JAVA_59_0_0_2 = new JavaKeyword("(", JavaCardinality.ONE);
	public final static JavaContainment JAVA_59_0_0_3 = new JavaContainment(org.emftext.language.java.statements.StatementsPackage.eINSTANCE.getSynchronizedBlock().getEStructuralFeature(org.emftext.language.java.statements.StatementsPackage.SYNCHRONIZED_BLOCK__LOCK_PROVIDER), JavaCardinality.ONE, new org.eclipse.emf.ecore.EClass[] {org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getAssignmentExpression(), }, 0);
	public final static JavaKeyword JAVA_59_0_0_4 = new JavaKeyword(")", JavaCardinality.ONE);
	public final static JavaWhiteSpace JAVA_59_0_0_5 = new JavaWhiteSpace(1, JavaCardinality.ONE);
	public final static JavaKeyword JAVA_59_0_0_6 = new JavaKeyword("{", JavaCardinality.ONE);
	public final static JavaLineBreak JAVA_59_0_0_7_0_0_0 = new JavaLineBreak(JavaCardinality.ONE, 1);
	public final static JavaContainment JAVA_59_0_0_7_0_0_1 = new JavaContainment(org.emftext.language.java.statements.StatementsPackage.eINSTANCE.getSynchronizedBlock().getEStructuralFeature(org.emftext.language.java.statements.StatementsPackage.SYNCHRONIZED_BLOCK__STATEMENTS), JavaCardinality.ONE, new org.eclipse.emf.ecore.EClass[] {org.emftext.language.java.statements.StatementsPackage.eINSTANCE.getStatement(), }, 0);
	public final static JavaSequence JAVA_59_0_0_7_0_0 = new JavaSequence(JavaCardinality.ONE, JAVA_59_0_0_7_0_0_0, JAVA_59_0_0_7_0_0_1);
	public final static JavaChoice JAVA_59_0_0_7_0 = new JavaChoice(JavaCardinality.ONE, JAVA_59_0_0_7_0_0);
	public final static JavaCompound JAVA_59_0_0_7 = new JavaCompound(JAVA_59_0_0_7_0, JavaCardinality.STAR);
	public final static JavaLineBreak JAVA_59_0_0_8 = new JavaLineBreak(JavaCardinality.ONE, 0);
	public final static JavaKeyword JAVA_59_0_0_9 = new JavaKeyword("}", JavaCardinality.ONE);
	public final static JavaSequence JAVA_59_0_0 = new JavaSequence(JavaCardinality.ONE, JAVA_59_0_0_0, JAVA_59_0_0_1, JAVA_59_0_0_2, JAVA_59_0_0_3, JAVA_59_0_0_4, JAVA_59_0_0_5, JAVA_59_0_0_6, JAVA_59_0_0_7, JAVA_59_0_0_8, JAVA_59_0_0_9);
	public final static JavaChoice JAVA_59_0 = new JavaChoice(JavaCardinality.ONE, JAVA_59_0_0);
	public final static JavaRule JAVA_59 = new JavaRule(org.emftext.language.java.statements.StatementsPackage.eINSTANCE.getSynchronizedBlock(), JAVA_59_0, JavaCardinality.ONE);
	public final static JavaKeyword JAVA_60_0_0_0 = new JavaKeyword("try", JavaCardinality.ONE);
	public final static JavaWhiteSpace JAVA_60_0_0_1 = new JavaWhiteSpace(1, JavaCardinality.ONE);
	public final static JavaKeyword JAVA_60_0_0_2 = new JavaKeyword("{", JavaCardinality.ONE);
	public final static JavaLineBreak JAVA_60_0_0_3_0_0_0 = new JavaLineBreak(JavaCardinality.ONE, 1);
	public final static JavaContainment JAVA_60_0_0_3_0_0_1 = new JavaContainment(org.emftext.language.java.statements.StatementsPackage.eINSTANCE.getTryBlock().getEStructuralFeature(org.emftext.language.java.statements.StatementsPackage.TRY_BLOCK__STATEMENTS), JavaCardinality.ONE, new org.eclipse.emf.ecore.EClass[] {org.emftext.language.java.statements.StatementsPackage.eINSTANCE.getStatement(), }, 0);
	public final static JavaSequence JAVA_60_0_0_3_0_0 = new JavaSequence(JavaCardinality.ONE, JAVA_60_0_0_3_0_0_0, JAVA_60_0_0_3_0_0_1);
	public final static JavaChoice JAVA_60_0_0_3_0 = new JavaChoice(JavaCardinality.ONE, JAVA_60_0_0_3_0_0);
	public final static JavaCompound JAVA_60_0_0_3 = new JavaCompound(JAVA_60_0_0_3_0, JavaCardinality.STAR);
	public final static JavaLineBreak JAVA_60_0_0_4 = new JavaLineBreak(JavaCardinality.ONE, 0);
	public final static JavaKeyword JAVA_60_0_0_5 = new JavaKeyword("}", JavaCardinality.ONE);
	public final static JavaContainment JAVA_60_0_0_6 = new JavaContainment(org.emftext.language.java.statements.StatementsPackage.eINSTANCE.getTryBlock().getEStructuralFeature(org.emftext.language.java.statements.StatementsPackage.TRY_BLOCK__CATCHE_BLOCKS), JavaCardinality.STAR, new org.eclipse.emf.ecore.EClass[] {org.emftext.language.java.statements.StatementsPackage.eINSTANCE.getCatchBlock(), }, 0);
	public final static JavaKeyword JAVA_60_0_0_7_0_0_0 = new JavaKeyword("finally", JavaCardinality.ONE);
	public final static JavaContainment JAVA_60_0_0_7_0_0_1 = new JavaContainment(org.emftext.language.java.statements.StatementsPackage.eINSTANCE.getTryBlock().getEStructuralFeature(org.emftext.language.java.statements.StatementsPackage.TRY_BLOCK__FINALLY_BLOCK), JavaCardinality.ONE, new org.eclipse.emf.ecore.EClass[] {org.emftext.language.java.statements.StatementsPackage.eINSTANCE.getBlock(), }, 0);
	public final static JavaSequence JAVA_60_0_0_7_0_0 = new JavaSequence(JavaCardinality.ONE, JAVA_60_0_0_7_0_0_0, JAVA_60_0_0_7_0_0_1);
	public final static JavaChoice JAVA_60_0_0_7_0 = new JavaChoice(JavaCardinality.ONE, JAVA_60_0_0_7_0_0);
	public final static JavaCompound JAVA_60_0_0_7 = new JavaCompound(JAVA_60_0_0_7_0, JavaCardinality.QUESTIONMARK);
	public final static JavaSequence JAVA_60_0_0 = new JavaSequence(JavaCardinality.ONE, JAVA_60_0_0_0, JAVA_60_0_0_1, JAVA_60_0_0_2, JAVA_60_0_0_3, JAVA_60_0_0_4, JAVA_60_0_0_5, JAVA_60_0_0_6, JAVA_60_0_0_7);
	public final static JavaChoice JAVA_60_0 = new JavaChoice(JavaCardinality.ONE, JAVA_60_0_0);
	public final static JavaRule JAVA_60 = new JavaRule(org.emftext.language.java.statements.StatementsPackage.eINSTANCE.getTryBlock(), JAVA_60_0, JavaCardinality.ONE);
	public final static JavaKeyword JAVA_61_0_0_0 = new JavaKeyword("catch", JavaCardinality.ONE);
	public final static JavaWhiteSpace JAVA_61_0_0_1 = new JavaWhiteSpace(1, JavaCardinality.ONE);
	public final static JavaKeyword JAVA_61_0_0_2 = new JavaKeyword("(", JavaCardinality.ONE);
	public final static JavaContainment JAVA_61_0_0_3 = new JavaContainment(org.emftext.language.java.statements.StatementsPackage.eINSTANCE.getCatchBlock().getEStructuralFeature(org.emftext.language.java.statements.StatementsPackage.CATCH_BLOCK__PARAMETER), JavaCardinality.ONE, new org.eclipse.emf.ecore.EClass[] {org.emftext.language.java.parameters.ParametersPackage.eINSTANCE.getOrdinaryParameter(), }, 0);
	public final static JavaKeyword JAVA_61_0_0_4 = new JavaKeyword(")", JavaCardinality.ONE);
	public final static JavaWhiteSpace JAVA_61_0_0_5 = new JavaWhiteSpace(1, JavaCardinality.ONE);
	public final static JavaKeyword JAVA_61_0_0_6 = new JavaKeyword("{", JavaCardinality.ONE);
	public final static JavaLineBreak JAVA_61_0_0_7_0_0_0 = new JavaLineBreak(JavaCardinality.ONE, 1);
	public final static JavaContainment JAVA_61_0_0_7_0_0_1 = new JavaContainment(org.emftext.language.java.statements.StatementsPackage.eINSTANCE.getCatchBlock().getEStructuralFeature(org.emftext.language.java.statements.StatementsPackage.CATCH_BLOCK__STATEMENTS), JavaCardinality.ONE, new org.eclipse.emf.ecore.EClass[] {org.emftext.language.java.statements.StatementsPackage.eINSTANCE.getStatement(), }, 0);
	public final static JavaSequence JAVA_61_0_0_7_0_0 = new JavaSequence(JavaCardinality.ONE, JAVA_61_0_0_7_0_0_0, JAVA_61_0_0_7_0_0_1);
	public final static JavaChoice JAVA_61_0_0_7_0 = new JavaChoice(JavaCardinality.ONE, JAVA_61_0_0_7_0_0);
	public final static JavaCompound JAVA_61_0_0_7 = new JavaCompound(JAVA_61_0_0_7_0, JavaCardinality.STAR);
	public final static JavaLineBreak JAVA_61_0_0_8 = new JavaLineBreak(JavaCardinality.ONE, 0);
	public final static JavaKeyword JAVA_61_0_0_9 = new JavaKeyword("}", JavaCardinality.ONE);
	public final static JavaSequence JAVA_61_0_0 = new JavaSequence(JavaCardinality.ONE, JAVA_61_0_0_0, JAVA_61_0_0_1, JAVA_61_0_0_2, JAVA_61_0_0_3, JAVA_61_0_0_4, JAVA_61_0_0_5, JAVA_61_0_0_6, JAVA_61_0_0_7, JAVA_61_0_0_8, JAVA_61_0_0_9);
	public final static JavaChoice JAVA_61_0 = new JavaChoice(JavaCardinality.ONE, JAVA_61_0_0);
	public final static JavaRule JAVA_61 = new JavaRule(org.emftext.language.java.statements.StatementsPackage.eINSTANCE.getCatchBlock(), JAVA_61_0, JavaCardinality.ONE);
	public final static JavaKeyword JAVA_62_0_0_0 = new JavaKeyword("switch", JavaCardinality.ONE);
	public final static JavaWhiteSpace JAVA_62_0_0_1 = new JavaWhiteSpace(1, JavaCardinality.ONE);
	public final static JavaKeyword JAVA_62_0_0_2 = new JavaKeyword("(", JavaCardinality.ONE);
	public final static JavaContainment JAVA_62_0_0_3 = new JavaContainment(org.emftext.language.java.statements.StatementsPackage.eINSTANCE.getSwitch().getEStructuralFeature(org.emftext.language.java.statements.StatementsPackage.SWITCH__VARIABLE), JavaCardinality.ONE, new org.eclipse.emf.ecore.EClass[] {org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getAssignmentExpression(), }, 0);
	public final static JavaKeyword JAVA_62_0_0_4 = new JavaKeyword(")", JavaCardinality.ONE);
	public final static JavaWhiteSpace JAVA_62_0_0_5 = new JavaWhiteSpace(1, JavaCardinality.ONE);
	public final static JavaKeyword JAVA_62_0_0_6 = new JavaKeyword("{", JavaCardinality.ONE);
	public final static JavaContainment JAVA_62_0_0_7_0_0_0 = new JavaContainment(org.emftext.language.java.statements.StatementsPackage.eINSTANCE.getSwitch().getEStructuralFeature(org.emftext.language.java.statements.StatementsPackage.SWITCH__CASES), JavaCardinality.STAR, new org.eclipse.emf.ecore.EClass[] {org.emftext.language.java.statements.StatementsPackage.eINSTANCE.getSwitchCase(), }, 0);
	public final static JavaSequence JAVA_62_0_0_7_0_0 = new JavaSequence(JavaCardinality.ONE, JAVA_62_0_0_7_0_0_0);
	public final static JavaChoice JAVA_62_0_0_7_0 = new JavaChoice(JavaCardinality.ONE, JAVA_62_0_0_7_0_0);
	public final static JavaCompound JAVA_62_0_0_7 = new JavaCompound(JAVA_62_0_0_7_0, JavaCardinality.ONE);
	public final static JavaKeyword JAVA_62_0_0_8 = new JavaKeyword("}", JavaCardinality.ONE);
	public final static JavaSequence JAVA_62_0_0 = new JavaSequence(JavaCardinality.ONE, JAVA_62_0_0_0, JAVA_62_0_0_1, JAVA_62_0_0_2, JAVA_62_0_0_3, JAVA_62_0_0_4, JAVA_62_0_0_5, JAVA_62_0_0_6, JAVA_62_0_0_7, JAVA_62_0_0_8);
	public final static JavaChoice JAVA_62_0 = new JavaChoice(JavaCardinality.ONE, JAVA_62_0_0);
	public final static JavaRule JAVA_62 = new JavaRule(org.emftext.language.java.statements.StatementsPackage.eINSTANCE.getSwitch(), JAVA_62_0, JavaCardinality.ONE);
	public final static JavaKeyword JAVA_63_0_0_0 = new JavaKeyword("case", JavaCardinality.ONE);
	public final static JavaContainment JAVA_63_0_0_1 = new JavaContainment(org.emftext.language.java.statements.StatementsPackage.eINSTANCE.getNormalSwitchCase().getEStructuralFeature(org.emftext.language.java.statements.StatementsPackage.NORMAL_SWITCH_CASE__CONDITION), JavaCardinality.ONE, new org.eclipse.emf.ecore.EClass[] {org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getAssignmentExpression(), }, 0);
	public final static JavaKeyword JAVA_63_0_0_2 = new JavaKeyword(":", JavaCardinality.ONE);
	public final static JavaLineBreak JAVA_63_0_0_3_0_0_0 = new JavaLineBreak(JavaCardinality.ONE, 1);
	public final static JavaContainment JAVA_63_0_0_3_0_0_1 = new JavaContainment(org.emftext.language.java.statements.StatementsPackage.eINSTANCE.getNormalSwitchCase().getEStructuralFeature(org.emftext.language.java.statements.StatementsPackage.NORMAL_SWITCH_CASE__STATEMENTS), JavaCardinality.ONE, new org.eclipse.emf.ecore.EClass[] {org.emftext.language.java.statements.StatementsPackage.eINSTANCE.getStatement(), }, 0);
	public final static JavaSequence JAVA_63_0_0_3_0_0 = new JavaSequence(JavaCardinality.ONE, JAVA_63_0_0_3_0_0_0, JAVA_63_0_0_3_0_0_1);
	public final static JavaChoice JAVA_63_0_0_3_0 = new JavaChoice(JavaCardinality.ONE, JAVA_63_0_0_3_0_0);
	public final static JavaCompound JAVA_63_0_0_3 = new JavaCompound(JAVA_63_0_0_3_0, JavaCardinality.STAR);
	public final static JavaLineBreak JAVA_63_0_0_4 = new JavaLineBreak(JavaCardinality.ONE, 0);
	public final static JavaSequence JAVA_63_0_0 = new JavaSequence(JavaCardinality.ONE, JAVA_63_0_0_0, JAVA_63_0_0_1, JAVA_63_0_0_2, JAVA_63_0_0_3, JAVA_63_0_0_4);
	public final static JavaChoice JAVA_63_0 = new JavaChoice(JavaCardinality.ONE, JAVA_63_0_0);
	public final static JavaRule JAVA_63 = new JavaRule(org.emftext.language.java.statements.StatementsPackage.eINSTANCE.getNormalSwitchCase(), JAVA_63_0, JavaCardinality.ONE);
	public final static JavaKeyword JAVA_64_0_0_0 = new JavaKeyword("default", JavaCardinality.ONE);
	public final static JavaKeyword JAVA_64_0_0_1 = new JavaKeyword(":", JavaCardinality.ONE);
	public final static JavaLineBreak JAVA_64_0_0_2_0_0_0 = new JavaLineBreak(JavaCardinality.ONE, 1);
	public final static JavaContainment JAVA_64_0_0_2_0_0_1 = new JavaContainment(org.emftext.language.java.statements.StatementsPackage.eINSTANCE.getDefaultSwitchCase().getEStructuralFeature(org.emftext.language.java.statements.StatementsPackage.DEFAULT_SWITCH_CASE__STATEMENTS), JavaCardinality.ONE, new org.eclipse.emf.ecore.EClass[] {org.emftext.language.java.statements.StatementsPackage.eINSTANCE.getStatement(), }, 0);
	public final static JavaSequence JAVA_64_0_0_2_0_0 = new JavaSequence(JavaCardinality.ONE, JAVA_64_0_0_2_0_0_0, JAVA_64_0_0_2_0_0_1);
	public final static JavaChoice JAVA_64_0_0_2_0 = new JavaChoice(JavaCardinality.ONE, JAVA_64_0_0_2_0_0);
	public final static JavaCompound JAVA_64_0_0_2 = new JavaCompound(JAVA_64_0_0_2_0, JavaCardinality.STAR);
	public final static JavaLineBreak JAVA_64_0_0_3 = new JavaLineBreak(JavaCardinality.ONE, 0);
	public final static JavaSequence JAVA_64_0_0 = new JavaSequence(JavaCardinality.ONE, JAVA_64_0_0_0, JAVA_64_0_0_1, JAVA_64_0_0_2, JAVA_64_0_0_3);
	public final static JavaChoice JAVA_64_0 = new JavaChoice(JavaCardinality.ONE, JAVA_64_0_0);
	public final static JavaRule JAVA_64 = new JavaRule(org.emftext.language.java.statements.StatementsPackage.eINSTANCE.getDefaultSwitchCase(), JAVA_64_0, JavaCardinality.ONE);
	public final static JavaKeyword JAVA_65_0_0_0 = new JavaKeyword("return", JavaCardinality.ONE);
	public final static JavaContainment JAVA_65_0_0_1 = new JavaContainment(org.emftext.language.java.statements.StatementsPackage.eINSTANCE.getReturn().getEStructuralFeature(org.emftext.language.java.statements.StatementsPackage.RETURN__RETURN_VALUE), JavaCardinality.QUESTIONMARK, new org.eclipse.emf.ecore.EClass[] {org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getAssignmentExpression(), }, 0);
	public final static JavaKeyword JAVA_65_0_0_2 = new JavaKeyword(";", JavaCardinality.ONE);
	public final static JavaSequence JAVA_65_0_0 = new JavaSequence(JavaCardinality.ONE, JAVA_65_0_0_0, JAVA_65_0_0_1, JAVA_65_0_0_2);
	public final static JavaChoice JAVA_65_0 = new JavaChoice(JavaCardinality.ONE, JAVA_65_0_0);
	public final static JavaRule JAVA_65 = new JavaRule(org.emftext.language.java.statements.StatementsPackage.eINSTANCE.getReturn(), JAVA_65_0, JavaCardinality.ONE);
	public final static JavaKeyword JAVA_66_0_0_0 = new JavaKeyword("throw", JavaCardinality.ONE);
	public final static JavaContainment JAVA_66_0_0_1 = new JavaContainment(org.emftext.language.java.statements.StatementsPackage.eINSTANCE.getThrow().getEStructuralFeature(org.emftext.language.java.statements.StatementsPackage.THROW__THROWABLE), JavaCardinality.ONE, new org.eclipse.emf.ecore.EClass[] {org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getAssignmentExpression(), }, 0);
	public final static JavaKeyword JAVA_66_0_0_2 = new JavaKeyword(";", JavaCardinality.ONE);
	public final static JavaSequence JAVA_66_0_0 = new JavaSequence(JavaCardinality.ONE, JAVA_66_0_0_0, JAVA_66_0_0_1, JAVA_66_0_0_2);
	public final static JavaChoice JAVA_66_0 = new JavaChoice(JavaCardinality.ONE, JAVA_66_0_0);
	public final static JavaRule JAVA_66 = new JavaRule(org.emftext.language.java.statements.StatementsPackage.eINSTANCE.getThrow(), JAVA_66_0, JavaCardinality.ONE);
	public final static JavaKeyword JAVA_67_0_0_0 = new JavaKeyword("break", JavaCardinality.ONE);
	public final static JavaPlaceholder JAVA_67_0_0_1_0_0_0 = new JavaPlaceholder(org.emftext.language.java.statements.StatementsPackage.eINSTANCE.getBreak().getEStructuralFeature(org.emftext.language.java.statements.StatementsPackage.BREAK__TARGET), "IDENTIFIER", JavaCardinality.ONE, 0);
	public final static JavaSequence JAVA_67_0_0_1_0_0 = new JavaSequence(JavaCardinality.ONE, JAVA_67_0_0_1_0_0_0);
	public final static JavaChoice JAVA_67_0_0_1_0 = new JavaChoice(JavaCardinality.ONE, JAVA_67_0_0_1_0_0);
	public final static JavaCompound JAVA_67_0_0_1 = new JavaCompound(JAVA_67_0_0_1_0, JavaCardinality.QUESTIONMARK);
	public final static JavaKeyword JAVA_67_0_0_2 = new JavaKeyword(";", JavaCardinality.ONE);
	public final static JavaSequence JAVA_67_0_0 = new JavaSequence(JavaCardinality.ONE, JAVA_67_0_0_0, JAVA_67_0_0_1, JAVA_67_0_0_2);
	public final static JavaChoice JAVA_67_0 = new JavaChoice(JavaCardinality.ONE, JAVA_67_0_0);
	public final static JavaRule JAVA_67 = new JavaRule(org.emftext.language.java.statements.StatementsPackage.eINSTANCE.getBreak(), JAVA_67_0, JavaCardinality.ONE);
	public final static JavaKeyword JAVA_68_0_0_0 = new JavaKeyword("continue", JavaCardinality.ONE);
	public final static JavaPlaceholder JAVA_68_0_0_1_0_0_0 = new JavaPlaceholder(org.emftext.language.java.statements.StatementsPackage.eINSTANCE.getContinue().getEStructuralFeature(org.emftext.language.java.statements.StatementsPackage.CONTINUE__TARGET), "IDENTIFIER", JavaCardinality.ONE, 0);
	public final static JavaSequence JAVA_68_0_0_1_0_0 = new JavaSequence(JavaCardinality.ONE, JAVA_68_0_0_1_0_0_0);
	public final static JavaChoice JAVA_68_0_0_1_0 = new JavaChoice(JavaCardinality.ONE, JAVA_68_0_0_1_0_0);
	public final static JavaCompound JAVA_68_0_0_1 = new JavaCompound(JAVA_68_0_0_1_0, JavaCardinality.QUESTIONMARK);
	public final static JavaKeyword JAVA_68_0_0_2 = new JavaKeyword(";", JavaCardinality.ONE);
	public final static JavaSequence JAVA_68_0_0 = new JavaSequence(JavaCardinality.ONE, JAVA_68_0_0_0, JAVA_68_0_0_1, JAVA_68_0_0_2);
	public final static JavaChoice JAVA_68_0 = new JavaChoice(JavaCardinality.ONE, JAVA_68_0_0);
	public final static JavaRule JAVA_68 = new JavaRule(org.emftext.language.java.statements.StatementsPackage.eINSTANCE.getContinue(), JAVA_68_0, JavaCardinality.ONE);
	public final static JavaPlaceholder JAVA_69_0_0_0 = new JavaPlaceholder(org.emftext.language.java.statements.StatementsPackage.eINSTANCE.getJumpLabel().getEStructuralFeature(org.emftext.language.java.statements.StatementsPackage.JUMP_LABEL__NAME), "IDENTIFIER", JavaCardinality.ONE, 0);
	public final static JavaKeyword JAVA_69_0_0_1 = new JavaKeyword(":", JavaCardinality.ONE);
	public final static JavaContainment JAVA_69_0_0_2 = new JavaContainment(org.emftext.language.java.statements.StatementsPackage.eINSTANCE.getJumpLabel().getEStructuralFeature(org.emftext.language.java.statements.StatementsPackage.JUMP_LABEL__STATEMENT), JavaCardinality.ONE, new org.eclipse.emf.ecore.EClass[] {org.emftext.language.java.statements.StatementsPackage.eINSTANCE.getStatement(), }, 0);
	public final static JavaSequence JAVA_69_0_0 = new JavaSequence(JavaCardinality.ONE, JAVA_69_0_0_0, JAVA_69_0_0_1, JAVA_69_0_0_2);
	public final static JavaChoice JAVA_69_0 = new JavaChoice(JavaCardinality.ONE, JAVA_69_0_0);
	public final static JavaRule JAVA_69 = new JavaRule(org.emftext.language.java.statements.StatementsPackage.eINSTANCE.getJumpLabel(), JAVA_69_0, JavaCardinality.ONE);
	public final static JavaContainment JAVA_70_0_0_0 = new JavaContainment(org.emftext.language.java.statements.StatementsPackage.eINSTANCE.getExpressionStatement().getEStructuralFeature(org.emftext.language.java.statements.StatementsPackage.EXPRESSION_STATEMENT__EXPRESSION), JavaCardinality.ONE, new org.eclipse.emf.ecore.EClass[] {org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getAssignmentExpression(), }, 0);
	public final static JavaKeyword JAVA_70_0_0_1 = new JavaKeyword(";", JavaCardinality.ONE);
	public final static JavaSequence JAVA_70_0_0 = new JavaSequence(JavaCardinality.ONE, JAVA_70_0_0_0, JAVA_70_0_0_1);
	public final static JavaChoice JAVA_70_0 = new JavaChoice(JavaCardinality.ONE, JAVA_70_0_0);
	public final static JavaRule JAVA_70 = new JavaRule(org.emftext.language.java.statements.StatementsPackage.eINSTANCE.getExpressionStatement(), JAVA_70_0, JavaCardinality.ONE);
	public final static JavaContainment JAVA_71_0_0_0 = new JavaContainment(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getExpressionList().getEStructuralFeature(org.emftext.language.java.expressions.ExpressionsPackage.EXPRESSION_LIST__EXPRESSIONS), JavaCardinality.ONE, new org.eclipse.emf.ecore.EClass[] {org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getAssignmentExpression(), }, 0);
	public final static JavaKeyword JAVA_71_0_0_1_0_0_0 = new JavaKeyword(",", JavaCardinality.ONE);
	public final static JavaContainment JAVA_71_0_0_1_0_0_1 = new JavaContainment(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getExpressionList().getEStructuralFeature(org.emftext.language.java.expressions.ExpressionsPackage.EXPRESSION_LIST__EXPRESSIONS), JavaCardinality.ONE, new org.eclipse.emf.ecore.EClass[] {org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getAssignmentExpression(), }, 0);
	public final static JavaSequence JAVA_71_0_0_1_0_0 = new JavaSequence(JavaCardinality.ONE, JAVA_71_0_0_1_0_0_0, JAVA_71_0_0_1_0_0_1);
	public final static JavaChoice JAVA_71_0_0_1_0 = new JavaChoice(JavaCardinality.ONE, JAVA_71_0_0_1_0_0);
	public final static JavaCompound JAVA_71_0_0_1 = new JavaCompound(JAVA_71_0_0_1_0, JavaCardinality.STAR);
	public final static JavaSequence JAVA_71_0_0 = new JavaSequence(JavaCardinality.ONE, JAVA_71_0_0_0, JAVA_71_0_0_1);
	public final static JavaChoice JAVA_71_0 = new JavaChoice(JavaCardinality.ONE, JAVA_71_0_0);
	public final static JavaRule JAVA_71 = new JavaRule(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getExpressionList(), JAVA_71_0, JavaCardinality.ONE);
	public final static JavaContainment JAVA_72_0_0_0 = new JavaContainment(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getAssignmentExpression().getEStructuralFeature(org.emftext.language.java.expressions.ExpressionsPackage.ASSIGNMENT_EXPRESSION__CHILD), JavaCardinality.ONE, new org.eclipse.emf.ecore.EClass[] {org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getConditionalExpression(), }, 0);
	public final static JavaWhiteSpace JAVA_72_0_0_1_0_0_0 = new JavaWhiteSpace(1, JavaCardinality.ONE);
	public final static JavaContainment JAVA_72_0_0_1_0_0_1 = new JavaContainment(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getAssignmentExpression().getEStructuralFeature(org.emftext.language.java.expressions.ExpressionsPackage.ASSIGNMENT_EXPRESSION__ASSIGNMENT_OPERATOR), JavaCardinality.ONE, new org.eclipse.emf.ecore.EClass[] {org.emftext.language.java.operators.OperatorsPackage.eINSTANCE.getAssignmentOperator(), }, 0);
	public final static JavaWhiteSpace JAVA_72_0_0_1_0_0_2 = new JavaWhiteSpace(1, JavaCardinality.ONE);
	public final static JavaContainment JAVA_72_0_0_1_0_0_3 = new JavaContainment(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getAssignmentExpression().getEStructuralFeature(org.emftext.language.java.expressions.ExpressionsPackage.ASSIGNMENT_EXPRESSION__VALUE), JavaCardinality.ONE, new org.eclipse.emf.ecore.EClass[] {org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getAssignmentExpression(), }, 0);
	public final static JavaSequence JAVA_72_0_0_1_0_0 = new JavaSequence(JavaCardinality.ONE, JAVA_72_0_0_1_0_0_0, JAVA_72_0_0_1_0_0_1, JAVA_72_0_0_1_0_0_2, JAVA_72_0_0_1_0_0_3);
	public final static JavaChoice JAVA_72_0_0_1_0 = new JavaChoice(JavaCardinality.ONE, JAVA_72_0_0_1_0_0);
	public final static JavaCompound JAVA_72_0_0_1 = new JavaCompound(JAVA_72_0_0_1_0, JavaCardinality.QUESTIONMARK);
	public final static JavaSequence JAVA_72_0_0 = new JavaSequence(JavaCardinality.ONE, JAVA_72_0_0_0, JAVA_72_0_0_1);
	public final static JavaChoice JAVA_72_0 = new JavaChoice(JavaCardinality.ONE, JAVA_72_0_0);
	public final static JavaRule JAVA_72 = new JavaRule(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getAssignmentExpression(), JAVA_72_0, JavaCardinality.ONE);
	public final static JavaContainment JAVA_73_0_0_0 = new JavaContainment(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getConditionalExpression().getEStructuralFeature(org.emftext.language.java.expressions.ExpressionsPackage.CONDITIONAL_EXPRESSION__CHILD), JavaCardinality.ONE, new org.eclipse.emf.ecore.EClass[] {org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getConditionalOrExpression(), }, 0);
	public final static JavaKeyword JAVA_73_0_0_1_0_0_0 = new JavaKeyword("?", JavaCardinality.ONE);
	public final static JavaContainment JAVA_73_0_0_1_0_0_1 = new JavaContainment(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getConditionalExpression().getEStructuralFeature(org.emftext.language.java.expressions.ExpressionsPackage.CONDITIONAL_EXPRESSION__EXPRESSION_IF), JavaCardinality.ONE, new org.eclipse.emf.ecore.EClass[] {org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getAssignmentExpression(), }, 0);
	public final static JavaKeyword JAVA_73_0_0_1_0_0_2 = new JavaKeyword(":", JavaCardinality.ONE);
	public final static JavaContainment JAVA_73_0_0_1_0_0_3 = new JavaContainment(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getConditionalExpression().getEStructuralFeature(org.emftext.language.java.expressions.ExpressionsPackage.CONDITIONAL_EXPRESSION__EXPRESSION_ELSE), JavaCardinality.ONE, new org.eclipse.emf.ecore.EClass[] {org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getConditionalExpression(), }, 0);
	public final static JavaSequence JAVA_73_0_0_1_0_0 = new JavaSequence(JavaCardinality.ONE, JAVA_73_0_0_1_0_0_0, JAVA_73_0_0_1_0_0_1, JAVA_73_0_0_1_0_0_2, JAVA_73_0_0_1_0_0_3);
	public final static JavaChoice JAVA_73_0_0_1_0 = new JavaChoice(JavaCardinality.ONE, JAVA_73_0_0_1_0_0);
	public final static JavaCompound JAVA_73_0_0_1 = new JavaCompound(JAVA_73_0_0_1_0, JavaCardinality.QUESTIONMARK);
	public final static JavaSequence JAVA_73_0_0 = new JavaSequence(JavaCardinality.ONE, JAVA_73_0_0_0, JAVA_73_0_0_1);
	public final static JavaChoice JAVA_73_0 = new JavaChoice(JavaCardinality.ONE, JAVA_73_0_0);
	public final static JavaRule JAVA_73 = new JavaRule(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getConditionalExpression(), JAVA_73_0, JavaCardinality.ONE);
	public final static JavaContainment JAVA_74_0_0_0 = new JavaContainment(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getConditionalOrExpression().getEStructuralFeature(org.emftext.language.java.expressions.ExpressionsPackage.CONDITIONAL_OR_EXPRESSION__CHILDREN), JavaCardinality.ONE, new org.eclipse.emf.ecore.EClass[] {org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getConditionalAndExpression(), }, 0);
	public final static JavaKeyword JAVA_74_0_0_1_0_0_0 = new JavaKeyword("||", JavaCardinality.ONE);
	public final static JavaContainment JAVA_74_0_0_1_0_0_1 = new JavaContainment(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getConditionalOrExpression().getEStructuralFeature(org.emftext.language.java.expressions.ExpressionsPackage.CONDITIONAL_OR_EXPRESSION__CHILDREN), JavaCardinality.ONE, new org.eclipse.emf.ecore.EClass[] {org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getConditionalAndExpression(), }, 0);
	public final static JavaSequence JAVA_74_0_0_1_0_0 = new JavaSequence(JavaCardinality.ONE, JAVA_74_0_0_1_0_0_0, JAVA_74_0_0_1_0_0_1);
	public final static JavaChoice JAVA_74_0_0_1_0 = new JavaChoice(JavaCardinality.ONE, JAVA_74_0_0_1_0_0);
	public final static JavaCompound JAVA_74_0_0_1 = new JavaCompound(JAVA_74_0_0_1_0, JavaCardinality.STAR);
	public final static JavaSequence JAVA_74_0_0 = new JavaSequence(JavaCardinality.ONE, JAVA_74_0_0_0, JAVA_74_0_0_1);
	public final static JavaChoice JAVA_74_0 = new JavaChoice(JavaCardinality.ONE, JAVA_74_0_0);
	public final static JavaRule JAVA_74 = new JavaRule(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getConditionalOrExpression(), JAVA_74_0, JavaCardinality.ONE);
	public final static JavaContainment JAVA_75_0_0_0 = new JavaContainment(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getConditionalAndExpression().getEStructuralFeature(org.emftext.language.java.expressions.ExpressionsPackage.CONDITIONAL_AND_EXPRESSION__CHILDREN), JavaCardinality.ONE, new org.eclipse.emf.ecore.EClass[] {org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getInclusiveOrExpression(), }, 0);
	public final static JavaKeyword JAVA_75_0_0_1_0_0_0 = new JavaKeyword("&&", JavaCardinality.ONE);
	public final static JavaContainment JAVA_75_0_0_1_0_0_1 = new JavaContainment(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getConditionalAndExpression().getEStructuralFeature(org.emftext.language.java.expressions.ExpressionsPackage.CONDITIONAL_AND_EXPRESSION__CHILDREN), JavaCardinality.ONE, new org.eclipse.emf.ecore.EClass[] {org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getInclusiveOrExpression(), }, 0);
	public final static JavaSequence JAVA_75_0_0_1_0_0 = new JavaSequence(JavaCardinality.ONE, JAVA_75_0_0_1_0_0_0, JAVA_75_0_0_1_0_0_1);
	public final static JavaChoice JAVA_75_0_0_1_0 = new JavaChoice(JavaCardinality.ONE, JAVA_75_0_0_1_0_0);
	public final static JavaCompound JAVA_75_0_0_1 = new JavaCompound(JAVA_75_0_0_1_0, JavaCardinality.STAR);
	public final static JavaSequence JAVA_75_0_0 = new JavaSequence(JavaCardinality.ONE, JAVA_75_0_0_0, JAVA_75_0_0_1);
	public final static JavaChoice JAVA_75_0 = new JavaChoice(JavaCardinality.ONE, JAVA_75_0_0);
	public final static JavaRule JAVA_75 = new JavaRule(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getConditionalAndExpression(), JAVA_75_0, JavaCardinality.ONE);
	public final static JavaContainment JAVA_76_0_0_0 = new JavaContainment(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getInclusiveOrExpression().getEStructuralFeature(org.emftext.language.java.expressions.ExpressionsPackage.INCLUSIVE_OR_EXPRESSION__CHILDREN), JavaCardinality.ONE, new org.eclipse.emf.ecore.EClass[] {org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getExclusiveOrExpression(), }, 0);
	public final static JavaKeyword JAVA_76_0_0_1_0_0_0 = new JavaKeyword("|", JavaCardinality.ONE);
	public final static JavaContainment JAVA_76_0_0_1_0_0_1 = new JavaContainment(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getInclusiveOrExpression().getEStructuralFeature(org.emftext.language.java.expressions.ExpressionsPackage.INCLUSIVE_OR_EXPRESSION__CHILDREN), JavaCardinality.ONE, new org.eclipse.emf.ecore.EClass[] {org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getExclusiveOrExpression(), }, 0);
	public final static JavaSequence JAVA_76_0_0_1_0_0 = new JavaSequence(JavaCardinality.ONE, JAVA_76_0_0_1_0_0_0, JAVA_76_0_0_1_0_0_1);
	public final static JavaChoice JAVA_76_0_0_1_0 = new JavaChoice(JavaCardinality.ONE, JAVA_76_0_0_1_0_0);
	public final static JavaCompound JAVA_76_0_0_1 = new JavaCompound(JAVA_76_0_0_1_0, JavaCardinality.STAR);
	public final static JavaSequence JAVA_76_0_0 = new JavaSequence(JavaCardinality.ONE, JAVA_76_0_0_0, JAVA_76_0_0_1);
	public final static JavaChoice JAVA_76_0 = new JavaChoice(JavaCardinality.ONE, JAVA_76_0_0);
	public final static JavaRule JAVA_76 = new JavaRule(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getInclusiveOrExpression(), JAVA_76_0, JavaCardinality.ONE);
	public final static JavaContainment JAVA_77_0_0_0 = new JavaContainment(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getExclusiveOrExpression().getEStructuralFeature(org.emftext.language.java.expressions.ExpressionsPackage.EXCLUSIVE_OR_EXPRESSION__CHILDREN), JavaCardinality.ONE, new org.eclipse.emf.ecore.EClass[] {org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getAndExpression(), }, 0);
	public final static JavaKeyword JAVA_77_0_0_1_0_0_0 = new JavaKeyword("^", JavaCardinality.ONE);
	public final static JavaContainment JAVA_77_0_0_1_0_0_1 = new JavaContainment(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getExclusiveOrExpression().getEStructuralFeature(org.emftext.language.java.expressions.ExpressionsPackage.EXCLUSIVE_OR_EXPRESSION__CHILDREN), JavaCardinality.ONE, new org.eclipse.emf.ecore.EClass[] {org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getAndExpression(), }, 0);
	public final static JavaSequence JAVA_77_0_0_1_0_0 = new JavaSequence(JavaCardinality.ONE, JAVA_77_0_0_1_0_0_0, JAVA_77_0_0_1_0_0_1);
	public final static JavaChoice JAVA_77_0_0_1_0 = new JavaChoice(JavaCardinality.ONE, JAVA_77_0_0_1_0_0);
	public final static JavaCompound JAVA_77_0_0_1 = new JavaCompound(JAVA_77_0_0_1_0, JavaCardinality.STAR);
	public final static JavaSequence JAVA_77_0_0 = new JavaSequence(JavaCardinality.ONE, JAVA_77_0_0_0, JAVA_77_0_0_1);
	public final static JavaChoice JAVA_77_0 = new JavaChoice(JavaCardinality.ONE, JAVA_77_0_0);
	public final static JavaRule JAVA_77 = new JavaRule(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getExclusiveOrExpression(), JAVA_77_0, JavaCardinality.ONE);
	public final static JavaContainment JAVA_78_0_0_0 = new JavaContainment(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getAndExpression().getEStructuralFeature(org.emftext.language.java.expressions.ExpressionsPackage.AND_EXPRESSION__CHILDREN), JavaCardinality.ONE, new org.eclipse.emf.ecore.EClass[] {org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getEqualityExpression(), }, 0);
	public final static JavaKeyword JAVA_78_0_0_1_0_0_0 = new JavaKeyword("&", JavaCardinality.ONE);
	public final static JavaContainment JAVA_78_0_0_1_0_0_1 = new JavaContainment(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getAndExpression().getEStructuralFeature(org.emftext.language.java.expressions.ExpressionsPackage.AND_EXPRESSION__CHILDREN), JavaCardinality.ONE, new org.eclipse.emf.ecore.EClass[] {org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getEqualityExpression(), }, 0);
	public final static JavaSequence JAVA_78_0_0_1_0_0 = new JavaSequence(JavaCardinality.ONE, JAVA_78_0_0_1_0_0_0, JAVA_78_0_0_1_0_0_1);
	public final static JavaChoice JAVA_78_0_0_1_0 = new JavaChoice(JavaCardinality.ONE, JAVA_78_0_0_1_0_0);
	public final static JavaCompound JAVA_78_0_0_1 = new JavaCompound(JAVA_78_0_0_1_0, JavaCardinality.STAR);
	public final static JavaSequence JAVA_78_0_0 = new JavaSequence(JavaCardinality.ONE, JAVA_78_0_0_0, JAVA_78_0_0_1);
	public final static JavaChoice JAVA_78_0 = new JavaChoice(JavaCardinality.ONE, JAVA_78_0_0);
	public final static JavaRule JAVA_78 = new JavaRule(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getAndExpression(), JAVA_78_0, JavaCardinality.ONE);
	public final static JavaContainment JAVA_79_0_0_0 = new JavaContainment(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getEqualityExpression().getEStructuralFeature(org.emftext.language.java.expressions.ExpressionsPackage.EQUALITY_EXPRESSION__CHILDREN), JavaCardinality.ONE, new org.eclipse.emf.ecore.EClass[] {org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getInstanceOfExpression(), }, 0);
	public final static JavaWhiteSpace JAVA_79_0_0_1_0_0_0 = new JavaWhiteSpace(1, JavaCardinality.ONE);
	public final static JavaContainment JAVA_79_0_0_1_0_0_1 = new JavaContainment(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getEqualityExpression().getEStructuralFeature(org.emftext.language.java.expressions.ExpressionsPackage.EQUALITY_EXPRESSION__EQUALITY_OPERATORS), JavaCardinality.ONE, new org.eclipse.emf.ecore.EClass[] {org.emftext.language.java.operators.OperatorsPackage.eINSTANCE.getEqualityOperator(), }, 0);
	public final static JavaWhiteSpace JAVA_79_0_0_1_0_0_2 = new JavaWhiteSpace(1, JavaCardinality.ONE);
	public final static JavaContainment JAVA_79_0_0_1_0_0_3 = new JavaContainment(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getEqualityExpression().getEStructuralFeature(org.emftext.language.java.expressions.ExpressionsPackage.EQUALITY_EXPRESSION__CHILDREN), JavaCardinality.ONE, new org.eclipse.emf.ecore.EClass[] {org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getInstanceOfExpression(), }, 0);
	public final static JavaSequence JAVA_79_0_0_1_0_0 = new JavaSequence(JavaCardinality.ONE, JAVA_79_0_0_1_0_0_0, JAVA_79_0_0_1_0_0_1, JAVA_79_0_0_1_0_0_2, JAVA_79_0_0_1_0_0_3);
	public final static JavaChoice JAVA_79_0_0_1_0 = new JavaChoice(JavaCardinality.ONE, JAVA_79_0_0_1_0_0);
	public final static JavaCompound JAVA_79_0_0_1 = new JavaCompound(JAVA_79_0_0_1_0, JavaCardinality.STAR);
	public final static JavaSequence JAVA_79_0_0 = new JavaSequence(JavaCardinality.ONE, JAVA_79_0_0_0, JAVA_79_0_0_1);
	public final static JavaChoice JAVA_79_0 = new JavaChoice(JavaCardinality.ONE, JAVA_79_0_0);
	public final static JavaRule JAVA_79 = new JavaRule(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getEqualityExpression(), JAVA_79_0, JavaCardinality.ONE);
	public final static JavaContainment JAVA_80_0_0_0 = new JavaContainment(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getInstanceOfExpression().getEStructuralFeature(org.emftext.language.java.expressions.ExpressionsPackage.INSTANCE_OF_EXPRESSION__CHILD), JavaCardinality.ONE, new org.eclipse.emf.ecore.EClass[] {org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getRelationExpression(), }, 0);
	public final static JavaKeyword JAVA_80_0_0_1_0_0_0 = new JavaKeyword("instanceof", JavaCardinality.ONE);
	public final static JavaContainment JAVA_80_0_0_1_0_0_1 = new JavaContainment(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getInstanceOfExpression().getEStructuralFeature(org.emftext.language.java.expressions.ExpressionsPackage.INSTANCE_OF_EXPRESSION__TYPE_REFERENCE), JavaCardinality.ONE, new org.eclipse.emf.ecore.EClass[] {org.emftext.language.java.types.TypesPackage.eINSTANCE.getTypeReference(), }, 0);
	public final static JavaContainment JAVA_80_0_0_1_0_0_2 = new JavaContainment(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getInstanceOfExpression().getEStructuralFeature(org.emftext.language.java.expressions.ExpressionsPackage.INSTANCE_OF_EXPRESSION__ARRAY_DIMENSIONS_BEFORE), JavaCardinality.STAR, new org.eclipse.emf.ecore.EClass[] {org.emftext.language.java.arrays.ArraysPackage.eINSTANCE.getArrayDimension(), }, 0);
	public final static JavaSequence JAVA_80_0_0_1_0_0 = new JavaSequence(JavaCardinality.ONE, JAVA_80_0_0_1_0_0_0, JAVA_80_0_0_1_0_0_1, JAVA_80_0_0_1_0_0_2);
	public final static JavaChoice JAVA_80_0_0_1_0 = new JavaChoice(JavaCardinality.ONE, JAVA_80_0_0_1_0_0);
	public final static JavaCompound JAVA_80_0_0_1 = new JavaCompound(JAVA_80_0_0_1_0, JavaCardinality.QUESTIONMARK);
	public final static JavaSequence JAVA_80_0_0 = new JavaSequence(JavaCardinality.ONE, JAVA_80_0_0_0, JAVA_80_0_0_1);
	public final static JavaChoice JAVA_80_0 = new JavaChoice(JavaCardinality.ONE, JAVA_80_0_0);
	public final static JavaRule JAVA_80 = new JavaRule(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getInstanceOfExpression(), JAVA_80_0, JavaCardinality.ONE);
	public final static JavaContainment JAVA_81_0_0_0 = new JavaContainment(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getRelationExpression().getEStructuralFeature(org.emftext.language.java.expressions.ExpressionsPackage.RELATION_EXPRESSION__CHILDREN), JavaCardinality.ONE, new org.eclipse.emf.ecore.EClass[] {org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getShiftExpression(), }, 0);
	public final static JavaWhiteSpace JAVA_81_0_0_1_0_0_0 = new JavaWhiteSpace(1, JavaCardinality.ONE);
	public final static JavaContainment JAVA_81_0_0_1_0_0_1 = new JavaContainment(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getRelationExpression().getEStructuralFeature(org.emftext.language.java.expressions.ExpressionsPackage.RELATION_EXPRESSION__RELATION_OPERATORS), JavaCardinality.ONE, new org.eclipse.emf.ecore.EClass[] {org.emftext.language.java.operators.OperatorsPackage.eINSTANCE.getRelationOperator(), }, 0);
	public final static JavaWhiteSpace JAVA_81_0_0_1_0_0_2 = new JavaWhiteSpace(1, JavaCardinality.ONE);
	public final static JavaContainment JAVA_81_0_0_1_0_0_3 = new JavaContainment(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getRelationExpression().getEStructuralFeature(org.emftext.language.java.expressions.ExpressionsPackage.RELATION_EXPRESSION__CHILDREN), JavaCardinality.ONE, new org.eclipse.emf.ecore.EClass[] {org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getShiftExpression(), }, 0);
	public final static JavaSequence JAVA_81_0_0_1_0_0 = new JavaSequence(JavaCardinality.ONE, JAVA_81_0_0_1_0_0_0, JAVA_81_0_0_1_0_0_1, JAVA_81_0_0_1_0_0_2, JAVA_81_0_0_1_0_0_3);
	public final static JavaChoice JAVA_81_0_0_1_0 = new JavaChoice(JavaCardinality.ONE, JAVA_81_0_0_1_0_0);
	public final static JavaCompound JAVA_81_0_0_1 = new JavaCompound(JAVA_81_0_0_1_0, JavaCardinality.STAR);
	public final static JavaSequence JAVA_81_0_0 = new JavaSequence(JavaCardinality.ONE, JAVA_81_0_0_0, JAVA_81_0_0_1);
	public final static JavaChoice JAVA_81_0 = new JavaChoice(JavaCardinality.ONE, JAVA_81_0_0);
	public final static JavaRule JAVA_81 = new JavaRule(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getRelationExpression(), JAVA_81_0, JavaCardinality.ONE);
	public final static JavaContainment JAVA_82_0_0_0 = new JavaContainment(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getShiftExpression().getEStructuralFeature(org.emftext.language.java.expressions.ExpressionsPackage.SHIFT_EXPRESSION__CHILDREN), JavaCardinality.ONE, new org.eclipse.emf.ecore.EClass[] {org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getAdditiveExpression(), }, 0);
	public final static JavaWhiteSpace JAVA_82_0_0_1_0_0_0 = new JavaWhiteSpace(1, JavaCardinality.ONE);
	public final static JavaContainment JAVA_82_0_0_1_0_0_1 = new JavaContainment(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getShiftExpression().getEStructuralFeature(org.emftext.language.java.expressions.ExpressionsPackage.SHIFT_EXPRESSION__SHIFT_OPERATORS), JavaCardinality.ONE, new org.eclipse.emf.ecore.EClass[] {org.emftext.language.java.operators.OperatorsPackage.eINSTANCE.getShiftOperator(), }, 0);
	public final static JavaWhiteSpace JAVA_82_0_0_1_0_0_2 = new JavaWhiteSpace(1, JavaCardinality.ONE);
	public final static JavaContainment JAVA_82_0_0_1_0_0_3 = new JavaContainment(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getShiftExpression().getEStructuralFeature(org.emftext.language.java.expressions.ExpressionsPackage.SHIFT_EXPRESSION__CHILDREN), JavaCardinality.ONE, new org.eclipse.emf.ecore.EClass[] {org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getAdditiveExpression(), }, 0);
	public final static JavaSequence JAVA_82_0_0_1_0_0 = new JavaSequence(JavaCardinality.ONE, JAVA_82_0_0_1_0_0_0, JAVA_82_0_0_1_0_0_1, JAVA_82_0_0_1_0_0_2, JAVA_82_0_0_1_0_0_3);
	public final static JavaChoice JAVA_82_0_0_1_0 = new JavaChoice(JavaCardinality.ONE, JAVA_82_0_0_1_0_0);
	public final static JavaCompound JAVA_82_0_0_1 = new JavaCompound(JAVA_82_0_0_1_0, JavaCardinality.STAR);
	public final static JavaSequence JAVA_82_0_0 = new JavaSequence(JavaCardinality.ONE, JAVA_82_0_0_0, JAVA_82_0_0_1);
	public final static JavaChoice JAVA_82_0 = new JavaChoice(JavaCardinality.ONE, JAVA_82_0_0);
	public final static JavaRule JAVA_82 = new JavaRule(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getShiftExpression(), JAVA_82_0, JavaCardinality.ONE);
	public final static JavaContainment JAVA_83_0_0_0 = new JavaContainment(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getAdditiveExpression().getEStructuralFeature(org.emftext.language.java.expressions.ExpressionsPackage.ADDITIVE_EXPRESSION__CHILDREN), JavaCardinality.ONE, new org.eclipse.emf.ecore.EClass[] {org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getMultiplicativeExpression(), }, 0);
	public final static JavaWhiteSpace JAVA_83_0_0_1_0_0_0 = new JavaWhiteSpace(1, JavaCardinality.ONE);
	public final static JavaContainment JAVA_83_0_0_1_0_0_1 = new JavaContainment(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getAdditiveExpression().getEStructuralFeature(org.emftext.language.java.expressions.ExpressionsPackage.ADDITIVE_EXPRESSION__ADDITIVE_OPERATORS), JavaCardinality.ONE, new org.eclipse.emf.ecore.EClass[] {org.emftext.language.java.operators.OperatorsPackage.eINSTANCE.getAdditiveOperator(), }, 0);
	public final static JavaWhiteSpace JAVA_83_0_0_1_0_0_2 = new JavaWhiteSpace(1, JavaCardinality.ONE);
	public final static JavaContainment JAVA_83_0_0_1_0_0_3 = new JavaContainment(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getAdditiveExpression().getEStructuralFeature(org.emftext.language.java.expressions.ExpressionsPackage.ADDITIVE_EXPRESSION__CHILDREN), JavaCardinality.ONE, new org.eclipse.emf.ecore.EClass[] {org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getMultiplicativeExpression(), }, 0);
	public final static JavaSequence JAVA_83_0_0_1_0_0 = new JavaSequence(JavaCardinality.ONE, JAVA_83_0_0_1_0_0_0, JAVA_83_0_0_1_0_0_1, JAVA_83_0_0_1_0_0_2, JAVA_83_0_0_1_0_0_3);
	public final static JavaChoice JAVA_83_0_0_1_0 = new JavaChoice(JavaCardinality.ONE, JAVA_83_0_0_1_0_0);
	public final static JavaCompound JAVA_83_0_0_1 = new JavaCompound(JAVA_83_0_0_1_0, JavaCardinality.STAR);
	public final static JavaSequence JAVA_83_0_0 = new JavaSequence(JavaCardinality.ONE, JAVA_83_0_0_0, JAVA_83_0_0_1);
	public final static JavaChoice JAVA_83_0 = new JavaChoice(JavaCardinality.ONE, JAVA_83_0_0);
	public final static JavaRule JAVA_83 = new JavaRule(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getAdditiveExpression(), JAVA_83_0, JavaCardinality.ONE);
	public final static JavaContainment JAVA_84_0_0_0 = new JavaContainment(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getMultiplicativeExpression().getEStructuralFeature(org.emftext.language.java.expressions.ExpressionsPackage.MULTIPLICATIVE_EXPRESSION__CHILDREN), JavaCardinality.ONE, new org.eclipse.emf.ecore.EClass[] {org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getUnaryExpression(), }, 0);
	public final static JavaWhiteSpace JAVA_84_0_0_1_0_0_0 = new JavaWhiteSpace(1, JavaCardinality.ONE);
	public final static JavaContainment JAVA_84_0_0_1_0_0_1 = new JavaContainment(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getMultiplicativeExpression().getEStructuralFeature(org.emftext.language.java.expressions.ExpressionsPackage.MULTIPLICATIVE_EXPRESSION__MULTIPLICATIVE_OPERATORS), JavaCardinality.ONE, new org.eclipse.emf.ecore.EClass[] {org.emftext.language.java.operators.OperatorsPackage.eINSTANCE.getMultiplicativeOperator(), }, 0);
	public final static JavaWhiteSpace JAVA_84_0_0_1_0_0_2 = new JavaWhiteSpace(1, JavaCardinality.ONE);
	public final static JavaContainment JAVA_84_0_0_1_0_0_3 = new JavaContainment(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getMultiplicativeExpression().getEStructuralFeature(org.emftext.language.java.expressions.ExpressionsPackage.MULTIPLICATIVE_EXPRESSION__CHILDREN), JavaCardinality.ONE, new org.eclipse.emf.ecore.EClass[] {org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getUnaryExpression(), }, 0);
	public final static JavaSequence JAVA_84_0_0_1_0_0 = new JavaSequence(JavaCardinality.ONE, JAVA_84_0_0_1_0_0_0, JAVA_84_0_0_1_0_0_1, JAVA_84_0_0_1_0_0_2, JAVA_84_0_0_1_0_0_3);
	public final static JavaChoice JAVA_84_0_0_1_0 = new JavaChoice(JavaCardinality.ONE, JAVA_84_0_0_1_0_0);
	public final static JavaCompound JAVA_84_0_0_1 = new JavaCompound(JAVA_84_0_0_1_0, JavaCardinality.STAR);
	public final static JavaSequence JAVA_84_0_0 = new JavaSequence(JavaCardinality.ONE, JAVA_84_0_0_0, JAVA_84_0_0_1);
	public final static JavaChoice JAVA_84_0 = new JavaChoice(JavaCardinality.ONE, JAVA_84_0_0);
	public final static JavaRule JAVA_84 = new JavaRule(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getMultiplicativeExpression(), JAVA_84_0, JavaCardinality.ONE);
	public final static JavaContainment JAVA_85_0_0_0 = new JavaContainment(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getUnaryExpression().getEStructuralFeature(org.emftext.language.java.expressions.ExpressionsPackage.UNARY_EXPRESSION__OPERATORS), JavaCardinality.STAR, new org.eclipse.emf.ecore.EClass[] {org.emftext.language.java.operators.OperatorsPackage.eINSTANCE.getUnaryOperator(), }, 0);
	public final static JavaContainment JAVA_85_0_0_1 = new JavaContainment(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getUnaryExpression().getEStructuralFeature(org.emftext.language.java.expressions.ExpressionsPackage.UNARY_EXPRESSION__CHILD), JavaCardinality.ONE, new org.eclipse.emf.ecore.EClass[] {org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getUnaryModificationExpression(), }, 0);
	public final static JavaSequence JAVA_85_0_0 = new JavaSequence(JavaCardinality.ONE, JAVA_85_0_0_0, JAVA_85_0_0_1);
	public final static JavaChoice JAVA_85_0 = new JavaChoice(JavaCardinality.ONE, JAVA_85_0_0);
	public final static JavaRule JAVA_85 = new JavaRule(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getUnaryExpression(), JAVA_85_0, JavaCardinality.ONE);
	public final static JavaContainment JAVA_86_0_0_0 = new JavaContainment(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getSuffixUnaryModificationExpression().getEStructuralFeature(org.emftext.language.java.expressions.ExpressionsPackage.SUFFIX_UNARY_MODIFICATION_EXPRESSION__CHILD), JavaCardinality.ONE, new org.eclipse.emf.ecore.EClass[] {org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getUnaryModificationExpressionChild(), }, 0);
	public final static JavaContainment JAVA_86_0_0_1_0_0_0 = new JavaContainment(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getSuffixUnaryModificationExpression().getEStructuralFeature(org.emftext.language.java.expressions.ExpressionsPackage.SUFFIX_UNARY_MODIFICATION_EXPRESSION__OPERATOR), JavaCardinality.ONE, new org.eclipse.emf.ecore.EClass[] {org.emftext.language.java.operators.OperatorsPackage.eINSTANCE.getUnaryModificationOperator(), }, 0);
	public final static JavaSequence JAVA_86_0_0_1_0_0 = new JavaSequence(JavaCardinality.ONE, JAVA_86_0_0_1_0_0_0);
	public final static JavaChoice JAVA_86_0_0_1_0 = new JavaChoice(JavaCardinality.ONE, JAVA_86_0_0_1_0_0);
	public final static JavaCompound JAVA_86_0_0_1 = new JavaCompound(JAVA_86_0_0_1_0, JavaCardinality.QUESTIONMARK);
	public final static JavaSequence JAVA_86_0_0 = new JavaSequence(JavaCardinality.ONE, JAVA_86_0_0_0, JAVA_86_0_0_1);
	public final static JavaChoice JAVA_86_0 = new JavaChoice(JavaCardinality.ONE, JAVA_86_0_0);
	public final static JavaRule JAVA_86 = new JavaRule(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getSuffixUnaryModificationExpression(), JAVA_86_0, JavaCardinality.ONE);
	public final static JavaContainment JAVA_87_0_0_0_0_0_0 = new JavaContainment(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getPrefixUnaryModificationExpression().getEStructuralFeature(org.emftext.language.java.expressions.ExpressionsPackage.PREFIX_UNARY_MODIFICATION_EXPRESSION__OPERATOR), JavaCardinality.ONE, new org.eclipse.emf.ecore.EClass[] {org.emftext.language.java.operators.OperatorsPackage.eINSTANCE.getUnaryModificationOperator(), }, 0);
	public final static JavaSequence JAVA_87_0_0_0_0_0 = new JavaSequence(JavaCardinality.ONE, JAVA_87_0_0_0_0_0_0);
	public final static JavaChoice JAVA_87_0_0_0_0 = new JavaChoice(JavaCardinality.ONE, JAVA_87_0_0_0_0_0);
	public final static JavaCompound JAVA_87_0_0_0 = new JavaCompound(JAVA_87_0_0_0_0, JavaCardinality.QUESTIONMARK);
	public final static JavaContainment JAVA_87_0_0_1 = new JavaContainment(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getPrefixUnaryModificationExpression().getEStructuralFeature(org.emftext.language.java.expressions.ExpressionsPackage.PREFIX_UNARY_MODIFICATION_EXPRESSION__CHILD), JavaCardinality.ONE, new org.eclipse.emf.ecore.EClass[] {org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getUnaryModificationExpressionChild(), }, 0);
	public final static JavaSequence JAVA_87_0_0 = new JavaSequence(JavaCardinality.ONE, JAVA_87_0_0_0, JAVA_87_0_0_1);
	public final static JavaChoice JAVA_87_0 = new JavaChoice(JavaCardinality.ONE, JAVA_87_0_0);
	public final static JavaRule JAVA_87 = new JavaRule(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getPrefixUnaryModificationExpression(), JAVA_87_0, JavaCardinality.ONE);
	public final static JavaKeyword JAVA_88_0_0_0 = new JavaKeyword("(", JavaCardinality.ONE);
	public final static JavaContainment JAVA_88_0_0_1 = new JavaContainment(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getCastExpression().getEStructuralFeature(org.emftext.language.java.expressions.ExpressionsPackage.CAST_EXPRESSION__TYPE_REFERENCE), JavaCardinality.ONE, new org.eclipse.emf.ecore.EClass[] {org.emftext.language.java.types.TypesPackage.eINSTANCE.getTypeReference(), }, 0);
	public final static JavaContainment JAVA_88_0_0_2 = new JavaContainment(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getCastExpression().getEStructuralFeature(org.emftext.language.java.expressions.ExpressionsPackage.CAST_EXPRESSION__ARRAY_DIMENSIONS_BEFORE), JavaCardinality.STAR, new org.eclipse.emf.ecore.EClass[] {org.emftext.language.java.arrays.ArraysPackage.eINSTANCE.getArrayDimension(), }, 0);
	public final static JavaKeyword JAVA_88_0_0_3 = new JavaKeyword(")", JavaCardinality.ONE);
	public final static JavaWhiteSpace JAVA_88_0_0_4 = new JavaWhiteSpace(1, JavaCardinality.ONE);
	public final static JavaContainment JAVA_88_0_0_5 = new JavaContainment(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getCastExpression().getEStructuralFeature(org.emftext.language.java.expressions.ExpressionsPackage.CAST_EXPRESSION__CHILD), JavaCardinality.ONE, new org.eclipse.emf.ecore.EClass[] {org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getUnaryExpression(), }, 0);
	public final static JavaSequence JAVA_88_0_0 = new JavaSequence(JavaCardinality.ONE, JAVA_88_0_0_0, JAVA_88_0_0_1, JAVA_88_0_0_2, JAVA_88_0_0_3, JAVA_88_0_0_4, JAVA_88_0_0_5);
	public final static JavaChoice JAVA_88_0 = new JavaChoice(JavaCardinality.ONE, JAVA_88_0_0);
	public final static JavaRule JAVA_88 = new JavaRule(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getCastExpression(), JAVA_88_0, JavaCardinality.ONE);
	public final static JavaKeyword JAVA_89_0_0_0 = new JavaKeyword("(", JavaCardinality.ONE);
	public final static JavaContainment JAVA_89_0_0_1 = new JavaContainment(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getNestedExpression().getEStructuralFeature(org.emftext.language.java.expressions.ExpressionsPackage.NESTED_EXPRESSION__EXPRESSION), JavaCardinality.ONE, new org.eclipse.emf.ecore.EClass[] {org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getAssignmentExpression(), }, 0);
	public final static JavaKeyword JAVA_89_0_0_2 = new JavaKeyword(")", JavaCardinality.ONE);
	public final static JavaContainment JAVA_89_0_0_3 = new JavaContainment(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getNestedExpression().getEStructuralFeature(org.emftext.language.java.expressions.ExpressionsPackage.NESTED_EXPRESSION__ARRAY_SELECTORS), JavaCardinality.STAR, new org.eclipse.emf.ecore.EClass[] {org.emftext.language.java.arrays.ArraysPackage.eINSTANCE.getArraySelector(), }, 0);
	public final static JavaKeyword JAVA_89_0_0_4_0_0_0 = new JavaKeyword(".", JavaCardinality.ONE);
	public final static JavaContainment JAVA_89_0_0_4_0_0_1 = new JavaContainment(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getNestedExpression().getEStructuralFeature(org.emftext.language.java.expressions.ExpressionsPackage.NESTED_EXPRESSION__NEXT), JavaCardinality.ONE, new org.eclipse.emf.ecore.EClass[] {org.emftext.language.java.references.ReferencesPackage.eINSTANCE.getReference(), }, 0);
	public final static JavaSequence JAVA_89_0_0_4_0_0 = new JavaSequence(JavaCardinality.ONE, JAVA_89_0_0_4_0_0_0, JAVA_89_0_0_4_0_0_1);
	public final static JavaChoice JAVA_89_0_0_4_0 = new JavaChoice(JavaCardinality.ONE, JAVA_89_0_0_4_0_0);
	public final static JavaCompound JAVA_89_0_0_4 = new JavaCompound(JAVA_89_0_0_4_0, JavaCardinality.QUESTIONMARK);
	public final static JavaSequence JAVA_89_0_0 = new JavaSequence(JavaCardinality.ONE, JAVA_89_0_0_0, JAVA_89_0_0_1, JAVA_89_0_0_2, JAVA_89_0_0_3, JAVA_89_0_0_4);
	public final static JavaChoice JAVA_89_0 = new JavaChoice(JavaCardinality.ONE, JAVA_89_0_0);
	public final static JavaRule JAVA_89 = new JavaRule(org.emftext.language.java.expressions.ExpressionsPackage.eINSTANCE.getNestedExpression(), JAVA_89_0, JavaCardinality.ONE);
	public final static JavaKeyword JAVA_90_0_0_0 = new JavaKeyword("=", JavaCardinality.ONE);
	public final static JavaSequence JAVA_90_0_0 = new JavaSequence(JavaCardinality.ONE, JAVA_90_0_0_0);
	public final static JavaChoice JAVA_90_0 = new JavaChoice(JavaCardinality.ONE, JAVA_90_0_0);
	public final static JavaRule JAVA_90 = new JavaRule(org.emftext.language.java.operators.OperatorsPackage.eINSTANCE.getAssignment(), JAVA_90_0, JavaCardinality.ONE);
	public final static JavaKeyword JAVA_91_0_0_0 = new JavaKeyword("+=", JavaCardinality.ONE);
	public final static JavaSequence JAVA_91_0_0 = new JavaSequence(JavaCardinality.ONE, JAVA_91_0_0_0);
	public final static JavaChoice JAVA_91_0 = new JavaChoice(JavaCardinality.ONE, JAVA_91_0_0);
	public final static JavaRule JAVA_91 = new JavaRule(org.emftext.language.java.operators.OperatorsPackage.eINSTANCE.getAssignmentPlus(), JAVA_91_0, JavaCardinality.ONE);
	public final static JavaKeyword JAVA_92_0_0_0 = new JavaKeyword("-=", JavaCardinality.ONE);
	public final static JavaSequence JAVA_92_0_0 = new JavaSequence(JavaCardinality.ONE, JAVA_92_0_0_0);
	public final static JavaChoice JAVA_92_0 = new JavaChoice(JavaCardinality.ONE, JAVA_92_0_0);
	public final static JavaRule JAVA_92 = new JavaRule(org.emftext.language.java.operators.OperatorsPackage.eINSTANCE.getAssignmentMinus(), JAVA_92_0, JavaCardinality.ONE);
	public final static JavaKeyword JAVA_93_0_0_0 = new JavaKeyword("*=", JavaCardinality.ONE);
	public final static JavaSequence JAVA_93_0_0 = new JavaSequence(JavaCardinality.ONE, JAVA_93_0_0_0);
	public final static JavaChoice JAVA_93_0 = new JavaChoice(JavaCardinality.ONE, JAVA_93_0_0);
	public final static JavaRule JAVA_93 = new JavaRule(org.emftext.language.java.operators.OperatorsPackage.eINSTANCE.getAssignmentMultiplication(), JAVA_93_0, JavaCardinality.ONE);
	public final static JavaKeyword JAVA_94_0_0_0 = new JavaKeyword("/=", JavaCardinality.ONE);
	public final static JavaSequence JAVA_94_0_0 = new JavaSequence(JavaCardinality.ONE, JAVA_94_0_0_0);
	public final static JavaChoice JAVA_94_0 = new JavaChoice(JavaCardinality.ONE, JAVA_94_0_0);
	public final static JavaRule JAVA_94 = new JavaRule(org.emftext.language.java.operators.OperatorsPackage.eINSTANCE.getAssignmentDivision(), JAVA_94_0, JavaCardinality.ONE);
	public final static JavaKeyword JAVA_95_0_0_0 = new JavaKeyword("&=", JavaCardinality.ONE);
	public final static JavaSequence JAVA_95_0_0 = new JavaSequence(JavaCardinality.ONE, JAVA_95_0_0_0);
	public final static JavaChoice JAVA_95_0 = new JavaChoice(JavaCardinality.ONE, JAVA_95_0_0);
	public final static JavaRule JAVA_95 = new JavaRule(org.emftext.language.java.operators.OperatorsPackage.eINSTANCE.getAssignmentAnd(), JAVA_95_0, JavaCardinality.ONE);
	public final static JavaKeyword JAVA_96_0_0_0 = new JavaKeyword("|=", JavaCardinality.ONE);
	public final static JavaSequence JAVA_96_0_0 = new JavaSequence(JavaCardinality.ONE, JAVA_96_0_0_0);
	public final static JavaChoice JAVA_96_0 = new JavaChoice(JavaCardinality.ONE, JAVA_96_0_0);
	public final static JavaRule JAVA_96 = new JavaRule(org.emftext.language.java.operators.OperatorsPackage.eINSTANCE.getAssignmentOr(), JAVA_96_0, JavaCardinality.ONE);
	public final static JavaKeyword JAVA_97_0_0_0 = new JavaKeyword("^=", JavaCardinality.ONE);
	public final static JavaSequence JAVA_97_0_0 = new JavaSequence(JavaCardinality.ONE, JAVA_97_0_0_0);
	public final static JavaChoice JAVA_97_0 = new JavaChoice(JavaCardinality.ONE, JAVA_97_0_0);
	public final static JavaRule JAVA_97 = new JavaRule(org.emftext.language.java.operators.OperatorsPackage.eINSTANCE.getAssignmentExclusiveOr(), JAVA_97_0, JavaCardinality.ONE);
	public final static JavaKeyword JAVA_98_0_0_0 = new JavaKeyword("%=", JavaCardinality.ONE);
	public final static JavaSequence JAVA_98_0_0 = new JavaSequence(JavaCardinality.ONE, JAVA_98_0_0_0);
	public final static JavaChoice JAVA_98_0 = new JavaChoice(JavaCardinality.ONE, JAVA_98_0_0);
	public final static JavaRule JAVA_98 = new JavaRule(org.emftext.language.java.operators.OperatorsPackage.eINSTANCE.getAssignmentModulo(), JAVA_98_0, JavaCardinality.ONE);
	public final static JavaKeyword JAVA_99_0_0_0 = new JavaKeyword("<", JavaCardinality.ONE);
	public final static JavaKeyword JAVA_99_0_0_1 = new JavaKeyword("<", JavaCardinality.ONE);
	public final static JavaKeyword JAVA_99_0_0_2 = new JavaKeyword("=", JavaCardinality.ONE);
	public final static JavaSequence JAVA_99_0_0 = new JavaSequence(JavaCardinality.ONE, JAVA_99_0_0_0, JAVA_99_0_0_1, JAVA_99_0_0_2);
	public final static JavaChoice JAVA_99_0 = new JavaChoice(JavaCardinality.ONE, JAVA_99_0_0);
	public final static JavaRule JAVA_99 = new JavaRule(org.emftext.language.java.operators.OperatorsPackage.eINSTANCE.getAssignmentLeftShift(), JAVA_99_0, JavaCardinality.ONE);
	public final static JavaKeyword JAVA_100_0_0_0 = new JavaKeyword(">", JavaCardinality.ONE);
	public final static JavaKeyword JAVA_100_0_0_1 = new JavaKeyword(">", JavaCardinality.ONE);
	public final static JavaKeyword JAVA_100_0_0_2 = new JavaKeyword("=", JavaCardinality.ONE);
	public final static JavaSequence JAVA_100_0_0 = new JavaSequence(JavaCardinality.ONE, JAVA_100_0_0_0, JAVA_100_0_0_1, JAVA_100_0_0_2);
	public final static JavaChoice JAVA_100_0 = new JavaChoice(JavaCardinality.ONE, JAVA_100_0_0);
	public final static JavaRule JAVA_100 = new JavaRule(org.emftext.language.java.operators.OperatorsPackage.eINSTANCE.getAssignmentRightShift(), JAVA_100_0, JavaCardinality.ONE);
	public final static JavaKeyword JAVA_101_0_0_0 = new JavaKeyword(">", JavaCardinality.ONE);
	public final static JavaKeyword JAVA_101_0_0_1 = new JavaKeyword(">", JavaCardinality.ONE);
	public final static JavaKeyword JAVA_101_0_0_2 = new JavaKeyword(">", JavaCardinality.ONE);
	public final static JavaKeyword JAVA_101_0_0_3 = new JavaKeyword("=", JavaCardinality.ONE);
	public final static JavaSequence JAVA_101_0_0 = new JavaSequence(JavaCardinality.ONE, JAVA_101_0_0_0, JAVA_101_0_0_1, JAVA_101_0_0_2, JAVA_101_0_0_3);
	public final static JavaChoice JAVA_101_0 = new JavaChoice(JavaCardinality.ONE, JAVA_101_0_0);
	public final static JavaRule JAVA_101 = new JavaRule(org.emftext.language.java.operators.OperatorsPackage.eINSTANCE.getAssignmentUnsignedRightShift(), JAVA_101_0, JavaCardinality.ONE);
	public final static JavaKeyword JAVA_102_0_0_0 = new JavaKeyword("+", JavaCardinality.ONE);
	public final static JavaSequence JAVA_102_0_0 = new JavaSequence(JavaCardinality.ONE, JAVA_102_0_0_0);
	public final static JavaChoice JAVA_102_0 = new JavaChoice(JavaCardinality.ONE, JAVA_102_0_0);
	public final static JavaRule JAVA_102 = new JavaRule(org.emftext.language.java.operators.OperatorsPackage.eINSTANCE.getAddition(), JAVA_102_0, JavaCardinality.ONE);
	public final static JavaKeyword JAVA_103_0_0_0 = new JavaKeyword("-", JavaCardinality.ONE);
	public final static JavaSequence JAVA_103_0_0 = new JavaSequence(JavaCardinality.ONE, JAVA_103_0_0_0);
	public final static JavaChoice JAVA_103_0 = new JavaChoice(JavaCardinality.ONE, JAVA_103_0_0);
	public final static JavaRule JAVA_103 = new JavaRule(org.emftext.language.java.operators.OperatorsPackage.eINSTANCE.getSubtraction(), JAVA_103_0, JavaCardinality.ONE);
	public final static JavaKeyword JAVA_104_0_0_0 = new JavaKeyword("*", JavaCardinality.ONE);
	public final static JavaSequence JAVA_104_0_0 = new JavaSequence(JavaCardinality.ONE, JAVA_104_0_0_0);
	public final static JavaChoice JAVA_104_0 = new JavaChoice(JavaCardinality.ONE, JAVA_104_0_0);
	public final static JavaRule JAVA_104 = new JavaRule(org.emftext.language.java.operators.OperatorsPackage.eINSTANCE.getMultiplication(), JAVA_104_0, JavaCardinality.ONE);
	public final static JavaKeyword JAVA_105_0_0_0 = new JavaKeyword("/", JavaCardinality.ONE);
	public final static JavaSequence JAVA_105_0_0 = new JavaSequence(JavaCardinality.ONE, JAVA_105_0_0_0);
	public final static JavaChoice JAVA_105_0 = new JavaChoice(JavaCardinality.ONE, JAVA_105_0_0);
	public final static JavaRule JAVA_105 = new JavaRule(org.emftext.language.java.operators.OperatorsPackage.eINSTANCE.getDivision(), JAVA_105_0, JavaCardinality.ONE);
	public final static JavaKeyword JAVA_106_0_0_0 = new JavaKeyword("%", JavaCardinality.ONE);
	public final static JavaSequence JAVA_106_0_0 = new JavaSequence(JavaCardinality.ONE, JAVA_106_0_0_0);
	public final static JavaChoice JAVA_106_0 = new JavaChoice(JavaCardinality.ONE, JAVA_106_0_0);
	public final static JavaRule JAVA_106 = new JavaRule(org.emftext.language.java.operators.OperatorsPackage.eINSTANCE.getRemainder(), JAVA_106_0, JavaCardinality.ONE);
	public final static JavaKeyword JAVA_107_0_0_0 = new JavaKeyword("<", JavaCardinality.ONE);
	public final static JavaSequence JAVA_107_0_0 = new JavaSequence(JavaCardinality.ONE, JAVA_107_0_0_0);
	public final static JavaChoice JAVA_107_0 = new JavaChoice(JavaCardinality.ONE, JAVA_107_0_0);
	public final static JavaRule JAVA_107 = new JavaRule(org.emftext.language.java.operators.OperatorsPackage.eINSTANCE.getLessThan(), JAVA_107_0, JavaCardinality.ONE);
	public final static JavaKeyword JAVA_108_0_0_0 = new JavaKeyword("<", JavaCardinality.ONE);
	public final static JavaKeyword JAVA_108_0_0_1 = new JavaKeyword("=", JavaCardinality.ONE);
	public final static JavaSequence JAVA_108_0_0 = new JavaSequence(JavaCardinality.ONE, JAVA_108_0_0_0, JAVA_108_0_0_1);
	public final static JavaChoice JAVA_108_0 = new JavaChoice(JavaCardinality.ONE, JAVA_108_0_0);
	public final static JavaRule JAVA_108 = new JavaRule(org.emftext.language.java.operators.OperatorsPackage.eINSTANCE.getLessThanOrEqual(), JAVA_108_0, JavaCardinality.ONE);
	public final static JavaKeyword JAVA_109_0_0_0 = new JavaKeyword(">", JavaCardinality.ONE);
	public final static JavaSequence JAVA_109_0_0 = new JavaSequence(JavaCardinality.ONE, JAVA_109_0_0_0);
	public final static JavaChoice JAVA_109_0 = new JavaChoice(JavaCardinality.ONE, JAVA_109_0_0);
	public final static JavaRule JAVA_109 = new JavaRule(org.emftext.language.java.operators.OperatorsPackage.eINSTANCE.getGreaterThan(), JAVA_109_0, JavaCardinality.ONE);
	public final static JavaKeyword JAVA_110_0_0_0 = new JavaKeyword(">", JavaCardinality.ONE);
	public final static JavaKeyword JAVA_110_0_0_1 = new JavaKeyword("=", JavaCardinality.ONE);
	public final static JavaSequence JAVA_110_0_0 = new JavaSequence(JavaCardinality.ONE, JAVA_110_0_0_0, JAVA_110_0_0_1);
	public final static JavaChoice JAVA_110_0 = new JavaChoice(JavaCardinality.ONE, JAVA_110_0_0);
	public final static JavaRule JAVA_110 = new JavaRule(org.emftext.language.java.operators.OperatorsPackage.eINSTANCE.getGreaterThanOrEqual(), JAVA_110_0, JavaCardinality.ONE);
	public final static JavaKeyword JAVA_111_0_0_0 = new JavaKeyword("<", JavaCardinality.ONE);
	public final static JavaKeyword JAVA_111_0_0_1 = new JavaKeyword("<", JavaCardinality.ONE);
	public final static JavaSequence JAVA_111_0_0 = new JavaSequence(JavaCardinality.ONE, JAVA_111_0_0_0, JAVA_111_0_0_1);
	public final static JavaChoice JAVA_111_0 = new JavaChoice(JavaCardinality.ONE, JAVA_111_0_0);
	public final static JavaRule JAVA_111 = new JavaRule(org.emftext.language.java.operators.OperatorsPackage.eINSTANCE.getLeftShift(), JAVA_111_0, JavaCardinality.ONE);
	public final static JavaKeyword JAVA_112_0_0_0 = new JavaKeyword(">", JavaCardinality.ONE);
	public final static JavaKeyword JAVA_112_0_0_1 = new JavaKeyword(">", JavaCardinality.ONE);
	public final static JavaSequence JAVA_112_0_0 = new JavaSequence(JavaCardinality.ONE, JAVA_112_0_0_0, JAVA_112_0_0_1);
	public final static JavaChoice JAVA_112_0 = new JavaChoice(JavaCardinality.ONE, JAVA_112_0_0);
	public final static JavaRule JAVA_112 = new JavaRule(org.emftext.language.java.operators.OperatorsPackage.eINSTANCE.getRightShift(), JAVA_112_0, JavaCardinality.ONE);
	public final static JavaKeyword JAVA_113_0_0_0 = new JavaKeyword(">", JavaCardinality.ONE);
	public final static JavaKeyword JAVA_113_0_0_1 = new JavaKeyword(">", JavaCardinality.ONE);
	public final static JavaKeyword JAVA_113_0_0_2 = new JavaKeyword(">", JavaCardinality.ONE);
	public final static JavaSequence JAVA_113_0_0 = new JavaSequence(JavaCardinality.ONE, JAVA_113_0_0_0, JAVA_113_0_0_1, JAVA_113_0_0_2);
	public final static JavaChoice JAVA_113_0 = new JavaChoice(JavaCardinality.ONE, JAVA_113_0_0);
	public final static JavaRule JAVA_113 = new JavaRule(org.emftext.language.java.operators.OperatorsPackage.eINSTANCE.getUnsignedRightShift(), JAVA_113_0, JavaCardinality.ONE);
	public final static JavaKeyword JAVA_114_0_0_0 = new JavaKeyword("==", JavaCardinality.ONE);
	public final static JavaSequence JAVA_114_0_0 = new JavaSequence(JavaCardinality.ONE, JAVA_114_0_0_0);
	public final static JavaChoice JAVA_114_0 = new JavaChoice(JavaCardinality.ONE, JAVA_114_0_0);
	public final static JavaRule JAVA_114 = new JavaRule(org.emftext.language.java.operators.OperatorsPackage.eINSTANCE.getEqual(), JAVA_114_0, JavaCardinality.ONE);
	public final static JavaKeyword JAVA_115_0_0_0 = new JavaKeyword("!=", JavaCardinality.ONE);
	public final static JavaSequence JAVA_115_0_0 = new JavaSequence(JavaCardinality.ONE, JAVA_115_0_0_0);
	public final static JavaChoice JAVA_115_0 = new JavaChoice(JavaCardinality.ONE, JAVA_115_0_0);
	public final static JavaRule JAVA_115 = new JavaRule(org.emftext.language.java.operators.OperatorsPackage.eINSTANCE.getNotEqual(), JAVA_115_0, JavaCardinality.ONE);
	public final static JavaKeyword JAVA_116_0_0_0 = new JavaKeyword("++", JavaCardinality.ONE);
	public final static JavaSequence JAVA_116_0_0 = new JavaSequence(JavaCardinality.ONE, JAVA_116_0_0_0);
	public final static JavaChoice JAVA_116_0 = new JavaChoice(JavaCardinality.ONE, JAVA_116_0_0);
	public final static JavaRule JAVA_116 = new JavaRule(org.emftext.language.java.operators.OperatorsPackage.eINSTANCE.getPlusPlus(), JAVA_116_0, JavaCardinality.ONE);
	public final static JavaKeyword JAVA_117_0_0_0 = new JavaKeyword("--", JavaCardinality.ONE);
	public final static JavaSequence JAVA_117_0_0 = new JavaSequence(JavaCardinality.ONE, JAVA_117_0_0_0);
	public final static JavaChoice JAVA_117_0 = new JavaChoice(JavaCardinality.ONE, JAVA_117_0_0);
	public final static JavaRule JAVA_117 = new JavaRule(org.emftext.language.java.operators.OperatorsPackage.eINSTANCE.getMinusMinus(), JAVA_117_0, JavaCardinality.ONE);
	public final static JavaKeyword JAVA_118_0_0_0 = new JavaKeyword("~", JavaCardinality.ONE);
	public final static JavaSequence JAVA_118_0_0 = new JavaSequence(JavaCardinality.ONE, JAVA_118_0_0_0);
	public final static JavaChoice JAVA_118_0 = new JavaChoice(JavaCardinality.ONE, JAVA_118_0_0);
	public final static JavaRule JAVA_118 = new JavaRule(org.emftext.language.java.operators.OperatorsPackage.eINSTANCE.getComplement(), JAVA_118_0, JavaCardinality.ONE);
	public final static JavaKeyword JAVA_119_0_0_0 = new JavaKeyword("!", JavaCardinality.ONE);
	public final static JavaSequence JAVA_119_0_0 = new JavaSequence(JavaCardinality.ONE, JAVA_119_0_0_0);
	public final static JavaChoice JAVA_119_0 = new JavaChoice(JavaCardinality.ONE, JAVA_119_0_0);
	public final static JavaRule JAVA_119 = new JavaRule(org.emftext.language.java.operators.OperatorsPackage.eINSTANCE.getNegate(), JAVA_119_0, JavaCardinality.ONE);
	public final static JavaKeyword JAVA_120_0_0_0_0_0_0 = new JavaKeyword("[", JavaCardinality.ONE);
	public final static JavaKeyword JAVA_120_0_0_0_0_0_1 = new JavaKeyword("]", JavaCardinality.ONE);
	public final static JavaSequence JAVA_120_0_0_0_0_0 = new JavaSequence(JavaCardinality.ONE, JAVA_120_0_0_0_0_0_0, JAVA_120_0_0_0_0_0_1);
	public final static JavaChoice JAVA_120_0_0_0_0 = new JavaChoice(JavaCardinality.ONE, JAVA_120_0_0_0_0_0);
	public final static JavaCompound JAVA_120_0_0_0 = new JavaCompound(JAVA_120_0_0_0_0, JavaCardinality.ONE);
	public final static JavaSequence JAVA_120_0_0 = new JavaSequence(JavaCardinality.ONE, JAVA_120_0_0_0);
	public final static JavaChoice JAVA_120_0 = new JavaChoice(JavaCardinality.ONE, JAVA_120_0_0);
	public final static JavaRule JAVA_120 = new JavaRule(org.emftext.language.java.arrays.ArraysPackage.eINSTANCE.getArrayDimension(), JAVA_120_0, JavaCardinality.ONE);
	public final static JavaKeyword JAVA_121_0_0_0 = new JavaKeyword("null", JavaCardinality.ONE);
	public final static JavaSequence JAVA_121_0_0 = new JavaSequence(JavaCardinality.ONE, JAVA_121_0_0_0);
	public final static JavaChoice JAVA_121_0 = new JavaChoice(JavaCardinality.ONE, JAVA_121_0_0);
	public final static JavaRule JAVA_121 = new JavaRule(org.emftext.language.java.literals.LiteralsPackage.eINSTANCE.getNullLiteral(), JAVA_121_0, JavaCardinality.ONE);
	public final static JavaKeyword JAVA_122_0_0_0 = new JavaKeyword("public", JavaCardinality.ONE);
	public final static JavaSequence JAVA_122_0_0 = new JavaSequence(JavaCardinality.ONE, JAVA_122_0_0_0);
	public final static JavaChoice JAVA_122_0 = new JavaChoice(JavaCardinality.ONE, JAVA_122_0_0);
	public final static JavaRule JAVA_122 = new JavaRule(org.emftext.language.java.modifiers.ModifiersPackage.eINSTANCE.getPublic(), JAVA_122_0, JavaCardinality.ONE);
	public final static JavaKeyword JAVA_123_0_0_0 = new JavaKeyword("abstract", JavaCardinality.ONE);
	public final static JavaSequence JAVA_123_0_0 = new JavaSequence(JavaCardinality.ONE, JAVA_123_0_0_0);
	public final static JavaChoice JAVA_123_0 = new JavaChoice(JavaCardinality.ONE, JAVA_123_0_0);
	public final static JavaRule JAVA_123 = new JavaRule(org.emftext.language.java.modifiers.ModifiersPackage.eINSTANCE.getAbstract(), JAVA_123_0, JavaCardinality.ONE);
	public final static JavaKeyword JAVA_124_0_0_0 = new JavaKeyword("protected", JavaCardinality.ONE);
	public final static JavaSequence JAVA_124_0_0 = new JavaSequence(JavaCardinality.ONE, JAVA_124_0_0_0);
	public final static JavaChoice JAVA_124_0 = new JavaChoice(JavaCardinality.ONE, JAVA_124_0_0);
	public final static JavaRule JAVA_124 = new JavaRule(org.emftext.language.java.modifiers.ModifiersPackage.eINSTANCE.getProtected(), JAVA_124_0, JavaCardinality.ONE);
	public final static JavaKeyword JAVA_125_0_0_0 = new JavaKeyword("private", JavaCardinality.ONE);
	public final static JavaSequence JAVA_125_0_0 = new JavaSequence(JavaCardinality.ONE, JAVA_125_0_0_0);
	public final static JavaChoice JAVA_125_0 = new JavaChoice(JavaCardinality.ONE, JAVA_125_0_0);
	public final static JavaRule JAVA_125 = new JavaRule(org.emftext.language.java.modifiers.ModifiersPackage.eINSTANCE.getPrivate(), JAVA_125_0, JavaCardinality.ONE);
	public final static JavaKeyword JAVA_126_0_0_0 = new JavaKeyword("final", JavaCardinality.ONE);
	public final static JavaSequence JAVA_126_0_0 = new JavaSequence(JavaCardinality.ONE, JAVA_126_0_0_0);
	public final static JavaChoice JAVA_126_0 = new JavaChoice(JavaCardinality.ONE, JAVA_126_0_0);
	public final static JavaRule JAVA_126 = new JavaRule(org.emftext.language.java.modifiers.ModifiersPackage.eINSTANCE.getFinal(), JAVA_126_0, JavaCardinality.ONE);
	public final static JavaKeyword JAVA_127_0_0_0 = new JavaKeyword("static", JavaCardinality.ONE);
	public final static JavaSequence JAVA_127_0_0 = new JavaSequence(JavaCardinality.ONE, JAVA_127_0_0_0);
	public final static JavaChoice JAVA_127_0 = new JavaChoice(JavaCardinality.ONE, JAVA_127_0_0);
	public final static JavaRule JAVA_127 = new JavaRule(org.emftext.language.java.modifiers.ModifiersPackage.eINSTANCE.getStatic(), JAVA_127_0, JavaCardinality.ONE);
	public final static JavaKeyword JAVA_128_0_0_0 = new JavaKeyword("native", JavaCardinality.ONE);
	public final static JavaSequence JAVA_128_0_0 = new JavaSequence(JavaCardinality.ONE, JAVA_128_0_0_0);
	public final static JavaChoice JAVA_128_0 = new JavaChoice(JavaCardinality.ONE, JAVA_128_0_0);
	public final static JavaRule JAVA_128 = new JavaRule(org.emftext.language.java.modifiers.ModifiersPackage.eINSTANCE.getNative(), JAVA_128_0, JavaCardinality.ONE);
	public final static JavaKeyword JAVA_129_0_0_0 = new JavaKeyword("synchronized", JavaCardinality.ONE);
	public final static JavaSequence JAVA_129_0_0 = new JavaSequence(JavaCardinality.ONE, JAVA_129_0_0_0);
	public final static JavaChoice JAVA_129_0 = new JavaChoice(JavaCardinality.ONE, JAVA_129_0_0);
	public final static JavaRule JAVA_129 = new JavaRule(org.emftext.language.java.modifiers.ModifiersPackage.eINSTANCE.getSynchronized(), JAVA_129_0, JavaCardinality.ONE);
	public final static JavaKeyword JAVA_130_0_0_0 = new JavaKeyword("transient", JavaCardinality.ONE);
	public final static JavaSequence JAVA_130_0_0 = new JavaSequence(JavaCardinality.ONE, JAVA_130_0_0_0);
	public final static JavaChoice JAVA_130_0 = new JavaChoice(JavaCardinality.ONE, JAVA_130_0_0);
	public final static JavaRule JAVA_130 = new JavaRule(org.emftext.language.java.modifiers.ModifiersPackage.eINSTANCE.getTransient(), JAVA_130_0, JavaCardinality.ONE);
	public final static JavaKeyword JAVA_131_0_0_0 = new JavaKeyword("volatile", JavaCardinality.ONE);
	public final static JavaSequence JAVA_131_0_0 = new JavaSequence(JavaCardinality.ONE, JAVA_131_0_0_0);
	public final static JavaChoice JAVA_131_0 = new JavaChoice(JavaCardinality.ONE, JAVA_131_0_0);
	public final static JavaRule JAVA_131 = new JavaRule(org.emftext.language.java.modifiers.ModifiersPackage.eINSTANCE.getVolatile(), JAVA_131_0, JavaCardinality.ONE);
	public final static JavaKeyword JAVA_132_0_0_0 = new JavaKeyword("strictfp", JavaCardinality.ONE);
	public final static JavaSequence JAVA_132_0_0 = new JavaSequence(JavaCardinality.ONE, JAVA_132_0_0_0);
	public final static JavaChoice JAVA_132_0 = new JavaChoice(JavaCardinality.ONE, JAVA_132_0_0);
	public final static JavaRule JAVA_132 = new JavaRule(org.emftext.language.java.modifiers.ModifiersPackage.eINSTANCE.getStrictfp(), JAVA_132_0, JavaCardinality.ONE);
	public final static JavaKeyword JAVA_133_0_0_0 = new JavaKeyword("void", JavaCardinality.ONE);
	public final static JavaSequence JAVA_133_0_0 = new JavaSequence(JavaCardinality.ONE, JAVA_133_0_0_0);
	public final static JavaChoice JAVA_133_0 = new JavaChoice(JavaCardinality.ONE, JAVA_133_0_0);
	public final static JavaRule JAVA_133 = new JavaRule(org.emftext.language.java.types.TypesPackage.eINSTANCE.getVoid(), JAVA_133_0, JavaCardinality.ONE);
	public final static JavaKeyword JAVA_134_0_0_0 = new JavaKeyword("boolean", JavaCardinality.ONE);
	public final static JavaSequence JAVA_134_0_0 = new JavaSequence(JavaCardinality.ONE, JAVA_134_0_0_0);
	public final static JavaChoice JAVA_134_0 = new JavaChoice(JavaCardinality.ONE, JAVA_134_0_0);
	public final static JavaRule JAVA_134 = new JavaRule(org.emftext.language.java.types.TypesPackage.eINSTANCE.getBoolean(), JAVA_134_0, JavaCardinality.ONE);
	public final static JavaKeyword JAVA_135_0_0_0 = new JavaKeyword("char", JavaCardinality.ONE);
	public final static JavaSequence JAVA_135_0_0 = new JavaSequence(JavaCardinality.ONE, JAVA_135_0_0_0);
	public final static JavaChoice JAVA_135_0 = new JavaChoice(JavaCardinality.ONE, JAVA_135_0_0);
	public final static JavaRule JAVA_135 = new JavaRule(org.emftext.language.java.types.TypesPackage.eINSTANCE.getChar(), JAVA_135_0, JavaCardinality.ONE);
	public final static JavaKeyword JAVA_136_0_0_0 = new JavaKeyword("byte", JavaCardinality.ONE);
	public final static JavaSequence JAVA_136_0_0 = new JavaSequence(JavaCardinality.ONE, JAVA_136_0_0_0);
	public final static JavaChoice JAVA_136_0 = new JavaChoice(JavaCardinality.ONE, JAVA_136_0_0);
	public final static JavaRule JAVA_136 = new JavaRule(org.emftext.language.java.types.TypesPackage.eINSTANCE.getByte(), JAVA_136_0, JavaCardinality.ONE);
	public final static JavaKeyword JAVA_137_0_0_0 = new JavaKeyword("short", JavaCardinality.ONE);
	public final static JavaSequence JAVA_137_0_0 = new JavaSequence(JavaCardinality.ONE, JAVA_137_0_0_0);
	public final static JavaChoice JAVA_137_0 = new JavaChoice(JavaCardinality.ONE, JAVA_137_0_0);
	public final static JavaRule JAVA_137 = new JavaRule(org.emftext.language.java.types.TypesPackage.eINSTANCE.getShort(), JAVA_137_0, JavaCardinality.ONE);
	public final static JavaKeyword JAVA_138_0_0_0 = new JavaKeyword("int", JavaCardinality.ONE);
	public final static JavaSequence JAVA_138_0_0 = new JavaSequence(JavaCardinality.ONE, JAVA_138_0_0_0);
	public final static JavaChoice JAVA_138_0 = new JavaChoice(JavaCardinality.ONE, JAVA_138_0_0);
	public final static JavaRule JAVA_138 = new JavaRule(org.emftext.language.java.types.TypesPackage.eINSTANCE.getInt(), JAVA_138_0, JavaCardinality.ONE);
	public final static JavaKeyword JAVA_139_0_0_0 = new JavaKeyword("long", JavaCardinality.ONE);
	public final static JavaSequence JAVA_139_0_0 = new JavaSequence(JavaCardinality.ONE, JAVA_139_0_0_0);
	public final static JavaChoice JAVA_139_0 = new JavaChoice(JavaCardinality.ONE, JAVA_139_0_0);
	public final static JavaRule JAVA_139 = new JavaRule(org.emftext.language.java.types.TypesPackage.eINSTANCE.getLong(), JAVA_139_0, JavaCardinality.ONE);
	public final static JavaKeyword JAVA_140_0_0_0 = new JavaKeyword("float", JavaCardinality.ONE);
	public final static JavaSequence JAVA_140_0_0 = new JavaSequence(JavaCardinality.ONE, JAVA_140_0_0_0);
	public final static JavaChoice JAVA_140_0 = new JavaChoice(JavaCardinality.ONE, JAVA_140_0_0);
	public final static JavaRule JAVA_140 = new JavaRule(org.emftext.language.java.types.TypesPackage.eINSTANCE.getFloat(), JAVA_140_0, JavaCardinality.ONE);
	public final static JavaKeyword JAVA_141_0_0_0 = new JavaKeyword("double", JavaCardinality.ONE);
	public final static JavaSequence JAVA_141_0_0 = new JavaSequence(JavaCardinality.ONE, JAVA_141_0_0_0);
	public final static JavaChoice JAVA_141_0 = new JavaChoice(JavaCardinality.ONE, JAVA_141_0_0);
	public final static JavaRule JAVA_141 = new JavaRule(org.emftext.language.java.types.TypesPackage.eINSTANCE.getDouble(), JAVA_141_0, JavaCardinality.ONE);
	public final static JavaPlaceholder JAVA_142_0_0_0 = new JavaPlaceholder(org.emftext.language.java.literals.LiteralsPackage.eINSTANCE.getDecimalLongLiteral().getEStructuralFeature(org.emftext.language.java.literals.LiteralsPackage.DECIMAL_LONG_LITERAL__DECIMAL_VALUE), "DECIMAL_LONG_LITERAL", JavaCardinality.ONE, 0);
	public final static JavaSequence JAVA_142_0_0 = new JavaSequence(JavaCardinality.ONE, JAVA_142_0_0_0);
	public final static JavaChoice JAVA_142_0 = new JavaChoice(JavaCardinality.ONE, JAVA_142_0_0);
	public final static JavaRule JAVA_142 = new JavaRule(org.emftext.language.java.literals.LiteralsPackage.eINSTANCE.getDecimalLongLiteral(), JAVA_142_0, JavaCardinality.ONE);
	public final static JavaPlaceholder JAVA_143_0_0_0 = new JavaPlaceholder(org.emftext.language.java.literals.LiteralsPackage.eINSTANCE.getDecimalFloatLiteral().getEStructuralFeature(org.emftext.language.java.literals.LiteralsPackage.DECIMAL_FLOAT_LITERAL__DECIMAL_VALUE), "DECIMAL_FLOAT_LITERAL", JavaCardinality.ONE, 0);
	public final static JavaSequence JAVA_143_0_0 = new JavaSequence(JavaCardinality.ONE, JAVA_143_0_0_0);
	public final static JavaChoice JAVA_143_0 = new JavaChoice(JavaCardinality.ONE, JAVA_143_0_0);
	public final static JavaRule JAVA_143 = new JavaRule(org.emftext.language.java.literals.LiteralsPackage.eINSTANCE.getDecimalFloatLiteral(), JAVA_143_0, JavaCardinality.ONE);
	public final static JavaPlaceholder JAVA_144_0_0_0 = new JavaPlaceholder(org.emftext.language.java.literals.LiteralsPackage.eINSTANCE.getDecimalIntegerLiteral().getEStructuralFeature(org.emftext.language.java.literals.LiteralsPackage.DECIMAL_INTEGER_LITERAL__DECIMAL_VALUE), "DECIMAL_INTEGER_LITERAL", JavaCardinality.ONE, 0);
	public final static JavaSequence JAVA_144_0_0 = new JavaSequence(JavaCardinality.ONE, JAVA_144_0_0_0);
	public final static JavaChoice JAVA_144_0 = new JavaChoice(JavaCardinality.ONE, JAVA_144_0_0);
	public final static JavaRule JAVA_144 = new JavaRule(org.emftext.language.java.literals.LiteralsPackage.eINSTANCE.getDecimalIntegerLiteral(), JAVA_144_0, JavaCardinality.ONE);
	public final static JavaPlaceholder JAVA_145_0_0_0 = new JavaPlaceholder(org.emftext.language.java.literals.LiteralsPackage.eINSTANCE.getDecimalDoubleLiteral().getEStructuralFeature(org.emftext.language.java.literals.LiteralsPackage.DECIMAL_DOUBLE_LITERAL__DECIMAL_VALUE), "DECIMAL_DOUBLE_LITERAL", JavaCardinality.ONE, 0);
	public final static JavaSequence JAVA_145_0_0 = new JavaSequence(JavaCardinality.ONE, JAVA_145_0_0_0);
	public final static JavaChoice JAVA_145_0 = new JavaChoice(JavaCardinality.ONE, JAVA_145_0_0);
	public final static JavaRule JAVA_145 = new JavaRule(org.emftext.language.java.literals.LiteralsPackage.eINSTANCE.getDecimalDoubleLiteral(), JAVA_145_0, JavaCardinality.ONE);
	public final static JavaPlaceholder JAVA_146_0_0_0 = new JavaPlaceholder(org.emftext.language.java.literals.LiteralsPackage.eINSTANCE.getHexLongLiteral().getEStructuralFeature(org.emftext.language.java.literals.LiteralsPackage.HEX_LONG_LITERAL__HEX_VALUE), "HEX_LONG_LITERAL", JavaCardinality.ONE, 0);
	public final static JavaSequence JAVA_146_0_0 = new JavaSequence(JavaCardinality.ONE, JAVA_146_0_0_0);
	public final static JavaChoice JAVA_146_0 = new JavaChoice(JavaCardinality.ONE, JAVA_146_0_0);
	public final static JavaRule JAVA_146 = new JavaRule(org.emftext.language.java.literals.LiteralsPackage.eINSTANCE.getHexLongLiteral(), JAVA_146_0, JavaCardinality.ONE);
	public final static JavaPlaceholder JAVA_147_0_0_0 = new JavaPlaceholder(org.emftext.language.java.literals.LiteralsPackage.eINSTANCE.getHexFloatLiteral().getEStructuralFeature(org.emftext.language.java.literals.LiteralsPackage.HEX_FLOAT_LITERAL__HEX_VALUE), "HEX_FLOAT_LITERAL", JavaCardinality.ONE, 0);
	public final static JavaSequence JAVA_147_0_0 = new JavaSequence(JavaCardinality.ONE, JAVA_147_0_0_0);
	public final static JavaChoice JAVA_147_0 = new JavaChoice(JavaCardinality.ONE, JAVA_147_0_0);
	public final static JavaRule JAVA_147 = new JavaRule(org.emftext.language.java.literals.LiteralsPackage.eINSTANCE.getHexFloatLiteral(), JAVA_147_0, JavaCardinality.ONE);
	public final static JavaPlaceholder JAVA_148_0_0_0 = new JavaPlaceholder(org.emftext.language.java.literals.LiteralsPackage.eINSTANCE.getHexDoubleLiteral().getEStructuralFeature(org.emftext.language.java.literals.LiteralsPackage.HEX_DOUBLE_LITERAL__HEX_VALUE), "HEX_DOUBLE_LITERAL", JavaCardinality.ONE, 0);
	public final static JavaSequence JAVA_148_0_0 = new JavaSequence(JavaCardinality.ONE, JAVA_148_0_0_0);
	public final static JavaChoice JAVA_148_0 = new JavaChoice(JavaCardinality.ONE, JAVA_148_0_0);
	public final static JavaRule JAVA_148 = new JavaRule(org.emftext.language.java.literals.LiteralsPackage.eINSTANCE.getHexDoubleLiteral(), JAVA_148_0, JavaCardinality.ONE);
	public final static JavaPlaceholder JAVA_149_0_0_0 = new JavaPlaceholder(org.emftext.language.java.literals.LiteralsPackage.eINSTANCE.getHexIntegerLiteral().getEStructuralFeature(org.emftext.language.java.literals.LiteralsPackage.HEX_INTEGER_LITERAL__HEX_VALUE), "HEX_INTEGER_LITERAL", JavaCardinality.ONE, 0);
	public final static JavaSequence JAVA_149_0_0 = new JavaSequence(JavaCardinality.ONE, JAVA_149_0_0_0);
	public final static JavaChoice JAVA_149_0 = new JavaChoice(JavaCardinality.ONE, JAVA_149_0_0);
	public final static JavaRule JAVA_149 = new JavaRule(org.emftext.language.java.literals.LiteralsPackage.eINSTANCE.getHexIntegerLiteral(), JAVA_149_0, JavaCardinality.ONE);
	public final static JavaPlaceholder JAVA_150_0_0_0 = new JavaPlaceholder(org.emftext.language.java.literals.LiteralsPackage.eINSTANCE.getOctalLongLiteral().getEStructuralFeature(org.emftext.language.java.literals.LiteralsPackage.OCTAL_LONG_LITERAL__OCTAL_VALUE), "OCTAL_LONG_LITERAL", JavaCardinality.ONE, 0);
	public final static JavaSequence JAVA_150_0_0 = new JavaSequence(JavaCardinality.ONE, JAVA_150_0_0_0);
	public final static JavaChoice JAVA_150_0 = new JavaChoice(JavaCardinality.ONE, JAVA_150_0_0);
	public final static JavaRule JAVA_150 = new JavaRule(org.emftext.language.java.literals.LiteralsPackage.eINSTANCE.getOctalLongLiteral(), JAVA_150_0, JavaCardinality.ONE);
	public final static JavaPlaceholder JAVA_151_0_0_0 = new JavaPlaceholder(org.emftext.language.java.literals.LiteralsPackage.eINSTANCE.getOctalIntegerLiteral().getEStructuralFeature(org.emftext.language.java.literals.LiteralsPackage.OCTAL_INTEGER_LITERAL__OCTAL_VALUE), "OCTAL_INTEGER_LITERAL", JavaCardinality.ONE, 0);
	public final static JavaSequence JAVA_151_0_0 = new JavaSequence(JavaCardinality.ONE, JAVA_151_0_0_0);
	public final static JavaChoice JAVA_151_0 = new JavaChoice(JavaCardinality.ONE, JAVA_151_0_0);
	public final static JavaRule JAVA_151 = new JavaRule(org.emftext.language.java.literals.LiteralsPackage.eINSTANCE.getOctalIntegerLiteral(), JAVA_151_0, JavaCardinality.ONE);
	public final static JavaPlaceholder JAVA_152_0_0_0 = new JavaPlaceholder(org.emftext.language.java.literals.LiteralsPackage.eINSTANCE.getCharacterLiteral().getEStructuralFeature(org.emftext.language.java.literals.LiteralsPackage.CHARACTER_LITERAL__VALUE), "CHARACTER_LITERAL", JavaCardinality.ONE, 0);
	public final static JavaSequence JAVA_152_0_0 = new JavaSequence(JavaCardinality.ONE, JAVA_152_0_0_0);
	public final static JavaChoice JAVA_152_0 = new JavaChoice(JavaCardinality.ONE, JAVA_152_0_0);
	public final static JavaRule JAVA_152 = new JavaRule(org.emftext.language.java.literals.LiteralsPackage.eINSTANCE.getCharacterLiteral(), JAVA_152_0, JavaCardinality.ONE);
	public final static JavaPlaceholder JAVA_153_0_0_0 = new JavaPlaceholder(org.emftext.language.java.literals.LiteralsPackage.eINSTANCE.getBooleanLiteral().getEStructuralFeature(org.emftext.language.java.literals.LiteralsPackage.BOOLEAN_LITERAL__VALUE), "BOOLEAN_LITERAL", JavaCardinality.ONE, 0);
	public final static JavaSequence JAVA_153_0_0 = new JavaSequence(JavaCardinality.ONE, JAVA_153_0_0_0);
	public final static JavaChoice JAVA_153_0 = new JavaChoice(JavaCardinality.ONE, JAVA_153_0_0);
	public final static JavaRule JAVA_153 = new JavaRule(org.emftext.language.java.literals.LiteralsPackage.eINSTANCE.getBooleanLiteral(), JAVA_153_0, JavaCardinality.ONE);


    /** A statically pre-loaded cache to speed up the syntax element id look up. */
    private static LinkedHashMap<JavaSyntaxElement, String> syntaxElementIDCache = new LinkedHashMap<JavaSyntaxElement, String>();

    /** A statically pre-loaded cache to speed up the syntax element look up. */
    private static LinkedHashMap<String, JavaSyntaxElement> syntaxElementCache = new LinkedHashMap<String, JavaSyntaxElement>();

    /** Preload the caches */
    static {
        syntaxElementIDCache.put(null, "<EOF>");
        for (Field field : JavaGrammarInformationProvider.class.getFields()) {
            try {
            Object fieldValue = field.get(null);
            syntaxElementIDCache.put((JavaSyntaxElement) fieldValue, field.getName());
            syntaxElementCache.put(field.getName(), (JavaSyntaxElement) fieldValue);
            } catch (Exception e) { }
        }
    }

	public static String getSyntaxElementID(JavaSyntaxElement syntaxElement) {
		return syntaxElementIDCache.get(syntaxElement);
	}

	public static JavaSyntaxElement getSyntaxElementByID(String syntaxElementID) {
		return syntaxElementCache.get(syntaxElementID);
	}

	public final static JavaRule[] RULES = new JavaRule[] {
		JAVA_0,
		JAVA_1,
		JAVA_2,
		JAVA_3,
		JAVA_4,
		JAVA_5,
		JAVA_6,
		JAVA_7,
		JAVA_8,
		JAVA_9,
		JAVA_10,
		JAVA_11,
		JAVA_12,
		JAVA_13,
		JAVA_14,
		JAVA_15,
		JAVA_16,
		JAVA_17,
		JAVA_18,
		JAVA_19,
		JAVA_20,
		JAVA_21,
		JAVA_22,
		JAVA_23,
		JAVA_24,
		JAVA_25,
		JAVA_26,
		JAVA_27,
		JAVA_28,
		JAVA_29,
		JAVA_30,
		JAVA_31,
		JAVA_32,
		JAVA_33,
		JAVA_34,
		JAVA_35,
		JAVA_36,
		JAVA_37,
		JAVA_38,
		JAVA_39,
		JAVA_40,
		JAVA_41,
		JAVA_42,
		JAVA_43,
		JAVA_44,
		JAVA_45,
		JAVA_46,
		JAVA_47,
		JAVA_48,
		JAVA_49,
		JAVA_50,
		JAVA_51,
		JAVA_52,
		JAVA_53,
		JAVA_54,
		JAVA_55,
		JAVA_56,
		JAVA_57,
		JAVA_58,
		JAVA_59,
		JAVA_60,
		JAVA_61,
		JAVA_62,
		JAVA_63,
		JAVA_64,
		JAVA_65,
		JAVA_66,
		JAVA_67,
		JAVA_68,
		JAVA_69,
		JAVA_70,
		JAVA_71,
		JAVA_72,
		JAVA_73,
		JAVA_74,
		JAVA_75,
		JAVA_76,
		JAVA_77,
		JAVA_78,
		JAVA_79,
		JAVA_80,
		JAVA_81,
		JAVA_82,
		JAVA_83,
		JAVA_84,
		JAVA_85,
		JAVA_86,
		JAVA_87,
		JAVA_88,
		JAVA_89,
		JAVA_90,
		JAVA_91,
		JAVA_92,
		JAVA_93,
		JAVA_94,
		JAVA_95,
		JAVA_96,
		JAVA_97,
		JAVA_98,
		JAVA_99,
		JAVA_100,
		JAVA_101,
		JAVA_102,
		JAVA_103,
		JAVA_104,
		JAVA_105,
		JAVA_106,
		JAVA_107,
		JAVA_108,
		JAVA_109,
		JAVA_110,
		JAVA_111,
		JAVA_112,
		JAVA_113,
		JAVA_114,
		JAVA_115,
		JAVA_116,
		JAVA_117,
		JAVA_118,
		JAVA_119,
		JAVA_120,
		JAVA_121,
		JAVA_122,
		JAVA_123,
		JAVA_124,
		JAVA_125,
		JAVA_126,
		JAVA_127,
		JAVA_128,
		JAVA_129,
		JAVA_130,
		JAVA_131,
		JAVA_132,
		JAVA_133,
		JAVA_134,
		JAVA_135,
		JAVA_136,
		JAVA_137,
		JAVA_138,
		JAVA_139,
		JAVA_140,
		JAVA_141,
		JAVA_142,
		JAVA_143,
		JAVA_144,
		JAVA_145,
		JAVA_146,
		JAVA_147,
		JAVA_148,
		JAVA_149,
		JAVA_150,
		JAVA_151,
		JAVA_152,
		JAVA_153,
	};

	/**
	 * Returns all keywords of the grammar. This includes all literals for boolean and
	 * enumeration terminals.
	 */
	public java.util.Set<String> getKeywords() {
		if (this.keywords == null) {
			this.keywords = new LinkedHashSet<String>();
			for (JavaRule rule : RULES) {
				findKeywords(rule, this.keywords);
			}
		}
		return keywords;
	}

	/**
	 * Finds all keywords in the given element and its children and adds them to the
	 * set. This includes all literals for boolean and enumeration terminals.
	 */
	private void findKeywords(JavaSyntaxElement element, Set<String> keywords) {
		if (element instanceof JavaKeyword) {
			keywords.add(((JavaKeyword) element).getValue());
		} else if (element instanceof JavaBooleanTerminal) {
			keywords.add(((JavaBooleanTerminal) element).getTrueLiteral());
			keywords.add(((JavaBooleanTerminal) element).getFalseLiteral());
		} else if (element instanceof JavaEnumerationTerminal) {
			JavaEnumerationTerminal terminal = (JavaEnumerationTerminal) element;
			for (String key : terminal.getLiteralMapping().keySet()) {
				keywords.add(key);
			}
		}
		for (JavaSyntaxElement child : element.getChildren()) {
			findKeywords(child, this.keywords);
		}
	}

}
