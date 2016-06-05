package com.linjin.zhimi.topic.details;


import android.support.v4.app.FragmentActivity;

import com.linjin.zhimi.base.BaseLoadMoreRxPresenter;
import com.linjin.zhimi.main.topic.TopicListView;
import com.linjin.zhimi.model.topic.TopicAnswer;

/**
 * Description:
 * Copyright  : Copyright (c) 2015
 * Company    : 北京畅游天下网络科技有限公司
 * Author     : wangjia_bi
 * Date       : 2015/6/11 14:53
 */
public class DetailsPresenter extends BaseLoadMoreRxPresenter<TopicListView, TopicAnswer> {

    FragmentActivity activity;

    public DetailsPresenter(FragmentActivity activity) {
        this.activity = activity;
    }
}
