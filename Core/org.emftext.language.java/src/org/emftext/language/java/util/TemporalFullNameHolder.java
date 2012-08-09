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
package org.emftext.language.java.util;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.impl.AdapterImpl;
import org.emftext.language.java.classifiers.ConcreteClassifier;


/**
 * This adapter is used during reference resolving to cache the
 * full qualified name of a {@link ConcreteClassifier} that 
 * is determined based on the classpath during reference resolving.
 */
public class TemporalFullNameHolder extends AdapterImpl {
	private String fullName;

	private TemporalFullNameHolder(String fullName) {
		this.fullName = fullName;
	}
	
	public static String getFullName(ConcreteClassifier concreteClassifier) {
		for (Adapter a : concreteClassifier.eAdapters()) {
			if (a instanceof TemporalFullNameHolder) {
				return ((TemporalFullNameHolder) a).fullName;
			}
		}
		return concreteClassifier.getName();
	}
	
	public static void setFullName(
			ConcreteClassifier concreteClassifier, 
			String fullName) {
		for (Adapter a : concreteClassifier.eAdapters()) {
			if (a instanceof TemporalFullNameHolder) {
				((TemporalFullNameHolder) a).fullName = fullName;
				return;
			}
		}
		concreteClassifier.eAdapters().add(
				new TemporalFullNameHolder(fullName));
	}
}
