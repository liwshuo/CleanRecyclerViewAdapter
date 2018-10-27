package com.baymax.cleanrecyclerviewadapter.meat;

import android.view.ViewGroup;
import android.widget.TextView;

import com.baymax.clean_adapter.BaseCleanViewHolder;
import com.baymax.cleanrecyclerviewadapter.R;

/**
 * Created by baymax on 27,十月,2018
 */
public class PorkViewHolder extends BaseCleanViewHolder<Meat> {
    private TextView meatName;

    public PorkViewHolder(ViewGroup parent) {
        super(parent, R.layout.layout_pork_viewholder);
        meatName = findViewById(R.id.name);
    }

    @Override
    public void onBindViewHolder(Meat meat) {
        meatName.setText(meat.name);
    }
}
