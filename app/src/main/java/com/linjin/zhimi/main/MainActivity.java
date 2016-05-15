package com.linjin.zhimi.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.cyou.quick.mvp.MvpBasePresenter;
import com.cyou.quick.mvp.MvpPresenter;
import com.linjin.zhimi.R;
import com.linjin.zhimi.base.BaseMvpActivity;
import com.linjin.zhimi.main.chat.ChatFragment;
import com.linjin.zhimi.main.discovery.DiscoveryFragment;
import com.linjin.zhimi.main.self.SelfFragment;
import com.linjin.zhimi.main.topic.TopicFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;


public class MainActivity extends BaseMvpActivity
        implements RadioGroup.OnCheckedChangeListener {

//    @Bind(R.id.bottom_bar)
//    View bottomBar;

    @Bind(R.id.main_group)
    public RadioGroup mainGroup;

//    @Bind(R.id.dock_1)
//    public RadioButton dock1;
//
//    @Bind(R.id.dock_2)
//    public RadioButton dock2;
//
//    @Bind(R.id.dock_3)
//    public RadioButton dock3;
//
//    @Bind(R.id.dock_4)
//    public RadioButton dock4;

    private List<Fragment> fragments;

    private int fragmentContentId;

    private TopicFragment topicFragment;
    private DiscoveryFragment discoveryFragment;
    private ChatFragment chatFragment;
    private SelfFragment selfFragment;

    private int currentTab; // 当前Tab页面索引

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

    private void initView() {

        //定位底部tab,默认定位0，即群列表页
        int index = getIntent().getIntExtra("index", 0);

        fragments = new ArrayList<>();
        topicFragment = new TopicFragment();
        fragments.add(topicFragment);
        discoveryFragment = new DiscoveryFragment();
        fragments.add(discoveryFragment);
        chatFragment = new ChatFragment();
        fragments.add(chatFragment);
        selfFragment = new SelfFragment();
        fragments.add(selfFragment);
//
//        FragmentTabAdapter tabAdapter =
//                new FragmentTabAdapter(this, fragments, index, R.id.fragmentContainer, mainGroup);

        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        //由通知进入个人主页
        // 默认显示第一页

        for (int i = 0; i < fragments.size(); i++) {
            ft.add(fragmentContentId, fragments.get(i));
            ft.hide(fragments.get(i));
        }
        ft.show(fragments.get(index));
        //ft.add(fragmentContentId, fragments.get(index));

        ft.commit();
        switch (index) {
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
        mainGroup.setOnCheckedChangeListener(this);
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

//    public void checkFragment(int i) {
//        switch (i) {
//            case 0:
//                mainGroup.check(R.id.dock_1);
//                break;
//            case 1:
//                mainGroup.check(R.id.dock_2);
//                break;
//            case 2:
//                mainGroup.check(R.id.dock_3);
//                break;
//            case 3:
//                mainGroup.check(R.id.dock_4);
//                break;
//        }
//    }


    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        //将radioButton筛选出来
        ArrayList<View> radioButtons = getChildRadions();

        for (int i = 0; i < radioButtons.size(); i++) {
            if (radioButtons.get(i).getId() == checkedId) {
                Fragment fragment = fragments.get(i);
                FragmentTransaction ft = obtainFragmentTransaction(i);

                //getCurrentFragment().onPause(); // 暂停当前tab
//                getCurrentFragment().onStop(); // 暂停当前tab

                if (fragment.isAdded()) {
//                    fragment.onStart(); // 启动目标tab的onStart()
                    //fragment.onResume(); // 启动目标tab的onResume()
                } else {
                    ft.add(fragmentContentId, fragment);
                }
                changeFragmentTab(i); // 显示目标tab
                ft.commitAllowingStateLoss();
            }
        }
    }

    private void changeFragmentTab(final int index) {
        for (int i = 0; i < fragments.size(); i++) {
            Fragment fragment = fragments.get(i);
            FragmentTransaction transaction = obtainFragmentTransaction(index);
            if (i == index) {
                transaction.show(fragment);
            } else {
                transaction.hide(fragment);
            }
            transaction.commit();
        }
    }

    /**
     * 获取一个带动画的FragmentTransaction
     *
     * @param index
     * @return
     */
    private FragmentTransaction obtainFragmentTransaction(int index) {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        // 设置切换动画
        if (index > currentTab) {
            ft.setCustomAnimations(R.anim.slide_left_in, R.anim.slide_left_out);
        } else {
            ft.setCustomAnimations(R.anim.slide_right_in, R.anim.slide_right_out);
        }
        return ft;
    }


    private ArrayList<View> getChildRadions() {
        ArrayList<View> childs = new ArrayList<>();
        for (int i = 0; i < mainGroup.getChildCount(); i++) {
            View v = mainGroup.getChildAt(i);
            if (v instanceof RadioButton) {
                childs.add(v);
            }
        }
        return childs;
    }


}