package com.linjin.zhimi.model.account;

import com.cyou.common.entity.BaseBean;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Description:
 * Copyright  : Copyright (c) 2015
 * Company    : 北京畅游天下网络技术有限公司
 * Author     : jiaxiaowei
 * Date       : 2015/12/11 11:19
 */
public class UserModel extends BaseBean {

    @Expose
    @SerializedName("data")
    private User user;

    public User getUser() {
        return user;
    }


}
