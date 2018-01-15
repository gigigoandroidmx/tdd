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

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.gigigoandroidmx.people.R;
import com.gigigoandroidmx.people.common.MvpBindingFragment;
import com.gigigoandroidmx.people.common.net.ServiceClient;
import com.gigigoandroidmx.people.common.net.ServiceClientFactory;
import com.gigigoandroidmx.people.common.recyclerext.EndlessScrollListener;
import com.gigigoandroidmx.people.data.RestApi;
import com.gigigoandroidmx.people.data.repository.UserRepository;
import com.gigigoandroidmx.people.data.repository.mapper.UserEntityToUserMapper;
import com.gigigoandroidmx.people.domain.usecase.GetListUsersUseCase;
import com.gigigoandroidmx.people.domain.usecase.LoginUseCase;
import com.gigigoandroidmx.people.presentation.model.UserViewModel;
import com.gigigoandroidmx.people.presentation.model.mapper.UserToUserViewModel;
import com.gigigoandroidmx.people.presentation.presenter.ListUsersPresenter;
import com.gigigoandroidmx.people.presentation.presenter.view.ListUsersView;
import com.gigigoandroidmx.people.presentation.ui.adapter.ListUsersAdapter;

import java.util.List;

import butterknife.BindView;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * A simple {@link Fragment} subclass.
 */
public class ListUsersFragment
        extends MvpBindingFragment<ListUsersView, ListUsersPresenter>
        implements ListUsersView {

    private static final int PAGE = 1;
    private static final int PER_PAGE = 10;
    public static final String PAGE_NUMBER_ARG = "page_number_arg";

    @BindView(R.id.swipe_refresh_layout_list_users)
    SwipeRefreshLayout refreshLayoutListUsers;

    @BindView(R.id.recycler_view_list_users)
    RecyclerView recyclerViewListUsers;

    private boolean isRefreshing;
    private ListUsersAdapter adapter;
    private int pageNumber;

    public static ListUsersFragment newInstance(int pageNumber) {

        Bundle args = new Bundle();
        args.putInt(PAGE_NUMBER_ARG, pageNumber);
        ListUsersFragment fragment = new ListUsersFragment();
        fragment.setArguments(args);
        return fragment;
    }

    //region BaseFragment members

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_list_users;
    }

    @Override
    protected void onRestoreExtras(Bundle arguments) {
        super.onRestoreExtras(arguments);
        pageNumber = arguments.getInt(PAGE_NUMBER_ARG);
    }

    @Override
    protected void onInitializeMembers() {
        adapter = new ListUsersAdapter();

        refreshLayoutListUsers.setColorSchemeResources(R.color.colorPrimary,
                R.color.colorAccent);
        refreshLayoutListUsers.setOnRefreshListener(refreshListener);

        presenter.getUsers(PAGE, PER_PAGE);
    }

    @Override
    protected void onInitializeUIComponents() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(),
                LinearLayoutManager.VERTICAL,
                false);
        recyclerViewListUsers.setLayoutManager(layoutManager);
        recyclerViewListUsers.setHasFixedSize(true);
        recyclerViewListUsers.setItemAnimator(new DefaultItemAnimator());
        recyclerViewListUsers.setAdapter(adapter);
        recyclerViewListUsers.addOnScrollListener(new EndlessScrollListener(layoutManager) {
            @Override
            public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {
                presenter.getUsers(page + 1, PER_PAGE);
            }

            @Override
            public void onHide() {
            }

            @Override
            public void onShow() {
            }
        });
    }

    //endregion

    //region ListUsersView members

    @Override
    public void showProgress(boolean active) {

    }

    @Override
    public void onFetchPeopleSuccess(List<UserViewModel> userViewModels) {
        Log.i("ListUsersFragment", "onFetchPeopleSuccess in page " + pageNumber);
        onRefreshCompleted();
        if (adapter.getItemCount() == 0) {
            adapter.set(userViewModels);
        } else {
            adapter.addRange(userViewModels);
        }
//        RecyclerExtensions.runLayoutAnimation(recyclerViewListUsers,
//                R.anim.layout_animation_from_bottom);
    }

    @Override
    public void onEmptyResult() {
        onRefreshCompleted();
        Toast.makeText(getContext(), "No se encontraron resultados.", Toast.LENGTH_LONG).show();
    }

    @Override
    public void showError(Throwable exception) {
        onRefreshCompleted();
        Toast.makeText(getContext(), exception.getMessage(), Toast.LENGTH_LONG).show();
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

        LoginUseCase loginUseCase = new LoginUseCase(
                repository, Schedulers.io(), AndroidSchedulers.mainThread());

        return new ListUsersPresenter(getListUsersUseCase, userViewModelMapper, loginUseCase);
    }

    //endregion

    private void onRefreshCompleted() {
        if (isRefreshing) {
            isRefreshing = false;
        }
    }

    private SwipeRefreshLayout.OnRefreshListener refreshListener = new SwipeRefreshLayout.OnRefreshListener() {
        @Override
        public void onRefresh() {
            refreshLayoutListUsers.setRefreshing(false);
            if (!isRefreshing) {
                adapter.clear();
                isRefreshing = true;
                presenter.getUsers(PAGE, PER_PAGE);
            }
        }
    };
}
