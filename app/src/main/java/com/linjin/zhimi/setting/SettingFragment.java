package com.linjin.zhimi.setting;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import com.cyou.quick.mvp.MvpBasePresenter;
import com.cyou.ui.slideswitch.OnToggleStateChangeListener;
import com.cyou.ui.slideswitch.SlideSwitch;
import com.linjin.zhimi.R;
import com.linjin.zhimi.base.BaseMvpFragment;
import com.linjin.zhimi.widget.TopActionBar;
import com.tinggu.common.utils.KeyboardUtils;

import butterknife.BindView;
import butterknife.ButterKnife;


public class SettingFragment extends BaseMvpFragment {

    @BindView(R.id.topActionBar)
    TopActionBar topActionBar;
    @BindView(R.id.item_account)
    LinearLayout itemAccount;
    @BindView(R.id.item_black_list)
    LinearLayout itemBlackList;
    @BindView(R.id.switch_message)
    SlideSwitch switchMessage;
    @BindView(R.id.btn_login)
    Button btnLogin;


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

     
}