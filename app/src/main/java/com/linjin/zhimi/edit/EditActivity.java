package com.linjin.zhimi.edit;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;

import com.cyou.app.mvp.BaseMvpActivity;
import com.linjin.zhimi.R; 

public class EditActivity extends BaseMvpActivity<EditView, EditPresenter> {

    EditPresenter editPresenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_container_fragment);
        editPresenter = new EditPresenter(this);
        showEdit();
    }

    @Override
    public EditPresenter createPresenter() {
        return editPresenter;
    }

    EditFragment editFragment;
    

    public void showEdit() {
        editFragment = new EditFragment(editPresenter);
        FragmentTransaction trasection = getSupportFragmentManager().beginTransaction();
        trasection.replace(R.id.fl_container, editFragment); 
        trasection.commit();
    }

    public void showEditName() {
        EditNameFragment editFragment = new EditNameFragment(editPresenter);
        FragmentTransaction trasection = getSupportFragmentManager().beginTransaction();
        trasection.replace(R.id.fl_container, editFragment);
        trasection.addToBackStack("name");
        trasection.commit();
    }

    public void showEditTitle() {
        EditTitleFragment editFragment = new EditTitleFragment(editPresenter);
        FragmentTransaction trasection = getSupportFragmentManager().beginTransaction();
        trasection.replace(R.id.fl_container, editFragment);
        trasection.addToBackStack("title");
        trasection.commit();
    }

    

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (editFragment != null) {
            editFragment.onActivityResult(requestCode, resultCode, data);
        }

    }

}