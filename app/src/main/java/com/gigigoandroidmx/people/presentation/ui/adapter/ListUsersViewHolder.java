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

package com.gigigoandroidmx.people.presentation.ui.adapter;

import android.view.View;

import com.gigigoandroidmx.people.common.recyclerext.ViewHolderAdapter;
import com.gigigoandroidmx.people.presentation.model.UserViewModel;

/**
 * Defines ...
 *
 * @author Juan Godinez Vera - January 05, 2018
 * @version 0.0.1
 * @since 0.0.1
 */
public class ListUsersViewHolder
        extends ViewHolderAdapter<UserViewModel> {

    public ListUsersViewHolder(View itemView) {
        super(itemView);
    }

    @Override
    public void onBindViewHolder(UserViewModel item) {
        super.onBindViewHolder(item);
        
    }
}
