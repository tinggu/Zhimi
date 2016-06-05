package com.linjin.zhimi.topic.summary;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.cyou.quick.mvp.MvpBasePresenter;
import com.facebook.drawee.view.SimpleDraweeView;
import com.linjin.zhimi.R;
import com.linjin.zhimi.base.BaseMvpFragment;
import com.linjin.zhimi.topic.TopicPresenter;
import com.linjin.zhimi.utils.IntentStarter;
import com.linjin.zhimi.utils.KeyboardUtils;
import com.linjin.zhimi.widget.TopActionBar;

import butterknife.BindView;
import butterknife.OnClick;


public class SummaryFragment extends BaseMvpFragment {

    @BindView(R.id.topActionBar)
    TopActionBar topActionBar;
    @BindView(R.id.iv_avatar)
    SimpleDraweeView ivAvatar;
    @BindView(R.id.btn_topic_details)
    Button btnTopicDetails;
    @BindView(R.id.item_account)
    LinearLayout itemAccount;
    @BindView(R.id.wv_summary)
    WebView wvSummary;
    @BindView(R.id.dock_thumb)
    TextView dockThumb;
    @BindView(R.id.dock_collect)
    TextView dockCollect;
    @BindView(R.id.dock_share)
    TextView dockShare;
    @BindView(R.id.dock_comment)
    TextView dockComment;

    private String topicId;

    TopicPresenter topicPresenter;
    
    public SummaryFragment(TopicPresenter topicPresenter){
     this.topicPresenter = topicPresenter;   
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView();
    }

    private void initView() {
        topActionBar.setTitle("匿题摘要");
        topActionBar.setBackListener(new TopActionBar.BackListener() {
            @Override
            public void onBack() {
                KeyboardUtils.callBackKeyClick();
            }
        });
        topActionBar.hideAction();

    }

    @Override
    public MvpBasePresenter createPresenter() {
        return new MvpBasePresenter();
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_summay;
    }

    
    @OnClick({R.id.iv_avatar, R.id.btn_topic_details, R.id.dock_thumb,
            R.id.dock_collect, R.id.dock_share, R.id.dock_comment})
    public void onItemOnclick(View view) {
        Intent intent;
        switch (view.getId()) {

            case R.id.iv_avatar:
//                IntentStarter.showNoticeInfo(getActivity(), 147, 10);
//                break;
            case R.id.btn_topic_details:
                topicPresenter.showDetails();
                break;
            case R.id.dock_thumb:
                IntentStarter.showLogin(null);
                break;
            case R.id.dock_collect:
                IntentStarter.showLogin(null);
                break;
            case R.id.dock_share:
                IntentStarter.showLogin(null);
                break;
            case R.id.dock_comment:
                IntentStarter.showLogin(null);
                break;


        }
    }


}
