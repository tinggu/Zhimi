package com.linjin.zhimi.main.self;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.cyou.quick.mvp.MvpFragment;
import com.linjin.zhimi.R;
import com.linjin.zhimi.edit.EditActivity;
import com.linjin.zhimi.widget.TopActionBar;

import butterknife.BindView;
import butterknife.OnClick;


public class SelfFragment
        extends MvpFragment<SelfView, SelfPresenter>
        implements SelfView {

    @BindView(R.id.topActionBar)
    TopActionBar topActionBar;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_zmnumber)
    TextView tvZmnumber;
    @BindView(R.id.tv_local)
    TextView tvLocal;
    
    @BindView(R.id.ll_self)
    LinearLayout llSelf;
    @BindView(R.id.ll_people)
    LinearLayout llPeople;
    @BindView(R.id.tv_thumb)
    TextView tvThumb;
    @BindView(R.id.tv_watch)
    TextView tvWatch;
    @BindView(R.id.tv_watched)
    TextView tvWatched;
    @BindView(R.id.tv_comment)
    TextView tvComment;
    @BindView(R.id.tv_answer_number)
    TextView tvAnswerNumber;
    @BindView(R.id.item_my_answer)
    LinearLayout itemMyAnswer;
    @BindView(R.id.tv_topic_number)
    TextView tvTopicNumber;
    @BindView(R.id.item_watch_topic)
    LinearLayout itemWatchTopic;
    @BindView(R.id.item_date_talent)
    LinearLayout itemDateTalent;
    @BindView(R.id.item_my_order)
    LinearLayout itemMyOrder;
    @BindView(R.id.btn_add_skill)
    Button btnAddSkill;


//    LatestPresenter

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView();
    }

    private void initView() {
        topActionBar.setTitle(R.string.radio_dock_4);
        topActionBar.hideBack();
        topActionBar.hideAction();
//        topActionBar.setActionImageResource(R.mipmap.topic_sidebar);

    }

    @Override
    public SelfPresenter createPresenter() {
        return new SelfPresenter();
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_self;
    }
    
//    @BindView(R.id.btn_edit)
//    Button btnEdit;
//    @BindView(R.id.btn_setting)
//    Button btnSetting;
    
    @OnClick({R.id.item_my_answer, R.id.item_watch_topic, R.id.item_date_talent, R.id.item_my_order, 
            R.id.btn_add_skill, R.id.btn_edit, R.id.btn_setting})
    public void onItemOnclick(View view) {
        Intent intent;
        switch (view.getId()) {
            case R.id.item_my_answer:
//                intent = latest Intent(getActivity(), CardManageActivity.class);
//                startActivity(intent);
                break;
            case R.id.item_watch_topic:
//                IntentStarter.showGroupSelect(getActivity());
                break;
            case R.id.item_date_talent:
//                IntentStarter.showNoticeCreate(getActivity(), 147);
                break;
            case R.id.item_my_order:
//                IntentStarter.showNoticeInfo(getActivity(), 147, 10);
//                break;
            case R.id.btn_edit:
                intent =  new Intent(getActivity(), EditActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_setting:
                
                break;
        }
    } 
    
}
