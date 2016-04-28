package com.cyou.common.utils;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.text.TextUtils;
import android.util.Log;

import com.cyou.band.Constants;
import com.cyou.quick.QuickApplication;
import com.umeng.analytics.AnalyticsConfig;
import com.umeng.analytics.MobclickAgent;

import java.util.HashMap;

/**
 * Description:
 * Copyright  : Copyright (c) 2015
 * Company    : 北京畅游天下网络科技有限公司
 * Author     : wangjia_bi
 * Date       : 2015/8/13 15:55
 */
public class TrackUtils {

    private static TrackUtils trackUtils;
    private Context context;

    private TrackUtils(Context context) {
        this.context = context;
    }

    public static TrackUtils getInstance() {
        if (trackUtils == null) {
            trackUtils = new TrackUtils(QuickApplication.getInstance());
        }
        return trackUtils;
    }

    public void init() {
        //友盟统计分析 打开调试模式
        MobclickAgent.setDebugMode(Constants.DEBUG);
        //友盟 默认渠道包
        if (Constants.DEBUG) {
            AnalyticsConfig.setChannel("test");
        }

        /** 设置是否对日志信息进行加密, 默认false(不加密) */
//        AnalyticsConfig.enableEncrypt(true);
        /** 禁止默认的页面统计方式，这样将不会再自动统计Activity */
        MobclickAgent.openActivityDurationTrack(false);

//        //talkingdata 初始化
//        TCAgent.init(context, Constants.TD_APP_ID, getAppMetaData(context, "UMENG_CHANNEL"));

    }

    public void onProfileSignIn(String userID) {
        MobclickAgent.onProfileSignIn(userID);
    }

    public void onProfileSignIn(String Provider, String userID) {
        MobclickAgent.onProfileSignIn(Provider, userID);
    }

    //
    public void onResume(Context context) {
        MobclickAgent.onResume(context);
//        if (context instanceof Activity) {
//            TCAgent.onResume((Activity) context);
//        }
    }

    public void onPause(Context context) {
        MobclickAgent.onPause(context);
//        if (context instanceof Activity) {
//            TCAgent.onPause((Activity) context);
//        }
    }

    public void exit(Context context) {
        //如果开发者调用Process.kill或者System.exit之类的方法杀死进程，请务必在此之前调用
        MobclickAgent.onKillProcess(context);
    }

    public void onPageStart(String name) {
        MobclickAgent.onPageStart(name);
//        TCAgent.onPageStart(context, name);
    }

    public void onPageEnd(String name) {
        MobclickAgent.onPageEnd(name);
//        TCAgent.onPageEnd(context, name);
    }

    public void onEvent(String eventId) {
        MobclickAgent.onEvent(context, eventId);
//        TCAgent.onEvent(context, eventId);
    }

    public void onEvent(String eventId, HashMap<String, String> map) {
        MobclickAgent.onEvent(context, eventId, map);
    }

    public void onEventValue(String eventId, int du) {
        MobclickAgent.onEventValue(context, eventId, null, du);
    }

    public void onEventValue(String eventId, HashMap<String, String> map, int du) {
        MobclickAgent.onEventValue(context, eventId, map, du);
    }

    /**
     * 获取application中指定的meta-data
     *
     * @return 如果没有获取成功(没有对应值，或者异常)，则返回值为空
     */
    public static String getAppMetaData(Context ctx, String key) {
        if (ctx == null || TextUtils.isEmpty(key)) {
            return null;
        }
        String resultData = null;
        try {
            PackageManager packageManager = ctx.getPackageManager();
            if (packageManager != null) {
                ApplicationInfo applicationInfo =
                        packageManager.getApplicationInfo(ctx.getPackageName(),
                                PackageManager.GET_META_DATA);
                if (applicationInfo != null) {
                    if (applicationInfo.metaData != null) {
                        resultData = applicationInfo.metaData.getString(key);
                    }
                }

            }
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

        Log.i("UMENG_CHANNEL", " UMENG_CHANNEL_VALUE = " + resultData);
        return resultData;
    }
}
