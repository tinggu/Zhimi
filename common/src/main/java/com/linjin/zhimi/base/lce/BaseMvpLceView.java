package com.linjin.zhimi.base.lce;

import com.cyou.quick.mvp.lce.MvpLceView;

/**
 * Description:
 * Copyright  : Copyright (c) 2015
 * Company    : 北京畅游天下网络科技有限公司
 * Author     : liujianguang
 * Date       : 2016/1/14
 **/

public interface BaseMvpLceView<M> extends MvpLceView<M> {

    void refresh();

    void showTip(String message);

}
