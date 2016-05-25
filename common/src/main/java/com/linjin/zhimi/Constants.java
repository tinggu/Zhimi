package com.linjin.zhimi;

/**
 * Created by liujianguang on 2015/12/18.
 */
public class Constants {

    //debug
    public static boolean DEBUG = true;
    
    

//    /**
//     * 友盟统计 appkey 5673daa367e58ee835002006
//     */
//    public final static String UMENG_APPKEY = "5673daa367e58ee835002006";
//    /**
//     * talkingdata统计
//     */
//    public final static String TD_APP_ID = "842192F1FC1CE49D57D66BCEE4A35BC5";

    /**
     * create latest question : select pic
     */
    public final static int REQUEST_CODE_CREATE_FROM_PHOTO = 1;
    public final static int REQUEST_CODE_CREATE_FROM_CAMERA = 2;
    public final static int REQUEST_CODE_CREATE_FROM_SEARCH = 3;
    public final static int REQUEST_CODE_CREATE_FROM_CROP = 4;

    public final static String RESULT_DATA_SEARCH_IMG_PATH = "result_data_search_img_path";

    /**
     * others activity which page we should load
     */
    public final static String OHTER_PAGE_INFO = "othter_page_info";

    public final static int OHTER_FEEDBACK = 1;

    public final static int SERVER_ERROR_SERVER = -1;
    public final static int SERVER_ERROR_NETWORK = -2;
    public final static int SERVER_ERROR_BAD_TOKEN = -3;
    public final static int SERVER_ERROR_NO_DATA = -4;

    public final static int IMG_OPTION_LIMIT_WIDTH = 362;
    public final static int IMG_OPTION_LIMIT_HEIGHT = 660;

    /**
     * share platform
     */
    public final static int SHARE_WECHAT = 1;
    public final static int SHARE_FRIENDS = 2;
    public final static int SHARE_QQ = 3;
    public final static int SHARE_QZONE = 4;
    public final static int SHARE_SINA = 5;
    public final static int SHARE_LINK = 6;
}
