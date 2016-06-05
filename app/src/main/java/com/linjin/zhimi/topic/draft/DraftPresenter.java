package com.linjin.zhimi.topic.draft;


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
public class DraftPresenter extends BaseLoadMoreRxPresenter<TopicListView, TopicAnswer> {

    FragmentActivity activity;

    public DraftPresenter(FragmentActivity activity) {
        this.activity = activity;
    }
}
