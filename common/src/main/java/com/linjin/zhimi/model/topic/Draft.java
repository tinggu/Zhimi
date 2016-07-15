package com.linjin.zhimi.model.topic;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Description:
 * Copyright  : Copyright (c) 2015
 * Company    : 北京畅游天下网络科技有限公司
 * Author     : wangjia_bi
 * Date       : 2016/5/17 15:45
 */
public class Draft {

    @Expose
    @SerializedName("row_id")
    public String rowId;

    @Expose
    @SerializedName("uid")
    public String uid;

    @Expose
    @SerializedName("topic_id")
    public String topicId;

    @Expose
    @SerializedName("content")
    public String content;

}
