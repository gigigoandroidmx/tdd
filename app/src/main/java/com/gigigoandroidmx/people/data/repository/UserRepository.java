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

import com.gigigoandroidmx.kmvp.Repository;
import com.gigigoandroidmx.people.domain.model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

import io.reactivex.Observable;

/**
 * Defines ...
 *
 * @author Juan Godinez Vera - December 19, 2017
 * @version 0.0.1
 * @since 0.0.1
 */
public class UserRepository
        implements Repository {

    public Observable<List<User>> getUsers() {
        return Observable.fromCallable(new Callable<List<User>>() {
            @Override
            public List<User> call() throws Exception {
                List<User> users = new ArrayList<>();

                for (int i = 0; i < 5; i++){
                    User user = new User();
                    user.setId(i);
                    user.setName("User " + String.valueOf(i));
                    users.add(user);
                }

                return users;
            }
        });
    }
}
