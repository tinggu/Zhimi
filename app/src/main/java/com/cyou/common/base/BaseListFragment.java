package com.cyou.common.base;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cyou.common.base.view.layout.TRecyclerView;
import com.cyou.common.base.view.viewholder.BaseViewHolder;
import com.cyou.common.utils.TUtil;
import com.linjin.zhimi.C;

import me.yokeyword.fragmentation.SupportFragment;


public class BaseListFragment extends SupportFragment {

    public TRecyclerView typeRecyclerView;

    /**
     * @param vh 传入VH的类名
     * @return
     */
    public static BaseListFragment newInstance(Class<? extends BaseViewHolder> vh, String type) {
        Bundle arguments = new Bundle();
        arguments.putString(C.VH_CLASS, vh.getCanonicalName());
        arguments.putString(C.TYPE, type);
        BaseListFragment fragment = new BaseListFragment();
        fragment.setArguments(arguments);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        typeRecyclerView = new TRecyclerView(getContext());
        typeRecyclerView.setView(TUtil.forName(getArguments().getString(C.VH_CLASS)));
        return typeRecyclerView;
    }


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (typeRecyclerView != null) typeRecyclerView.fetch();
    }

    public void setHeadView(Class<? extends BaseViewHolder> vh){
        typeRecyclerView.setHeadView(vh);
    }
}
