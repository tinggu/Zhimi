package com.linjin.zhimi.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * Description:
 * Copyright  : Copyright (c) 2015
 * Company    : 北京畅游天下网络科技有限公司
 * Author     : wangjia_bi
 * Date       : 2015/12/31 12:01
 */
public class GsonUtils {

    public static final Gson GSON = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();


}
