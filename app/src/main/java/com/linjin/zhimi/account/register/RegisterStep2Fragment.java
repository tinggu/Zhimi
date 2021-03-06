package com.linjin.zhimi.account.register;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.RadioGroup;

import com.cyou.ui.ClearableEditText;
import com.linjin.zhimi.R;
import com.mobsandgeeks.saripaar.annotation.Length;
import com.mobsandgeeks.saripaar.annotation.NotEmpty;
import com.mobsandgeeks.saripaar.annotation.Order;

import butterknife.BindView;

/**
 * Description:
 * Copyright  : Copyright (c) 2015
 * Company    : 北京畅游天下网络科技有限公司
 * Author     : wangjia_bi
 * Date       : 2015/6/11 14:43
 */
@SuppressLint("ValidFragment")
public class RegisterStep2Fragment extends RegisterStepBaseFragment {

    public static RegisterStep2Fragment newInstance() {
        Bundle args = new Bundle();
        RegisterStep2Fragment fragment = new RegisterStep2Fragment();
        fragment.setArguments(args);
        return fragment;
    }
    
    @NotEmpty(messageResId = R.string.login_error_name_empty, sequence = 0)
    @Length(max = 10, sequence = 1)
    @Order(0)
    @BindView(R.id.ev_name)
    ClearableEditText evName;

    @BindView(R.id.rg_sex)
    RadioGroup radiogroup;
    
    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_register_step2;
    }

    @Override
    protected void initView() {
        super.initView();
        radiogroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if(checkedId == R.id.rb_male){
                    registerPresenter.setSex(1);
                }else  if (checkedId == R.id.rb_female){
                    registerPresenter.setSex(0);
                }
            }
        });
    }


    @Override
    public void onValidationSucceeded() {
        registerPresenter.setName(evName.getText().toString());
        registerPresenter.nextStep();
//        TrackUtils.getInstance().onTrackEvent("Register_rp_register");
    }


}
