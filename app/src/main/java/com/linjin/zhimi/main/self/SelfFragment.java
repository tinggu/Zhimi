package com.linjin.zhimi.main.self;


import android.os.Bundle;
import android.view.View;

import com.cyou.quick.mvp.MvpFragment;
import com.linjin.zhimi.model.card.GroupCard;
import com.linjin.zhimi.R;


public class SelfFragment 
        extends MvpFragment<SelfView, SelfPresenter> 
        implements SelfView{

   

    GroupCard card;

    int initTop = 100;

    private View[] items = new View[6];

    private int indexDown = -1, indexUp = -1, index = -1;

    @Override
    public SelfPresenter createPresenter() {
        return new SelfPresenter();
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_self;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
//        initView();
//        initSelfInfo();
    }

//    /**
//     * 个人信息
//     */
//    private void initSelfInfo() {
//        Realm realm = Realm.getDefaultInstance();
//        List<GroupCard> cards = CacheUtils.findAll(realm, GroupCard.class);
//        if (cards != null && cards.size() > 0) {
//            card = cards.get(0);
//            sdSelfHeader.setImageURI(Uri.parse(card.getImageUrl()));
//            tvSelfName.setText(card.getCardName());
//        }
//    }
//
//    private void initView() {
//        tvText.setAlpha(0f);
//        svContent.setHeader(image);
//        svContent.setOnScrollOffsetListener(this);
//        svContent.setOnSubViewTouchEvent(this);
//
//        items[0] = tvCardManager;
//        items[1] = tvInvite;
//        items[2] = tvNotice;
//        items[3] = tvNoticeInfo;
//        items[4] = tvLocation;
//        items[5] = tvExit;
//    }
//
//    @OnClick({R.id.tv_invite, R.id.tv_card_manager, R.id.tv_exit, R.id.tv_notice, R.id.tv_notice_info, R.id.tv_location})
//    public void onItemOnclick(View view) {
//        Intent intent;
//        switch (view.getId()) {
//            case R.id.tv_card_manager:
//                intent = new Intent(getActivity(), CardManageActivity.class);
//                startActivity(intent);
//                break;
//            case R.id.tv_invite:
//                IntentStarter.showGroupSelect(getActivity());
//                break;
//            case R.id.tv_notice:
//                IntentStarter.showNoticeCreate(getActivity(), 147);
//                break;
//            case R.id.tv_notice_info:
//                IntentStarter.showNoticeInfo(getActivity(), 147, 10);
//                break;
//            case R.id.tv_location:
//                Intent i = new Intent(getActivity(), LocationActivity.class);
//                startActivity(i);
//                break;
//            case R.id.tv_exit:
//                //退出当前账号逻辑
////                GeTuiUtils.unBindPush(getActivity(), DataCenter.getInstance().getUserID());
//
//                break;
//        }
//    }
//
//    @Override
//    public void onScrollOffset(boolean pull, int offset) {
//        if (!pull) {
//            tvText.setAlpha(0f);
//            return;
//        }
//        float alpha = 0f;
//        int top = initTop + offset / 2;
//        if (offset < 30) {
//            tvText.setAlpha(0f);
//        } else {
//            alpha = offset * 1f / 100;
//        }
//        tvText.setAlpha(alpha);
//        tvText.layout(0, top, tvText.getWidth(), top + tvText.getHeight());
//    }
//
//    @Override
//    public void onPushScrollOffset(int pushOffset) {
//        if (pushOffset > ScreenUtils.dip2px(130)) {
//            tvTopbar.setText(card.getCardName());
//        } else {
//            tvTopbar.setText(getResources().getText(R.string.person));
//        }
//    }
//
//    @Override
//    public void onSubView(int action, MotionEvent ev) {
//        if (MotionEvent.ACTION_DOWN == action) {
//            for (int i = 0; i < items.length; i++) {
//                if (inViewRect(items[i], ev)) {
//                    indexDown = i;
//                }
//            }
//        }
//        if (MotionEvent.ACTION_UP == action) {
//            for (int i = 0; i < items.length; i++) {
//                if (inViewRect(items[i], ev)) {
//                    indexUp = i;
//                    LogUtils.i("onSubView", "indexUp " + indexUp + indexDown);
//                }
//            }
//            if (indexDown == indexUp && indexDown != -1) {
//                index = indexDown;
//            }
//            LogUtils.i("onSubView", " " + indexDown + " " + indexUp + index);
//            if (-1 != index) {
//                onItemOnclick(items[index]);
//            }
//            indexDown = indexUp = index = -1;
//        }
//    }
//
//    /**
//     * 触点是否在view范围内
//     *
//     * @return
//     */
//    public boolean inViewRect(final View v, final MotionEvent ev) {
//
//        float x = ev.getX();
//        float y = ev.getY();
//        int[] location = new int[2];
//        v.getLocationOnScreen(location);
//        float left = v.getLeft();
//        float right = left + v.getWidth();
//        float top = v.getTop() + llTtems.getTop();
//        float bottom = top + v.getHeight();
//
//        if (x > left && x < right && y > top && y < bottom) {
//            return true;
//        }
//        return false;
//    }
}
