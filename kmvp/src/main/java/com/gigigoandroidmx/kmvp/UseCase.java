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

import io.reactivex.Observable;
import io.reactivex.Scheduler;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableObserver;

/**
 * Defines ...
 *
 * @author JG - December 19, 2017
 * @version 0.0.1
 * @since 0.0.1
 */
public abstract class UseCase<T, P> {

    private final CompositeDisposable compositeDisposable;
    private final Scheduler executorThread;
    private final Scheduler uiThread;

    public UseCase(Scheduler executorThread,
                   Scheduler uiThread) {
        this.compositeDisposable = new CompositeDisposable();
        this.executorThread = executorThread;
        this.uiThread = uiThread;
    }

    public void execute(DisposableObserver<T> disposableObserver, P parameters) {
        if(null == disposableObserver) {
            throw new NullPointerException("DisposableObserver must not be null");
        }

        final Observable<T> observable = createObservableUseCase(parameters)
                //.retryWhen(new RetryWithDelay(3, 30000))
                .subscribeOn(executorThread)
                .observeOn(uiThread);

        DisposableObserver observer = observable.subscribeWith(disposableObserver);
        compositeDisposable.add(observer);
    }

    public void dispose() {
        if (!compositeDisposable.isDisposed()) {
            compositeDisposable.dispose();
        }
    }

    protected abstract Observable<T> createObservableUseCase(P parameters);
}
