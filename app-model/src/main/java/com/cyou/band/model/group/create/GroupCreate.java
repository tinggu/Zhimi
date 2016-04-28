package com.cyou.band.model.group.create;

import com.cyou.band.model.BaseModel;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Description:
 * Copyright  : Copyright (c) 2015
 * Company    : 北京畅游天下网络科技有限公司
 * Author     : liujianguang
 * Date       : 2015/1/8
 **/

public class GroupCreate extends BaseModel {

    /**
     * 创建群成功返回groupId
     */
    @Expose
    @SerializedName("groupId")
    public long groupId;
    /**
     * 创建群时间
     */
    @Expose
    @SerializedName("createTime")
    public long createTime;

}
