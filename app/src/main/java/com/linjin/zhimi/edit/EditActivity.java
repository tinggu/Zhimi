package com.linjin.zhimi.edit;

import android.os.Bundle;

import com.cyou.quick.mvp.MvpBasePresenter;
import com.cyou.quick.mvp.MvpPresenter;
import com.linjin.zhimi.R;
import com.linjin.zhimi.base.BaseMvpActivity;
import com.linjin.zhimi.publish.PublshPresenter;

public class EditActivity extends BaseMvpActivity<EditView, EditPresenter> {

     
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

    }

    @Override
    public EditPresenter createPresenter() {
        return new EditPresenter();
    }


}