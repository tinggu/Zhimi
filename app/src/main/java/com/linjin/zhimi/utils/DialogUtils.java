package com.linjin.zhimi.utils;

import android.app.Activity;
import android.app.Dialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.linjin.zhimi.R;


/**
 * Description:
 * Copyright  : Copyright (c) 2015
 * Company    : 北京畅游天下网络科技有限公司
 * Author     : liujianguang
 * Date       : 2016/1/5
 **/

public class DialogUtils {

    Dialog mDialog;

    public void showLoading(Activity activity, int content) {
        CharSequence message = activity.getText(content);
        showLoading(activity, message);
    }

    /**
     * 得到自定义的loadingDialog
     *
     * @param activity
     * @param msg
     * @return
     */
    public void showLoading(Activity activity, CharSequence msg) {
        if (mDialog != null && mDialog.isShowing()) {
            mDialog.dismiss();
        } else {
            LayoutInflater inflater = LayoutInflater.from(activity);
            View v = inflater.inflate(R.layout.dialog_loading, null);// 得到加载view
            LinearLayout layout = (LinearLayout) v.findViewById(R.id.dialog_view);// 加载布局
            TextView tipTextView = (TextView) v.findViewById(R.id.tipTextView);// 提示文字
            tipTextView.setText(msg);// 设置加载信息
            mDialog = new Dialog(activity, R.style.loading_dialog);// 创建自定义样式dialog
            mDialog.setCanceledOnTouchOutside(false);
            mDialog.setContentView(layout, new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.MATCH_PARENT));// 设置布局
            mDialog.getWindow().setWindowAnimations(R.style.dialogWindowAnim);
        }
        mDialog.show();
    }

    public void hideLoading() {
        if (mDialog != null && mDialog.isShowing()) {
            mDialog.dismiss();
        }
    }

}
