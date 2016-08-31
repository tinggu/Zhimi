package com.linjin.zhimi.business;


import com.cyou.quick.mvp.MvpView;


public interface BusinessView extends MvpView {
//    String FLAG_SUMMARY = "summary";
//    String FLAG_DRAFT = "draft";
//    String FLAG_DETAILS = "details";
//    String FLAG_LATEST = "latest";
//    String FLAG_COLLENT = "collent";

    void showSummary();
    
    void showDraft();
    
    void showLatest();
    
    void showCollent();
    
    void showDetails();
    
}
