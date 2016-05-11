package cn.smssdk;

import android.content.Context;

/**
 * Description:
 * Copyright  : Copyright (c) 2015
 * Company    : 北京畅游天下网络科技有限公司
 * Author     : wangjia_bi
 * Date       : 2016/5/2 11:34
 */
public class SMSSDKInitUtils {
    // 填写从短信SDK应用后台注册得到的APPKEY
    //此APPKEY仅供测试使用，且不定期失效，请到mob.com后台申请正式APPKEY
    public static final String APPKEY = "12511def5d812";

    // 填写从短信SDK应用后台注册得到的APPSECRET
    private static final String APPSECRET = "832a86245d2ebb4e32927727300d91d4";
    
    public static void initSDK(Context context){
        // 初始化短信SDK
        SMSSDK.initSDK(context, APPKEY, APPSECRET, true);
    }
    
}
