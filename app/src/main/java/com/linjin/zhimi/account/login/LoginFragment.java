package com.linjin.zhimi.account.login;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.cyou.ui.ClearableEditText;
import com.linjin.zhimi.R;
import com.linjin.zhimi.base.BaseMvpFragment;
import com.linjin.zhimi.model.account.AuthCredentials;
import com.linjin.zhimi.widget.TopActionBar;
import com.mobsandgeeks.saripaar.ValidationError;
import com.mobsandgeeks.saripaar.Validator;
import com.mobsandgeeks.saripaar.annotation.Length;
import com.mobsandgeeks.saripaar.annotation.NotEmpty;
import com.mobsandgeeks.saripaar.annotation.Order;
import com.mobsandgeeks.saripaar.annotation.Password;
import com.linjin.zhimi.utils.KeyboardUtils;
import com.linjin.zhimi.utils.LogUtils;
import com.linjin.zhimi.utils.NetWorkUtils;
import com.linjin.zhimi.utils.TrackUtils;
import com.umeng.message.UmengRegistrar;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

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
    @Order(0)
    @BindView(R.id.ev_phonenum)
    ClearableEditText evPhonenum;

    @NotEmpty(messageResId = R.string.login_error_password_empty)
    @Password(min = 6, messageResId = R.string.register_error_password_invalid, sequence = 2)
    @Order(1)
    @BindView(R.id.ev_password)
    ClearableEditText evPassword;


    @BindView(R.id.topActionBar)
    TopActionBar topActionBar;

    private Validator validator;

    private LoginActivity loginActivity;

    public LoginFragment(LoginActivity loginActivity) {
        this.loginActivity = loginActivity;
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

    @OnClick({R.id.btn_login, R.id.tv_findpassword})
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.btn_login) {
            if (!NetWorkUtils.isNetConnected(getActivity())) {
                toast(R.string.net_error);
                return;
            }
            LogUtils.d("login", "click login");
            validator.validate();
        } else if (id == R.id.tv_findpassword) {
            loginActivity.showFindPassword(evPhonenum.getText().toString());
            TrackUtils.getInstance().onTrackEvent("Register_forgotpassword");
        }
    }


    @Override
    public void showTip(String message) {
        toast(message);
        hideLoading();
    }

    @Override
    public void showLoading() {
        loginActivity.dialogUtils.showLoading(getActivity(), "登录中，请稍后...");
    }

    @Override
    public void hideLoading() {
        loginActivity.dialogUtils.hideLoading();
    }


    @Override
    public void onValidationSucceeded() {
        LogUtils.d("login", "onValidationSucceeded ... ");
        showLoading();
        String name = evPhonenum.getText().toString();
        String pass = evPassword.getText().toString();
        String device_token = UmengRegistrar.getRegistrationId(getActivity());
        presenter.doLogin(new AuthCredentials(name, pass, device_token));
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

