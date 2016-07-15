package com.linjin.zhimi.model.group;

import com.cyou.common.entity.BaseBean;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Description:
 * Copyright  : Copyright (c) 2015
 * Company    : 北京畅游天下网络科技有限公司
 * Author     : TANGXUTAO
 * Date       : 2016/4/8
 **/

public class TopInfoList extends BaseBean {

    /**
     * 数据集合
     */
    @Expose
    @SerializedName("list")
    public List<TopInfo> list;

    public List<TopInfo> getTopList(){ return list;}

    @Override
    public String toString() {
        return "TopInfoList{" +
                "list=" + list +
                '}';
    }
}
