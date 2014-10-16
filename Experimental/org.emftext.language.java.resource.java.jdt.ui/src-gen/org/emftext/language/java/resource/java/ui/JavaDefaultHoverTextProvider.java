/**
 * <copyright>
 * </copyright>
 *
 * 
 */
package org.emftext.language.java.resource.java.ui;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;

public class JavaDefaultHoverTextProvider implements org.emftext.language.java.resource.java.IJavaHoverTextProvider {
	
	public String getHoverText(EObject container, EObject referencedObject) {
		return getHoverText(referencedObject);
	}
	
	public String getHoverText(EObject object) {
		if (object == null) {
			return null;
		}
		EClass eClass = object.eClass();
		String label = "<strong>" + eClass.getName() + "</strong>";
		String documentation = EcoreUtil.getDocumentation(eClass);
		String documentationHTML = documentation == null ? "" : " (" + documentation +")";
		label += documentationHTML;
		for (EAttribute attribute : eClass.getEAllAttributes()) {
			Object value = null;
			try {
				value = object.eGet(attribute);
			} catch (Exception e) {
				// Exception in eGet, do nothing
			}
			if (value != null && value.toString() != null && !value.toString().equals("[]")) {
				label += "<br />" + attribute.getName() + ": " + object.eGet(attribute).toString();
			}
		}
		return label;
	}
	
}
