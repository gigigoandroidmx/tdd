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

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.CallAdapter;
import retrofit2.Converter;
import retrofit2.Retrofit;

/**
 * Defines ...
 *
 * @author Juan Godinez Vera - December 28, 2017
 * @version 0.0.1
 * @since 0.0.1
 */
public class ServiceClientFactory {

    public static <T> T createService(ServiceClient serviceClient,
                                      Class<T> classType) {
        return createService(serviceClient,
                classType,
                0,
                ServiceTimeoutSettings.DEFAULT());
    }

    public static <T> T createService(ServiceClient serviceClient,
                                      Class<T> classType, int endpointIndex) {
        return createService(serviceClient,
                classType,
                endpointIndex,
                ServiceTimeoutSettings.DEFAULT());
    }

    public static <T> T createService(ServiceClient serviceClient,
                                      Class<T> classType,
                                      int endpointIndex,
                                      ServiceTimeoutSettings serviceTimeoutSettings) {

        String endpoint = serviceClient.getEndpointByIndex(endpointIndex);

        OkHttpClient.Builder okHttpBuilder = new OkHttpClient.Builder();

        okHttpBuilder.connectTimeout(serviceTimeoutSettings.getConnectTimeout(), TimeUnit.SECONDS)
                .writeTimeout(serviceTimeoutSettings.getWriteTimeout(), TimeUnit.SECONDS)
                .readTimeout(serviceTimeoutSettings.getReadTimeout(), TimeUnit.SECONDS);

        if(null != serviceClient.getLoggingInterceptor()) {
            okHttpBuilder.interceptors().add(serviceClient.getLoggingInterceptor() );
        }

        if(null != serviceClient.getConnectivityInterceptor()) {
            okHttpBuilder.interceptors().add(serviceClient.getConnectivityInterceptor());
        }

        OkHttpClient okHttpClient = okHttpBuilder.build();

        Retrofit.Builder retrofitBuilder = new Retrofit.Builder()
                .baseUrl(endpoint)
                .client(okHttpClient);

        if(serviceClient.hasConverterFactories()) {
            for(Converter.Factory factory : serviceClient.getConverterFactories()) {
                retrofitBuilder.addConverterFactory(factory);
            }
        }

        if(serviceClient.hasAdapterFactories()) {
            for(CallAdapter.Factory factory : serviceClient.getAdapterFactories()) {
                retrofitBuilder.addCallAdapterFactory(factory);
            }
        }

        return retrofitBuilder.build()
                .create(classType);
    }
}
