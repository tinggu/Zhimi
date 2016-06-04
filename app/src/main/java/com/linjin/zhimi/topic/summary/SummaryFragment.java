package com.linjin.zhimi.topic.summary;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.cyou.quick.mvp.MvpBasePresenter;
import com.linjin.zhimi.R;
import com.linjin.zhimi.base.BaseMvpFragment;
import com.linjin.zhimi.utils.IntentStarter;
import com.linjin.zhimi.utils.KeyboardUtils;
import com.linjin.zhimi.widget.TopActionBar;

import butterknife.BindView;
import butterknife.OnClick;


public class SummaryFragment extends BaseMvpFragment {

    @BindView(R.id.topActionBar)
    TopActionBar topActionBar;

    private String topicId;


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView();
    }

    private void initView() {
        topActionBar.setTitle("匿题摘要");
        topActionBar.setBackListener(new TopActionBar.BackListener() {
            @Override
            public void onBack() {
                KeyboardUtils.callBackKeyClick();
            }
        });
        topActionBar.hideAction();

    }

    @Override
    public MvpBasePresenter createPresenter() {
        return new MvpBasePresenter();
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_summay;
    }

    @OnClick({R.id.item_black_list, R.id.item_account, R.id.btn_logout})
    public void onItemOnclick(View view) {
        Intent intent;
        switch (view.getId()) {

            case R.id.item_black_list:
//                IntentStarter.showNoticeInfo(getActivity(), 147, 10);
//                break;
            case R.id.item_account:

                break;
            case R.id.btn_logout:
                IntentStarter.showLogin(null);
                break;
        }
    }

}
