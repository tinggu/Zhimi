package com.linjin.zhimi.utils;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;

import com.linjin.zhimi.DataCenter;
import com.cyou.quick.QuickApplication;
import com.linjin.zhimi.R;
import com.linjin.zhimi.account.AccuntActivity;
import com.linjin.zhimi.main.MainActivity;
import com.linjin.zhimi.main.WebViewActivity;

/**
 * Description:
 * Copyright  : Copyright (c) 2015
 * Company    : 北京畅游天下网络科技有限公司
 * Author     : wangjia
 * Date       : 2016/1/5
 **/

public class IntentStarter {

    public static final String EXTRA_USER = "user";
    public static final String EXTRA_GROUP = "group";
    public static final String EXTRA_GROUP_ID = "groupid";
    public static final String EXTRA_GROUP_NAME = "title";
    public static final String EXTRA_GROUP_URL = "groupUrl";
    public static final String EXTRA_GROUP_DES = "groupDes";
    public static final String EXTRA_TITLE = "title";
    public static final String EXTRA_URL = "url"; 
    public static final String EXTRA_COVER_URI = "coverUri";
    public static final String EXTRA_AVATAR_URI = "avatarUri";
    public static final String EXTRA_VOTE_ID = "voteid";
    public static final String EXTRA_NOTICE_ID = "noticeid";

    public static void enter(Activity activity) {
        DataCenter dataCenter = DataCenter.getInstance();
        Intent intent;
        if (dataCenter.hasUser()) {
            intent = new Intent(activity, MainActivity.class);
        } else {
            intent = new Intent(activity, AccuntActivity.class);
        }
        activity.startActivity(intent);
    }

    public static void showMain(Activity activity) {
        Intent i = new Intent(activity, MainActivity.class);
        activity.startActivity(i);
        activity.overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
    }
    
    public static void showLogin(Activity activity) {
        if (activity != null) {
            Intent i = new Intent(activity, AccuntActivity.class);
            activity.startActivity(i);
            activity.overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
            activity.finish();
        } else {
            Intent i = new Intent(QuickApplication.getInstance(), AccuntActivity.class);
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            QuickApplication.getInstance().startActivity(i);
        }
    }

    public static void showProtocal(Activity activity, String url, String title) {
        Intent i = new Intent(activity, WebViewActivity.class);
        i.putExtra(EXTRA_TITLE, title);
        i.putExtra(EXTRA_URL, url);
        activity.startActivity(i);
    }
    
    /**
     * 发送系统短信
     *
     * @param activity
     */
    public static void sendMessage(Activity activity, final String msg) {
        Uri smsToUri = Uri.parse("smsto:");
        Intent intent = new Intent(Intent.ACTION_SENDTO, smsToUri);
        intent.putExtra("sms_body", msg);
        activity.startActivity(intent);
    }

    
}
