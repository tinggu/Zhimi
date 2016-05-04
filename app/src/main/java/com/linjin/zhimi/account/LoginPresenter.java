package com.linjin.zhimi.account;

import com.linjin.zhimi.DataCenter;
import com.linjin.zhimi.event.LoginSuccessfulEvent;
import com.cyou.zhimi.model.account.AuthCredentials;
import com.cyou.zhimi.model.account.LoginException;
import com.cyou.zhimi.model.account.User;
import com.linjin.zhimi.rest.ApiCode;
import com.linjin.common.utils.LogUtils;

import de.greenrobot.event.EventBus;
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
    public Subscriber<User> createSubscriber() {
        return new Subscriber<User>() {
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
//                    if (e instanceof LoginException) {
//                        int errorCode = ((LoginException) e).errorEode;
//                        if (errorCode == ApiCode.FAILED_CODE_PASSWORD_ERROR) {
//                            getView().showFindPassword();
//                        }
//                    }
                }
            }

            @Override
            public void onNext(User token) {
                LogUtils.i("login", "LoginSuccess onNext ");
                if (token.getCode() == ApiCode.SUCCESS_CODE) {
                    LogUtils.i("login", "LoginSuccess onNext ");
                    DataCenter.getInstance().saveUser(token);
//                    initConstants();
                    EventBus.getDefault().post(new LoginSuccessfulEvent());
                } else {
                    onError(new LoginException(token.getCode(), token.getMsg()));
                }
            }
        };
    }

    @Override
    public Observable<User> createObservable(AuthCredentials credentials) {
//        AccuntApi accuntApi = RestUtils.createApi(AccuntApi.class);
        //保存手机号
//        DataCenter.getInstance().saveUserMobile(credentials.getMobileNum());
        return accuntApi.login(credentials.getMobileNum(), credentials.getPassword());
    }
    
//    public void initConstants(){
//        Log.i("token", "initConstants: ");
////        accuntApi.getConstants().observeOn(AndroidSchedulers.mainThread())
//        accuntApi.getConstants().compose(new AndroidSchedulerTransformer<ConstantsModel>())
//                .subscribe(new Subscriber<ConstantsModel>() {
//            @Override
//            public void onCompleted() {
//            }
//
//            @Override
//            public void onError(Throwable e) {
//                e.printStackTrace();
//            }
//
//            @Override
//            public void onNext(ConstantsModel constantsModel) {
//                DataCenter.getInstance().saveConstants(constantsModel);
//            }
//        });
//    }

}