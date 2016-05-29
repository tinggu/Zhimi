package com.linjin.zhimi.edit;


import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.linjin.zhimi.R;
import com.linjin.zhimi.base.BaseMvpFragment;
import com.linjin.zhimi.setting.SettingActivity;
import com.linjin.zhimi.utils.PhotoUtils;
import com.linjin.zhimi.widget.TopActionBar;
import com.tinggu.common.utils.KeyboardUtils;
import com.tinggu.common.utils.LogUtils;

import butterknife.BindView;
import butterknife.OnClick;


public class EditFragment extends BaseMvpFragment implements PhotoUtils.RefreshImage {

    PhotoUtils photoUtils = new PhotoUtils();
    private Uri headUri;
    private boolean isUpdatePic;

    @BindView(R.id.topActionBar)
    TopActionBar topActionBar;
    @BindView(R.id.iv_avatar)
    SimpleDraweeView ivAvatar;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.item_title)
    LinearLayout itemTitle;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.item_name)
    LinearLayout itemName;
    @BindView(R.id.tv_sex)
    TextView tvSex;
    @BindView(R.id.item_sex)
    LinearLayout itemSex;
    @BindView(R.id.tv_birthday)
    TextView tvBirthday;
    @BindView(R.id.item_birthday)
    LinearLayout itemBirthday;
    @BindView(R.id.tv_location)
    TextView tvLocation;
    @BindView(R.id.item_location)
    LinearLayout itemLocation;


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView();
    }

    private void initView() {
        topActionBar.setTitle(R.string.text_edit);
        topActionBar.setBackListener(new TopActionBar.BackListener() {
            @Override
            public void onBack() {
                KeyboardUtils.callBackKeyClick();
            }
        });
        topActionBar.hideAction();
    }

    @Override
    public EditPresenter createPresenter() {
        return new EditPresenter();
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_edit;
    }


    @OnClick({R.id.iv_avatar, R.id.item_title, R.id.item_name, R.id.item_sex,
            R.id.item_birthday, R.id.item_location})
    public void onItemOnclick(View view) {
        Intent intent;
        switch (view.getId()) {
            case R.id.iv_avatar:
//                intent = latest Intent(getActivity(), CardManageActivity.class);
//                startActivity(intent);
                break;
            case R.id.item_title:
//                IntentStarter.showGroupSelect(getActivity());
                break;
            case R.id.item_name:
//                IntentStarter.showNoticeCreate(getActivity(), 147);
                break;
            case R.id.item_sex:
//                IntentStarter.showNoticeInfo(getActivity(), 147, 10);
//                break;
            case R.id.item_birthday:
                intent = new Intent(getActivity(), EditActivity.class);
                startActivity(intent);
                break;
            case R.id.item_location:
                intent = new Intent(getActivity(), SettingActivity.class);
                startActivity(intent);
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
        LogUtils.i("header", "requestCode = " + requestCode);
        if (Activity.RESULT_OK != resultCode) {
            return;
        }
        photoUtils.setBitmapFromResult(getActivity(), this, requestCode, data, PhotoUtils.HEAD_PIC);
    }

    @Override
    public void refresh(Uri uri) {

    }
}
