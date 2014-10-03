/**
 * <copyright>
 * </copyright>
 *
 * 
 */
package org.emftext.language.java.resource.java.ui;

import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import org.eclipse.jface.preference.ColorSelector;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.preference.PreferenceConverter;
import org.eclipse.jface.preference.PreferencePage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.List;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;

/**
 * <p>
 * The preference page for the bracket setting with following features:
 * </p>
 * <p>
 * <ul>
 * </p>
 * <p>
 * <li>enables bracket matching</li>
 * </p>
 * <p>
 * <li>chooses matching highlight color</li>
 * </p>
 * <p>
 * <li>customizes bracket set</li>
 * </p>
 * <p>
 * </ul>
 * </p>
 */
public class JavaBracketPreferencePage extends PreferencePage implements IWorkbenchPreferencePage {
	
	private static final String[] ALL_LEFT_BRACKETS = new String[] { "{", "(", "[", "<", "\"", "'", };
	private static final String[] ALL_RIGHT_BRACKETS = new String[] { "}", ")", "]", ">", "\"", "'", };
	
	private String BRACKETS_COLOR = org.emftext.language.java.resource.java.ui.JavaPreferenceConstants.EDITOR_MATCHING_BRACKETS_COLOR;
	
	private Set<String> languageIDs = new LinkedHashSet<String>();
	
	private ColorSelector matchingBracketsColorEditor;
	private Label colorEditorLabel;
	private Button enableCheckbox;
	private Button enableClosingInside;
	private Button enableCloseAfterEnter;
	private Button matchingBracketsColorButton;
	private Label bracketTokensLabel;
	private Combo leftBracketTokensCombo;
	private Combo rightBracketTokensCombo;
	private List bracketsList;
	private Button addBracketButton;
	private Button removeBracketButton;
	private Map<String, String> bracketSetTemp = new LinkedHashMap<String, String>();
	private String language = new org.emftext.language.java.resource.java.mopp.JavaMetaInformation().getSyntaxName();
	
	private org.emftext.language.java.resource.java.ui.JavaBracketSet bracketsTmp;
	
	/**
	 * Creates a preference page for bracket setting.
	 */
	public JavaBracketPreferencePage() {
		super();
		
		org.emftext.language.java.resource.java.IJavaMetaInformation metaInformation = new org.emftext.language.java.resource.java.mopp.JavaMetaInformation();
		String languageId = metaInformation.getSyntaxName();
		languageIDs.add(languageId);
	}
	
	/**
	 * 
	 * @see IWorkbenchPreferencePage#init(IWorkbench)
	 */
	public void init(IWorkbench workbench) {
		setPreferenceStore(org.emftext.language.java.resource.java.ui.JavaUIPlugin.getDefault().getPreferenceStore());
		setDescription("Define the coloring of matching brackets.");
		
		bracketsTmp = new org.emftext.language.java.resource.java.ui.JavaBracketSet();
		for (String languageID : languageIDs) {
			bracketSetTemp.put(languageID, getPreferenceStore().getString(languageID + org.emftext.language.java.resource.java.ui.JavaPreferenceConstants.EDITOR_BRACKETS_SUFFIX));
		}
	}
	
