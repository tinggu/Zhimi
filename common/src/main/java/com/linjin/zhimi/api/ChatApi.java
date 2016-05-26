package com.linjin.zhimi.api;

import com.linjin.zhimi.model.account.UserModel;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import rx.Observable;

public interface ChatApi {

    /**
     * 私聊申请列表
     *
     * @param page
     * @return
     */
    @FormUrlEncoded
    @POST("rel/friendApplyList")
    Observable<UserModel> friendApplyList(@Field("page") String page

    );

    /**
     * 私聊申请（添加 接受 删除）
     *
     * @param rowId 申请数据标示
     * @param type  操作类别
     * @return
     */
    @FormUrlEncoded
    @POST("rel/friendApply")
    Observable<UserModel> friendApply(@Field("row_id") String rowId,
                                      @Field("type") String type
    );


    @GET("rel/hotUser")
    Observable<UserModel> hotUser();


    @FormUrlEncoded
    @POST("/search/people")
    Observable<UserModel> people(@Field("keyword") String keyword
    );
    
}
