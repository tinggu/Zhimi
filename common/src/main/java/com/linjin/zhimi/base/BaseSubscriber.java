package com.linjin.zhimi.base;

import android.text.TextUtils;
import android.widget.Toast;

import com.cyou.quick.QuickApplication;
import com.zhimi.model.BaseModel;
import com.linjin.zhimi.DataCenter;
import com.linjin.zhimi.rest.ApiCode;

import rx.Subscriber;

/**
 * Description:
 * Copyright  : Copyright (c) 2015
 * Company    : 北京畅游天下网络科技有限公司
 * Author     : liujianguang
 * Date       : 2016/1/12
 **/

public class BaseSubscriber<T> extends Subscriber<T> {

   

    @Override
    public void onCompleted() {

    }

    @Override
    public void onError(Throwable e) {
        String message = e.getMessage();
        if (!TextUtils.isEmpty(message)) {
            //Toast.makeText(QuickApplication.getInstance(), message, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onNext(T o) {

        BaseModel model;
        if ((o instanceof BaseModel)) {
            model = (BaseModel) o;
        } else {
            return;
        }

        if (model.getCode() == ApiCode.FAILED_CODE_TOKEN_INVALID) {
//                IntentStarter.showLogin(null);
            DataCenter.getInstance().removeUser();
            String tip = ApiCode.getMsg(model.getCode());
            if (!TextUtils.isEmpty(tip)) {
                Toast.makeText(QuickApplication.getInstance(), tip, Toast.LENGTH_SHORT).show();
            }
            return;
        }

        if (model.getCode() == ApiCode.FAILED_CODE_SERVER_ERROR) {
            String tip = ApiCode.getMsg(model.getCode());
            if (!TextUtils.isEmpty(tip)) {
                Toast.makeText(QuickApplication.getInstance(), tip, Toast.LENGTH_SHORT).show();
            }
        }

    }
}
