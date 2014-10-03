/**
 * <copyright>
 * </copyright>
 *
 * 
 */
package org.emftext.language.java.resource.java.ui;


/**
 * An interface that defines some constants used to create the keys for
 * preferences.
 */
public interface JavaPreferenceConstants {
	
	// Constants for syntax highlighting
	/**
	 * Preference key suffix to enable syntax highlighting for a token type.
	 */
	public static final String EDITOR_ENABLE_SUFFIX = "_enable";
	public static final String EDITOR_COLOR_SUFFIX = "_color";
	
	// Constants for brackets
	public static final String EDITOR_MATCHING_BRACKETS_COLOR = "_matchingBracketsColor";
	public static final String EDITOR_MATCHING_BRACKETS_CHECKBOX = "_matchingBracketsCheckbox";
	public static final String EDITOR_BRACKETS_SUFFIX = "_brackets";
	
	// Constants for content assists
	public static final String EDITOR_CONTENT_ASSIST_ENABLED 	= "_activationEnabled";
	public static final String EDITOR_CONTENT_ASSIST_DELAY 		= "_activationDelay";
	public static final String EDITOR_CONTENT_ASSIST_TRIGGERS 	= "_activationTriggers";
	// and their defaults
	public static final boolean EDITOR_CONTENT_ASSIST_ENABLED_DEFAULT	= true;
	public static final int EDITOR_CONTENT_ASSIST_DELAY_DEFAULT			= 200;
	public static final String EDITOR_CONTENT_ASSIST_TRIGGERS_DEFAULT 	= ".*@=&?:|^+-/%~!";
	
}
