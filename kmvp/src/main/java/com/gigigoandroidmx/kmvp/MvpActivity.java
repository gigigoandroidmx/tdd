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
 * @author Omar Bacilio - January 15, 2018
 * @version 0.0.1
 * @since 0.0.1
 */
public abstract class MvpActivity<V extends View, P extends Presenter<V>> extends BaseActivity {

    protected P presenter;

    protected abstract P createPresenter();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (presenter == null)
            presenter = createPresenter();

        if (presenter == null)
            throw new NullPointerException("The activity presenter must not be null.");

        if (!(this instanceof View))
            throw new MvpActivityNotImplementedException();

        presenter.attachView((V) this);
    }

    public static class MvpActivityNotImplementedException extends RuntimeException {
        public MvpActivityNotImplementedException() {
            super("The MvpActivity must implement a View. This is required by the presenter.");
        }
    }
}
