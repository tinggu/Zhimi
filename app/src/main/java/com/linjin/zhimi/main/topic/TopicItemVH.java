package com.linjin.zhimi.main.topic;

import android.view.View;
import android.widget.TextView;

import com.cyou.common.base.view.viewholder.BaseViewHolder;
import com.facebook.drawee.view.SimpleDraweeView;
import com.linjin.zhimi.R;
import com.linjin.zhimi.model.topic.Topic;

import butterknife.BindView;

/**
 * Description:
 * Copyright  : Copyright (c) 2015
 * Company    : 北京畅游天下网络科技有限公司
 * Author     : wangjia_bi
 * Date       : 2016/6/6 15:59
 */
public class TopicItemVH extends BaseViewHolder<Topic> {

    @BindView(R.id.iv_avatar)
    SimpleDraweeView ivAvatar;
    
    @BindView(R.id.tv_thumb)
    TextView tvThumb;
    
    @BindView(R.id.tv_title)
    TextView tvTitle;
    
    @BindView(R.id.tv_answer)
    TextView tvAnswer;

    public TopicItemVH(View view) {
        super(view);
    }

     

    @Override
    public int getType() {
        return R.layout.item_topic;
    }

    @Override
    public void onBindViewHolder(View view, final Topic mSubject) {
        
    }

}
