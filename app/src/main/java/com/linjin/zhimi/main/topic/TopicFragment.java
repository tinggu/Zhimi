package com.linjin.zhimi.main.topic;


import android.support.v4.widget.SwipeRefreshLayout;

import com.linjin.zhimi.R;
import com.linjin.zhimi.base.loadmore.BaseLoadMoreFragment;
import com.linjin.zhimi.model.topic.TopicAnswer;


public class TopicFragment extends BaseLoadMoreFragment<SwipeRefreshLayout, TopicAnswer, TopicView, TopicPresenter> {


    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_topic;
    }

    @Override
    public TopicAnswer getData() {
        return null;
    }

    @Override
    public void addMoreData(TopicAnswer data) {

    }

    @Override
    public void enableLoadmore() {

    }

    @Override
    public void disableLoadmore() {

    }

    @Override
    public void refresh() {

    }

    @Override
    protected String getErrorMessage(Throwable throwable, boolean b) {
        return null;
    }

    @Override
    public void setData(TopicAnswer topicAnswer) {

    }

    @Override
    public void loadData(boolean b) {

    }

    @Override
    public TopicPresenter createPresenter() {
        return new TopicPresenter();
    }
}
