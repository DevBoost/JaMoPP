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
package org.apache.bcel5_2_0.generic;

/**
 * Wrapper class for `compound' operations, virtual instructions that
 * don't exist as byte code, but give a useful meaning. For example,
 * the (virtual) PUSH instruction takes an arbitray argument and produces the
 * appropiate code at dump time (ICONST, LDC, BIPUSH, ...). Also you can use the
 * SWITCH instruction as a useful template for either LOOKUPSWITCH or
 * TABLESWITCH.
 *
 * The interface provides the possibilty for the user to write
 * `templates' or `macros' for such reuseable code patterns.
 *
 * @version $Id: CompoundInstruction.java 386056 2006-03-15 11:31:56Z tcurdt $
 * @author  <A HREF="mailto:m.dahm@gmx.de">M. Dahm</A>
 * @see PUSH
 * @see SWITCH
 */
public interface CompoundInstruction {

    public InstructionList getInstructionList();
}
