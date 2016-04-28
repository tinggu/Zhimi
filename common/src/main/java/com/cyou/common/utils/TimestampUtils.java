package com.cyou.common.utils;

import com.cyou.band.DataCenter;
import com.cyou.band.api.TimestampApi;
import com.cyou.band.model.Timestamp;
import com.cyou.band.rest.ApiCode;
import com.cyou.band.rest.RestUtils;

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
