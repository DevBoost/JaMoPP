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
package org.emftext.language.java.test.resolving;

import java.util.List;

import org.emftext.language.java.members.ClassMethod;
import org.emftext.language.java.members.Member;
import org.emftext.language.java.statements.Statement;
import org.junit.Test;

/**
 * A test for resolving method calls to the respective method.
 */
public class MethodCallsWithLocalTypeReferencesResolverTest extends AbstractResolverTestCase {

	@Test
	public void testReferencing() throws Exception {
		String typename = "MethodCallsWithLocalTypeReferences";
		org.emftext.language.java.classifiers.Class clazz = assertParsesToClass(typename);
		assertNotNull(clazz);
		assertMemberCount(clazz, 4);

		List<Member> members = clazz.getMembers();

		ClassMethod method1 = assertIsMethod(members.get(0), "m1");
		ClassMethod method2 = assertIsMethod(members.get(1), "m2");
		ClassMethod method3 = assertIsMethod(members.get(2), "m3");
		ClassMethod method4 = assertIsMethod(members.get(3), "m3");

		List<? extends Statement> methodStatements2 = method2.getStatements();

		assertEquals(4, methodStatements2.size());
		assertIsCallToMethod(methodStatements2.get(0), method1);
		assertIsCallToMethod(methodStatements2.get(1), method2);
		assertIsCallToMethod(methodStatements2.get(2), method3);
		// the last call should refer to m4, because of the signature
		assertIsCallToMethod(methodStatements2.get(3), method4);
	}
}
