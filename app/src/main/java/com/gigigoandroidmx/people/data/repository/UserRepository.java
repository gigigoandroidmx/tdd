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

package com.gigigoandroidmx.people.data.repository;

import com.gigigoandroidmx.people.data.RestApi;
import com.gigigoandroidmx.people.data.entity.ListUsersResponse;
import com.gigigoandroidmx.people.data.repository.mapper.UserEntityToUserMapper;
import com.gigigoandroidmx.people.domain.model.User;
import com.gigigoandroidmx.people.domain.repository.ListUsersRepository;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.functions.Function;

/**
 * Defines ...
 *
 * @author Juan Godinez Vera - December 19, 2017
 * @version 0.0.1
 * @since 0.0.1
 */
public class UserRepository
        implements ListUsersRepository {

    private final RestApi api;
    private final UserEntityToUserMapper userMapper;

    public UserRepository(RestApi api,
                          UserEntityToUserMapper userMapper) {
        this.api = api;
        this.userMapper = userMapper;
    }

    @Override
    public Observable<List<User>> getListUser(int page) {
        Observable<ListUsersResponse> response = api.getListUsers(page);

        if(null == response) return null;

        return response.map(new Function<ListUsersResponse, List<User>>() {
            @Override
            public List<User> apply(ListUsersResponse listUsersResponse) throws Exception {
                if(null != listUsersResponse && listUsersResponse.hasData()) {
                    return userMapper.map(listUsersResponse.getData());
                } else {
                    return null;
                }
            }
        });
    }
}
