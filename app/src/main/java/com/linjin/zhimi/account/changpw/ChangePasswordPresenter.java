package com.linjin.zhimi.account.changpw;

import android.widget.Toast;

import com.cyou.app.mvp.rx.scheduler.AndroidSchedulerTransformer;
import com.cyou.quick.QuickApplication;
import com.cyou.quick.mvp.MvpBasePresenter;
import com.cyou.common.utils.MD5Utils;
import com.linjin.zhimi.DataCenter;
import com.linjin.zhimi.api.AccuntApi;
import com.linjin.zhimi.model.account.UserModel;
import com.linjin.zhimi.rest.ApiCode;
import com.linjin.zhimi.rest.RestUtils;

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

    protected void subscribeChangePassword(Observable<UserModel> observable) {

        Subscriber<UserModel> subscriber = new Subscriber<UserModel>() {
            @Override
            public void onCompleted() {
                getView().hideLoading();
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(UserModel changePassword) {
                if (changePassword.getCode() == ApiCode.SUCCESS_CODE) {
                    Toast.makeText(QuickApplication.getInstance(), "密码修改成功", Toast.LENGTH_SHORT).show();
                   DataCenter.getInstance().saveUser(changePassword.getUser());
                } else {
                    Toast.makeText(QuickApplication.getInstance(), "密码修改失败", Toast.LENGTH_SHORT).show();

                }
            }
        };
        observable.compose(new AndroidSchedulerTransformer<UserModel>()).subscribe(subscriber);
    }

}
