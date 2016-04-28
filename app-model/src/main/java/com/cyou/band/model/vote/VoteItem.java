package com.cyou.band.model.vote;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import io.realm.annotations.Ignore;

/**
 * Description:
 * Copyright  : Copyright (c) 2015
 * Company    : 北京畅游天下网络科技有限公司
 * Author     : liujianguang
 * Date       : 2016/3/30
 **/

public class VoteItem {

    /**
     * 选项id
     */
    @Expose
    @SerializedName("itemId")
    public long itemId;
    /**
     * 选项内容
     */
    @Expose
    @SerializedName("content")
    public String content;
    /**
     * 选号
     */
    @Expose
    @SerializedName("serialNum")
    public String serialNum;
    /**
     * 投票数量
     */
    @Expose
    @SerializedName("voteNum")
    public int voteNum;
    /**
     * 是否选择
     */
    @Ignore
    public boolean isSelect;

    @Override
    public String toString() {
        return "VoteItem{" +
                "itemId=" + itemId +
                ", content='" + content + '\'' +
                ", serialNum='" + serialNum + '\'' +
                ", voteNum=" + voteNum +
                ", isSelect=" + isSelect +
                '}';
    }
}
