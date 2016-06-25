package com.linjin.zhimi.base.viewholder;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import butterknife.ButterKnife;


/**
 * RecyclerView.ViewHolder 的公共类，做了一些封装。目的：
 * ViewHolder 与 Adapter 解耦，和 ViewHolder 相关的逻辑都放到 ViewHolder 里边，避免 Adapter 有相关逻辑
 */

public abstract class CommonViewHolder<T> extends RecyclerView.ViewHolder implements ViewHolderCreator {

//    public static int TYPE = 0;

    public CommonViewHolder(Context context, ViewGroup root, int layoutRes) {
        super(LayoutInflater.from(context).inflate(layoutRes, root, false));
        ButterKnife.bind(this, itemView);
    }

    public Context getContext() {
        return itemView.getContext();
    }

    /**
     * 用给定的 data 对 holder 的 view 进行赋值
     */
    public abstract void bindData(T t);

    public void setData(T t) {
        bindData(t);
    }

    public abstract int getType();
     
}