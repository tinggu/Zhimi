package com.linjin.zhimi.publish;

import android.annotation.SuppressLint;
import android.support.v7.widget.AppCompatEditText;
import android.util.Log;

import com.linjin.zhimi.R;
import com.linjin.zhimi.widget.TopActionBar;
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
  
    
    public PublishStep0Fragment(PublshPresenter presenter) {
        super(presenter);
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_publish_step0;
    }

    @Override
    protected void initView() { 
        super.initView();
        topActionBar.setTitle("问题属于");
        Log.i("code", " initView: " + getClass().getName());
    }

    @Override
    protected void validate() {
        
    }

    


}
