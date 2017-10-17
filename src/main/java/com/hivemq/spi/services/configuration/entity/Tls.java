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

import com.hivemq.spi.annotations.Immutable;
import com.hivemq.spi.annotations.NotNull;
import com.hivemq.spi.annotations.Nullable;

import java.util.List;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * The TLS configuration
 *
 * @author Dominik Obermaier
 * @author Christoph Schäbel
 * @since 3.0
 */
@Immutable
public class Tls {


    /**
     * The X509 client certificate authentication mode.
     */
    public enum ClientAuthMode {
        /**
         * Clients are not allowed to send X509 client certificates
         */
        NONE("none"),
        /**
         * Clients can send X509 client certificates but they're not required to do so
         */
        OPTIONAL("optional"),
        /**
         * Clients must send X509 client certificates
         */
        REQUIRED("required");
        private final String clientAuthMode;

        ClientAuthMode(final String clientAuthMode) {

            this.clientAuthMode = clientAuthMode;
        }

        /**
         * @return the client authentication mode
         */
        public String getClientAuthMode() {
            return clientAuthMode;
        }

        @Override
        public String toString() {
            return clientAuthMode;
        }
    }

    private final String keystorePath;
    private final String keystorePassword;
    private final String keystoreType;
    private final String privateKeyPassword;
    private final String truststorePath;
    private final String truststorePassword;
    private final String truststoreType;
    private final Integer handshakeTimeout;

    private final ClientAuthMode clientAuthMode;

    private final List<String> protocols;

    private final List<String> cipherSuites;

    private final Long concurrentHandshakeLimit;
    private final boolean nativeSsl;

    /**
     * Enables OCSP stapling.
     */
    private boolean ocspStaplingEnabled;

    /**
     * ocspOverrideUrl = URL to OCSP responder.
     */
    private final String ocspOverrideUrl;

    /**
     * Interval in seconds to cache the OCSP response cyclically.
     * default: 3600 -> 1h
     */
    private final Integer ocspCacheInterval;

    /**
     * Creates a new TLS configuration
     *
     * @param keystorePath             the path to the keystore
     * @param keystorePassword         the password for the keystore
     * @param keystoreType             the keystore type. When in doubt, use <b>JKS</b>
     * @param privateKeyPassword       the password to the private key
     * @param truststorePath           the path to the truststore
     * @param truststorePassword       the password for the truststore
     * @param truststoreType           the truststore type. When in doubt, use <b>JKS</b>
     * @param handshakeTimeout         the TLS handshake timeout
     * @param clientAuthMode           the client authentication mode
     * @param protocols                the supported protocols. <code>null</code> means that all enabled protocols by the JVM are
     *                                 enabled
     * @param cipherSuites             the supported cipher suites. <code>null</code> means that all enabled cipher suites by the
     *                                 JVM are enabled
     * @param concurrentHandshakeLimit the maximum number of concurrent TLS handshakes that HiveMQ allows at any time
     * @param nativeSSl                use the native SSL implementation
     * @param ocspStaplingEnabled      enable OCSP stapling
     * @param ocspOverrideUrl          overrides the URL of the OCSP-Responder contained in the server certificate. Can also be set if there is no OCSP URL information in the server certificate
     * @param ocspCacheInterval        interval in seconds to cache the OCSP response cyclically
     * @since 3.3
     */
    public Tls(@NotNull final String keystorePath,
               @NotNull final String keystorePassword, @NotNull final String keystoreType,
               @NotNull final String privateKeyPassword,
               @Nullable final String truststorePath,
               @Nullable final String truststorePassword,
               @Nullable final String truststoreType, final int handshakeTimeout,
               @NotNull final ClientAuthMode clientAuthMode,
               @NotNull final List<String> protocols, @NotNull final List<String> cipherSuites,
               @Nullable final Long concurrentHandshakeLimit,
               final boolean nativeSSl,
               final boolean ocspStaplingEnabled,
               @Nullable final String ocspOverrideUrl,
               final int ocspCacheInterval) {

        checkNotNull(clientAuthMode, "clientAuthMode must not be null");
        checkNotNull(protocols, "protocols must not be null");
        checkNotNull(cipherSuites, "cipher suites must not be null");
        this.keystorePath = keystorePath;
        this.keystorePassword = keystorePassword;
        this.keystoreType = keystoreType;
        this.privateKeyPassword = privateKeyPassword;
        this.truststorePath = truststorePath;
        this.truststorePassword = truststorePassword;
        this.truststoreType = truststoreType;
        this.handshakeTimeout = handshakeTimeout;
        this.clientAuthMode = clientAuthMode;
        this.protocols = protocols;
        this.cipherSuites = cipherSuites;
        this.concurrentHandshakeLimit = concurrentHandshakeLimit;
        this.nativeSsl = nativeSSl;
        this.ocspStaplingEnabled = ocspStaplingEnabled;
        this.ocspOverrideUrl = ocspOverrideUrl;
        this.ocspCacheInterval = ocspCacheInterval;
    }

