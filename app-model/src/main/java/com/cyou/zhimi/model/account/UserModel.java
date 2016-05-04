package com.cyou.zhimi.model.account;

import com.cyou.zhimi.model.BaseModel;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Description:
 * Copyright  : Copyright (c) 2015
 * Company    : 北京畅游天下网络技术有限公司
 * Author     : jiaxiaowei
 * Date       : 2015/12/11 11:19
 */
public class UserModel extends BaseModel {

    @Expose
    @SerializedName("data")
    User user;

    public User getUser() {
        return user;
    }


}
