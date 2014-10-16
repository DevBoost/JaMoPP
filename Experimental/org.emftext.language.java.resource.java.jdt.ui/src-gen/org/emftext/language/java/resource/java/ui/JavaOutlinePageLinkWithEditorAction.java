/**
 * <copyright>
 * </copyright>
 *
 * 
 */
package org.emftext.language.java.resource.java.ui;

import org.eclipse.jface.action.IAction;

public class JavaOutlinePageLinkWithEditorAction extends org.emftext.language.java.resource.java.ui.AbstractJavaOutlinePageAction {
	
	public JavaOutlinePageLinkWithEditorAction(org.emftext.language.java.resource.java.ui.JavaOutlinePageTreeViewer treeViewer) {
		super(treeViewer, "Link with Editor", IAction.AS_CHECK_BOX);
		initialize("icons/link_with_editor_icon.gif");
	}
	
	public void runInternal(boolean on) {
		getTreeViewer().setLinkWithEditor(on);
	}
	
}
