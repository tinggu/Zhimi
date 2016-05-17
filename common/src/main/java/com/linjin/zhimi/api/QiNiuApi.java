package com.linjin.zhimi.api;

import com.zhimi.model.QiNiuToken;

import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by liujianguang on 2015/12/18.
 */
public interface QiNiuApi {

    //http://qs.tayaapp.cn/v1.0/s/queryQiNiuToken.json s/queryQiNiuToken
//    @GET("v1.0/s/queryQiNiuToken.json")
//    Observable<QiNiuToken> getQNToken();

    /**
     * 获取七牛token
     *
     * @return
     */
    @POST("/getQNToken")
    Observable<QiNiuToken> getQNToken();

}
