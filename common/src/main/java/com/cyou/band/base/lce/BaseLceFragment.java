package com.cyou.band.base.lce;

import android.view.View;

import com.cyou.band.base.delegate.BaseFragamentDelegateImpl;
import com.cyou.quick.mvp.delegate.FragmentMvpDelegate;
import com.cyou.quick.mvp.lce.MvpLceFragment;

/**
 * Description:
 * Copyright  : Copyright (c) 2015
 * Company    : 北京畅游天下网络科技有限公司
 * Author     : wangjia_bi
 * Date       : 2016/1/28 11:46
 */
public abstract class BaseLceFragment<CV extends View, M, V extends BaseMvpLceView<M>, P extends BaseLcePresenter<V, M>>
        extends MvpLceFragment<CV, M, V, P>
        implements BaseMvpLceView<M> {

    protected FragmentMvpDelegate<V, P> getMvpDelegate() {
        if (this.mvpDelegate == null) {
            this.mvpDelegate = new BaseFragamentDelegateImpl<>(this);
        }
        return this.mvpDelegate;
    }

//    @Override
//    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
//        super.onViewCreated(view, savedInstanceState);
//        getMvpView().loadData(false);
//    }
    
    
}
