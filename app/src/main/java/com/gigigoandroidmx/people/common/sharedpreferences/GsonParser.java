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

package com.gigigoandroidmx.people.common.sharedpreferences;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import java.lang.reflect.Type;

/**
 * @author JG - January 15, 2018
 * @version 0.0.1
 * @since 0.0.1
 */
public class GsonParser
        implements Parser {

    private static final String TAG = GsonParser.class.getSimpleName();

    private final Gson gson;

    public GsonParser(Gson gson) {
        this.gson = gson;
    }

    public <T> String serialize(T data) {
        return serialize(data, data.getClass());
    }

    public <T> String serialize(T data, Type sourceType) {
        String json;

        try {
            json = gson.toJson(data, sourceType);
        } catch (JsonSyntaxException e) {
            Log.e(TAG, Log.getStackTraceString(e));
            json = null;
        }

        return json;
    }

    public <T> T deserialize(String json, Class<T> typeClass) {
        return deserialize(json,  (Type) typeClass);
    }

    public <T> T deserialize(String json, Type type) {
        T data;

        try {
            data = gson.fromJson(json, type);
        } catch (JsonSyntaxException e) {
            Log.e(TAG, Log.getStackTraceString(e));
            data = null;
        }

        return data;
    }
}
