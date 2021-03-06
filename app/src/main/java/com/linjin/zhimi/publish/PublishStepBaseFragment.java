package com.linjin.zhimi.publish;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import com.cyou.app.mvp.BaseMvpFragment;
import com.linjin.zhimi.R;
import com.linjin.zhimi.widget.TopActionBar;

import butterknife.BindView;

/**
 * Description:
 * Copyright  : Copyright (c) 2015
 * Company    : 北京畅游天下网络科技有限公司
 * Author     : wangjia_bi
 * Date       : 2015/6/11 14:43
 */
public abstract class PublishStepBaseFragment
        extends BaseMvpFragment<PublishView, PublshPresenter> {
            
    @BindView(R.id.topActionBar)
    TopActionBar topActionBar;
    
//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setRetainInstance(true);
//    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //屏蔽事件透传
        view.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return true;
            }
        });

        initView();
    }

    @Override
    public PublshPresenter createPresenter() {
//        if (publshPresenter == null) {
//            publshPresenter = new PublshPresenter();
//        }
//        return publshPresenter;
        return presenter;
    }

    @Override
    public PublishView getMvpView() {
        return presenter.getView();
    }

    //    @Override
//    public PublshPresenter getPresenter() {
//        createPresenter().attachView(getMvpView());
//        return publshPresenter;
//    }


    protected void initView() {
        topActionBar.setBackListener(new TopActionBar.BackListener() {
            @Override
            public void onBack() {
                getPresenter().back();

            }
        });
        topActionBar.setActionListener(new TopActionBar.ActionListener() {
            @Override
            public void onAction() {
                validate();
            }
        });
        Log.i("code", " initView: " + getClass().getName());
    }


    protected abstract void validate();


}
