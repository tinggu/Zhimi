package com.cyou.band;

/**
 * Created by liujianguang on 2015/12/18.
 */
public interface AppConstants {

    //---------------OkHttp配置-----------------------
    String RESPONSE_CACHE = "netCache";

    long RESPONSE_CACHE_SIZE = 10 * 1024 * 1024;

    long HTTP_CONNECT_TIMEOUT = 1000 * 30;

    long HTTP_READ_TIMEOUT = HTTP_CONNECT_TIMEOUT;

    //-------------项目配置------------------------
    String PLATFORM_VALUE = "and";
    /**
     * 签名MD5
     */
    String APP_SIGN_MD5 = "$2a$09$USLzavCB5Gpd5evaAWk8etjkaJmioyi7fhgYLqsWqJaDskRXQJ@@";

    /**
     * 项目名字做为资源访问路径
     */
    String PROJECT_NAME = "socialComment";

    String IMAGE_PATH = "/group/image/";

    String SCRET_KEY = "9cb5ee00b6a87c24a2fabf87c6dba2e1";
}
