package com.linjin.zhimi.model.group;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Description:
 * Copyright  : Copyright (c) 2015
 * Company    : 北京畅游天下网络科技有限公司
 * Author     : TANGXUTAO
 * Date       : 2016/4/8
 **/

public class TopInfo {

    /**
     * 置顶ID
     */
    @Expose
    @SerializedName("topId")
    public long topId;

    /**
     * 置顶类型 1：通知2：投票3：报名
     */
    @Expose
    @SerializedName("type")
    public byte type;

    /**
     * 创建时间
     */
    @Expose
    @SerializedName("createTime")
    public long createTime;

    /**
     * 标题
     */
    @Expose
    @SerializedName("title")
    public String title;

    /**
     * 关联ID
     */
    @Expose
    @SerializedName("relatedId")
    public long relatedId;


    @Override
    public String toString() {
        return "TopInfo{" +
                "topId='" + topId + '\'' +
                ", type='" + type + '\'' +
                ", createTime=" + createTime +
                ", title='" + title + '\'' +
                '}';
    }
}
