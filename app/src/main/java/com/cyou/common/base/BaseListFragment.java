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

    private TRecyclerView tRecyclerView;
    Class<? extends BaseViewHolder> headVH;
    protected int headType;
    protected Object headData;
    protected ListPresenter listPresenter;

    /**
     * @param vh 传入VH的类名
     * @return
     */
    public static BaseListFragment newInstance(Class<? extends BaseViewHolder> vh,
                                               String type,   ListPresenter listPresenter) {
        Bundle arguments = new Bundle();
        arguments.putString(C.VH_CLASS, vh.getCanonicalName());
        arguments.putString(C.TYPE, type);
        BaseListFragment fragment = new BaseListFragment();
        fragment.setArguments(arguments); 
        fragment.setPresenter(listPresenter);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        tRecyclerView = new TRecyclerView(getContext());
        tRecyclerView.setView(TUtil.forName(getArguments().getString(C.VH_CLASS)), listPresenter);
        if (headVH != null) {
            tRecyclerView.setHeadView(headVH, headType, headData);
        }
        return tRecyclerView;
    }


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (tRecyclerView != null) tRecyclerView.fetch();
    }

    public void setHeadView(Class<? extends BaseViewHolder> vh, int headType, Object headData) {
        this.headVH = vh;
        this.headType = headType;
        this.headData = headData;
    }

    public void setPresenter(ListPresenter listPresenter) {
        this.listPresenter = listPresenter;
    }
}
