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
package org.emftext.language.java.test.locations;

import java.io.File;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.emftext.language.java.members.ClassMethod;
import org.emftext.language.java.members.Member;
import org.emftext.language.java.resource.java.IJavaLocationMap;
import org.emftext.language.java.resource.java.mopp.JavaResource;
import org.emftext.language.java.test.AbstractJavaParserTestCase;
import org.junit.Test;

/**
 * A test case that checks whether the generated parser creates
 * correct location information (line, position...) for the
 * elements in a TextResource.
 */
public class LocationTest extends AbstractJavaParserTestCase {

	private static final String INPUT_FOLDER = "src-input" + File.separator + "locations" + File.separator;

	@Override
	protected String getTestInputFolder() {
		return INPUT_FOLDER;
	}

	@Test
	public void testElementLocations() {
		String typename = "Location";
		try {
			org.emftext.language.java.classifiers.Class clazz = assertParsesToClass(typename);

			List<Member> members = clazz.getMembers();
			// check location of the method x
			ClassMethod x = (ClassMethod) members.get(1);
			assertElementLocation(x.getStatements().get(0), 21, 2, 820, 823);
			// check location of class
			assertElementLocation(clazz, 18, 0, 757, 831);
			// check location of the field 'm'
			assertElementLocation(members.get(0), 19, 1, 783, 795);
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

	private void assertElementLocation(EObject element, int line, int column, int charStart, int charEnd) {
		Resource resource = element.eResource();
		assertTrue(resource instanceof JavaResource);
		JavaResource textResource = (JavaResource) resource;
		IJavaLocationMap locationMap = textResource.getLocationMap();

		assertEquals(element.eClass().getName() + ": Wrong line", line, locationMap.getLine(element));
		assertEquals(element.eClass().getName() + ": Wrong column", column, locationMap.getColumn(element));
		assertEquals(element.eClass().getName() + ": Wrong start character", charStart, locationMap.getCharStart(element));
		assertEquals(element.eClass().getName() + ": Wrong end character", charEnd, locationMap.getCharEnd(element));
	}

	@Override
	protected boolean isExcludedFromReprintTest(String filename) {
		return true;
	}
}
