package com.baymax.cleanrecyclerviewadapter.area;

import com.baymax.clean_adapter.AbstractViewHolderFactory;
import com.baymax.clean_adapter_annotation.ViewHolderFactory;

/**
 * Created by baymax on 27,十月,2018
 */
@ViewHolderFactory(category = "FoodMaterial")
public class FoodMaterialAreaViewHolderFactory extends AbstractViewHolderFactory<FoodMaterialArea> {
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
