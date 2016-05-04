package com.cyou.zhimi.model.im;

import com.cyou.zhimi.model.BasePagModel;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Description:
 * Copyright  : Copyright (c) 2015
 * Company    : 北京畅游天下网络科技有限公司
 * Author     : liujianguang
 * Date       : 2016/1/14
 **/

public class MessageGroup extends BasePagModel {

    /**
     * 数据集合
     */
    @Expose
    @SerializedName("list")
    public List<Message> list;

}
