package com.baymax.clean_adapter;

import android.view.ViewGroup;

public interface IViewHolderGenerateHelper<ExtraData> {
    int getItemType(Object item);

    BaseCleanViewHolder createViewHolder(ViewGroup parent, int itemType, ExtraData extraData);
}
