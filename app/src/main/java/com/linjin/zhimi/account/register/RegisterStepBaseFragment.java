package com.linjin.zhimi.account.register;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import com.cyou.app.mvp.BaseMvpFragment;
import com.linjin.zhimi.R;
import com.linjin.zhimi.utils.LogUtils;
import com.linjin.zhimi.widget.TopActionBar;
import com.mobsandgeeks.saripaar.ValidationError;
import com.mobsandgeeks.saripaar.Validator;

import java.util.List;

import butterknife.BindView;

/**
 * Description:
 * Copyright  : Copyright (c) 2015
 * Company    : 北京畅游天下网络科技有限公司
 * Author     : wangjia_bi
 * Date       : 2015/6/11 14:43
 */
@SuppressLint("ValidFragment")
public abstract class RegisterStepBaseFragment
        extends BaseMvpFragment<RegisterView, RegisterPresenter>
        implements Validator.ValidationListener,RegisterView {

    protected Validator validator;

    @BindView(R.id.topActionBar)
    TopActionBar topActionBar;

    protected static RegisterPresenter presenter;
    
    protected void initView() {
        validator = new Validator(this);
        validator.setValidationListener(this);

        topActionBar.setBackListener(new TopActionBar.BackListener() {
            @Override
            public void onBack() {
                presenter.back();

            }
        });
        topActionBar.setActionListener(new TopActionBar.ActionListener() {
            @Override
            public void onAction() {
                validator.validate();
            }
        });
        Log.i("code", " initView: " + getClass().getName());
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
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
    public RegisterPresenter getPresenter() {
        createPresenter().attachView(getMvpView());
        return presenter;
    }

    public Context getContext() {
        return getActivity();
    }

    @Override
    public abstract void onValidationSucceeded();

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

    @Override
    public void showRegister1() {
        start(RegisterStep1Fragment.newInstance());
    }

    @Override
    public void showRegister2() {
        start(RegisterStep2Fragment.newInstance());
    }

    @Override
    public void showRegister3() {
        start(RegisterStep3Fragment.newInstance());
    }
    
    @Override
    public void showRegister4() { 
        start(RegisterStep4Fragment.newInstance());
    }

    @Override
    public void finish() {

    }

    @Override
    public void showTip(String message) {

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public RegisterPresenter createPresenter() {
        if(presenter == null){
            presenter = new RegisterPresenter();
        }
        return presenter;
    }

}
