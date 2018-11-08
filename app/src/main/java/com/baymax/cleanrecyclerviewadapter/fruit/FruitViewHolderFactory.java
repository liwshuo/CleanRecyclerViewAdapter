package com.baymax.cleanrecyclerviewadapter.fruit;

import com.baymax.clean_adapter.AbstractViewHolderFactory;
import com.baymax.clean_adapter.DummyCleanViewHolder;
import com.baymax.clean_adapter_annotation.ViewHolderFactory;

/**
 * Created by baymax on 27,十月,2018
 */
@ViewHolderFactory(category = "FoodMaterial")
public class FruitViewHolderFactory extends AbstractViewHolderFactory<Fruit> {
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
//    public BaseCleanExtraDataViewHolder create(ViewGroup parent, Class viewHolderClass, MarketInfo marketInfo) {
//        BaseCleanViewHolder viewHolder;
//        if (viewHolderClass == AppleViewHolder.class) {
//            viewHolder = new AppleViewHolder(parent, marketInfo);
//        } else if (viewHolderClass == OrangeViewHolder.class) {
//            viewHolder = new OrangeViewHolder(parent, marketInfo);
//        } else {
//            viewHolder = new DummyCleanViewHolder(parent);
//        }
//
//
//        return viewHolder;
//    }

}