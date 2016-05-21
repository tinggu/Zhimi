package com.linjin.zhimi.account;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;

import com.linjin.zhimi.R;
import com.linjin.zhimi.utils.PhotoUtils;
import com.linjin.zhimi.widget.SelectPicPopWindow;
import com.tinggu.common.utils.LogUtils;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Description:
 * Copyright  : Copyright (c) 2015
 * Company    : 北京畅游天下网络科技有限公司
 * Author     : wangjia_bi
 * Date       : 2015/6/11 14:43
 */
@SuppressLint("ValidFragment")
public class RegisterStep4Fragment extends RegisterStepBaseFragment implements PhotoUtils.RefreshImage {

    PhotoUtils photoUtils = new PhotoUtils();
    private Uri headUri;
    private boolean isUpdatePic;

    @Bind(R.id.img_avatar)
    ImageView imgAvatar;


    SelectPicPopWindow selectPicPW;

    public RegisterStep4Fragment(RegisterPresenter presenter) {
        super(presenter);
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_register_step4;
    }

    @Override
    protected void initView() {
        super.initView();
    }


    @Override
    public void onValidationSucceeded() {
        presenter.nextStep();
//        TrackUtils.getInstance().onTrackEvent("Register_rp_register");
    }

    //为弹出窗口实现监听类
    private View.OnClickListener itemsOnClick = new View.OnClickListener() {
        public void onClick(View v) {
            selectPicPW.dismiss();
            switch (v.getId()) {
                case R.id.camera:
                    photoUtils.startCamera(getActivity(), PhotoUtils.HEAD_PIC);
                    break;
                case R.id.gallery:
                    PhotoUtils.startGallery(getActivity(), PhotoUtils.HEAD_PIC);
                    break;
                default:
                    break;
            }
        }
    };


    /**
     * 点击头像，弹出的选择框
     */
    protected void showSwitchPicDlg() {
        if (null == selectPicPW) {
            //实例化SelectPicPopupWindow
            selectPicPW = new SelectPicPopWindow(getActivity(), itemsOnClick);
        }
        //显示窗口
        selectPicPW.showAtLocation(imgAvatar, Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
        //设置layout在PopupWindow中显示的位置
    }

    @OnClick({R.id.img_avatar, R.id.btn_login})
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.img_avatar) {
            showSwitchPicDlg();

        } else if (id == R.id.btn_login) {
            presenter.doRegister();
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
        LogUtils.i("header", "card header uri =" + uri);
        headUri = uri;
        isUpdatePic = true;
        imgAvatar.setImageURI(uri);
    }
}
