package com.baymax.clean_adapter;

import android.content.Context;
import android.content.res.Resources;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public abstract class BaseCleanViewHolder<Item> extends RecyclerView.ViewHolder {
    protected static final int GONE = View.GONE;
    protected static final int VISIBLE = View.VISIBLE;
    protected static final int INVISIBLE = View.INVISIBLE;

    /**
     * 一般情况下请重写本构造函数
     */
    public BaseCleanViewHolder(ViewGroup parent, @LayoutRes int layoutId) {
        super(LayoutInflater.from(parent.getContext()).inflate(layoutId, parent, false));
    }

    /**
     * 在用到组合方式的时候重写本构造函数
     */
    public BaseCleanViewHolder(View itemView) {
        super(itemView);
    }

    public abstract void onBindViewHolder(Item item);

    protected <T extends View> T findViewById(@IdRes int id) {
        return itemView.findViewById(id);
    }

    protected Resources getResources() {
        return itemView.getResources();
    }

    protected Context getContext() {
        return itemView.getContext();
    }

    protected void onAttach() {

    }

    protected void onDetach() {

    }

    protected void onRecycled() {

    }
}
