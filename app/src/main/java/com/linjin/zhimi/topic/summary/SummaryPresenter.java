package com.linjin.zhimi.topic.summary;


import android.support.v4.app.FragmentActivity;

import com.linjin.zhimi.base.BaseLoadMoreRxPresenter;
import com.linjin.zhimi.main.topic.TopicView;
import com.linjin.zhimi.model.topic.TopicAnswer;

/**
 * Description:
 * Copyright  : Copyright (c) 2015
 * Company    : 北京畅游天下网络科技有限公司
 * Author     : wangjia_bi
 * Date       : 2015/6/11 14:53
 */
public class SummaryPresenter extends BaseLoadMoreRxPresenter<TopicView, TopicAnswer> {

    FragmentActivity activity;

    public SummaryPresenter(FragmentActivity activity) {
        this.activity = activity;
    }
}
