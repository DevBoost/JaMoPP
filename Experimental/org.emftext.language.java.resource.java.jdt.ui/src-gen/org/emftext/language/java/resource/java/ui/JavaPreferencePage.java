/**
 * <copyright>
 * </copyright>
 *
 * 
 */
package org.emftext.language.java.resource.java.ui;

import org.eclipse.jface.preference.PreferencePage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.program.Program;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Link;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;

/**
 * The root preference page.
 */
public class JavaPreferencePage extends PreferencePage implements IWorkbenchPreferencePage {
	
	public void init(IWorkbench workbench) {
		setPreferenceStore(org.emftext.language.java.resource.java.ui.JavaUIPlugin.getDefault().getPreferenceStore());
		setDescription(org.emftext.language.java.resource.java.ui.JavaUIResourceBundle.ROOT_PREFERENCE_PAGE_DESCRIPTION);
	}
	
	@Override
	protected Control createContents(Composite parent) {
		Composite settingComposite = new Composite(parent, SWT.NONE);
		GridLayout layout = new GridLayout();
		GridData gd;
		layout.numColumns= 1;
		layout.marginHeight= 0;
		layout.marginWidth= 0;
		gd = new GridData(GridData.BEGINNING);
		settingComposite.setLayout(layout);
		settingComposite.setLayoutData(gd);
		
		Link link = new Link(settingComposite, SWT.NONE);
		link.setText(org.emftext.language.java.resource.java.ui.JavaUIResourceBundle.ROOT_PREFERENCE_PAGE_TEXT);
		link.setSize(140, 40);
		link.addSelectionListener(new SelectionListener() {
			public void widgetSelected(SelectionEvent e) {
				if (e.text.startsWith("http")) Program.launch(e.text);
			}
			public void widgetDefaultSelected(SelectionEvent e) {}
		});
		return settingComposite;
	}
	
}
