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

/**
 * Defines ...
 *
 * @author JG - January 12, 2018
 * @version 0.0.1
 * @since 0.0.1
 */
public class SharedPreferencesExtensions {

    private final Context context;
    private String name;
    private int mode;
    private boolean isDebug;

    private static DefaultStorage storage;

    public static SharedPreferencesBuilder builder(Context context) {
        return new SharedPreferencesBuilder(context);
    }

    private SharedPreferencesExtensions(SharedPreferencesBuilder builder) {
        context = builder.context;
        name = builder.name;
        mode = builder.mode;
        isDebug = builder.isDebug;

        storage = new DefaultStorage(context, name, mode, isDebug);
    }

    public static boolean settingExist(String key) {
        return storage.settingExist(key);
    }

    public static <T> boolean put(String key, Class<T> type, T value) {
        return SharedPreferencesExtensions.put(key, type, value, true);
    }

    public static <T> boolean put(String key, Class<T> type, T value, boolean replaceIfExist) {
        return storage.put(key, type, value, replaceIfExist);
    }

    public static <T> T get(String key, Class<T> type) {
        return SharedPreferencesExtensions.get(key, type,
                DefaultStorage.DefaultValues.defaultValue(type));
    }

    public static <T> T get(String key, Class<T> type, T defaultValue) {
        return storage.get(key, type, defaultValue);
    }

    public static boolean delete(String key) {
        return storage.delete(key);
    }

    public static class SharedPreferencesBuilder {
        private final Context context;
        private String name;
        private int mode;
        private boolean isDebug;

        public SharedPreferencesBuilder(Context context) {
            this.context = context;
        }

        public SharedPreferencesBuilder setName(String name) {
            this.name = name;
            return this;
        }

        public SharedPreferencesBuilder setMode(int mode) {
            this.mode = mode;
            return this;
        }

        public SharedPreferencesBuilder loggable(boolean isDebug) {
            this.isDebug = isDebug;
            return this;
        }

        public SharedPreferencesExtensions build() {
            return new SharedPreferencesExtensions(this);
        }
    }
}
