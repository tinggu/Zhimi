package com.cyou.common.base.view.viewholder;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import butterknife.ButterKnife;


public abstract class BaseViewHolder<T> extends RecyclerView.ViewHolder {
    
    public Context mContext;

    public BaseViewHolder(View v) {
        super(v);
        mContext = v.getContext();
        ButterKnife.bind(v);
    }

    /**
     * ViewHolder的Type，同时也是它的LayoutId
     *
     * @return
     */
    public abstract int getType();

    /**
     * 绑定ViewHolder
     *
     * @return
     */
    public abstract void onBindViewHolder(View view, T obj) ;

}
