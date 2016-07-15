package com.linjin.zhimi.account.register;

import android.annotation.SuppressLint;
import android.os.Bundle;

import com.cyou.ui.ClearableEditText;
import com.linjin.zhimi.R;
import com.mobsandgeeks.saripaar.annotation.Length;
import com.mobsandgeeks.saripaar.annotation.NotEmpty;
import com.mobsandgeeks.saripaar.annotation.Order;
import com.mobsandgeeks.saripaar.annotation.Password;

import butterknife.BindView;

/**
 * Description:
 * Copyright  : Copyright (c) 2015
 * Company    : 北京畅游天下网络科技有限公司
 * Author     : wangjia_bi
 * Date       : 2015/6/11 14:43
 */
public class RegisterStep0Fragment extends RegisterStepBaseFragment {

    public static RegisterStep0Fragment newInstance() {
        Bundle args = new Bundle();
        RegisterStep0Fragment fragment = new RegisterStep0Fragment();
        fragment.setArguments(args);
        return fragment;
    }
    
    @NotEmpty(messageResId = R.string.login_error_phonenum_empty, sequence = 0)
    @Length(min = 11, max = 11, messageResId = R.string.register_error_phone_invalid, sequence = 1)
    @Order(0)
    @BindView(R.id.ev_phonenum)
    ClearableEditText evPhonenum;

    @NotEmpty(messageResId = R.string.login_error_password_empty)
    @Password(messageResId = R.string.register_error_password_invalid)
//    @Length(min = 6, max = 18, messageResId = R.string.register_error_password_invalid, sequence = 0)
    @Order(1)
    @BindView(R.id.ev_password)
    ClearableEditText evPassword;
    
    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_register_step0;
    }


    @Override
    public void onValidationSucceeded() {
        String phone = evPhonenum.getText().toString();
        String password = evPassword.getText().toString();
        registerPresenter.setPhoneAndPassword(phone, password);
        registerPresenter.nextStep();
//        TrackUtils.getInstance().onTrackEvent("Register_rp_register");
    }


    
}
