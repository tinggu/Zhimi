package com.cyou.band.model.notice;

import com.cyou.band.model.BaseModel;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Description:
 * Copyright  : Copyright (c) 2015
 * Company    : 北京畅游天下网络科技有限公司
 * Author     : liujianguang
 * Date       : 2016/4/6
 **/

public class NoticeCreate extends BaseModel {

    /**
     * 公告id
     */
    @Expose
    @SerializedName("noticeId")
    public long noticeId;

}
