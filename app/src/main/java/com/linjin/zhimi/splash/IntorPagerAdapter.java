package com.linjin.zhimi.splash;

import android.view.View;

import com.cyou.common.base.adapter.AbstractViewPagerAdapter;

import java.util.List;

/**
 * Description:
 * Copyright  : Copyright (c) 2015
 * Company    : 北京畅游天下网络科技有限公司
 * Author     : wangjia_bi
 * Date       : 2015/9/6 17:28
 */

public class IntorPagerAdapter extends AbstractViewPagerAdapter<View> {

//    private Context context;

    public IntorPagerAdapter(List<View> data) {
        super(data);
    }

    @Override
    public View newView(int position) {
        return mData.get(position);
    }
}
