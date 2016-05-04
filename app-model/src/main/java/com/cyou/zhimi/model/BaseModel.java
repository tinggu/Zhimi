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
    @SerializedName("code")
    public int code;
   

    public int getCode() {
        return code;
    }

     
    @Override
    public String toString() {
        return "BaseModel{" +
                "code=" + code +
                '}';
    }
}
