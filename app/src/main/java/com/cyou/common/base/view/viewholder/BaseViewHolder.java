package com.cyou.common.base.view.viewholder;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import butterknife.ButterKnife;


public abstract class BaseViewHolder<T> extends RecyclerView.ViewHolder {

    public static final String TAG = "BaseViewHolder";

    public Context mContext;

    public BaseViewHolder(View view) {
        super(view);
        mContext = view.getContext();
        ButterKnife.bind(view);
        Log.i(TAG, "CommonFooterVH: " + view);
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
    public abstract void onBindViewHolder(View view, T obj);

}
