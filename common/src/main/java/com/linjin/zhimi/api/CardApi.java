package com.linjin.zhimi.api;

import com.cyou.zhimi.model.BaseModel;
import com.cyou.zhimi.model.card.CardCreateResult;
import com.cyou.zhimi.model.card.GroupCardList;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Description:
 * Copyright  : Copyright (c) 2015
 * Company    : 北京畅游天下网络科技有限公司
 * Author     : liujianguang
 * Date       : 2016/1/25
 **/

public interface CardApi {

    /**
     * @param name
     * @param age
     * @param sex
     * @param des
     * @param avatar
     * @return
     */
    @FormUrlEncoded
    @POST("/createUserIdentity")
    Observable<CardCreateResult> createCard(@Field("name") String name,
                                            @Field("age") int age,
                                            @Field("sex") byte sex,
                                            @Field("des") String des,
                                            @Field("avatar") String avatar);

    /**
     * @param name
     * @param age
     * @param sex
     * @param des
     * @param avatar
     * @return
     */
    @FormUrlEncoded
    @POST("/updateUserIdentity")
    Observable<CardCreateResult> updateCard(@Field("name") String name,
                                            @Field("age") int age,
                                            @Field("sex") byte sex,
                                            @Field("des") String des,
                                            @Field("avatar") String avatar,
                                            @Field("identityId") long identityId);

    /**
     * 获取用户名片列表
     *
     * @return
     */
    @POST("/getUserIdentityList")
    Observable<GroupCardList> requestGroupCardList();

    /**
     * 删除用户名片
     *
     * @return
     */
    @FormUrlEncoded
    @POST("/deleteUserIdentity")
    Observable<BaseModel> deleteCardByIdentityId(@Field("identityId") long identityId);

}