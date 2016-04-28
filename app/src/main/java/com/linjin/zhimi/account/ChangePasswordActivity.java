package com.linjin.zhimi.account;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.afollestad.materialdialogs.MaterialDialog; 
import com.cyou.band.base.BaseMvpActivity;
import com.cyou.common.utils.LogUtils;
import com.linjin.zhimi.R;
import com.mobsandgeeks.saripaar.ValidationError;
import com.mobsandgeeks.saripaar.Validator;
import com.mobsandgeeks.saripaar.annotation.NotEmpty;
import com.mobsandgeeks.saripaar.annotation.Order;
import com.mobsandgeeks.saripaar.annotation.Password;

import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by nidingya on 2015/8/5.
 */
public class ChangePasswordActivity 
        extends BaseMvpActivity<ChangePasswordView, ChangePasswordPresenter> 
        implements ChangePasswordView, Validator.ValidationListener {


    MaterialDialog loading;

    @NotEmpty(messageResId = R.string.login_error_password_empty)
    @Password(min = 6, messageResId = R.string.register_error_password_invalid, sequence = 2)
    @Order(1)
    @Bind(R.id.enter_old_password)
    EditText enter_old_password;

    @NotEmpty(messageResId = R.string.login_error_password_empty)
    @Password(min = 6, messageResId = R.string.register_error_password_invalid, sequence = 2)
    @Order(2)
    @Bind(R.id.enter_new_password)
    EditText enter_new_password;


    @Bind(R.id.tv_password_state)
    TextView tv_password_state;

    @NotEmpty(messageResId = R.string.login_error_password_empty)
    @Password(min = 6, messageResId = R.string.register_error_password_invalid, sequence = 2)
    @Order(3)
    @Bind(R.id.repeat_new_password)
    EditText repeat_new_password;
    @Bind(R.id.iv_back)
    ImageView iv_back;

    Validator validator;
    ChangePasswordPresenter changePasswordPresenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_changepassword);
        initView();
    }
    
    @Override
    public ChangePasswordPresenter createPresenter() {
        changePasswordPresenter = new ChangePasswordPresenter();
        ;
        return changePasswordPresenter;
    }

    protected void initView() {
        tv_password_state.setVisibility(View.INVISIBLE);
        validator = new Validator(this);
        validator.setValidationListener(this);

    }

    @Override
    public void showLoading() {
        if (loading == null) {
            loading = new MaterialDialog.Builder(this)
//                    .title(R.string.progress_dialog)
//                    .content(R.string.please_wait)
                    .content("密码修改中，请稍后...")
                    .progress(true, 0).build();
        }
        loading.show();
    }

    @Override
    public void hideLoading() {
        LogUtils.e("changePassword","hideLoading");
        loading.hide();
    }

    @Override
    public void showError(int type, Throwable e) {
        String message = e.getMessage();
        repeat_new_password.setVisibility(View.VISIBLE);
        tv_password_state.setText(message);
    }

    String oldPassword;
    String newPassword;
    String newInputPassword;

    @OnClick({R.id.btn_change_password, R.id.iv_back})
    public void onClick(View v) {
        if (v.getId() == R.id.btn_change_password) {
            oldPassword = enter_old_password.getText().toString();
            newPassword = repeat_new_password.getText().toString();
            newInputPassword = enter_new_password.getText().toString();
            if (newPassword.equals(newInputPassword)) {

                validator.validate();
            } else {
                tv_password_state.setVisibility(View.VISIBLE);
                tv_password_state.setText(R.string.password_unmuch);
            }
        } else if (v.getId() == R.id.iv_back) {
            this.finish();
        }

    }

    @Override
    public void onValidationSucceeded() {

        showLoading();
        changePasswordPresenter.changePassword(oldPassword, newPassword);

    }

    @Override
    public void onValidationFailed(List<ValidationError> errors) {
        for (ValidationError error : errors) {
            String message = error.getCollatedErrorMessage(this);
            tv_password_state.setText(message);
            showError(ERROR_TYPE_OTHER, new Throwable(message.split("\n")[0]));
            tv_password_state.setVisibility(View.VISIBLE);
            return;

        }
    }


}
