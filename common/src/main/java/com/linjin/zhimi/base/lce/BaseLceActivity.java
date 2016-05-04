package com.linjin.zhimi.base.lce;

import android.os.Bundle;
import android.view.View;

import com.linjin.zhimi.base.delegate.BaseActivityDelegateImpl;
import com.cyou.quick.mvp.delegate.ActivityMvpDelegate;
import com.cyou.quick.mvp.lce.MvpLceActivity;

/**
 * Description:
 * Copyright  : Copyright (c) 2015
 * Company    : 北京畅游天下网络科技有限公司
 * Author     : liujianguang
 * Date       : 2015/12/21
 **/

public abstract class BaseLceActivity<CV extends View, M, V extends BaseMvpLceView<M>, P extends BaseLcePresenter<V, M>>
        extends MvpLceActivity<CV, M, V, P>
        implements BaseMvpLceView<M>{

    @Override
    protected ActivityMvpDelegate<V, P> getMvpDelegate() {
        if (this.mvpDelegate == null) {
            this.mvpDelegate = new BaseActivityDelegateImpl<>(this, getClass().getSimpleName());
        }

        return this.mvpDelegate;
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        getMvpView().loadData(false);
    }

    @Override
    public void refresh() {

    }
}
