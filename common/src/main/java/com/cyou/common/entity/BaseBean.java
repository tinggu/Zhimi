package com.cyou.common.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BaseBean {

    /**
     * 状态码
     */
    @Expose
    @SerializedName("code")
    private int code;
   

    public int getCode() {
        return code;
    }

    /**
     * message
     */
    @Expose
    @SerializedName("message")
    private String  msg;


    public String getMsg() {
        return msg;
    }

    @Override
    public String toString() {
        return "BaseBean{" +
                "code=" + code +
                ", data='" + msg + '\'' +
                '}';
    }

    
}
