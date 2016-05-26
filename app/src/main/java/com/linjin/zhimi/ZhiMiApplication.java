package com.linjin.zhimi;

import android.app.ActivityManager;
import android.content.Context;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.tinggu.common.utils.LogUtils;
import com.tinggu.common.utils.TrackUtils;
import com.umeng.message.PushAgent;

import io.rong.imkit.RongIM;


public class ZhiMiApplication extends CommonApplication {
    private static final String TAG = "BandApplication";

    /**
     * 内存泄露监测对象
     */

    @Override
    public void onCreate() {
        super.onCreate();
        init();
    }

    private void init() {
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
    
    private void initPush(){
        PushAgent mPushAgent = PushAgent.getInstance(this);
        mPushAgent.enable();
    }
    
    private void initRongIM(){
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

            /**c
             * 融云SDK事件监听处理
             *
             * 注册相关代码，只需要在主进程里做。
             */
//            if (getApplicationInfo().packageName.equals(getCurProcessName(getApplicationContext()))) {
//
//                RongCloudEvent.init(this);
//                DemoContext.init(this);
//
//                new ResourceHandler.Builder().enableBitmapCache().setOutputSizeLimit(120).setType("app").build(this);
//
//                Thread.setDefaultUncaughtExceptionHandler(new RongExceptionHandler(this));
//
//                try {
//                    RongIM.registerMessageType(AgreedFriendRequestMessage.class);
//                    RongIM.registerMessageType(RichContentMessage.class);
//
//                    RongIM.registerMessageTemplate(new ContactNotificationMessageProvider());
//
//                    RongIM.registerMessageTemplate(new RealTimeLocationMessageProvider());
//                    //@ 消息模板展示
//                    RongContext.getInstance().registerConversationTemplate(new NewDiscussionConversationProvider());
//
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
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
