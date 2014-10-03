/**
 * <copyright>
 * </copyright>
 *
 * 
 */
package org.emftext.language.java.resource.java.ui;

import java.util.ArrayList;
import java.util.List;
import org.eclipse.jface.action.IAction;

public class JavaOutlinePageActionProvider {
	
	public List<IAction> getActions(org.emftext.language.java.resource.java.ui.JavaOutlinePageTreeViewer treeViewer) {
		// To add custom actions to the outline view, set the
		// 'overrideOutlinePageActionProvider' option to <code>false</code> and modify
		// this method.
		List<IAction> defaultActions = new ArrayList<IAction>();
		defaultActions.add(new org.emftext.language.java.resource.java.ui.JavaOutlinePageLinkWithEditorAction(treeViewer));
		defaultActions.add(new org.emftext.language.java.resource.java.ui.JavaOutlinePageCollapseAllAction(treeViewer));
		defaultActions.add(new org.emftext.language.java.resource.java.ui.JavaOutlinePageExpandAllAction(treeViewer));
		defaultActions.add(new org.emftext.language.java.resource.java.ui.JavaOutlinePageAutoExpandAction(treeViewer));
		defaultActions.add(new org.emftext.language.java.resource.java.ui.JavaOutlinePageLexicalSortingAction(treeViewer));
		defaultActions.add(new org.emftext.language.java.resource.java.ui.JavaOutlinePageTypeSortingAction(treeViewer));
		return defaultActions;
	}
	
}
