package com.baymax.clean_adapter;

import android.support.annotation.NonNull;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseCleanAdapter<Item, ExtraData> extends RecyclerView.Adapter<BaseCleanViewHolder> {

    private IViewHolderGenerateHelper<ExtraData> viewHolderGenerateHelper;
    private ExtraData extraData;
    protected final List<Item> dataList = new ArrayList<>();

    public BaseCleanAdapter(IViewHolderGenerateHelper<ExtraData> viewHolderGenerateHelper, ExtraData extraData) {
        this.viewHolderGenerateHelper = viewHolderGenerateHelper;
        this.extraData = extraData;
    }

    @NonNull
    @Override
    public BaseCleanViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return viewHolderGenerateHelper.createViewHolder(parent, viewType, extraData);
    }

    @Override
    public void onBindViewHolder(@NonNull BaseCleanViewHolder holder, int position) {
        holder.onBindViewHolder(dataList.get(position));
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
    public void onViewAttachedToWindow(@NonNull BaseCleanViewHolder holder) {
        holder.onAttach();
    }

    @Override
    public void onViewDetachedFromWindow(@NonNull BaseCleanViewHolder holder) {
        holder.onDetach();
    }

    @Override
    public void onViewRecycled(@NonNull BaseCleanViewHolder holder) {
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
