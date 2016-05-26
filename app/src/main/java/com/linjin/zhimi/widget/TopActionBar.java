package com.linjin.zhimi.widget;

import android.content.Context;
import android.support.annotation.ColorRes;
import android.support.annotation.DrawableRes;
import android.support.annotation.StringRes;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.linjin.zhimi.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Description:
 * Copyright  : Copyright (c) 2015
 * Company    : 北京畅游天下网络科技有限公司
 * Author     : wangjia_bi
 * Date       : 2016/2/29 16:51
 */
public class TopActionBar extends RelativeLayout {

    public static final byte BACK_IMAGE = 0;
    public static final byte BACK_TXT = 1;

    @BindView(R.id.iv_back)
    ImageView iv_back;

    @BindView(R.id.tv_title)
    TextView tv_title;

    @BindView(R.id.tv_back)
    TextView tv_back;

    @BindView(R.id.tv_action)
    TextView tv_action;

    @BindView(R.id.iv_action)
    ImageView iv_action;

    @BindView(R.id.bottom_line)
    View bottom_line;

    private BackListener backListener;
    private ActionListener actionListener;

    public TopActionBar(Context context) {
        super(context);
        initView(context);
    }

    public TopActionBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public TopActionBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    private void initView(Context context) {
        View.inflate(context, R.layout.top_bar, this);
        ButterKnife.bind(this);
//        setVisibility(GONE);
    }

//    @Override
//    protected void onFinishInflate() {
//        super.onFinishInflate();
//        ButterKnife.bind(this);
//        //默认返回是图片类型
//        setBackType(BACK_IMAGE);
//        //默认不显示finish
//        tv_action.setVisibility(GONE);
//    }

    public void setTitle(@StringRes int resid) {
        tv_title.setText(resid);
    }

    public void setTitle(String title) {
        tv_title.setText(title);
    }

    public void setTitleColor(@ColorRes int colorid) {
        tv_title.setTextColor(getResources().getColor(colorid));
    }

    public void setBackText(@StringRes int resid) {
        setBackText(getResources().getString(resid));
    }

    public void setBackText(String title) {
        iv_back.setVisibility(View.GONE);
        tv_back.setVisibility(View.VISIBLE);
        tv_back.setText(title);
    }

    public void setActionText(@StringRes int resid) {
        tv_action.setVisibility(View.VISIBLE);
        tv_action.setText(resid);
    }

    public void setActionText(String text) {
        tv_action.setVisibility(View.VISIBLE);
        tv_action.setText(text);
    }

    public void showActionText() {
        tv_action.setVisibility(VISIBLE);
    }

    public void setActionEnabled(boolean isEnabled, @ColorRes int colorid) {
        tv_action.setEnabled(isEnabled);
        tv_action.setTextColor(getResources().getColor(colorid));
    }

//    public void setActionVisibility(int visibility) {
//        tv_action.setVisibility(GONE);
//    }

    public void showBottomLine() {
        bottom_line.setVisibility(VISIBLE);
    }

    public void hideBottomLine() {
        bottom_line.setVisibility(GONE);
    }

    public void hideBack() {
        tv_back.setVisibility(View.GONE);
        iv_back.setVisibility(View.GONE);
    }

    public void hideAction() {
        tv_action.setVisibility(View.GONE);
        iv_action.setVisibility(View.GONE);
    }

    public void setActionImageResource(@DrawableRes int resId) {
        iv_action.setVisibility(View.VISIBLE);
        iv_action.setImageResource(resId);
    }

    public void setBackType(int type) {
        if (type == BACK_IMAGE) {
            tv_back.setVisibility(GONE);
            iv_back.setVisibility(VISIBLE);
        } else {
            tv_back.setVisibility(VISIBLE);
            iv_back.setVisibility(GONE);
        }
    }

    @OnClick({R.id.iv_back, R.id.tv_back, R.id.tv_action, R.id.iv_action})
    public void onClick(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.iv_back:
            case R.id.tv_back:
                if (backListener != null) {
                    backListener.onBack();
                }
                break;

            case R.id.tv_action:
                if (actionListener != null) {
                    actionListener.onAction();
                }
                break;
            case R.id.iv_action:
                if (actionListener != null) {
                    actionListener.onAction();
                }
                break;
        }
    }

    public void setBackListener(BackListener backListener) {
        this.backListener = backListener;
    }

    public void setActionListener(ActionListener actionListener) {
        this.actionListener = actionListener;
    }

    public interface BackListener {

        void onBack();
    }

    public interface ActionListener {
        void onAction();
    }

}
