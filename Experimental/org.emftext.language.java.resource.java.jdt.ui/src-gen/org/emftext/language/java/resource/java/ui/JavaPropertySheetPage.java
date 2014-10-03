/**
 * <copyright>
 * </copyright>
 *
 * 
 */
package org.emftext.language.java.resource.java.ui;

import java.util.Iterator;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.views.properties.PropertySheetPage;

public class JavaPropertySheetPage extends PropertySheetPage implements ISelectionChangedListener {
	
	public void selectionChanged(SelectionChangedEvent event) {
		selectionChanged(null, event.getSelection());
	}
	
	public void selectionChanged(IWorkbenchPart part, ISelection iSelection) {
		// This is a workaround for a bug in EMF (see
		// https://bugs.eclipse.org/bugs/show_bug.cgi?id=291301).Unfortunately Ed Merks
		// refuses to fix it, so we need to solve it here.
		if (iSelection instanceof org.emftext.language.java.resource.java.ui.JavaEObjectSelection) {
			final org.emftext.language.java.resource.java.ui.JavaEObjectSelection selection = (org.emftext.language.java.resource.java.ui.JavaEObjectSelection) iSelection;
			final EObject selectedObject = selection.getSelectedObject();
			// check whether the selected object or one of its children contains a proxy which
			// is a GenXYZClass (e.g., GenFeature, GenClass, GenPackage)
			if (containsGenProxy(selectedObject)) {
				return;
			}
		}
		if (iSelection instanceof IStructuredSelection) {
			IStructuredSelection structuredSelection = (IStructuredSelection) iSelection;
			Iterator<?> it = structuredSelection.iterator();
			while (it.hasNext()) {
				final Object next = it.next();
				if (next instanceof EObject) {
					if (containsGenProxy((EObject) next)) {
						return;
					}
				}
			}
		}
		// end of workaround
		super.selectionChanged(part, iSelection);
	}
	
	private boolean containsGenProxy(EObject selectedObject) {
		boolean isGenProxy = isGenProxy(selectedObject);
		if (isGenProxy) {
			return true;
		}
		for (EObject child : selectedObject.eCrossReferences()) {
			if (isGenProxy(child)) {
				return true;
			}
		}
		for (EObject child : selectedObject.eContents()) {
			if (containsGenProxy(child)) {
				return true;
			}
		}
		return false;
	}
	
	private boolean isGenProxy(EObject selectedObject) {
		boolean isGenMetaclass = isInstanceOf("org.eclipse.emf.codegen.ecore.genmodel.GenClass", selectedObject);
		isGenMetaclass |= isInstanceOf("org.eclipse.emf.codegen.ecore.genmodel.GenFeature", selectedObject);
		isGenMetaclass |= isInstanceOf("org.eclipse.emf.codegen.ecore.genmodel.GenPackage", selectedObject);
		boolean isProxy = selectedObject.eIsProxy();
		return isGenMetaclass && isProxy;
	}
	
	private boolean isInstanceOf(String className, Object object) {
		try {
			Class<?> clazz = Class.forName(className);
			return clazz.isInstance(object);
		} catch (ClassNotFoundException e) {
			return false;
		}
	}
}
