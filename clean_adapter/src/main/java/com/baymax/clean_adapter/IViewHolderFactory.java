package com.baymax.clean_adapter;

import android.view.ViewGroup;


/**
 * 所有的ViewHolder必须由相应的{@link IViewHolderFactory}创建
 * ViewHolder的分类依赖于其内部持有的{@link Item}数据结构
 * 一种{@link Item}可以对应一个或者多个ViewHolder
 * </p>
 * {@link IViewHolderFactory#getItemClass()}
 * 返回Item的Class对象
 * <p>
 * {@link IViewHolderFactory#getViewHolderClassList()}
 * 返回Item对应的所有ViewHolder的数组
 * <p>
 * {@link IViewHolderFactory#getViewHolderClass(Item)}
 * 根据条件返回ViewHolder
 * <p>
 * {@link IViewHolderFactory#create(ViewGroup, Class)}
 * 返回和传入ViewHolderClass一致的ViewHolder的实例
 * 这里为什么要用ViewHolder的class来作为判断条件来生成ViewHolder的实例呢
 * 原因是在createViewHolder的时候，只有ItemType，而ItemType是在{@link CleanViewHolderGenerateHelper}中生成的
 * 我们在{@link IViewHolderFactory}中是无从得知自己的ItemType的。
 * 所以我们会在{@link CleanViewHolderGenerateHelper}利用ItemType找到ViewHolderClass，然后再间接利用ViewHolderClass创建其实例。
 * <p>
 * 注意，以上三个ViewHolder相关的方法中，ViewHolder的数量和类型必须一致。
 * <p>
 * 四个方法的具体用途参考{@link CleanViewHolderGenerateHelper}
 *
 * @param <Item>
 */
public interface IViewHolderFactory<Item> {

    Class getItemClass();

    Class[] getViewHolderClassList();

    Class getViewHolderClass(Item item);

    BaseCleanExtraDataViewHolder create(ViewGroup parent, Class viewHolderClass);
}

