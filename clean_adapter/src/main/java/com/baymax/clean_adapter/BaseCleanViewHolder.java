package com.baymax.clean_adapter;

import android.content.Context;
import android.content.res.Resources;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.Adapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public abstract class BaseCleanViewHolder<Item> extends BaseCleanExtraDataViewHolder<Item, Object> {

    public BaseCleanViewHolder(ViewGroup parent, int layoutId) {
        super(parent, layoutId);
    }

    public BaseCleanViewHolder(View itemView) {
        super(itemView);
    }

    @Override
    public final void onBindViewHolder(Item item, Object o) {
        onBindViewHolder(item);
    }

    public void onBindViewHolder(Item item) {

    }
}
