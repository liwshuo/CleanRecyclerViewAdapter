package com.baymax.cleanrecyclerviewadapter.fruit;

import android.view.ViewGroup;
import android.widget.TextView;

import com.baymax.clean_adapter.BaseCleanViewHolder;
import com.baymax.cleanrecyclerviewadapter.MarketInfo;
import com.baymax.cleanrecyclerviewadapter.R;

/**
 * Created by baymax on 27,十月,2018
 */
public class OrangeViewHolder extends BaseCleanViewHolder<Fruit> {
    private TextView fruitName;

    public OrangeViewHolder(ViewGroup parent, MarketInfo marketInfo) {
        super(parent, R.layout.layout_orange_viewholder);
        fruitName = findViewById(R.id.name);
    }

    @Override
    public void onBindViewHolder(Fruit fruit) {
        fruitName.setText(fruit.name);
    }
}
