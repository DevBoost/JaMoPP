/**
 * <copyright>
 * </copyright>
 *
 * 
 */
package org.emftext.language.java.resource.java.ui;

import org.eclipse.jface.action.IAction;

public class JavaOutlinePageLexicalSortingAction extends org.emftext.language.java.resource.java.ui.AbstractJavaOutlinePageAction {
	
	public JavaOutlinePageLexicalSortingAction(org.emftext.language.java.resource.java.ui.JavaOutlinePageTreeViewer treeViewer) {
		super(treeViewer, "Sort alphabetically", IAction.AS_CHECK_BOX);
		initialize("icons/sort_lexically_icon.gif");
	}
	
	public void runInternal(boolean on) {
		getTreeViewerComparator().setSortLexically(on);
		getTreeViewer().refresh();
	}
	
}
