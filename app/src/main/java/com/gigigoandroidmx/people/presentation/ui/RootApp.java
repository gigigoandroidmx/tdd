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

package com.gigigoandroidmx.people.presentation.ui;

import android.app.Application;

import com.gigigoandroidmx.people.BuildConfig;
import com.gigigoandroidmx.people.common.net.Connectivity;
import com.gigigoandroidmx.people.common.net.RequestInterceptor;
import com.gigigoandroidmx.people.common.net.ServiceClient;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.ihsanbal.logging.Level;
import com.ihsanbal.logging.LoggingInterceptor;


import okhttp3.internal.platform.Platform;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Defines ...
 *
 * @author Juan Godinez Vera - January 04, 2018
 * @version 0.0.1
 * @since 0.0.1
 */
public class RootApp
        extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
    }

    private void initializaeService() {
        LoggingInterceptor loggerInterceptor = new LoggingInterceptor.Builder()
                .loggable(BuildConfig.DEBUG)
                .setLevel(Level.BASIC)
                .log(Platform.INFO)
                .request("Request")
                .response("Response")
                .build();

        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd HH:mm:ss")
                .serializeNulls()
                .create();

        RequestInterceptor requestInterceptor =
                new RequestInterceptor(new Connectivity(this));

        ServiceClient.builder()
                .setLoggingInterceptor(loggerInterceptor)
                .setConnectivityInterceptor(requestInterceptor)
                .addEndpoint("https://reqres.in"/*BuildConfig.HOST*/)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }
}
