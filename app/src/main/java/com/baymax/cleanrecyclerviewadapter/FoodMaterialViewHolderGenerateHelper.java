package com.baymax.cleanrecyclerviewadapter;

import android.view.ViewGroup;

import com.baymax.clean_adapter.BaseCleanViewHolder;
import com.baymax.clean_adapter.CleanViewHolderGenerateHelper;
import com.baymax.clean_adapter.IViewHolderGenerateHelper;
import com.baymax.viewholder.FoodMaterialViewHolderFactoryListCreator;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by baymax on 27,十月,2018
 */
public class FoodMaterialViewHolderGenerateHelper implements IViewHolderGenerateHelper<MarketInfo> {
    private CleanViewHolderGenerateHelper<MarketInfo> cleanViewHolderGenerateHelper;

    public FoodMaterialViewHolderGenerateHelper() {
        List<Object> foodMaterialList = new ArrayList<>();
        foodMaterialList.addAll(FoodMaterialViewHolderFactoryListCreator.createFactoryList());
        cleanViewHolderGenerateHelper = new CleanViewHolderGenerateHelper<>(foodMaterialList);
    }

    @Override
    public int getItemType(Object item) {
        return cleanViewHolderGenerateHelper.getItemType(item);
    }

    @Override
    public BaseCleanViewHolder createViewHolder(ViewGroup parent, int itemType, MarketInfo marketInfo) {
        return cleanViewHolderGenerateHelper.createViewHolder(parent, itemType, marketInfo);
    }
}
