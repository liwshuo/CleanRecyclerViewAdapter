package com.baymax.cleanrecyclerviewadapter.fruit;

import com.baymax.cleanrecyclerviewadapter.FoodMaterial;

/**
 * Created by baymax on 27,十月,2018
 */
public class Fruit extends FoodMaterial {
    public final static String APPLE = "apple";
    public final static String ORANGE = "orange";

    public Fruit(String name, String type) {
        this.name = name;
        this.type = type;
    }
}
