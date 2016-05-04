package com.linjin.zhimi.base.lce;

import com.cyou.quick.mvp.rx.lce.MvpLceRxPresenter;

/**
 * Description:
 * Copyright  : Copyright (c) 2015
 * Company    : 北京畅游天下网络科技有限公司
 * Author     : wangjia_bi
 * Date       : 2016/2/1 17:50
 */
public abstract class BaseLcePresenter<V extends BaseMvpLceView<M>, M> 
        extends MvpLceRxPresenter<V, M> {

    public abstract void load(boolean pullToRefresh);
}
