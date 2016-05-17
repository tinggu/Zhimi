package com.linjin.zhimi.api;

import com.linjin.zhimi.model.Timestamp;

import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Description:
 * Copyright  : Copyright (c) 2015
 * Company    : 北京畅游天下网络科技有限公司
 * Author     : liujianguang
 * Date       : 2015/12/30
 **/

public interface TimestampApi {

    /**
     * 获取服务器时间
     * @return
     */
    @FormUrlEncoded
    @POST("getServiceTime")
    Observable<Timestamp> getServerTime();

}
