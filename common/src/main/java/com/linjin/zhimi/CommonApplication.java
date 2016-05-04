package com.linjin.zhimi;

import com.cyou.quick.QuickApplication;
import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;

/**
 * Description:
 * Copyright  : Copyright (c) 2015
 * Company    : 北京畅游天下网络科技有限公司
 * Author     : liujianguang
 * Date       : 2016/1/4
 **/

public class CommonApplication extends QuickApplication {

    /**
     * 内存泄露监测对象
     */
    RefWatcher mRefWatcher;

    @Override
    public void onCreate() {
        super.onCreate();
        //init leakCanary
        mRefWatcher = LeakCanary.install(this);
    }

    @Override
    public RefWatcher getRefWatcher() {
        return mRefWatcher;
    }
}
