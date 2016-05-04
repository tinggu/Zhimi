package com.tinggu.common.utils;

import com.linjin.zhimi.DataCenter;
import com.linjin.zhimi.api.TimestampApi;
import com.cyou.zhimi.model.Timestamp;
import com.linjin.zhimi.rest.ApiCode;
import com.linjin.zhimi.rest.RestUtils;

import rx.Observable;
import rx.Subscriber;

/**
 * Description:
 * Copyright  : Copyright (c) 2015
 * Company    : 北京畅游天下网络科技有限公司
 * Author     : liujianguang
 * Date       : 2015/12/30
 **/

public class TimestampUtils {


    public static void requestServerTimestamp() {

        TimestampApi timestampApi = RestUtils.createApi(TimestampApi.class);

        Observable<Timestamp> observable = timestampApi.getServerTime();

        Subscriber<Timestamp> subscriber = new Subscriber<Timestamp>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(Timestamp timestamp) {
                if (timestamp.code == ApiCode.SUCCESS_CODE) {
                    DataCenter.getInstance().setServerTime(timestamp.timestamp);
                }
            }
        };

    }

    ;

}
