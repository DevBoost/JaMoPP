/*
 * Copyright  2000-2004 The Apache Software Foundation
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 *
 */
package org.apache.bcel5_2_0.classfile;

/** 
 * Thrown when the BCEL attempts to read a class file and determines
 * that the file is malformed or otherwise cannot be interpreted as a
 * class file.
 *
 * @version $Id: ClassFormatException.java 386056 2006-03-15 11:31:56Z tcurdt $
 * @author  <A HREF="mailto:m.dahm@gmx.de">M. Dahm</A>
 */
public class ClassFormatException extends RuntimeException {

    public ClassFormatException() {
        super();
    }


    public ClassFormatException(String s) {
        super(s);
    }
}
