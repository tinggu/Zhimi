package com.linjin.zhimi.publish;

import android.os.Bundle;
import android.support.annotation.NonNull;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.cyou.app.mvp.BaseMvpActivity;
import com.linjin.zhimi.R;

/**
 * Description:
 * Copyright  : Copyright (c) 2015
 * Company    : 北京畅游天下网络科技有限公司
 * Author     : wangjia_bi
 * Date       : 2015/6/8 15:29
 */
public class PublishActivity extends BaseMvpActivity<PublishView, PublshPresenter>
        implements PublishView  {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_container_fragment);
        if (savedInstanceState == null) {
//            Log.i(TAG, "onCreate: savedInstanceState == null");
            loadRootFragment(R.id.fl_container, PublishStep0Fragment.newInstance(getPresenter()));
        }
    }

    @Override
    public PublshPresenter createPresenter() {
        return new PublshPresenter();
    }


    @Override
    public void onDestroy() {
//        EventBus.getDefault().unregister(this);
        super.onDestroy();
    }

    @Override
    public void showPublish1() {
        start(PublishStep1Fragment.newInstance(getPresenter()));
    }

    @Override
    public void showPublish2() {
        start(PublishStep2Fragment.newInstance(getPresenter()));
    }

//    @Override
//    public void finish() {
//         finish();
//    }

    @Override
    public   void showGiveUpDialog() {
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