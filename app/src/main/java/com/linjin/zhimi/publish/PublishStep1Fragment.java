package com.linjin.zhimi.publish;

import android.annotation.SuppressLint;

import com.cyou.ui.ClearableEditText;
import com.linjin.zhimi.R;
import com.linjin.zhimi.account.RegisterPresenter;
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
public class PublishStep1Fragment extends PublishStepBaseFragment {
  

    @NotEmpty(messageResId = R.string.login_error_name_empty, sequence = 0)
    @Length(max = 15, sequence = 1)
    @Order(0)
    @Bind(R.id.ev_title)
    ClearableEditText evTitle;

     

    public PublishStep1Fragment(PublshPresenter presenter) {
        super(presenter);
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_register_step3;
    }

   


    @Override
    public void onValidationSucceeded() {
//        presenter.setContent(evContent.getText().toString());
//        presenter.nextStep();
        
//        TrackUtils.getInstance().onTrackEvent("Register_rp_register");
    }


}
