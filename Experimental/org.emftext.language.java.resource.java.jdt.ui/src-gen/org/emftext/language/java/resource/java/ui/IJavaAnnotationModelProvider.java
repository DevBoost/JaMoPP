/**
 * <copyright>
 * </copyright>
 *
 * 
 */
package org.emftext.language.java.resource.java.ui;

import org.eclipse.jface.text.source.IAnnotationModel;

/**
 * A provider for annotation models.
 */
public interface IJavaAnnotationModelProvider {
	
	/**
	 * Returns the annotation model.
	 */
	public IAnnotationModel getAnnotationModel();
	
}
