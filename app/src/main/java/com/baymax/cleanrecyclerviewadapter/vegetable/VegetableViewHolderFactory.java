package com.baymax.cleanrecyclerviewadapter.vegetable;

import android.view.ViewGroup;

import com.baymax.clean_adapter.BaseCleanViewHolder;
import com.baymax.clean_adapter_annotation.ViewHolderFactory;
import com.baymax.cleanrecyclerviewadapter.AbstractFoodMaterialViewHolderFactory;
import com.baymax.cleanrecyclerviewadapter.MarketInfo;

/**
 * Created by baymax on 27,十月,2018
 */
@ViewHolderFactory(category = "FoodMaterial")
public class VegetableViewHolderFactory extends AbstractFoodMaterialViewHolderFactory<Vegetable> {
    @Override
    public Class getItemClass() {
        return Vegetable.class;
    }

    @Override
    public Class[] getViewHolderClassList() {
        return new Class[]{
                CabbageViewHolder.class
        };
    }

    @Override
    public Class getViewHolderClass(Vegetable vegetable) {
        return CabbageViewHolder.class;
    }

//    @Override
//    public BaseCleanViewHolder create(ViewGroup parent, Class viewHolderClass, MarketInfo marketInfo) {
//        return new CabbageViewHolder(parent, marketInfo);
//    }
}
