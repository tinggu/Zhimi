package com.cyou.quick;

import android.app.Application;

import com.squareup.leakcanary.RefWatcher;

public abstract class QuickApplication extends Application {

    private static QuickApplication sInstance;

    public static QuickApplication getInstance() {
        return sInstance;
    }

    @Override
    public void onCreate() {
        sInstance = this;
        super.onCreate();
    }

    public abstract RefWatcher getRefWatcher();

}
