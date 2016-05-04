package com.cyou.zhimi.model.chat;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Description:
 * Copyright  : Copyright (c) 2015
 * Company    : 北京畅游天下网络科技有限公司
 * Author     : liujianguang
 * Date       : 2015/12/23
 **/

public class Chat {

    @Expose
    @SerializedName("chatId")
    public String chatId;

    @Expose
    @SerializedName("time")
    public String time;

    @Expose
    @SerializedName("headUrl")
    public String headUrl;

    @Expose
    @SerializedName("message")
    public String message;

}
