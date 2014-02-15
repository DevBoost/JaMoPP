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

import org.apache.bcel5_2_0.classfile.ConstantPool;

/**
 * Super class for the GET/PUTxxx family of instructions.
 *
 * @version $Id: FieldInstruction.java 386056 2006-03-15 11:31:56Z tcurdt $
 * @author  <A HREF="mailto:m.dahm@gmx.de">M. Dahm</A>
 */
public abstract class FieldInstruction extends FieldOrMethod implements TypedInstruction {

    /**
     * Empty constructor needed for the Class.newInstance() statement in
     * Instruction.readInstruction(). Not to be used otherwise.
     */
    FieldInstruction() {
    }


    /**
     * @param index to constant pool
     */
    protected FieldInstruction(short opcode, int index) {
        super(opcode, index);
    }


    /**
     * @return mnemonic for instruction with symbolic references resolved
     */
    public String toString( ConstantPool cp ) {
        return org.apache.bcel5_2_0.Constants.OPCODE_NAMES[opcode] + " "
                + cp.constantToString(index, org.apache.bcel5_2_0.Constants.CONSTANT_Fieldref);
    }


    /** @return size of field (1 or 2)
     */
    protected int getFieldSize( ConstantPoolGen cpg ) {
        return getType(cpg).getSize();
    }


    /** @return return type of referenced field
     */
    public Type getType( ConstantPoolGen cpg ) {
        return getFieldType(cpg);
    }


    /** @return type of field
     */
    public Type getFieldType( ConstantPoolGen cpg ) {
        return Type.getType(getSignature(cpg));
    }


    /** @return name of referenced field.
     */
    public String getFieldName( ConstantPoolGen cpg ) {
        return getName(cpg);
    }
}
