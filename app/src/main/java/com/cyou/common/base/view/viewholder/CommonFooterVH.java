package com.cyou.common.base.view.viewholder;

import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.linjin.zhimi.R;

import butterknife.BindView;


public class CommonFooterVH extends BaseViewHolder<Object> {

    @BindView(R.id.progressbar)
    public ProgressBar progressbar;

    @BindView(R.id.tv_state)
    public TextView tv_state;

    public static final int LAYOUT_TYPE = R.layout.common_list_footer_view;
//    public static final int LAYOUT_TYPE = 1;

    public CommonFooterVH(View view) {
        super(view);
//        tv_state = (TextView) view.findViewById(R.id.tv_state);
//        progressbar = (ProgressBar) view.findViewById(R.id.progressbar);
    }

    @Override
    public int getType() {
        return LAYOUT_TYPE;
    }

    @Override
    public void onBindViewHolder(View view, Object o) {
        boolean isHasMore = (null != o);
        progressbar.setVisibility(isHasMore ? View.VISIBLE : View.GONE);
        tv_state.setText(isHasMore ? "正在加载" : "已经到底");
    }
}