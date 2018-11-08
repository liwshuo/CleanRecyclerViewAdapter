package com.baymax.clean_adapter;

import android.content.Context;
import android.content.res.Resources;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public abstract class BaseCleanExtraDataViewHolder<Item, ExtraData> extends RecyclerView.ViewHolder {
    protected static final int GONE = View.GONE;
    protected static final int VISIBLE = View.VISIBLE;
    protected static final int INVISIBLE = View.INVISIBLE;

    /**
     * 一般情况下请重写本构造函数
     * 本构造函数可以确保所有的ViewHolder拥有相同的参数，比如单参数ViewGroup
     * 这样可以写一个实现了{@link IViewHolderFactory}的抽象类
     * 实现{@link IViewHolderFactory#create(ViewGroup, Class)}方法，在该方法中使用反射初始化ViewHolder
     * 这样可以减少大量的初始化ViewHolder的代码
     */
    public BaseCleanExtraDataViewHolder(ViewGroup parent, @LayoutRes int layoutId) {
        super(LayoutInflater.from(parent.getContext()).inflate(layoutId, parent, false));
    }

    /**
     * 一般在用到组合方式的时候重写本构造函数
     * 如果遇到一个数据结构A包含另外一个数据结构B，而B已经有了自己的ViewHolder的情况下，
     * 可以在A的ViewHolder中使用组合方式引用B的ViewHolder，B的ViewHolder可以利用本构造函数来初始化。
     */
    public BaseCleanExtraDataViewHolder(View itemView) {
        super(itemView);
    }

    public abstract void onBindViewHolder(Item item, ExtraData extraData);

    /**
     * 不需要再写很多的itemView.findViewById了
     *
     * @param id  View对应的id
     * @param <T> View的控件类型
     * @return View的实例
     */
    protected <T extends View> T findViewById(@IdRes int id) {
        return itemView.findViewById(id);
    }

    protected Resources getResources() {
        return itemView.getResources();
    }

    protected Context getContext() {
        return itemView.getContext();
    }

    /**
     * 在{@link RecyclerView.Adapter#onViewAttachedToWindow(RecyclerView.ViewHolder)}时调用
     */
    protected void onAttach() {

    }

    /**
     * 在{@link RecyclerView.Adapter#onViewDetachedFromWindow(RecyclerView.ViewHolder)}时调用
     */
    protected void onDetach() {

    }

    /**
     * 在{@link RecyclerView.Adapter#onViewRecycled(RecyclerView.ViewHolder)}时调用
     */
    protected void onRecycled() {

    }

}
