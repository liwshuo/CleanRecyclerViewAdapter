package com.baymax.cleanrecyclerviewadapter.fruit;

import android.view.ViewGroup;
import android.widget.TextView;

import com.baymax.clean_adapter.BaseCleanViewHolder;
import com.baymax.cleanrecyclerviewadapter.MarketInfo;
import com.baymax.cleanrecyclerviewadapter.R;

/**
 * Created by baymax on 27,十月,2018
 */
public class AppleViewHolder extends BaseCleanViewHolder<Fruit> {
    private TextView fruitName;

    public AppleViewHolder(ViewGroup parent, MarketInfo marketInfo) {
        super(parent, R.layout.layout_apple_viewholder);
        fruitName = findViewById(R.id.name);
    }

    @Override
    public void onBindViewHolder(Fruit fruit) {
        fruitName.setText(fruit.name);
    }
}
