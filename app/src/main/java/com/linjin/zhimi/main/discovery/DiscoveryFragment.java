package com.linjin.zhimi.main.discovery;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.cyou.quick.mvp.MvpFragment;
import com.cyou.quick.mvp.MvpPresenter;
import com.linjin.zhimi.R;
import com.linjin.zhimi.utils.IntentStarter;
import com.linjin.zhimi.widget.TopActionBar;

import butterknife.BindView;


public class DiscoveryFragment extends MvpFragment {

    @BindView(R.id.topActionBar)
    TopActionBar topActionBar;
 

//    LatestPresenter

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView();
    }

    private void initView(){
        topActionBar.setTitle(R.string.radio_dock_2);
        topActionBar.hideBack();
        topActionBar.hideAction();
        topActionBar.setActionImageResource(R.mipmap.topic_sidebar);
        
    }
    
    @Override
    public MvpPresenter createPresenter() {
        return new DiscoveryPresenter();
    }

    
    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_discovery;
    }
}
