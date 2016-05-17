package com.linjin.zhimi.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
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

    private static final String TAG = "MainActivity";
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

    private TopicFragment topicFragment;
    private DiscoveryFragment discoveryFragment;
    private ChatFragment chatFragment;
    private SelfFragment selfFragment;

    private int currentTab; // 当前Tab页面索引

    private ArrayList<View> radioButtons;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView(savedInstanceState);
    }

    @Override
    public MvpPresenter createPresenter() {
        return new MvpBasePresenter();
    }

//    @Override
//    protected void onNewIntent(Intent intent) {
//
//    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("index", currentTab);
        Log.i(TAG, "onSaveInstanceState: ");
    }
    

    private void initView(Bundle savedInstanceState) {

        //定位底部tab,默认定位0，即群列表页
        int index = getIntent().getIntExtra("index", 0);

        fragments = new ArrayList<>();
        FragmentManager fragmentManager = getSupportFragmentManager();
        
        if(savedInstanceState != null){ // “内存重启”时调用
            topicFragment = (TopicFragment) fragmentManager.findFragmentByTag(topicFragment.getClass().getSimpleName());
            discoveryFragment = (DiscoveryFragment) fragmentManager.findFragmentByTag(discoveryFragment.getClass().getSimpleName());
            chatFragment = (ChatFragment) fragmentManager.findFragmentByTag(chatFragment.getClass().getSimpleName());
            selfFragment = (SelfFragment) fragmentManager.findFragmentByTag(selfFragment.getClass().getSimpleName());
        }else{
            topicFragment = new TopicFragment();
            discoveryFragment = new DiscoveryFragment();
            chatFragment = new ChatFragment();
            selfFragment = new SelfFragment();
        }
       
        
        fragments.add(topicFragment);
        fragments.add(discoveryFragment);
        fragments.add(chatFragment);
        fragments.add(selfFragment);
        
        radioButtons = getChildRadions(); 

        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();

        for (int i = 0; i < fragments.size(); i++) {
            ft.add(R.id.fragmentContainer, fragments.get(i),fragments.get(i).getClass().getSimpleName());
            ft.hide(fragments.get(i));
        }
        ft.show(fragments.get(index));
        //ft.add(fragmentContentId, fragments.get(index));

        ft.commit();
        mainGroup.setOnCheckedChangeListener(this);
        checkFragment(index);
        
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


    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        //将radioButton筛选出来
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        for (int i = 0; i < radioButtons.size(); i++) {

            Fragment fragment = fragments.get(i);

            if (i > currentTab) {
                ft.setCustomAnimations(R.anim.slide_left_in, R.anim.slide_right_out);
            } else {
                ft.setCustomAnimations(R.anim.slide_right_in, R.anim.slide_left_out);
            }

            if (radioButtons.get(i).getId() == checkedId) {

                if(checkedId == currentTab){
                    return;
                }

                ft.show(fragment);
                currentTab = i;
            } else {
                ft.hide(fragment);
            }
        }

        ft.commitAllowingStateLoss();
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