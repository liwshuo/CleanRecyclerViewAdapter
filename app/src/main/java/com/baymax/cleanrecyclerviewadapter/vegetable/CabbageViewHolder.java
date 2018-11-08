package com.baymax.cleanrecyclerviewadapter.vegetable;

import android.view.ViewGroup;
import android.widget.TextView;

import com.baymax.clean_adapter.BaseCleanExtraDataViewHolder;
import com.baymax.clean_adapter.BaseCleanViewHolder;
import com.baymax.cleanrecyclerviewadapter.MarketInfo;
import com.baymax.cleanrecyclerviewadapter.R;

/**
 * Created by baymax on 27,十月,2018
 */
public class CabbageViewHolder extends BaseCleanExtraDataViewHolder<Vegetable, MarketInfo> {
    private TextView vegetableName;

    public CabbageViewHolder(ViewGroup parent) {
        super(parent, R.layout.layout_cabbage_viewholder);
        vegetableName = findViewById(R.id.name);
    }

    @Override
    public void onBindViewHolder(Vegetable vegetable, MarketInfo marketInfo) {
        vegetableName.setText(vegetable.name);
    }
}
