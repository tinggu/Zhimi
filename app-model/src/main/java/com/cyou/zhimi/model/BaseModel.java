package com.cyou.zhimi.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by liujianguang on 2015/12/18.
 */
public class BaseModel {

    /**
     * 状态码
     */
    @Expose
    @SerializedName("stateCode")
    public int code;
    /**
     * 信息
     */
    @Expose
    @SerializedName("info")
    public String msg;

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    @Override
    public String toString() {
        return "BaseModel{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                '}';
    }
}
