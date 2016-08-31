package com.linjin.zhimi.model;

import com.cyou.common.entity.RESTResult;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Description:
 * Copyright  : Copyright (c) 2015
 * Company    : 北京畅游天下网络科技有限公司
 * Author     : liujianguang
 * Date       : 2015/12/30
 **/

public class Timestamp extends RESTResult<Long> {

    @Expose
    @SerializedName("time")
    public long timestamp;

    @Override
    public Long getData() {
        return timestamp;
    }
}
