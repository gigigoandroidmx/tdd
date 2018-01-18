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

import com.gigigoandroidmx.people.common.net.error.RxErrorHandlerFunction;
import com.gigigoandroidmx.people.data.RestApi;
import com.gigigoandroidmx.people.data.entity.ListUsersResponse;
import com.gigigoandroidmx.people.data.repository.mapper.UserEntityToUserTransform;
import com.gigigoandroidmx.people.domain.model.User;
import com.gigigoandroidmx.people.domain.repository.ListUsersRepository;

import java.util.ArrayList;
import com.gigigoandroidmx.people.data.entity.SimpleResponseError;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.functions.Function;
import okhttp3.ResponseBody;

/**
 * Defines ...
 *
 * @author JG - December 19, 2017
 * @version 0.0.1
 * @since 0.0.1
 */
public class UserRepository
        implements ListUsersRepository {

    private final RestApi api;
    private final UserEntityToUserTransform userMapper;

    public UserRepository(RestApi api,
                          UserEntityToUserTransform userMapper) {
        this.api = api;
        this.userMapper = userMapper;
    }

    @Override
    public Observable<List<User>> getListUser(int page, int perPage) {
        Map<String, String> data = new HashMap<>();
        data.put("page", String.valueOf(page));
        data.put("per_page", String.valueOf(perPage));

        Observable<ListUsersResponse> response = api.getListUsers(data);

        return response.map(new Function<ListUsersResponse, List<User>>() {
            @Override
            public List<User> apply(ListUsersResponse listUsersResponse) throws Exception {
                if(null != listUsersResponse && listUsersResponse.hasData()) {
                    return userMapper.transform(listUsersResponse.getData());
                } else {
                    return new ArrayList<>();
                }
            }
        }).onErrorReturn(new Function<Throwable, List<User>>() {
            @Override
            public List<User> apply(Throwable throwable) throws Exception {
                return new ArrayList<>();
            }
        });


//        return Observable.create(new ObservableOnSubscribe<List<User>>() {
//            @Override
//            public void subscribe(ObservableEmitter<List<User>> emitter) throws Exception {
//                ListUsersResponse listUsersResponse = response.blockingSingle();
//
//                if(null != listUsersResponse && listUsersResponse.hasData()) {
//                    List<User> values = userMapper.transform(listUsersResponse.getData());
//
//                    if (null != values) {
//                        emitter.onNext(values);
//                        emitter.onComplete();
//                    }
//                } else {
//                    emitter.onComplete();
//                }
//            }
//        });



/*
        return response.transform(new Function<ListUsersResponse, List<User>>() {
            @Override
            public List<User> apply(ListUsersResponse listUsersResponse) throws Exception {
                if(null != listUsersResponse && listUsersResponse.hasData()) {
                    return userMapper.transform(listUsersResponse.getData());
=======
                if (null != listUsersResponse && listUsersResponse.hasData()) {
                    return userMapper.map(listUsersResponse.getData());
>>>>>>> 044bc16ba37a82ed4aa339c66c33dcdb5d4646bc
                } else {
                    return null;
                }
            }
        });
        */
    }

    public Observable<ListUsersResponse> getListUser2(int page, int perPage) {
        Map<String, String> data = new HashMap<>();
        data.put("page", String.valueOf(page));
        data.put("per_page", String.valueOf(perPage));

        Observable<ListUsersResponse> response = api.getListUsers(data);


        return response;
    }

    @Override
    public Observable<ResponseBody> login(String email) {
        Map<String, String> params = new HashMap<>();
        params.put("email", email);

        /*return (Observable<ResponseBody>) api.login(params).onErrorResumeNext(
                new RxErrorHandlerFunction(SimpleResponseError.class));*/

        return (Observable<ResponseBody>) api.singleUserNotFound().onErrorResumeNext(
                new RxErrorHandlerFunction(SimpleResponseError.class));
    }
}
