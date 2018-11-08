package com.baymax.clean_adapter;

import android.support.annotation.NonNull;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseCleanAdapter<Item, ExtraData> extends RecyclerView.Adapter<BaseCleanExtraDataViewHolder> {

    private IViewHolderGenerateHelper viewHolderGenerateHelper;
    private ExtraData extraData;
    protected final List<Item> dataList = new ArrayList<>();

    public BaseCleanAdapter(IViewHolderGenerateHelper viewHolderGenerateHelper, ExtraData extraData) {
        this.viewHolderGenerateHelper = viewHolderGenerateHelper;
        this.extraData = extraData;
    }

    @NonNull
    @Override
    public BaseCleanExtraDataViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return viewHolderGenerateHelper.createViewHolder(parent, viewType);
    }

    @Override
    public void onBindViewHolder(@NonNull BaseCleanExtraDataViewHolder holder, int position) {
        holder.onBindViewHolder(dataList.get(position), extraData);
    }

    @Override
    public int getItemViewType(int position) {
        return viewHolderGenerateHelper.getItemType(dataList.get(position));
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    @Override
    public void onViewAttachedToWindow(@NonNull BaseCleanExtraDataViewHolder holder) {
        holder.onAttach();
    }

    @Override
    public void onViewDetachedFromWindow(@NonNull BaseCleanExtraDataViewHolder holder) {
        holder.onDetach();
    }

    @Override
    public void onViewRecycled(@NonNull BaseCleanExtraDataViewHolder holder) {
        holder.onRecycled();
    }

    public final void updateData(final List<Item> newItems, final DiffUtil.Callback callback) {
        if (callback != null) {
            DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(callback);
            dataList.clear();
            dataList.addAll(newItems);
            diffResult.dispatchUpdatesTo(this);
        } else {
            dataList.clear();
            dataList.addAll(newItems);
            notifyDataSetChanged();
        }
    }

}