    /**
     * Creates a new TLS configuration
     *
     * @param keystorePath          the path to the keystore
     * @param keystorePassword      the password for the keystore
     * @param keystoreType          the keystore type. When in doubt, use <b>JKS</b>
     * @param privateKeyPassword    the password to the private key
     * @param truststorePath        the path to the truststore
     * @param truststorePassword    the password for the truststore
     * @param truststoreType        the truststore type. When in doubt, use <b>JKS</b>
     * @param handshakeTimeout      the TLS handshake timeout
     * @param clientAuthMode        the client authentication mode
     * @param protocols             the supported protocols. <code>null</code> means that all enabled protocols by the JVM are
     *                              enabled
     * @param cipherSuites          the supported cipher suites. <code>null</code> means that all enabled cipher suites by the
     *                              JVM are enabled
     */
    public Tls(@NotNull final String keystorePath,
               @NotNull final String keystorePassword, @NotNull final String keystoreType,
               final String privateKeyPassword,
               @Nullable final String truststorePath,
               @Nullable final String truststorePassword,
               @Nullable final String truststoreType, final int handshakeTimeout,
               @NotNull final ClientAuthMode clientAuthMode,
               @Nullable final List<String> protocols, @NotNull final List<String> cipherSuites) {
        this(keystorePath,
                keystorePassword,
                keystoreType,
                privateKeyPassword,
                truststorePath,
                truststorePassword,
                truststoreType,
                handshakeTimeout,
                clientAuthMode,
                protocols,
                cipherSuites,
                null,
                false,
                false,
                null,
                3600);
    }

    /**
     * @return the keystore path
     */
    public String getKeystorePath() {
        return keystorePath;
    }

    /**
     * @return the keystore password
     */
    public String getKeystorePassword() {
        return keystorePassword;
    }

    /**
     * @return the keystore type
     */
    public String getKeystoreType() {
        return keystoreType;
    }

    /**
     * @return the password of the private key
     */
    public String getPrivateKeyPassword() {
        return privateKeyPassword;
    }

    /**
     * @return the truststore path
     */
    @Nullable
    public String getTruststorePath() {
        return truststorePath;
    }

    /**
     * @return the truststore password
     */
    @Nullable
    public String getTruststorePassword() {
        return truststorePassword;
    }

    /**
     * @return the truststore type
     */
    @Nullable
    public String getTruststoreType() {
        return truststoreType;
    }

    /**
     * @return the TLS handshake timeout
     */
    public int getHandshakeTimeout() {
        return handshakeTimeout;
    }

    /**
     * @return the client authentication mode
     */
    public ClientAuthMode getClientAuthMode() {
        return clientAuthMode;
    }

    /**
     * @return the enabled TLS protocols
     */
    public List<String> getProtocols() {
        return protocols;
    }

    /**
     * @return the enabled cipher suites
     */
    public List<String> getCipherSuites() {
        return cipherSuites;
    }

    /**
     * @return the concurrentHandshakeLimit or {@code null} if none is set
     */
    @Nullable
    public Long getConcurrentHandshakeLimit() {
        return concurrentHandshakeLimit;
    }

    /**
     * @return is native SSL used for this listener
     */
    public boolean isNativeSsl() {
        return nativeSsl;
    }

