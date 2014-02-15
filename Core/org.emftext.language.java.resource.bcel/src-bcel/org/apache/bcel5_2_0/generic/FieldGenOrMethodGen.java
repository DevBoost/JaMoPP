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

import java.util.ArrayList;
import java.util.List;

import org.apache.bcel5_2_0.Constants;
import org.apache.bcel5_2_0.classfile.AccessFlags;
import org.apache.bcel5_2_0.classfile.Attribute;

/**
 * Super class for FieldGen and MethodGen objects, since they have
 * some methods in common!
 *
 * @version $Id: FieldGenOrMethodGen.java 410087 2006-05-29 12:12:19Z tcurdt $
 * @author  <A HREF="mailto:m.dahm@gmx.de">M. Dahm</A>
 */
public abstract class FieldGenOrMethodGen extends AccessFlags implements NamedAndTyped, Cloneable {

    protected String name;
    protected Type type;
    protected ConstantPoolGen cp;
    private List attribute_vec = new ArrayList();


    protected FieldGenOrMethodGen() {
    }


    public void setType( Type type ) {
        if (type.getType() == Constants.T_ADDRESS) {
            throw new IllegalArgumentException("Type can not be " + type);
        }
        this.type = type;
    }


    public Type getType() {
        return type;
    }


    /** @return name of method/field.
     */
    public String getName() {
        return name;
    }


    public void setName( String name ) {
        this.name = name;
    }


    public ConstantPoolGen getConstantPool() {
        return cp;
    }


    public void setConstantPool( ConstantPoolGen cp ) {
        this.cp = cp;
    }


    /**
     * Add an attribute to this method. Currently, the JVM knows about
     * the `Code', `ConstantValue', `Synthetic' and `Exceptions'
     * attributes. Other attributes will be ignored by the JVM but do no
     * harm.
     *
     * @param a attribute to be added
     */
    public void addAttribute( Attribute a ) {
        attribute_vec.add(a);
    }


    /**
     * Remove an attribute.
     */
    public void removeAttribute( Attribute a ) {
        attribute_vec.remove(a);
    }


    /**
     * Remove all attributes.
     */
    public void removeAttributes() {
        attribute_vec.clear();
    }


    /**
     * @return all attributes of this method.
     */
    public Attribute[] getAttributes() {
        Attribute[] attributes = new Attribute[attribute_vec.size()];
        attribute_vec.toArray(attributes);
        return attributes;
    }


    /** @return signature of method/field.
     */
    public abstract String getSignature();


    public Object clone() {
        try {
            return super.clone();
        } catch (CloneNotSupportedException e) {
            System.err.println(e);
            return null;
        }
    }
}
