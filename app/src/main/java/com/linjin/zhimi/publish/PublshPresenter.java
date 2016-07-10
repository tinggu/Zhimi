package com.linjin.zhimi.publish;


import android.os.Bundle;
import android.text.TextUtils;

import com.cyou.quick.mvp.MvpBasePresenter;
import com.linjin.zhimi.utils.KeyboardUtils;

/**
 * Description:
 * Copyright  : Copyright (c) 2015
 * Company    : 北京畅游天下网络科技有限公司
 * Author     : wangjia_bi
 * Date       : 2015/6/11 14:53
 */
public class PublshPresenter extends MvpBasePresenter<PublishView> {
    
//    private PublishActivity publishActivity;

    private int currStep;

    private String content;

    private String supplement;

    public void setContent(String content) {
        this.content = content;
    }

    public void setSupplement(String supplement) {
        this.supplement = supplement;
    }

//    public PublshPresenter(PublishActivity publishActivity) {
//        this.publishActivity = publishActivity;
//    }

    public void back() {
        if (currStep == 0) {
            if (!TextUtils.isEmpty(content)) {
                getView().showGiveUpDialog();
                return;
            }
            getView().finish();
            return;
        }
        currStep--;
        KeyboardUtils.callBackKeyClick();
    }

    //    private String checkCode;
    public void nextStep() {
        switch (currStep) {
            case 1:
                getView().showPublish1();
                break;
            case 2:
                getView().showPublish2();
                break;
        }
        currStep++;
    }
}
