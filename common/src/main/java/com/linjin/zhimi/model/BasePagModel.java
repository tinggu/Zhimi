package com.linjin.zhimi.model;

import com.cyou.common.entity.BaseBean;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BasePagModel extends BaseBean {

    /**
     * 当前页数
     */
    @Expose
    @SerializedName("currentPage")
    public int currentPage;

    /**
     * 总页数
     */
    @Expose
    @SerializedName("totalPage")
    public int totalPage;

}
