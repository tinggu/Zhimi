package com.linjin.zhimi.base.adapter;

import android.app.Activity;

import com.cyou.zhimi.model.im.Message;

import java.util.List;

/**
 * Description:
 * Copyright  : Copyright (c) 2015
 * Company    : 北京畅游天下网络科技有限公司
 * Author     : wangjia_bi
 * Date       : 2016/2/2 11:55
 */
public class MessageAdapter extends BaseDelegateAdapter<Message> {

    public MessageAdapter(Activity activity, List<Message> items) {
        super(activity, items);
    }

    public void setupDelegates() {

    }


}
