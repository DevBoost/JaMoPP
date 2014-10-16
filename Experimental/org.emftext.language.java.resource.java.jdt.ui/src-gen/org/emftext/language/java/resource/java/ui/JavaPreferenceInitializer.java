/**
 * <copyright>
 * </copyright>
 *
 * 
 */
package org.emftext.language.java.resource.java.ui;

import java.util.Collection;
import org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer;
import org.eclipse.jface.preference.IPreferenceStore;

/**
 * This class can be used to initialize default preference values.
 */
public class JavaPreferenceInitializer extends AbstractPreferenceInitializer {
	
	public void initializeDefaultPreferences() {
		
		initializeDefaultSyntaxHighlighting();
		initializeDefaultBrackets();
		initializeDefaultsContentAssist();
		
		IPreferenceStore store = org.emftext.language.java.resource.java.ui.JavaUIPlugin.getDefault().getPreferenceStore();
		// Set default value for matching brackets
		store.setDefault(org.emftext.language.java.resource.java.ui.JavaPreferenceConstants.EDITOR_MATCHING_BRACKETS_COLOR, "192,192,192");
		store.setDefault(org.emftext.language.java.resource.java.ui.JavaPreferenceConstants.EDITOR_MATCHING_BRACKETS_CHECKBOX, true);
		
	}
	
	protected void initializeDefaultBrackets() {
		IPreferenceStore store = org.emftext.language.java.resource.java.ui.JavaUIPlugin.getDefault().getPreferenceStore();
		initializeDefaultBrackets(store, new org.emftext.language.java.resource.java.mopp.JavaMetaInformation());
	}
	
	protected void initializeDefaultBrackets(IPreferenceStore store, org.emftext.language.java.resource.java.IJavaMetaInformation metaInformation) {
		String languageId = metaInformation.getSyntaxName();
		// set default brackets
		org.emftext.language.java.resource.java.ui.JavaBracketSet bracketSet = new org.emftext.language.java.resource.java.ui.JavaBracketSet();
		final Collection<org.emftext.language.java.resource.java.IJavaBracketPair> bracketPairs = metaInformation.getBracketPairs();
		if (bracketPairs != null) {
			for (org.emftext.language.java.resource.java.IJavaBracketPair bracketPair : bracketPairs) {
				bracketSet.addBracketPair(bracketPair.getOpeningBracket(), bracketPair.getClosingBracket(), bracketPair.isClosingEnabledInside(), bracketPair.isCloseAfterEnter());
			}
		}
		store.setDefault(languageId + org.emftext.language.java.resource.java.ui.JavaPreferenceConstants.EDITOR_BRACKETS_SUFFIX, bracketSet.serialize());
	}
	
	public void initializeDefaultSyntaxHighlighting() {
		IPreferenceStore store = org.emftext.language.java.resource.java.ui.JavaUIPlugin.getDefault().getPreferenceStore();
		initializeDefaultSyntaxHighlighting(store, new org.emftext.language.java.resource.java.mopp.JavaMetaInformation());
	}
	
	protected void initializeDefaultSyntaxHighlighting(IPreferenceStore store, org.emftext.language.java.resource.java.mopp.JavaMetaInformation metaInformation) {
		String languageId = metaInformation.getSyntaxName();
		String[] tokenNames = metaInformation.getSyntaxHighlightableTokenNames();
		if (tokenNames == null) {
			return;
		}
		for (int i = 0; i < tokenNames.length; i++) {
			String tokenName = tokenNames[i];
			org.emftext.language.java.resource.java.IJavaTokenStyle style = metaInformation.getDefaultTokenStyle(tokenName);
			if (style != null) {
				String color = getColorString(style.getColorAsRGB());
				setProperties(store, languageId, tokenName, color, style.isBold(), true, style.isItalic(), style.isStrikethrough(), style.isUnderline());
			} else {
				setProperties(store, languageId, tokenName, "0,0,0", false, false, false, false, false);
			}
		}
	}
	
	private void initializeDefaultsContentAssist() {
		IPreferenceStore store = org.emftext.language.java.resource.java.ui.JavaUIPlugin.getDefault().getPreferenceStore();
		store.setDefault(org.emftext.language.java.resource.java.ui.JavaPreferenceConstants.EDITOR_CONTENT_ASSIST_ENABLED, org.emftext.language.java.resource.java.ui.JavaPreferenceConstants.EDITOR_CONTENT_ASSIST_ENABLED_DEFAULT);
		store.setDefault(org.emftext.language.java.resource.java.ui.JavaPreferenceConstants.EDITOR_CONTENT_ASSIST_DELAY, org.emftext.language.java.resource.java.ui.JavaPreferenceConstants.EDITOR_CONTENT_ASSIST_DELAY_DEFAULT);
		store.setDefault(org.emftext.language.java.resource.java.ui.JavaPreferenceConstants.EDITOR_CONTENT_ASSIST_TRIGGERS, org.emftext.language.java.resource.java.ui.JavaPreferenceConstants.EDITOR_CONTENT_ASSIST_TRIGGERS_DEFAULT);
	}
	
	protected void setProperties(IPreferenceStore store, String languageID, String tokenName, String color, boolean bold, boolean enable, boolean italic, boolean strikethrough, boolean underline) {
		store.setDefault(org.emftext.language.java.resource.java.ui.JavaSyntaxColoringHelper.getPreferenceKey(languageID, tokenName, org.emftext.language.java.resource.java.ui.JavaSyntaxColoringHelper.StyleProperty.BOLD), bold);
		store.setDefault(org.emftext.language.java.resource.java.ui.JavaSyntaxColoringHelper.getPreferenceKey(languageID, tokenName, org.emftext.language.java.resource.java.ui.JavaSyntaxColoringHelper.StyleProperty.COLOR), color);
		store.setDefault(org.emftext.language.java.resource.java.ui.JavaSyntaxColoringHelper.getPreferenceKey(languageID, tokenName, org.emftext.language.java.resource.java.ui.JavaSyntaxColoringHelper.StyleProperty.ENABLE), enable);
		store.setDefault(org.emftext.language.java.resource.java.ui.JavaSyntaxColoringHelper.getPreferenceKey(languageID, tokenName, org.emftext.language.java.resource.java.ui.JavaSyntaxColoringHelper.StyleProperty.ITALIC), italic);
		store.setDefault(org.emftext.language.java.resource.java.ui.JavaSyntaxColoringHelper.getPreferenceKey(languageID, tokenName, org.emftext.language.java.resource.java.ui.JavaSyntaxColoringHelper.StyleProperty.STRIKETHROUGH), strikethrough);
		store.setDefault(org.emftext.language.java.resource.java.ui.JavaSyntaxColoringHelper.getPreferenceKey(languageID, tokenName, org.emftext.language.java.resource.java.ui.JavaSyntaxColoringHelper.StyleProperty.UNDERLINE), underline);
	}
	
	protected String getColorString(int[] colorAsRGB) {
		if (colorAsRGB == null) {
			return "0,0,0";
		}
		if (colorAsRGB.length != 3) {
			return "0,0,0";
		}
		return colorAsRGB[0] + "," +colorAsRGB[1] + ","+ colorAsRGB[2];
	}
	
}

