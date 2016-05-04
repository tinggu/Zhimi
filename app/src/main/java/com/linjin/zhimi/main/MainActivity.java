package com.linjin.zhimi.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.cyou.quick.mvp.MvpBasePresenter;
import com.cyou.quick.mvp.MvpPresenter;
import com.linjin.zhimi.R;
import com.linjin.zhimi.base.BaseMvpActivity;

import java.util.List;

import butterknife.Bind;


public class MainActivity extends BaseMvpActivity {

    @Bind(R.id.bottom_bar)
    View bottomBar;

    @Bind(R.id.main_group)
    public RadioGroup mainGroup;

    @Bind(R.id.dock_1)
    public RadioButton dock1;

    @Bind(R.id.dock_2)
    public RadioButton dock2;

    @Bind(R.id.dock_4)
    public RadioButton dock_4;

    private List<Fragment> fragments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    @Override
    public MvpPresenter createPresenter() {
        return new MvpBasePresenter();
    }

    @Override
    protected void onNewIntent(Intent intent) {
    }

//    private GroupListFragment groupListFragment;
//    private ConversationRecentFragment messageGroupFragment;
//    private SelfFragment selfFragment;

    private void initView() {

        //定位底部tab,默认定位0，即群列表页
        int index = getIntent().getIntExtra("index", 0);

//        fragments = new ArrayList<>();
//        groupListFragment = new GroupListFragment();
//        fragments.add(groupListFragment);
//        messageGroupFragment = new ConversationRecentFragment();
//        fragments.add(messageGroupFragment);
//        selfFragment = new SelfFragment();
//        fragments.add(selfFragment);
//
//        FragmentTabAdapter tabAdapter =
//                new FragmentTabAdapter(this, fragments, index, R.id.fragmentContainer, mainGroup);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    private long mPressedTime = 0;

    @Override
    public void onBackPressed() {
        long mNowTime = System.currentTimeMillis();//获取第一次按键时间
        if ((mNowTime - mPressedTime) > 2000) {//比较两次按键时间差
            toast("再按一次退出程序");
            mPressedTime = mNowTime;
        } else {//退出程序
            finish();
        }
    }

    public void checkFragment(int i) {
        switch (i) {
            case 0:
                mainGroup.check(R.id.dock_1);
                break;
            case 1:
                mainGroup.check(R.id.dock_2);
                break;
            case 2:
                mainGroup.check(R.id.dock_3);
                break;
            case 3:
                mainGroup.check(R.id.dock_4);
                break;
        }
    }


}