	@Override
	protected Control createContents(Composite parent) {
		
		// outer Composite
		Composite settingComposite = new Composite(parent, SWT.NONE);
		GridLayout layout = new GridLayout();
		GridData gd;
		layout.numColumns = 2;
		layout.marginHeight = 0;
		layout.marginWidth = 0;
		gd = new GridData(GridData.BEGINNING);
		settingComposite.setLayout(layout);
		settingComposite.setLayoutData(gd);
		
		enableCheckbox = new Button(settingComposite, SWT.CHECK);
		enableCheckbox.setText("Enable");
		gd = new GridData(GridData.BEGINNING);
		gd.horizontalAlignment = GridData.BEGINNING;
		gd.horizontalSpan = 2;
		enableCheckbox.setLayoutData(gd);
		
		colorEditorLabel = new Label(settingComposite, SWT.LEFT);
		colorEditorLabel.setText("Color:");
		gd = new GridData(GridData.HORIZONTAL_ALIGN_BEGINNING);
		gd.horizontalIndent = 20;
		colorEditorLabel.setLayoutData(gd);
		
		matchingBracketsColorEditor = new ColorSelector(settingComposite);
		matchingBracketsColorButton = matchingBracketsColorEditor.getButton();
		gd = new GridData(GridData.HORIZONTAL_ALIGN_BEGINNING);
		matchingBracketsColorButton.setLayoutData(gd);
		
		Composite tokenSelectionComposite = new Composite(settingComposite, SWT.NONE);
		layout = new GridLayout();
		layout.numColumns = 3;
		layout.marginHeight = 0;
		layout.marginWidth = 0;
		gd = new GridData(GridData.FILL_HORIZONTAL);
		gd.horizontalSpan = 2;
		gd.verticalIndent = 20;
		tokenSelectionComposite.setLayout(layout);
		tokenSelectionComposite.setLayoutData(gd);
		
		bracketTokensLabel = new Label(tokenSelectionComposite, SWT.LEFT);
		gd = new GridData(GridData.BEGINNING);
		gd.horizontalSpan = 3;
		bracketTokensLabel.setText("Add new bracket pair");
		bracketTokensLabel.setLayoutData(gd);
		
		leftBracketTokensCombo = new Combo(tokenSelectionComposite,SWT.DROP_DOWN | SWT.READ_ONLY);
		gd = new GridData(GridData.BEGINNING);
		leftBracketTokensCombo.setLayoutData(gd);
		
		rightBracketTokensCombo = new Combo(tokenSelectionComposite,SWT.DROP_DOWN | SWT.READ_ONLY);
		gd = new GridData(GridData.FILL);
		rightBracketTokensCombo.setLayoutData(gd);
		
		addBracketButton = new Button(tokenSelectionComposite, SWT.PUSH);
		addBracketButton.setText("Add");
		addBracketButton.setLayoutData(new GridData(GridData.BEGINNING, GridData.BEGINNING, false, false));
		
		Label configurePairsLabel = new Label(tokenSelectionComposite, SWT.LEFT);
		gd = new GridData(GridData.BEGINNING);
		gd.horizontalSpan = 3;
		gd.verticalIndent = 20;
		configurePairsLabel.setText("Configure bracket pairs");
		configurePairsLabel.setLayoutData(gd);
		bracketsList = new List(tokenSelectionComposite, SWT.SINGLE | SWT.BORDER);
		gd = new GridData(GridData.CENTER, GridData.FILL, false, true);
		gd.horizontalSpan = 2;
		gd.verticalSpan = 4;
		gd.widthHint = 100;
		gd.heightHint = 300;
		bracketsList.setLayoutData(gd);
		
		enableClosingInside = new Button(tokenSelectionComposite, SWT.CHECK);
		enableClosingInside.setText("Enable closing inside");
		enableClosingInside.setToolTipText("If this option is enabled, other bracket pair can close inside this pair automatically.");
		enableClosingInside.setLayoutData(new GridData(GridData.BEGINNING, GridData.BEGINNING, false, false));
		enableClosingInside.setEnabled(false);
		
		enableCloseAfterEnter = new Button(tokenSelectionComposite, SWT.CHECK);
		enableCloseAfterEnter.setText("Enable close after enter");
		enableCloseAfterEnter.setToolTipText("If this option is enabled the closing bracket is only inserted when the enter key is pressed.");
		enableCloseAfterEnter.setLayoutData(new GridData(GridData.BEGINNING, GridData.BEGINNING, false, false));
		enableCloseAfterEnter.setEnabled(false);
		
		removeBracketButton = new Button(tokenSelectionComposite, SWT.PUSH);
		removeBracketButton.setText("Remove");
		removeBracketButton.setLayoutData(new GridData(GridData.BEGINNING, GridData.BEGINNING, false, false));
		
		addListenersToStyleButtons();
		
		settingComposite.layout(false);
		handleMatchingBracketsSelection();
		return settingComposite;
	}
	
