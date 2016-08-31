package com.linjin.zhimi.model;

import com.cyou.common.entity.RESTResult;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by liujianguang on 2015/12/18.
 */
public class QiNiuToken extends RESTResult<String> {

    @Expose
    @SerializedName("token")
    public String token;

    @Override
    public String getData() {
        return token;
    }

    @Override
    public String toString() {
        return "QiNiuToken{" +
                "token='" + token + '\'' +
                '}';
    }
}
