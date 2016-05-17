package com.linjin.zhimi.api;

import com.zhimi.model.card.GroupCardList;

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

public interface GroupMemberApi {
    
    /**
     * 获取群成员身份列表
     *
     * @param groupId 群id
     * @return
     */
    @FormUrlEncoded
    @POST("/getGroupMemberList")
    Observable<GroupCardList> getGroupMemberList(@Field("groupId") long groupId);

   

}
