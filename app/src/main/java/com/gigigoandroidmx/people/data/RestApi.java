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

package com.gigigoandroidmx.people.data;

import com.gigigoandroidmx.people.data.entity.ListUsersResponse;

import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

/**
 * Defines ...
 *
 * @author JG - January 04, 2018
 * @version 0.0.1
 * @since 0.0.1
 */
public interface RestApi {
    @GET("/api/users")
    Observable<ListUsersResponse> getListUsers(@QueryMap(encoded=true) Map<String, String> options);
}
