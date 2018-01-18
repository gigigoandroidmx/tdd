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

import com.gigigoandroidmx.kmvp.Network;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Response;

/**
 * Defines ...
 *
 * @author JG - December 28, 2017
 * @version 0.0.1
 * @since 0.0.1
 */
public class RequestInterceptor
        implements Interceptor {

    private final Network network;

    public RequestInterceptor(Network network) {
        this.network = network;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        if(null != network && network.isConnected()) {
            return chain.proceed(chain.request());
        } else {
            throw new NetworkException();
        }
    }

    public static class NetworkException
            extends IOException {
        public NetworkException() {
            super("Parece que su conexión a Internet está desactivada." +
                    "\n\nPor favor, enciéndala y vuelva a intentarlo.");
        }
    }
}
