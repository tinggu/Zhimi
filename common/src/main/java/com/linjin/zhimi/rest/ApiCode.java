package com.linjin.zhimi.rest;


import com.cyou.zhimi.model.BaseModel;

public class ApiCode {

    //协议code码
    /**
     * 成功
     */
    public static final int SUCCESS_CODE = 200;
    /**
     * 成功,但是没更多的数据了
     */
    public static final int NO_DATA_CODE = 2;
    /**
     * token失效
     */
    public static final   int FAILED_CODE_TOKEN_INVALID = 1001;
    /**
     * 服务器错误
     */
    public static final   int FAILED_CODE_SERVER_ERROR = -1;
    /**
     * 用户不存在
     */
    public static final   int FAILED_CODE_USER_ABSENT = 1002;
    /**
     * 密码错误
     */
    public static final  int FAILED_CODE_PASSWORD_ERROR = 1003;


    public static String getMsg(int code) {
        return "错误码:" + code;
    }
}
