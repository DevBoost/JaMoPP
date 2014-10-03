/**
 * <copyright>
 * </copyright>
 *
 * 
 */
package org.emftext.language.java.resource.java.ui;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.jface.preference.ColorSelector;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.preference.PreferenceConverter;
import org.eclipse.jface.preference.PreferencePage;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.StructuredViewer;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.ScrollBar;
import org.eclipse.swt.widgets.Scrollable;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;

/**
 * <p>
 * Preference page for configuring syntax coloring.
 * </p>
 * <p>
 * <p><i>Parts of the code were taken from the JDT Java Editor</i>
 * </p>
 */
public class JavaSyntaxColoringPreferencePage extends PreferencePage implements IWorkbenchPreferencePage {
	
	private final static Map<String, List<HighlightingColorListItem>> content = new LinkedHashMap<String, List<HighlightingColorListItem>>();
	private final static Collection<IChangedPreference> changedPreferences = new ArrayList<IChangedPreference>();
	
	public JavaSyntaxColoringPreferencePage() {
		super();
		
		org.emftext.language.java.resource.java.mopp.JavaMetaInformation metaInformation = new org.emftext.language.java.resource.java.mopp.JavaMetaInformation();
		
		String languageId = metaInformation.getSyntaxName();
		
		List<HighlightingColorListItem> terminals = new ArrayList<HighlightingColorListItem>();
		String[] tokenNames = metaInformation.getSyntaxHighlightableTokenNames();
		
		for (int i = 0; i < tokenNames.length; i++) {
			HighlightingColorListItem item = new HighlightingColorListItem(languageId, tokenNames[i]);
			terminals.add(item);
		}
		Collections.sort(terminals);
		content.put(languageId, terminals);
		
		setPreferenceStore(org.emftext.language.java.resource.java.ui.JavaUIPlugin.getDefault().getPreferenceStore());
		setDescription("Configure syntax coloring for ." + languageId + " files.");
	}
	
	private interface IChangedPreference {
		public void apply(IPreferenceStore store);
	}
	
	private abstract static class AbstractChangedPreference implements IChangedPreference {
		
		private String key;
		
		public AbstractChangedPreference(String key) {
			super();
			this.key = key;
		}
		
		public String getKey() {
			return key;
		}
	}
	
	private static class ChangedBooleanPreference extends AbstractChangedPreference {
		
		private boolean newValue;
		
		public ChangedBooleanPreference(String key, boolean newValue) {
			super(key);
			this.newValue = newValue;
		}
		
		public void apply(IPreferenceStore store) {
			store.setValue(getKey(), newValue);
		}
	}
	
	private static class ChangedRGBPreference extends AbstractChangedPreference {
		
		private RGB newValue;
		
		public ChangedRGBPreference(String key, RGB newValue) {
			super(key);
			this.newValue = newValue;
		}
		
		public void apply(IPreferenceStore store) {
			PreferenceConverter.setValue(store, getKey(), newValue);
		}
	}
	
	/**
	 * Item in the highlighting color list.
	 */
	private static class HighlightingColorListItem implements Comparable<HighlightingColorListItem> {
		/**
		 * Display name
		 */
		private String fDisplayName;
		/**
		 * Color preference key
		 */
		private String fColorKey;
		/**
		 * Bold preference key
		 */
		private String fBoldKey;
		/**
		 * Italic preference key
		 */
		private String fItalicKey;
		/**
		 * Strikethrough preference key.
		 */
		private String fStrikethroughKey;
		/**
		 * Underline preference key.
		 */
		private String fUnderlineKey;
		private String fEnableKey;
		
