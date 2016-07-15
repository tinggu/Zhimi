package com.linjin.zhimi.publish;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;

import com.linjin.zhimi.R;

/**
 * Description:
 * Copyright  : Copyright (c) 2015
 * Company    : 北京畅游天下网络科技有限公司
 * Author     : wangjia_bi
 * Date       : 2015/6/11 14:43
 */ 
public class PublishStep2Fragment extends PublishStepBaseFragment {

    public static PublishStep2Fragment newInstance(PublshPresenter publshPresenter) {
        Bundle args = new Bundle();
        PublishStep2Fragment fragment = new PublishStep2Fragment();
        fragment.setArguments(args);
        fragment.presenter = publshPresenter;
        return fragment;
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_publish_step2;
    }


    @Override
    protected void initView() {
        super.initView();
        topActionBar.setTitle("补充描述（选填）");
        Log.i("code", " initView: " + getClass().getName());
    }

    @Override
    protected void validate() {

    }


}
