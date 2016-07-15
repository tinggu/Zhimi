package com.cyou.common.base.view.viewholder;


import android.net.Uri;
import android.text.TextUtils;
import android.view.View;

import com.facebook.drawee.view.SimpleDraweeView;
import com.linjin.zhimi.R;

import butterknife.BindView;


public class CommonHeaderVH extends BaseViewHolder<String> {

    @BindView(R.id.image)
    public SimpleDraweeView mSimpleDraweeView;


    public static final int LAYOUT_TYPE = R.layout.common_list_header_view;
//    public static final int LAYOUT_TYPE = 1;

    public CommonHeaderVH(View view) {
        super(view);
//        mSimpleDraweeView.setAspectRatio(1.33f); 
    }

    @Override
    public int getType() {
        return LAYOUT_TYPE;
    }

    @Override
    public void onBindViewHolder(View view, String url) {
        Uri uri;
        if (TextUtils.isEmpty(url)) {
            uri = Uri.parse("res://com.linjin.zhimi/" + R.mipmap.fx_intro);
        } else {
            uri = Uri.parse(url);
        }
        mSimpleDraweeView.setImageURI(uri);
    }
}