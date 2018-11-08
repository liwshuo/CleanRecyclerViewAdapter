package com.baymax.cleanrecyclerviewadapter;

import android.support.annotation.NonNull;
import android.util.Log;
import android.view.ViewGroup;

import com.baymax.clean_adapter.BaseCleanAdapter;
import com.baymax.clean_adapter.BaseCleanExtraDataViewHolder;
import com.baymax.clean_adapter.BaseCleanViewHolder;
import com.baymax.clean_adapter.IViewHolderGenerateHelper;

/**
 * Created by baymax on 27,十月,2018
 */
public class MarketAdapter extends BaseCleanAdapter<Object, MarketInfo> {

    public MarketAdapter(IViewHolderGenerateHelper viewHolderGenerateHelper, MarketInfo marketInfo) {
        super(viewHolderGenerateHelper, marketInfo);
    }

}
