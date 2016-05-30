package com.linjin.zhimi.setting;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.cyou.quick.mvp.MvpBasePresenter;
import com.linjin.zhimi.R;
import com.linjin.zhimi.base.BaseMvpFragment;
import com.linjin.zhimi.widget.TopActionBar;
import com.tinggu.common.utils.KeyboardUtils;

import butterknife.BindView;


public class SettingFragment extends BaseMvpFragment {

    @BindView(R.id.topActionBar)
    TopActionBar topActionBar;


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView();
    }

    private void initView() {
        topActionBar.setTitle(R.string.text_setting);
        topActionBar.setBackListener(new TopActionBar.BackListener() {
            @Override
            public void onBack() {
                KeyboardUtils.callBackKeyClick();
            }
        });
        topActionBar.hideAction();
    }

    @Override
    public MvpBasePresenter createPresenter() {
        return new MvpBasePresenter();
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_setting;
    }

}
