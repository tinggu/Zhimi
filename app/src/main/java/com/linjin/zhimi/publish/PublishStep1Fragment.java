package com.linjin.zhimi.publish;

import android.annotation.SuppressLint;
import android.util.Log;

import com.cyou.ui.ClearableEditText;
import com.linjin.zhimi.R;
import com.mobsandgeeks.saripaar.annotation.Length;
import com.mobsandgeeks.saripaar.annotation.NotEmpty;
import com.mobsandgeeks.saripaar.annotation.Order;

import butterknife.BindView;

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
    @BindView(R.id.ev_title)
    ClearableEditText evTitle;

     

    public PublishStep1Fragment(PublshPresenter presenter) {
        super(presenter);
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_register_step3;
    }


    @Override
    protected void initView() {
        super.initView();
        topActionBar.setTitle("问题内容");
        Log.i("code", " initView: " + getClass().getName());
    }

    @Override
    protected void validate() {

    }


}
