package com.linjin.zhimi.base.adapter;

import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.ViewGroup;

import com.linjin.zhimi.base.viewholder.CommonViewHolder;
import com.linjin.zhimi.base.viewholder.ViewHolderCreator;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wli on 15/11/23.
 * 现在还仅仅支持单类型 item，多类型 item 稍后在重构
 */
public class CommonListAdapter<T> extends RecyclerView.Adapter<CommonViewHolder> {

    private SparseArray<ViewHolderCreator> vhArray;
    protected List<T> dataList;
//    private Class<?> vhClass;

    public CommonListAdapter() {
        vhArray = new SparseArray<>();
        dataList = new ArrayList<>();
    }

//    public CommonListAdapter(Class<?> vhClass) {
//        this.vhClass = vhClass;
//    }

    public void addViewHoder(int type, ViewHolderCreator vhc) {
//        int type = clazz.getField("TYPE")
        vhArray.append(type, vhc);
    }

    public List<T> getDataList() {
        return dataList;
    }

    public void setDataList(List<T> datas) {
        dataList.clear();
        if (null != datas) {
            dataList.addAll(datas);
        }
    }

    /**
     * 默认在最后插入
     *
     * @param datas
     */
    public void addDataList(List<T> datas) {
        dataList.addAll(datas);
    }

    @Override
    public CommonViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        ViewHolderCreator creator = vhArray.get(viewType);

        if (creator != null) {
            return creator.createView(parent, viewType);
        } else {
            throw new IllegalArgumentException("please add viewHolder by type = " + viewType);
        }
      
    }

    @Override
    public void onBindViewHolder(CommonViewHolder holder, int position) {
        if (position >= 0 && position < dataList.size()) {
            holder.bindData(dataList.get(position));
        }
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }
}