	/**
	 * Initialize and handle the values of this preference page.
	 */
	private void handleMatchingBracketsSelection() {
		// not for the case of none existing language
		enableCheckbox.setSelection(getPreferenceStore().getBoolean(org.emftext.language.java.resource.java.ui.JavaPreferenceConstants.EDITOR_MATCHING_BRACKETS_CHECKBOX));
		enableClosingInside.setSelection(false);
		matchingBracketsColorButton.setEnabled(getPreferenceStore().getBoolean(		org.emftext.language.java.resource.java.ui.JavaPreferenceConstants.EDITOR_MATCHING_BRACKETS_CHECKBOX));
		RGB rgb = PreferenceConverter.getColor(getPreferenceStore(), BRACKETS_COLOR);
		matchingBracketsColorEditor.setColorValue(rgb);
		removeBracketButton.setEnabled(false);
		
		initializeLanguage();
		bracketsTmp.deserialize(getPreferenceStore().getString(language + org.emftext.language.java.resource.java.ui.JavaPreferenceConstants.EDITOR_BRACKETS_SUFFIX));
		String[] brackets = bracketsTmp.getBracketArray();
		if (brackets != null) {
			bracketsList.setItems(brackets);
		}
	}
	
	public void initializeLanguage() {
		bracketSetTemp.put(language, bracketsTmp.serialize());
		bracketsTmp.deserialize(bracketSetTemp.get(language));
		leftBracketTokensCombo.setItems(ALL_LEFT_BRACKETS);
		leftBracketTokensCombo.select(0);
		rightBracketTokensCombo.setItems(ALL_RIGHT_BRACKETS);
		rightBracketTokensCombo.select(0);
		bracketsList.setItems(bracketsTmp.getBracketArray());
	}
	
	private void addListenersToStyleButtons() {
		enableCheckbox.addSelectionListener(new SelectionListener() {
			
			public void widgetSelected(SelectionEvent e) {
				matchingBracketsColorButton.setEnabled(enableCheckbox.getSelection());
			}
			
			public void widgetDefaultSelected(SelectionEvent e) {
				// do nothing
			}
		});
		
		addBracketButton.addSelectionListener(new SelectionListener() {
			
			public void widgetSelected(SelectionEvent e) {
				String open = leftBracketTokensCombo.getText();
				String close = rightBracketTokensCombo.getText();
				if (bracketsTmp.isBracket(open) || bracketsTmp.isBracket(close)) {
					setErrorMessage("One or both bracket parts are set!");
				} else {
					bracketsTmp.addBracketPair(open, close, enableClosingInside.getSelection(), enableCloseAfterEnter.getSelection());
					bracketsList.setItems(bracketsTmp.getBracketArray());
					setErrorMessage(null);
					bracketSetTemp.put(language, bracketsTmp.serialize());
				}
			}
			
			public void widgetDefaultSelected(SelectionEvent e) {
				// do nothing
			}
		});
		
		removeBracketButton.addSelectionListener(new SelectionListener() {
			
			public void widgetSelected(SelectionEvent e) {
				bracketsTmp.removeBracketPairs(bracketsList.getSelection());
				setErrorMessage(null);
				bracketsList.setItems(bracketsTmp.getBracketArray());
				bracketSetTemp.put(language, bracketsTmp.serialize());
			}
			
			public void widgetDefaultSelected(SelectionEvent e) {
				// do nothing
			}
		});
		
		bracketsList.addSelectionListener(new SelectionListener() {
			
			public void widgetSelected(SelectionEvent e) {
				org.emftext.language.java.resource.java.IJavaBracketPair bracketPair = getSelectedBracketPair();
				if (bracketPair == null) {
					removeBracketButton.setEnabled(false);
					return;
				}
				enableClosingInside.setEnabled(true);
				enableCloseAfterEnter.setEnabled(true);
				enableClosingInside.setSelection(bracketPair.isClosingEnabledInside());
				enableCloseAfterEnter.setSelection(bracketPair.isCloseAfterEnter());
				removeBracketButton.setEnabled(true);
			}
			
			public void widgetDefaultSelected(SelectionEvent e) {
				// do nothing
			}
		});
		
		enableClosingInside.addSelectionListener(new SelectionListener() {
			
			public void widgetSelected(SelectionEvent e) {
				org.emftext.language.java.resource.java.IJavaBracketPair bracketPair = getSelectedBracketPair();
				if (bracketPair != null) {
					boolean closingEnabledInside = enableClosingInside.getSelection();
					bracketPair.setClosingEnabledInside(closingEnabledInside);
				}
				bracketSetTemp.put(language, bracketsTmp.serialize());
			}
			
			public void widgetDefaultSelected(SelectionEvent e) {
				// do nothing
			}
		});
		
		enableCloseAfterEnter.addSelectionListener(new SelectionListener() {
			
			public void widgetSelected(SelectionEvent e) {
				org.emftext.language.java.resource.java.IJavaBracketPair bracketPair = getSelectedBracketPair();
				if (bracketPair != null) {
					boolean closeAfterEnter = enableCloseAfterEnter.getSelection();
					bracketPair.setCloseAfterEnter(closeAfterEnter);
				}
				bracketSetTemp.put(language, bracketsTmp.serialize());
			}
			
			public void widgetDefaultSelected(SelectionEvent e) {
				// do nothing
			}
		});
	}
	
