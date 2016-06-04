package com.linjin.zhimi.topic;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.cyou.quick.mvp.MvpBasePresenter;
import com.cyou.quick.mvp.MvpPresenter;
import com.linjin.zhimi.R;
import com.linjin.zhimi.base.BaseMvpActivity;
import com.linjin.zhimi.topic.latest.LatestFragment;
import com.linjin.zhimi.topic.summary.SummaryFragment;

/**
 * Description:
 * Copyright  : Copyright (c) 2015
 * Company    : 北京畅游天下网络科技有限公司
 * Author     : wangjia_bi
 * Date       : 2015/6/8 15:29
 */
public class TopicActivity extends BaseMvpActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_topic);

        showLatest();
    }

    @Override
    public MvpPresenter createPresenter() {
        return new MvpBasePresenter();
    }


//    @Override
//    public void onDestroy() {
//        dialogUtils.hideLoading();
////        EventBus.getDefault().unregister(this);
//        super.onDestroy();
//    }

    public void showSummary() {
        SummaryFragment summaryFragment = new SummaryFragment();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        transaction.replace(R.id.fragmentContainer, summaryFragment);
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