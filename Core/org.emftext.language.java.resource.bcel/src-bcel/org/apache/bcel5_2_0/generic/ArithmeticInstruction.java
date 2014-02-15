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

import org.apache.bcel5_2_0.Constants;

/**
 * Super class for the family of arithmetic instructions.
 *
 * @version $Id: ArithmeticInstruction.java 386056 2006-03-15 11:31:56Z tcurdt $
 * @author  <A HREF="mailto:m.dahm@gmx.de">M. Dahm</A>
 */
public abstract class ArithmeticInstruction extends Instruction implements TypedInstruction,
        StackProducer, StackConsumer {

    /**
     * Empty constructor needed for the Class.newInstance() statement in
     * Instruction.readInstruction(). Not to be used otherwise.
     */
    ArithmeticInstruction() {
    }


    /**
     * @param opcode of instruction
     */
    protected ArithmeticInstruction(short opcode) {
        super(opcode, (short) 1);
    }


    /** @return type associated with the instruction
     */
    public Type getType( ConstantPoolGen cp ) {
        switch (opcode) {
            case Constants.DADD:
            case Constants.DDIV:
            case Constants.DMUL:
            case Constants.DNEG:
            case Constants.DREM:
            case Constants.DSUB:
                return Type.DOUBLE;
            case Constants.FADD:
            case Constants.FDIV:
            case Constants.FMUL:
            case Constants.FNEG:
            case Constants.FREM:
            case Constants.FSUB:
                return Type.FLOAT;
            case Constants.IADD:
            case Constants.IAND:
            case Constants.IDIV:
            case Constants.IMUL:
            case Constants.INEG:
            case Constants.IOR:
            case Constants.IREM:
            case Constants.ISHL:
            case Constants.ISHR:
            case Constants.ISUB:
            case Constants.IUSHR:
            case Constants.IXOR:
                return Type.INT;
            case Constants.LADD:
            case Constants.LAND:
            case Constants.LDIV:
            case Constants.LMUL:
            case Constants.LNEG:
            case Constants.LOR:
            case Constants.LREM:
            case Constants.LSHL:
            case Constants.LSHR:
            case Constants.LSUB:
            case Constants.LUSHR:
            case Constants.LXOR:
                return Type.LONG;
            default: // Never reached
                throw new ClassGenException("Unknown type " + opcode);
        }
    }
}
