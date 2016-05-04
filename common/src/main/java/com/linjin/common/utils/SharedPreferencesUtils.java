package com.linjin.common.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by liujianguang on 2015/12/18.
 */
public class SharedPreferencesUtils {

    /**
     * 保存在手机里面的文件名
     */
    private static final String FILE_NAME = "share_date";

    private Context context;

    private SharedPreferencesUtils(Context context) {
        this.context = context;
    }

    private static SharedPreferencesUtils instance;

    public static SharedPreferencesUtils getInstance(Context context) {
        if (instance == null) {
            instance = new SharedPreferencesUtils(context);
        }
        return instance;
    }

    public enum Type {
        STRRING, INTEGER, BOOLEAN, FLOAT, LONG
    }

    /**
     * 保存数据的方法，我们需要拿到保存数据的具体类型，然后根据类型调用不同的保存方法
     *
     * @param type
     * @param key
     * @param object
     */
    public void setParam(Type type, String key, Object object) {

        SharedPreferences sp = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        switch (type) {
            case STRRING:
                editor.putString(key, (String) object);
                break;
            case INTEGER:
                editor.putInt(key, (Integer) object);
                break;
            case BOOLEAN:
                editor.putBoolean(key, (Boolean) object);
                break;
            case FLOAT:
                editor.putFloat(key, (Float) object);
                break;
            case LONG:
                editor.putLong(key, (Long) object);
                break;
        }
        editor.apply();
    }


    /**
     * 得到保存数据的方法，我们根据默认值得到保存的数据的具体类型，然后调用相对于的方法获取值
     *
     * @param type
     * @param key
     * @param defaultObject
     * @return
     */
    public Object getParam(Type type, String key, Object defaultObject) {

        SharedPreferences sp = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);

        switch (type) {
            case STRRING:
                return sp.getString(key, (String) defaultObject);
            case INTEGER:
                return sp.getInt(key, (Integer) defaultObject);
            case BOOLEAN:
                return sp.getBoolean(key, (Boolean) defaultObject);
            case FLOAT:
                return sp.getFloat(key, (Float) defaultObject);
            case LONG:
                return sp.getLong(key, (Long) defaultObject);
        }
        return null;
    }

}
