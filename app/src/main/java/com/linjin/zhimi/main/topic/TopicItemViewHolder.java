package com.linjin.zhimi.main.topic;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.linjin.zhimi.R;
import com.linjin.zhimi.base.viewholder.CommonViewHolder;
import com.linjin.zhimi.model.topic.Topic;

import butterknife.BindView;

/**
 * Description:
 * Copyright  : Copyright (c) 2015
 * Company    : 北京畅游天下网络科技有限公司
 * Author     : wangjia_bi
 * Date       : 2016/6/6 15:59
 */
public class TopicItemViewHolder extends CommonViewHolder<Topic> {

    @BindView(R.id.iv_avatar)
    SimpleDraweeView ivAvatar;
    @BindView(R.id.tv_thumb)
    TextView tvThumb;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_answer)
    TextView tvAnswer;

    public TopicItemViewHolder(Context context, ViewGroup root) {
        super(context, root, R.layout.item_topic);
    }

    @Override
    public void bindData(Topic topic) {

    }

    @Override
    public int getType() {
        return 1;
    }

//    public static ViewHolderCreator HOLDER_CREATOR() {
//        return new ViewHolderCreator() {
//
//            @Override
//            public CommonViewHolder createView(ViewGroup parent, int viewType) {
//                return new TopicItemViewHolder(parent.getContext(), parent);
//            }
//        };
//    }

    @Override
    public CommonViewHolder createView(ViewGroup parent, int viewType) {
        return new TopicItemViewHolder(parent.getContext(), parent);
    }
}
