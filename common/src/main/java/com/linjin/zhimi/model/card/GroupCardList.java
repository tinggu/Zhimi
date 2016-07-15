package com.linjin.zhimi.model.card;

import com.cyou.common.entity.BaseBean;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Description:
 * Copyright  : Copyright (c) 2015
 * Company    : 北京畅游天下网络科技有限公司
 * Author     : liujianguang
 * Date       : 2016/1/15
 **/

public class GroupCardList extends BaseBean {


    public GroupCardList(List<GroupCard> list) {
        this.list = list;
    }
    /**
     * 数据集合
     */
    @Expose
    @SerializedName("list")
    public List<GroupCard> list;

    @Override
    public String toString() {
        return "GroupCardList{" +
                "list=" + list +
                '}';
    }
}
