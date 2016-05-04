package com.linjin.common.utils;

import android.text.TextUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Description:
 * Copyright  : Copyright (c) 2015
 * Company    : 北京畅游天下网络科技有限公司
 * Author     : liujianguang
 * Date       : 2015/12/30
 **/

public class MoblieUtils {

    public static boolean isMobileNumber(String mobiles) {

        if (TextUtils.isEmpty(mobiles)) {
            return false;
        }

        //待优化的正则
        Pattern p = Pattern.compile("^((13[0-9])|(15[^4,\\D])|(18[0-9]))\\d{8}$");
        Matcher m = p.matcher(mobiles);
//        System.out.println(m.matches() + "---");
        return m.matches();
    }
}
