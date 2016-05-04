package com.linjin.zhimi.splash;

import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.view.WindowManager;

import com.linjin.zhimi.base.BaseMvpActivity;
import com.cyou.quick.mvp.MvpBasePresenter;
import com.cyou.quick.mvp.MvpPresenter;
import com.linjin.zhimi.R;
import com.linjin.zhimi.utils.IntentStarter;


/**
 * Description:
 * Copyright  : Copyright (c) 2015
 * Company    : 北京畅游天下网络科技有限公司
 * Author     : wangjia_bi
 * Date       : 2016/1/3 16:56
 */
public class SplashActivity extends BaseMvpActivity {
    private static final String TAG = "SplashActivity";
    private static final int DELAY_TIME = 1500;

    private Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //设置全屏
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        super.onCreate(savedInstanceState);

//        SwipeBackHelper.getCurrentPage(this).setSwipeBackEnable(false);
        setContentView(R.layout.activity_splash);

        handler.postDelayed(getRunnable(), DELAY_TIME);
//        if (DataCenter.getInstance().hasUser()) {
//            CacheUtils.init(DataCenter.getInstance().getUserID());
//            CacheUtils.refreshCache();
//        }
        showSplash();
//        com.igexin.sdk.LeanCloudPushManager.getInstance().initialize(this.getApplicationContext());
    }

    private void showSplash() {
        SplashFragment splashFragment = new SplashFragment();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragmentContainer, splashFragment).commit();
    }

    private void showIntro() {
        IntroFragment introFragment = new IntroFragment();
        // 极地的概率抛出异常
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragmentContainer, introFragment).commitAllowingStateLoss();
    }

    Runnable runnable;

    private Runnable getRunnable() {
        if (runnable == null) {
            runnable = new Runnable() {
                @Override
                public void run() {
                    IntentStarter.enter(SplashActivity.this);

                }
            };
        }
        return runnable;
    }

//    @Override
//    protected void onNewIntent(Intent intent) {
//        super.onNewIntent(intent);
//        if (DataCenter.getInstance().getUserToken() != null) {
//            IntentStarter.showMain(SplashActivity.this);
//        }
//    }

    @Override
    public MvpPresenter createPresenter() {
        return new MvpBasePresenter();
    }

}
