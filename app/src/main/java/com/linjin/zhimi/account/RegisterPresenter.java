package com.linjin.zhimi.account;


import com.linjin.zhimi.DataCenter;
import com.linjin.zhimi.api.AccuntApi;
import com.linjin.zhimi.event.RegisterSuccessfulEvent;
import com.cyou.zhimi.model.BaseModel;
import com.cyou.zhimi.model.account.AuthCredentials;
import com.cyou.zhimi.model.account.User;
import com.linjin.zhimi.rest.ApiCode;
import com.cyou.quick.mvp.rx.scheduler.AndroidSchedulerTransformer;

import de.greenrobot.event.EventBus;
import rx.Observable;
import rx.Subscriber;
import rx.functions.Func1;

/**
 * Description:
 * Copyright  : Copyright (c) 2015
 * Company    : 北京畅游天下网络科技有限公司
 * Author     : wangjia_bi
 * Date       : 2015/6/11 14:53
 */
public class RegisterPresenter extends AccuntPresenter<RegisterView> {

   
//    private String checkCode;

    public void doGetCheckCode(String mobileNum) {

        Subscriber<BaseModel> subscriber = new Subscriber<BaseModel>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                if (isViewAttached()) {
                    /*getView().showError(e.getMessage());*/
                    String message = e.getMessage();
                    getView().showTip(message);
                }
            }

            @Override
            public void onNext(BaseModel code) {
                if (isViewAttached() && code.getCode() != ApiCode.SUCCESS_CODE) {
                    getView().showTip(code.getMsg());
                }
//                checkCode = code.getCheckCode();
            }
        };

        Observable<BaseModel> observable = accuntApi.getCheckCode(mobileNum, AccuntApi.TYPE_REGISTER);
        observable.compose(new AndroidSchedulerTransformer<BaseModel>()).subscribe(subscriber);
//        AppProvide.applyScheduler()  .subscribe(subscriber);

    }

    public void doRegister(AuthCredentials credentials) {
        if (isViewAttached())
            getView().showLoading();
        super.doBase(credentials);
    }

    @Override
    public Subscriber<User> createSubscriber() {
        Subscriber<User> subscriber = new Subscriber<User>() {
            @Override
            public void onCompleted() {
                if (isViewAttached()) {
                    getView().finish();
                }
            }

            @Override
            public void onError(Throwable e) {
                if (isViewAttached()) {
                   /* getView().showError(e.getMessage());*/
                    String message = e.getMessage();
                    getView().showTip(message);
                }
            }

            @Override
            public void onNext(User account) {
                EventBus.getDefault().post(new RegisterSuccessfulEvent());
            }

        };
        return subscriber;
    }

    @Override
    public Observable<User> createObservable(AuthCredentials credentials) {
        //保存手机号
//       DataCenter.getInstance().saveUserMobile(credentials.getMobileNum());


        return accuntApi.register(credentials.getCode(), credentials.getMobileNum(), credentials.getPassword())
                        .flatMap(new Func1<User, Observable<User>>() {
                            @Override
                            public Observable<User> call(User token) {
                                if (token.getCode() == ApiCode.SUCCESS_CODE) {
                                   DataCenter.getInstance().saveUser(token);
                                    return Observable.just(token);
                                } else {
                                    return Observable.error(new Throwable(token.getMsg()));
                                }
                            }
                        });


    }
}
