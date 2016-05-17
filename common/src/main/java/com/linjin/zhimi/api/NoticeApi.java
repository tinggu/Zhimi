package com.linjin.zhimi.api;

import com.linjin.zhimi.model.notice.NoticeCreate;
import com.linjin.zhimi.model.notice.NoticeInfo;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Description: 群公告
 * Copyright  : Copyright (c) 2015
 * Company    : 北京畅游天下网络科技有限公司
 * Author     : liujianguang
 * Date       : 2016/4/6
 **/

public interface NoticeApi {

    /**
     * 创建公告
     *
     * @param groupId 群组id
     * @param content 内容
     * @param title   标题
     * @param images  图片
     * @param isTop   是否置顶 0：不置顶 1：置顶
     * @param isSyn   是否同步 0：不同步 1：同步
     * @return
     */
    @FormUrlEncoded
    @POST("/createNotice")
    Observable<NoticeCreate> createNotice(@Field("groupId") long groupId,
                                          @Field("content") String content,
                                          @Field("title") String title,
                                          @Field("images") String images,
                                          @Field("isTop") byte isTop,
                                          @Field("isSyn") byte isSyn);

    /**
     * 获取公告信息
     *
     * @param noticeId 公告id
     * @param groupId  群组id
     * @return
     */
    @FormUrlEncoded
    @POST("/getNoticeInfo")
    Observable<NoticeInfo> getNoticeInfo(@Field("noticeId") long noticeId,
                                         @Field("groupId") long groupId);

}
