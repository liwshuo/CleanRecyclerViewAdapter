package com.baymax.cleanrecyclerviewadapter.meat;

import com.baymax.clean_adapter.AbstractViewHolderFactory;
import com.baymax.clean_adapter.DummyCleanViewHolder;
import com.baymax.clean_adapter_annotation.ViewHolderFactory;

/**
 * Created by baymax on 27,十月,2018
 */
@ViewHolderFactory(category = "FoodMaterial")
public class MeatViewHolderFactory extends AbstractViewHolderFactory<Meat> {
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
//    public BaseCleanExtraDataViewHolder create(ViewGroup parent, Class viewHolderClass, MarketInfo marketInfo) {
//        if (viewHolderClass == BeefViewHolder.class) {
//            return new BeefViewHolder(parent);
//        } else if (viewHolderClass == PorkViewHolder.class) {
//            return new PorkViewHolder(parent);
//        }
//        return new DummyCleanViewHolder(parent);
//    }
}
