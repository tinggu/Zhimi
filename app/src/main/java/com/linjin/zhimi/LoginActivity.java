package com.linjin.zhimi;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.cyou.app.mvp.BaseMvpActivity;
import com.cyou.quick.QuickApplication;
import com.cyou.quick.mvp.MvpBasePresenter;
import com.cyou.quick.mvp.MvpPresenter;
import com.linjin.zhimi.account.findpw.SelectFragment;
import com.linjin.zhimi.account.register.RegisterPresenter;
import com.linjin.zhimi.account.register.RegisterStep1Fragment;
import com.linjin.zhimi.account.register.RegisterStep2Fragment;
import com.linjin.zhimi.account.register.RegisterStep3Fragment;
import com.linjin.zhimi.account.register.RegisterStep4Fragment;
import com.linjin.zhimi.event.FindPWSuccessfulEvent;
import com.linjin.zhimi.event.LoginSuccessfulEvent;
import com.linjin.zhimi.event.RegisterSuccessfulEvent;
import com.linjin.zhimi.utils.CacheUtils;
import com.linjin.zhimi.utils.DialogUtils;
import com.linjin.zhimi.utils.IntentStarter;
import com.linjin.zhimi.utils.LogUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/**
 * Description:
 * Copyright  : Copyright (c) 2015
 * Company    : 北京畅游天下网络科技有限公司
 * Author     : wangjia_bi
 * Date       : 2015/6/8 15:29
 */
public class LoginActivity extends BaseMvpActivity {

    public static DialogUtils dialogUtils;

    private RegisterPresenter regPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accunt);
        EventBus.getDefault().register(this);
        dialogUtils = new DialogUtils();
        regPresenter = new RegisterPresenter(this);
        if (savedInstanceState == null) {
            loadRootFragment(R.id.fl_container, SelectFragment.newInstance(regPresenter));
        }
//        start(SelectFragment.newInstance(regPresenter));
    }

    @Override
    public MvpPresenter createPresenter() {
        return new MvpBasePresenter();
    }

//    @Override
//    protected void onNewIntent(Intent intent) {
//        super.onNewIntent(intent);
//        if (DataCenter.getInstance().getUserToken() != null) {
//            IntentStarter.showMain(LoginActivity.this);
//        }
//    }

    @Override
    public void onDestroy() {
        dialogUtils.hideLoading();
        EventBus.getDefault().unregister(this);
        super.onDestroy();
    }

 

    public void showRegister1() {
        start(RegisterStep1Fragment.newInstance(regPresenter));
    }

    public void showRegister2() {
        start(RegisterStep2Fragment.newInstance(regPresenter));
    }

    public void showRegister3() {
        start(RegisterStep3Fragment.newInstance(regPresenter));
    }

    RegisterStep4Fragment registerFragment4;

    public void showRegister4() {
        registerFragment4 = RegisterStep4Fragment.newInstance(regPresenter);
        start(registerFragment4);
    }

//    public void showFindPassword(String phone) {
//        FindPasswordFragment findPWFragment = new FindPasswordFragment(phone);
//        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
//        transaction.addToBackStack(null)
//                .add(R.id.fragmentContainer, findPWFragment)
//                .commit();
//    }

    //登录成功事件
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(LoginSuccessfulEvent event) {
        refreshCache();
        LogUtils.i("login", "LoginSuccessfulEvent");
        Toast.makeText(QuickApplication.getInstance(), "登录成功！", Toast.LENGTH_LONG).show();
        enterMain();
    }

    //注册成功事件
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(RegisterSuccessfulEvent event) {
        refreshCache();
        LogUtils.i("login", "RegisterSuccessfulEvent");
        //强制创建默认身份
        IntentStarter.enter(this);
        Toast.makeText(QuickApplication.getInstance(), "注册成功！", Toast.LENGTH_LONG).show();
    }

    //找回密码成功事件
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(FindPWSuccessfulEvent event) {
        refreshCache();
        LogUtils.i("login", "FindPWSuccessfulEvent");
        Toast.makeText(QuickApplication.getInstance(), "密码已找回，请注意保管！", Toast.LENGTH_LONG).show();
        enterMain();
    }

    private void refreshCache() {
        CacheUtils.init();
        if (DataCenter.getInstance().hasUser()) {

            CacheUtils.refreshCache();
        } else {
            LogUtils.e("user 为空初始化失败");
        }
    }


    private void enterMain() {
        finish();
        IntentStarter.showMain(LoginActivity.this);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (registerFragment4 != null) {
            registerFragment4.onActivityResult(requestCode, resultCode, data);
        }

    }
}