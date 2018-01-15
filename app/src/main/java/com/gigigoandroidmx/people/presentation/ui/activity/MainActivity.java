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


package com.gigigoandroidmx.people.presentation.ui.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import com.gigigoandroidmx.people.R;
import com.gigigoandroidmx.people.common.ExampleBaseActivity;
import com.gigigoandroidmx.people.presentation.ui.adapter.viewpager.HomePagerAdapter;
import com.gigigoandroidmx.people.presentation.ui.fragment.ListUsersFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class MainActivity extends ExampleBaseActivity {

    // region Constants
    // endregion Constants

    // region UI Components

    @BindView(R.id.viewpager_sections)
    ViewPager sectionsViewPager;

    // endregion UI Components

    // region Members
    private HomePagerAdapter homePagerAdapter;
    // endregion Members

    // region Activity Lifecycle
    // endregion Activity Lifecycle

    // region ExampleBaseActivity Methods

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void onRestoreExtras(Bundle arguments) {
        super.onRestoreExtras(arguments);
    }

    @Override
    protected void onInitializeMembers() {
        homePagerAdapter = new HomePagerAdapter(getSupportFragmentManager());
    }

    @Override
    protected void onInitializeUIComponents() {
        sectionsViewPager.setAdapter(homePagerAdapter);
        homePagerAdapter.setFragments(createFragmentPages(4));
    }

    // endregion ExampleBaseActivity Methods

    // region Interface Implementations
    // endregion Interface Implementations

    // region ButterKnife View Events
    // endregion ButterKnife View Events

    // region Public Methods
    // endregion Public Methods

    // region Private Methods
    private List<Fragment> createFragmentPages(int number) {
        List<Fragment> fragments = new ArrayList<>();

        for (int index = 0; index < number; index++) {
            fragments.add(ListUsersFragment.newInstance(index + 1));
        }

        return fragments;
    }
    // endregion Private Methods
}
