package com.linjin.zhimi.account;

import android.annotation.SuppressLint;
import android.widget.RadioGroup;

import com.cyou.ui.ClearableEditText;
import com.linjin.zhimi.R;
import com.mobsandgeeks.saripaar.Validator;
import com.mobsandgeeks.saripaar.annotation.Length;
import com.mobsandgeeks.saripaar.annotation.NotEmpty;
import com.mobsandgeeks.saripaar.annotation.Order;

import butterknife.Bind;

/**
 * Description:
 * Copyright  : Copyright (c) 2015
 * Company    : 北京畅游天下网络科技有限公司
 * Author     : wangjia_bi
 * Date       : 2015/6/11 14:43
 */
@SuppressLint("ValidFragment")
public class RegisterStep2Fragment extends RegisterStepBaseFragment {
  

    @NotEmpty(messageResId = R.string.login_error_name_empty, sequence = 0)
    @Length(max = 10, sequence = 1)
    @Order(0)
    @Bind(R.id.ev_name)
    ClearableEditText evName;

    @Bind(R.id.rg_sex)
    RadioGroup radiogroup;

    public RegisterStep2Fragment(RegisterPresenter presenter) {
        super(presenter);
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_register_step2;
    }

    @Override
    protected void initView() {
        super.initView();
        radiogroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if(checkedId == R.id.rb_male){
                    presenter.setSex(1);
                }else  if (checkedId == R.id.rb_female){
                    presenter.setSex(0);
                }
            }
        });
    }


    @Override
    public void onValidationSucceeded() {
        presenter.setName(evName.getText().toString());
        presenter.nextStep();
//        TrackUtils.getInstance().onEvent("Register_rp_register");
    }


}
