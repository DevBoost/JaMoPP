/*******************************************************************************
 * Copyright (c) 2006-2012
 * Software Technology Group, Dresden University of Technology
 * DevBoost GmbH, Berlin, Amtsgericht Charlottenburg, HRB 140026
 * 
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *   Software Technology Group - TU Dresden, Germany;
 *   DevBoost GmbH - Berlin, Germany
 *      - initial API and implementation
 ******************************************************************************/
package org.emftext.language.java.util;

/**
 * A CharacterEscaper can be used to escape and unescape special characters
 * in Java strings and character literals. Among these special characters are
 * tabs, single and double quotes, line breaks and backslashes.
 */
public class CharacterEscaper {

	private static final char BACKSLASH = '\\';

	/**
	 * Removes the escape symbol if the given String contains escaped
	 * characters.
	 * 
	 * @param source
	 *            string with escapes
	 * @return string with characters un-escaped
	 */
	public static String unescapeEscapedCharacters(String source) {
		final int srcLen = source.length();
		char c;
		StringBuffer buffer = new StringBuffer(srcLen);

		int i = 0;
		while (i < srcLen) {
			c = source.charAt(i++);

			if (c == BACKSLASH) {
				char nc = source.charAt(i);
				switch (nc) {
					// octal characters: \0 to \377
					case '0':
					case '1':
					case '2':
					case '3':
					case '4':
					case '5':
					case '6':
					case '7': {
						// Now we found the '0' we need to find up to 3 octal digits
						// Note: shifting left by 3 is the same as multiplying by 8
						int v = 0; // Accumulator
						int j;
						boolean stop = false;
						for (j = 0; j < 3 && !stop; j++) {
							if (i + j < source.length()) {
								nc = source.charAt(i + j);
								switch (nc) {
								case 48: // '0'
								case 49: // '1'
								case 50: // '2'
								case 51: // '3'
								case 52: // '4'
								case 53: // '5'
								case 54: // '6'
								case 55: // '7'
									v = ((v << 3) + nc) - 48;
									break;
								default:
									// some other character
									// almost but no go
									stop = true;
									// we have to go back one character, because
									// we've read to far
									j--;
									break;
								}
							}
						} // for each of the digits
	
						if (v >= 0) { // We got a full conversion
							c = (char) v; // Use the converted char
							i += j; // skip the numeric values
						}
						break;
					}
					case BACKSLASH: {
						// if the next character is a backslash we have an
						// escaped backslash
						// skip the second backslash
						i++;
						break;
					}
					case 'b': {
						c = '\b';
						i++;
						break;
					}
					case 't': {
						c = '\t';
						i++;
						break;
					}
					case 'n': {
						c = '\n';
						i++;
						break;
					}
					case 'f': {
						c = '\f';
						i++;
						break;
					}
					case 'r': {
						c = '\r';
						i++;
						break;
					}
					case '\"': {
						c = '\"';
						i++;
						break;
					}
					case '\'': {
						c = '\'';
						i++;
						break;
					}
				}
			}
			buffer.append(c);
		}
		// Fill in the remaining characters from the buffer
		while (i < srcLen) {
			buffer.append(source.charAt(i++));
		}
		return buffer.toString();
	}

	public static String escapeEscapedCharacters(String source) {
		final int srcLen = source.length();
		StringBuffer buffer = new StringBuffer(srcLen);
		char[] characters = source.toCharArray();
		for (int i=0; i < characters.length; i++) {
			boolean octalDigitFollows = false;
			if (i+1 < characters.length) {
				octalDigitFollows = 48 <= characters[i+1] && characters[i+1] <= 55;
			}
			buffer.append(escapeEscapedCharacter(characters[i], octalDigitFollows));
		}
		return buffer.toString();
	}

	public static String escapeEscapedCharacter(char character, boolean octalDigitFollows) {
		String s;
		if (Character.MIN_SURROGATE <= character && character <= Character.MAX_SURROGATE) {
			//escape unicode surrogate characters
			s = "\\u" + Integer.toHexString(character).toUpperCase(); 
		} else switch (character) {
			case BACKSLASH: {
				s = "\\\\";
				break;
			}
			case '\b': {
				s = "\\b";
				break;
			}
			case '\t': {
				s = "\\t";
				break;
			}
			case '\n': {
				s = "\\n";
				break;
			}
			case '\f': {
				s = "\\f";
				break;
			}
			case '\r': {
				s = "\\r";
				break;
			}
			case '\"': {
				s = "\\\"";
				break;
			}
			case '\'': {
				s = "\\\'";
				break;
			}
			default : {
				if ((0 <= character && character <= 31) || character == 127) {
					String octalString = Integer.toOctalString(character);
					while (octalDigitFollows == true && octalString.length() != 3) {
						octalString = "0" + octalString;
					}
					s = "\\" + octalString;
				} else {
					s = "" + character;					
				}
			}
		}
		return s;
	}

}
