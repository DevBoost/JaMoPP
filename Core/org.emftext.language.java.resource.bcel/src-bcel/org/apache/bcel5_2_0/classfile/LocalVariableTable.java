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
 * This class represents colection of local variables in a
 * method. This attribute is contained in the <em>Code</em> attribute.
 *
 * @version $Id: LocalVariableTable.java 386056 2006-03-15 11:31:56Z tcurdt $
 * @author  <A HREF="mailto:m.dahm@gmx.de">M. Dahm</A>
 * @see     Code
 * @see LocalVariable
 */
public class LocalVariableTable extends Attribute {

    private int local_variable_table_length; // Table of local
    private LocalVariable[] local_variable_table; // variables


    /**
     * Initialize from another object. Note that both objects use the same
     * references (shallow copy). Use copy() for a physical copy.
     */
    public LocalVariableTable(LocalVariableTable c) {
        this(c.getNameIndex(), c.getLength(), c.getLocalVariableTable(), c.getConstantPool());
    }


    /**
     * @param name_index Index in constant pool to `LocalVariableTable'
     * @param length Content length in bytes
     * @param local_variable_table Table of local variables
     * @param constant_pool Array of constants
     */
    public LocalVariableTable(int name_index, int length, LocalVariable[] local_variable_table,
            ConstantPool constant_pool) {
        super(Constants.ATTR_LOCAL_VARIABLE_TABLE, name_index, length, constant_pool);
        setLocalVariableTable(local_variable_table);
    }


    /**
     * Construct object from file stream.
     * @param name_index Index in constant pool
     * @param length Content length in bytes
     * @param file Input stream
     * @param constant_pool Array of constants
     * @throws IOException
     */
    LocalVariableTable(int name_index, int length, DataInputStream file, ConstantPool constant_pool)
            throws IOException {
        this(name_index, length, (LocalVariable[]) null, constant_pool);
        local_variable_table_length = (file.readUnsignedShort());
        local_variable_table = new LocalVariable[local_variable_table_length];
        for (int i = 0; i < local_variable_table_length; i++) {
            local_variable_table[i] = new LocalVariable(file, constant_pool);
        }
    }


    /**
     * Called by objects that are traversing the nodes of the tree implicitely
     * defined by the contents of a Java class. I.e., the hierarchy of methods,
     * fields, attributes, etc. spawns a tree of objects.
     *
     * @param v Visitor object
     */
    public void accept( Visitor v ) {
        v.visitLocalVariableTable(this);
    }


    /**
     * Dump local variable table attribute to file stream in binary format.
     *
     * @param file Output file stream
     * @throws IOException
     */
    public final void dump( DataOutputStream file ) throws IOException {
        super.dump(file);
        file.writeShort(local_variable_table_length);
        for (int i = 0; i < local_variable_table_length; i++) {
            local_variable_table[i].dump(file);
        }
    }


    /**
     * @return Array of local variables of method.
     */
    public final LocalVariable[] getLocalVariableTable() {
        return local_variable_table;
    }


    /** 
     * @return first matching variable using index
     * 
     * @param index the variable slot
     * 
     * @return the first LocalVariable that matches the slot or null if not found
     * 
     * @deprecated since 5.2 because multiple variables can share the
     *             same slot, use getLocalVariable(int index, int pc) instead.
     */
    public final LocalVariable getLocalVariable( int index ) {
        for (int i = 0; i < local_variable_table_length; i++) {
            if (local_variable_table[i].getIndex() == index) {
                return local_variable_table[i];
            }
        }
        return null;
    }


    /** 
     * @return matching variable using index when variable is used at supplied pc
     * 
     * @param index the variable slot
     * @param pc the current pc that this variable is alive
     * 
     * @return the LocalVariable that matches or null if not found
     */
    public final LocalVariable getLocalVariable( int index, int pc ) {
        for (int i = 0; i < local_variable_table_length; i++) {
            if (local_variable_table[i].getIndex() == index) {
                int start_pc = local_variable_table[i].getStartPC();
                int end_pc = start_pc + local_variable_table[i].getLength();
                if ((pc >= start_pc) && (pc < end_pc)) {
                    return local_variable_table[i];
                }
            }
        }
        return null;
    }


    public final void setLocalVariableTable( LocalVariable[] local_variable_table ) {
        this.local_variable_table = local_variable_table;
        local_variable_table_length = (local_variable_table == null)
                ? 0
                : local_variable_table.length;
    }


    /**
     * @return String representation.
     */
    public final String toString() {
    	StringBuilder buf = new StringBuilder("");
        for (int i = 0; i < local_variable_table_length; i++) {
            buf.append(local_variable_table[i].toString());
            if (i < local_variable_table_length - 1) {
                buf.append('\n');
            }
        }
        return buf.toString();
    }


    /**
     * @return deep copy of this attribute
     */
    public Attribute copy( ConstantPool _constant_pool ) {
        LocalVariableTable c = (LocalVariableTable) clone();
        c.local_variable_table = new LocalVariable[local_variable_table_length];
        for (int i = 0; i < local_variable_table_length; i++) {
            c.local_variable_table[i] = local_variable_table[i].copy();
        }
        c.constant_pool = _constant_pool;
        return c;
    }


    public final int getTableLength() {
        return local_variable_table_length;
    }
}
