package com.baymax.cleanrecyclerviewadapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.ViewGroup;

import com.baymax.clean_adapter.BaseCleanViewHolder;
import com.baymax.clean_adapter.DummyCleanViewHolder;
import com.baymax.cleanrecyclerviewadapter.area.FoodMaterialArea;
import com.baymax.cleanrecyclerviewadapter.area.FoodMaterialAreaViewHolder;
import com.baymax.cleanrecyclerviewadapter.fruit.AppleViewHolder;
import com.baymax.cleanrecyclerviewadapter.fruit.Fruit;
import com.baymax.cleanrecyclerviewadapter.fruit.OrangeViewHolder;
import com.baymax.cleanrecyclerviewadapter.meat.BeefViewHolder;
import com.baymax.cleanrecyclerviewadapter.meat.Meat;
import com.baymax.cleanrecyclerviewadapter.meat.PorkViewHolder;
import com.baymax.cleanrecyclerviewadapter.vegetable.CabbageViewHolder;
import com.baymax.cleanrecyclerviewadapter.vegetable.Vegetable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lishuo on 29,十月,2018
 */
public class OldMarketAdapter extends RecyclerView.Adapter {
    private final List<Object> data;
    private final MarketInfo marketInfo;

    public OldMarketAdapter(MarketInfo marketInfo) {
        data = new ArrayList<>();
        this.marketInfo = marketInfo;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        RecyclerView.ViewHolder viewHolder;
        switch (i) {
            case 0:
                viewHolder = new AppleViewHolder(viewGroup, marketInfo);
                break;
            case 1:
                viewHolder = new OrangeViewHolder(viewGroup, marketInfo);
                break;
            case 2:
                viewHolder = new BeefViewHolder(viewGroup);
                break;
            case 3:
                viewHolder = new PorkViewHolder(viewGroup);
                break;
            case 4:
                viewHolder = new CabbageViewHolder(viewGroup, marketInfo);
                break;
            case 5:
                viewHolder = new FoodMaterialAreaViewHolder(viewGroup);
                break;
            default:
                viewHolder = new DummyCleanViewHolder(viewGroup);
        }
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        ((BaseCleanViewHolder) viewHolder).onBindViewHolder(data.get(i));
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public void updateData(List<Object> data) {
        this.data.clear();
        this.data.addAll(data);
        notifyDataSetChanged();
    }

    @Override
    public int getItemViewType(int position) {
        Object o = data.get(position);
        if (o instanceof Fruit) {
            if (Fruit.APPLE.equalsIgnoreCase(((Fruit) o).type)) {
                return 0;
            } else if (Fruit.ORANGE.equalsIgnoreCase(((Fruit) o).type)) {
                return 1;
            }
        } else if (o instanceof Meat) {
            if (Meat.BEEF.equalsIgnoreCase(((Meat) o).type)) {
                return 2;
            } else if (Meat.PORK.equalsIgnoreCase(((Meat) o).type)) {
                return 3;
            }
        } else if (o instanceof Vegetable) {
            if (Vegetable.CABBAGE.equalsIgnoreCase(((Vegetable) o).type)) {
                return 4;
            }
        } else if (o instanceof FoodMaterialArea) {
            return 5;
        }
        return DummyCleanViewHolder.viewType;
    }
}
