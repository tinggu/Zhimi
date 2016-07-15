package com.linjin.zhimi.model.topic;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Description:
 * Copyright  : Copyright (c) 2015
 * Company    : 北京畅游天下网络科技有限公司
 * Author     : wangjia_bi
 * Date       : 2016/5/17 15:30
 */
public class Topic {
    @Expose
    @SerializedName("row_id")
    public String rowId;

    @Expose
    @SerializedName("uid")
    public String uid;
    
    @Expose
    @SerializedName("title")
    public String title;

    @Expose
    @SerializedName("answer_total")
    public String answerTotal;
    
    @Expose
    @SerializedName("reply_total")
    public String replyTotal;

    @Expose
    @SerializedName("label")
    public List<String> label;
}
