package com.linjin.zhimi.model.vote;

import com.cyou.common.entity.BaseBean;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Description:
 * Copyright  : Copyright (c) 2015
 * Company    : 北京畅游天下网络科技有限公司
 * Author     : liujianguang
 * Date       : 2016/3/30
 **/

public class VoteCreate extends BaseBean {

    /**
     * 投票id
     */
    @Expose
    @SerializedName("voteId")
    public long voteId;

}
