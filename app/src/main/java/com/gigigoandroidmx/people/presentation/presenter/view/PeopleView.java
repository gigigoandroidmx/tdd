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

package com.gigigoandroidmx.people.presentation.presenter.view;

import com.gigigoandroidmx.kmvp.View;
import com.gigigoandroidmx.people.data.model.People;

import java.util.List;

/**
 * Defines ...
 *
 * @author Juan Godinez Vera - December 13, 2017
 * @version 0.0.1
 * @since 0.0.1
 */
public interface PeopleView
        extends View {

    void showErrorMessageForUserNameOrPassword();
    void showErrorMessageForMaxLoginAttempt();
    void showMessageForLoginSuccess();

    void onFetchPeopleSuccess(List<People> people);
    void showProgress(boolean active);
    void showError(Throwable exception);
}
