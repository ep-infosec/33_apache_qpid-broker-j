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
 *   8-0
 */

package org.apache.qpid.server.protocol.v0_8.transport;

import org.apache.qpid.server.QpidException;
import org.apache.qpid.server.bytebuffer.QpidByteBuffer;
import org.apache.qpid.server.protocol.v0_8.AMQShortString;

public class BasicDeliverBody extends AMQMethodBodyImpl implements EncodableAMQDataBlock, AMQMethodBody
{

    public static final int CLASS_ID =  60;
    public static final int METHOD_ID = 60;

    // Fields declared in specification
    private final AMQShortString _consumerTag; // [consumerTag]
    private final long _deliveryTag; // [deliveryTag]
    private final byte _bitfield0; // [redelivered]
    private final AMQShortString _exchange; // [exchange]
    private final AMQShortString _routingKey; // [routingKey]

    public BasicDeliverBody(
            AMQShortString consumerTag,
            long deliveryTag,
            boolean redelivered,
            AMQShortString exchange,
            AMQShortString routingKey
                           )
    {
        _consumerTag = consumerTag;
        _deliveryTag = deliveryTag;
        byte bitfield0 = (byte)0;
        if( redelivered )
        {
            bitfield0 = (byte) (((int) bitfield0) | (1 << 0));
        }

        _bitfield0 = bitfield0;
        _exchange = exchange;
        _routingKey = routingKey;
    }

    @Override
    public int getClazz()
    {
        return CLASS_ID;
    }

    @Override
    public int getMethod()
    {
        return METHOD_ID;
    }

    public final AMQShortString getConsumerTag()
    {
        return _consumerTag;
    }
    public final long getDeliveryTag()
    {
        return _deliveryTag;
    }
    public final boolean getRedelivered()
    {
        return (((int)(_bitfield0)) & ( 1 << 0)) != 0;
    }
    public final AMQShortString getExchange()
    {
        return _exchange;
    }
    public final AMQShortString getRoutingKey()
    {
        return _routingKey;
    }

    @Override
    protected int getBodySize()
    {
        int size = 9;
        size += getSizeOf( _consumerTag );
        size += getSizeOf( _exchange );
        size += getSizeOf( _routingKey );
        return size;
    }

    @Override
    public void writeMethodPayload(QpidByteBuffer buffer)
    {
        writeAMQShortString( buffer, _consumerTag );
        writeLong( buffer, _deliveryTag );
        writeBitfield( buffer, _bitfield0 );
        writeAMQShortString( buffer, _exchange );
        writeAMQShortString( buffer, _routingKey );
    }

    @Override
    public boolean execute(MethodDispatcher dispatcher, int channelId) throws QpidException
	{
        return dispatcher.dispatchBasicDeliver(this, channelId);
	}

    @Override
    public String toString()
    {
        StringBuilder buf = new StringBuilder("[BasicDeliverBodyImpl: ");
        buf.append( "consumerTag=" );
        buf.append(  getConsumerTag() );
        buf.append( ", " );
        buf.append( "deliveryTag=" );
        buf.append(  getDeliveryTag() );
        buf.append( ", " );
        buf.append( "redelivered=" );
        buf.append(  getRedelivered() );
        buf.append( ", " );
        buf.append( "exchange=" );
        buf.append(  getExchange() );
        buf.append( ", " );
        buf.append( "routingKey=" );
        buf.append(  getRoutingKey() );
        buf.append("]");
        return buf.toString();
    }

    public static void process(final QpidByteBuffer buffer,
                               final ClientChannelMethodProcessor dispatcher)
    {

        AMQShortString consumerTag = AMQShortString.readAMQShortString(buffer);
        long deliveryTag = buffer.getLong();
        boolean redelivered = (buffer.get() & 0x01) != 0;
        AMQShortString exchange = AMQShortString.readAMQShortString(buffer);
        AMQShortString routingKey = AMQShortString.readAMQShortString(buffer);
        if(!dispatcher.ignoreAllButCloseOk())
        {
            dispatcher.receiveBasicDeliver(consumerTag, deliveryTag, redelivered, exchange, routingKey);
        }
    }
}
