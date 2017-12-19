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
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.inputmethod.InputMethodManager;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

/**
 * Defines ...
 *
 * @author Juan Godinez Vera - December 19, 2017
 * @version 0.0.1
 * @since 0.0.1
 */
public abstract class BaseActivity
        extends AppCompatActivity {

    private Toolbar toolbar;

    @LayoutRes
    protected abstract int getLayoutId();
    protected abstract void onInitializeUIComponents();
    protected abstract void onInitializeMembers();
    protected abstract void onBindView();
    protected abstract void onUnbindView();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        onBindView();
        onInitializeUIComponents();
        onInitializeMembers();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        onUnbindView();
    }

    public void setupToolbar(Toolbar toolbar) {
        this.toolbar = toolbar;

        if(null != this.toolbar) {
            setSupportActionBar(this.toolbar);
        }
    }

    @Nullable
    public Toolbar getToolbar() {
        return toolbar;
    }

    public void setKeyBoardStateFrom(View view, boolean show) {
        if(null == view) {
            throw new NullPointerException("The view must not be null.");
        }

        InputMethodManager inputMethodManager
                = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);

        if(null != inputMethodManager) {
            if(show) {
                if(null != view.getApplicationWindowToken()) {
                    inputMethodManager.toggleSoftInputFromWindow(view.getApplicationWindowToken(),
                            InputMethodManager.SHOW_FORCED,
                            0);
                }
            } else {
                if(null != view.getWindowToken()) {
                    inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
                }
            }
        }
    }

}
