package com.cyou.band.api;

import com.cyou.band.model.BaseModel;
import com.cyou.band.model.group.Group;
import com.cyou.band.model.group.GroupList;
import com.cyou.band.model.group.create.GroupCreate;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Description:
 * Copyright  : Copyright (c) 2015
 * Company    : 北京畅游天下网络科技有限公司
 * Author     : liujianguang
 * Date       : 2016/1/8
 **/

public interface GroupApi {

    /**
     * 获取群列表
     *
     * @param currentPage 请求页码
     * @return
     */
    @FormUrlEncoded
    @POST("/getGroupList")
    Observable<GroupList> requestGroupList(@Field("currentPage") int currentPage);

    /**
     * 创建群
     *
     * @param name  群名称
     * @param bgImg 群背景图片
     * @return
     */
    @FormUrlEncoded
    @POST("/createGroup")
    Observable<GroupCreate> createGroup(@Field("name") String name, @Field("bgImg") String bgImg, @Field("identityId") long identityId);

    /**
     * 搜索群组
     *
     * @param currentPage 当前页码
     * @param name        搜索词
     * @return
     */
    @FormUrlEncoded
    @POST("/searchGroup")
    Observable<GroupList> searchGroup(@Field("currentPage") int currentPage, @Field("name") String name);

    /**
     * 群信息
     *
     * @param groupId 群id
     * @return
     */
    @FormUrlEncoded
    @POST("/getGroupInfo")
    Observable<Group> getGroupInfo(@Field("groupId") long groupId);

    /**
     * 修改群 (设置信息)
     *
     * @param groupId    群id
     * @param name       名字 （没用 不传）
     * @param bgImg      背景图片（没用 不传）
     * @param openType   公开度 1：公开 2：半公开 3：不公开 （没用 不传）
     * @param category   分类（没用 不传）
     * @param des        描述（没用 不传）
     * @param searchType 搜索类型 0：搜索不到 1：可以搜到（没有不传）
     * @param checkType  验证类型 0：不需要验证1：需要验证（没有不传）
     * @return
     */
    @FormUrlEncoded
    @POST("/updateGroup")
    Observable<BaseModel> updateGroup(@Field("groupId") long groupId,
                                      @Field("name") String name,
                                      @Field("bgImg") String bgImg,
                                      @Field("openType") byte openType,
                                      @Field("category") int category,
                                      @Field("des") String des,
                                      @Field("searchType") byte searchType,
                                      @Field("checkType") byte checkType);

    /**
     * 修改群 (设置信息)
     *
     * @param groupId    群id
     * @param searchType 搜索类型 0：搜索不到 1：可以搜到（没有不传）
     * @param checkType  验证类型 0：不需要验证1：需要验证（没有不传）
     * @return
     */
    @FormUrlEncoded
    @POST("/updateGroup")
    Observable<BaseModel> updateGroup(@Field("groupId") long groupId,
                                      @Field("des") String des,
                                      @Field("searchType") byte searchType,
                                      @Field("checkType") byte checkType);

    /**
     * 申请加入群
     *
     * @param groupId    群id
     * @param identityId 身份id
     * @return
     */
    @FormUrlEncoded
    @POST("/joinGroup")
    Observable<BaseModel> applyJoinGroup(@Field("groupId") long groupId, @Field("identityId") long identityId);

    /**
     * 通过群审核
     *
     * @param groupId
     * @param userId
     * @return
     */
    @FormUrlEncoded
    @POST("/passGroup")
    Observable<BaseModel> passGroup(@Field("groupId") long groupId, @Field("userId") long userId);

}
