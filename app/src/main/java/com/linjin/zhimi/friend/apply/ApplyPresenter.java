package com.linjin.zhimi.friend.apply;


import com.cyou.quick.mvp.MvpBasePresenter;
import com.linjin.zhimi.publish.PublishView;

/**
 * Description:
 * Copyright  : Copyright (c) 2015
 * Company    : 北京畅游天下网络科技有限公司
 * Author     : wangjia_bi
 * Date       : 2015/6/11 14:53
 */
public class ApplyPresenter extends MvpBasePresenter<PublishView> {

    private int currStep;
    
    private String content;
    
    private String supplement;

    public void setContent(String content) {
        this.content = content;
    }

    public void setSupplement(String supplement) {
        this.supplement = supplement;
    }

   
    
}
