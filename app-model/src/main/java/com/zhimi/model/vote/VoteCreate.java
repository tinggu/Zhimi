package com.zhimi.model.vote;

import com.zhimi.model.BaseModel;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Description:
 * Copyright  : Copyright (c) 2015
 * Company    : 北京畅游天下网络科技有限公司
 * Author     : liujianguang
 * Date       : 2016/3/30
 **/

public class VoteCreate extends BaseModel {

    /**
     * 投票id
     */
    @Expose
    @SerializedName("voteId")
    public long voteId;

}
