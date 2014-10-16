/**
 * <copyright>
 * </copyright>
 *
 * 
 */
package org.emftext.language.java.resource.java.ui;

import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.DefaultIndentLineAutoEditStrategy;
import org.eclipse.jface.text.DocumentCommand;
import org.eclipse.jface.text.IDocument;

/**
 * The JavaAutoEditStrategy extends the default auto edit strategy such that an
 * additional tab is added if a line break is entered after opening brackets which
 * are configured as <code>closeAfterEnter</code>. Also, closing brackets are
 * automatically inserted right away when opening brackets are added where
 * <code>closeAfterEnter</code> is set to <code>false</code>.
 */
public class JavaAutoEditStrategy extends DefaultIndentLineAutoEditStrategy {
	
	private org.emftext.language.java.resource.java.ui.JavaBracketSet bracketSet;
	
	public JavaAutoEditStrategy() {
		super();
		org.emftext.language.java.resource.java.ui.JavaUIPlugin plugin = org.emftext.language.java.resource.java.ui.JavaUIPlugin.getDefault();
		if (plugin != null) {
			IPreferenceStore preferenceStore = plugin.getPreferenceStore();
			bracketSet = new org.emftext.language.java.resource.java.ui.JavaBracketSet();
			bracketSet.resetBrackets(preferenceStore);
		}
	}
	
	/**
	 * This method is only used for injecting a bracket set during tests.
	 */
	@Deprecated
	public void setBracketSet(org.emftext.language.java.resource.java.ui.JavaBracketSet bracketSet) {
		this.bracketSet = bracketSet;
	}
	
	@Override
	public void customizeDocumentCommand(IDocument document, DocumentCommand command) {
		String text = command.text;
		String textBefore = command.text;
		super.customizeDocumentCommand(document, command);
		String textAfter = command.text;
		if (textAfter.length() < textBefore.length()) {
			return;
		}
		
		consumeAddedClosingBracket(document, command);
		addClosingBracketAfterEnterIfRequired(document, command, text, textBefore, textAfter);
		addClosingBracket(document, command);
	}
	
	protected void consumeAddedClosingBracket(IDocument document, DocumentCommand command) {
		// When typing the closing bracket manually and the next character is an auto
		// generated closing bracket, then do not insert the new closing bracket (i.e.,
		// make it feel like it was overridden).
		String insertedText = command.text;
		
		if (!bracketSet.isClosingBracket(insertedText) || insertedText.length() != 1) {
			return;
		}
		
		try {
			char insertedBracket = insertedText.charAt(0);
			
			int offset = command.offset;
			char nextCharacter = document.getChar(offset);
			
			// NOTE: To be entirely accurate, one would have to check whether the next
			// character truly _functions_ as a closing bracket (e.g., is the second of a pair
			// of quotes, not the first).
			boolean nextCharacterIsClosingBracket = bracketSet.isClosingBracket(Character.toString(nextCharacter));
			
			boolean nextCharacterWasAddedAutomatically = true;
			
			if (insertedBracket == nextCharacter && nextCharacterIsClosingBracket && nextCharacterWasAddedAutomatically) {
				// Do not add the character again but forward the caret. Effectively gives the
				// illusion of overriding the previously automatically added closing bracket.
				command.text = "";
				command.caretOffset = offset + 1;
				command.shiftsCaret = true;
			}
		} catch(BadLocationException e) {
		}
	}
	
	protected void addClosingBracket(IDocument document, DocumentCommand command) {
		String openingBracket = command.text;
		
		if (!bracketSet.isOpeningBracket(openingBracket) || !bracketSet.isCloseInstantly(openingBracket)) {
			return;
		}
		
		String closingBracket = bracketSet.getCounterpart(openingBracket);
		
		command.text = command.text + closingBracket;
		command.shiftsCaret = false;
		command.caretOffset = command.offset + 1;
	}
	
	protected void addClosingBracketAfterEnterIfRequired(IDocument document, DocumentCommand command, String text, String textBefore, String textAfter) {
		boolean isLineBreak = isLineBreak(text);
		if (!isLineBreak) {
			return;
		}
		
		String documentText = document.get();
		String openingBracketBefore = getCloseAfterBracketBefore(documentText, command.offset);
		if (openingBracketBefore == null) {
			return;
		}
		// add additional indentation (because a line break was entered after an opening
		// bracket)
		command.text = command.text + "\t";
		String closingBracket = bracketSet.getCounterpart(openingBracketBefore);
		boolean closingBracketIsMissing = count(documentText, openingBracketBefore) != count(documentText, closingBracket);
		// add closing bracket (if required)
		if (closingBracketIsMissing) {
			command.text = command.text + textAfter;
			command.text = command.text + closingBracket;
		}
		command.shiftsCaret = false;
		command.caretOffset = command.offset + textAfter.length() + 1;
	}
	
	/**
	 * Returns the number of occurrences of 'searchString' in 'text'.
	 */
	protected int count(String text, String searchString) {
		int index = text.indexOf(searchString);
		int count = 0;
		while (index >= 0) {
			count++;
			index = text.indexOf(searchString, index + 1);
		}
		
		return count;
	}
	
	/**
	 * Searches for a bracket that must be closed when line breaks are entered and
	 * which is located right before the cursor (ignoring whitespace).
	 */
	protected String getCloseAfterBracketBefore(String text, int offset) {
		for (int i = offset - 1; i >= 0; i--) {
			char charAtI = text.charAt(i);
			String stringAtI = Character.toString(charAtI);
			if (bracketSet.isCloseAfterEnter(stringAtI)) {
				return stringAtI;
			}
			if (charAtI == ' ' || charAtI == '\t') {
				continue;
			}
			break;
		}
		return null;
	}
	
	protected boolean isLineBreak(String text) {
		return "\n".equals(text) || "\r".equals(text) || "\r\n".equals(text);
	}
	
}
