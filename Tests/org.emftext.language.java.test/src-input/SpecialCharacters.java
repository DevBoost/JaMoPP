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
import java.util.HashMap;
import java.util.Map;

public class SpecialCharacters {

	void m1() {
		"xyz\5abc".toString();
		char aChar = ' ';
		aChar = '\"'; // " 1
		aChar = '"'; // " 2
		aChar = '\5';
		int x = 0;
		StringBuffer outBuffer = new StringBuffer();
		boolean escapeSpace = true;

		switch (aChar) {

		case ' ':

			if (x == 0 || escapeSpace)

				outBuffer.append('\\');

			outBuffer.append(' ');

			break;

		case '\t': //tab 1
			outBuffer.append('\\');
			outBuffer.append('t');
			aChar = '	'; //tab 2
			break;

		case '\n':
			outBuffer.append('\\');
			outBuffer.append('n');

			break;

		case '\r':
			outBuffer.append('\\');
			outBuffer.append('r');

			break;

		case '\f':
			outBuffer.append('\\');
			outBuffer.append('f');
			
			break;
		case '\37': //octal format
			break;
		case '\010': //octal format
			break;

		case '=': // Fall through

		case ':': // Fall through

		case '#': // Fall through

		case '!':

			outBuffer.append('\\');
			outBuffer.append(aChar);

			break;

		default:

			if (((aChar < 0x0020) || (aChar > 0x007e))) {

				outBuffer.append('\\');

				outBuffer.append('u');

			} else {

				outBuffer.append(aChar);

			}

		}
	}

	private static Map<String, Character> entities;

	static {

		entities = new HashMap<String, Character>();

		entities.put("&lt;", '<');

		entities.put("&gt;", '>');

		entities.put("&amp;", '&');

		entities.put("&quot;", '"');

		entities.put("&acute;", (char) 180);

		entities.put("&sect;", (char) 167);

		entities.put("&#39;", '\'');

	}

}
