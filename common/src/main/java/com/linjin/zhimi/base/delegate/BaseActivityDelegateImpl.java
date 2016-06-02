package com.linjin.zhimi.base.delegate;

import com.linjin.zhimi.utils.TrackUtils;
import com.cyou.quick.QuickApplication;
import com.cyou.quick.mvp.MvpPresenter;
import com.cyou.quick.mvp.MvpView;
import com.cyou.quick.mvp.delegate.ActivityMvpDelegateImpl;
import com.cyou.quick.mvp.delegate.MvpDelegateCallback;

/**
 * Description:
 * Copyright  : Copyright (c) 2015
 * Company    : 北京畅游天下网络科技有限公司
 * Author     : wangjia_bi
 * Date       : 2016/1/28 14:17
 */
public class BaseActivityDelegateImpl<V extends MvpView, P extends MvpPresenter<V>>
        extends ActivityMvpDelegateImpl<V, P> {

    private String activityName;
    private TrackUtils trackUtils;


    public BaseActivityDelegateImpl(MvpDelegateCallback<V, P> delegateCallback, String activityName) {
        super(delegateCallback);
        this.activityName = activityName;
        this.trackUtils = TrackUtils.getInstance();
    }

    @Override
    public void onResume() {
        super.onResume();
        trackUtils.onResume(QuickApplication.getInstance());
        trackUtils.onPageStart(activityName);
    }

    @Override
    public void onPause() {
        super.onPause();
        trackUtils.onPause(QuickApplication.getInstance());
        trackUtils.onPageEnd(activityName);
    }
}
