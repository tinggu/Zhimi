package com.linjin.zhimi.api;

import com.zhimi.model.im.MessageGroup;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Description:
 * Copyright  : Copyright (c) 2015
 * Company    : 北京畅游天下网络科技有限公司
 * Author     : liujianguang
 * Date       : 2016/1/14
 **/

public interface MessageApi {

    /**
     * 获取消息列表
     *
     * @param currentPage 请求页码
     * @return
     */
    @FormUrlEncoded
    @POST("/getMessageList")
    Observable<MessageGroup> getMessageList(@Field("currentPage") int currentPage);

}
