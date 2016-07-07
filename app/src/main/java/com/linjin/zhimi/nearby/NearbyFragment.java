package com.linjin.zhimi.nearby;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.cyou.app.mvp.BaseMvpFragment;
import com.cyou.quick.mvp.MvpBasePresenter;
import com.cyou.ui.slideswitch.OnToggleStateChangeListener;
import com.cyou.ui.slideswitch.SlideSwitch;
import com.linjin.zhimi.R;
import com.linjin.zhimi.utils.IntentStarter;
import com.linjin.zhimi.utils.KeyboardUtils;
import com.linjin.zhimi.widget.TopActionBar;

import butterknife.BindView;
import butterknife.OnClick;


public class NearbyFragment extends BaseMvpFragment {

    @BindView(R.id.topActionBar)
    TopActionBar topActionBar;
    

    @BindView(R.id.switch_message)
    SlideSwitch switchMessage;


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView();
    }

    private void initView() {
        topActionBar.setTitle(R.string.text_setting);
        topActionBar.setBackListener(new TopActionBar.BackListener() {
            @Override
            public void onBack() {
                KeyboardUtils.callBackKeyClick();
            }
        });
        topActionBar.hideAction();
        switchMessage.setOnToggleStateChangeListener(new OnToggleStateChangeListener() {
            @Override
            public void onToggleStateChange(boolean state) {

            }
        });
    }

    @Override
    public MvpBasePresenter createPresenter() {
        return new MvpBasePresenter();
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_setting;
    }

    @OnClick({R.id.item_black_list, R.id.item_account, R.id.btn_logout})
    public void onItemOnclick(View view) {
        Intent intent;
        switch (view.getId()) {

            case R.id.item_black_list:
//                IntentStarter.showNoticeInfo(getActivity(), 147, 10);
//                break;
            case R.id.item_account:
               
                break;
            case R.id.btn_logout:
                IntentStarter.showLogin(null);
                break;
        }
    }

}
