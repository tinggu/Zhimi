package com.linjin.zhimi.main.topic;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;

import com.cyou.app.mvp.base.BaseLoadMoreFragment;
import com.linjin.zhimi.R;
import com.linjin.zhimi.base.adapter.HeaderListAdapter;
import com.linjin.zhimi.base.view.RefreshableRecyclerView;
import com.linjin.zhimi.model.topic.Topic;
import com.linjin.zhimi.model.topic.TopicAnswer;
import com.linjin.zhimi.publish.PublishActivity;
import com.linjin.zhimi.utils.IntentStarter;
import com.linjin.zhimi.widget.TopActionBar;

import butterknife.BindView;

public class TopicFragment
        extends BaseLoadMoreFragment<SwipeRefreshLayout, TopicAnswer, TopicListView, TopicListPresenter> {

    @BindView(R.id.topActionBar)
    TopActionBar topActionBar;

    @BindView(R.id.drawerlayout)
    DrawerLayout drawerLayout;

    @BindView(R.id.right)
    View right;

    @BindView(R.id.recyclerView)
    RefreshableRecyclerView recyclerView;

    HeaderListAdapter<Topic> adapter;
//    LatestPresenter

    public static TopicFragment newInstance() {
        Bundle args = new Bundle();
        TopicFragment fragment = new TopicFragment();
        fragment.setArguments(args);
        return fragment;
    }
    
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView();
    }

    private void initView() {
        topActionBar.setTitle("热门匿题");
        topActionBar.setBackText("提问");
        topActionBar.hideAction();
        topActionBar.setActionImageResource(R.mipmap.topic_sidebar);
        topActionBar.setBackListener(new TopActionBar.BackListener() {
            @Override
            public void onBack() {
                Intent i = new Intent(getActivity(), PublishActivity.class);
                startActivity(i);
//                IntentStarter.showPublish(getActivity());
            }
        });
        topActionBar.setActionListener(new TopActionBar.ActionListener() {
            @Override
            public void onAction() {
                drawerLayout.openDrawer(right);
            }
        });

        RecyclerView.LayoutManager manager = new LinearLayoutManager(getContext());
        View headView = LayoutInflater.from(getContext())
                .inflate(R.layout.item_banner, null);
        adapter = new HeaderListAdapter<>();
        adapter.setHeaderView(headView);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(manager);
    }

    @Override
    public void onResume() {
        super.onResume();
        if (right != null) {
            drawerLayout.closeDrawer(right);
        }
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
    public TopicListPresenter createPresenter() {
        return new TopicListPresenter();
    }
}
