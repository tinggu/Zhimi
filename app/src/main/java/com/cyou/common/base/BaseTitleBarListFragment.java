package com.cyou.common.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.cyou.common.base.view.layout.TRecyclerView;
import com.cyou.common.base.view.viewholder.BaseViewHolder;
import com.cyou.common.utils.KeyboardUtils;
import com.cyou.common.utils.TUtil;
import com.cyou.quick.QuickFragment;
import com.linjin.zhimi.C;
import com.linjin.zhimi.R;
import com.linjin.zhimi.widget.TopActionBar;

import butterknife.BindView;


public class BaseTitleBarListFragment extends QuickFragment {

    @BindView(R.id.topActionBar)
  public   TopActionBar topActionBar;
    
    @BindView(R.id.trecyclerView)
    public TRecyclerView typeRecyclerView;

    /**
     * @param vh 传入VH的类名
     * @return
     */
    public static BaseTitleBarListFragment newInstance(Class<? extends BaseViewHolder> vh, String title) {
        Bundle arguments = new Bundle();
        arguments.putString(C.VH_CLASS, vh.getCanonicalName());
        arguments.putString(C.TYPE, title);
        BaseTitleBarListFragment fragment = new BaseTitleBarListFragment();
        fragment.setArguments(arguments);
        return fragment;
    }

//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//        super.onCreateView(inflater,container,savedInstanceState);
//        
//        tRecyclerView = new TRecyclerView(getContext());
//        tRecyclerView.setView(TUtil.forName(getArguments().getString(C.VH_CLASS)));
//        return tRecyclerView;
//    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        typeRecyclerView.setView(TUtil.forName(getArguments().getString(C.VH_CLASS)));
        topActionBar.setTitle(getArguments().getString(C.TITLE));
        topActionBar.hideAction();
//        topActionBar.setActionImageResource(R.mipmap.topic_sidebar);
        topActionBar.setBackListener(new TopActionBar.BackListener() {
            @Override
            public void onBack() {
                KeyboardUtils.callBackKeyClick();
            }
        });
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.common_title_list_fragment;
    }


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (typeRecyclerView != null) typeRecyclerView.fetch();
    }

//    public void setHeadView(Class<? extends BaseViewHolder> vh) {
//        tRecyclerView.setHeadView(vh);
//    }
}
