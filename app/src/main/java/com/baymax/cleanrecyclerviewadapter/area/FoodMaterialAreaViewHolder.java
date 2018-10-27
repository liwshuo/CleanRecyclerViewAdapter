package com.baymax.cleanrecyclerviewadapter.area;

import android.view.ViewGroup;
import android.widget.TextView;

import com.baymax.clean_adapter.BaseCleanViewHolder;
import com.baymax.cleanrecyclerviewadapter.R;

/**
 * Created by baymax on 27,十月,2018
 */
public class FoodMaterialAreaViewHolder extends BaseCleanViewHolder<FoodMaterialArea> {
    private TextView areaName;

    public FoodMaterialAreaViewHolder(ViewGroup parent) {
        super(parent, R.layout.layout_food_material_area_viewholder);
        areaName = findViewById(R.id.name);
    }

    @Override
    public void onBindViewHolder(FoodMaterialArea foodMaterialArea) {
        areaName.setText(foodMaterialArea.name);
    }
}
