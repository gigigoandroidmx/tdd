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

package com.gigigoandroidmx.people.data.repository.mapper;

import com.gigigoandroidmx.kmvp.Transform;
import com.gigigoandroidmx.people.data.entity.UserEntity;
import com.gigigoandroidmx.people.domain.model.User;

/**
 * Defines ...
 *
 * @author JG - January 04, 2018
 * @version 0.0.1
 * @since 0.0.1
 */
public class UserEntityToUserTransform
        extends Transform<UserEntity, User> {

    @Override
    public User transform(UserEntity value) {
        if(null ==  value) return null;

        User model = new User();
        model.setId(value.getId());
        model.setName(value.getFirstName() + " " + value.getLastName());
        model.setAvatar(value.getAvatar());

        return model;
    }

    @Override
    public UserEntity transformMap(User value) {
        throw new UnsupportedOperationException();
    }
}