	/**
	 * Sets the default values for this preference page.
	 */
	protected void performDefaults() {
		IPreferenceStore preferenceStore = getPreferenceStore();
		enableCheckbox.setSelection(preferenceStore.getDefaultBoolean(org.emftext.language.java.resource.java.ui.JavaPreferenceConstants.EDITOR_MATCHING_BRACKETS_CHECKBOX));
		matchingBracketsColorButton.setEnabled(enableCheckbox.getSelection());
		matchingBracketsColorEditor.setColorValue(PreferenceConverter.getDefaultColor(preferenceStore, BRACKETS_COLOR));
		String defaultBrackets = preferenceStore.getDefaultString(language + org.emftext.language.java.resource.java.ui.JavaPreferenceConstants.EDITOR_BRACKETS_SUFFIX);
		bracketSetTemp.put(language, defaultBrackets);
		bracketsTmp.deserialize(bracketSetTemp.get(language));
		bracketsList.setItems(bracketsTmp.getBracketArray());
		// Reset check boxes and disable them because no item is selected in the
		// bracketsList component.
		enableClosingInside.setSelection(false);
		enableCloseAfterEnter.setSelection(false);
		enableClosingInside.setEnabled(false);
		enableCloseAfterEnter.setEnabled(false);
	}
	
	public boolean performOk() {
		if (!super.performOk()) {
			return false;
		}
		updateActiveEditor();
		return true;
	}
	
	protected void performApply() {
		updateActiveEditor();
	}
	
	/**
	 * Sets the chosen options to the preference store and refreshes it in the editor.
	 */
	private void updateActiveEditor() {
		// set the values after ok or apply
		IPreferenceStore preferenceStore = getPreferenceStore();
		PreferenceConverter.setValue(preferenceStore, BRACKETS_COLOR, matchingBracketsColorEditor.getColorValue());
		preferenceStore.setValue(org.emftext.language.java.resource.java.ui.JavaPreferenceConstants.EDITOR_MATCHING_BRACKETS_CHECKBOX, enableCheckbox.getSelection());
		preferenceStore.setValue(language + org.emftext.language.java.resource.java.ui.JavaPreferenceConstants.EDITOR_BRACKETS_SUFFIX, bracketSetTemp.get(language));
	}
	
	protected org.emftext.language.java.resource.java.IJavaBracketPair getSelectedBracketPair() {
		int[] itemIndices = bracketsList.getSelectionIndices();
		if (itemIndices == null || itemIndices.length != 1) {
			// The bracketsList component is set to single selection. Thus, we expect exactly
			// one selected item.
			return null;
		}
		int index = itemIndices[0];
		return bracketsTmp.getBracketPair(index);
	}
	
}
