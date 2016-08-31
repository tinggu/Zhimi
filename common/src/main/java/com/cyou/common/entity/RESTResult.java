package com.cyou.common.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public abstract class RESTResult<T> {

    public static final int SUCCESS = 0;
    public static final int SIGN_OUT = 1;
    /**
     * 状态码
     */
    @Expose
    @SerializedName("code")
    public int code;


    public int getCode() {
        return code;
    }

    /**
     * message
     */
    @Expose
    @SerializedName("message")
    public String message;


    public String getMessage() {
        return message;
    }

    public abstract T getData();

    @Override
    public String toString() {
        return "RESTResult{" +
                "code=" + code +
                ", data='" + message + '\'' +
                '}';
    }


}
