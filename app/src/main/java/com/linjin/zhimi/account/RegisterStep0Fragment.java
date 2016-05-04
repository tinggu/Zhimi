package com.linjin.zhimi.account;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.MotionEvent;
import android.view.View;

import com.cyou.quick.mvp.MvpView;
import com.cyou.ui.ClearableEditText;
import com.tinggu.common.utils.KeyboardUtils;
import com.tinggu.common.utils.LogUtils;
import com.tinggu.common.utils.TrackUtils;
import com.linjin.zhimi.R;
import com.linjin.zhimi.base.BaseMvpFragment;
import com.linjin.zhimi.widget.TopActionBar;
import com.mobsandgeeks.saripaar.ValidationError;
import com.mobsandgeeks.saripaar.Validator;
import com.mobsandgeeks.saripaar.annotation.Length;
import com.mobsandgeeks.saripaar.annotation.NotEmpty;
import com.mobsandgeeks.saripaar.annotation.Order;
import com.mobsandgeeks.saripaar.annotation.Password;

import java.util.List;

import butterknife.Bind;

/**
 * Description:
 * Copyright  : Copyright (c) 2015
 * Company    : 北京畅游天下网络科技有限公司
 * Author     : wangjia_bi
 * Date       : 2015/6/11 14:43
 */
@SuppressLint("ValidFragment")
public class RegisterStep0Fragment extends BaseMvpFragment<RegisterView, RegisterPresenter>
        implements MvpView, Validator.ValidationListener {

    private static final int RETRY_INTERVAL = 59;
    Validator validator;

    @NotEmpty(messageResId = R.string.login_error_phonenum_empty, sequence = 0)
    @Length(min = 11, max = 11, messageResId = R.string.register_error_phone_invalid, sequence = 1)
    @Order(0)
    @Bind(R.id.ev_phonenum)
    ClearableEditText evPhonenum;

    @NotEmpty(messageResId = R.string.login_error_password_empty)
    @Password(messageResId = R.string.register_error_password_invalid)
    @Order(1)
    @Bind(R.id.ev_password)
    ClearableEditText evPassword;


    @Bind(R.id.topActionBar)
    TopActionBar topActionBar;

    private RegisterPresenter presenter;



    public RegisterStep0Fragment(RegisterPresenter presenter) {
        this.presenter = presenter;
    }

    private void initView() {
//        topActionBar.setTitle("登录知觅");
        topActionBar.setBackListener(new TopActionBar.BackListener() {
            @Override
            public void onBack() {
                KeyboardUtils.callBackKeyClick();
            }
        });
        topActionBar.setActionListener(new TopActionBar.ActionListener() {
            @Override
            public void onAction() {
                presenter.nextStep(1);
            }
        });
        validator = new Validator(this);
        validator.setValidationListener(this);

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }


    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_register_step0;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //屏蔽事件透传
        view.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return true;
            }
        });

        initView();
    }

    @Override
    public RegisterPresenter createPresenter() {
        return presenter;
    }

    public Context getContext() {
        return getActivity();
    }

    @Override
    public void onValidationSucceeded() {
//        showLoading();
        String mobileNum = evPhonenum.getText().toString();
        String password = evPassword.getText().toString();

        TrackUtils.getInstance().onEvent("Register_rp_register");
//        String inviteCode = evInvitationMobile.getText() == null ? "" : evInvitationMobile.getText().toString();
    }

    @Override
    public void onValidationFailed(List<ValidationError> errors) {
        for (ValidationError error : errors) {
//            View view = error.getView();
//            if (view instanceof EditText) {
//                view.clearAnimation();
//                Animation shake = AnimationUtils.loadAnimation(getActivity(), R.anim.shake);
//                view.startAnimation(shake);
//            }
            String message = error.getCollatedErrorMessage(getActivity());
            LogUtils.e("register", "register error == " + message);
            toast(message.split("\n")[0]);
            return;
        }

    }

}
