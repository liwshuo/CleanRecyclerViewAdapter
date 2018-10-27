package com.baymax.cleanrecyclerviewadapter.vegetable;

import com.baymax.clean_adapter_annotation.ViewHolderFactory;
import com.baymax.cleanrecyclerviewadapter.AbstractFoodMaterialViewHolderFactory;

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
}
