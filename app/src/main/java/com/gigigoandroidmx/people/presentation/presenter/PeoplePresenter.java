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
import com.gigigoandroidmx.people.presentation.presenter.view.PeopleView;

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

    public int incrementLoginAttempt() {
        loginAttempt += 1;
        return loginAttempt;
    }

    public boolean isLoginAttemptExceeded() {
        return loginAttempt >= MAX_LOGIN_ATTEMPT;
    }

    public boolean isLoginSuccess(String username, String password) {
        if(isLoginAttemptExceeded()) return false;

        if(username.equals("peter@klaven") && password.equals("cityslicka")) return true;

        // increment login attempt if it's fail
        incrementLoginAttempt();

        return false;
    }
}
