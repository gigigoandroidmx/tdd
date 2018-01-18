/*
 * Copyright (c) 2017 Gigigo Android Development Team México
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

package com.gigigoandroidmx.kmvp;

import java.util.ArrayList;
import java.util.List;

/**
 * Defines ...
 *
 * @author JG - December 19, 2017
 * @version 0.0.1
 * @since 0.0.1
 */
public abstract class Transform<T1, T2> {
    public abstract T2 transform(T1 value);

    public abstract T1 transformMap(T2 value);

    public List<T2> transform(List<T1> values) {
        if(null == values || values.isEmpty()) return null;

        List<T2> returnValues = new ArrayList<>(values.size());
        for (T1 value : values) {
            returnValues.add(transform(value));
        }
        return returnValues;
    }

    public List<T1> transformMap(List<T2> values) {
        if(null == values || values.isEmpty()) return null;

        List<T1> returnValues = new ArrayList<>(values.size());
        for (T2 value : values) {
            returnValues.add(transformMap(value));
        }
        return returnValues;
    }
}
