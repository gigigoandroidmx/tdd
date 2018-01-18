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

import android.support.annotation.NonNull;

import com.gigigoandroidmx.kmvp.BasePresenter;
import com.gigigoandroidmx.kmvp.UseCaseObserver;
import com.gigigoandroidmx.people.domain.model.User;
import com.gigigoandroidmx.people.domain.usecase.GetListUsersUseCase;
import com.gigigoandroidmx.people.domain.usecase.LoginUseCase;
import com.gigigoandroidmx.people.presentation.model.mapper.UserToUserViewModel;
import com.gigigoandroidmx.people.presentation.presenter.view.ListUsersView;

import java.util.List;

import okhttp3.ResponseBody;

/**
 * Defines ...
 *
 * @author JG - December 13, 2017
 * @version 0.0.1
 * @since 0.0.1
 */
public class ListUsersPresenter
        extends BasePresenter<ListUsersView> {

    private final GetListUsersUseCase getListUsersUseCase;
    private final UserToUserViewModel userViewModelMapper;
    private final LoginUseCase loginUseCase;

    public ListUsersPresenter(@NonNull GetListUsersUseCase getListUsersUseCase,
                              @NonNull UserToUserViewModel userViewModelMapper,
                              @NonNull LoginUseCase loginUseCase) {
        this.getListUsersUseCase = getListUsersUseCase;
        this.userViewModelMapper = userViewModelMapper;
        this.loginUseCase = loginUseCase;
    }

    public void getUsers(int page, int perPage) {
        GetListUsersUseCase.Params params = GetListUsersUseCase.Params.forPage(page, perPage);
        getListUsersUseCase.execute(new UserListObserver(), params);
    }

    public void login(String email) {
        LoginUseCase.Params params = LoginUseCase.Params.withEmail(email);
        loginUseCase.execute(new LoginObserver(), params);
    }

    @Override
    public void destroy() {
        super.destroy();

        getListUsersUseCase.dispose();
    }

    private final class UserListObserver
            extends UseCaseObserver<List<User>> {

        @Override
        public void onComplete() {
            if (!isViewAttached()) return;

            getView().showProgress(false);
        }

        @Override
        public void onError(Throwable e) {
            if (!isViewAttached()) return;

            getView().showProgress(false);
            getView().showError(e);
        }

        @Override
        public void onNext(List<User> users) {
            super.onNext(users);

            if (!isViewAttached()) return;

            if(null != users && !users.isEmpty()) {
                getView().onFetchPeopleSuccess(userViewModelMapper.transform(users));
            } else {
                getView().onEmptyResult();
            }
        }
    }

    private final class LoginObserver extends UseCaseObserver<ResponseBody> {

        @Override
        public void onComplete() {
            super.onComplete();
            if (!isViewAttached()) return;

            getView().showProgress(false);
        }

        @Override
        public void onNext(ResponseBody responseBody) {
            super.onNext(responseBody);
            if (!isViewAttached()) return;

            getView().onEmptyResult();
        }

        @Override
        public void onError(Throwable e) {
            super.onError(e);
            if (!isViewAttached()) return;
            getView().showError(e);
        }
    }
}
