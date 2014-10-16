/**
 * <copyright>
 * </copyright>
 *
 * 
 */
package org.emftext.language.java.resource.java.ui;

import org.eclipse.jface.action.IAction;

public class JavaOutlinePageAutoExpandAction extends org.emftext.language.java.resource.java.ui.AbstractJavaOutlinePageAction {
	
	public JavaOutlinePageAutoExpandAction(org.emftext.language.java.resource.java.ui.JavaOutlinePageTreeViewer treeViewer) {
		super(treeViewer, "Auto expand", IAction.AS_CHECK_BOX);
		initialize("icons/auto_expand_icon.gif");
	}
	
	public void runInternal(boolean on) {
		getTreeViewer().setAutoExpand(on);
		getTreeViewer().refresh();
	}
	
}
