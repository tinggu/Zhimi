package com.cyou.common.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.support.v4.content.SharedPreferencesCompat;

import com.cyou.quick.QuickApplication;

/**
 * Description:  Create by wangjia_bi on 2016/8/16 12:44
 * Copyright  : Copyright (c) 2015
 * Company    : 北京畅游天下网络科技有限公司
 * Author     : wangjia_bi
 * Date       : 2016/8/16 12:44
 */
public class SimpleKVStore {

    private static final String PRE_NAME = "channel_pre";
    public static final String CURRENT_ACCOUNT = "degrade_currentAccount";

    public SimpleKVStore() {
    }

    public static final SharedPreferences getPreferences() {
        return SimpleKVStore.SingletonHolder.sprefs;
    }

    public static void setIntPrefs(String key, int value) {
        SharedPreferences sprefs = getPreferences();
        Editor editor = sprefs.edit();
        editor.putInt(key, value);
        SharedPreferencesCompat.EditorCompat.getInstance().apply(editor);
    }

    public static int getIntPrefs(String key) {
        return getIntPrefs(key, 0);
    }

    public static int getIntPrefs(String key, int defaultValue) {
        SharedPreferences sprefs = getPreferences();
        return sprefs.getInt(key, defaultValue);
    }

    public static void setLongPrefs(String key, long value) {
        SharedPreferences sprefs = getPreferences();
        Editor editor = sprefs.edit();
        editor.putLong(key, value);
        SharedPreferencesCompat.EditorCompat.getInstance().apply(editor);
    }

    public static long getLongPrefs(String key) {
        return getLongPrefs(key, 0L);
    }

    public static long getLongPrefs(String key, long defaultValue) {
        SharedPreferences sprefs = getPreferences();
        return sprefs.getLong(key, defaultValue);
    }

    public static void setBooleanPrefs(String key, boolean value) {
        SharedPreferences sprefs = getPreferences();
        Editor editor = sprefs.edit();
        editor.putBoolean(key, value);
        SharedPreferencesCompat.EditorCompat.getInstance().apply(editor);
    }

    public static boolean getBooleanPrefs(String key) {
        return getBooleanPrefs(key, false);
    }

    public static boolean getBooleanPrefs(String key, boolean defaultValue) {
        SharedPreferences sprefs = getPreferences();
        return sprefs.getBoolean(key, defaultValue);
    }

    public static void setStringPrefs(String key, String value) {
        SharedPreferences sprefs = getPreferences();
        Editor editor = sprefs.edit();
        editor.putString(key, value);
        SharedPreferencesCompat.EditorCompat.getInstance().apply(editor);
    }

    public static String getStringPrefs(String key) {
        return getStringPrefs(key, "");
    }

    public static String getStringPrefs(String key, String defaultValue) {
        SharedPreferences sprefs = getPreferences();
        return sprefs.getString(key, defaultValue);
    }

    public static int getIntPrefs(Context context, String key, int defaultValue) {
        SharedPreferences sprefs = context.getSharedPreferences(PRE_NAME, 0);
        return sprefs.getInt(key, defaultValue);
    }

    private static class SingletonHolder {
        private static final SharedPreferences sprefs;

        private SingletonHolder() {
        }

        static {
            sprefs = QuickApplication.getInstance().getSharedPreferences(PRE_NAME, 0);
        }
    }
}
