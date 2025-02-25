/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.dubbo.config;

import org.apache.dubbo.common.serialization.PreferSerializationProvider;
import org.apache.dubbo.common.utils.CollectionUtils;
import org.apache.dubbo.common.utils.StringUtils;
import org.apache.dubbo.config.nested.TripleConfig;
import org.apache.dubbo.config.support.Nested;
import org.apache.dubbo.config.support.Parameter;
import org.apache.dubbo.rpc.model.ApplicationModel;

import java.lang.reflect.Field;
import java.util.Map;
import java.util.Optional;

import static org.apache.dubbo.common.constants.CommonConstants.DUBBO_PROTOCOL;
import static org.apache.dubbo.common.constants.CommonConstants.JSON_CHECK_LEVEL_KEY;
import static org.apache.dubbo.common.constants.CommonConstants.SSL_ENABLED_KEY;
import static org.apache.dubbo.common.constants.CommonConstants.THREAD_POOL_EXHAUSTED_LISTENERS_KEY;
import static org.apache.dubbo.common.constants.LoggerCodeConstants.COMMON_UNEXPECTED_EXCEPTION;

/**
 * Configuration for the protocol.
 */
public class ProtocolConfig extends AbstractConfig {

    private static final long serialVersionUID = 6913423882496634749L;

    /**
     * The name of the protocol.
     */
    private String name;

    /**
     * The service's IP address (useful when there are multiple network cards available).
     */
    private String host;

    /**
     * The service's port number.
     */
    private Integer port;

    /**
     * The context path for the service.
     */
    private String contextpath;

    /**
     * The name of the thread pool.
     */
    private String threadpool;

    /**
     * The core thread size of the thread pool.
     */
    private Integer corethreads;

    /**
     * The fixed size of the thread pool.
     */
    private Integer threads;

    /**
     * The fixed size of the IO thread pool.
     */
    private Integer iothreads;

    /**
     * The keep-alive time for threads in the thread pool (default unit is TimeUnit.MILLISECONDS).
     */
    private Integer alive;

    /**
     * The length of the thread pool's queue.
     */
    private Integer queues;

    /**
     * Listeners for exhausted thread pool.
     */
    private String threadPoolExhaustedListeners;

    /**
     * The maximum acceptable connections.
     */
    private Integer accepts;

    /**
     * The protocol codec.
     */
    private String codec;

    /**
     * The serialization method.
     */
    private String serialization;

    /**
     * Specifies the preferred serialization method for the consumer.
     *  If specified, the consumer will use this parameter first.
     * If the Dubbo Sdk you are using contains the serialization type, the serialization method specified by the argument is used.
     * <p>
     * When this parameter is null or the serialization type specified by this parameter does not exist in the Dubbo SDK, the serialization type specified by serialization is used.
     * If the Dubbo SDK if still does not exist, the default type of the Dubbo SDK is used.
     * For Dubbo SDK >= 3.2, <code>preferSerialization</code> takes precedence over <code>serialization</code>
     * <p>
     * Supports multiple values separated by commas, e.g., "fastjson2,fastjson,hessian2".
     */
    private String preferSerialization; // default:fastjson2,hessian2

    /**
     * The character set used for communication.
     */
    private String charset;

    /**
     * The maximum payload length.
     */
    private Integer payload;

    /**
     * The buffer size.
     */
    private Integer buffer;

    /**
     * The interval for sending heartbeats.
     */
    private Integer heartbeat;

    /**
     * The access log configuration.
     */
    private String accesslog;

    /**
     * The transporter used for communication.
     */
    private String transporter;

    /**
     * The method of information exchange.
     */
    private String exchanger;

    /**
     * The thread dispatch mode.
     */
    private String dispatcher;

    /**
     * The networker implementation.
     */
    private String networker;

    /**
     * The server implementation.
     */
    private String server;

    /**
     * The client implementation.
     */
    private String client;

    /**
     * Supported Telnet commands, separated by commas.
     */
    private String telnet;

    /**
     * The command line prompt.
     */
    private String prompt;

    /**
     * The status check configuration.
     */
    private String status;

    /**
     * Indicates whether the service should be registered.
     */
    private Boolean register;

    // TODO: Move this property to the provider configuration.
    /**
     * Indicates whether it is a persistent connection.
     */
    private Boolean keepAlive;

