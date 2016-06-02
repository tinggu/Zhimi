package com.linjin.zhimi.edit;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.EditText;

import com.linjin.zhimi.R;
import com.mobsandgeeks.saripaar.annotation.Length;
import com.mobsandgeeks.saripaar.annotation.NotEmpty;
import com.mobsandgeeks.saripaar.annotation.Order;

import butterknife.BindView;


public class EditTitleFragment extends EditBaseFragment {
    
    @NotEmpty(messageResId = R.string.login_error_title_empty, sequence = 0)
    @Length(max = 15, sequence = 1)
    @Order(0) 
    @BindView(R.id.editText)
    EditText editText;

    public EditTitleFragment(EditPresenter presenter) {
        super(presenter);
    }

    
    @Override
    public void onValidationSucceeded() {
        
    }

    @Override
    protected void initView(){
        super.initView();
        topActionBar.setTitle(R.string.text_title);
    }

   
    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_edit_title;
    }
    
}