		/**
		 * Initializes a new item with the given values.
		 */
		public HighlightingColorListItem(String languageID, String tokenName) {
			fDisplayName = tokenName;
			fColorKey = org.emftext.language.java.resource.java.ui.JavaSyntaxColoringHelper.getPreferenceKey(languageID, tokenName, org.emftext.language.java.resource.java.ui.JavaSyntaxColoringHelper.StyleProperty.COLOR);
			fBoldKey = org.emftext.language.java.resource.java.ui.JavaSyntaxColoringHelper.getPreferenceKey(languageID, tokenName, org.emftext.language.java.resource.java.ui.JavaSyntaxColoringHelper.StyleProperty.BOLD);
			fItalicKey = org.emftext.language.java.resource.java.ui.JavaSyntaxColoringHelper.getPreferenceKey(languageID, tokenName, org.emftext.language.java.resource.java.ui.JavaSyntaxColoringHelper.StyleProperty.ITALIC);
			fStrikethroughKey = org.emftext.language.java.resource.java.ui.JavaSyntaxColoringHelper.getPreferenceKey(languageID, tokenName, org.emftext.language.java.resource.java.ui.JavaSyntaxColoringHelper.StyleProperty.STRIKETHROUGH);
			fUnderlineKey = org.emftext.language.java.resource.java.ui.JavaSyntaxColoringHelper.getPreferenceKey(languageID, tokenName, org.emftext.language.java.resource.java.ui.JavaSyntaxColoringHelper.StyleProperty.UNDERLINE);
			fEnableKey = org.emftext.language.java.resource.java.ui.JavaSyntaxColoringHelper.getPreferenceKey(languageID, tokenName, org.emftext.language.java.resource.java.ui.JavaSyntaxColoringHelper.StyleProperty.ENABLE);
		}
		
		/**
		 * 
		 * @return the bold preference key
		 */
		public String getBoldKey() {
			return fBoldKey;
		}
		
		/**
		 * 
		 * @return the italic preference key
		 */
		public String getItalicKey() {
			return fItalicKey;
		}
		
		/**
		 * 
		 * @return the strikethrough preference key
		 */
		public String getStrikethroughKey() {
			return fStrikethroughKey;
		}
		
		/**
		 * 
		 * @return the underline preference key
		 */
		public String getUnderlineKey() {
			return fUnderlineKey;
		}
		
		/**
		 * 
		 * @return the color preference key
		 */
		public String getColorKey() {
			return fColorKey;
		}
		
		/**
		 * 
		 * @return the display name
		 */
		public String getDisplayName() {
			return fDisplayName;
		}
		
		public String getEnableKey() {
			return fEnableKey;
		}
		
		public int compareTo(HighlightingColorListItem o) {
			return fDisplayName.compareTo(o.getDisplayName());
		}
		
	}
	
	/**
	 * Color list label provider.
	 */
	private class ColorListLabelProvider extends LabelProvider {
		
		public String getText(Object element) {
			if (element instanceof String) {
				return (String) element;
			}
			String displayName = ((HighlightingColorListItem) element).getDisplayName();
			if (displayName.startsWith("QUOTED_")) {
				String[] parts = displayName.split("_");
				if (parts.length == 3) {
					if (parts[1].length() == 2 && parts[2].length() == 2) {
						// Convert decimal ascii codes to string
						try {
							char prefix = (char) Integer.parseInt(parts[1]);
							char suffix = (char) Integer.parseInt(parts[2]);
							displayName = prefix + "..." + suffix;
						} catch (NumberFormatException nfe) {
							// ignore
						}
					}
				}
			}
			return displayName;
		}
	}
	
	/**
	 * Color list content provider.
	 */
	private class ColorListContentProvider implements ITreeContentProvider {
		
		public ColorListContentProvider() {
			super();
		}
		
		public Object[] getElements(Object inputElement) {
			List<HighlightingColorListItem> contentsList = new ArrayList<HighlightingColorListItem>();
			for (List<HighlightingColorListItem> l : content.values()) {
				contentsList.addAll(l);
			}
			return contentsList.toArray();
		}
		
		public void dispose() {
		}
		
		public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
		}
		
		public Object[] getChildren(Object parentElement) {
			return new Object[0];
		}
		
		public Object getParent(Object element) {
			return null;
		}
		
