package com.linjin.zhimi.model.topic;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Description:
 * Copyright  : Copyright (c) 2015
 * Company    : 北京畅游天下网络科技有限公司
 * Author     : wangjia_bi
 * Date       : 2016/5/17 15:22
 */
public class AnsContent {

    @Expose
    @SerializedName("pic")
    public String pic;
    
    /**
     * message
     */
    @Expose
    @SerializedName("text")
    public String text;
}
