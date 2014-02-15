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

import java.io.DataOutputStream;
import java.io.IOException;

import org.apache.bcel5_2_0.ExceptionConstants;
import org.apache.bcel5_2_0.classfile.ConstantPool;
import org.apache.bcel5_2_0.util.ByteSequence;

/** 
 * MULTIANEWARRAY - Create new mutidimensional array of references
 * <PRE>Stack: ..., count1, [count2, ...] -&gt; ..., arrayref</PRE>
 *
 * @version $Id: MULTIANEWARRAY.java 386056 2006-03-15 11:31:56Z tcurdt $
 * @author  <A HREF="mailto:m.dahm@gmx.de">M. Dahm</A>
 */
public class MULTIANEWARRAY extends CPInstruction implements LoadClass, AllocationInstruction,
        ExceptionThrower {

    private short dimensions;


    /**
     * Empty constructor needed for the Class.newInstance() statement in
     * Instruction.readInstruction(). Not to be used otherwise.
     */
    MULTIANEWARRAY() {
    }


    public MULTIANEWARRAY(int index, short dimensions) {
        super(org.apache.bcel5_2_0.Constants.MULTIANEWARRAY, index);
        if (dimensions < 1) {
            throw new ClassGenException("Invalid dimensions value: " + dimensions);
        }
        this.dimensions = dimensions;
        length = 4;
    }


    /**
     * Dump instruction as byte code to stream out.
     * @param out Output stream
     */
    public void dump( DataOutputStream out ) throws IOException {
        out.writeByte(opcode);
        out.writeShort(index);
        out.writeByte(dimensions);
    }


    /**
     * Read needed data (i.e., no. dimension) from file.
     */
    protected void initFromFile( ByteSequence bytes, boolean wide ) throws IOException {
        super.initFromFile(bytes, wide);
        dimensions = bytes.readByte();
        length = 4;
    }


    /**
     * @return number of dimensions to be created
     */
    public final short getDimensions() {
        return dimensions;
    }


    /**
     * @return mnemonic for instruction
     */
    public String toString( boolean verbose ) {
        return super.toString(verbose) + " " + index + " " + dimensions;
    }


    /**
     * @return mnemonic for instruction with symbolic references resolved
     */
    public String toString( ConstantPool cp ) {
        return super.toString(cp) + " " + dimensions;
    }


    /**
     * Also works for instructions whose stack effect depends on the
     * constant pool entry they reference.
     * @return Number of words consumed from stack by this instruction
     */
    public int consumeStack( ConstantPoolGen cpg ) {
        return dimensions;
    }


    public Class[] getExceptions() {
        Class[] cs = new Class[2 + ExceptionConstants.EXCS_CLASS_AND_INTERFACE_RESOLUTION.length];
        System.arraycopy(ExceptionConstants.EXCS_CLASS_AND_INTERFACE_RESOLUTION, 0, cs, 0,
                ExceptionConstants.EXCS_CLASS_AND_INTERFACE_RESOLUTION.length);
        cs[ExceptionConstants.EXCS_CLASS_AND_INTERFACE_RESOLUTION.length + 1] = ExceptionConstants.NEGATIVE_ARRAY_SIZE_EXCEPTION;
        cs[ExceptionConstants.EXCS_CLASS_AND_INTERFACE_RESOLUTION.length] = ExceptionConstants.ILLEGAL_ACCESS_ERROR;
        return cs;
    }


    public ObjectType getLoadClassType( ConstantPoolGen cpg ) {
        Type t = getType(cpg);
        if (t instanceof ArrayType) {
            t = ((ArrayType) t).getBasicType();
        }
        return (t instanceof ObjectType) ? (ObjectType) t : null;
    }


    /**
     * Call corresponding visitor method(s). The order is:
     * Call visitor methods of implemented interfaces first, then
     * call methods according to the class hierarchy in descending order,
     * i.e., the most specific visitXXX() call comes last.
     *
     * @param v Visitor object
     */
    public void accept( Visitor v ) {
        v.visitLoadClass(this);
        v.visitAllocationInstruction(this);
        v.visitExceptionThrower(this);
        v.visitTypedInstruction(this);
        v.visitCPInstruction(this);
        v.visitMULTIANEWARRAY(this);
    }
}
