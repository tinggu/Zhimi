package com.linjin.zhimi.publish;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentTransaction;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.cyou.quick.mvp.MvpBasePresenter;
import com.cyou.quick.mvp.MvpPresenter;
import com.linjin.zhimi.R;
import com.linjin.zhimi.base.BaseMvpActivity;
import com.linjin.zhimi.utils.DialogUtils;

import cn.smssdk.SMSSDKInitUtils;

/**
 * Description:
 * Copyright  : Copyright (c) 2015
 * Company    : 北京畅游天下网络科技有限公司
 * Author     : wangjia_bi
 * Date       : 2015/6/8 15:29
 */
public class PublishActivity extends BaseMvpActivity {

    public DialogUtils dialogUtils;

    private PublshPresenter publshPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accunt);
//        EventBus.getDefault().register(this);
        dialogUtils = new DialogUtils();

        showPublish0();
    }

    @Override
    public MvpPresenter createPresenter() {
        return new MvpBasePresenter();
    }


    @Override
    public void onDestroy() {
        dialogUtils.hideLoading();
//        EventBus.getDefault().unregister(this);
        super.onDestroy();
    }

    public void showPublish0() {
        SMSSDKInitUtils.initSDK(this);
        publshPresenter = new PublshPresenter(this);
        PublishStep0Fragment registerFragment = new PublishStep0Fragment(publshPresenter);
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragmentContainer, registerFragment);
        transaction.addToBackStack("step0");
        transaction.commit();
    }

    public void showPublish1() {
        PublishStep0Fragment registerFragment = new PublishStep0Fragment(publshPresenter);
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragmentContainer, registerFragment);
        transaction.addToBackStack("step1");
        transaction.commit();
    }

    public void showPublish2() {
        PublishStep2Fragment registerFragment = new PublishStep2Fragment(publshPresenter);
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragmentContainer, registerFragment);
        transaction.addToBackStack("step2");
        transaction.commit();
    }

    public void showGiveUpDialog() {
        String content = "放弃创建匿题吗？";
        new MaterialDialog.Builder(this)
//                    .title(R.string.title)
                .content(content)
                .positiveText(R.string.text_confirm)
                .negativeText(R.string.text_cancel)
                .onPositive(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        finish();
                    }
                })
                .show();
    }


}