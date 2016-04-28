package com.linjin.zhimi.account;

import com.cyou.quick.mvp.MvpView;

/**
 * Description:
 * Copyright  : Copyright (c) 2015
 * Company    : 北京畅游天下网络科技有限公司
 * Author     : wangjia_bi
 * Date       : 2015/6/8 14:55
 */
public interface LoginView extends MvpView {

    void showTip(String message);

    void showLoading();

    void hideLoading();

}