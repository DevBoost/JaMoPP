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

import org.apache.bcel5_2_0.classfile.LineNumber;

/** 
 * This class represents a line number within a method, i.e., give an instruction
 * a line number corresponding to the source code line.
 *
 * @version $Id: LineNumberGen.java 386056 2006-03-15 11:31:56Z tcurdt $
 * @author  <A HREF="mailto:m.dahm@gmx.de">M. Dahm</A>
 * @see     LineNumber
 * @see     MethodGen
 */
public class LineNumberGen implements InstructionTargeter, Cloneable, java.io.Serializable {

    private InstructionHandle ih;
    private int src_line;


    /**
     * Create a line number.
     *
     * @param ih instruction handle to reference
     */
    public LineNumberGen(InstructionHandle ih, int src_line) {
        setInstruction(ih);
        setSourceLine(src_line);
    }


    /**
     * @return true, if ih is target of this line number
     */
    public boolean containsTarget( InstructionHandle ih ) {
        return this.ih == ih;
    }


    /**
     * @param old_ih old target
     * @param new_ih new target
     */
    public void updateTarget( InstructionHandle old_ih, InstructionHandle new_ih ) {
        if (old_ih != ih) {
            throw new ClassGenException("Not targeting " + old_ih + ", but " + ih + "}");
        } else {
            setInstruction(new_ih);
        }
    }


    /**
     * Get LineNumber attribute .
     *
     * This relies on that the instruction list has already been dumped to byte code or
     * or that the `setPositions' methods has been called for the instruction list.
     */
    public LineNumber getLineNumber() {
        return new LineNumber(ih.getPosition(), src_line);
    }


    public void setInstruction( InstructionHandle ih ) {
        BranchInstruction.notifyTarget(this.ih, ih, this);
        this.ih = ih;
    }


    public Object clone() {
        try {
            return super.clone();
        } catch (CloneNotSupportedException e) {
            System.err.println(e);
            return null;
        }
    }


    public InstructionHandle getInstruction() {
        return ih;
    }


    public void setSourceLine( int src_line ) {
        this.src_line = src_line;
    }


    public int getSourceLine() {
        return src_line;
    }
}
