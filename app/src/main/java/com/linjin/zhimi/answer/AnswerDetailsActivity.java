package com.linjin.zhimi.answer;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;

import com.cyou.app.mvp.BaseMvpActivity;
import com.cyou.quick.mvp.MvpBasePresenter;
import com.cyou.quick.mvp.MvpPresenter;
import com.linjin.zhimi.R; 
import com.linjin.zhimi.publish.PublishStep0Fragment;
import com.linjin.zhimi.publish.PublshPresenter;
import com.linjin.zhimi.utils.DialogUtils;

import org.greenrobot.eventbus.EventBus;

import cn.smssdk.SMSSDKInitUtils;

/**
 * Description:
 * Copyright  : Copyright (c) 2015
 * Company    : 北京畅游天下网络科技有限公司
 * Author     : wangjia_bi
 * Date       : 2015/6/8 15:29
 */
public class AnswerDetailsActivity extends BaseMvpActivity {

    public DialogUtils dialogUtils;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_container_fragment);
        EventBus.getDefault().register(this);
        dialogUtils = new DialogUtils();
 
    }

    @Override
    public MvpPresenter createPresenter() {
        return new MvpBasePresenter();
    }


    @Override
    public void onDestroy() {
        dialogUtils.hideLoading();
        EventBus.getDefault().unregister(this);
        super.onDestroy();
    }
    
//    public void showPublish0() {
//        SMSSDKInitUtils.initSDK(this);
//        PublishStep0Fragment registerFragment = new PublishStep0Fragment();
//        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
//        transaction.replace(R.id.fragmentContainer, registerFragment);
//        transaction.addToBackStack("step0");
//        transaction.commit();
//    }
//
//    public void showPublish1() {
//        PublishStep0Fragment registerFragment = new PublishStep0Fragment(publshPresenter);
//        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
//        transaction.replace(R.id.fragmentContainer, registerFragment);
//        transaction.addToBackStack("step1");
//        transaction.commit();
//    }
//
//    public void showPublish2() {
//        PublishStep0Fragment registerFragment = new PublishStep0Fragment(publshPresenter);
//        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
//        transaction.replace(R.id.fragmentContainer, registerFragment);
//        transaction.addToBackStack("step2");
//        transaction.commit();
//    }

   
}