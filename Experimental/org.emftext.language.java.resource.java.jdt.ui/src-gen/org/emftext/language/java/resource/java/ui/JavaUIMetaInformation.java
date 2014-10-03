/**
 * <copyright>
 * </copyright>
 *
 * 
 */
package org.emftext.language.java.resource.java.ui;

import org.eclipse.core.resources.IResource;

public class JavaUIMetaInformation extends org.emftext.language.java.resource.java.mopp.JavaMetaInformation {
	
	public org.emftext.language.java.resource.java.IJavaHoverTextProvider getHoverTextProvider() {
		return new org.emftext.language.java.resource.java.ui.JavaHoverTextProvider();
	}
	
	public org.emftext.language.java.resource.java.ui.JavaImageProvider getImageProvider() {
		return org.emftext.language.java.resource.java.ui.JavaImageProvider.INSTANCE;
	}
	
	public org.emftext.language.java.resource.java.ui.JavaColorManager createColorManager() {
		return new org.emftext.language.java.resource.java.ui.JavaColorManager();
	}
	
	/**
	 * @deprecated this method is only provided to preserve API compatibility. Use
	 * createTokenScanner(org.emftext.language.java.resource.java.IJavaTextResource,
	 * org.emftext.language.java.resource.java.ui.JavaColorManager) instead.
	 */
	public org.emftext.language.java.resource.java.ui.JavaTokenScanner createTokenScanner(org.emftext.language.java.resource.java.ui.JavaColorManager colorManager) {
		return (org.emftext.language.java.resource.java.ui.JavaTokenScanner) createTokenScanner(null, colorManager);
	}
	
	public org.emftext.language.java.resource.java.ui.IJavaTokenScanner createTokenScanner(org.emftext.language.java.resource.java.IJavaTextResource resource, org.emftext.language.java.resource.java.ui.JavaColorManager colorManager) {
		return new org.emftext.language.java.resource.java.ui.JavaTokenScanner(resource, colorManager);
	}
	
	public org.emftext.language.java.resource.java.ui.JavaCodeCompletionHelper createCodeCompletionHelper() {
		return new org.emftext.language.java.resource.java.ui.JavaCodeCompletionHelper();
	}
	
	@SuppressWarnings("rawtypes")
	public Object createResourceAdapter(Object adaptableObject, Class adapterType, IResource resource) {
		return new org.emftext.language.java.resource.java.ui.debug.JavaLineBreakpointAdapter();
	}
	
}
