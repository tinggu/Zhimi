package com.linjin.zhimi.account;

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

import com.afollestad.materialdialogs.MaterialDialog;
import com.cyou.ui.ClearableEditText;
import com.linjin.zhimi.model.account.AuthCredentials;
import com.linjin.zhimi.R;
import com.linjin.zhimi.base.BaseMvpFragment;
import com.linjin.zhimi.widget.TopActionBar;
import com.mobsandgeeks.saripaar.ValidationError;
import com.mobsandgeeks.saripaar.Validator;
import com.mobsandgeeks.saripaar.annotation.ConfirmPassword;
import com.mobsandgeeks.saripaar.annotation.Length;
import com.mobsandgeeks.saripaar.annotation.NotEmpty;
import com.mobsandgeeks.saripaar.annotation.Order;
import com.mobsandgeeks.saripaar.annotation.Password;
import com.tinggu.common.utils.KeyboardUtils;
import com.tinggu.common.utils.LogUtils;
import com.tinggu.common.utils.TrackUtils;

import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Description:
 * Copyright  : Copyright (c) 2015
 * Company    : 北京畅游天下网络科技有限公司
 * Author     : wangjia_bi
 * Date       : 2015/6/11 14:43
 */
@SuppressLint("ValidFragment")
public class FindPWFragment
        extends BaseMvpFragment<FindPWView, FindPWPresenter>
        implements FindPWView, Validator.ValidationListener {

    private static final int RETRY_INTERVAL = 59;
    Validator validator;
    MaterialDialog loading;

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

    @ConfirmPassword(messageResId = R.string.register_error_password_repeat_wrong)
    @Order(2)
    @Bind(R.id.ev_password_repeat)
    ClearableEditText evPasswordRepeat;

    @Order(3)
    @NotEmpty(messageResId = R.string.validation_code_null)
    @Bind(R.id.ev_validation_code)
    EditText evValidationCode;

    @Bind(R.id.tv_get_code)
    TextView tvGetCode;

    @Bind(R.id.topActionBar)
    TopActionBar topActionBar;

    private int time = RETRY_INTERVAL;


    private boolean isFinish;

    String phone;

    public FindPWFragment(String phone) {

        this.phone = phone;
    }

    private void initView() {
        topActionBar.setTitle("密码找回");
        topActionBar.setBackListener(new TopActionBar.BackListener() {
            @Override
            public void onBack() { 
                KeyboardUtils.callBackKeyClick();
            }
        });
        topActionBar.hideAction();
        validator = new Validator(this);
        validator.setValidationListener(this);
        evPhonenum.setText(phone);
//        evPhonenum.post(new Runnable() {
//            @Override
//            public void run() {
//                evPhonenum.requestFocus();
//                evPhonenum.setSelection(phone.length());
//            }
//        });
//        if (phone != null && phone.length() == 11) {
//            onClick(tvGetCode);
//        }
        isFinish = false;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Override
    public void onResume() {
        super.onResume();

        TrackUtils.getInstance().onPageStart("FindPWFragment");
    }

    @Override
    public void onPause() {
        super.onPause();

        TrackUtils.getInstance().onPageEnd("FindPWFragment");
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_find_password;
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
    public FindPWPresenter createPresenter() {
        return new FindPWPresenter();
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

    @OnClick({R.id.tv_get_code, R.id.btn_reset_password})
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.btn_reset_password) {
            TrackUtils.getInstance().onTrackEvent("Register_fp1_next");
            validator.validate();
        } else if (id == R.id.tv_get_code) {
            String mobileNum = evPhonenum.getText().toString();
            if (TextUtils.isEmpty(mobileNum)) {
                String message = getContext().getString(R.string.login_error_phonenum_empty);
                showTip(message);
            } else if (mobileNum.length() == 11) {
                countDown();
                presenter.doGetCheckCode(mobileNum);
                /*AppProvide.getInstance().onTrackEvent("Register_fp_verification");*/
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
        toast(message);
        hideLoading();
    }

    @Override
    public void showLoading() {
        if (loading == null) {
            loading = new MaterialDialog.Builder(getActivity())
//                    .title(R.string.progress_dialog)
//                    .content(R.string.please_wait)
                    .content(getActivity().getString(R.string.findPW_loading))
                    .progress(true, 0).build();
        }
        loading.show();
    }

    @Override
    public void hideLoading() {
        if (loading != null) {
            loading.hide();
        }
    }

    @Override
    public void finish() {
        isFinish = true;
    }

    @Override
    public void onValidationSucceeded() {
//        showLoading();
        String mobileNum = evPhonenum.getText().toString();
        String password = evPassword.getText().toString();
        String inputCode = evValidationCode.getText().toString();
        getPresenter().doRegister(new AuthCredentials(mobileNum, password, inputCode));

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
