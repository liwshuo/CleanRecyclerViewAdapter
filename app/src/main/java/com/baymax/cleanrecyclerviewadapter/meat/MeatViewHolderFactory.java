package com.baymax.cleanrecyclerviewadapter.meat;

import android.view.ViewGroup;

import com.baymax.clean_adapter.BaseCleanViewHolder;
import com.baymax.clean_adapter.CleanViewHolderGenerateHelper;
import com.baymax.clean_adapter.DummyCleanViewHolder;
import com.baymax.clean_adapter_annotation.ViewHolderFactory;
import com.baymax.cleanrecyclerviewadapter.AbstractFoodMaterialViewHolderFactory;
import com.baymax.cleanrecyclerviewadapter.MarketInfo;

/**
 * Created by baymax on 27,十月,2018
 */
@ViewHolderFactory(category = "FoodMaterial")
public class MeatViewHolderFactory extends AbstractFoodMaterialViewHolderFactory<Meat> {
    @Override
    public Class getItemClass() {
        return Meat.class;
    }

    @Override
    public Class[] getViewHolderClassList() {
        return new Class[]{
                BeefViewHolder.class,
                PorkViewHolder.class
        };
    }

    @Override
    public Class getViewHolderClass(Meat meat) {
        switch (meat.type) {
            case Meat.BEEF:
                return BeefViewHolder.class;
            case Meat.PORK:
                return PorkViewHolder.class;
            default:
                return DummyCleanViewHolder.class;
        }
    }

//    @Override
//    public BaseCleanViewHolder create(ViewGroup parent, Class viewHolderClass, MarketInfo marketInfo) {
//        if (viewHolderClass == BeefViewHolder.class) {
//            return new BeefViewHolder(parent);
//        } else if (viewHolderClass == PorkViewHolder.class) {
//            return new PorkViewHolder(parent);
//        }
//        return new DummyCleanViewHolder(parent);
//    }
}
