package com.tinggu.common.utils;

import android.text.TextUtils;

/**
 * Description:
 * Copyright  : Copyright (c) 2015
 * Company    : 北京畅游天下网络科技有限公司
 * Author     : liujianguang
 * Date       : 2016/2/25
 **/

public class RetrofitErrorFilter {

    public static Throwable filterError(Throwable e) {
        String errorMsg = e.toString();
        if (!TextUtils.isEmpty(errorMsg) && errorMsg.contains("Network is unreachable")) {
            e = new Throwable("当前没有网络");
        }
        if (!TextUtils.isEmpty(errorMsg) && errorMsg.contains("Failed to connect to")) {
            e = new Throwable("当前没有网络");
        }
        return e;
    }

}
