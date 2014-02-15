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
package org.apache.bcel5_2_0.util;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;

/**
 * Utility class that implements a sequence of bytes which can be read
 * via the `readByte()' method. This is used to implement a wrapper for the 
 * Java byte code stream to gain some more readability.
 *
 * @version $Id: ByteSequence.java 386056 2006-03-15 11:31:56Z tcurdt $
 * @author  <A HREF="mailto:m.dahm@gmx.de">M. Dahm</A>
 */
public final class ByteSequence extends DataInputStream {

    private ByteArrayStream byte_stream;


    public ByteSequence(byte[] bytes) {
        super(new ByteArrayStream(bytes));
        byte_stream = (ByteArrayStream) in;
    }


    public final int getIndex() {
        return byte_stream.getPosition();
    }


    final void unreadByte() {
        byte_stream.unreadByte();
    }

    private static final class ByteArrayStream extends ByteArrayInputStream {

        ByteArrayStream(byte[] bytes) {
            super(bytes);
        }


        final int getPosition() {
            return pos;
        } // is protected in ByteArrayInputStream


        final void unreadByte() {
            if (pos > 0) {
                pos--;
            }
        }
    }
}
