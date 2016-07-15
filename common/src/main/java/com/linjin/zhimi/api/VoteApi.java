package com.linjin.zhimi.api;

import com.cyou.common.entity.BaseBean;
import com.linjin.zhimi.model.vote.VoteCreate;
import com.linjin.zhimi.model.vote.VoteInfo;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Description: 投票相关api
 * Copyright  : Copyright (c) 2015
 * Company    : 北京畅游天下网络科技有限公司
 * Author     : liujianguang
 * Date       : 2016/3/31
 **/

public interface VoteApi {

    /**
     * 获取投票信息
     *
     * @param voteId  投票id
     * @param groupId 群组id
     * @return
     */
    @FormUrlEncoded
    @POST("/getVoteInfo")
    Observable<VoteInfo> getVoteInfo(@Field("voteId") long voteId,
                                     @Field("groupId") long groupId);

    /**
     * 投票
     *
     * @param groupId 群组id
     * @param voteId  投票id
     * @param itemIds 选项id（多个以逗号分隔）
     * @return
     */
    @FormUrlEncoded
    @POST("/voteItem")
    Observable<BaseBean> doVote(@Field("groupId") long groupId,
                                @Field("voteId") long voteId,
                                @Field("itemIds") String itemIds);

    /**
     * 创建投票信息
     *
     * @param groupId    群组id
     * @param content    主题内容
     * @param items      选择（按照顺序每一项有URLEncoder("","UTF-8")）编码用逗号分隔组成一个字符串
     * @param selectType 选择类型 1:单选题2：多选题
     * @param isSynch    是否同步群圈 0：不同步 1：同步
     * @param isTop    是否置顶 0：不置顶1：置顶
     * @return
     */
    @FormUrlEncoded
    @POST("/createVoteInfo")
    Observable<VoteCreate> createVoteInfo(@Field("groupId") long groupId,
                                          @Field("content") String content,
                                          @Field("items") String items,
                                          @Field("selectType") byte selectType,
                                          @Field("isSynch") byte isSynch,
                                          @Field("isTop") byte isTop);

}
