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

package com.gigigoandroidmx.people.common.recyclerext;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Defines ...
 *
 * @author JG - December 29, 2017
 * @version 0.0.1
 * @since 0.0.1
 */
public class ViewHolderAdapter<T>
        extends RecyclerView.ViewHolder {

    private T item;
    private Context context;

    public ViewHolderAdapter(View itemView) {
        super(itemView);
        this.context = itemView.getContext();
    }

    public void onBindViewHolder(T item) {
        if(null == item) {
            throw new NullPointerException("Item is required");
        } else {
            this.item = item;
        }
    }

    public T getItem() {
        return this.item;
    }

    public Context getContext() {
        return this.context;
    }
}
