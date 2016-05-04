package com.linjin.zhimi.account;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.cyou.quick.mvp.MvpBasePresenter;
import com.cyou.quick.mvp.MvpPresenter;
import com.linjin.zhimi.R;
import com.linjin.zhimi.base.BaseMvpFragment;

import butterknife.OnClick;


@SuppressLint("ValidFragment")
public class SelectFragment extends BaseMvpFragment {

    private AccuntActivity accuntActivity;

    public SelectFragment(AccuntActivity accuntActivity) {
        this.accuntActivity = accuntActivity;
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_select;
    }

    @Override
    public MvpPresenter createPresenter() {
        return new MvpBasePresenter();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView();
    }

    private void initView() {

    }

    @OnClick({R.id.btn_register, R.id.btn_login})
    void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_register:
                accuntActivity.showRegister0();
                break;
            case R.id.btn_login:
                accuntActivity.showLogin();
                break;
        }
    }

}
