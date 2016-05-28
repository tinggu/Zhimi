package com.linjin.zhimi.friend.find;


import android.text.TextUtils;

import com.cyou.quick.mvp.MvpBasePresenter;
import com.linjin.zhimi.publish.PublishView;
import com.linjin.zhimi.publish.Publish1Activity;
import com.tinggu.common.utils.KeyboardUtils;

/**
 * Description:
 * Copyright  : Copyright (c) 2015
 * Company    : 北京畅游天下网络科技有限公司
 * Author     : wangjia_bi
 * Date       : 2015/6/11 14:53
 */
public class FindFriendPresenter extends MvpBasePresenter<PublishView> {

    private Publish1Activity publish1Activity;
    
    private int currStep;
    
    private String content;
    
    private String supplement;

    public void setContent(String content) {
        this.content = content;
    }

    public void setSupplement(String supplement) {
        this.supplement = supplement;
    }

    public FindFriendPresenter(Publish1Activity publish1Activity) {
        this.publish1Activity = publish1Activity;
    }
    
    public void back() {
        if(currStep == 0){
            if(!TextUtils.isEmpty(content)){
                publish1Activity.showGiveUpDialog();
                return;
            }
            publish1Activity.finish();
            return;
        }
        currStep--;
        KeyboardUtils.callBackKeyClick();
    }

    //    private String checkCode;
    public void nextStep() {
        switch (currStep) {
            case 1:
                publish1Activity.showPublish1();
                break;
            case 2:
                publish1Activity.showPublish2();
                break;
        }
        currStep++;
    }
}
