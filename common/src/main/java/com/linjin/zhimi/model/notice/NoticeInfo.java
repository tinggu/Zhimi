package com.linjin.zhimi.model.notice;

import com.cyou.common.entity.BaseBean;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Description:
 * Copyright  : Copyright (c) 2015
 * Company    : 北京畅游天下网络科技有限公司
 * Author     : liujianguang
 * Date       : 2016/4/6
 **/

public class NoticeInfo extends BaseBean {

    /**
     * 公告标题
     */
    @Expose
    @SerializedName("title")
    public String title;
    /**
     * 创建日期
     */
    @Expose
    @SerializedName("createTime")
    public long createTime;
    /**
     * 公告内容
     */
    @Expose
    @SerializedName("content")
    public String content;
    /**
     * 公告图片
     */
    @Expose
    @SerializedName("image")
    public String image;

}
