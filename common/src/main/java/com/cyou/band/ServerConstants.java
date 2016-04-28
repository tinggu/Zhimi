package com.cyou.band;

/**
 * Created by liujianguang on 2015/12/18.
 */
public interface ServerConstants {

    String API_BASE_URL = "http://101.200.208.96:9000";//测试服务器
//    String API_BASE_URL = "http://10.12.24.116:8080/groupWeb";//发现服务器

//    String API_BASE_URL = "http://101.200.31.239/";//正式服务器

    // 注意：在微信授权的时候，必须传递appSecret
    String WEIXIN_PLAT_appId = "wxe8e250e4fd81152f";
    String WEIXIN_PLAT_appSecret = "027b713609cc8c2fa67485cae7d1440d";

    //QQ和QZone
    String QQQZone_PLAT_appId = "1104733731";
    String QQQZone_PLAT_appKey = "r08TVyqXQiwRXASA";

}
