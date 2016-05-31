package com.linjin.zhimi.edit;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.cyou.quick.mvp.MvpFragment;
import com.linjin.zhimi.R;
import com.linjin.zhimi.base.BaseMvpFragment;
import com.linjin.zhimi.base.lce.BaseMvpLceView;
import com.linjin.zhimi.main.self.SelfPresenter;
import com.linjin.zhimi.main.self.SelfView;
import com.linjin.zhimi.widget.TopActionBar;
import com.tinggu.common.utils.KeyboardUtils;

import butterknife.BindView;


public class EditNameFragment  extends BaseMvpFragment {

    @BindView(R.id.topActionBar)
    TopActionBar topActionBar;


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView();
    }

    private void initView(){
        topActionBar.setTitle(R.string.text_edit);
        topActionBar.setBackListener(new TopActionBar.BackListener() {
            @Override
            public void onBack() {
                KeyboardUtils.callBackKeyClick();
            }
        });
        topActionBar.hideAction();
    }

    @Override
    public SelfPresenter createPresenter() {
        return new SelfPresenter();
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_edit_name;
    }
    
}