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

package com.gigigoandroidmx.people.data.entity;

import com.gigigoandroidmx.people.data.entity.base.ResponseBase;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Defines ...
 *
 * @author JG - December 13, 2017
 * @version 0.0.1
 * @since 0.0.1
 */
public class ListUsersResponse
        extends ResponseBase<List<UserEntity>> {

    @Override
    public boolean hasData() {
        return null != getData() && !getData().isEmpty();
    }
}
