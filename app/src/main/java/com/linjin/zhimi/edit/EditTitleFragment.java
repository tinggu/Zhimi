package com.linjin.zhimi.edit;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.cyou.quick.mvp.MvpFragment;
import com.linjin.zhimi.R;
import com.linjin.zhimi.main.self.SelfPresenter;
import com.linjin.zhimi.main.self.SelfView;
import com.linjin.zhimi.widget.TopActionBar;

import butterknife.BindView;


public class EditTitleFragment 
        extends MvpFragment<SelfView, SelfPresenter> 
        implements SelfView{

    @BindView(R.id.topActionBar)
    TopActionBar topActionBar;


//    LatestPresenter

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView();
    }

    private void initView(){
        topActionBar.setTitle(R.string.text_edit);
        topActionBar.hideBack();
        topActionBar.hideAction();
        topActionBar.setActionImageResource(R.mipmap.topic_sidebar);

    }

    @Override
    public SelfPresenter createPresenter() {
        return new SelfPresenter();
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_self;
    }
    
}
