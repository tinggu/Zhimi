package com.linjin.zhimi.business;

import com.cyou.quick.mvp.MvpBasePresenter;

/**
 * Description:
 * Copyright  : Copyright (c) 2015
 * Company    : 北京畅游天下网络科技有限公司
 * Author     : wangjia
 * Date       : 2016/1/4
 **/

public class BusinessPresenter extends MvpBasePresenter<BusinessView> {


    public void show(String flag) {

        if (BusinessFlag.FLAG_SUMMARY.equals(flag)) {
            getView().showSummary();

        } else if (BusinessFlag.FLAG_DETAILS.equals(flag)) {
            getView().showDetails();
        } else if (BusinessFlag.FLAG_DRAFT.equals(flag)) {
            getView().showDraft();
        } else if (BusinessFlag.FLAG_COLLENT.equals(flag)) {
            getView().showCollent();
        } else if (BusinessFlag.FLAG_LATEST.equals(flag)) {
            getView().showLatest();
        }
    }
}
