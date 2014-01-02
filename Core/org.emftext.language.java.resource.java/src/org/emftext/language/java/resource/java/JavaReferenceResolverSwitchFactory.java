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
 *   Benjamin Klatt - initial API and implementation
 ******************************************************************************/
package org.emftext.language.java.resource.java;

import org.emftext.language.java.resource.java.mopp.JavaReferenceResolverSwitch;

/**
 * Factory to receive a JavaReferenceResolverSwitch instances.
 *
 * Returns a singleton instance of the switch.
 */
public class JavaReferenceResolverSwitchFactory {

    /** The singleton instance. */
    private static JavaReferenceResolverSwitch resolverSwitch = new JavaReferenceResolverSwitch();

    /**
     * Get the switch instance to use.
     *
     * @return The global singleton.
     */
    public static JavaReferenceResolverSwitch getSwitch() {
        return resolverSwitch;
    }

}
