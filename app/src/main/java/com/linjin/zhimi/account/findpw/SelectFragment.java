package com.linjin.zhimi.account.findpw;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.cyou.quick.mvp.MvpBasePresenter;
import com.cyou.quick.mvp.MvpPresenter;
import com.linjin.zhimi.R;
import com.linjin.zhimi.account.login.LoginActivity;
import com.linjin.zhimi.base.BaseMvpFragment;
import com.linjin.zhimi.utils.IntentStarter;

import butterknife.OnClick;


@SuppressLint("ValidFragment")
public class SelectFragment extends BaseMvpFragment {

    private LoginActivity loginActivity;

    public SelectFragment(LoginActivity loginActivity) {
        this.loginActivity = loginActivity;
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
                loginActivity.showRegister0();
                break;
            case R.id.btn_login:
//                loginActivity.showLogin();
                IntentStarter.showMain(getActivity());
                break;
        }
    }

}
