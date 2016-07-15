package com.linjin.zhimi.topic;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.cyou.app.mvp.BaseMvpActivity;
import com.cyou.common.base.BaseTitleBarListFragment;
import com.linjin.zhimi.R;
import com.linjin.zhimi.main.topic.TopicItemVH;
import com.linjin.zhimi.topic.details.DetailsFragment;
import com.linjin.zhimi.topic.draft.DraftFragment;
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
        setContentView(R.layout.activity_container_fragment);
        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();

            if (extras.containsKey(TopicFlag.FLAG)) {

                show(extras.getString(TopicFlag.FLAG));
            } else {
                finish();
            }
        }
    }

    private void show(String flag) {
        if (TopicFlag.FLAG_SUMMARY.equals(flag)) {
            showSummary();

        } else if (TopicFlag.FLAG_DETAILS.equals(flag)) {

        } else if (TopicFlag.FLAG_DRAFT.equals(flag)) {
            showLatest();
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
        SummaryFragment summaryFragment = SummaryFragment.newInstance();

        if (getTopFragment() == null) {
            loadRootFragment(R.id.fl_container, summaryFragment);
        } else {
            start(summaryFragment);
        }


    }

    public void showDetails(boolean isAddBack) {
        DetailsFragment detailsFragment = new DetailsFragment();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragmentContainer, detailsFragment);
        if (isAddBack) {
            transaction.addToBackStack("login");
        }
        transaction.commit();
    }

    public void showDraft() {
        start(BaseTitleBarListFragment.newInstance(TopicItemVH.class, getString(R.string.text_my_draft)));
//        DraftFragment draftFragment = new DraftFragment();
//        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
//        transaction.replace(R.id.fragmentContainer, draftFragment);
//        transaction.commit();
    }

    public void showLatest() {
        start(BaseTitleBarListFragment.newInstance(TopicItemVH.class, getString(R.string.text_latest_topic)));
//        LatestFragment latestFragment = new LatestFragment();
//        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
//        transaction.replace(R.id.fragmentContainer, latestFragment);
//        transaction.commit();
    }

    public void clearFragment() {
        FragmentManager fm = getSupportFragmentManager();

    }

}