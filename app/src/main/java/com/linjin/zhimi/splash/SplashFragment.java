package com.linjin.zhimi.splash;

import com.cyou.quick.QuickFragment;
import com.linjin.zhimi.R;
import com.cyou.common.utils.TrackUtils;


/**
 * Description:
 * Copyright  : Copyright (c) 2015
 * Company    : 北京畅游天下网络科技有限公司
 * Author     : wangjia_bi
 * Date       : 2015/9/7 11:36
 */
public class SplashFragment extends QuickFragment {
    TrackUtils mTrackUtils = TrackUtils.getInstance();

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_splash;
    }

    @Override
    public void onResume() {
        super.onResume();
        mTrackUtils.onPageStart(this.getClass().getName());
    }

    @Override
    public void onPause() {
        super.onPause();
        mTrackUtils.onPageEnd(this.getClass().getName());
    }
}
