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

import android.support.annotation.LayoutRes;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Defines ...
 *
 * @author Juan Godinez Vera - December 29, 2017
 * @version 0.0.1
 * @since 0.0.1
 */
public abstract class RecyclerAdapter<T>
        extends RecyclerView.Adapter<ViewHolderAdapter<T>> {

    private ArrayList<T> itemsSource = new ArrayList();

    @Override
    public void onBindViewHolder(ViewHolderAdapter<T> holder, int position) {
        if(!this.isEmpty()) {
            holder.onBindViewHolder(this.itemsSource.get(position));
        }
    }

    @Override
    public int getItemCount() {
        return this.itemsSource != null?this.itemsSource.size():0;
    }

    public boolean isEmpty() {
        return this.getItemCount() == 0;
    }

    public View getView(ViewGroup parent, @LayoutRes int resourceId) {
        return LayoutInflater.from(parent.getContext()).inflate(resourceId, parent, false);
    }

    /**
     * Adds an object to the end of the sequence
     *
     * @param item
     */
    public void add(T item) {
        addRange(Collections.singletonList(item));
    }

    /**
     * Adds the elements of the specified collection to the end of the sequence
     *
     * @param items
     */
    public void addRange(Iterable<T> items) {
        if(items == null) return;

        for (T item : items) {
            itemsSource.add(item);
        }

        notifyDataSetChanged();
    }

    /**
     * Adds the array elements of the specified collection to the end of the sequence
     *
     * @param items
     */
    public void addRange(T... items) {
        if(items == null) return;

        for (T item : items) {
            itemsSource.add(item);
        }

        notifyDataSetChanged();
    }

    /**
     * Replaces the elements of the specified collection on the sequence
     *
     * @param items
     */
    public void set(Iterable<T> items) {
        if(items == null) return;

        ArrayList<T> arrayList = new ArrayList();

        for(T item : items) {
            arrayList.add(item);
        }

        itemsSource = arrayList;

        notifyDataSetChanged();
    }

    /**
     * Replaces the array elements of the specified collection on the sequence
     *
     * @param items
     */
    public void set(T... items) {
        if(items == null) return;

        ArrayList<T> arrayList = new ArrayList(items.length);

        for(T item : items) {
            arrayList.add(item);
        }

        itemsSource = arrayList;
        notifyDataSetChanged();
    }

    /**
     * Updates the first occurrence of a specific object from the sequence
     *
     * @param item
     */
    public void update(T item) {
        if(isEmpty()) return;
        int position = itemsSource.indexOf(item);
        if(position == -1) return;
        itemsSource.set(position, item);
        notifyDataSetChanged();
    }

    /**
     * Removes the first occurrence of a specific object from the sequence
     *
     * @param item
     */
    public void remove(T item) {
        if(isEmpty()) return;
        int position = itemsSource.indexOf(item);
        remove(position);
    }

    /**
     * Removes the object at the specified position in the sequence
     *
     * @param index
     */
    public void remove(int index) {
        if(index == -1) return;
        itemsSource.remove(index);
        notifyItemRemoved(index);
        notifyDataSetChanged();
    }

    /**
     * Retrieves all the elements that match the conditions defined by the specified predicate.
     *
     * @param predicate
     * @return
     */
    public Iterable<T> where(IPredicate<T> predicate) {
        if(isEmpty()) return null;
        List<T> result = new ArrayList<>();
        for(T item : itemsSource) {
            if(predicate.apply(item)) {
                result.add(item);
            }
        }

        return result;
    }

    /**
     * Removes all elements from the sequence
     */
    public void clear() {
        itemsSource.clear();
        notifyDataSetChanged();
    }

    /**
     * Gets all elements from the sequence
     *
     * @return
     */
    public ArrayList<T> items() {
        return itemsSource;
    }

}
