package com.baymax.cleanrecyclerviewadapter.fruit;

import com.baymax.clean_adapter.CleanViewHolderGenerateHelper;
import com.baymax.clean_adapter_annotation.ViewHolderFactory;
import com.baymax.cleanrecyclerviewadapter.AbstractFoodMaterialViewHolderFactory;

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
                return CleanViewHolderGenerateHelper.DummyCleanViewHolder.class;
        }
    }

}
