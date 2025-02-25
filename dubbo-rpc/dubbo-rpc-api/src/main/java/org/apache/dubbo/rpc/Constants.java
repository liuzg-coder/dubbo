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
package org.apache.dubbo.rpc;

public interface Constants {
    String LOCAL_KEY = "local";

    String STUB_KEY = "stub";

    String MOCK_KEY = "mock";

    String DEPRECATED_KEY = "deprecated";

    String $ECHO = "$echo";
    String $ECHO_PARAMETER_DESC = "Ljava/lang/Object;";

    String RETURN_PREFIX = "return ";

    String THROW_PREFIX = "throw";

    String FAIL_PREFIX = "fail:";

    String FORCE_PREFIX = "force:";

    String MERGER_KEY = "merger";

    String IS_SERVER_KEY = "isserver";

    String FORCE_USE_TAG = "dubbo.force.tag";

    String TPS_LIMIT_RATE_KEY = "tps";

    String TPS_LIMIT_INTERVAL_KEY = "tps.interval";

    long DEFAULT_TPS_LIMIT_INTERVAL = 60 * 1000;

    String AUTO_ATTACH_INVOCATIONID_KEY = "invocationid.autoattach";

    boolean DEFAULT_STUB_EVENT = false;

    String STUB_EVENT_METHODS_KEY = "dubbo.stub.event.methods";

    String COMPRESSOR_KEY = "dubbo.rpc.tri.compressor";

    String PROXY_KEY = "proxy";

    String EXECUTES_KEY = "executes";

    String ACCESS_LOG_KEY = "accesslog";

    String ACCESS_LOG_FIXED_PATH_KEY = "accesslog.fixed.path";

    String ACTIVES_KEY = "actives";

    String ID_KEY = "id";

    String ASYNC_KEY = "async";

    String RETURN_KEY = "return";

    String TOKEN_KEY = "token";

    String INTERFACE = "interface";

    String INTERFACES = "interfaces";

    String GENERIC_KEY = "generic";

    String LOCAL_PROTOCOL = "injvm";

    String DEFAULT_REMOTING_SERVER = "netty";

    String SCOPE_KEY = "scope";
    String SCOPE_LOCAL = "local";
    String SCOPE_REMOTE = "remote";

    String INPUT_KEY = "input";
    String OUTPUT_KEY = "output";

    String CONSUMER_MODEL = "consumerModel";
    String METHOD_MODEL = "methodModel";

    String INVOCATION_KEY = "invocation";
    String SERIALIZATION_ID_KEY = "serialization_id";

    String HTTP3_KEY = "http3";

    String H2_SETTINGS_SUPPORT_NO_LOWER_HEADER_KEY = "dubbo.rpc.tri.support-no-lower-header";
    String H2_SETTINGS_IGNORE_1_0_0_KEY = "dubbo.rpc.tri.ignore-1.0.0-version";
    String H2_SETTINGS_RESOLVE_FALLBACK_TO_DEFAULT_KEY = "dubbo.rpc.tri.resolve-fallback-to-default";
    String H2_SETTINGS_BUILTIN_SERVICE_INIT = "dubbo.tri.builtin.service.init";
    String H2_SETTINGS_PASS_THROUGH_STANDARD_HTTP_HEADERS = "dubbo.rpc.tri.pass-through-standard-http-headers";

    String H2_SETTINGS_VERBOSE_ENABLED = "dubbo.protocol.triple.verbose";
    String H2_SETTINGS_SERVLET_ENABLED = "dubbo.protocol.triple.servlet.enabled";
    String H3_SETTINGS_HTTP3_ENABLED = "dubbo.protocol.triple.http3.enabled";
    String H3_SETTINGS_HTTP3_NEGOTIATION = "dubbo.protocol.triple.http3.negotiation";

    String ADAPTIVE_LOADBALANCE_ATTACHMENT_KEY = "lb_adaptive";
    String ADAPTIVE_LOADBALANCE_START_TIME = "adaptive_startTime";
}
