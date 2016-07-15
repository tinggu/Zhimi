package com.linjin.zhimi.edit;


import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.cyou.app.mvp.BaseMvpFragment;
import com.cyou.common.utils.KeyboardUtils;
import com.cyou.common.utils.LogUtils;
import com.cyou.ui.wheelView.ChangeAddressDialog;
import com.cyou.ui.wheelView.ChangeBirthDialog;
import com.cyou.ui.wheelView.ChangeSexDialog;
import com.facebook.drawee.view.SimpleDraweeView;
import com.linjin.zhimi.DataCenter;
import com.linjin.zhimi.R;
import com.linjin.zhimi.model.account.User;
import com.linjin.zhimi.utils.PhotoUtils;
import com.linjin.zhimi.widget.SelectPicPopWindow;
import com.linjin.zhimi.widget.TopActionBar;

import butterknife.BindView;
import butterknife.OnClick;
//import io.realm.Realm;
//import io.realm.RealmResults;


public class EditFragment extends BaseMvpFragment<EditView, EditPresenter>
        implements PhotoUtils.RefreshImage {

    EditPresenter editPresenter;

    PhotoUtils photoUtils = new PhotoUtils();
    private Uri headUri;
    private boolean isUpdatePic;
    SelectPicPopWindow selectPicPW;

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

    private User user;

    public EditFragment(EditPresenter editPresenter) {
        this.editPresenter = editPresenter;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
//        realm = Realm.getDefaultInstance();
//        RealmResults<User> users = CacheUtils.findAll(realm, User.class);
//        user = users.first();
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
        tvName.setText(user.getName());
        tvTitle.setText(user.getTitle());
        if (TextUtils.isEmpty(user.getBirthday())) {
            tvBirthday.setText("选填，只用于匹配系统");
        } else {
            tvBirthday.setText(user.getBirthday());
        }
        tvSex.setText(user.getSex() == 0 ? R.string.girl : R.string.boy);

        if (TextUtils.isEmpty(user.getBirthday())) {
            tvLocation.setText("请选择");
        } else {
            tvLocation.setText(user.getLocation());
        }
    }

    @Override
    public EditPresenter createPresenter() {
        return editPresenter;
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_edit;
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
        selectPicPW.showAtLocation(ivAvatar, Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
        //设置layout在PopupWindow中显示的位置
    }

    @OnClick({R.id.iv_avatar, R.id.item_title, R.id.item_name, R.id.item_sex,
            R.id.item_birthday, R.id.item_location})
    public void onItemOnclick(View view) {
        Intent intent;
        switch (view.getId()) {
            case R.id.iv_avatar:
                showSwitchPicDlg();
                break;
            case R.id.item_title:
                editPresenter.showEditTitle();
                break;
            case R.id.item_name:
                editPresenter.showEditTitle();
                break;
            case R.id.item_sex:
                showChangeSexDialog();
                break;
            case R.id.item_birthday:
                showChangeBirthdayDialog();
                break;
            case R.id.item_location:
                showChangeLocationDialog();
                break;
        }
    }

    private void showChangeLocationDialog() {
        DataCenter.getInstance().getUserID();
        final ChangeAddressDialog mChangeAddressDialog = new ChangeAddressDialog(getActivity());
//        mChangeAddressDialog.setAddress(mChangeUser.areaName, mChangeUser.areaProvince, mChangeUser.areaCity);
        mChangeAddressDialog.show();
        mChangeAddressDialog.setAddresskListener(new ChangeAddressDialog.OnAddressCListener() {

            @Override
            public void onClick(String area, String province, String city) {
                toast(area + "-" + province + "-" + city);

//                mChangeUser.areaName = area;
//                mChangeUser.areaProvince = province;
//                mChangeUser.areaCity = city;
//                updateUserInfo();
            }
        });
    }

    private void showChangeBirthdayDialog() {
        ChangeBirthDialog mChangeBirthDialog = new ChangeBirthDialog(getActivity());
//        if (TextUtils.isEmpty(mChangeUser.birthday)) {
//            mChangeBirthDialog.setDate(1985, 1, 1);//默认值
//        } else {
//            String[] ymd = mChangeUser.birthday.split("-");
//            if (ymd.length == 3) {
//                mChangeBirthDialog.setDate(
//                        Integer.parseInt(ymd[0]),
//                        Integer.parseInt(ymd[1]),
//                        Integer.parseInt(ymd[2]));
//            } else {
//                mChangeBirthDialog.setDate(1985, 1, 1);//默认值
//            }
//
//        }
        mChangeBirthDialog.show();
        mChangeBirthDialog.setBirthdayListener(new ChangeBirthDialog.OnBirthListener() {
            @Override
            public void onClick(String year, String month, String day) {

                StringBuilder sb = new StringBuilder();
                sb.append(year)
                        .append("-")
                        .append(month)
                        .append("-")
                        .append(day);

                toast(sb.toString());

//                mChangeUser.birthday = sb.toString();
//                updateUserInfo();
            }
        });
    }

    public void showChangeSexDialog() {
        ChangeSexDialog changeSexDialog = new ChangeSexDialog(getActivity());
        changeSexDialog.show();
//        changeSexDialog.setOnSexCListener(new ChangeSexDialog.OnSexCListener() {
//            @Override
//            public void onClick(String sex) {
//                User user = DataCenter.getDataCenter().getLoginUser();
//                if (user != null) {
//                    if (sex.equals(getActivity().getResources().getString(R.string.girl))) {
//                        user.sex = "0";
//                    } else {
//                        user.sex = "1";
//                    }
//                }
//                DataCenter.getDataCenter().saveLoginUser(user);
//                //upload user info
//                uploadUserInfo(user);
//            }
//        });
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