    /**
     * @return is OCSP stapling used for this listener
     */
    public boolean isOcspStaplingEnabled() {
        return ocspStaplingEnabled;
    }

    /**
     * ocspOverrideUrl = URL to OCSP responder.
     * @return the ocspOverrideUrl
     */
    public String getOcspOverrideUrl() {
        return ocspOverrideUrl;
    }

    /**
     * @return the cache interval for OCSP caching
     */
    public int getOcspCacheInterval() {
        return ocspCacheInterval;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Tls tls = (Tls) o;

        if (nativeSsl != tls.nativeSsl) return false;
        if (keystorePath != null ? !keystorePath.equals(tls.keystorePath) : tls.keystorePath != null) return false;
        if (keystorePassword != null ? !keystorePassword.equals(tls.keystorePassword) : tls.keystorePassword != null)
            return false;
        if (keystoreType != null ? !keystoreType.equals(tls.keystoreType) : tls.keystoreType != null) return false;
        if (privateKeyPassword != null ? !privateKeyPassword.equals(tls.privateKeyPassword) : tls.privateKeyPassword != null)
            return false;
        if (truststorePath != null ? !truststorePath.equals(tls.truststorePath) : tls.truststorePath != null)
            return false;
        if (truststorePassword != null ? !truststorePassword.equals(tls.truststorePassword) : tls.truststorePassword != null)
            return false;
        if (truststoreType != null ? !truststoreType.equals(tls.truststoreType) : tls.truststoreType != null)
            return false;
        if (handshakeTimeout != null ? !handshakeTimeout.equals(tls.handshakeTimeout) : tls.handshakeTimeout != null)
            return false;
        if (clientAuthMode != tls.clientAuthMode) return false;
        if (protocols != null ? !protocols.equals(tls.protocols) : tls.protocols != null) return false;
        if (cipherSuites != null ? !cipherSuites.equals(tls.cipherSuites) : tls.cipherSuites != null) return false;
        if (concurrentHandshakeLimit != null ? !concurrentHandshakeLimit.equals(tls.concurrentHandshakeLimit) : tls.concurrentHandshakeLimit != null)
            return false;
        if (ocspStaplingEnabled != tls.ocspStaplingEnabled) return false;
        if (ocspOverrideUrl != null ? !ocspOverrideUrl.equals(tls.ocspOverrideUrl) : tls.ocspOverrideUrl != null)
            return false;
        return ocspCacheInterval != null ? ocspCacheInterval.equals(tls.ocspCacheInterval) : tls.ocspCacheInterval == null;
    }

    @Override
    public int hashCode() {
        int result = keystorePath != null ? keystorePath.hashCode() : 0;
        result = 31 * result + (keystorePassword != null ? keystorePassword.hashCode() : 0);
        result = 31 * result + (keystoreType != null ? keystoreType.hashCode() : 0);
        result = 31 * result + (privateKeyPassword != null ? privateKeyPassword.hashCode() : 0);
        result = 31 * result + (truststorePath != null ? truststorePath.hashCode() : 0);
        result = 31 * result + (truststorePassword != null ? truststorePassword.hashCode() : 0);
        result = 31 * result + (truststoreType != null ? truststoreType.hashCode() : 0);
        result = 31 * result + (handshakeTimeout != null ? handshakeTimeout.hashCode() : 0);
        result = 31 * result + (clientAuthMode != null ? clientAuthMode.hashCode() : 0);
        result = 31 * result + (protocols != null ? protocols.hashCode() : 0);
        result = 31 * result + (cipherSuites != null ? cipherSuites.hashCode() : 0);
        result = 31 * result + (concurrentHandshakeLimit != null ? concurrentHandshakeLimit.hashCode() : 0);
        result = 31 * result + (nativeSsl ? 1 : 0);
        result = 31 * result + (ocspStaplingEnabled ? 1 : 0);
        result = 31 * result + (ocspOverrideUrl != null ? ocspOverrideUrl.hashCode() : 0);
        result = 31 * result + (ocspCacheInterval != null ? ocspCacheInterval.hashCode() : 0);
        return result;
    }
}
