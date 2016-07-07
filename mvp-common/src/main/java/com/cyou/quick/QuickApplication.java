package com.cyou.quick;

import android.app.Application;
import android.os.StrictMode;

import com.squareup.leakcanary.RefWatcher;

import static android.os.Build.VERSION.SDK_INT;
import static android.os.Build.VERSION_CODES.GINGERBREAD;

public abstract class QuickApplication extends Application {

    /**
     * 内存泄露监测对象
     */
    protected RefWatcher mRefWatcher;
    
    private static QuickApplication sInstance;

    public static QuickApplication getInstance() {
        return sInstance;
    }

    @Override
    public void onCreate() {
        sInstance = this;
        super.onCreate();
//        enabledStrictMode(); 
    }

    private void enabledStrictMode() {
        if (SDK_INT >= GINGERBREAD) {
            StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder() //
                    .detectAll() //
                    .penaltyLog() //
                    .penaltyDeath() //
                    .build());
        }
    }

    public RefWatcher getRefWatcher() {
        return mRefWatcher;
    }
}
