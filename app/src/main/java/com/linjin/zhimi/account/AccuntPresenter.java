package com.linjin.zhimi.account;

import com.cyou.app.mvp.rx.scheduler.AndroidSchedulerTransformer;
import com.cyou.quick.mvp.MvpBasePresenter;
import com.cyou.quick.mvp.MvpView;
import com.linjin.zhimi.api.AccuntApi;
import com.linjin.zhimi.model.account.AuthCredentials;
import com.linjin.zhimi.model.account.UserModel;
import com.linjin.zhimi.rest.RestUtils;

import rx.Observable;
import rx.Subscriber;

/**
 * Description:
 * Copyright  : Copyright (c) 2015
 * Company    : 北京畅游天下网络科技有限公司
 * Author     : wangjia_bi
 * Date       : 2015/6/12 15:24
 */
public abstract class AccuntPresenter<V extends MvpView> extends MvpBasePresenter<V> {

    private Subscriber<UserModel> subscriber;
    protected AccuntApi accuntApi = RestUtils.createApi(AccuntApi.class);
//    protected SchedulerTransformer schedulerTransformer = AppProvide.schedulerTransformer();
    
    public AccuntPresenter() {
        accuntApi = RestUtils.createApi(AccuntApi.class);
    }
    
    /**
     * Cancels any previous callback
     */
    protected void cancelSubscription() {
        if (subscriber != null && !subscriber.isUnsubscribed()) {
            subscriber.unsubscribe();
            subscriber = null;
        }
    }

    public abstract Subscriber<UserModel> createSubscriber();

    public abstract Observable<UserModel> createObservable(AuthCredentials credentials);

    protected void doBase(AuthCredentials credentials) {
        // Kind of "callback"
        cancelSubscription();
        //can't use lastest subscriber
        subscriber = createSubscriber();

        Observable<UserModel> observable = createObservable(credentials);
        observable.compose(new AndroidSchedulerTransformer<UserModel>()).subscribe(subscriber);
    }

    @Override
    public void detachView(boolean retainInstance) {
        super.detachView(retainInstance);
        if (!retainInstance) {
            cancelSubscription();
        }
    }

//    protected final void saveAndUpdateUserInfo(UserToken user) {
//        Context context = QuickApplication.getInstance();
//        SharedPreferencesUtils spUtils = SharedPreferencesUtils.getInstance(context);
//        DataCenter.getInstance().saveUser(user);
//        spUtils.setParam(SharedPreferencesUtils.Type.STRRING, KEY_USER_ID, user.getFriendUserId());
//        spUtils.setParam(SharedPreferencesUtils.Type.STRRING, KEY_USER_TOKEN, user.getToken());
//    }

}
