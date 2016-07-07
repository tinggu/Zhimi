package com.cyou.app.mvp.base;

import android.view.View;

import com.cyou.app.mvp.lce.MvpLceFragment;


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

  
    
}
