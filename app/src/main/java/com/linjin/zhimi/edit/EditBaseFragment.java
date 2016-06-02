package com.linjin.zhimi.edit;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import com.linjin.zhimi.R;
import com.linjin.zhimi.account.register.RegisterPresenter;
import com.linjin.zhimi.account.register.RegisterView;
import com.linjin.zhimi.base.BaseMvpFragment;
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
public abstract class EditBaseFragment
        extends BaseMvpFragment<EditView, EditPresenter>
        implements Validator.ValidationListener {

    protected Validator validator;

    @BindView(R.id.topActionBar)
    TopActionBar topActionBar;

    protected EditPresenter presenter;


    public EditBaseFragment(EditPresenter presenter) {
        this.presenter = presenter;
    }

    protected void initView() {
        validator = new Validator(this);
        validator.setValidationListener(this);
        topActionBar.setActionText(R.string.text_save);

        topActionBar.setActionListener(new TopActionBar.ActionListener() {
            @Override
            public void onAction() {

            }
        });
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
    public EditPresenter createPresenter() {
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

}
