package com.baymax.cleanrecyclerviewadapter.vegetable;

import com.baymax.cleanrecyclerviewadapter.FoodMaterial;

/**
 * Created by baymax on 27,十月,2018
 */
public class Vegetable extends FoodMaterial {
    public final static String CABBAGE = "cabbage";

    public Vegetable(String name, String type) {
        this.name = name;
        this.type = type;
    }
}
