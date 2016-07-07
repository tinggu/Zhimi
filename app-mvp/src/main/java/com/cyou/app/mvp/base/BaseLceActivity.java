package com.cyou.app.mvp.base;

import android.os.Bundle;
import android.view.View;

import com.cyou.app.mvp.lce.MvpLceActivity;


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
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        getMvpView().loadData(false);
    }

    @Override
    public void refresh() {

    }
}
