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

import com.gigigoandroidmx.people.domain.usecase.GetUsers;
import com.gigigoandroidmx.people.presentation.presenter.view.PeopleView;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

import static org.mockito.Mockito.*;

/**
 * Defines ...
 *
 * @author Juan Godinez Vera - December 18, 2017
 * @version 0.0.1
 * @since 0.0.1
 */
public class PeoplePresenterTest {

    @Before
    public void setUp() throws Exception { }

    @After
    public void tearDown() throws Exception { }

    @Test
    public void checkIfLoginAttemptIsExceeded() {
        PeopleView peopleView = mock(PeopleView.class);
        GetUsers getUsers = new GetUsers(Schedulers.io(), AndroidSchedulers.mainThread());
        PeoplePresenter peoplePresenter = new PeoplePresenter(getUsers);
        peoplePresenter.attachView(peopleView);

        Assert.assertEquals(1, peoplePresenter.incrementLoginAttempt());
        Assert.assertEquals(2, peoplePresenter.incrementLoginAttempt());
        Assert.assertEquals(3, peoplePresenter.incrementLoginAttempt());
        Assert.assertTrue(peoplePresenter.isLoginAttemptExceeded());
    }

    @Test
    public void checkIfLoginAttemptIsNotExceeded() {
        PeopleView peopleView = mock(PeopleView.class);
        GetUsers getUsers = new GetUsers(Schedulers.io(), AndroidSchedulers.mainThread());
        PeoplePresenter peoplePresenter = new PeoplePresenter(getUsers);
        peoplePresenter.attachView(peopleView);
        Assert.assertTrue(peoplePresenter.isLoginAttemptExceeded());
    }

    @Test
    public void checkIfUsernameAndPasswordIsCorrect() {
        PeopleView peopleView = mock(PeopleView.class);
        GetUsers getUsers = new GetUsers(Schedulers.io(), AndroidSchedulers.mainThread());
        PeoplePresenter peoplePresenter = new PeoplePresenter(getUsers);
        peoplePresenter.attachView(peopleView);
        peoplePresenter.doLogin("peter@klaven", "cityslicka");
        verify(peopleView).showMessageForLoginSuccess();
    }

    @Test
    public void checkIfUsernameAndPasswordIsIncorrect() {
        PeopleView peopleView = mock(PeopleView.class);
        GetUsers getUsers = new GetUsers(Schedulers.io(), AndroidSchedulers.mainThread());
        PeoplePresenter peoplePresenter = new PeoplePresenter(getUsers);
        peoplePresenter.attachView(peopleView);
        peoplePresenter.doLogin("qwerty", "cityslicka");
        verify(peopleView).showErrorMessageForUserNameOrPassword();
    }

    @Test
    public void checkIfLoginAttemptIsExceededAndViewMethodCalled() {
        PeopleView peopleView = mock(PeopleView.class);
        GetUsers getUsers = new GetUsers(Schedulers.io(), AndroidSchedulers.mainThread());
        PeoplePresenter peoplePresenter = new PeoplePresenter(getUsers);
        peoplePresenter.attachView(peopleView);

        peoplePresenter.doLogin("qwerty", "cityslicka");
        peoplePresenter.doLogin("qwerty", "cityslicka");
        peoplePresenter.doLogin("qwerty", "cityslicka");
        peoplePresenter.doLogin("qwerty", "cityslicka");

        verify(peopleView).showErrorMessageForMaxLoginAttempt();
    }

}