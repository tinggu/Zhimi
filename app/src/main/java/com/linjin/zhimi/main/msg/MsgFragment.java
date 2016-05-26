package com.linjin.zhimi.main.msg;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.cyou.quick.mvp.MvpFragment;
import com.cyou.quick.mvp.MvpPresenter;
import com.linjin.zhimi.R;
import com.linjin.zhimi.utils.IntentStarter;
import com.linjin.zhimi.widget.TopActionBar;

import butterknife.BindView;
import butterknife.OnClick;


public class MsgFragment extends MvpFragment {
    
    @BindView(R.id.topActionBar)
    TopActionBar topActionBar; 

   @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView();
    }

    private void initView(){
        topActionBar.setTitle(R.string.radio_dock_3);
        topActionBar.hideBack();
        topActionBar.hideAction();
        topActionBar.setActionImageResource(R.mipmap.msg_add_user);
        topActionBar.setActionListener(new TopActionBar.ActionListener() {
            @Override
            public void onAction() {
                IntentStarter.showFind(getActivity());
            }
        });
    }

    @Override
    public MvpPresenter createPresenter() {
        return new MsgPresenter();
    }


    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_msg;
    }

    @OnClick(R.id.item_apply) void apply() {
        IntentStarter.showApply(getActivity());
    }
}
