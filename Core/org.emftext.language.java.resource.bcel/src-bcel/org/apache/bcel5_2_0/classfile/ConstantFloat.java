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

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import org.apache.bcel5_2_0.Constants;

/** 
 * This class is derived from the abstract 
 * <A HREF="org.apache.bcel.classfile.Constant.html">Constant</A> class 
 * and represents a reference to a float object.
 *
 * @version $Id: ConstantFloat.java 386056 2006-03-15 11:31:56Z tcurdt $
 * @author  <A HREF="mailto:m.dahm@gmx.de">M. Dahm</A>
 * @see     Constant
 */
public final class ConstantFloat extends Constant implements ConstantObject {

    private float bytes;


    /** 
     * @param bytes Data
     */
    public ConstantFloat(float bytes) {
        super(Constants.CONSTANT_Float);
        this.bytes = bytes;
    }


    /**
     * Initialize from another object. Note that both objects use the same
     * references (shallow copy). Use clone() for a physical copy.
     */
    public ConstantFloat(ConstantFloat c) {
        this(c.getBytes());
    }


    /** 
     * Initialize instance from file data.
     *
     * @param file Input stream
     * @throws IOException
     */
    ConstantFloat(DataInputStream file) throws IOException {
        this(file.readFloat());
    }


    /**
     * Called by objects that are traversing the nodes of the tree implicitely
     * defined by the contents of a Java class. I.e., the hierarchy of methods,
     * fields, attributes, etc. spawns a tree of objects.
     *
     * @param v Visitor object
     */
    public void accept( Visitor v ) {
        v.visitConstantFloat(this);
    }


    /**
     * Dump constant float to file stream in binary format.
     *
     * @param file Output file stream
     * @throws IOException
     */
    public final void dump( DataOutputStream file ) throws IOException {
        file.writeByte(tag);
        file.writeFloat(bytes);
    }


    /**
     * @return data, i.e., 4 bytes.
     */
    public final float getBytes() {
        return bytes;
    }


    /**
     * @param bytes the raw bytes that represent this float
     */
    public final void setBytes( float bytes ) {
        this.bytes = bytes;
    }


    /**
     * @return String representation.
     */
    public final String toString() {
        return super.toString() + "(bytes = " + bytes + ")";
    }


    /** @return Float object
     */
    public Object getConstantValue( ConstantPool cp ) {
        return new Float(bytes);
    }
}
