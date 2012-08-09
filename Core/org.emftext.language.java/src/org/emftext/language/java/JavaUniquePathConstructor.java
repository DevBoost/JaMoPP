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
package org.emftext.language.java;

import java.util.regex.Pattern;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.emftext.language.java.commons.NamespaceAwareElement;

/**
 * This provides functionality to construct unique identifier
 * (i.e., logical URI strings) for Java classes represented as EMF-models.
 */
public class JavaUniquePathConstructor {

	/**
	 * Pathmap (URI scheme + first segment) for Java classes.
	 */
	public static final String JAVA_CLASSIFIER_PATHMAP = "pathmap:/javaclass/";

	/**
	 * Pathmap (URI scheme + first segment) for Java classes.
	 */
	public static final String JAVA_PACKAGE_PATHMAP    = "pathmap:/javapackage/";

	/**
	 * Start of a URI fragment part pointing at a classifier contained in a
	 * compilation unit.
	 */
	public static final String CLASSIFIERS_ROOT_PATH_PREFIX = "@classifiers[name='";

	/**
	 * Start of a URI fragment part pointing at a classifier contained as member in
	 * another classifier.
	 */
	public static final String CLASSIFIERS_SUB_PATH_PREFIX  = "@members[name='";

	/**
	 * End of a URI fragment part.
	 */
	public static final String CLASSIFIERS_PATH_SUFIX       = "']";

	/**
	 * Java's separator for package names (.).
	 */
	public static final String PACKAGE_SEPARATOR    = ".";

	/**
	 * Java's separator for classifier names ($).
	 */
	public static final String CLASSIFIER_SEPARATOR = "$";

	/**
	 * Java's file extension (.java).
	 */
	public static final String JAVA_FILE_EXTENSION = ".java";

	/**
	 * Java's class file extension (.class).
	 */
	public static final String JAVA_CLASS_FILE_EXTENSION = ".class";
	
	/**
	 * We cache this regular expression because it is used very frequently.
	 */
	private static final Pattern CLASSIFIER_SEPARATOR_REGEX_PATTERN = Pattern.compile("\\" + CLASSIFIER_SEPARATOR);

	/**
	 * Constructs an URI from a fully qualified classifier name
	 * pointing at the resource containing the classifier.
	 *
	 * @param fullQualifiedName
	 * @return the logical URI for the classifier
	 */
	public static URI getJavaFileResourceURI(String fullQualifiedName) {
		StringBuilder logicalUriString = new StringBuilder(JAVA_CLASSIFIER_PATHMAP);
		logicalUriString.append(fullQualifiedName);
		logicalUriString.append(JAVA_FILE_EXTENSION);

		return URI.createURI(logicalUriString.toString());
	}

	/**
	 * Constructs an URI from a fully qualified classifier name
	 * pointing at the file containing the classifier and the classifier
	 * itself inside the EMF-model constructed from that resource.
	 *
	 * @param fullQualifiedName
	 * @return the logical URI for the classifier
	 */
	public static URI getClassifierURI(String fullQualifiedName) {
		URI logicalUri = getJavaFileResourceURI(fullQualifiedName);

		String classesPart = fullQualifiedName;
		int idx = fullQualifiedName.lastIndexOf(PACKAGE_SEPARATOR);
		if (idx >= 0) {
			classesPart = classesPart.substring(idx + 1);
		}

		String[] classNames = CLASSIFIER_SEPARATOR_REGEX_PATTERN.split(classesPart, -1);

		StringBuilder uriFragment = new StringBuilder();
		for(int i = 0; i < classNames.length; i++) {
			if (i == 0) {
				uriFragment.append("//");
				uriFragment.append(CLASSIFIERS_ROOT_PATH_PREFIX);
			}
			else {
				uriFragment.append("/");
				uriFragment.append(CLASSIFIERS_SUB_PATH_PREFIX);
			}
			uriFragment.append(classNames[i]);
			uriFragment.append(CLASSIFIERS_PATH_SUFIX);
		}
		logicalUri = logicalUri.appendFragment(uriFragment.toString());
		return logicalUri;
	}

	/**
	 * Returns a simple name (i.e., last segment) for a given fully
	 * qualified name
	 *
	 * @param fullQualifiedName
	 * @return simple name string
	 */
	public static String getSimpleClassName(String fullQualifiedName) {
		int idx1 = fullQualifiedName.lastIndexOf(PACKAGE_SEPARATOR);
		int idx2 = fullQualifiedName.lastIndexOf(CLASSIFIER_SEPARATOR);
		if (idx1 == -1 && idx2 == -1) {
			return fullQualifiedName;
		}
		if (idx1 > idx2) {
			return fullQualifiedName.substring(idx1 + 1);
		}
		else {
			return fullQualifiedName.substring(idx2 + 1);
		}
	}

	/**
	 * Constructs a single string representation of the given element's
	 * namespace, assuming that the namespace points at a package
	 * (and not a classifier).
	 *
	 * @param nsaElement
	 * @return
	 */
	public static String packageName(NamespaceAwareElement nsaElement) {
		EList<String> packageNameSegements = nsaElement.getNamespaces();
		String packageName = packageName(packageNameSegements);

		if (packageName == null) {
			packageName = "";
		}
		return packageName;
	}

	private static String packageName(EList<String> packageNameSegements) {
		String packageName = null;
		for(String packageNamePart : packageNameSegements) {
			if (packageName == null) {
				packageName = packageNamePart;
			}
			else {
				packageName = packageName + PACKAGE_SEPARATOR + packageNamePart;
			}
		}
		return packageName;
	}
}
