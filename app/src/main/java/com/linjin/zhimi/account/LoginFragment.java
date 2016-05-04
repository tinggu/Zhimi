package com.linjin.zhimi.account;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.linjin.zhimi.base.BaseMvpFragment;
import com.cyou.zhimi.model.account.AuthCredentials;
import com.tinggu.common.utils.KeyboardUtils;
import com.tinggu.common.utils.LogUtils;
import com.tinggu.common.utils.NetWorkUtils;
import com.tinggu.common.utils.TrackUtils;
import com.cyou.ui.ClearableEditText;
import com.linjin.zhimi.R;
import com.mobsandgeeks.saripaar.ValidationError;
import com.mobsandgeeks.saripaar.Validator;
import com.mobsandgeeks.saripaar.annotation.Length;
import com.mobsandgeeks.saripaar.annotation.NotEmpty;
import com.mobsandgeeks.saripaar.annotation.Order;
import com.mobsandgeeks.saripaar.annotation.Password;

import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;
import com.linjin.zhimi.widget.TopActionBar;

/**
 * Description:
 * Copyright  : Copyright (c) 2015
 * Company    : 北京畅游天下网络科技有限公司
 * Author     : wangjia_bi
 * Date       : 2015/6/22 12:39
 */
@SuppressLint("ValidFragment")
public class LoginFragment
        extends BaseMvpFragment<LoginView, LoginPresenter>
        implements LoginView, Validator.ValidationListener {
    private static final String TAG = "LoginFragment";

    @NotEmpty(messageResId = R.string.login_error_phonenum_empty, sequence = 0)
    @Length(min = 11, max = 11, messageResId = R.string.register_error_phone_invalid, sequence = 1)
    @Order(1)
    @Bind(R.id.ev_phonenum)
    ClearableEditText evPhonenum;

    @NotEmpty(messageResId = R.string.login_error_password_empty)
    @Password(min = 6, messageResId = R.string.register_error_password_invalid, sequence = 2)
    @Order(2)
    @Bind(R.id.ev_password)
    ClearableEditText evPassword;
    
//    @Bind(R.id.btn_login)
//    Button btnLogin;
    
//    @Bind(R.id.topSnackBar)
//    TopSnackBar topSnackBar;
    
    @Bind(R.id.topActionBar)
    TopActionBar topActionBar;
    
    private Validator validator;

    private AccuntActivity accuntActivity;

    public LoginFragment(AccuntActivity accuntActivity) {
        this.accuntActivity = accuntActivity;
    }

    private void initView() {
        topActionBar.setTitle("登录知觅");
        topActionBar.hideAction();
        topActionBar.setBackListener(new TopActionBar.BackListener() {
            @Override
            public void onBack() {
                KeyboardUtils.callBackKeyClick();
            }
        });
        validator = new Validator(this);
        validator.setValidationListener(this);
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_login;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView();
    }
    
    @Override
    public LoginPresenter createPresenter() {
        return new LoginPresenter();
    }

    @OnClick({R.id.btn_login,  R.id.tv_findpassword})
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.btn_login) {
            if (!NetWorkUtils.isNetConnected(getActivity())) {
                toast(R.string.net_error); 
                return;
            }
            LogUtils.d("login", "click login");
            validator.validate();
//        } else if (id == R.id.tv_register) {
//            accuntActivity.showRegister(evPhonenum.getText().toString(), evPassword.getText().toString());
//            TrackUtils.getInstance().onEvent("Register_registration");
        
        } else if (id == R.id.tv_findpassword) {
            accuntActivity.showFindPassword(evPhonenum.getText().toString());
            TrackUtils.getInstance().onEvent("Register_forgotpassword");
        }
    }

//    @OnTextChanged({R.id.ev_password, R.id.ev_phonenum})
//    public void onTextChanged() {
//        hideError();
//    }
    
    @Override
    public void showTip(String message) {
        LogUtils.i(TAG, "show tip " + message);
//        topSnackBar.showOnceTip(message);
        toast(message);
        hideLoading();
    }

    @Override
    public void showLoading() {
        accuntActivity.dialogUtils.showLoading(getActivity(), "登录中，请稍后...");
//        btnLogin.setText(R.string.action_login_loading);
    }

    @Override
    public void hideLoading() {
        accuntActivity.dialogUtils.hideLoading();
//        btnLogin.setText(R.string.action_login);
    }
    

    @Override
    public void onValidationSucceeded() {
        LogUtils.d("login", "onValidationSucceeded ... ");
        showLoading();
        String name = evPhonenum.getText().toString();
        String pass = evPassword.getText().toString();
        presenter.doLogin(new AuthCredentials(name, pass));
    }

    @Override
    public void onValidationFailed(List<ValidationError> errors) {
        for (ValidationError error : errors) {
            View view = error.getView();

            int id = view.getId();
            if (id == R.id.ev_phonenum) {
                
            }
//            if (view instanceof EditText) {
//                view.clearAnimation();
//                Animation shake = AnimationUtils.loadAnimation(getActivity(), R.anim.shake);
//                view.startAnimation(shake);
//            }
            String message = error.getCollatedErrorMessage(getActivity());
            LogUtils.e(TAG, "login error == " + message);
            showTip(message.split("\n")[0]);
            return;
        }
    }
}

