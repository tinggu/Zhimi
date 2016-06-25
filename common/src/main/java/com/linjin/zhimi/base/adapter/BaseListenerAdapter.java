package com.linjin.zhimi.base.adapter;

import android.view.View;

import com.linjin.zhimi.base.listener.ItemClickListener;
import com.linjin.zhimi.base.listener.ItemLongClickListener;

import static android.support.v7.widget.RecyclerView.Adapter;
import static android.support.v7.widget.RecyclerView.ViewHolder;
import static android.view.View.OnClickListener;
import static android.view.View.OnLongClickListener;
import static com.linjin.zhimi.base.adapter.BaseListenerAdapter.BaseViewHolder;

/**
 * Description:
 * Copyright  : Copyright (c) 2015
 * Company    : 北京畅游天下网络科技有限公司
 * Author     : wangjia_bi
 * Date       : 2016/2/4 11:10
 */
public abstract class BaseListenerAdapter<VH extends BaseViewHolder> extends Adapter<VH> {
    protected ItemClickListener mItemClickListener;
    protected ItemLongClickListener mItemLongClickListener;


//    @Override
//    public VH onCreateViewHolder(ViewGroup parent, int viewType) {
//        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent,false);
//        VH vh = latest VH(itemView, mItemClickListener,mItemLongClickListener);
//        return vh;
//    }


    /**
     * 设置Item点击监听
     *
     * @param listener
     */
    public void setOnItemClickListener(ItemClickListener listener) {
        this.mItemClickListener = listener;
    }

    public void setOnItemLongClickListener(ItemLongClickListener listener) {
        this.mItemLongClickListener = listener;
    }

    public static class BaseViewHolder
            extends ViewHolder
            implements OnClickListener, OnLongClickListener {

        private ItemClickListener mItemClickListener;
        private ItemLongClickListener mItemLongClickListener;

        public BaseViewHolder(View itemView,
                              ItemClickListener itemClickListener,
                              ItemLongClickListener itemLongClickListener) {
            super(itemView);
            this.mItemClickListener = itemClickListener;
            this.mItemLongClickListener = itemLongClickListener;
            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);
        }

        /**
         * 点击监听
         */
        @Override
        public void onClick(View view) {
            if (mItemClickListener != null) {
                mItemClickListener.onItemClick(view, getAdapterPosition());
            }
        }

        /**
         * 长按监听
         */
        @Override
        public boolean onLongClick(View view) {
            if (mItemLongClickListener != null) {
                mItemLongClickListener.onItemLongClick(view, getAdapterPosition());
            }
            return true;
        }
    }
}
