package com.linjin.zhimi.main.discovery;

import com.cyou.quick.mvp.MvpFragment;
import com.cyou.quick.mvp.MvpPresenter;
import com.linjin.zhimi.R;


public class DiscoveryFragment extends MvpFragment {
    
    
    @Override
    public MvpPresenter createPresenter() {
        return new DiscoveryPresenter();
    }

    
    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_discovery;
    }
}
