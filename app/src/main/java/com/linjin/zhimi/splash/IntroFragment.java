package com.linjin.zhimi.splash;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;

import com.cyou.quick.QuickFragment;
import com.linjin.zhimi.R;
import com.linjin.zhimi.main.MainActivity;
import com.cyou.common.utils.TrackUtils;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Description:
 * Copyright  : Copyright (c) 2015
 * Company    : 北京畅游天下网络科技有限公司
 * Author     : wangjia_bi
 * Date       : 2015/9/7 9:18
 */
public class IntroFragment extends QuickFragment
        implements View.OnClickListener, ViewPager.OnPageChangeListener {
    TrackUtils mTrackUtils = TrackUtils.getInstance();
    
    private static final int[] viewIds = {
//            R.layout.view_intro_0,
//            R.layout.view_intro_1,
//            R.layout.view_intro_2,
    };

    @BindView(R.id.viewpager)
    ViewPager viewPager;

//    @Bind(R.id.indicator)
//    SpriteIndicator indicator;


    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_intro;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView();
    }

    private void initView() {
        ArrayList<View> views = new ArrayList<>();
        LayoutInflater layoutInflater = LayoutInflater.from(getActivity());

        //初始化引导图片列表  
        for (int i = 0; i < viewIds.length; i++) {
            View view = layoutInflater.inflate(viewIds[i], null);
            views.add(view);
            if (i == viewIds.length - 1) {
//                Button butEnter  = (Button) view.findViewById(R.id.but_enter);
//                butEnter.setOnClickListener(this);
            }
        }


        //初始化Adapter  
        IntorPagerAdapter vpAdapter = new IntorPagerAdapter(views);
        viewPager.setAdapter(vpAdapter);
//        //绑定回调  
        viewPager.addOnPageChangeListener(this);

        //初始化底部小点  
//        indicator.setViewPager(viewPager);
    }

    @OnClick({R.id.btn_register, R.id.btn_login})
    public void onClick(View v) {
        Context context = getActivity(); 
        Intent i = new Intent(context, MainActivity.class);
        context.startActivity(i);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
//        if (position == viewIds.length - 1) {
//            indicator.setVisibility(View.GONE);
//        } else {
//            indicator.setVisibility(View.VISIBLE);
//        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public void onResume() {
        super.onResume();
        mTrackUtils.onPageStart("IntroFragment");
    }

    @Override
    public void onPause() {
        super.onPause();
        mTrackUtils.onPageEnd("IntroFragment");
    }
}
