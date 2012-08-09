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
package org.emftext.language.java.test;


import java.io.File;
import java.io.IOException;

import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.Plugin;
import org.osgi.framework.BundleContext;

/**
 * The activator class controls the plug-in life cycle
 */
public class JavaParserTestPlugin extends Plugin {

	// The plug-in ID
	public static final String PLUGIN_ID = "org.reuseware.emftextedit.language.java.test";

	// The shared instance
	private static JavaParserTestPlugin plugin;

	/**
	 * The constructor
	 */
	public JavaParserTestPlugin() {
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.core.runtime.Plugins#start(org.osgi.framework.BundleContext)
	 */
	public void start(BundleContext context) throws Exception {
		super.start(context);
		JavaParserTestPlugin.setPluginReference(this);
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.core.runtime.Plugin#stop(org.osgi.framework.BundleContext)
	 */
	public void stop(BundleContext context) throws Exception {
		JavaParserTestPlugin.setPluginReference(null);
		super.stop(context);
	}

	/**
	 * Returns the shared instance
	 *
	 * @return the shared instance
	 */
	public static JavaParserTestPlugin getDefault() {
		return plugin;
	}

	/**
	 * Returns the base directory of this plug-in
	 *
	 * @return the base directory of this plug-in
	 */
	public static String getBaseDirectory(){
		try {
			JavaParserTestPlugin plugin = JavaParserTestPlugin.getDefault();
			return new File(FileLocator.toFileURL(plugin.getBundle().getEntry("/")).getFile()).getParent();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Method mostly here to workaround FindBugs warning "[ST] Write to static
	 * field from instance method"
	 *
	 * @param pluginReference
	 */
	private static void setPluginReference(JavaParserTestPlugin pluginReference) {
		plugin = pluginReference;
	}
}
