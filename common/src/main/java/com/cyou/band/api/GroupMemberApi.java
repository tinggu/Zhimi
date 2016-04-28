package com.cyou.band.api;

import com.cyou.band.model.BaseModel;
import com.cyou.band.model.card.GroupCardList;
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
