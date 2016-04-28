package com.linjin.zhimi.account;

import com.cyou.quick.mvp.MvpView;

/**
 * Created by nidingya on 2015/8/5.
 */
public interface ChangePasswordView extends MvpView {

    int ERROR_TYPE_OTHER = 2;

    void showError(int type, Throwable e);

    void showLoading();

    void hideLoading();
}
