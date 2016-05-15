package com.linjin.zhimi.main;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.linjin.zhimi.R;

import java.util.ArrayList;
import java.util.List;


public class FragmentTabAdapter implements RadioGroup.OnCheckedChangeListener {

    private FragmentActivity fragmentActivity;
    private List<Fragment> fragments;
    private int fragmentContentId;
    private RadioGroup radioGroup; // 用于切换tab
    private int currentTab; // 当前Tab页面索引

    public FragmentTabAdapter(FragmentActivity fragmentActivity, List<Fragment> fragments,
                              int index, int fragmentContentId, RadioGroup rgs) {
        this.fragmentActivity = fragmentActivity;
        this.fragments = fragments;
        this.fragmentContentId = fragmentContentId;
        this.radioGroup = rgs;

        FragmentTransaction ft = fragmentActivity.getSupportFragmentManager().beginTransaction();
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
                rgs.check(R.id.dock_1);
                break;
            case 1:
                rgs.check(R.id.dock_2);
                break;
            case 2:
                rgs.check(R.id.dock_3);
                break;
            case 3:
                rgs.check(R.id.dock_4);
                break;
        }
//        rgs.setOnCheckedChangeListener(this);
    }

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
        FragmentTransaction ft = fragmentActivity.getSupportFragmentManager().beginTransaction();
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
        for (int i = 0; i < radioGroup.getChildCount(); i++) {
            View v = radioGroup.getChildAt(i);
            if (v instanceof RadioButton) {
                childs.add(v);
            }
        }
        return childs;
    }

}