		public boolean hasChildren(Object element) {
			return false;
		}
	}
	
	private ColorSelector fSyntaxForegroundColorEditor;
	private Label fColorEditorLabel;
	private Button fBoldCheckBox;
	private Button fEnableCheckbox;
	/**
	 * Check box for italic preference.
	 */
	private Button fItalicCheckBox;
	/**
	 * Check box for strikethrough preference.
	 */
	private Button fStrikethroughCheckBox;
	/**
	 * Check box for underline preference.
	 */
	private Button fUnderlineCheckBox;
	private Button fForegroundColorButton;
	
	/**
	 * Highlighting color list viewer
	 */
	private StructuredViewer fListViewer;
	
	public void dispose() {
		super.dispose();
	}
	
	private void handleSyntaxColorListSelection() {
		HighlightingColorListItem item = getHighlightingColorListItem();
		if (item == null) {
			fEnableCheckbox.setEnabled(false);
			fSyntaxForegroundColorEditor.getButton().setEnabled(false);
			fColorEditorLabel.setEnabled(false);
			fBoldCheckBox.setEnabled(false);
			fItalicCheckBox.setEnabled(false);
			fStrikethroughCheckBox.setEnabled(false);
			fUnderlineCheckBox.setEnabled(false);
			return;
		}
		RGB rgb = PreferenceConverter.getColor(getPreferenceStore(), item.getColorKey());
		fSyntaxForegroundColorEditor.setColorValue(rgb);
		fBoldCheckBox.setSelection(getPreferenceStore().getBoolean(item.getBoldKey()));
		fItalicCheckBox.setSelection(getPreferenceStore().getBoolean(item.getItalicKey()));
		fStrikethroughCheckBox.setSelection(getPreferenceStore().getBoolean(item.getStrikethroughKey()));
		fUnderlineCheckBox.setSelection(getPreferenceStore().getBoolean(item.getUnderlineKey()));
		
		fEnableCheckbox.setEnabled(true);
		boolean enable = getPreferenceStore().getBoolean(item.getEnableKey());
		fEnableCheckbox.setSelection(enable);
		fSyntaxForegroundColorEditor.getButton().setEnabled(enable);
		fColorEditorLabel.setEnabled(enable);
		fBoldCheckBox.setEnabled(enable);
		fItalicCheckBox.setEnabled(enable);
		fStrikethroughCheckBox.setEnabled(enable);
		fUnderlineCheckBox.setEnabled(enable);
	}
	
	private Control createSyntaxPage(final Composite parent) {
		
		Composite colorComposite = new Composite(parent, SWT.NONE);
		GridLayout layout = new GridLayout();
		layout.marginHeight = 0;
		layout.marginWidth = 0;
		colorComposite.setLayout(layout);
		
		addFiller(colorComposite, 1);
		
		Label label = new Label(colorComposite, SWT.LEFT);
		label.setText("Element:");
		label.setLayoutData(new GridData(GridData.FILL, GridData.BEGINNING, true, false));
		
		Composite editorComposite = createEditorComposite(colorComposite);
		createListViewer(editorComposite);
		createStylesComposite(editorComposite);
		
		addListenersToStyleButtons();
		colorComposite.layout(false);
		handleSyntaxColorListSelection();
		
		return colorComposite;
	}
	
	private Composite createEditorComposite(Composite colorComposite) {
		GridLayout layout;
		Composite editorComposite = new Composite(colorComposite, SWT.NONE);
		layout = new GridLayout();
		layout.numColumns = 2;
		layout.marginHeight = 0;
		layout.marginWidth = 0;
		editorComposite.setLayout(layout);
		GridData gd = new GridData(GridData.FILL, GridData.FILL, true, true);
		editorComposite.setLayoutData(gd);
		return editorComposite;
	}
	
	private void createListViewer(Composite editorComposite) {
		fListViewer = new TreeViewer(editorComposite, SWT.SINGLE | SWT.BORDER);
		fListViewer.setLabelProvider(new ColorListLabelProvider());
		fListViewer.setContentProvider(new ColorListContentProvider());
		
		GridData gd = new GridData(GridData.FILL, GridData.FILL, true, true);
		gd.heightHint = convertHeightInCharsToPixels(26);
		int maxWidth = 0;
		for (Iterator<List<HighlightingColorListItem>> it = content.values().iterator(); it.hasNext();) {
			for (Iterator<HighlightingColorListItem> j = it.next().iterator(); j.hasNext();) {
				HighlightingColorListItem item = j.next();
				maxWidth = Math.max(maxWidth, convertWidthInCharsToPixels(item.getDisplayName().length()));
			}
		}
		ScrollBar vBar = ((Scrollable) fListViewer.getControl()).getVerticalBar();
		if (vBar != null) {
			// scrollbars and tree indentation guess
			maxWidth += vBar.getSize().x * 3;
		}
		gd.widthHint = maxWidth;
		
		fListViewer.getControl().setLayoutData(gd);
		
		fListViewer.setInput(content);
		fListViewer.setSelection(new StructuredSelection(content.values().iterator().next()));
		fListViewer.addSelectionChangedListener(new ISelectionChangedListener() {
			public void selectionChanged(SelectionChangedEvent event) {
				handleSyntaxColorListSelection();
			}
		});
	}
	
	private void addListenersToStyleButtons() {
		fForegroundColorButton.addSelectionListener(new SelectionListener() {
			public void widgetDefaultSelected(SelectionEvent e) {
				// do nothing
			}
			
			public void widgetSelected(SelectionEvent e) {
				HighlightingColorListItem item = getHighlightingColorListItem();
				
				changedPreferences.add(new ChangedRGBPreference(item.getColorKey(), fSyntaxForegroundColorEditor.getColorValue()));
			}
		});
		
		fBoldCheckBox.addSelectionListener(new SelectionListener() {
			public void widgetDefaultSelected(SelectionEvent e) {
				// do nothing
			}
			
			public void widgetSelected(SelectionEvent e) {
				HighlightingColorListItem item = getHighlightingColorListItem();
				changedPreferences.add(new ChangedBooleanPreference(item.getBoldKey(),
				fBoldCheckBox.getSelection()));
			}
		});
		
		fItalicCheckBox.addSelectionListener(new SelectionListener() {
			public void widgetDefaultSelected(SelectionEvent e) {
				// do nothing
			}
			
			public void widgetSelected(SelectionEvent e) {
				HighlightingColorListItem item = getHighlightingColorListItem();
				changedPreferences.add(new ChangedBooleanPreference(item.getItalicKey(),
				fItalicCheckBox.getSelection()));
			}
		});
		fStrikethroughCheckBox.addSelectionListener(new SelectionListener() {
			public void widgetDefaultSelected(SelectionEvent e) {
				// do nothing
			}
			
			public void widgetSelected(SelectionEvent e) {
				HighlightingColorListItem item = getHighlightingColorListItem();
				changedPreferences.add(new ChangedBooleanPreference(item.getStrikethroughKey(),
				fStrikethroughCheckBox.getSelection()));
			}
		});
		
		fUnderlineCheckBox.addSelectionListener(new SelectionListener() {
			public void widgetDefaultSelected(SelectionEvent e) {
				// do nothing
			}
			
			public void widgetSelected(SelectionEvent e) {
				HighlightingColorListItem item = getHighlightingColorListItem();
				changedPreferences.add(new ChangedBooleanPreference(item.getUnderlineKey(),
				fUnderlineCheckBox.getSelection()));
			}
		});
		
		fEnableCheckbox.addSelectionListener(new SelectionListener() {
			public void widgetDefaultSelected(SelectionEvent e) {
				// do nothing
			}
			
			public void widgetSelected(SelectionEvent e) {
				HighlightingColorListItem item = getHighlightingColorListItem();
				
				boolean enable = fEnableCheckbox.getSelection();
				changedPreferences.add(new ChangedBooleanPreference(item.getEnableKey(), enable));
				fEnableCheckbox.setSelection(enable);
				fSyntaxForegroundColorEditor.getButton().setEnabled(enable);
				fColorEditorLabel.setEnabled(enable);
				fBoldCheckBox.setEnabled(enable);
				fItalicCheckBox.setEnabled(enable);
				fStrikethroughCheckBox.setEnabled(enable);
				fUnderlineCheckBox.setEnabled(enable);
				// uninstallSemanticHighlighting();
				// installSemanticHighlighting();
			}
		});
	}
	
	private void createStylesComposite(Composite editorComposite) {
		GridLayout layout;
		GridData gd;
		Composite stylesComposite = new Composite(editorComposite, SWT.NONE);
		layout = new GridLayout();
		layout.marginHeight = 0;
		layout.marginWidth = 0;
		layout.numColumns = 2;
		stylesComposite.setLayout(layout);
		stylesComposite.setLayoutData(new GridData(GridData.END, GridData.FILL, false, true));
		
		fEnableCheckbox = new Button(stylesComposite, SWT.CHECK);
		fEnableCheckbox.setText("Enable");
		gd = new GridData(GridData.FILL_HORIZONTAL);
		gd.horizontalAlignment = GridData.BEGINNING;
		gd.horizontalSpan = 2;
		fEnableCheckbox.setLayoutData(gd);
		
		fColorEditorLabel = new Label(stylesComposite, SWT.LEFT);
		fColorEditorLabel.setText("Color:");
		gd = new GridData(GridData.HORIZONTAL_ALIGN_BEGINNING);
		gd.horizontalIndent = 20;
		fColorEditorLabel.setLayoutData(gd);
		
		fSyntaxForegroundColorEditor = new ColorSelector(stylesComposite);
		fForegroundColorButton = fSyntaxForegroundColorEditor.getButton();
		gd = new GridData(GridData.HORIZONTAL_ALIGN_BEGINNING);
		fForegroundColorButton.setLayoutData(gd);
		
		fBoldCheckBox = new Button(stylesComposite, SWT.CHECK);
		fBoldCheckBox.setText("Bold");
		gd = new GridData(GridData.HORIZONTAL_ALIGN_BEGINNING);
		gd.horizontalIndent = 20;
		gd.horizontalSpan = 2;
		fBoldCheckBox.setLayoutData(gd);
		
		fItalicCheckBox = new Button(stylesComposite, SWT.CHECK);
		fItalicCheckBox.setText("Italic");
		gd = new GridData(GridData.HORIZONTAL_ALIGN_BEGINNING);
		gd.horizontalIndent = 20;
		gd.horizontalSpan = 2;
		fItalicCheckBox.setLayoutData(gd);
		
		fStrikethroughCheckBox = new Button(stylesComposite, SWT.CHECK);
		fStrikethroughCheckBox.setText("Strikethrough");
		gd = new GridData(GridData.HORIZONTAL_ALIGN_BEGINNING);
		gd.horizontalIndent = 20;
		gd.horizontalSpan = 2;
		fStrikethroughCheckBox.setLayoutData(gd);
		
		fUnderlineCheckBox = new Button(stylesComposite, SWT.CHECK);
		fUnderlineCheckBox.setText("Underlined");
		gd = new GridData(GridData.HORIZONTAL_ALIGN_BEGINNING);
		gd.horizontalIndent = 20;
		gd.horizontalSpan = 2;
		fUnderlineCheckBox.setLayoutData(gd);
	}
	
	private void addFiller(Composite composite, int horizontalSpan) {
		org.emftext.language.java.resource.java.ui.JavaPixelConverter pixelConverter = new org.emftext.language.java.resource.java.ui.JavaPixelConverter(composite);
		Label filler = new Label(composite, SWT.LEFT);
		GridData gd = new GridData(GridData.HORIZONTAL_ALIGN_FILL);
		gd.horizontalSpan = horizontalSpan;
		gd.heightHint = pixelConverter.convertHeightInCharsToPixels(1) / 2;
		filler.setLayoutData(gd);
	}
	
	/**
	 * Returns the current highlighting color list item.
	 */
	private HighlightingColorListItem getHighlightingColorListItem() {
		IStructuredSelection selection = (IStructuredSelection) fListViewer.getSelection();
		Object element = selection.getFirstElement();
		if (element instanceof String) {
			return null;
		}
		return (HighlightingColorListItem) element;
	}
	
	public void init(IWorkbench workbench) {
	}
	
	protected Control createContents(Composite parent) {
		Control content = createSyntaxPage(parent);
		return content;
	}
	
	public boolean performOk() {
		if (!super.performOk()) {
			return false;
		}
		performApply();
		return true;
	}
	
	public boolean performCancel() {
		if (!super.performCancel()) {
			return false;
		}
		// discard all changes that were made
		changedPreferences.clear();
		return true;
	}
	
	protected void performApply() {
		for (IChangedPreference changedPreference : changedPreferences) {
			changedPreference.apply(getPreferenceStore());
		}
		changedPreferences.clear();
	}
	
	public void performDefaults() {
		super.performDefaults();
		
		IPreferenceStore preferenceStore = getPreferenceStore();
		// reset all preferences to their default values
		for (String languageID : content.keySet()) {
			List<HighlightingColorListItem> items = content.get(languageID);
			for (HighlightingColorListItem item : items) {
				restoreDefaultBooleanValue(preferenceStore, item.getBoldKey());
				restoreDefaultBooleanValue(preferenceStore, item.getEnableKey());
				restoreDefaultBooleanValue(preferenceStore, item.getItalicKey());
				restoreDefaultBooleanValue(preferenceStore, item.getStrikethroughKey());
				restoreDefaultBooleanValue(preferenceStore, item.getUnderlineKey());
				restoreDefaultStringValue(preferenceStore, item.getColorKey());
			}
		}
		handleSyntaxColorListSelection();
	}
	
	private void restoreDefaultBooleanValue(IPreferenceStore preferenceStore, String key) {
		preferenceStore.setValue(key, preferenceStore.getDefaultBoolean(key));
	}
	
	private void restoreDefaultStringValue(IPreferenceStore preferenceStore, String key) {
		preferenceStore.setValue(key, preferenceStore.getDefaultString(key));
	}
	
}
