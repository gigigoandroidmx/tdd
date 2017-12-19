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

package com.gigigoandroidmx.people.domain.usecase;

import com.gigigoandroidmx.kmvp.UseCase;
import com.gigigoandroidmx.people.data.repository.UserRepository;
import com.gigigoandroidmx.people.domain.model.User;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Scheduler;

/**
 * Defines ...
 *
 * @author Juan Godinez Vera - December 19, 2017
 * @version 0.0.1
 * @since 0.0.1
 */
public class GetUsers
        extends UseCase<List<User>, UserRepository> {

    public GetUsers(Scheduler executorThread,
                    Scheduler uiThread) {
        super(executorThread, uiThread);
    }

    @Override
    protected Observable<List<User>> createObservableUseCase() {
        return getRepository().getUsers();
    }
}
