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

public class ExchangeBoundBody extends AMQMethodBodyImpl implements EncodableAMQDataBlock, AMQMethodBody
{

    public static final int CLASS_ID =  40;
    public static final int METHOD_ID = 22;

    // Fields declared in specification
    private final AMQShortString _exchange; // [exchange]
    private final AMQShortString _routingKey; // [routingKey]
    private final AMQShortString _queue; // [queue]

    public ExchangeBoundBody(
            AMQShortString exchange,
            AMQShortString routingKey,
            AMQShortString queue
                            )
    {
        _exchange = exchange;
        _routingKey = routingKey;
        _queue = queue;
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

    public final AMQShortString getExchange()
    {
        return _exchange;
    }
    public final AMQShortString getRoutingKey()
    {
        return _routingKey;
    }
    public final AMQShortString getQueue()
    {
        return _queue;
    }

    @Override
    protected int getBodySize()
    {
        int size = 0;
        size += getSizeOf( _exchange );
        size += getSizeOf( _routingKey );
        size += getSizeOf( _queue );
        return size;
    }

    @Override
    public void writeMethodPayload(QpidByteBuffer buffer)
    {
        writeAMQShortString( buffer, _exchange );
        writeAMQShortString( buffer, _routingKey );
        writeAMQShortString( buffer, _queue );
    }

    @Override
    public boolean execute(MethodDispatcher dispatcher, int channelId) throws QpidException
	{
        return dispatcher.dispatchExchangeBound(this, channelId);
	}

    @Override
    public String toString()
    {
        StringBuilder buf = new StringBuilder("[ExchangeBoundBodyImpl: ");
        buf.append( "exchange=" );
        buf.append(  getExchange() );
        buf.append( ", " );
        buf.append( "routingKey=" );
        buf.append(  getRoutingKey() );
        buf.append( ", " );
        buf.append( "queue=" );
        buf.append(  getQueue() );
        buf.append("]");
        return buf.toString();
    }

    public static void process(final QpidByteBuffer buffer,
                               final ServerChannelMethodProcessor dispatcher)
    {

        AMQShortString exchange = AMQShortString.readAMQShortString(buffer);
        AMQShortString routingKey = AMQShortString.readAMQShortString(buffer);
        AMQShortString queue = AMQShortString.readAMQShortString(buffer);
        if(!dispatcher.ignoreAllButCloseOk())
        {
            dispatcher.receiveExchangeBound(exchange, routingKey, queue);
        }
    }
}
