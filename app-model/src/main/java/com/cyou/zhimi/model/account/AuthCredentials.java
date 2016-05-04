package com.cyou.zhimi.model.account;

import com.cyou.common.utils.MD5Utils;

/**
 * Description:
 * Copyright  : Copyright (c) 2015
 * Company    : 北京畅游天下网络科技有限公司
 * Author     : wangjia_bi
 * Date       : 2015/6/8 15:04
 */
public class AuthCredentials {

    private String code;

    private String mobileNum;

    private String password;

    public AuthCredentials(String mobileNum, String password) {
        this.mobileNum = mobileNum;
        this.password = password;
    }

    public AuthCredentials(String mobileNum, String password, String code) {
        this(mobileNum, password);
        this.code = code;
    }

    public String getMobileNum() {
        return mobileNum;
    }

    public String getPassword() {
        return MD5Utils.getStringMD5_32(password);
    }

    public String getCode() {
        return code;
    }

}