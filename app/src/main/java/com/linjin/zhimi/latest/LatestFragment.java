package com.linjin.zhimi.latest;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;

import com.linjin.zhimi.R;
import com.linjin.zhimi.base.loadmore.BaseLoadMoreFragment;
import com.linjin.zhimi.main.topic.TopicView;
import com.linjin.zhimi.model.topic.TopicAnswer;
import com.linjin.zhimi.utils.IntentStarter;
import com.linjin.zhimi.widget.TopActionBar;

import butterknife.BindView;


public class LatestFragment 
        extends BaseLoadMoreFragment<SwipeRefreshLayout, TopicAnswer, TopicView, LatestPresenter> {
    
    @BindView(R.id.topActionBar)
    TopActionBar topActionBar;

    @BindView(R.id.drawerlayout)
    DrawerLayout drawerLayout;

    @BindView(R.id.right)
    View right;
    
    
    
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView();
    }
    
    private void initView(){
        topActionBar.setTitle("最新匿题"); 
        topActionBar.hideAction();
        topActionBar.setActionImageResource(R.mipmap.topic_sidebar);
        topActionBar.setBackListener(new TopActionBar.BackListener() {
            @Override
            public void onBack() {
                IntentStarter.showPublish(getActivity());
            }
        });
        topActionBar.setActionListener(new TopActionBar.ActionListener() {
            @Override
            public void onAction() {
                drawerLayout.openDrawer(right);
            }
        });
    }
    
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
    public LatestPresenter createPresenter() {
        return new LatestPresenter(getActivity());
    }
}
