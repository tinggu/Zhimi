package com.linjin.zhimi.account;

import com.cyou.quick.mvp.rx.scheduler.AndroidSchedulerTransformer;
import com.zhimi.model.BaseModel;
import com.zhimi.model.account.AuthCredentials;
import com.zhimi.model.account.UserModel;
import com.linjin.zhimi.DataCenter;
import com.linjin.zhimi.api.AccuntApi;
import com.linjin.zhimi.event.FindPWSuccessfulEvent;
import com.linjin.zhimi.event.LoginSuccessfulEvent;
import com.linjin.zhimi.rest.ApiCode;
import com.linjin.zhimi.rest.RestUtils;
import com.tinggu.common.utils.LogUtils;

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
                    getView().showTip(ApiCode.getMsg(code.getCode()));
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
    public Subscriber<UserModel> createSubscriber() {
        return new Subscriber<UserModel>() {
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
            public void onNext(UserModel account) {
                LogUtils.i("login", "FindPWPresenter onNext ");

                DataCenter.getInstance().saveUser(account.getUser());
                EventBus.getDefault().post(new LoginSuccessfulEvent());

                EventBus.getDefault().post(new FindPWSuccessfulEvent());
            }

        };
    }

    @Override
    public Observable<UserModel> createObservable(AuthCredentials credentials) {

        return accuntApi.findPassword(credentials.getMobileNum(), credentials.getPassword(), credentials.getCode())
                .flatMap(new Func1<UserModel, Observable<UserModel>>() {
                    @Override
                    public Observable<UserModel> call(UserModel token) {
                        if (token.getCode() == ApiCode.SUCCESS_CODE) {
                            DataCenter.getInstance().saveUser(token.getUser());
                            return Observable.just(token);
                        } else {
                            return Observable.error(new Throwable(ApiCode.getMsg(token.getCode())));
                        }
                    }
                });

    }
}
