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
 * Equality of instructions isn't clearly to be defined. You might
 * wish, for example, to compare whether instructions have the same
 * meaning. E.g., whether two INVOKEVIRTUALs describe the same
 * call.<br>The DEFAULT comparator however, considers two instructions
 * to be equal if they have same opcode and point to the same indexes
 * (if any) in the constant pool or the same local variable index. Branch
 * instructions must have the same target.
 *
 * @see Instruction
 * @version $Id: InstructionComparator.java 386056 2006-03-15 11:31:56Z tcurdt $
 * @author <A HREF="mailto:m.dahm@gmx.de">M. Dahm</A>
 */
public interface InstructionComparator {

    public static final InstructionComparator DEFAULT = new InstructionComparator() {

        public boolean equals( Instruction i1, Instruction i2 ) {
            if (i1.opcode == i2.opcode) {
                if (i1 instanceof Select) {
                    InstructionHandle[] t1 = ((Select) i1).getTargets();
                    InstructionHandle[] t2 = ((Select) i2).getTargets();
                    if (t1.length == t2.length) {
                        for (int i = 0; i < t1.length; i++) {
                            if (t1[i] != t2[i]) {
                                return false;
                            }
                        }
                        return true;
                    }
                } else if (i1 instanceof BranchInstruction) {
                    return ((BranchInstruction) i1).target == ((BranchInstruction) i2).target;
                } else if (i1 instanceof ConstantPushInstruction) {
                    return ((ConstantPushInstruction) i1).getValue().equals(
                            ((ConstantPushInstruction) i2).getValue());
                } else if (i1 instanceof IndexedInstruction) {
                    return ((IndexedInstruction) i1).getIndex() == ((IndexedInstruction) i2)
                            .getIndex();
                } else if (i1 instanceof NEWARRAY) {
                    return ((NEWARRAY) i1).getTypecode() == ((NEWARRAY) i2).getTypecode();
                } else {
                    return true;
                }
            }
            return false;
        }
    };


    public boolean equals( Instruction i1, Instruction i2 );
}
