package com.zhimi.model.group;

import com.zhimi.model.BasePagModel;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Description:
 * Copyright  : Copyright (c) 2015
 * Company    : 北京畅游天下网络科技有限公司
 * Author     : liujianguang
 * Date       : 2015/12/28
 **/

public class GroupList extends BasePagModel{

    /**
     * 数据集合
     */
    @Expose
    @SerializedName("list")
    public List<Group> list;

    public GroupList(List<Group> list) {
        this.list = list;
    }

    @Override
    public String toString() {
        return "GroupList{" +
                "list=" + list +
                '}';
    }
}
