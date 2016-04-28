package com.cyou.band.model.group.create;

/**
 * Description: 群创建时的封面
 * Copyright  : Copyright (c) 2015
 * Company    : 北京畅游天下网络科技有限公司
 * Author     : liujianguang
 * Date       : 2016/1/15
 **/

public class GroupCover {
    /**
     * item类型，第一个为相册入口 0
     */
    public int type;
    /**
     * 封面图片资源id（本地，但同服务器相同）
     */
    public int coverResId;
    /**
     * 封面图片url
     */
    public String coverKey;
    /**
     * 封面名称
     */
    public String coverName;
    /**
     * 选中状态
     */
    public boolean isSelect;

}
