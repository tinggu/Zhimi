package com.linjin.zhimi.nearby;


import android.support.v4.app.FragmentActivity;

import com.cyou.quick.mvp.MvpBasePresenter;
import com.linjin.zhimi.main.topic.TopicView;

/**
 * Description:
 * Copyright  : Copyright (c) 2015
 * Company    : 北京畅游天下网络科技有限公司
 * Author     : wangjia_bi
 * Date       : 2015/6/11 14:53
 */
public class NearbyPresenter extends MvpBasePresenter<TopicView> {

    FragmentActivity activity;

    public NearbyPresenter(FragmentActivity activity) {
        this.activity = activity;
    }
}
