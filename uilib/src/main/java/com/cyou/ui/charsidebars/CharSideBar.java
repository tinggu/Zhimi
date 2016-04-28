package com.cyou.ui.charsidebars;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

/**
 * Description: 屏幕右侧字母条（用于字母导航功能）
 * Copyright  : Copyright (c) 2015
 * Company    : 北京畅游天下网络科技有限公司
 * Author     : liujianguang
 * Date       : 2016/3/4
 **/

public class CharSideBar extends View {

    /**
     * 触摸事件监听器
     */
    private OnTouchingCharChangedListener onTouchingCharChangedListener;

    /**
     * 显示字符(26字母+#)
     */
    public static final String[] b = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q",
            "R", "S", "T", "U", "V", "W", "X", "Y", "Z", "#"};

    /**
     * 选中的第几个字符
     */
    private int choose = -1;

    /**
     * 选中颜色
     */
    private int checkedColor = Color.parseColor("#237AD8");

    /**
     * 未选中颜色(默认)
     */
    private int uncheckedColor = Color.parseColor("#787878");

    /**
     * 画笔
     */
    private Paint paint = new Paint();

    private TextView mTextDialog;

    public CharSideBar(Context context) {
        super(context);
    }

    public CharSideBar(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CharSideBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    /**
     * 设置字符选中和未选中颜色
     *
     * @param uncheckedColor
     */
    public void setCharColor(int checkedColor, int uncheckedColor) {
        if (0 != checkedColor) {
            this.checkedColor = checkedColor;
        }
        if (0 != uncheckedColor) {
            this.uncheckedColor = uncheckedColor;
        }
    }

    /**
     * 设置弹出选中字符
     *
     * @param mTextDialog
     */
    public void setmTextDialog(TextView mTextDialog) {
        this.mTextDialog = mTextDialog;
    }

    /**
     * 绘制不同时刻bar
     *
     * @param canvas
     */
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //获得bar高度和宽度
        int height = getHeight();
        int width = getWidth();

        //获得一个字母高度
        float singleHeight = (height * 1f) / b.length;
        for (int i = 0; i < b.length; i++) {
            paint.setColor(uncheckedColor);
            paint.setTypeface(Typeface.DEFAULT_BOLD);
            //打开抗锯齿
            paint.setAntiAlias(true);
            paint.setTextSize(30);

            //选中状态
            if (i == choose) {
                paint.setColor(checkedColor);
                //设置文本仿粗体
                paint.setFakeBoldText(true);
            }

            float xPos = width / 2 - paint.measureText(b[i]) / 2;
            float yPos = singleHeight * i + singleHeight;
            canvas.drawText(b[i], xPos, yPos, paint);
            paint.reset();
        }
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        final int action = event.getAction();
        // 触摸点y坐标
        final float y = event.getY();
        final int oldChoose = choose;
        final OnTouchingCharChangedListener listener = onTouchingCharChangedListener;
        // 点击y坐标所占总高度的比例*b数组的长度就等于点击b中的个数
        final int c = (int) (y / getHeight() * b.length);

        switch (action) {
            case MotionEvent.ACTION_UP:
//                setBackgroundDrawable(new ColorDrawable(0x00000000));
                choose = -1;//
                invalidate();
                if (mTextDialog != null) {
                    mTextDialog.setVisibility(View.INVISIBLE);
                }
                break;

            default:
//                setBackgroundResource(R.drawable.charsidebar_bg);
                if (oldChoose != c) {
                    if (c >= 0 && c < b.length) {
                        if (listener != null) {
                            listener.onTouchingCharChanged(b[c]);
                        }
                        if (mTextDialog != null) {
                            mTextDialog.setText(b[c]);
                            mTextDialog.setVisibility(View.VISIBLE);
                        }

                        choose = c;
                        invalidate();
                    }
                }

                break;
        }
        return true;
    }

    /**
     * 向外暴露的接口
     *
     * @param onTouchingCharChangedListener
     */
    public void setOnTouchingCharChangedListener(OnTouchingCharChangedListener onTouchingCharChangedListener) {
        this.onTouchingCharChangedListener = onTouchingCharChangedListener;
    }

    /**
     * 字符被触摸时调用接口
     */
    public interface OnTouchingCharChangedListener {
        void onTouchingCharChanged(String c);
    }
}