    // TODO: Move this property to the provider configuration.
    /**
     * The optimizer used for dubbo protocol.
     */
    private String optimizer;

    /**
     * Additional extensions.
     */
    private String extension;

    /**
     * Custom parameters.
     */
    private Map<String, String> parameters;

    /**
     * Indicates whether SSL is enabled.
     */
    private Boolean sslEnabled;

    /**
     * Extra protocol for this service, using Port Unification Server.
     */
    private String extProtocol;

    /**
     * JSON check level for serialization.
     */
    private String jsonCheckLevel;

    @Nested
    private TripleConfig triple;

    public ProtocolConfig() {}

    public ProtocolConfig(ApplicationModel applicationModel) {
        super(applicationModel);
    }

    public ProtocolConfig(String name) {
        setName(name);
    }

    public ProtocolConfig(ApplicationModel applicationModel, String name) {
        super(applicationModel);
        setName(name);
    }

    public ProtocolConfig(String name, int port) {
        setName(name);
        setPort(port);
    }

    public ProtocolConfig(ApplicationModel applicationModel, String name, int port) {
        super(applicationModel);
        setName(name);
        setPort(port);
    }

    @Override
    protected void checkDefault() {
        super.checkDefault();
        if (name == null) {
            name = DUBBO_PROTOCOL;
        }

        if (StringUtils.isBlank(preferSerialization)) {
            preferSerialization = serialization != null
                    ? serialization
                    : getScopeModel()
                            .getBeanFactory()
                            .getBean(PreferSerializationProvider.class)
                            .getPreferSerialization();
        }
    }

    @Parameter(excluded = true)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Parameter(excluded = true)
    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    @Parameter(excluded = true)
    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    @Deprecated
    @Parameter(excluded = true, attribute = false)
    public String getPath() {
        return getContextpath();
    }

    @Deprecated
    public void setPath(String path) {
        setContextpath(path);
    }

    @Parameter(excluded = true)
    public String getContextpath() {
        return contextpath;
    }

    public void setContextpath(String contextpath) {
        this.contextpath = contextpath;
    }

    public String getThreadpool() {
        return threadpool;
    }

    public void setThreadpool(String threadpool) {
        this.threadpool = threadpool;
    }

    @Parameter(key = JSON_CHECK_LEVEL_KEY)
    public String getJsonCheckLevel() {
        return jsonCheckLevel;
    }

    public void setJsonCheckLevel(String jsonCheckLevel) {
        this.jsonCheckLevel = jsonCheckLevel;
    }

    @Parameter(key = THREAD_POOL_EXHAUSTED_LISTENERS_KEY)
    public String getThreadPoolExhaustedListeners() {
        return threadPoolExhaustedListeners;
    }

    public void setThreadPoolExhaustedListeners(String threadPoolExhaustedListeners) {
        this.threadPoolExhaustedListeners = threadPoolExhaustedListeners;
    }

    public Integer getCorethreads() {
        return corethreads;
    }

    public void setCorethreads(Integer corethreads) {
        this.corethreads = corethreads;
    }

    public Integer getThreads() {
        return threads;
    }

    public void setThreads(Integer threads) {
        this.threads = threads;
    }

    public Integer getIothreads() {
        return iothreads;
    }

    public void setIothreads(Integer iothreads) {
        this.iothreads = iothreads;
    }

    public Integer getAlive() {
        return alive;
    }

    public void setAlive(Integer alive) {
        this.alive = alive;
    }

    public Integer getQueues() {
        return queues;
    }

    public void setQueues(Integer queues) {
        this.queues = queues;
    }

    public Integer getAccepts() {
        return accepts;
    }

    public void setAccepts(Integer accepts) {
        this.accepts = accepts;
    }

    public String getCodec() {
        return codec;
    }

    public void setCodec(String codec) {
        this.codec = codec;
    }

    public String getSerialization() {
        return serialization;
    }

    public void setSerialization(String serialization) {
        this.serialization = serialization;
    }

    public String getPreferSerialization() {
        return preferSerialization;
    }

    public void setPreferSerialization(String preferSerialization) {
        this.preferSerialization = preferSerialization;
    }

    public String getCharset() {
        return charset;
    }

    public void setCharset(String charset) {
        this.charset = charset;
    }

    public Integer getPayload() {
        return payload;
    }

    public void setPayload(Integer payload) {
        this.payload = payload;
    }

