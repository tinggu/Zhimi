package com.linjin.zhimi.business;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.cyou.app.mvp.BaseMvpActivity;
import com.cyou.common.base.BaseTitleBarListFragment;
import com.linjin.zhimi.R;
import com.linjin.zhimi.business.details.DetailsFragment;
import com.linjin.zhimi.business.summary.SummaryFragment;
import com.linjin.zhimi.main.topic.TopicItemVH;
import com.linjin.zhimi.main.topic.TopicListPresenter;

/**
 * Description:
 * Copyright  : Copyright (c) 2015
 * Company    : 北京畅游天下网络科技有限公司
 * Author     : wangjia_bi
 * Date       : 2015/6/8 15:29
 */
public class BusinessActivity extends BaseMvpActivity<BusinessView, BusinessPresenter> 
implements BusinessView{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_container_fragment);
        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if (extras.containsKey(BusinessFlag.FLAG)) {
                getPresenter().show(extras.getString(BusinessFlag.FLAG));
            } else {
                finish();
            }
        }
    }

    @Override
    public BusinessPresenter createPresenter() {
        return new BusinessPresenter();
    }

    public void showSummary() {
        SummaryFragment summaryFragment = SummaryFragment.newInstance();

        if (getTopFragment() == null) {
            loadRootFragment(R.id.fl_container, summaryFragment);
        } else {
            start(summaryFragment);
        }
    }

    @Override
    public void showDetails() {
        DetailsFragment detailsFragment = new DetailsFragment();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fl_container, detailsFragment);
       
        transaction.commit();
    }

    @Override
    public void showDraft() {
        BaseTitleBarListFragment draftFragment = BaseTitleBarListFragment.newInstance(
                TopicItemVH.class, getString(R.string.text_my_draft), new TopicListPresenter());
        if (getTopFragment() == null) {
            loadRootFragment(R.id.fl_container, draftFragment);
        } else {
            start(draftFragment);
        }

    }

    @Override
    public void showLatest() {
        TopicListPresenter presenter = new TopicListPresenter();
        BaseTitleBarListFragment topFragment = BaseTitleBarListFragment.newInstance(
                TopicItemVH.class, getString(R.string.text_latest_topic), presenter);
        if (getTopFragment() == null) {
            loadRootFragment(R.id.fl_container, topFragment);
        } else {
            start(topFragment);
        }
    }
    
    @Override
    public void showCollent() {
        TopicListPresenter presenter = new TopicListPresenter();
        BaseTitleBarListFragment topFragment = BaseTitleBarListFragment.newInstance(
                TopicItemVH.class, getString(R.string.text_latest_topic), presenter);
        if (getTopFragment() == null) {
            loadRootFragment(R.id.fl_container, topFragment);
        } else {
            start(topFragment);
        }
    }

}