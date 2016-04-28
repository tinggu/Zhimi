package com.linjin.zhimi.widget;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.content.Context;
import android.support.annotation.StringRes;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.linjin.zhimi.R;

import butterknife.ButterKnife;


/**
 * Description:
 * Copyright  : Copyright (c) 2015
 * Company    : 北京畅游天下网络科技有限公司
 * Author     : wangjia_bi
 * Date       : 2016/2/29 14:42
 */
public class TopSnackBar extends LinearLayout {
    private static final String TAG = "TopSnackBar";
//    private static final int DURATION = 250;
//    private static final int SHOW_TIME = 2000;

    private TextView tvTip;

    private boolean isAnimFinsh;

    public TopSnackBar(Context context) {
        super(context);
        initView(context);
    }

    public TopSnackBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public TopSnackBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    private void initView(Context context) {
        View.inflate(context, R.layout.top_tip, this);
        ButterKnife.bind(this);
        setVisibility(GONE);
        isAnimFinsh = true;
        tvTip = (TextView) findViewById(R.id.tv_tip);
    }

//    @Override
//    protected void onFinishInflate() {
//        super.onFinishInflate();
//        setVisibility(GONE);
//        isAnimFinsh = true;
//        tvTip = (TextView) findViewById(R.id.tv_tip);
//    }

    public void showTip(@StringRes int stringId) {
//        String tip = getContext().getString()
        tvTip.setText(stringId);
    }

    public void showOnceTip(@StringRes int stringId) {
        tvTip.setText(stringId);
        addonceAnim();
    }

    public void showOnceTip(String message) {
        tvTip.setText(message);
        addonceAnim();
    }

    private void addonceAnim() {
        if (!isAnimFinsh) {
            return;
        }
        // 加载动画  
        Animator anim = AnimatorInflater.loadAnimator(getContext(), R.animator.tip_once_animator);

        anim.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
                isAnimFinsh = false;
                Log.i(TAG, "onAnimationStart: ");
                TopSnackBar.this.setVisibility(VISIBLE);
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                Log.i(TAG, "onAnimationEnd: ");
                isAnimFinsh = true;
                TopSnackBar.this.setVisibility(GONE);
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });

        anim.setTarget(this);
        anim.start();
    }

}
