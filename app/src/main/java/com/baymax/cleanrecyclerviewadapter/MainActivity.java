package com.baymax.cleanrecyclerviewadapter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.baymax.cleanrecyclerviewadapter.area.FoodMaterialArea;
import com.baymax.cleanrecyclerviewadapter.fruit.Fruit;
import com.baymax.cleanrecyclerviewadapter.meat.Meat;
import com.baymax.cleanrecyclerviewadapter.vegetable.Vegetable;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MarketInfo marketInfo = new MarketInfo("Baymax SuperMarket");
        MarketAdapter marketAdapter = new MarketAdapter(new FoodMaterialViewHolderGenerateHelper(), marketInfo);

        RecyclerView foodMaterialList = findViewById(R.id.food_material_list);
        foodMaterialList.setAdapter(marketAdapter);
        foodMaterialList.setLayoutManager(new LinearLayoutManager(this));
        marketAdapter.updateData(createMarketData(), null);
    }

    private List<Object> createMarketData() {
        List<Object> marketData = new ArrayList<>();
        marketData.add(new FoodMaterialArea("fruit"));
        marketData.add(new Fruit("apple", Fruit.APPLE));
        marketData.add(new Fruit("apple", Fruit.APPLE));
        marketData.add(new Fruit("apple", Fruit.APPLE));
        marketData.add(new Fruit("orange", Fruit.ORANGE));

        marketData.add(new FoodMaterialArea("meat"));
        marketData.add(new Meat("beef", Meat.BEEF));
        marketData.add(new Meat("pork", Meat.PORK));

        marketData.add(new FoodMaterialArea("vegetable"));
        marketData.add(new Vegetable("cabbage", Vegetable.CABBAGE));
        marketData.add(new Vegetable("cabbage", Vegetable.CABBAGE));
        marketData.add(new Vegetable("cabbage", Vegetable.CABBAGE));
        return marketData;
    }
}
