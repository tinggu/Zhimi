package com.linjin.zhimi.friend.search;


import com.cyou.quick.mvp.MvpBasePresenter;

/**
 * Description:
 * Copyright  : Copyright (c) 2015
 * Company    : 北京畅游天下网络科技有限公司
 * Author     : wangjia_bi
 * Date       : 2015/6/11 14:53
 */
public class SearchFriendPresenter extends MvpBasePresenter<SearchFriendView> {
 
    
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
