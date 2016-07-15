package com.linjin.zhimi.model.topic;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Description:
 * Copyright  : Copyright (c) 2015
 * Company    : 北京畅游天下网络科技有限公司
 * Author     : wangjia_bi
 * Date       : 2016/5/17 15:35
 */
public class TopicDetail extends TopicAnswer {
    
    @Expose
    @SerializedName("has_collect")
    public String hasCollect;

    @Expose
    @SerializedName("has_answer")
    public String hasAnswer;

    @Expose
    @SerializedName("has_draft")
    public Draft hasDraft;
}
