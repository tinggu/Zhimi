package com.linjin.zhimi.account.register;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.cyou.quick.QuickApplication;
import com.cyou.ui.ClearableEditText;
import com.linjin.zhimi.R;
import com.linjin.zhimi.account.login.LoginActivity;
import com.linjin.zhimi.base.BaseMvpFragment;
import com.linjin.zhimi.config.BandConfig;
import com.linjin.zhimi.model.account.AuthCredentials;
import com.linjin.zhimi.utils.IntentStarter;
import com.linjin.zhimi.widget.TopActionBar;
import com.linjin.zhimi.widget.TopSnackBar;
import com.mobsandgeeks.saripaar.ValidationError;
import com.mobsandgeeks.saripaar.Validator;
import com.mobsandgeeks.saripaar.annotation.ConfirmPassword;
import com.mobsandgeeks.saripaar.annotation.Length;
import com.mobsandgeeks.saripaar.annotation.NotEmpty;
import com.mobsandgeeks.saripaar.annotation.Order;
import com.mobsandgeeks.saripaar.annotation.Password;
import com.tinggu.common.utils.KeyboardUtils;
import com.tinggu.common.utils.LogUtils;
import com.tinggu.common.utils.NetWorkUtils;
import com.tinggu.common.utils.TrackUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Description:
 * Copyright  : Copyright (c) 2015
 * Company    : 北京畅游天下网络科技有限公司
 * Author     : wangjia_bi
 * Date       : 2015/6/11 14:43
 */
@SuppressLint("ValidFragment")
public class RegisterFragment
        extends BaseMvpFragment<RegisterView, RegisterPresenter>
        implements RegisterView, Validator.ValidationListener {

    private static final int RETRY_INTERVAL = 59;
    Validator validator;

    @NotEmpty(messageResId = R.string.login_error_phonenum_empty, sequence = 0)
    @Length(min = 11, max = 11, messageResId = R.string.register_error_phone_invalid, sequence = 1)
    @Order(0)
    @BindView(R.id.ev_phonenum)
    ClearableEditText evPhonenum;

    @NotEmpty(messageResId = R.string.login_error_password_empty)
    @Password(messageResId = R.string.register_error_password_invalid)
    @Order(1)
    @BindView(R.id.ev_password)
    ClearableEditText evPassword;

    @ConfirmPassword(messageResId = R.string.register_error_password_repeat_wrong)
    @Order(2)
    @BindView(R.id.ev_password_repeat)
    ClearableEditText evPasswordRepeat;

    @Order(3)
    @NotEmpty(messageResId = R.string.validation_code_null)
    @BindView(R.id.ev_validation_code)
    EditText evValidationCode;

    @BindView(R.id.tv_get_code)
    TextView tvGetCode;

    @BindView(R.id.topSnackBar)
    TopSnackBar topSnackBar;

    @BindView(R.id.topActionBar)
    TopActionBar topActionBar;

    private int time = RETRY_INTERVAL;

    private LoginActivity loginActivity;

    private boolean isFinish;
    private boolean isGetcode;


    String phone;
    String password;

    public RegisterFragment(LoginActivity loginActivity, String phone, String password) {
        this.loginActivity = loginActivity;
        this.phone = phone;
        this.password = password;
    }

    private void initView() {
        topActionBar.setTitle(R.string.action_register);
        topActionBar.setBackListener(new TopActionBar.BackListener() {
            @Override
            public void onBack() {
                KeyboardUtils.callBackKeyClick();
            }
        });
        validator = new Validator(this);
        validator.setValidationListener(this);
        evPhonenum.setText(phone);
        evPassword.setText(password);
        evPhonenum.post(new Runnable() {
            @Override
            public void run() {
                evPhonenum.requestFocus();
                evPhonenum.setSelection(phone.length());
            }
        });
        if (phone != null && phone.length() == 11) {
//            tvGetCode.callOnClick();
            onClick(tvGetCode);
        }
        isFinish = false;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        isGetcode = false;
    }
    

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_register;
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
        return new RegisterPresenter(loginActivity);
    }

    /**
     * 倒数计时
     */
    private void countDown() {
        tvGetCode.setEnabled(false);
        final Handler handler = new Handler();

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                time--;
                if (time == 0) {
                    tvGetCode.setText(R.string.reget_validation_code);
                    tvGetCode.setEnabled(true);
                    time = RETRY_INTERVAL;
                } else {
                    Context context = getContext();
                    if (context == null) {
                        return;
                    }
                    String countDown = getContext().getString(R.string.validation_code_count_down, time);
                    tvGetCode.setText(countDown);
                    if (!isFinish) {
                        handler.postDelayed(this, 1000);
                    }
                }

            }
        };

        handler.post(runnable);
    }

    @OnClick({R.id.tv_get_code, R.id.btn_register, R.id.tv_protocal})
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.btn_register) {
            if (!NetWorkUtils.isNetConnected(getActivity())) {
                showTip(getResources().getString(R.string.net_error));
                return;
            }
//            doInvitation();
            validator.validate();
        } else if (id == R.id.tv_protocal) {
            //打开使用协议
            String url = BandConfig.h5_use_protocol;
            String title = getString(R.string.use_protocol);
            IntentStarter.showProtocal(loginActivity, url, title);
            TrackUtils.getInstance().onTrackEvent("Register_declaration");

        } else if (id == R.id.tv_get_code) {
            String mobileNum = evPhonenum.getText().toString();
            if (TextUtils.isEmpty(mobileNum)) {
                String message = getContext().getString(R.string.login_error_phonenum_empty);
                showTip(message);
            } else if (mobileNum.length() == 11) {
                if (NetWorkUtils.isNetConnected(QuickApplication.getInstance())) {
                    countDown();
                    presenter.doGetCheckCode(mobileNum);
                    if (isGetcode) {
                        TrackUtils.getInstance().onTrackEvent("Register_rp_nextsend");
                    } else {
                        /*AppProvide.getInstance().onTrackEvent("Register_rp_verification");*/
                        isGetcode = true;
                    }
                } else {
                    showTip(getContext().getString(R.string.net_error));
                }

            } else {
                String message = getContext().getString(R.string.register_error_phone_invalid);
                showTip(message);
            }
        }

    }

    public Context getContext() {
        return getActivity();
    }
    
    @Override
    public void showTip(String message) {
        topSnackBar.showOnceTip(message);
        hideLoading();
    }

    @Override
    public void showLoading() {
        loginActivity.dialogUtils.showLoading(getActivity(), "注册中，请稍后...");
    }

    @Override
    public void hideLoading() {
        loginActivity.dialogUtils.hideLoading();
    }

    @Override
    public void finish() {
        hideLoading();
        isFinish = true;
    }

    @Override
    public void onValidationSucceeded() {
//        showLoading();
        String mobileNum = evPhonenum.getText().toString();
        String password = evPassword.getText().toString();
        String code = evValidationCode.getText().toString();
        TrackUtils.getInstance().onTrackEvent("Register_rp_register");
//        TalkingDataAppCpa.onRegister(AppProvide.dataCenter().getUserID());
        getPresenter().doRegister(new AuthCredentials(mobileNum, password, code));
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
            showTip(message.split("\n")[0]);
            return;
        }

    }

}
