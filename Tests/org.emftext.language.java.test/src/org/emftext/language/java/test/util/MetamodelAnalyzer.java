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
package org.emftext.language.java.test.util;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.impl.EcoreResourceFactoryImpl;

public class MetamodelAnalyzer {
	
	private static Map<String, List<EClass>> opToClassMap = 
			new LinkedHashMap<String, List<EClass>>();

	private static Set<String> allOps = new LinkedHashSet<String>();
	
	public static void main(String[] args) {
		Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().put(
				"ecore", new EcoreResourceFactoryImpl());
		URI fileURI = URI.createFileURI(
				"../org.emftext.language.java/metamodel/java.ecore");
		ResourceSet rs = new ResourceSetImpl();
		Resource r = rs.getResource(fileURI, true);
		EPackage root = (EPackage) r.getContents().get(0);
		
		for (Iterator<EObject> i = root.eAllContents(); i.hasNext();) {
			EObject next = i.next();
			if (next instanceof EOperation) {
				allOps.add(((EOperation) next).getName());
			}
		}
		
		for (EPackage subPackage : root.getESubpackages()) {
			for (EClassifier eClassifier : subPackage.getEClassifiers()) {
				if (eClassifier instanceof EClass) {
					simplifyEClass((EClass) eClassifier);
				}
			}
		}
	}

	private static void simplifyEClass(EClass eClass) {
		System.out.println("Symplifying " + toSimpleString(eClass));
		List<EClass> directSuperTypes = eClass.getESuperTypes();
		Set<EClass> indirectSuperTypes = new LinkedHashSet<EClass>();
		for (EClass directSuperType : directSuperTypes) {
			indirectSuperTypes.addAll(directSuperType.getEAllSuperTypes());
		}
		for (Iterator<EClass> i = directSuperTypes.iterator(); i.hasNext();) {
			EClass directSuperType = i.next();
			if (indirectSuperTypes.contains(directSuperType)) {
				i.remove();
				System.out.println("  * Removed " + toSimpleString(directSuperType));
			}
		}
		/*for (EClass directSuperType : directSuperTypes) {
			if ("Commentable".equals(directSuperType.getName())) {
				if (directSuperTypes.indexOf(directSuperType) != 0) {
					System.out.println("  * Commentable not first!");
				}
			}
			if ("NamedElement".equals(directSuperType.getName())) {
				if (directSuperTypes.indexOf(directSuperType) != 0) {
					System.out.println("  * NamedElement not first!");
				}
			}
			if ("Statement".equals(directSuperType.getName())) {
				if (directSuperTypes.indexOf(directSuperType) != 0) {
					System.out.println("  * Statement not first!");
				}
			}
		}*/
		
		main : for (String opName : allOps) {
			Set<EClass> impls = new LinkedHashSet<EClass>();
			for (EOperation op : eClass.getEOperations()) {
				if (op.getName().equals(opName)) {
					continue main;
				}
				
			}
			for (EClass superClass : eClass.getESuperTypes()) {
				EClass opInSuperclass = findOpInSuperclass(superClass, opName);
				if (opInSuperclass != null) {
					impls.add(opInSuperclass);
				}
			}
			if (impls.size() > 1) {
				System.out.println("  * Multiple implementation of " 
						+ toSimpleString(eClass) + "." + opName
						+ "(): " + impls.toString());
			}
		}
		
		for (EOperation op : eClass.getEOperations()) {
			String opName = op.getName();
			List<EClass> l = opToClassMap.get(opName);
			if (l == null) {
				l = new ArrayList<EClass>();
				opToClassMap.put(opName, l);
			}
			if (!opDeclaredInSuperclass(eClass, opName)) {
				if (!l.isEmpty()) {
					System.out.println(" * Operation already defined " + opName + "() " 
							+ toSimpleString(l.get(0)));
				}
				l.add(eClass);
			}
			
		}
	}

	private static EClass findOpInSuperclass(EClass eClass, String opName) {
		for (EOperation sop : eClass.getEOperations()) {
			if (sop.getName().equals(opName)) {
				return eClass;
			}
		}
		for (EClass superClass : eClass.getESuperTypes()) {
			return findOpInSuperclass(superClass, opName);
		}
		return null;
	}
	
	private static boolean opDeclaredInSuperclass(EClass eClass, String opName) {
		for (EClass superClass : eClass.getEAllSuperTypes()) {
			for (EOperation op : superClass.getEOperations()) {
				if (op.getName().equals(opName)) {
					return true;
				}
			}
		}
		return false;
	}

	private static String toSimpleString(EClass eClass) {
		return eClass.getEPackage().getName() + "." + eClass.getName();
	}

}
