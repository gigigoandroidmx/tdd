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

package com.gigigoandroidmx.kmvp;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Defines ...
 *
 * @author Juan Godinez Vera - December 13, 2017
 * @version 0.0.1
 * @since 0.0.1
 */
public abstract class BaseFragment
        extends Fragment {

    protected Context context;

    @LayoutRes
    protected abstract int getLayoutId();
    protected abstract void onInitializeUIComponents();
    protected abstract void onInitializeMembers();
    protected abstract void onBindView(View root);
    protected abstract void onUnbindView();
    protected void onRestoreExtras(Bundle arguments) { }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (null != getArguments()) {
            onRestoreExtras(getArguments());
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(getLayoutId(), container, false);
        onBindView(root);
        return root;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        onInitializeMembers();
        onInitializeUIComponents();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        onUnbindView();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        this.context = null;
    }
}
