package com.linjin.zhimi;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import java.util.Vector;

/**
 * Description:
 * Copyright  : Copyright (c) 2015
 * Company    : 北京畅游天下网络科技有限公司
 * Author     : wangjia_bi
 * Date       : 2016/2/16 12:12
 */
public class LifecycleManager implements Application.ActivityLifecycleCallbacks {

    //单例
    private static LifecycleManager instance = new LifecycleManager();

    private static final String TAG = "LifecycleManager";

    private static final int CHECK_DELAY = 500;


    //用于判断是否程序在前台
    private boolean foreground = false, paused = true;
    //handler用于处理切换activity时的短暂时期可能出现的判断错误
    private Handler handler;
    private Runnable check;

    private int appCount = 0;

    private Vector<Activity> activities;

    public static void init(Application app) {
        app.registerActivityLifecycleCallbacks(instance);
    }

    public static LifecycleManager getInstance() {
        return instance;
    }

    private LifecycleManager() {
        activities = new Vector<>();
        handler = new Handler();
        foreground = false;
        paused = true;
    }

    @Override
    public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
        activities.add(activity);
        logActivites();
        appCount++;
    }

    @Override
    public void onActivityStopped(Activity activity) {
        activities.remove(activity);
        logActivites();
        appCount--;
    }

    @Override
    public void onActivityStarted(Activity activity) {

    }

    @Override
    public void onActivityResumed(Activity activity) {
        paused = false;
        foreground = true;
        if (check != null)
            handler.removeCallbacks(check);
    }

    @Override
    public void onActivityPaused(Activity activity) {
        paused = true;
        if (check != null)
            handler.removeCallbacks(check);
        handler.postDelayed(check = new Runnable() {
            @Override
            public void run() {
                if (foreground && paused) {
                    foreground = false;
                    Log.i(TAG, "went background");
                } else {
                    Log.i(TAG, "still foreground");
                }
            }
        }, CHECK_DELAY);

    }

    @Override
    public void onActivitySaveInstanceState(Activity activity, Bundle outState) {

    }

    @Override
    public void onActivityDestroyed(Activity activity) {

    }

    public int getAppCount() {
        return appCount;
    }


    public boolean isForeground() {
        return foreground;
    }

    public void exit() {
        logActivites();
        for (Activity a : activities) {
//            activities.remove(a);
            a.finish();
        }
    }
    
    private void logActivites(){
        Log.i(TAG, "exit: activities: " + activities.size());
        for (Activity a : activities) {
            Log.i(TAG, "activity : " + a.getClass().getSimpleName());
        }
    }
}
