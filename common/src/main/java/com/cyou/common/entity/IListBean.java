package com.cyou.common.entity;

import java.util.List;

import rx.Observable;

/**
 * Description:
 * Copyright  : Copyright (c) 2015
 * Company    : 北京畅游天下网络科技有限公司
 * Author     : wangjia_bi
 * Date       : 2016/7/13 16:39
 */
public interface IListBean<M> {
    Observable<List<M>> getPageAt(int page);

//    void setParam(Map<String, String> param);
}
