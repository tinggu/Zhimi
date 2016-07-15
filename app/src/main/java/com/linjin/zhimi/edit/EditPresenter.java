package com.linjin.zhimi.edit;

import com.cyou.quick.mvp.MvpBasePresenter;
import com.cyou.common.utils.KeyboardUtils;

public class EditPresenter extends MvpBasePresenter<EditView> {
    EditActivity editActivity;
    public EditPresenter(EditActivity editActivity){
        this.editActivity = editActivity;
    }
    
    public void showEditName(){
        editActivity.showEditName();
    }
    
    public  void showEditTitle(){
        editActivity.showEditTitle();
    }

    public void back() {
        KeyboardUtils.callBackKeyClick();
    }
}
