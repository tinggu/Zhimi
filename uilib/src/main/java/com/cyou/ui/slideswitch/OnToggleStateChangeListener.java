package com.cyou.ui.slideswitch;

/**
 * Description: 开关状态变化监听事件
 * Copyright  : Copyright (c) 2015
 * Company    : 北京畅游天下网络科技有限公司
 * Author     : liujianguang
 * Date       : 2016/3/3
 **/

public interface OnToggleStateChangeListener {
    /**
     * 开关状态变化，回调方法
     *
     * @param state 当前开关状态
     */
    void onToggleStateChange(boolean state);
}
