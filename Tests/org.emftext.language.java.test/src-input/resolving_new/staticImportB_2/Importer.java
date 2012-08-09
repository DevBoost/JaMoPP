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
package resolving_new.staticImportB_2;

import static resolving_new.staticImportB_2.Exporter.CONSTANT1;

public class Importer {
	
	private Exporter CONSTANT2 // target:2
	;
	
	public void useImportAndLocalField() {
		
		// source:2:target
		CONSTANT2 = 
		// source:1:target
		CONSTANT1;
	}
}
