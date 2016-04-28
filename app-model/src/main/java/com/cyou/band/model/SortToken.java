package com.cyou.band.model;

import java.io.Serializable;

/**
 * Description:
 * Copyright  : Copyright (c) 2015
 * Company    : 北京畅游天下网络科技有限公司
 * Author     : liujianguang
 * Date       : 2016/3/7
 **/

public class SortToken implements Serializable {
    public String simpleSpell = "";//简拼
    public String wholeSpell = "";//全拼
    public String chName = "";//中文全名

    @Override
    public String toString() {
        return "SortToken{" +
                "simpleSpell='" + simpleSpell + '\'' +
                ", wholeSpell='" + wholeSpell + '\'' +
                ", chName='" + chName + '\'' +
                '}';
    }
}
