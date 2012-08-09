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
package org.emftext.language.java.test.bugs;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.emftext.language.java.classifiers.Class;
import org.emftext.language.java.classifiers.ClassifiersFactory;
import org.emftext.language.java.containers.CompilationUnit;
import org.emftext.language.java.containers.ContainersFactory;
import org.emftext.language.java.members.ClassMethod;
import org.emftext.language.java.members.MembersFactory;
import org.emftext.language.java.statements.Return;
import org.emftext.language.java.statements.StatementsFactory;
import org.emftext.language.java.types.TypesFactory;
import org.junit.Test;

public class Bug1541Test extends AbstractTestCase {

	private static final String LB = System.getProperty("line.separator");

	@Test
	public void testPrinting() throws IOException {
		CompilationUnit cu = ContainersFactory.eINSTANCE.createCompilationUnit();
		Class classA = ClassifiersFactory.eINSTANCE.createClass();
		cu.getClassifiers().add(classA);
		
		cu.getNamespaces().add("org");
		cu.getNamespaces().add("my");
		cu.getNamespaces().add("namespace1");
		classA.setName("ClassA");
		
		ClassMethod m1 = MembersFactory.eINSTANCE.createClassMethod();
		m1.setName("m1");
		m1.setTypeReference(TypesFactory.eINSTANCE.createVoid());
		m1.makePublic();
		classA.getMembers().add(m1);
		
		Return returnStatement = StatementsFactory.eINSTANCE.createReturn();
		m1.getStatements().add(returnStatement);
		
		Resource r = createResourceSet().createResource(URI.createURI("ClassA.java"));
		
		r.getContents().add(cu);
		
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		r.save(out, null);
		System.out.println("Test1541.testPrinting() " + out.toString());
		assertEquals(
			"package org.my.namespace1;" + LB +
			LB +
			LB +
			"class ClassA {" + LB +
			"\t" + "public void m1() {" + LB +
			"\t\t" + "return;" + LB +
			"\t" + "}" + LB + // the tab at the beginning of this line was missing
			"}" + LB + LB + LB + LB, 
			out.toString());
	}
}
