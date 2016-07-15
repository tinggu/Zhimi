package com.linjin.zhimi.api;

import com.cyou.common.entity.BaseBean;
import com.linjin.zhimi.model.account.UserModel;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import rx.Observable;

public interface TopicApi {

    /**
     * 匿题列表
     *
     * @param uid
     * @param page
     * @param item
     * @param label
     * @return
     */
    @FormUrlEncoded
    @POST("topic/topicList")
    Observable<UserModel> list(@Field("uid") String uid,
                               @Field("page") String page,
                               @Field("item") String item,
                               @Field("label") String label
    );


    @FormUrlEncoded
    @POST("topic/show")
    Observable<UserModel> show(@Field("uid") String uid,
                               @Field("row_id") String row_id
    );


    /**
     * 创建匿题
     */
    @FormUrlEncoded
    @POST("topic/add")
    Observable<UserModel> add(@Field("uid") String uid,
                              @Field("title") String title,
                              @Field("label") String label,
                              @Field("content") String content
    );


    @FormUrlEncoded
    @POST("topic/collectAdd")
    Observable<BaseBean> collectAdd(@Field("uid") String uid,
                                    @Field("row_id") String row_id
    );

    @FormUrlEncoded
    @POST("topic/collectDel")
    Observable<BaseBean> collectDel(@Field("uid") String uid,
                                    @Field("row_id") String row_id
    );

    
}
