package com.linjin.zhimi.main.menu;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;

import com.linjin.zhimi.R;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * Description:
 * Copyright  : Copyright (c) 2015
 * Company    : 北京畅游天下网络科技有限公司
 * Author     : wangjia_bi
 * Date       : 2016/2/29 14:42
 */
public class DrawMenu extends LinearLayout {
    private static final String TAG = "DrawMenu";
    @BindView(R.id.rv_menu)
    RecyclerView rvMenu;


    public DrawMenu(Context context) {
        super(context);
        initView(context);
    }

    public DrawMenu(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public DrawMenu(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    private void initView(Context context) {
        View.inflate(context, R.layout.view_draw_menu, this);
        ButterKnife.bind(this);
    }


    @OnClick({R.id.tv_last, R.id.tv_collect, R.id.tv_draft})
    public void onItemOnclick(View view) {
        Intent intent;
        switch (view.getId()) {

            case R.id.tv_last:
                EventBus.getDefault().post(new MenuEventLast());
            case R.id.tv_collect:

                EventBus.getDefault().post(new MenuEventCollent());
                break;
            case R.id.tv_draft:
                EventBus.getDefault().post(new MenuEventDraft());
                break;
        }
    }
}
