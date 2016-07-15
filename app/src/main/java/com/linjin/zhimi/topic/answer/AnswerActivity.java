package com.linjin.zhimi.topic.answer;

import android.os.Bundle;

import com.cyou.app.mvp.BaseMvpActivity;
import com.cyou.quick.mvp.MvpBasePresenter;
import com.cyou.quick.mvp.MvpPresenter;
import com.linjin.zhimi.R;

/**
 * Description:
 * Copyright  : Copyright (c) 2015
 * Company    : 北京畅游天下网络科技有限公司
 * Author     : wangjia_bi
 * Date       : 2015/6/8 15:29
 */
public class AnswerActivity extends BaseMvpActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_container_fragment);
 
    }

    @Override
    public MvpPresenter createPresenter() {
        return new MvpBasePresenter();
    }
    
}