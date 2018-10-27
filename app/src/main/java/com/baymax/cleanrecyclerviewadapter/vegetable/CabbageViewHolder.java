package com.baymax.cleanrecyclerviewadapter.vegetable;

import android.view.ViewGroup;
import android.widget.TextView;

import com.baymax.clean_adapter.BaseCleanViewHolder;
import com.baymax.cleanrecyclerviewadapter.MarketInfo;
import com.baymax.cleanrecyclerviewadapter.R;

/**
 * Created by baymax on 27,十月,2018
 */
public class CabbageViewHolder extends BaseCleanViewHolder<Vegetable> {
    private TextView vegetableName;

    public CabbageViewHolder(ViewGroup parent, MarketInfo marketInfo) {
        super(parent, R.layout.layout_cabbage_viewholder);
        vegetableName = findViewById(R.id.name);
    }

    @Override
    public void onBindViewHolder(Vegetable vegetable) {
        vegetableName.setText(vegetable.name);
    }
}
