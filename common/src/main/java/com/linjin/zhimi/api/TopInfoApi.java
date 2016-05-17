package com.linjin.zhimi.api;

import com.zhimi.model.group.TopInfoList;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Description: 置顶相关api
 * Copyright  : Copyright (c) 2015
 * Company    : 北京畅游天下网络科技有限公司
 * Author     : TANGXUTAO
 * Date       : 2016/3/31
 **/

public interface TopInfoApi {

    /**
     * 获取置顶信息
     * @param groupId 群组id
     * @return
     */
    @FormUrlEncoded
    @POST("/getTopInfoList")
    Observable<TopInfoList> getTopInfoList(@Field("groupId") long groupId);
}
