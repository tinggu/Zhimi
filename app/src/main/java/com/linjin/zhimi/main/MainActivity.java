package com.linjin.zhimi.main;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.cyou.app.mvp.BaseMvpActivity;
import com.cyou.quick.mvp.MvpBasePresenter;
import com.cyou.quick.mvp.MvpPresenter;
import com.linjin.zhimi.R;
import com.linjin.zhimi.main.discovery.DiscoveryFragment;
import com.linjin.zhimi.main.menu.MenuEventCollent;
import com.linjin.zhimi.main.menu.MenuEventDraft;
import com.linjin.zhimi.main.menu.MenuEventLast;
import com.linjin.zhimi.main.msg.MsgFragment;
import com.linjin.zhimi.main.self.SelfFragment;
import com.linjin.zhimi.main.topic.TopicFragment;
import com.linjin.zhimi.topic.TopicActivity;
import com.linjin.zhimi.topic.TopicFlag;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;

import butterknife.BindView;
import me.yokeyword.fragmentation.SupportFragment;


public class MainActivity extends BaseMvpActivity
        implements RadioGroup.OnCheckedChangeListener {

    private static final String TAG = "MainActivity";

    public static final int FIRST = 0;
    public static final int SECOND = 1;
    public static final int THIRD = 2;
    public static final int FOURTH = 3;


    @BindView(R.id.main_group)
    public RadioGroup mainGroup;

    private SupportFragment[] mFragments = new SupportFragment[4];

//    private List<Fragment> fragments;
//
//    private TopicFragment topicFragment;
//    private DiscoveryFragment discoveryFragment;
//    private MsgFragment msgFragment;
//    private SelfFragment selfFragment;

    private int prePosition; // 当前Tab页面索引

//    private ArrayList<View> radioButtons;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.i(TAG, "onCreate: ");
        EventBus.getDefault().register(this);
        initView(savedInstanceState);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
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
        outState.putInt("index", prePosition);
        Log.i(TAG, "onSaveInstanceState: ");
    }


    private void initView(Bundle savedInstanceState) {

//        //定位底部tab,默认定位0，即群列表页
//        int index = getIntent().getIntExtra("index", 0);
        
        if (savedInstanceState == null) {
            Log.i(TAG, "initView: savedInstanceState == null");
            
            
            mFragments[FIRST] = TopicFragment.newInstance();
            mFragments[SECOND] = DiscoveryFragment.newInstance();
            mFragments[THIRD] = MsgFragment.newInstance();
            mFragments[FOURTH] = SelfFragment.newInstance();

            loadMultipleRootFragment(R.id.fl_container, FIRST,
                    mFragments[FIRST],
                    mFragments[SECOND],
                    mFragments[THIRD],
                    mFragments[FOURTH]);
        } else {
            Log.i(TAG, "initView: savedInstanceState != null");
            // 这里库已经做了Fragment恢复,所有不需要额外的处理了, 不会出现重叠问题

            // 这里我们需要拿到mFragments的引用,也可以通过getSupportFragmentManager.getFragments()自行进行判断查找(效率更高些),用下面的方法查找更方便些
            mFragments[FIRST] = findFragment(TopicFragment.class);
            mFragments[SECOND] = findFragment(DiscoveryFragment.class);
            mFragments[THIRD] = findFragment(MsgFragment.class);
            mFragments[FOURTH] = findFragment(SelfFragment.class);
        }


        mainGroup.setOnCheckedChangeListener(this);
        
//        checkFragment(index);

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


    private int getClickId(int checkedId) {
        if (checkedId == R.id.dock_1) {
            return FIRST;
        } else if (checkedId == R.id.dock_2) {
            return SECOND;
        } else if (checkedId == R.id.dock_3) {
            return THIRD;
        } else if (checkedId == R.id.dock_4) {
            return FOURTH;
        }
        return FIRST;
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        
        int position = getClickId(checkedId);
        showHideFragment(mFragments[position], mFragments[prePosition]);
        prePosition = position;
//        //将radioButton筛选出来
//        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
//        for (int i = 0; i < radioButtons.size(); i++) {
//
//            Fragment fragment = fragments.get(i);
//
//            if (i > prePosition) {
//                ft.setCustomAnimations(R.anim.slide_left_in, R.anim.slide_right_out);
//            } else {
//                ft.setCustomAnimations(R.anim.slide_right_in, R.anim.slide_left_out);
//            }
//
//            if (radioButtons.get(i).getId() == checkedId) {
//
//                if (checkedId == prePosition) {
//                    return;
//                }
//
//                ft.show(fragment);
//                prePosition = i;
//            } else {
//                ft.hide(fragment);
//            }
//        }
//
//        ft.commitAllowingStateLoss();
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

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventLast(MenuEventLast eventLast) {
        Intent intent = new Intent(this, TopicActivity.class);
        intent.putExtra(TopicFlag.FLAG, TopicFlag.FLAG_LAST);
        startActivity(intent);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventCollent(MenuEventCollent eventCollent) {
        Intent intent = new Intent(this, TopicActivity.class);
        intent.putExtra(TopicFlag.FLAG, TopicFlag.FLAG_COLLENT);
        startActivity(intent);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventDraft(MenuEventDraft eventDraft) {
        Intent intent = new Intent(this, TopicActivity.class);
        intent.putExtra(TopicFlag.FLAG, TopicFlag.FLAG_DRAFT);
        startActivity(intent);
    }
}