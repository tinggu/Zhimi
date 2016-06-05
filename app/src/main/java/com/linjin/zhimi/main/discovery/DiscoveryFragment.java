package com.linjin.zhimi.main.discovery;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.cyou.quick.mvp.MvpFragment;
import com.cyou.quick.mvp.MvpPresenter;
import com.linjin.zhimi.R;
import com.linjin.zhimi.utils.IntentStarter;
import com.linjin.zhimi.widget.TopActionBar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class DiscoveryFragment extends MvpFragment {

    @BindView(R.id.topActionBar)
    TopActionBar topActionBar;
    @BindView(R.id.item_watch)
    LinearLayout itemWatch;
    @BindView(R.id.item_message)
    LinearLayout itemMessage;
    @BindView(R.id.item_nearby)
    LinearLayout itemNearby;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;


//    LatestPresenter

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView();
    }

    private void initView() {
        topActionBar.setTitle(R.string.radio_dock_2);
        topActionBar.hideBack();
        topActionBar.hideAction();
        topActionBar.setActionImageResource(R.mipmap.topic_sidebar);

    }

    @Override
    public MvpPresenter createPresenter() {
        return new DiscoveryPresenter();
    }


    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_discovery;
    }

    @OnClick({R.id.item_watch, R.id.item_message, R.id.item_nearby})
    public void onItemOnclick(View view) {
        Intent intent;
        switch (view.getId()) {

            case R.id.iv_avatar:
//                IntentStarter.showNoticeInfo(getActivity(), 147, 10);
//                break;
            case R.id.item_message:

                break;
            case R.id.item_nearby:
                IntentStarter.showLogin(null);
                break;
        }
    }
}
