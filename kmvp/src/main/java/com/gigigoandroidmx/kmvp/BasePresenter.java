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

import java.lang.ref.WeakReference;
import java.util.List;

/**
 * Defines ...
 *
 * @author Juan Godinez Vera - December 13, 2017
 * @version 0.0.1
 * @since 0.0.1
 */
public abstract class BasePresenter<V extends View>
        implements Presenter<V> {

    protected final String TAG = getClass().getSimpleName();

    private WeakReference<V> viewReference;
    private List<Object> parameters;

    public void attachView(V view) {
        viewReference = new WeakReference<>(view);
    }

    public void detachView() {
        if (viewReference != null) {
            viewReference.clear();
            viewReference = null;
        }
    }

    @Override
    public void onStart() { }

    @Override
    public void onResume() { }

    @Override
    public void onPause() { }

    @Override
    public void onStop() { }

    @Override
    public void onDestroy() { }

    @Override
    public void handleError(Throwable exception) { }

    public V getView() {
        return null == viewReference ? null : viewReference.get();
    }

    public boolean isViewAttached() {
        return null != getView();
    }

    public void checkViewAttached() {
        if (!isViewAttached()) throw new MvpViewNotAttachedException();
    }

    public static class MvpViewNotAttachedException
            extends RuntimeException {
        public MvpViewNotAttachedException() {
            super("Please call Presenter.attachView(View) before requesting data to the Presenter");
        }
    }
}
