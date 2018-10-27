package com.baymax.cleanrecyclerviewadapter.area;

import com.baymax.clean_adapter_annotation.ViewHolderFactory;
import com.baymax.cleanrecyclerviewadapter.AbstractFoodMaterialViewHolderFactory;

/**
 * Created by baymax on 27,十月,2018
 */
@ViewHolderFactory(category = "FoodMaterial")
public class FoodMaterialAreaViewHolderFactory extends AbstractFoodMaterialViewHolderFactory<FoodMaterialArea> {
    @Override
    public Class getItemClass() {
        return FoodMaterialArea.class;
    }

    @Override
    public Class[] getViewHolderClassList() {
        return new Class[]{
                FoodMaterialAreaViewHolder.class
        };
    }

    @Override
    public Class getViewHolderClass(FoodMaterialArea foodMaterialArea) {
        return FoodMaterialAreaViewHolder.class;
    }
}
