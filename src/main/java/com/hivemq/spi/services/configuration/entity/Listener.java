/*
 * Copyright 2015 dc-square GmbH
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.hivemq.spi.services.configuration.entity;

import com.google.common.base.Optional;

/**
 * A marker interface for a listener. Any listener implementation must
 * implement this interface.
 *
 * @author Dominik Obermaier
 * @author Christoph Schaebel
 * @author Georg Held
 * @see TcpListener
 * @see TlsTcpListener
 * @see WebsocketListener
 * @see TlsWebsocketListener
 */
public interface Listener {

    /**
     * @return the port of the listener
     */
    int getPort();

    /**
     * @return the bind address of a listener
     */
    String getBindAddress();

    /**
     * @return the human readable, name of the listener
     */
    String readableName();

    /**
     * @return if the PROXY protocol is supported by this listener
     * @since 3.2
     */
    boolean isProxyProtocolSupported();

    /**
     * @return an {@link Optional} of {@link SocketOptionsProperties} of this listener
     */
    Optional<SocketOptionsProperties> getSocketOptionsProperties();

    /**
     * @return an {@link Optional} of {@link ConnectOverloadProtectionProperties} of this listener
     */
    Optional<ConnectOverloadProtectionProperties> getConnectOverloadProtectionProperties();

    /**
     * @return an {@link Optional} of {@link ClientWriteBufferProperties} of this listener
     */
    Optional<ClientWriteBufferProperties> getClientWriteBufferProperties();
}
