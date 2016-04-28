package com.cyou.band.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by liujianguang on 2015/12/18.
 */
public class BasePagModel extends BaseModel {

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
