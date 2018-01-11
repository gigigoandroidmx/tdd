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
import android.support.annotation.AnimRes;
import android.support.v7.widget.RecyclerView;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;

/**
 * Defines ...
 *
 * @author Juan Godinez Vera - January 10, 2018
 * @version 0.0.1
 * @since 0.0.1
 */
public class RecyclerExtensions {

    public static final void runLayoutAnimation(RecyclerView recyclerView, @AnimRes int id) {
        if(null == recyclerView) return;

        final Context context = recyclerView.getContext();
        final LayoutAnimationController controller =
                AnimationUtils.loadLayoutAnimation(context, id);

        recyclerView.setLayoutAnimation(controller);
        recyclerView.getAdapter().notifyDataSetChanged();
        recyclerView.scheduleLayoutAnimation();
    }
}
