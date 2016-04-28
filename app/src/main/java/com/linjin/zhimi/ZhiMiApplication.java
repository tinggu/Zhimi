package com.linjin.zhimi;

import com.cyou.band.CommonApplication;
import com.cyou.band.Constants;
import com.cyou.band.DataCenter;
import com.cyou.common.utils.LogUtils;
import com.cyou.common.utils.TrackUtils;
import com.facebook.drawee.backends.pipeline.Fresco;


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
        
        //initLeanCloud fresco
        Fresco.initialize(this);

        //initLeanCloud TrackUtils
        TrackUtils.getInstance().init();
        
    }


    private void initLog() {
        Constants.DEBUG = BuildConfig.DEBUG;
        LogUtils.init("band", Constants.DEBUG);
        LogUtils.i("DEBUG", "DEBUG= " + Constants.DEBUG);
    }
    
}
