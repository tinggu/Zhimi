package com.linjin.zhimi.api;

import com.cyou.common.entity.BaseBean;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Description:
 * Copyright  : Copyright (c) 2015
 * Company    : 北京畅游天下网络科技有限公司
 * Author     : wangjia_bi
 * Date       : 2016/2/5 18:13
 */
public interface CheckToken {
    
    @FormUrlEncoded
    @POST("/checkToken")
    Observable<BaseBean> checkToken(@Field("token") String token);
}
