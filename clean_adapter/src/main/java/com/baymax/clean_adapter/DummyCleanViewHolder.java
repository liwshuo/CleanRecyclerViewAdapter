package com.baymax.clean_adapter;

import android.view.ViewGroup;

/**
 * Created by lishuo on 29,十月,2018
 */
public class DummyCleanViewHolder extends BaseCleanViewHolder<Object> {
    public static final int viewType = -1;

    public DummyCleanViewHolder(ViewGroup parent) {
        super(parent, R.layout.dummy_view_holder);
    }

    @Override
    public void onBindViewHolder(Object card) {

    }
}
