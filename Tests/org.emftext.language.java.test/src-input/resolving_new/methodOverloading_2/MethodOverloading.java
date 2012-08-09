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
package resolving_new.methodOverloading_2;
import java.util.ArrayList;

public class MethodOverloading {
	
	 // target:1
    public Object[] getAnnotations // target:1
    	(int lineNumber) {
        	return null;
    }
    
    // target:2
    private ArrayList<Object> getAnnotations  // 2 returns list (not array)
    	(Object lineAnnotations) {
        	return null;
    }

    public void m() {
        // source:1:target
    	getAnnotations(0);    	
        ArrayList lineAnnotationsArray = new ArrayList();
        // source:2:target
        getAnnotations(lineAnnotationsArray.get(0));

        ArrayList<Object> result = new ArrayList<Object>();
        result.addAll(getAnnotations(lineAnnotationsArray.get(0)));
    }


}
