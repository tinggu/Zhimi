package com.linjin.zhimi.publish;

import android.annotation.SuppressLint;
import android.support.v7.widget.AppCompatEditText;

import com.linjin.zhimi.R;
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
public class PublishStep0Fragment extends PublishStepBaseFragment {
  

    @NotEmpty(messageResId = R.string.login_error_name_empty, sequence = 0)
    @Length(max = 15, sequence = 1)
    @Order(0)
    @Bind(R.id.ev_title)
    AppCompatEditText evContent;

     

    public PublishStep0Fragment(PublshPresenter presenter) {
        super(presenter);
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_publish_step0;
    }
    
    @Override
    public void onValidationSucceeded() {
        presenter.setContent(evContent.getText().toString());
        presenter.nextStep();
//        TrackUtils.getInstance().onTrackEvent("Register_rp_register");
    }


}
