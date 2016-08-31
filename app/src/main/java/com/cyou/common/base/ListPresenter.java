package com.cyou.common.base;

import java.util.List;

import rx.Observable;

/**
 * Description:
 * Copyright  : Copyright (c) 2015
 * Company    : 北京畅游天下网络科技有限公司
 * Author     : wangjia_bi
 * Date       : 2016/2/1 17:50
 */
public abstract class ListPresenter<M> {

    public abstract Observable<List<M>> getPageAt(int page);
}
