package com.baymax.clean_adapter;

import android.view.ViewGroup;

public interface IViewHolderGenerateHelper {
    int getItemType(Object item);

    BaseCleanExtraDataViewHolder createViewHolder(ViewGroup parent, int itemType);
}
