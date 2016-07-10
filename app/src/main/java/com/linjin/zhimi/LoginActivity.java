package com.linjin.zhimi;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.cyou.app.mvp.BaseMvpActivity;
import com.cyou.quick.QuickApplication;
import com.linjin.zhimi.account.SelectFragment;
import com.linjin.zhimi.account.login.LoginPresenter;
import com.linjin.zhimi.account.login.LoginView;
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
public class LoginActivity extends BaseMvpActivity<LoginView, LoginPresenter> {

    private static final String TAG = "login";

    public static DialogUtils dialogUtils;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        Log.i(TAG, "onCreate: login");
        setContentView(R.layout.activity_container_fragment);
        EventBus.getDefault().register(this);
        dialogUtils = new DialogUtils();
        if (savedInstanceState == null) {
//            Log.i(TAG, "onCreate: savedInstanceState == null");
            loadRootFragment(R.id.fl_container, SelectFragment.newInstance());
        }
    }

    @Override
    public LoginPresenter createPresenter() {
        return new LoginPresenter();
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
    
    //登录成功事件
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(LoginSuccessfulEvent event) {
        refreshCache();
        Log.i(TAG, "LoginSuccessfulEvent");
        Toast.makeText(QuickApplication.getInstance(), "登录成功！", Toast.LENGTH_LONG).show();
        enterMain();
    }

    //注册成功事件
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(RegisterSuccessfulEvent event) {
        refreshCache();
        Log.i(TAG, "RegisterSuccessfulEvent");
        //强制创建默认身份
        IntentStarter.enter(this);
        Toast.makeText(QuickApplication.getInstance(), "注册成功！", Toast.LENGTH_LONG).show();
    }

    //找回密码成功事件
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(FindPWSuccessfulEvent event) {
        refreshCache();
        Log.i(TAG, "FindPWSuccessfulEvent");
        Toast.makeText(QuickApplication.getInstance(), "密码已找回，请注意保管！", Toast.LENGTH_LONG).show();
        enterMain();
    }

    private void refreshCache() {
        CacheUtils.init();
        if (DataCenter.getInstance().hasUser()) {

            CacheUtils.refreshCache();
        } else {
            Log.e(TAG, "user 为空初始化失败");
        }
    }

    private void enterMain() {
        finish();
        IntentStarter.showMain(LoginActivity.this);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        getTopFragment().onActivityResult(requestCode, resultCode, data);
//        if (registerFragment4 != null) {
//            registerFragment4.onActivityResult(requestCode, resultCode, data);
//        }
    }
}