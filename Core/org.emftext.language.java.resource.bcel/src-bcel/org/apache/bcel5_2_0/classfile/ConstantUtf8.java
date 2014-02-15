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
 * and represents a reference to a Utf8 encoded string.
 *
 * @version $Id: ConstantUtf8.java 386056 2006-03-15 11:31:56Z tcurdt $
 * @author  <A HREF="mailto:m.dahm@gmx.de">M. Dahm</A>
 * @see     Constant
 */
public final class ConstantUtf8 extends Constant {

    private String bytes;


    /**
     * Initialize from another object.
     */
    public ConstantUtf8(ConstantUtf8 c) {
        this(c.getBytes());
    }


    /**
     * Initialize instance from file data.
     *
     * @param file Input stream
     * @throws IOException
     */
    ConstantUtf8(DataInputStream file) throws IOException {
        super(Constants.CONSTANT_Utf8);
        bytes = file.readUTF();
    }


    /**
     * @param bytes Data
     */
    public ConstantUtf8(String bytes) {
        super(Constants.CONSTANT_Utf8);
        if (bytes == null) {
            throw new IllegalArgumentException("bytes must not be null!");
        }
        this.bytes = bytes;
    }


    /**
     * Called by objects that are traversing the nodes of the tree implicitely
     * defined by the contents of a Java class. I.e., the hierarchy of methods,
     * fields, attributes, etc. spawns a tree of objects.
     *
     * @param v Visitor object
     */
    public void accept( Visitor v ) {
        v.visitConstantUtf8(this);
    }


    /**
     * Dump String in Utf8 format to file stream.
     *
     * @param file Output file stream
     * @throws IOException
     */
    public final void dump( DataOutputStream file ) throws IOException {
        file.writeByte(tag);
        file.writeUTF(bytes);
    }


    /**
     * @return Data converted to string.
     */
    public final String getBytes() {
        return bytes;
    }


    /**
     * @param bytes the raw bytes of this Utf-8
     */
    public final void setBytes( String bytes ) {
        this.bytes = bytes;
    }


    /**
     * @return String representation
     */
    public final String toString() {
        return super.toString() + "(\"" + Utility.replace(bytes, "\n", "\\n") + "\")";
    }
}
