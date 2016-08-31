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
    public TopActionBar topActionBar;

    @BindView(R.id.trecyclerView)
    public TRecyclerView tRecyclerView;

    private TRecyclerView typeRecyclerView;
    Class<? extends BaseViewHolder> headVH;
    private int headType;
    private Object headData;
    private ListPresenter listPresenter;

    /**
     * @param vh 传入VH的类名
     * @return
     */
    public static BaseTitleBarListFragment newInstance(Class<? extends BaseViewHolder> vh, String title,
                                                        ListPresenter listPresenter) {
        Bundle arguments = new Bundle();
        arguments.putString(C.VH_CLASS, vh.getCanonicalName());
        arguments.putString(C.TITLE, title);
        BaseTitleBarListFragment fragment = new BaseTitleBarListFragment();
        fragment.setArguments(arguments); 
        fragment.setPresenter(listPresenter);
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
        tRecyclerView.setView(TUtil.forName(getArguments().getString(C.VH_CLASS)), listPresenter);
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
        if (tRecyclerView != null) tRecyclerView.fetch();
    }

    public void setHeadView(Class<? extends BaseViewHolder> vh, int headType, Object headData) {
        this.headVH = vh;
        this.headType = headType;
        this.headData = headData;
    }

    public void setPresenter(ListPresenter listPresenter) {
        this.listPresenter = listPresenter;
    }
}
