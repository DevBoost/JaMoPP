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
public class EqualityExpression {
    void foo(int i) {
    	double x = 3.0E4;
    	double y= 3e2f;
    	double z = 3.32423e4;
    	double a = 3;
    	double b = 9;
    	double c = 9f;
    	
    	
        switch (i) {
            case (("1" == "2" == true != false == false != false == true == false == false != true) ? 1 : 0):
            case (("1" != "2" == true) ? 2 : 0):
            case (("1" == "2" != true) ? 3 : 0):
            case (("1" != "2" != false) ? 4 : 0):
        }
    }
}
