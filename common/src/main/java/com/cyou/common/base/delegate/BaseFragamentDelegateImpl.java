package com.cyou.common.base.delegate;

import com.cyou.common.utils.TrackUtils;
import com.cyou.quick.mvp.MvpPresenter;
import com.cyou.quick.mvp.MvpView;
import com.cyou.quick.mvp.delegate.FragmentMvpDelegateImpl;
import com.cyou.quick.mvp.delegate.MvpDelegateCallback;

/**
 * Description:
 * Copyright  : Copyright (c) 2015
 * Company    : 北京畅游天下网络科技有限公司
 * Author     : wangjia_bi
 * Date       : 2016/1/28 11:52
 */
public class BaseFragamentDelegateImpl<V extends MvpView, P extends MvpPresenter<V>>
        extends FragmentMvpDelegateImpl<V, P> {

    TrackUtils trackUtils;

    public BaseFragamentDelegateImpl(MvpDelegateCallback<V, P> delegateCallback) {
        super(delegateCallback);
        trackUtils = TrackUtils.getInstance();
    }

    @Override
    public void onResume() {
        super.onResume();
        trackUtils.onPageStart(this.getClass().getSimpleName());
    }

    @Override
    public void onPause() {
        super.onPause();
        trackUtils.onPageEnd(this.getClass().getSimpleName());
    }

}
