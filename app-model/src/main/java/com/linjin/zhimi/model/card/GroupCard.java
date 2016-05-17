package com.linjin.zhimi.model.card;

import com.linjin.zhimi.model.SortToken;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;
import io.realm.annotations.Ignore;
import io.realm.annotations.PrimaryKey;

/**
 * Description: 群名片
 * Copyright  : Copyright (c) 2015
 * Company    : 北京畅游天下网络科技有限公司
 * Author     : liujianguang
 * Date       : 2016/1/15
 **/

public class GroupCard extends RealmObject {

    /**
     * 用户id
     */
    @Expose
    @SerializedName("userId")
    private long userId;

    /**
     * 身份id
     */
    @Expose
    @SerializedName("identityId")
    @PrimaryKey
    private long identityId;

    /**
     * 名片名称
     */
    @Expose
    @SerializedName("name")
    private String cardName;
    /**
     * 出生日期
     */
    @Expose
    @SerializedName("age")
    private int age;
    /**
     * 性别 1：男 2：女
     */
    @Expose
    @SerializedName("sex")
    private byte sex;
    /**
     * 说明
     */
    @Expose
    @SerializedName("des")
    private String des;
    /**
     * 头像
     */
    @Expose
    @SerializedName("avatar")
    private String imageUrl;

    /**
     * 用户默认身份类型 0：默认身份 1：非默认
     */
    @Expose
    @SerializedName("isDefault")
    private byte isDefault;

    @Ignore
    public String sortLetters; //显示数据拼音的首字母
    @Ignore
    public SortToken sortToken = new SortToken();

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public long getIdentityId() {
        return identityId;
    }

    public void setIdentityId(long identityId) {
        this.identityId = identityId;
    }

    public String getCardName() {
        return cardName;
    }

    public void setCardName(String cardName) {
        this.cardName = cardName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public byte getSex() {
        return sex;
    }

    public void setSex(byte sex) {
        this.sex = sex;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public byte getIsDefault() {
        return isDefault;
    }

    public void setIsDefault(byte isDefault) {
        this.isDefault = isDefault;
    }

    @Override
    public String toString() {
        return "GroupCard{" +
                "cardName='" + cardName + '\'' +
                ", age=" + age +
                ", sex=" + sex +
                ", des='" + des + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", identityId='" + identityId + '\'' +
                ", isDefault=" + isDefault +
                '}';
    }

}
