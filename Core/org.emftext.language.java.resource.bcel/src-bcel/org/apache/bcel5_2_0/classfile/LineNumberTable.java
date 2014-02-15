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
 * This class represents a table of line numbers for debugging
 * purposes. This attribute is used by the <em>Code</em> attribute. It
 * contains pairs of PCs and line numbers.
 *
 * @version $Id: LineNumberTable.java 386056 2006-03-15 11:31:56Z tcurdt $
 * @author  <A HREF="mailto:m.dahm@gmx.de">M. Dahm</A>
 * @see     Code
 * @see LineNumber
 */
public final class LineNumberTable extends Attribute {

    private int line_number_table_length;
    private LineNumber[] line_number_table; // Table of line/numbers pairs


    /*
     * Initialize from another object. Note that both objects use the same
     * references (shallow copy). Use copy() for a physical copy.
     */
    public LineNumberTable(LineNumberTable c) {
        this(c.getNameIndex(), c.getLength(), c.getLineNumberTable(), c.getConstantPool());
    }


    /*
     * @param name_index Index of name
     * @param length Content length in bytes
     * @param line_number_table Table of line/numbers pairs
     * @param constant_pool Array of constants
     */
    public LineNumberTable(int name_index, int length, LineNumber[] line_number_table,
            ConstantPool constant_pool) {
        super(Constants.ATTR_LINE_NUMBER_TABLE, name_index, length, constant_pool);
        setLineNumberTable(line_number_table);
    }


    /**
     * Construct object from file stream.
     * @param name_index Index of name
     * @param length Content length in bytes
     * @param file Input stream
     * @param constant_pool Array of constants
     * @throws IOException
     */
    LineNumberTable(int name_index, int length, DataInputStream file, ConstantPool constant_pool)
            throws IOException {
        this(name_index, length, (LineNumber[]) null, constant_pool);
        line_number_table_length = (file.readUnsignedShort());
        line_number_table = new LineNumber[line_number_table_length];
        for (int i = 0; i < line_number_table_length; i++) {
            line_number_table[i] = new LineNumber(file);
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
        v.visitLineNumberTable(this);
    }


    /**
     * Dump line number table attribute to file stream in binary format.
     *
     * @param file Output file stream
     * @throws IOException
     */
    public final void dump( DataOutputStream file ) throws IOException {
        super.dump(file);
        file.writeShort(line_number_table_length);
        for (int i = 0; i < line_number_table_length; i++) {
            line_number_table[i].dump(file);
        }
    }


    /**
     * @return Array of (pc offset, line number) pairs.
     */
    public final LineNumber[] getLineNumberTable() {
        return line_number_table;
    }


    /**
     * @param line_number_table the line number entries for this table
     */
    public final void setLineNumberTable( LineNumber[] line_number_table ) {
        this.line_number_table = line_number_table;
        line_number_table_length = (line_number_table == null) ? 0 : line_number_table.length;
    }


    /**
     * @return String representation.
     */
    public final String toString() {
    	StringBuilder buf = new StringBuilder();
    	StringBuilder line = new StringBuilder();
        String newLine = System.getProperty("line.separator", "\n");
        for (int i = 0; i < line_number_table_length; i++) {
            line.append(line_number_table[i].toString());
            if (i < line_number_table_length - 1) {
                line.append(", ");
            }
            if (line.length() > 72) {
                line.append(newLine);
                buf.append(line.toString());
                line.setLength(0);
            }
        }
        buf.append(line);
        return buf.toString();
    }


    /**
     * Map byte code positions to source code lines.
     *
     * @param pos byte code offset
     * @return corresponding line in source code
     */
    public int getSourceLine( int pos ) {
        int l = 0, r = line_number_table_length - 1;
        if (r < 0) {
            return -1;
        }
        int min_index = -1, min = -1;
        /* Do a binary search since the array is ordered.
         */
        do {
            int i = (l + r) / 2;
            int j = line_number_table[i].getStartPC();
            if (j == pos) {
                return line_number_table[i].getLineNumber();
            } else if (pos < j) {
                r = i - 1;
            } else {
                l = i + 1;
            }
            /* If exact match can't be found (which is the most common case)
             * return the line number that corresponds to the greatest index less
             * than pos.
             */
            if (j < pos && j > min) {
                min = j;
                min_index = i;
            }
        } while (l <= r);
        /* It's possible that we did not find any valid entry for the bytecode
         * offset we were looking for.
         */
        if (min_index < 0) {
            return -1;
        }
        return line_number_table[min_index].getLineNumber();
    }


    /**
     * @return deep copy of this attribute
     */
    public Attribute copy( ConstantPool _constant_pool ) {
        LineNumberTable c = (LineNumberTable) clone();
        c.line_number_table = new LineNumber[line_number_table_length];
        for (int i = 0; i < line_number_table_length; i++) {
            c.line_number_table[i] = line_number_table[i].copy();
        }
        c.constant_pool = _constant_pool;
        return c;
    }


    public final int getTableLength() {
        return line_number_table_length;
    }
}
