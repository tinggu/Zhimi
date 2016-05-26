package com.linjin.zhimi.splash;

import android.app.Activity;
import android.util.Log;

import com.cyou.quick.mvp.MvpBasePresenter;
import com.cyou.quick.mvp.MvpView;
import com.linjin.zhimi.ZhiMiApplication;

import io.rong.imkit.RongIM;
import io.rong.imlib.RongIMClient;

/**
 * Description:
 * Copyright  : Copyright (c) 2015
 * Company    : 北京畅游天下网络科技有限公司
 * Author     : liujianguang
 * Date       : 2016/1/4
 **/

public class SplashPresenter extends MvpBasePresenter<MvpView> {

    private Activity activity;
    private String token =
            "rKg+fLrt3zX6+wGhweYNnPQU8iL19fiRVxCF1sp3BLp7otFTXIVMxTlLXkqRBObnoL81JbxtNI4hbTdXKjkmekXJ9B+Z+t6L";

    public SplashPresenter(Activity activity) {
        this.activity = activity;
    }

    protected void loginIM() {
        connect(token);
    }

    /**
     * 建立与融云服务器的连接
     *
     * @param token
     */
    private void connect(String token) {

        if (activity.getApplicationInfo().packageName.equals(ZhiMiApplication
                .getCurProcessName(activity.getApplicationContext()))) {

            /**
             * IMKit SDK调用第二步,建立与服务器的连接
             */
            RongIM.connect(token, new RongIMClient.ConnectCallback() {

                /**
                 * Token 错误，在线上环境下主要是因为 Token 已经过期，您需要向 App Server 重新请求一个新的 Token
                 */
                @Override
                public void onTokenIncorrect() {

                    Log.d("LoginActivity", "--onTokenIncorrect");
                }

                /**
                 * 连接融云成功
                 * @param userid 当前 token
                 */
                @Override
                public void onSuccess(String userid) {

                    Log.d("LoginActivity", "--onSuccess" + userid);
//                    startActivity(new Intent(LoginActivity.this, MainActivity.class));
//                    finish();
                }

                /**
                 * 连接融云失败
                 * @param errorCode 错误码，可到官网 查看错误码对应的注释
                 */
                @Override
                public void onError(RongIMClient.ErrorCode errorCode) {

                    Log.d("LoginActivity", "--onError" + errorCode);
                }
            });
        }
    }
}
