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

import com.gigigoandroidmx.people.domain.usecase.GetListUsersUseCase;
import com.gigigoandroidmx.people.presentation.presenter.view.ListUsersView;

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
public class ListUsersPresenterTest {

    @Before
    public void setUp() throws Exception { }

    @After
    public void tearDown() throws Exception { }

    @Test
    public void checkIfLoginAttemptIsExceeded() {
        ListUsersView listUsersView = mock(ListUsersView.class);
        GetListUsersUseCase getListUsersUseCase = new GetListUsersUseCase(Schedulers.io(), AndroidSchedulers.mainThread(), repository);
        ListUsersPresenter listUsersPresenter = new ListUsersPresenter(getListUsersUseCase, userViewModelMapper);
        listUsersPresenter.attachView(listUsersView);

        Assert.assertEquals(1, listUsersPresenter.incrementLoginAttempt());
        Assert.assertEquals(2, listUsersPresenter.incrementLoginAttempt());
        Assert.assertEquals(3, listUsersPresenter.incrementLoginAttempt());
        Assert.assertTrue(listUsersPresenter.isLoginAttemptExceeded());
    }

    @Test
    public void checkIfLoginAttemptIsNotExceeded() {
        ListUsersView listUsersView = mock(ListUsersView.class);
        GetListUsersUseCase getListUsersUseCase = new GetListUsersUseCase(Schedulers.io(), AndroidSchedulers.mainThread(), repository);
        ListUsersPresenter listUsersPresenter = new ListUsersPresenter(getListUsersUseCase, userViewModelMapper);
        listUsersPresenter.attachView(listUsersView);
        Assert.assertTrue(listUsersPresenter.isLoginAttemptExceeded());
    }

    @Test
    public void checkIfUsernameAndPasswordIsCorrect() {
        ListUsersView listUsersView = mock(ListUsersView.class);
        GetListUsersUseCase getListUsersUseCase = new GetListUsersUseCase(Schedulers.io(), AndroidSchedulers.mainThread(), repository);
        ListUsersPresenter listUsersPresenter = new ListUsersPresenter(getListUsersUseCase, userViewModelMapper);
        listUsersPresenter.attachView(listUsersView);
        listUsersPresenter.doLogin("peter@klaven", "cityslicka");
        verify(listUsersView).showMessageForLoginSuccess();
    }

    @Test
    public void checkIfUsernameAndPasswordIsIncorrect() {
        ListUsersView listUsersView = mock(ListUsersView.class);
        GetListUsersUseCase getListUsersUseCase = new GetListUsersUseCase(Schedulers.io(), AndroidSchedulers.mainThread(), repository);
        ListUsersPresenter listUsersPresenter = new ListUsersPresenter(getListUsersUseCase, userViewModelMapper);
        listUsersPresenter.attachView(listUsersView);
        listUsersPresenter.doLogin("qwerty", "cityslicka");
        verify(listUsersView).showErrorMessageForUserNameOrPassword();
    }

    @Test
    public void checkIfLoginAttemptIsExceededAndViewMethodCalled() {
        ListUsersView listUsersView = mock(ListUsersView.class);
        GetListUsersUseCase getListUsersUseCase = new GetListUsersUseCase(Schedulers.io(), AndroidSchedulers.mainThread(), repository);
        ListUsersPresenter listUsersPresenter = new ListUsersPresenter(getListUsersUseCase, userViewModelMapper);
        listUsersPresenter.attachView(listUsersView);

        listUsersPresenter.doLogin("qwerty", "cityslicka");
        listUsersPresenter.doLogin("qwerty", "cityslicka");
        listUsersPresenter.doLogin("qwerty", "cityslicka");
        listUsersPresenter.doLogin("qwerty", "cityslicka");

        verify(listUsersView).showErrorMessageForMaxLoginAttempt();
    }

}