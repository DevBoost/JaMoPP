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
 * Super class for JSR - Jump to subroutine
 *
 * @version $Id: JsrInstruction.java 386056 2006-03-15 11:31:56Z tcurdt $
 * @author  <A HREF="mailto:m.dahm@gmx.de">M. Dahm</A>
 */
public abstract class JsrInstruction extends BranchInstruction implements UnconditionalBranch,
        TypedInstruction, StackProducer {

    JsrInstruction(short opcode, InstructionHandle target) {
        super(opcode, target);
    }


    /**
     * Empty constructor needed for the Class.newInstance() statement in
     * Instruction.readInstruction(). Not to be used otherwise.
     */
    JsrInstruction() {
    }


    /** @return return address type
     */
    public Type getType( ConstantPoolGen cp ) {
        return new ReturnaddressType(physicalSuccessor());
    }


    /**
     * Returns an InstructionHandle to the physical successor
     * of this JsrInstruction. <B>For this method to work,
     * this JsrInstruction object must not be shared between
     * multiple InstructionHandle objects!</B>
     * Formally, there must not be InstructionHandle objects
     * i, j where i != j and i.getInstruction() == this ==
     * j.getInstruction().
     * @return an InstructionHandle to the "next" instruction that
     * will be executed when RETurned from a subroutine.
     */
    public InstructionHandle physicalSuccessor() {
        InstructionHandle ih = this.target;
        // Rewind!
        while (ih.getPrev() != null) {
            ih = ih.getPrev();
        }
        // Find the handle for "this" JsrInstruction object.
        while (ih.getInstruction() != this) {
            ih = ih.getNext();
        }
        InstructionHandle toThis = ih;
        while (ih != null) {
            ih = ih.getNext();
            if ((ih != null) && (ih.getInstruction() == this)) {
                throw new RuntimeException("physicalSuccessor() called on a shared JsrInstruction.");
            }
        }
        // Return the physical successor		
        return toThis.getNext();
    }
}
