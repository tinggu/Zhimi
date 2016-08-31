package com.linjin.zhimi.main.topic;

import com.cyou.common.base.ListPresenter;
import com.linjin.zhimi.model.topic.Topic;
import com.linjin.zhimi.model.topic.TopicList;

import java.util.List;

import rx.Observable;

/**
 * Description:
 * Copyright  : Copyright (c) 2015
 * Company    : 北京畅游天下网络科技有限公司
 * Author     : wangjia
 * Date       : 2016/1/4
 **/

public class TopicListPresenter extends ListPresenter<Topic> {
    
    @Override
    public Observable<List<Topic>> getPageAt(int page) {
        return null;
    }
}
