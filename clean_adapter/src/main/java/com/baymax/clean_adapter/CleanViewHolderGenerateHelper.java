package com.baymax.clean_adapter;

import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 辅助生成ItemType与创建ViewHolder
 * <p>
 * 当RecyclerView中需要展示的元素类型比较多的时候
 * Adapter的getItemType、createViewHolder和onBindViewHolder方法中会存在大量的代码
 * 增加了复杂性并造成阅读的困难。
 * <p>
 * 为了减少Adapter中的代码，减少代码的耦合性，将各自的ViewHolder的创建放在相应的{@link IViewHolderFactory}中，分散管理。
 * 由于RecyclerView中需要通过itemType来生成不同的ViewHolder，本类会负责itemType的生成以及找到相应的factory来创建ViewHolder
 * <p>
 * 所有{@link ViewHolderFactory}注解的类会
 * 通过注解处理器将相同类型category的ViewHolderFactory的实例放入到同一个List中，
 * 在初始化本类的时候通过构造函数将该List传入。
 * <p>
 * 解析过程中，会遍历viewHolderFactoryList，计算生成itemType，并建立四组映射关系，返回ItemType与创建ViewHolder
 * 四组映射关系的作用如下：
 * 在获取ItemType时，
 * 利用{@link CleanViewHolderGenerateHelper#itemClassViewHolderFactoryMap}可以
 * 通过Item的class对象获取其对应的{@link IViewHolderFactory}，然后调用factory的getViewHolderClass方法获取ItemClass对应的ViewHolderClass
 * 再通过{@link CleanViewHolderGenerateHelper#viewHolderClassItemTypeMap}找到ViewHolderClass对应的ItemType。
 * 这样就可以在{@link android.support.v7.widget.RecyclerView.Adapter#getItemViewType(int)}方法中通过Item获取到其对应的ItemType。
 * <p>
 * 在创建ViewHolder时，
 * 利用{@link CleanViewHolderGenerateHelper#itemTypeViewHolderClassMap}可以
 * 通过ItemType获取到对应的ViewHolderClass
 * 利用{@link CleanViewHolderGenerateHelper#itemTypeViewHolderFactoryMap}可以
 * 通过ItemType获取到对应的ViewHolderFactory
 * 然后调用ViewHolderFactory的create方法，传入ViewHolderClass，创建对应的ViewHolder实例。
 * 这样就可以在{@link android.support.v7.widget.RecyclerView.Adapter#onCreateViewHolder(ViewGroup, int)}方法中通过ItemType获取其对应的ViewHolder实例
 */
public class CleanViewHolderGenerateHelper<ExtraData> implements IViewHolderGenerateHelper<ExtraData> {
    private Map<Class, IViewHolderFactory> itemClassViewHolderFactoryMap = new HashMap<>();
    private Map<Integer, IViewHolderFactory> itemTypeViewHolderFactoryMap = new HashMap<>();
    private Map<Class, Integer> viewHolderClassItemTypeMap = new HashMap<>();
    private Map<Integer, Class> itemTypeViewHolderClassMap = new HashMap<>();

    private List<Object> viewHolderFactoryList = new ArrayList<>();

    public CleanViewHolderGenerateHelper(List<Object> viewHolderFactoryList) {
        this.viewHolderFactoryList.addAll(viewHolderFactoryList);
        generateRelatedMap();
    }

    private void generateRelatedMap() {
        int factoryCount = 0;
        for (Object factory : viewHolderFactoryList) {
            if (factory instanceof IViewHolderFactory) {
                IViewHolderFactory viewHolderFactory = (IViewHolderFactory) factory;
                if (TerraModule.DEBUG && itemClassViewHolderFactoryMap.containsKey(viewHolderFactory.getItemClass())) {
                    throw new IllegalArgumentException("viewHolderClass: " + viewHolderFactory.getItemClass() + "has existed, please check your viewHolderFactory");
                }
                itemClassViewHolderFactoryMap.put(viewHolderFactory.getItemClass(), viewHolderFactory);
                Class[] viewHolderClassArray = viewHolderFactory.getViewHolderClassList();
                int viewHolderClassCount = 0;
                for (Class clazz : viewHolderClassArray) {
                    int itemType = factoryCount * 1000 + viewHolderClassCount;
                    viewHolderClassItemTypeMap.put(clazz, itemType);
                    itemTypeViewHolderClassMap.put(itemType, clazz);
                    itemTypeViewHolderFactoryMap.put(itemType, viewHolderFactory);
                    viewHolderClassCount++;
                }
                factoryCount++;
            }
        }
    }

    @Override
    public int getItemType(Object item) {
        IViewHolderFactory factory = itemClassViewHolderFactoryMap.get(item.getClass());
        if (factory == null) {
            return DummyCleanViewHolder.viewType;
        }
        Class viewHolderClass = factory.getViewHolderClass(item);
        if (viewHolderClass == DummyCleanViewHolder.class) {
            return DummyCleanViewHolder.viewType;
        }
        Integer itemType = viewHolderClassItemTypeMap.get(viewHolderClass);
        if (itemType == null) {
            if (TerraModule.DEBUG) {
                throw new NullPointerException("please check if " + viewHolderClass + " class is added in its factory's getViewHolderList Method");
            }
            return DummyCleanViewHolder.viewType;
        }
        return itemType;
    }

    @Override
    public BaseCleanViewHolder createViewHolder(ViewGroup parent, int itemType, ExtraData extraData) {
        Class viewHolderClass = itemTypeViewHolderClassMap.get(itemType);
        if (viewHolderClass == null) {
            return new DummyCleanViewHolder(parent);
        }
        return itemTypeViewHolderFactoryMap.get(itemType).create(parent, viewHolderClass, extraData);
    }



}
