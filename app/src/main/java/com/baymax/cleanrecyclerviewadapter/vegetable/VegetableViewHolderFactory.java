package com.baymax.cleanrecyclerviewadapter.vegetable;

import com.baymax.clean_adapter.AbstractViewHolderFactory;
import com.baymax.clean_adapter_annotation.ViewHolderFactory;

/**
 * Created by baymax on 27,十月,2018
 */
@ViewHolderFactory(category = "FoodMaterial")
public class VegetableViewHolderFactory extends AbstractViewHolderFactory<Vegetable> {
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
//    public BaseCleanExtraDataViewHolder create(ViewGroup parent, Class viewHolderClass, MarketInfo marketInfo) {
//        return new CabbageViewHolder(parent, marketInfo);
//    }
}
