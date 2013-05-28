package org.emftext.language.java.test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedHashMap;
import java.util.Map;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.emftext.language.java.resource.JaMoPPUtil;
import org.emftext.language.java.resource.java.IJavaOptions;
import org.junit.Test;

public class PrintCommentsInEmptyMethodTest {

	@Test
	public void testPrinting() {
		JaMoPPUtil.initialize();
		ResourceSet rs = new ResourceSetImpl();
		Resource resource = rs.createResource(URI.createURI("C1.java"));
		assertNotNull(resource);
		InputStream inputStream = new ByteArrayInputStream("public class C1 {public void m1() {\n/*comment*/\n//another\n}}".getBytes());
		
		Map<Object, Object> options = new LinkedHashMap<Object, Object>();
		options.put(IJavaOptions.DISABLE_LAYOUT_INFORMATION_RECORDING, false);
		options.put(IJavaOptions.DISABLE_LOCATION_MAP, false);
		try {
			resource.load(inputStream, options);
		} catch (IOException e) {
			fail(e.getMessage());
		}
		
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		try {
			resource.save(outputStream, null);
		} catch (IOException e) {
			fail(e.getMessage());
		}
		String text = outputStream.toString();
		System.out.println("PrintCommentsInEmptyMethodTest.testPrinting() >>>" + text + "<<<");
		assertTrue(text.contains("comment"));
	}
}
