package com.linjin.zhimi.rest;

/**
 * Description:
 * Copyright  : Copyright (c) 2015
 * Company    : 北京畅游天下网络科技有限公司
 * Author     : liujianguang
 * Date       : 2015/12/21
 **/

public interface ApiCode {

    //协议code码
    /**
     * 成功
     */
    int SUCCESS_CODE = 1;
    /**
     * 成功,但是没更多的数据了
     */
    int NO_DATA_CODE = 2;
    /**
     * token失效
     */
    int FAILED_CODE_TOKEN_INVALID = 1001;
    /**
     * 服务器错误
     */
    int FAILED_CODE_SERVER_ERROR = -1;
    /**
     * 用户不存在
     */
    int FAILED_CODE_USER_ABSENT = 1002;
    /**
     * 密码错误
     */
    int FAILED_CODE_PASSWORD_ERROR = 1003;

}
