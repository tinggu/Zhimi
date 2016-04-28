package com.linjin.zhimi.account;

import com.cyou.band.DataCenter;
import com.cyou.band.api.AccuntApi;
import com.cyou.band.event.FindPWSuccessfulEvent;
import com.cyou.band.event.LoginSuccessfulEvent;
import com.cyou.band.model.BaseModel;
import com.cyou.band.model.account.AuthCredentials;
import com.cyou.band.model.account.User;
import com.cyou.band.rest.ApiCode;
import com.cyou.band.rest.RestUtils;
import com.cyou.common.utils.LogUtils;
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
 * Date       : 2015/6/8 15:34
 */
public class FindPWPresenter extends AccuntPresenter<FindPWView> {

    private AccuntApi accuntApi = RestUtils.createApi(AccuntApi.class);
//    private String checkCode;

    public void doGetCheckCode(String mobileNum) {

        Subscriber<BaseModel> subscriber = new Subscriber<BaseModel>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                if (isViewAttached()) {
                    getView().showTip(e.getMessage());
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
        Observable<BaseModel> observable = accuntApi.getCheckCode(mobileNum, AccuntApi.TYPE_FIND_PASSWORD);
        observable.compose(new AndroidSchedulerTransformer<BaseModel>()).subscribe(subscriber);
//        AppProvide.applyScheduler(accuntApi.getCheckCode(mobileNum, AccuntApi.TYPE_FIND_PASSWORD))
//                .subscribe(subscriber);
    }

    public void doRegister(AuthCredentials credentials) {
        if (isViewAttached())
            getView().showLoading();
        super.doBase(credentials);
//        String inputCode = credentials.getCode();
//        if (inputCode.equals(checkCode)) {
//            super.doBase(credentials);
//        } else {
//            getView().showError("验证码不正确");
//        }

    }

    @Override
    public Subscriber<User> createSubscriber() {
        return new Subscriber<User>() {
            @Override
            public void onCompleted() {
                if (isViewAttached()) {
                    getView().hideLoading();
                    getView().finish();
                }
            }

            @Override
            public void onError(Throwable e) {
                if (isViewAttached()) {
                    getView().showTip(e.getMessage());
                }
            }

            @Override
            public void onNext(User account) {
                LogUtils.i("login", "FindPWPresenter onNext ");
                  
                DataCenter.getInstance().saveUser(account);
                EventBus.getDefault().post(new LoginSuccessfulEvent());

                EventBus.getDefault().post(new FindPWSuccessfulEvent());
            }

        };
    }

    @Override
    public Observable<User> createObservable(AuthCredentials credentials) {

        return accuntApi.findPassword(credentials.getMobileNum(), credentials.getPassword(), credentials.getCode())
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
