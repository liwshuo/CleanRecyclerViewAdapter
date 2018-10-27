package com.baymax.cleanrecyclerviewadapter;

import com.baymax.clean_adapter.BaseCleanAdapter;
import com.baymax.clean_adapter.IViewHolderGenerateHelper;

/**
 * Created by baymax on 27,十月,2018
 */
public class MarketAdapter extends BaseCleanAdapter<Object, MarketInfo> {

    public MarketAdapter(IViewHolderGenerateHelper<MarketInfo> viewHolderGenerateHelper, MarketInfo marketInfo) {
        super(viewHolderGenerateHelper, marketInfo);
    }

}