    public Integer getBuffer() {
        return buffer;
    }

    public void setBuffer(Integer buffer) {
        this.buffer = buffer;
    }

    public Integer getHeartbeat() {
        return heartbeat;
    }

    public void setHeartbeat(Integer heartbeat) {
        this.heartbeat = heartbeat;
    }

    public String getServer() {
        return server;
    }

    public void setServer(String server) {
        this.server = server;
    }

    public String getClient() {
        return client;
    }

    public void setClient(String client) {
        this.client = client;
    }

    public String getAccesslog() {
        return accesslog;
    }

    public void setAccesslog(String accesslog) {
        this.accesslog = accesslog;
    }

    public String getTelnet() {
        return telnet;
    }

    public void setTelnet(String telnet) {
        this.telnet = telnet;
    }

    @Parameter(escaped = true)
    public String getPrompt() {
        return prompt;
    }

    public void setPrompt(String prompt) {
        this.prompt = prompt;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Boolean isRegister() {
        return register;
    }

    public void setRegister(Boolean register) {
        this.register = register;
    }

    public String getTransporter() {
        return transporter;
    }

    public void setTransporter(String transporter) {
        this.transporter = transporter;
    }

    public String getExchanger() {
        return exchanger;
    }

    public void setExchanger(String exchanger) {
        this.exchanger = exchanger;
    }

    /**
     * typo, switch to use {@link #getDispatcher()}
     *
     * @deprecated {@link #getDispatcher()}
     */
    @Deprecated
    @Parameter(excluded = true, attribute = false)
    public String getDispather() {
        return getDispatcher();
    }

    /**
     * typo, switch to use {@link #getDispatcher()}
     *
     * @deprecated {@link #setDispatcher(String)}
     */
    @Deprecated
    public void setDispather(String dispather) {
        setDispatcher(dispather);
    }

    public String getDispatcher() {
        return dispatcher;
    }

    public void setDispatcher(String dispatcher) {
        this.dispatcher = dispatcher;
    }

    public String getNetworker() {
        return networker;
    }

    public void setNetworker(String networker) {
        this.networker = networker;
    }

    public Map<String, String> getParameters() {
        return parameters;
    }

    public void setParameters(Map<String, String> parameters) {
        this.parameters = parameters;
    }

    @Parameter(key = SSL_ENABLED_KEY)
    public Boolean getSslEnabled() {
        return sslEnabled;
    }

    public void setSslEnabled(Boolean sslEnabled) {
        this.sslEnabled = sslEnabled;
    }

    public Boolean getKeepAlive() {
        return keepAlive;
    }

    public void setKeepAlive(Boolean keepAlive) {
        this.keepAlive = keepAlive;
    }

    public String getOptimizer() {
        return optimizer;
    }

    public void setOptimizer(String optimizer) {
        this.optimizer = optimizer;
    }

    public String getExtension() {
        return extension;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }

    @Override
    @Parameter(excluded = true, attribute = false)
    public boolean isValid() {
        return StringUtils.isNotEmpty(name);
    }

    public String getExtProtocol() {
        return extProtocol;
    }

    public void setExtProtocol(String extProtocol) {
        this.extProtocol = extProtocol;
    }

    public TripleConfig getTriple() {
        return triple;
    }

    @Parameter(excluded = true)
    public TripleConfig getTripleOrDefault() {
        if (triple == null) {
            triple = new TripleConfig();
        }
        return triple;
    }

    public void setTriple(TripleConfig triple) {
        this.triple = triple;
    }

    public void mergeProtocol(ProtocolConfig sourceConfig) {
        if (sourceConfig == null) {
            return;
        }
        Field[] targetFields = getClass().getDeclaredFields();
        try {
            Map<String, Object> protocolConfigMap = CollectionUtils.objToMap(sourceConfig);
            for (Field targetField : targetFields) {
                Optional.ofNullable(protocolConfigMap.get(targetField.getName()))
                        .ifPresent(value -> {
                            try {
                                targetField.setAccessible(true);
                                if (targetField.get(this) == null) {
                                    targetField.set(this, value);
                                }
                            } catch (IllegalAccessException e) {
                                throw new RuntimeException(e);
                            }
                        });
            }
        } catch (Exception e) {
            logger.error(COMMON_UNEXPECTED_EXCEPTION, "", "", "merge protocol config fail, error: ", e);
        }
    }
}
