package com.cyou.band.base.adapter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.hannesdorfmann.adapterdelegates.AbsAdapterDelegate;
import com.hannesdorfmann.adapterdelegates.AdapterDelegatesManager;

import java.util.List;

/**
 * Description:
 * Copyright  : Copyright (c) 2015
 * Company    : 北京畅游天下网络科技有限公司
 * Author     : wangjia_bi
 * Date       : 2016/2/2 14:31
 */
public abstract class BaseDelegateAdapter<T> extends RecyclerView.Adapter {

    private AdapterDelegatesManager<List<T>> delegatesManager;
    private List<T> items;

    public BaseDelegateAdapter(Activity activity, List<T> items) {
        // Delegates
        delegatesManager = new AdapterDelegatesManager<>();
        setupDelegates();
    }

    public abstract void setupDelegates();


    public void addDelegate(AbsAdapterDelegate<List<T>> delegate) {
        delegatesManager.addDelegate(delegate);
    }

    @Override
    public int getItemViewType(int position) {
        return delegatesManager.getItemViewType(items, position);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return delegatesManager.onCreateViewHolder(parent, viewType);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        delegatesManager.onBindViewHolder(items, position, holder);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}
