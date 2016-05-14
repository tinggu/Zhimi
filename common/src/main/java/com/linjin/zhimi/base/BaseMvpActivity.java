package com.linjin.zhimi.base;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.widget.Toast;

import com.cyou.quick.mvp.MvpActivity;
import com.cyou.quick.mvp.MvpPresenter;
import com.cyou.quick.mvp.MvpView;
import com.cyou.quick.mvp.delegate.ActivityMvpDelegate;
import com.linjin.zhimi.base.delegate.BaseActivityDelegateImpl;

import java.util.List;

import io.realm.Realm;

/**
 * Description:
 * Copyright  : Copyright (c) 2015
 * Company    : 北京畅游天下网络科技有限公司
 * Author     : liujianguang
 * Date       : 2015/12/21
 **/

public abstract class BaseMvpActivity<V extends MvpView, P extends MvpPresenter<V>>
        extends MvpActivity<V, P> {

    private static final String TAG = "BaseMvpActivity";
    protected Realm realm;

    @Override
    protected ActivityMvpDelegate<V, P> getMvpDelegate() {
        if (this.mvpDelegate == null) {
            this.mvpDelegate = new BaseActivityDelegateImpl<>(this, getClass().getSimpleName());
        }

        return this.mvpDelegate;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        SwipeBackHelper.onCreate(this);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
//        SwipeBackHelper.onPostCreate(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
//        SwipeBackHelper.onDestroy(this);
        if (realm != null) {
            realm.close();
        }
    }

    protected void toast(String str) {
        Toast.makeText(this, str, Toast.LENGTH_SHORT).show();
    }

    protected void toast(int id) {
        Toast.makeText(this, id, Toast.LENGTH_SHORT).show();
    }

//    protected void swipeBackConfig(){
//        SwipeBackHelper.getCurrentPage(this)//获取当前页面
//                .setSwipeBackEnable(true)//设置是否可滑动
//                .setSwipeEdge(200)//可滑动的范围。px。200表示为左边200px的屏幕
//                .setSwipeEdgePercent(0.2f)//可滑动的范围。百分比。0.2表示为左边20%的屏幕
//                .setSwipeSensitivity(0.5f)//对横向滑动手势的敏感程度。0为迟钝 1为敏感
//                .setScrimColor(Color.BLUE)//底层阴影颜色
//                .setClosePercent(0.8f)//触发关闭Activity百分比
//                .setSwipeRelateEnable(false)//是否与下一级activity联动(微信效果)。默认关
//                .setSwipeRelateOffset(500)//activity联动时的偏移量。默认500px。
//                .addListener(new SwipeListener() {//滑动监听
//
//                    @Override
//                    public void onScroll(float percent, int px) {//滑动的百分比与距离
//                    }
//
//                    @Override
//                    public void onEdgeTouch() {//当开始滑动
//                    }
//
//                    @Override
//                    public void onScrollToClose() {//当滑动关闭
//                    }
//                });
//    }
}
