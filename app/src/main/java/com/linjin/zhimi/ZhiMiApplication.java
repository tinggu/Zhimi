package com.linjin.zhimi;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.tinggu.common.utils.LogUtils;
import com.tinggu.common.utils.TrackUtils;
import com.umeng.message.PushAgent;


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
    
}
