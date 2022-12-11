/*
 *
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 *
 */

/*
 * This file is auto-generated by Qpid Gentools v.0.1 - do not modify.
 * Supported AMQP version:
 *   0-91
 */

package org.apache.qpid.server.protocol.v0_8.transport;

import org.apache.qpid.server.QpidException;
import org.apache.qpid.server.bytebuffer.QpidByteBuffer;
import org.apache.qpid.server.protocol.ProtocolVersion;

public class BasicRecoverSyncBody extends AMQMethodBodyImpl implements EncodableAMQDataBlock, AMQMethodBody
{
    public static final int CLASS_ID =  60;

    private final int _methodId;

    // Fields declared in specification
    private final byte _bitfield0; // [requeue]

    public BasicRecoverSyncBody(ProtocolVersion protocolVersion,
                                boolean requeue
                               )
    {
        _methodId = ProtocolVersion.v0_9.equals(protocolVersion) ? 102 : 110;

        byte bitfield0 = (byte)0;
        if( requeue )
        {
            bitfield0 = (byte) (((int) bitfield0) | (1 << 0));
        }
        _bitfield0 = bitfield0;
    }

    @Override
    public int getClazz()
    {
        return CLASS_ID;
    }

    @Override
    public int getMethod()
    {
        return _methodId;
    }

    public final boolean getRequeue()
    {
        return (((int)(_bitfield0)) & ( 1 << 0)) != 0;
    }

    @Override
    protected int getBodySize()
    {
        int size = 1;
        return size;
    }

    @Override
    public void writeMethodPayload(QpidByteBuffer buffer)
    {
        writeBitfield( buffer, _bitfield0 );
    }

    @Override
    public boolean execute(MethodDispatcher dispatcher, int channelId) throws QpidException
	{
        return dispatcher.dispatchBasicRecoverSync(this, channelId);
	}

    @Override
    public String toString()
    {
        StringBuilder buf = new StringBuilder("[BasicRecoverSyncBodyImpl: ");
        buf.append( "requeue=" );
        buf.append(  getRequeue() );
        buf.append("]");
        return buf.toString();
    }

    public static void process(final QpidByteBuffer in,
                               final ServerChannelMethodProcessor dispatcher)
    {
        boolean requeue = (in.get() & 0x01) == 0x01;
        if(!dispatcher.ignoreAllButCloseOk())
        {
            dispatcher.receiveBasicRecover(requeue, true);
        }
    }
}
