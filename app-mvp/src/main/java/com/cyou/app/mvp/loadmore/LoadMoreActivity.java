package com.cyou.app.mvp.loadmore;

import android.view.View;

import com.cyou.app.mvp.lce.MvpLceActivity;
import com.cyou.quick.mvp.MvpPresenter; 

/**
 * Description:
 * Copyright  : Copyright (c) 2015
 * Company    : 北京畅游天下网络科技有限公司
 * Author     : wangjia_bi
 * Date       : 2015/7/8 14:16
 */
public abstract class LoadMoreActivity<CV extends View, M, V extends LoadMoreView<M>, P extends MvpPresenter<V>>
        extends MvpLceActivity<CV, M, V, P> implements LoadMoreView<M> {


}
