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

package com.gigigoandroidmx.people.presentation.model.mapper;

import com.gigigoandroidmx.kmvp.Mapper;
import com.gigigoandroidmx.people.domain.model.User;
import com.gigigoandroidmx.people.presentation.model.UserViewModel;

/**
 * Defines ...
 *
 * @author Juan Godinez Vera - January 04, 2018
 * @version 0.0.1
 * @since 0.0.1
 */
public class UserToUserViewModel
        extends Mapper<User, UserViewModel> {
    @Override
    public UserViewModel map(User value) {
        if(null ==  value) return null;

        UserViewModel model = new UserViewModel();
        model.setId(value.getId());
        model.setName(value.getName());
        model.setAvatar(value.getAvatar());

        return model;
    }

    @Override
    public User reverseMap(UserViewModel value) {
        throw new UnsupportedOperationException();
    }
}
