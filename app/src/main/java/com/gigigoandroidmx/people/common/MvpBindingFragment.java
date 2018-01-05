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

package com.gigigoandroidmx.people.common;

import com.gigigoandroidmx.kmvp.MvpFragment;
import com.gigigoandroidmx.kmvp.Presenter;
import com.gigigoandroidmx.kmvp.View;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Defines ...
 *
 * @author Juan Godinez Vera - January 05, 2018
 * @version 0.0.1
 * @since 0.0.1
 */
public abstract class MvpBindingFragment<V extends View, P extends Presenter<V>>
        extends MvpFragment<V, P> {

    private Unbinder unbinder;

    @Override
    protected void onBindView(android.view.View root) {
        unbinder = ButterKnife.bind(this, root);
    }

    @Override
    protected void onUnbindView() {
        if(null != unbinder) unbinder.unbind();
    }
}
