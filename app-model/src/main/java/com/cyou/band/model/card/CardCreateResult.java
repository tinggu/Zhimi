package com.cyou.band.model.card;

import com.cyou.band.model.BaseModel;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Description: 创建名片结果
 * Copyright  : Copyright (c) 2015
 * Company    : 北京畅游天下网络科技有限公司
 * Author     : liujianguang
 * Date       : 2016/1/15
 **/

public class CardCreateResult extends BaseModel {

    /**
     * 身份id
     */
    @Expose
    @SerializedName("identityId")
    public String identityId;
}
