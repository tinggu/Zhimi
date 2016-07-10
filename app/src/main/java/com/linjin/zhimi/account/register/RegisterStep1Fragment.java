package com.linjin.zhimi.account.register;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.cyou.quick.QuickApplication;
import com.linjin.zhimi.R;
import com.linjin.zhimi.utils.NetWorkUtils;
import com.mobsandgeeks.saripaar.annotation.NotEmpty;

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
public class RegisterStep1Fragment extends RegisterStepBaseFragment {

    private static final int RETRY_INTERVAL = 60;


    @NotEmpty(messageResId = R.string.validation_code_null)
    @BindView(R.id.ev_validation_code)
    EditText evValidationCode;

    @BindView(R.id.tv_tip)
    TextView tvTip;

    @BindView(R.id.tv_get_code)
    TextView tvGetCode;

    private int time;
    private boolean isFinsh;

    public static RegisterStep1Fragment newInstance() {
        Bundle args = new Bundle();
        RegisterStep1Fragment fragment = new RegisterStep1Fragment();
        fragment.setArguments(args);
        return fragment;
    }
    
    @Override
    protected void initView() {
        super.initView();
        
        tvTip.setText("短信验证码已发送到 " + presenter.getPhone());
        onClick(tvGetCode);
    }

    private void reset() {
        tvGetCode.setText(R.string.register_get_validation_code);
        tvGetCode.setEnabled(true);

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
        time = RETRY_INTERVAL;
        isFinsh = false;
        final Handler handler = new Handler();

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                if (isFinsh) {
                    return;
                }
                time--;
                if (time == 0) {
                    tvGetCode.setText(R.string.reget_validation_code);
                    tvGetCode.setEnabled(true);
                } else {
                    if (tvGetCode == null) {
                        return;
                    }
                    String countDown = getContext().getString(R.string.validation_code_count_down, time);
                    tvGetCode.setText(countDown);
                    handler.postDelayed(this, 1000);

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
                toast(getContext().getString(R.string.net_error));
            } else {
                presenter.getVerificationCode();
                countDown();
            }
        }
    }


    public Context getContext() {
        return getActivity();
    }


    @Override
    public void onValidationSucceeded() {
        String code = evValidationCode.getText().toString();
//        presenter.checkCode(code);
        isFinsh = true;
        reset();
        presenter.nextStep();
    }


}
