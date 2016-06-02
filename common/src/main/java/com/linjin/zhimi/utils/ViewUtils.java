package com.linjin.zhimi.utils;

import android.os.Handler;
import android.os.Message;
import android.view.MotionEvent;
import android.view.View;

/**
 * Description:
 * Copyright  : Copyright (c) 2015
 * Company    : 北京畅游天下网络科技有限公司
 * Author     : wangjia_bi
 * Date       : 2015/8/17 14:56
 */
public class ViewUtils {

    public static final int DOUBLE_CLICK_TIME = 350;        //双击间隔时间350毫秒

    /**
     * 屏蔽view点击事件
     *
     * @param view
     */
    public static final void interruptOnClick(View view) {
        //屏蔽事件透传
        view.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return true;
            }
        });
    }

    /**
     * //恢复事件透传
     *
     * @param view
     */
    public static final void revertOnClick(View view) {

        view.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return false;
            }
        });
    }


    /**
     * 注册一个双击事件
     * 增加  Handler  处理，如果不加这个，会引起线程安全之类错误。
     */
    public static void registerDoubleClickListener(View view, final OnDoubleClickListener listener) {
        if (listener == null) return;
        view.setOnClickListener(new View.OnClickListener() {

            private boolean waitDouble = true;

            private Handler handler = new Handler() {
                @Override
                public void handleMessage(Message msg) {
                    listener.OnSingleClick((View) msg.obj);
                }

            };

            //等待双击
            public void onClick(final View v) {
                if (waitDouble) {
                    waitDouble = false;        //与执行双击事件
                    new Thread() {

                        public void run() {
                            try {
                                Thread.sleep(DOUBLE_CLICK_TIME);
                            } catch (InterruptedException e) {
                                // TODO Auto-generated catch block
                                e.printStackTrace();
                            }    //等待双击时间，否则执行单击事件
                            if (!waitDouble) {
                                //如果过了等待事件还是预执行双击状态，则视为单击
                                waitDouble = true;
                                Message msg = handler.obtainMessage();
                                msg.obj = v;
                                handler.sendMessage(msg);
                            }
                        }

                    }.start();
                } else {
                    waitDouble = true;
                    listener.OnDoubleClick(v);    //执行双击
                }
            }
        });
    }

    public interface OnDoubleClickListener {
        void OnSingleClick(View v);

        void OnDoubleClick(View v);
    }

    /**
     * 获得测量view的宽度，可保证在view布局前获得view设定的具体宽度
     *
     * @param view 只限有具体宽度属性值
     * @return
     */
    public static int getViewWidth(View view) {
        int width = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        int height = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        view.measure(width, height);
        return view.getMeasuredWidth();
    }

}