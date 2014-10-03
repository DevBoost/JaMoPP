/**
 * <copyright>
 * </copyright>
 *
 * 
 */
package org.emftext.language.java.resource.java.ui;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.Path;
import org.eclipse.emf.common.ui.viewer.IViewerProvider;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.edit.domain.IEditingDomainProvider;
import org.eclipse.jface.text.IRegion;
import org.eclipse.jface.text.hyperlink.IHyperlink;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.ui.IEditorDescriptor;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.part.FileEditorInput;

/**
 * A hyperlink for the proxy elements in source code.
 */
public class JavaHyperlink implements IHyperlink {
	
	private String text;
	private EObject linkTarget;
	private IRegion region;
	
	/**
	 * <p>
	 * Creates the hyperlink.
	 * </p>
	 * 
	 * @param region the region of the hyperlink to highlight
	 * @param linkTarget the link target where this hyperlink should go to
	 * @param targetText the text to specify the target position in the
	 * <code>linkTarget</code>
	 */
	public JavaHyperlink(IRegion region, EObject linkTarget, String targetText) {
		this.region = region;
		this.linkTarget = linkTarget;
		this.text = targetText;
	}
	
	public String getHyperlinkText() {
		return text;
	}
	
	/**
	 * 
	 * @return the length of the hyperlink text
	 */
	public int length() {
		return text.length();
	}
	
	public String getTypeLabel() {
		return null;
	}
	
	/**
	 * Opens the resource in <code>linkTarget</code> with the generated editor, if it
	 * supports the file extension of this resource, and tries to jump to the
	 * definition. Otherwise it tries to open the target with the default editor.
	 */
	public void open() {
		if (linkTarget == null) {
			return;
		}
		IFile file = getIFileFromResource();
		if (file != null) {
			IWorkbench workbench = PlatformUI.getWorkbench();
			IWorkbenchPage page = workbench.getActiveWorkbenchWindow().getActivePage();
			try {
				IEditorDescriptor desc = workbench.getEditorRegistry().getDefaultEditor(file.getName());
				if (desc == null) {
					desc = workbench.getEditorRegistry().findEditor("org.eclipse.emf.ecore.presentation.ReflectiveEditorID");
				}
				IEditorPart editorPart = page.openEditor(new FileEditorInput(file), desc.getId());
				if (editorPart instanceof IEditingDomainProvider) {
					IEditingDomainProvider editingDomainProvider = (IEditingDomainProvider) editorPart;
					EditingDomain editingDomain = editingDomainProvider.getEditingDomain();
					URI uri = EcoreUtil.getURI(linkTarget);
					EObject originalObject = editingDomain.getResourceSet().getEObject(uri, true);
					if (editingDomainProvider instanceof IViewerProvider) {
						IViewerProvider viewerProvider = (IViewerProvider) editingDomainProvider;
						Viewer viewer = viewerProvider.getViewer();
						viewer.setSelection(new StructuredSelection(originalObject), true);
					}
				}
			} catch (PartInitException e) {
				org.emftext.language.java.resource.java.mopp.JavaPlugin.logError("Exception while opening hyperlink target.", e);
			}
		}
	}
	
	private IFile getIFileFromResource() {
		Resource linkTargetResource = linkTarget.eResource();
		if (linkTargetResource == null) {
			return null;
		}
		URI resourceURI = linkTargetResource.getURI();
		if (linkTargetResource.getResourceSet() != null && linkTargetResource.getResourceSet().getURIConverter() != null) {
			resourceURI = linkTargetResource.getResourceSet().getURIConverter().normalize(resourceURI);
		}
		if (resourceURI.isPlatformResource()) {
			String platformString = resourceURI.toPlatformString(true);
			if (platformString != null) {
				IWorkspace workspace = ResourcesPlugin.getWorkspace();
				IWorkspaceRoot root = workspace.getRoot();
				return root.getFile(new Path(platformString));
			}
		}
		return null;
	}
	
	public IRegion getHyperlinkRegion() {
		return region;
	}
	
}
