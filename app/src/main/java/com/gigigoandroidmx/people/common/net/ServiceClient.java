/*
 * Copyright (c) 2018 Gigigo Android Development Team México
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.gigigoandroidmx.people.common.net;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Interceptor;
import retrofit2.CallAdapter;
import retrofit2.Converter;

/**
 * Defines ...
 *
 * @author Juan Godinez Vera - December 28, 2017
 * @version 0.0.1
 * @since 0.0.1
 */
public class ServiceClient {
    private final List<String> endpoints;
    private final List<Converter.Factory> converterFactories;
    private final List<CallAdapter.Factory> adapterFactories;
    private Interceptor loggingInterceptor;
    private Interceptor connectivityInterceptor;

    private static volatile ServiceClient defaultInstance;

    private static final ServiceClientBuilder DEFAULT_BUILDER = new ServiceClientBuilder();

    public static ServiceClient getDefault() {
        if (defaultInstance == null) {
            synchronized (ServiceClient.class) {
                if (defaultInstance == null) {
                    defaultInstance = new ServiceClient();
                }
            }
        }
        return defaultInstance;
    }

    public static ServiceClientBuilder builder() {
        return new ServiceClientBuilder();
    }

    private ServiceClient() {
        this(DEFAULT_BUILDER);
    }

    private ServiceClient(ServiceClientBuilder builder) {
        endpoints = builder.endpoints;
        converterFactories = builder.converterFactories;
        adapterFactories = builder.adapterFactories;
        loggingInterceptor = builder.loggingInterceptor;
        connectivityInterceptor = builder.connectivityInterceptor;
    }

    public String getEndpointByIndex(int index) {
        if(null != endpoints && !endpoints.isEmpty() && index < endpoints.size()) {
            return endpoints.get(index);
        }
        return null;
    }

    public List<String> getEndpoints() {
        return endpoints;
    }

    public List<Converter.Factory> getConverterFactories() {
        return converterFactories;
    }

    public boolean hasConverterFactories() {
        return null != converterFactories && !converterFactories.isEmpty();
    }

    public List<CallAdapter.Factory> getAdapterFactories() {
        return adapterFactories;
    }

    public boolean hasAdapterFactories() {
        return null != adapterFactories && !adapterFactories.isEmpty();
    }

    public Interceptor getLoggingInterceptor() {
        return loggingInterceptor;
    }

    public Interceptor getConnectivityInterceptor() {
        return connectivityInterceptor;
    }

    public static class ServiceClientBuilder {
        private final List<String> endpoints = new ArrayList<>();
        private final List<Converter.Factory> converterFactories = new ArrayList<>();
        private final List<CallAdapter.Factory> adapterFactories = new ArrayList<>();
        private Interceptor loggingInterceptor;
        private Interceptor connectivityInterceptor;

        ServiceClientBuilder() {

        }

        public ServiceClientBuilder addEndpoint(String endpoint) {
            endpoints.add(endpoint);
            return this;
        }

        public ServiceClientBuilder addConverterFactory(Converter.Factory factory) {
            converterFactories.add(factory);
            return this;
        }

        public ServiceClientBuilder addCallAdapterFactory(CallAdapter.Factory factory) {
            adapterFactories.add(factory);
            return this;
        }

        public ServiceClientBuilder setLoggingInterceptor(Interceptor interceptor) {
            loggingInterceptor = interceptor;
            return this;
        }

        public ServiceClientBuilder setConnectivityInterceptor(Interceptor interceptor) {
            connectivityInterceptor = interceptor;
            return this;
        }

        public ServiceClient build() {
            if(null != ServiceClient.defaultInstance) {
                throw new ServiceClientException();
            }

            ServiceClient.defaultInstance = new ServiceClient(this);

            return ServiceClient.defaultInstance;
        }

        public static class ServiceClientException
                extends RuntimeException {
            public ServiceClientException() {
                super("Default instance already exists.");
            }
        }
    }
}
