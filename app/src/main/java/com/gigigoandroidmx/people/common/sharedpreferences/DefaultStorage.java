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

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.google.gson.Gson;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * @author JG - January 15, 2018
 * @version 0.0.1
 * @since 0.0.1
 */
public class DefaultStorage
        implements Storage {

    private static final String TAG = DefaultStorage.class.getSimpleName();

    private final SharedPreferences sharedPreferences;
    private final GsonParser parser;
    private final boolean isDebug;

    DefaultStorage(Context context, String name, int mode, boolean isDebug) {
        this.isDebug = isDebug;
        sharedPreferences = context.getSharedPreferences(name, mode);
        parser = new GsonParser(new Gson());
    }

    @Override
    public boolean settingExist(String key) {
        return sharedPreferences.contains(key);
    }

    @Override
    public <T> boolean put(String key, Class<T> type, T value, boolean replaceIfExist) {
        if(null == value) {
            value = DefaultValues.defaultValue(type);
        }

        if(!replaceIfExist && settingExist(key)) return false;

        logI("key: " + key + ", value: " + value);

        boolean successfully;
        SharedPreferences.Editor editor = sharedPreferences.edit();

        if(value instanceof String) {
            successfully = editor.putString(key, (String) value).commit();
        } else if(value instanceof Integer) {
            successfully = editor.putInt(key, (Integer) value).commit();
        } else if(value instanceof Boolean) {
            successfully = editor.putBoolean(key, (Boolean) value).commit();
        } else if(value instanceof Float) {
            successfully = editor.putFloat(key, (Float) value).commit();
        } else if(value instanceof Long) {
            successfully = editor.putLong(key, (Long) value).commit();
        } else {
            String json = parser.serialize(value, type);
            successfully = editor.putString(key, json).commit();
        }

        return successfully;
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T> T get(String key, Class<T> type, T defaultValue) {
        if(null == defaultValue) {
            defaultValue = DefaultValues.defaultValue(type);
        }

        logI("key: " + key + ", default value: " + defaultValue);

        T value = defaultValue;

        if(defaultValue instanceof String) {
            value = (T) sharedPreferences.getString(key, (String) defaultValue);
        } else if(defaultValue instanceof Integer) {
            value = (T)((Integer) sharedPreferences.getInt(key, (Integer) defaultValue));
        } else if(defaultValue instanceof Boolean) {
            value = (T) ((Boolean)sharedPreferences.getBoolean(key, (Boolean) defaultValue));
        } else if(defaultValue instanceof Float) {
            value = (T) ((Float)sharedPreferences.getFloat(key, (Float) defaultValue));
        } else if(defaultValue instanceof Long) {
            value = (T) ((Long)sharedPreferences.getLong(key, (Long) defaultValue));
        } else {
            String json = sharedPreferences.getString(key, "");
            if(!json.isEmpty()) {
                value = parser.deserialize(json, type);
            }
        }

        return value;
    }

    @Override
    public boolean delete(String key) {
        return sharedPreferences.edit().remove(key).commit();
    }

    private void logI(String log) {
        if(isDebug) {
            Log.i(TAG, log);
        }
    }

    public static class DefaultValues {
        private static final Map<Class<?>, Object> DEFAULTS;

        static {
            Map<Class<?>, Object> map = new HashMap<>();
            put(map, boolean.class, false);
            put(map, byte.class, (byte) 0);
            put(map, short.class, (short) 0);
            put(map, int.class, 0);
            put(map, long.class, 0L);
            put(map, float.class, 0f);
            put(map, double.class, 0d);
            put(map, char.class, '\0');
            put(map, Boolean.class, false);
            put(map, Byte.class, (byte) 0);
            put(map, Short.class, (short) 0);
            put(map, Integer.class, 0);
            put(map, Long.class, 0L);
            put(map, Float.class, 0f);
            put(map, Double.class, 0d);
            put(map, String.class, "");
            DEFAULTS = Collections.unmodifiableMap(map);
        }

        private static <T> void put(Map<Class<?>, Object> map, Class<T> type, T value) {
            map.put(type, value);
        }

        public static <T> T defaultValue(Class<T> type) {
            T t = (T) DEFAULTS.get(type);
            return t;
        }
    }
}
