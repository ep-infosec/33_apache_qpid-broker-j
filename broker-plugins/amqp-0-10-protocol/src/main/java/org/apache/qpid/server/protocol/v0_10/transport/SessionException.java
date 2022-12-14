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
package org.apache.qpid.server.protocol.v0_10.transport;

import org.apache.qpid.server.transport.TransportException;

/**
 * SessionException
 *
 */

public class SessionException extends TransportException
{

    private final ExecutionException exception;

    public SessionException(String message, ExecutionException exception, Throwable cause)
    {
        super(message, cause);
        this.exception = exception;
    }

    public SessionException(ExecutionException exception)
    {
        this(String.valueOf(exception), exception, null);
    }

    public SessionException(String message)
    {
        this(message, null, null);
    }

    public ExecutionException getException()
    {
        return exception;
    }

    @Override public void rethrow()
    {
        throw new SessionException(getMessage(), exception, this);
    }

}
