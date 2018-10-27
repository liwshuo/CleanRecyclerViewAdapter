package com.baymax.cleanrecyclerviewadapter;

import android.view.ViewGroup;

import com.baymax.clean_adapter.BaseCleanViewHolder;
import com.baymax.clean_adapter.IViewHolderFactory;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public abstract class AbstractFoodMaterialViewHolderFactory<Item> implements IViewHolderFactory<Item, MarketInfo> {
    @Override
    public BaseCleanViewHolder create(ViewGroup parent, Class viewHolderClass, MarketInfo marketInfo) {
        BaseCleanViewHolder viewHolder = null;
        try {
            Constructor constructor = viewHolderClass.getConstructor(ViewGroup.class, MarketInfo.class);
            viewHolder = (BaseCleanViewHolder) constructor.newInstance(parent, marketInfo);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        if (viewHolder == null) {
            try {
                Constructor constructor = viewHolderClass.getConstructor(ViewGroup.class);
                viewHolder = (BaseCleanViewHolder) constructor.newInstance(parent);
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        }
        if (viewHolder == null && BuildConfig.DEBUG) {
            throw new IllegalArgumentException(viewHolderClass + "不是标准构造函数参数，请重写ViewHolderFactory的create方法自行初始化");
        }
        if (viewHolder == null) {
            viewHolder = new BaseCleanViewHolder(parent, R.layout.dummy_view_holder) {
                @Override
                public void onBindViewHolder(Object o) {

                }
            };
        }
        return viewHolder;
    }

}
