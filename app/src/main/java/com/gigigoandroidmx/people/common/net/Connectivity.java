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

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

import com.gigigoandroidmx.kmvp.Network;

import java.io.IOException;
import java.net.InetAddress;

/**
 * Defines ...
 *
 * @author JG - December 28, 2017
 * @version 0.0.1
 * @since 0.0.1
 */
public class Connectivity
        extends BroadcastReceiver
        implements Network {

    private static final String TAG = Connectivity.class.getSimpleName();

    public static final int TYPE_NONE = -1;

    private ConnectivityManager connectivityManager;

    public Connectivity(Context context) {
        connectivityManager = (ConnectivityManager) context.
                getSystemService(Context.CONNECTIVITY_SERVICE);
    }

    @Override
    public void onReceive(Context context, Intent intent) { }

    @Override
    public boolean isConnected() {
        NetworkInfo activeNetwork = connectivityManager != null
                ? connectivityManager.getActiveNetworkInfo()
                : null;

        return null != activeNetwork && activeNetwork.isConnectedOrConnecting();
    }

    @Override
    public int connectivityType() {
        NetworkInfo activeNetwork = connectivityManager != null
                ? connectivityManager.getActiveNetworkInfo()
                : null;

        return null != activeNetwork ?
                activeNetwork.getType() :
                TYPE_NONE;
    }

    @Override
    public boolean isReachable(String host, int timeout) {
        if(!isConnected()) return false;

        boolean reachable;

        try {
            reachable = InetAddress.getByName(host).isReachable(timeout);
        } catch (IOException e) {
            Log.e(TAG, Log.getStackTraceString(e));
            reachable = false;
        }

        return reachable;
    }
}
