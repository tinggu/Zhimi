package com.linjin.zhimi.account;

import android.widget.Toast;

import com.cyou.band.DataCenter;
import com.cyou.band.api.AccuntApi;
import com.cyou.band.model.account.User;
import com.cyou.band.rest.ApiCode;
import com.cyou.band.rest.RestUtils;
import com.cyou.common.utils.MD5Utils;
import com.cyou.quick.QuickApplication;
import com.cyou.quick.mvp.MvpBasePresenter;
import com.cyou.quick.mvp.rx.scheduler.AndroidSchedulerTransformer;

import rx.Observable;
import rx.Subscriber;


public class ChangePasswordPresenter extends MvpBasePresenter<ChangePasswordView> {

    //发送新旧密码给服务器
    public void changePassword(String oldPassword, String newPassword) {
        if (isViewAttached())
            getView().showLoading();
        AccuntApi accuntApi = RestUtils.createApi(AccuntApi.class);

        subscribeChangePassword(accuntApi.changePassword(
                MD5Utils.getStringMD5_32(oldPassword),
                MD5Utils.getStringMD5_32(newPassword)
        ));
    }

    protected void subscribeChangePassword(Observable<User> observable) {

        Subscriber<User> subscriber = new Subscriber<User>() {
            @Override
            public void onCompleted() {
                getView().hideLoading();
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(User changePassword) {
                if (changePassword.getCode() == ApiCode.SUCCESS_CODE) {
                    Toast.makeText(QuickApplication.getInstance(), "密码修改成功", Toast.LENGTH_SHORT).show();
                   DataCenter.getInstance().saveUser(changePassword);
                } else {
                    Toast.makeText(QuickApplication.getInstance(), "密码修改失败", Toast.LENGTH_SHORT).show();

                }
            }
        };
        observable.compose(new AndroidSchedulerTransformer<User>()).subscribe(subscriber);
    }

}
