/*
 * Copyright 2017 dc-square GmbH
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

package com.hivemq.spi.services.exception;

/**
 * This exception is used to signal that a given MQTT ClientId is unknown to the broker in the given context.
 *
 * @author Georg Held
 * @since 3.3.0
 */
public class NoSuchClientIdException extends Exception {

    private final String clientId;
    private final boolean fillInStacktrace;

    /**
     * Creates a new NoSuchClientException.
     *
     * @param clientId         the not available MQTT ClientId.
     * @param fillInStacktrace whether the created exception should fill in a stacktrace.
     */
    public NoSuchClientIdException(final String clientId, final boolean fillInStacktrace) {
        this.clientId = clientId;
        this.fillInStacktrace = fillInStacktrace;
    }

    /**
     * Creates a new NoSuchClientException that will not contain a stacktrace.
     *
     * @param clientId the not available MQTT ClientId.
     */
    public NoSuchClientIdException(final String clientId) {
        this(clientId, false);
    }

    @Override
    public synchronized Throwable fillInStackTrace() {
        if (fillInStacktrace) {
            return super.fillInStackTrace();
        }
        return this;
    }

    /**
     * Returns the unknown MQTT ClientId.
     *
     * @return the not available MQTT ClientId.
     */
    public String getClientId() {
        return clientId;
    }
}
