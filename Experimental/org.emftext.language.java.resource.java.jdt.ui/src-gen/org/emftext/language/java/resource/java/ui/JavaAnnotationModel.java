/**
 * <copyright>
 * </copyright>
 *
 * 
 */
package org.emftext.language.java.resource.java.ui;

import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IResource;
import org.eclipse.ui.texteditor.MarkerAnnotation;
import org.eclipse.ui.texteditor.ResourceMarkerAnnotationModel;

public class JavaAnnotationModel extends ResourceMarkerAnnotationModel {
	
	public JavaAnnotationModel(IResource resource) {
		super(resource);
	}
	
	protected MarkerAnnotation createMarkerAnnotation(IMarker marker) {
		return new org.emftext.language.java.resource.java.ui.JavaMarkerAnnotation(marker);
	}
	
}
