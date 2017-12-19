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


import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.gigigoandroidmx.kmvp.BaseFragment;
import com.gigigoandroidmx.kmvp.MvpFragment;
import com.gigigoandroidmx.people.R;
import com.gigigoandroidmx.people.data.model.People;
import com.gigigoandroidmx.people.presentation.presenter.PeoplePresenter;
import com.gigigoandroidmx.people.presentation.presenter.view.PeopleView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;


/**
 * A simple {@link Fragment} subclass.
 */
public class MainFragment
        extends MvpFragment<PeopleView, PeoplePresenter>
        implements PeopleView {

    private Unbinder unbinder;

    @BindView(R.id.edit_user_name)
    EditText userName;
    @BindView(R.id.edit_password)
    EditText password;
    @BindView(R.id.btn_login)
    Button login;

    @OnClick(R.id.btn_login)
    void doLogin() {
        presenter.doLogin(String.valueOf(userName.getText()),
                String.valueOf(password.getText()));
    }

    //region BaseFragment members

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_main;
    }

    @Override
    protected void onInitializeUIComponents() {
        userName.setText("peter@klaven");
        password.setText("cityslicka");
    }

    @Override
    protected void onInitializeMembers() {

    }

    @Override
    protected void onBindView(View root) {
        unbinder = ButterKnife.bind(this, root);
    }

    @Override
    protected void onUnbindView() {
        if(null != unbinder) unbinder.unbind();
    }

    //endregion


    //region MvpFragment members

    @Override
    protected PeoplePresenter createPresenter() {
        return new PeoplePresenter();
    }

    //endregion

    //region PeopleView members

    @Override
    public void showErrorMessageForUserNameOrPassword() {
        Snackbar.make(password,
                "Please check your Username or Password.",
                Snackbar.LENGTH_LONG)
                .show();
    }

    @Override
    public void showErrorMessageForMaxLoginAttempt() {
        Snackbar.make(login,
                "You have exceeded MAX attempt.",
                Snackbar.LENGTH_LONG)
                .show();
    }

    @Override
    public void showMessageForLoginSuccess() {
        Snackbar.make(login,
                "Login successful.",
                Snackbar.LENGTH_LONG)
                .show();
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
