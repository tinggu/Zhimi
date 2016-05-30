package com.linjin.zhimi.account.login;

import com.linjin.zhimi.DataCenter;
import com.linjin.zhimi.account.AccuntPresenter;
import com.linjin.zhimi.event.LoginSuccessfulEvent;
import com.linjin.zhimi.model.account.AuthCredentials;
import com.linjin.zhimi.model.account.UserModel;
import com.linjin.zhimi.rest.ApiCode;
import com.tinggu.common.utils.LogUtils;

import org.greenrobot.eventbus.EventBus;

import rx.Observable;
import rx.Subscriber;

/**
 * Description:
 * Copyright  : Copyright (c) 2015
 * Company    : 北京畅游天下网络科技有限公司
 * Author     : wangjia_bi
 * Date       : 2015/6/8 15:34
 */
public class LoginPresenter extends AccuntPresenter<LoginView> {
    

    public void doLogin(AuthCredentials credentials) {
        if (isViewAttached()) {
            getView().showLoading();
        }
        super.doBase(credentials);
    }

    @Override
    public Subscriber<UserModel> createSubscriber() {
        return new Subscriber<UserModel>() {
            @Override
            public void onCompleted() {
                LogUtils.i("login", "LoginSuccess onCompleted ");
                if (isViewAttached()) {
                    getView().hideLoading();
                }
            }

            @Override
            public void onError(Throwable e) {
                LogUtils.i("login", "LoginSuccess onError ");
                if (isViewAttached()) {
                    String message = e.getMessage();
                    getView().showTip(message);
                }
            }

            @Override
            public void onNext(UserModel userModel) {
                LogUtils.i("login", "LoginSuccess onNext ");
                if (userModel.getCode() == ApiCode.SUCCESS_CODE) {
                    LogUtils.i("login", "LoginSuccess onNext ");
                    DataCenter.getInstance().saveUser(userModel.getUser());
//                    initConstants();
                    EventBus.getDefault().post(new LoginSuccessfulEvent());
                } else {
                    if (isViewAttached()) {
                     
                        getView().showTip( userModel.getMsg());
                    }
                    
                }
            }
        };
    }

    @Override
    public Observable<UserModel> createObservable(AuthCredentials credentials) {
//        AccuntApi accuntApi = RestUtils.createApi(AccuntApi.class);
        //保存手机号
//        DataCenter.getInstance().saveUserMobile(credentials.getMobileNum());
        return accuntApi.login(credentials.getMobileNum(), credentials.getPassword(), credentials.getDeviceToken());
    }

}