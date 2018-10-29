package com.baymax.cleanrecyclerviewadapter.fruit;

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
public class FruitViewHolderFactory extends AbstractFoodMaterialViewHolderFactory<Fruit> {
    @Override
    public Class getItemClass() {
        return Fruit.class;
    }

    @Override
    public Class[] getViewHolderClassList() {
        return new Class[]{
                AppleViewHolder.class,
                OrangeViewHolder.class
        };
    }

    @Override
    public Class getViewHolderClass(Fruit fruit) {
        switch (fruit.type) {
            case Fruit.APPLE:
                return AppleViewHolder.class;
            case Fruit.ORANGE:
                return OrangeViewHolder.class;
            default:
                return DummyCleanViewHolder.class;
        }
    }

//    @Override
//    public BaseCleanViewHolder create(ViewGroup parent, Class viewHolderClass, MarketInfo marketInfo) {
//        if (viewHolderClass == AppleViewHolder.class) {
//            return new AppleViewHolder(parent, marketInfo);
//        } else if (viewHolderClass == OrangeViewHolder.class) {
//            return new OrangeViewHolder(parent, marketInfo);
//        }
//        return new DummyCleanViewHolder(parent);
//    }
}
