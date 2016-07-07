package com.linjin.zhimi.topic;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.cyou.app.mvp.BaseMvpActivity;
import com.linjin.zhimi.R; 
import com.linjin.zhimi.topic.details.DetailsFragment;
import com.linjin.zhimi.topic.draft.DraftFragment;
import com.linjin.zhimi.topic.latest.LatestFragment;
import com.linjin.zhimi.topic.summary.SummaryFragment;

/**
 * Description:
 * Copyright  : Copyright (c) 2015
 * Company    : 北京畅游天下网络科技有限公司
 * Author     : wangjia_bi
 * Date       : 2015/6/8 15:29
 */
public class TopicActivity extends BaseMvpActivity<TopicView, TopicPresenter> {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle extras = getIntent().getExtras();

        if (extras.containsKey(TopicFlag.FLAG)) {
            setContentView(R.layout.activity_topic);
            show(extras.getString(TopicFlag.FLAG));
        } else {
            finish();
        }


        showLatest();
    }

    private void show(String flag) {
        if (TopicFlag.FLAG_SUMMARY.equals(flag)) {
            showSummary();

        } else if (TopicFlag.FLAG_DETAILS.equals(flag)) {

        } else if (TopicFlag.FLAG_DRAFT.equals(flag)) {
        } else {
            finish();
        }
    }

    @Override
    public TopicPresenter createPresenter() {
        return new TopicPresenter(this);
    }


//    @Override
//    public void onDestroy() {
//        dialogUtils.hideLoading();
////        EventBus.getDefault().unregister(this);
//        super.onDestroy();
//    }

    public void showSummary() {
        SummaryFragment summaryFragment = new SummaryFragment(getPresenter());
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        transaction.replace(R.id.fragmentContainer, summaryFragment);
        transaction.commit();
    }

    public void showDetails(boolean isAddBack) {
        DetailsFragment detailsFragment = new DetailsFragment();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragmentContainer, detailsFragment);
        if(isAddBack){
            transaction.addToBackStack("login");
        }
        transaction.commit();
    }

    public void showDraft() {
        DraftFragment draftFragment = new DraftFragment();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragmentContainer, draftFragment);
        transaction.commit();
    }

    public void showLatest() {

        LatestFragment latestFragment = new LatestFragment();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragmentContainer, latestFragment);
        transaction.commit();
    }

    public void clearFragment() {
        FragmentManager fm = getSupportFragmentManager();

    }

}