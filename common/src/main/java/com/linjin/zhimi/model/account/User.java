package com.linjin.zhimi.model.account;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;

/**
 * Description:
 * Copyright  : Copyright (c) 2015
 * Company    : 北京畅游天下网络科技有限公司
 * Author     : liujianguang
 * Date       : 2016/1/5 18:45
 */
public class User extends RealmObject {
    //    private static final long serialVersionUID = 1L;
    @Expose
    @SerializedName("uid")
    private String uid;

    @Expose
    @SerializedName("name")
    private String name;

    @Expose
    @SerializedName("sex")
    private int sex;

    @Expose
    @SerializedName("age")
    private int age;

    @Expose
    @SerializedName("location")
    private String location;

    @Expose
    @SerializedName("birthday")
    private String birthday;

    @Expose
    @SerializedName("title")
    private String title;

    @Expose
    @SerializedName("avatar")
    private String avatar;

    @Expose
    @SerializedName("usercode")
    private String usercode;

    @Expose
    @SerializedName("good_tota")
    private int goodTota;

    @Expose
    @SerializedName("be_follow_total")
    private int beFollowTotal;

    @Expose
    @SerializedName("follow_total")
    private int followTotal;

    @Expose
    @SerializedName("praise_total")
    private int praiseTotal;

    @Expose
    @SerializedName("topic_collect_total")
    private int topicCollectTotal;

    @Expose
    @SerializedName("topic_answer_total")
    private int topicAnswerTotal;

    @Expose
    @SerializedName("vip")
    private int vip;

    @Expose
    @SerializedName("rel")
    private int rel;

    @Expose
    @SerializedName("ry_token")
    private String ryToken;

    @Expose
    @SerializedName("lnk_token")
    private String lnkToken;


    /**
     * 是否有默认身份  0:没有身份 1:有身份
     */
    @Expose
    @SerializedName("isIdentity")
    private byte isIdentity;

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getUsercode() {
        return usercode;
    }

    public void setUsercode(String usercode) {
        this.usercode = usercode;
    }

    public int getGoodTota() {
        return goodTota;
    }

    public void setGoodTota(int goodTota) {
        this.goodTota = goodTota;
    }

    public int getBeFollowTotal() {
        return beFollowTotal;
    }

    public void setBeFollowTotal(int beFollowTotal) {
        this.beFollowTotal = beFollowTotal;
    }

    public int getFollowTotal() {
        return followTotal;
    }

    public void setFollowTotal(int followTotal) {
        this.followTotal = followTotal;
    }

    public int getPraiseTotal() {
        return praiseTotal;
    }

    public void setPraiseTotal(int praiseTotal) {
        this.praiseTotal = praiseTotal;
    }

    public int getTopicCollectTotal() {
        return topicCollectTotal;
    }

    public void setTopicCollectTotal(int topicCollectTotal) {
        this.topicCollectTotal = topicCollectTotal;
    }

    public int getTopicAnswerTotal() {
        return topicAnswerTotal;
    }

    public void setTopicAnswerTotal(int topicAnswerTotal) {
        this.topicAnswerTotal = topicAnswerTotal;
    }

    public int getVip() {
        return vip;
    }

    public void setVip(int vip) {
        this.vip = vip;
    }

    public int getRel() {
        return rel;
    }

    public void setRel(int rel) {
        this.rel = rel;
    }

    public String getRyToken() {
        return ryToken;
    }

    public void setRyToken(String ryToken) {
        this.ryToken = ryToken;
    }

    public String getLnkToken() {
        return lnkToken;
    }

    public void setLnkToken(String lnkToken) {
        this.lnkToken = lnkToken;
    }

    public byte getIsIdentity() {
        return isIdentity;
    }

    public void setIsIdentity(byte isIdentity) {
        this.isIdentity = isIdentity;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }
}
