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

package com.gigigoandroidmx.people.presentation.presenter;

import com.gigigoandroidmx.kmvp.BasePresenter;
import com.gigigoandroidmx.kmvp.UseCaseObserver;
import com.gigigoandroidmx.people.domain.model.User;
import com.gigigoandroidmx.people.domain.usecase.GetUsers;
import com.gigigoandroidmx.people.presentation.presenter.view.PeopleView;

import java.util.List;

/**
 * Defines ...
 *
 * @author Juan Godinez Vera - December 13, 2017
 * @version 0.0.1
 * @since 0.0.1
 */
public class PeoplePresenter
        extends BasePresenter<PeopleView> {

    private static final int MAX_LOGIN_ATTEMPT = 3;

    private int loginAttempt;

    private final GetUsers getUsers;

    public PeoplePresenter(GetUsers getUsers) {
        this.getUsers = getUsers;
    }

    public int incrementLoginAttempt() {
        loginAttempt += 1;
        return loginAttempt;
    }

    public boolean isLoginAttemptExceeded() {
        return loginAttempt >= MAX_LOGIN_ATTEMPT;
    }

    public void doLogin(String username, String password) {
        //checkViewAttached();

        if(isLoginAttemptExceeded()) {
            getView().showErrorMessageForMaxLoginAttempt();
            return;
        }

        if(username.equals("peter@klaven") && password.equals("cityslicka")) {
            getView().showMessageForLoginSuccess();
            return;
        }

        // increment login attempt if it's fail
        incrementLoginAttempt();
        getView().showErrorMessageForUserNameOrPassword();
    }

    public void getUsers() {
        getUsers.execute(new UserListObserver());
    }


    @Override
    public void onDestroy() {
        super.onDestroy();

        if(null != getUsers) getUsers.dispose();
    }

    private final class UserListObserver
            extends UseCaseObserver<List<User>> {

        @Override
        public void onComplete() {
            super.onComplete();
        }

        @Override
        public void onError(Throwable e) {
            super.onError(e);
        }

        @Override
        public void onNext(List<User> users) {
            super.onNext(users);

            getView().onFetchPeopleSuccess(users);
        }
    }
}
