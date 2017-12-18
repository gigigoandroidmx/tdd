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

package com.gigigoandroidmx.people.presentation.ui.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gigigoandroidmx.kmvp.BaseFragment;
import com.gigigoandroidmx.people.R;
import com.gigigoandroidmx.people.data.model.People;
import com.gigigoandroidmx.people.presentation.presenter.view.PeopleView;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class MainFragment
        extends BaseFragment implements PeopleView {

    //region BaseFragment members

    @Override
    protected int getLayoutResourceId() {
        return R.layout.fragment_main;
    }

    @Override
    protected void onInitializeComponents() {

    }

    @Override
    protected void onInitializeMembers() {

    }

    @Override
    protected void onBindView(View root) {

    }

    @Override
    protected void onUnbindView() {

    }

    //endregion

    //region PeopleView members

    @Override
    public void showErrorMessageForUserNameOrPassword() {

    }

    @Override
    public void showErrorMessageForMaxLoginAttempt() {

    }

    @Override
    public void showMessageForLoginSuccess() {

    }

    @Override
    public void onFetchPeopleSuccess(List<People> people) {

    }

    @Override
    public void showProgress(boolean active) {

    }

    @Override
    public void showError(Throwable exception) {

    }

    //endregion
}
