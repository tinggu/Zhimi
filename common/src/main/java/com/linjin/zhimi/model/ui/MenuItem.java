package com.linjin.zhimi.model.ui;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Description:
 * Copyright  : Copyright (c) 2015
 * Company    : 北京畅游天下网络科技有限公司
 * Author     : wangjia_bi
 * Date       : 2016/5/17 11:55
 */
public class MenuItem {
    @Expose
    @SerializedName("iamge")
    String iconResId;

    @Expose
    @SerializedName("title")
    String textResId;

    @Expose
    @SerializedName("code")
    String command;
}
