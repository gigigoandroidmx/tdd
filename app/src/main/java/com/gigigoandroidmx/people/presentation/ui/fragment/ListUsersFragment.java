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

package com.gigigoandroidmx.people.presentation.ui.fragment;

import android.support.v4.app.Fragment;
import android.view.View;

import com.gigigoandroidmx.kmvp.MvpFragment;
import com.gigigoandroidmx.people.R;
import com.gigigoandroidmx.people.common.net.ServiceClient;
import com.gigigoandroidmx.people.common.net.ServiceClientFactory;
import com.gigigoandroidmx.people.data.RestApi;
import com.gigigoandroidmx.people.data.repository.UserRepository;
import com.gigigoandroidmx.people.data.repository.mapper.UserEntityToUserMapper;
import com.gigigoandroidmx.people.domain.usecase.GetListUsersUseCase;
import com.gigigoandroidmx.people.presentation.model.UserViewModel;
import com.gigigoandroidmx.people.presentation.model.mapper.UserToUserViewModel;
import com.gigigoandroidmx.people.presentation.presenter.ListUsersPresenter;
import com.gigigoandroidmx.people.presentation.presenter.view.ListUsersView;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * A simple {@link Fragment} subclass.
 */
public class ListUsersFragment
        extends MvpFragment<ListUsersView, ListUsersPresenter>
        implements ListUsersView {


    //region BaseFragment members

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_list_users;
    }

    @Override
    protected void onInitializeUIComponents() {

    }

    @Override
    protected void onInitializeMembers() {
        presenter.getUsers(2);
    }

    @Override
    protected void onBindView(View root) {

    }

    @Override
    protected void onUnbindView() {

    }

    //endregion

    //region ListUsersView members

    @Override
    public void onFetchPeopleSuccess(List<UserViewModel> userViewModels) {

    }

    @Override
    public void onEmptyResult() {

    }

    @Override
    public void showProgress(boolean active) {

    }

    @Override
    public void showError(Throwable exception) {

    }

    //endregion

    //region MvpFragment members

    @Override
    protected ListUsersPresenter createPresenter() {
        RestApi api = ServiceClientFactory.createService(ServiceClient.getDefault(), RestApi.class);
        UserRepository repository = new UserRepository(api, new UserEntityToUserMapper());

        GetListUsersUseCase getListUsersUseCase = new GetListUsersUseCase(repository,
                Schedulers.io(),
                AndroidSchedulers.mainThread());
        UserToUserViewModel userViewModelMapper = new UserToUserViewModel();
        return new ListUsersPresenter(getListUsersUseCase, userViewModelMapper);
    }

    //endregion
}
