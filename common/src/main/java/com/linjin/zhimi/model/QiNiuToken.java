package com.linjin.zhimi.model;

import com.cyou.common.entity.BaseBean;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by liujianguang on 2015/12/18.
 */
public class QiNiuToken extends BaseBean {

    @Expose
    @SerializedName("token")
    public String token;

    @Override
    public String toString() {
        return "QiNiuToken{" +
                "token='" + token + '\'' +
                '}';
    }
}
