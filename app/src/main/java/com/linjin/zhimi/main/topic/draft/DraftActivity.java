package com.linjin.zhimi.main.topic.draft;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;

import com.cyou.quick.mvp.MvpBasePresenter;
import com.cyou.quick.mvp.MvpPresenter;
import com.linjin.zhimi.R;
import com.linjin.zhimi.base.BaseMvpActivity;
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
public class DraftActivity extends BaseMvpActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accunt); 
 
    }

    @Override
    public MvpPresenter createPresenter() {
        return new MvpBasePresenter();
    }


   
    
    
   
}