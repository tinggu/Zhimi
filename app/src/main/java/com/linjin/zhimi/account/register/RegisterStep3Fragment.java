package com.linjin.zhimi.account.register;

import android.annotation.SuppressLint;
import android.os.Bundle;

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
public class RegisterStep3Fragment extends RegisterStepBaseFragment {
    
    @NotEmpty(messageResId = R.string.login_error_title_empty, sequence = 0)
    @Length(max = 15, sequence = 1)
    @Order(0)
    @BindView(R.id.ev_title)
    ClearableEditText evTitle;

    public static RegisterStep3Fragment newInstance() {
        Bundle args = new Bundle();
        RegisterStep3Fragment fragment = new RegisterStep3Fragment();
        fragment.setArguments(args);
        return fragment;
    }
    

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_register_step3;
    }


    @Override
    public void onValidationSucceeded() {
        presenter.setTitle(evTitle.getText().toString());
        presenter.nextStep();
//        TrackUtils.getInstance().onTrackEvent("Register_rp_register");
    }


}
