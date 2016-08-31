package com.linjin.zhimi;

import android.app.ActivityManager;
import android.content.Context;

import com.cyou.quick.QuickApplication;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.cyou.common.utils.LogUtils;
import com.cyou.common.utils.TrackUtils;
import com.facebook.stetho.Stetho;
import com.squareup.leakcanary.LeakCanary;
import com.umeng.message.PushAgent;

import io.rong.imkit.RongIM;


public class ZhiMiApplication extends QuickApplication {
    private static final String TAG = "BandApplication";
    
    @Override
    public void onCreate() {
        super.onCreate();
        mRefWatcher = LeakCanary.install(this);
        init();
    }

    private void init() {
        Stetho.initializeWithDefaults(this);
        
        initLog();

        DataCenter.getInstance().init();

        initRongIM();

        Fresco.initialize(this);

        TrackUtils.getInstance().init();

        initPush();
    }


    private void initLog() {
        Constants.DEBUG = BuildConfig.DEBUG;
        LogUtils.init("band", Constants.DEBUG);
        LogUtils.i("DEBUG", "DEBUG= " + Constants.DEBUG);
    }

    private void initPush() {
        PushAgent mPushAgent = PushAgent.getInstance(this);
        mPushAgent.enable();
    }

    private void initRongIM() {
        /**
         * OnCreate 会被多个进程重入，这段保护代码，确保只有您需要使用 RongIM 的进程和 Push 进程执行了 init。
         * io.rong.push 为融云 push 进程名称，不可修改。
         */
        if (getApplicationInfo().packageName.equals(getCurProcessName(getApplicationContext())) ||
                "io.rong.push".equals(getCurProcessName(getApplicationContext()))) {

            /**
             * IMKit SDK调用第一步 初始化
             */
            RongIM.init(this);

        }
    }

    /**
     * 获得当前进程的名字
     *
     * @param context
     * @return 进程号
     */
    public static String getCurProcessName(Context context) {

        int pid = android.os.Process.myPid();

        ActivityManager activityManager = (ActivityManager) context
                .getSystemService(Context.ACTIVITY_SERVICE);

        for (ActivityManager.RunningAppProcessInfo appProcess : activityManager
                .getRunningAppProcesses()) {

            if (appProcess.pid == pid) {
                return appProcess.processName;
            }
        }
        return null;
    }

}
