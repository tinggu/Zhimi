package com.linjin.zhimi.topic;


import com.cyou.quick.mvp.MvpBasePresenter;

/**
 * Description:
 * Copyright  : Copyright (c) 2015
 * Company    : 北京畅游天下网络科技有限公司
 * Author     : wangjia_bi
 * Date       : 2015/6/11 14:53
 */
public class TopicPresenter extends MvpBasePresenter<TopicView> {

    TopicActivity activity;

    public TopicPresenter(TopicActivity activity) {
        this.activity = activity;
    }

    public void showDetails() {
        activity.showDetails(true);
    }
}
