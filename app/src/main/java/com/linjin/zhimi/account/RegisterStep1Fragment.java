package com.linjin.zhimi.account;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.cyou.quick.QuickApplication;
import com.linjin.zhimi.R;
import com.mobsandgeeks.saripaar.Validator;
import com.mobsandgeeks.saripaar.annotation.NotEmpty;
import com.tinggu.common.utils.NetWorkUtils;
import com.tinggu.common.utils.TrackUtils;

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
public class RegisterStep1Fragment extends RegisterStepBaseFragment {

    private static final int RETRY_INTERVAL = 60;


    @NotEmpty(messageResId = R.string.validation_code_null)
    @Bind(R.id.ev_validation_code)
    EditText evValidationCode;

    @Bind(R.id.tv_tip)
    TextView tvTip;

    @Bind(R.id.tv_get_code)
    TextView tvGetCode;

    private int time = RETRY_INTERVAL;

    private boolean isFinish;

    public RegisterStep1Fragment(RegisterPresenter presenter) {
        super(presenter);
    }

    @Override
    protected void initView() {
        super.initView();
        tvTip.setText("短信验证码已发送到 " + presenter.getPhone());
        onClick(tvGetCode);
        isFinish = false;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }


    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_register_step1;
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

    @OnClick({R.id.tv_get_code})
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.tv_get_code) {

            if (!NetWorkUtils.isNetConnected(QuickApplication.getInstance())) {
                toast(getContext().getString(R.string.no_network_connection));
            } else {
                countDown();
                presenter.getVerificationCode();
            }
        }
    }


    public Context getContext() {
        return getActivity();
    }


    @Override
    public void onValidationSucceeded() {
//        showLoading(); 
        String code = evValidationCode.getText().toString();
//        TrackUtils.getInstance().onEvent("Register_rp_register");
        presenter.checkCode(code);
//        getPresenter().doRegister(new AuthCredentials(mobileNum, password, code));
//        String inviteCode = evInvitationMobile.getText() == null ? "" : evInvitationMobile.getText().toString();
    }


}
