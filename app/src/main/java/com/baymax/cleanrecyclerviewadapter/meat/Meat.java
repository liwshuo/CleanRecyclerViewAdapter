package com.baymax.cleanrecyclerviewadapter.meat;

import com.baymax.cleanrecyclerviewadapter.FoodMaterial;

/**
 * Created by baymax on 27,十月,2018
 */
public class Meat extends FoodMaterial {
    public final static String BEEF = "beef";
    public final static String PORK = "pork";

    public Meat(String name, String type) {
        this.name = name;
        this.type = type;
    }
}
