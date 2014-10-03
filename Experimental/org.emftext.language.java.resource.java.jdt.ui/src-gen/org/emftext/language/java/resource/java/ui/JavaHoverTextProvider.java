/**
 * <copyright>
 * </copyright>
 *
 * 
 */
package org.emftext.language.java.resource.java.ui;

import org.eclipse.emf.ecore.EObject;

public class JavaHoverTextProvider implements org.emftext.language.java.resource.java.IJavaHoverTextProvider {
	
	private org.emftext.language.java.resource.java.ui.JavaDefaultHoverTextProvider defaultProvider = new org.emftext.language.java.resource.java.ui.JavaDefaultHoverTextProvider();
	
	public String getHoverText(EObject container, EObject referencedObject) {
		// Set option overrideHoverTextProvider to false and customize this method to
		// obtain custom hover texts.
		return defaultProvider.getHoverText(referencedObject);
	}
	
	public String getHoverText(EObject object) {
		// Set option overrideHoverTextProvider to false and customize this method to
		// obtain custom hover texts.
		return defaultProvider.getHoverText(object);
	}
	
}
