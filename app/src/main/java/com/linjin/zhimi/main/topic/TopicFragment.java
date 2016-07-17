package com.linjin.zhimi.main.topic;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;

import com.cyou.app.mvp.BaseMvpFragment;
import com.cyou.common.base.BaseListFragment;
import com.cyou.common.base.view.viewholder.CommonHeaderVH;
import com.linjin.zhimi.R;
import com.linjin.zhimi.publish.PublishActivity;
import com.linjin.zhimi.widget.TopActionBar;

import butterknife.BindView;


public class TopicFragment
        extends BaseMvpFragment<TopicView, TopicPresenter> {

    public static TopicFragment newInstance() {
        Bundle args = new Bundle();
        TopicFragment fragment = new TopicFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @BindView(R.id.topActionBar)
    TopActionBar topActionBar;

    @BindView(R.id.drawerlayout)
    DrawerLayout drawerLayout;

    @BindView(R.id.right)
    View right;

    BaseListFragment listFragment;
//    @BindView(R.id.recyclerView)
//    RefreshableRecyclerView recyclerView;
//
//    HeaderListAdapter<Topic> adapter;


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView();
        if (savedInstanceState == null) {
            listFragment = BaseListFragment.newInstance(TopicItemVH.class, "topic");
            listFragment.typeRecyclerView.setHeadView(CommonHeaderVH.class);
            loadRootFragment(R.id.fl_container, listFragment);
        }
    }

    private void initView() {
        topActionBar.setTitle("热门匿题");
        topActionBar.setBackText("提问");
        topActionBar.hideAction();

        showCategoryAction();

        RecyclerView.LayoutManager manager = new LinearLayoutManager(getContext());
        View headView = LayoutInflater.from(getContext())
                .inflate(R.layout.item_banner, null);

//        adapter = new HeaderListAdapter<>();
//        adapter.setHeaderView(headView);
//        recyclerView.setAdapter(adapter);
//        recyclerView.setLayoutManager(manager);
    }

    public void showCategoryAction() {
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
    public TopicPresenter createPresenter() {
        return new TopicPresenter();
    }
}
