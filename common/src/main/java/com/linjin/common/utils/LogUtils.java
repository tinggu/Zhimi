package com.linjin.common.utils;

import android.util.Log;

/**
 * Created by wangjia_bi on 2015/4/24.
 */
public class LogUtils {

    public static String TAG = "Band";

    public static boolean debugEnabled;

    public static boolean DEBUG_ENABLED;

    public static void init(String tag, boolean debugEnabled) {
        TAG = tag;
        DEBUG_ENABLED = debugEnabled;
    }

    private static String getDebugInfo() {
        Throwable stack = new Throwable().fillInStackTrace();
        StackTraceElement[] trace = stack.getStackTrace();
        int n = 2;
        return trace[n].getClassName() + " " + trace[n].getMethodName() + "()" + ":" + trace[n].getLineNumber() +
                " ";
    }

    private static String getLogInfoByArray(String[] infos) {
        StringBuilder sb = new StringBuilder();
        for (String info : infos) {
            sb.append(info);
            sb.append(" ");
        }
        return sb.toString();
    }

    public static void i(String... s) {
        if (DEBUG_ENABLED) {
            Log.i(TAG, getDebugInfo() + getLogInfoByArray(s));
        }
    }

    public static void e(String... s) {
        if (DEBUG_ENABLED) {
            Log.e(TAG, getDebugInfo() + getLogInfoByArray(s));
        }
    }

    public static void d(String... s) {
        if (DEBUG_ENABLED) {
            Log.d(TAG, getDebugInfo() + getLogInfoByArray(s));
        }
    }

    public static void v(String... s) {
        if (DEBUG_ENABLED) {
            Log.v(TAG, getDebugInfo() + getLogInfoByArray(s));
        }
    }

    public static void w(String... s) {
        if (DEBUG_ENABLED) {
            Log.w(TAG, getDebugInfo() + getLogInfoByArray(s));
        }
    }

    public static void logException(Throwable tr) {
        if (DEBUG_ENABLED) {
            Log.e(TAG, getDebugInfo(), tr);
        }
    }

    public static void d(String msg) {
        d(TAG, msg);
    }

    public static void d(String tag, String msg) {
        if (DEBUG_ENABLED) {
            Log.d(tag, msg);
        }
    }

    public static void i(String msg) {
        i(TAG, msg);
    }

    public static void i(String tag, String msg) {
        if (DEBUG_ENABLED) {
            Log.i(tag, msg);
        }
    }

    public static void e(String msg) {
        e(TAG, msg);
    }

    public static void e(String tag, String msg) {
        if (DEBUG_ENABLED) {
            Log.e(tag, msg);
        }
    }

    public static void v(String msg) {
        v(TAG, msg);
    }

    public static void v(String tag, String msg) {
        if (DEBUG_ENABLED) {
            Log.v(tag, msg);
        }
    }


//    public static void t(String tag) {
//        TAG = tag;
//    }
}
