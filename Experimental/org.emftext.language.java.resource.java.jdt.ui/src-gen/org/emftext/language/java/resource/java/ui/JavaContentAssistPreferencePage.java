/**
 * <copyright>
 * </copyright>
 *
 * 
 */
package org.emftext.language.java.resource.java.ui;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.preference.PreferencePage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;

/**
 * The preference page for the content assist settings.
 */
public class JavaContentAssistPreferencePage extends PreferencePage implements IWorkbenchPreferencePage {
	
	private Group grpAuto;
	private Button btnActivate;
	private Label lblAutoActivationDelay;
	private Text txtDelay;
	private Label lblAutoActivationTriggers;
	private Text txtTriggers;
	
	// preference values
	private boolean assistEnabled;
	private int activationDelay;
	private String activationTriggers = "";
	
	public JavaContentAssistPreferencePage() {
		initialize();
	}
	
	private void initialize() {
		setDescription("Define the behaviour of the content assist");
		setTitle("Content Assist");
	}
	
	public void init(IWorkbench workbench) {
		setPreferenceStore(org.emftext.language.java.resource.java.ui.JavaUIPlugin.getDefault().getPreferenceStore());
		IPreferenceStore preferenceStore = getPreferenceStore();
		// init values
		assistEnabled = preferenceStore.getBoolean(org.emftext.language.java.resource.java.ui.JavaPreferenceConstants.EDITOR_CONTENT_ASSIST_ENABLED);
		activationDelay = preferenceStore.getInt(org.emftext.language.java.resource.java.ui.JavaPreferenceConstants.EDITOR_CONTENT_ASSIST_DELAY);
		activationTriggers = preferenceStore.getString(org.emftext.language.java.resource.java.ui.JavaPreferenceConstants.EDITOR_CONTENT_ASSIST_TRIGGERS);
	}
	
	protected Control createContents(Composite parent) {
		// outer Composite
		Composite settingComposite = new Composite(parent, SWT.NONE);
		settingComposite.setLayout(new GridLayout(2, false));
		
		grpAuto = new Group(settingComposite, SWT.SHADOW_NONE);
		grpAuto.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		GridLayout layout = new GridLayout();
		layout.numColumns = 2;
		grpAuto.setLayout(layout);
		grpAuto.setText("Auto Activation");
		
		btnActivate = new Button(grpAuto, SWT.CHECK);
		btnActivate.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				updateAutoActivationWidgets();
			}
		});
		btnActivate.setText("Enable auto activation");
		btnActivate.setSelection(assistEnabled);
		btnActivate.setEnabled(true);
		new Label(grpAuto, SWT.NULL);
		
		lblAutoActivationDelay = new Label(grpAuto, SWT.NONE);
		lblAutoActivationDelay.setText("Auto activation delay (ms):");
		
		txtDelay = new Text(grpAuto, SWT.BORDER);
		GridData gd_txtDelay = new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1);
		gd_txtDelay.minimumWidth = 50;
		txtDelay.setLayoutData(gd_txtDelay);
		txtDelay.setText(String.valueOf(activationDelay));
		
		lblAutoActivationTriggers = new Label(grpAuto, SWT.NONE);
		lblAutoActivationTriggers.setText("Auto activation triggers:");
		
		txtTriggers = new Text(grpAuto, SWT.BORDER);
		txtTriggers.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		txtTriggers.setText(activationTriggers);
		
		updateAutoActivationWidgets();
		
		return settingComposite;
	}
	
	protected void performDefaults() {
		IPreferenceStore preferenceStore = getPreferenceStore();
		assistEnabled = preferenceStore.getDefaultBoolean(org.emftext.language.java.resource.java.ui.JavaPreferenceConstants.EDITOR_CONTENT_ASSIST_ENABLED);
		activationDelay = preferenceStore.getDefaultInt(org.emftext.language.java.resource.java.ui.JavaPreferenceConstants.EDITOR_CONTENT_ASSIST_DELAY);
		activationTriggers = preferenceStore.getDefaultString(org.emftext.language.java.resource.java.ui.JavaPreferenceConstants.EDITOR_CONTENT_ASSIST_TRIGGERS);
		// defaults in widgets
		btnActivate.setEnabled(assistEnabled);
		btnActivate.setSelection(assistEnabled);
		txtDelay.setEnabled(assistEnabled);
		txtDelay.setText(String.valueOf(activationDelay));
		txtTriggers.setEnabled(assistEnabled);
		txtTriggers.setText(activationTriggers);
	}
	
	public boolean performOk() {
		if (!super.performOk()) {
			return false;
		}
		return updatePreferences();
	}
	
	protected void performApply() {
		updatePreferences();
	}
	
	private boolean updatePreferences() {
		assistEnabled = btnActivate.getSelection();
		String delayText = txtDelay.getText();
		try {
			if(delayText != null && delayText.length() > 0){
				activationDelay = Integer.parseInt(delayText);
			}
		} catch (NumberFormatException exception) {
			org.emftext.language.java.resource.java.mopp.JavaPlugin.getDefault().getLog().log(			new Status(IStatus.ERROR			, org.emftext.language.java.resource.java.ui.JavaUIPlugin.getDefault().getBundle().getSymbolicName()			, "Value '" + delayText + "' is no valid delay value"			, exception)			);
			return false;
		}
		activationTriggers = txtTriggers.getText();
		IPreferenceStore preferenceStore = getPreferenceStore();
		preferenceStore.setValue(org.emftext.language.java.resource.java.ui.JavaPreferenceConstants.EDITOR_CONTENT_ASSIST_ENABLED, assistEnabled);
		preferenceStore.setValue(org.emftext.language.java.resource.java.ui.JavaPreferenceConstants.EDITOR_CONTENT_ASSIST_DELAY, activationDelay);
		preferenceStore.setValue(org.emftext.language.java.resource.java.ui.JavaPreferenceConstants.EDITOR_CONTENT_ASSIST_TRIGGERS, activationTriggers);
		return true;
	}
	
	private void updateAutoActivationWidgets() {
		boolean enabled = btnActivate.getSelection();
		lblAutoActivationDelay.setEnabled(enabled);
		lblAutoActivationTriggers.setEnabled(enabled);
		txtDelay.setEnabled(enabled);
		txtTriggers.setEnabled(enabled);
	}
	
}
