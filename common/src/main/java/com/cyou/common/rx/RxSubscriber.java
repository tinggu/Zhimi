package com.cyou.common.rx;

import com.cyou.common.utils.NetWorkUtils;
import com.cyou.quick.QuickApplication;

import rx.Subscriber;

/**
 * Description:  封装Subscriber
 * Copyright  : Copyright (c) 2015
 * Company    : 北京畅游天下网络科技有限公司
 * Author     : wangjia_bi
 * Date       : 2016/8/25 18:30
 */
public abstract class RxSubscriber<T> extends Subscriber<T> {

    @Override
    public void onCompleted() {

    }

    @Override
    public void onError(Throwable e) {
        e.printStackTrace();

        if (!NetWorkUtils.isNetConnected(QuickApplication.getInstance())) {
            _onError("网络不可用!");
            return;
        }

        if (e instanceof ServerException) {
            _onError(e.getMessage());
//        } else if {
            // 更多异常处理
        }else{
            _onError("请求失败，请稍后重试...");
        }
    }

    @Override
    public void onNext(T t) {
        _onNext(t);
    }

    public abstract void _onNext(T t);

    public abstract void _onError(String msg);
}