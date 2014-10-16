/**
 * <copyright>
 * </copyright>
 *
 * 
 */
package org.emftext.language.java.resource.java.ui;

import org.eclipse.jface.action.IAction;

public class JavaOutlinePageTypeSortingAction extends org.emftext.language.java.resource.java.ui.AbstractJavaOutlinePageAction {
	
	public JavaOutlinePageTypeSortingAction(org.emftext.language.java.resource.java.ui.JavaOutlinePageTreeViewer treeViewer) {
		super(treeViewer, "Group types", IAction.AS_CHECK_BOX);
		initialize("icons/group_types_icon.gif");
	}
	
	public void runInternal(boolean on) {
		getTreeViewerComparator().setGroupTypes(on);
		getTreeViewer().refresh();
	}
	
}
