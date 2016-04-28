package com.cyou.band.model.chat;

import com.cyou.band.model.BasePagModel;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Description:
 * Copyright  : Copyright (c) 2015
 * Company    : 北京畅游天下网络科技有限公司
 * Author     : liujianguang
 * Date       : 2015/12/23
 **/

public class GroupChat extends BasePagModel {

    @Expose
    @SerializedName("groupId")
    public String groupId;

    /**
     * 数据集合
     */
    @Expose
    @SerializedName("list")
    public List<Chat> list;

}
