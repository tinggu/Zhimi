package com.linjin.zhimi.friend.search;

import android.os.Bundle;

import com.cyou.app.mvp.BaseMvpActivity;
import com.cyou.quick.mvp.MvpBasePresenter;
import com.cyou.quick.mvp.MvpPresenter;
import com.linjin.zhimi.R; 
import com.linjin.zhimi.widget.TopActionBar;

import butterknife.BindView;

/**
 * Description:
 * Copyright  : Copyright (c) 2015
 * Company    : 北京畅游天下网络科技有限公司
 * Author     : wangjia_bi
 * Date       : 2015/6/8 15:29
 */
public class SearchFriendActivity extends BaseMvpActivity {

    @BindView(R.id.topActionBar)
    TopActionBar topActionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_friend);
        initView();
    }

    private void initView() {
        topActionBar.setTitle(R.string.text_find);
        topActionBar.hideBack();
        topActionBar.hideAction();
    }

    @Override
    public MvpPresenter createPresenter() {
        return new MvpBasePresenter();
    }

}