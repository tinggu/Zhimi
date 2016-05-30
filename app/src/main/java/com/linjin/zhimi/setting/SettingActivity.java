package com.linjin.zhimi.setting;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;

import com.cyou.quick.mvp.MvpBasePresenter;
import com.linjin.zhimi.R;
import com.linjin.zhimi.base.BaseMvpActivity;

public class SettingActivity extends BaseMvpActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        showStting();
    }

    void showStting() {
        SettingFragment settingFragment = new SettingFragment();
        FragmentTransaction trasection = getSupportFragmentManager().beginTransaction();
        trasection.replace(R.id.fragmentContainer, settingFragment);
//        trasection.addToBackStack("edit");
        trasection.commit();
    }


    @Override
    public MvpBasePresenter createPresenter() {
        return new MvpBasePresenter();
    }


}