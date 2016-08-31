package com.cyou.common.rx;

import com.cyou.common.entity.RESTResult;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Func1;

/**
 * Description:  Rx处理服务器返回
 * Copyright  : Copyright (c) 2015
 * Company    : 北京畅游天下网络科技有限公司
 * Author     : wangjia_bi
 * Date       : 2016/8/25 17:45
 */
public class RxResultHelper {

    public static <T> Observable.Transformer<RESTResult<T>, T> handleResult() {
        return new Observable.Transformer<RESTResult<T>, T>() {
            @Override
            public Observable<T> call(Observable<RESTResult<T>> tObservable) {
                return tObservable.flatMap(
                        new Func1<RESTResult<T>, Observable<T>>() {
                            @Override
                            public Observable<T> call(RESTResult<T> result) {

                                if (result.code == RESTResult.SUCCESS) {
                                    return createData(result.getData());
                                } else if (result.code == RESTResult.SIGN_OUT) {
                                    // 处理被踢出登录情况
                                } else {
                                    return Observable.error(new ServerException(result.message));
                                }
                                return Observable.empty();

                            }
                        }

                );
            }
        };
    }

    private static <T> Observable<T> createData(final T t) {
        return Observable.create(new Observable.OnSubscribe<T>() {
            @Override
            public void call(Subscriber<? super T> subscriber) {
                try {
                    subscriber.onNext(t);
                    subscriber.onCompleted();
                } catch (Exception e) {
                    subscriber.onError(e);
                }
            }
        });
    }
}
