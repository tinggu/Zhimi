package com.cyou.band.model.vote;

import com.cyou.band.model.BaseModel;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Description:
 * Copyright  : Copyright (c) 2015
 * Company    : 北京畅游天下网络科技有限公司
 * Author     : liujianguang
 * Date       : 2016/3/30
 **/

public class VoteInfo extends BaseModel {

    /**
     * 创建人名字
     */
    @Expose
    @SerializedName("name")
    public String name;
    /**
     * 创建人头像
     */
    @Expose
    @SerializedName("avatar")
    public String avatar;
    /**
     * 创建时间
     */
    @Expose
    @SerializedName("createTime")
    public long createTime;
    /**
     * 投票内容
     */
    @Expose
    @SerializedName("content")
    public String content;
    /**
     * 是否投票 0：没有 1：投了
     */
    @Expose
    @SerializedName("isVote")
    public byte isVote;

    /**
     * 用户投票选项id(没有返回空字符串，多个以英文逗号分隔)
     */
    @Expose
    @SerializedName("itemIds")
    public String itemIds;

    /**
     * 选项列表
     */
    @Expose
    @SerializedName("itemList")
    public List<VoteItem> itemList;

    @Override
    public String toString() {
        return "VoteInfo{" +
                "name='" + name + '\'' +
                ", avatar='" + avatar + '\'' +
                ", createTime=" + createTime +
                ", content='" + content + '\'' +
                ", isVote=" + isVote +
                ", itemIds='" + itemIds + '\'' +
                ", itemList=" + itemList +
                '}';
    }
}
