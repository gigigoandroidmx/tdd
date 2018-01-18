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

import android.os.Bundle;
import android.support.annotation.Nullable;

/**
 * Defines ...
 *
 * @author JG - December 13, 2017
 * @version 0.0.1
 * @since 0.0.1
 */
public abstract class MvpFragment<V extends View, P extends Presenter<V>>
        extends BaseFragment {

    protected P presenter;
    protected abstract P createPresenter();

    @SuppressWarnings("unchecked")
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if(null == presenter) presenter = createPresenter();

        if(null == presenter) throw new NullPointerException("The presenter must not be null.");

        if(!(this instanceof View))
            throw new MvpFragmentNotImplementedException();

        presenter.attachView((V) this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        if(null != presenter) presenter.detachView();
    }

    public static class MvpFragmentNotImplementedException
            extends RuntimeException {
        public MvpFragmentNotImplementedException() {
            super("The MvpFragment must implement View. This is required by the presenter.");
        }
    }
}
