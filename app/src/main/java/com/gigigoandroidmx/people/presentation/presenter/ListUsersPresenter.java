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
import com.gigigoandroidmx.people.presentation.model.mapper.UserToUserViewModel;
import com.gigigoandroidmx.people.presentation.presenter.view.ListUsersView;

import java.util.List;

/**
 * Defines ...
 *
 * @author Juan Godinez Vera - December 13, 2017
 * @version 0.0.1
 * @since 0.0.1
 */
public class ListUsersPresenter
        extends BasePresenter<ListUsersView> {

    private final GetListUsersUseCase getListUsersUseCase;
    private final UserToUserViewModel userViewModelMapper;

    public ListUsersPresenter(@NonNull GetListUsersUseCase getListUsersUseCase,
                              @NonNull UserToUserViewModel userViewModelMapper) {
        this.getListUsersUseCase = getListUsersUseCase;
        this.userViewModelMapper = userViewModelMapper;
    }

    public void getUsers(int page) {
        GetListUsersUseCase.Params params = GetListUsersUseCase.Params.forPage(page);
        getListUsersUseCase.execute(new UserListObserver(), params);
    }


    @Override
    public void destroy() {
        super.destroy();

        if(null != getListUsersUseCase) getListUsersUseCase.dispose();
    }

    private final class UserListObserver
            extends UseCaseObserver<List<User>> {

        @Override
        public void onComplete() {
            if(!isViewAttached()) return;

            getView().showProgress(false);
        }

        @Override
        public void onError(Throwable e) {
            if(!isViewAttached()) return;

            getView().showProgress(false);
            getView().showError(e);
        }

        @Override
        public void onNext(List<User> users) {
            super.onNext(users);

            if(!isViewAttached()) return;

            if(null != users && !users.isEmpty()) {
                getView().onFetchPeopleSuccess(userViewModelMapper.map(users));
            } else {
                getView().onEmptyResult();
            }
        }
    }
}
