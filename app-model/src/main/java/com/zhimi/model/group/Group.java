package com.zhimi.model.group;

import com.zhimi.model.SortToken;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

import io.realm.RealmObject;
import io.realm.annotations.Ignore;
import io.realm.annotations.PrimaryKey;

/**
 * Description:
 * Copyright  : Copyright (c) 2015
 * Company    : 北京畅游天下网络科技有限公司
 * Author     : liujianguang
 * Date       : 2015/12/29
 **/

public class Group extends RealmObject implements Serializable {

    /**
     * 群id
     */
    @Expose
    @SerializedName("groupId")
    @PrimaryKey
    private long groupId;

    /**
     * 群名称
     */
    @Expose
    @SerializedName("name")
    private String name;

    /**
     * 群创建时间
     */
    @Expose
    @SerializedName("createTime")
    private long createTime;

    /**
     * 群封面图片
     */
    @Expose
    @SerializedName("bgImg")
    private String imageUrl;

    /**
     * 新消息数
     */
    @Expose
    @SerializedName("count")
    private String count;

    //公开度 1：公开 2：半公开 3：不公开
    @Expose
    @SerializedName("openType")
    private byte openType;

    //分类
    @Expose
    @SerializedName("category")
    private int category;

    /**
     * 群描述
     */
    @Expose
    @SerializedName("des")
    private String des;

    /**
     * 成员数量
     */
    @Expose
    @SerializedName("memberCount")
    private int memberCount;

    /**
     * 头像
     */
    @Expose
    @SerializedName("avatar")
    private String avatar;

    /**
     * 群类型 1：我创建群2：我加入的群
     */
    @Expose
    @SerializedName("groupType")
    private byte groupType;

    /**
     * 搜索类型 1:可以搜索到 0：搜索不到
     */
    @Expose
    @SerializedName("searchType")
    @Ignore
    public byte searchType;

    /**
     * 审核类型 1：需要审核 0：不需要审核
     */
    @Expose
    @SerializedName("checkType")
    @Ignore
    public byte checkType;

    //群主名字
    @Expose
    @SerializedName("identityName")
    private String identityName;

    @Expose
    @SerializedName("objectId")
    private String conversationId;

    /**
     * 身份id
     */
    @Expose
    @SerializedName("identityId")
    private long identityId;

    public long getGroupId() {
        return groupId;
    }

    public void setGroupId(long groupId) {
        this.groupId = groupId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public byte getOpenType() {
        return openType;
    }

    public void setOpenType(byte openType) {
        this.openType = openType;
    }

    public int getCategory() {
        return category;
    }

    public void setCategory(int category) {
        this.category = category;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    public int getMemberCount() {
        return memberCount;
    }

    public void setMemberCount(int memberCount) {
        this.memberCount = memberCount;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public byte getGroupType() {
        return groupType;
    }

    public void setGroupType(byte groupType) {
        this.groupType = groupType;
    }

    public String getIdentityName() {
        return identityName;
    }

    public void setIdentityName(String identityName) {
        this.identityName = identityName;
    }

    public String getConversationId() {
        return conversationId;
    }

    public void setConversationId(String conversationId) {
        this.conversationId = conversationId;
    }

    public long getIdentityId() {
        return identityId;
    }

    public void setIdentityId(long identityId) {
        this.identityId = identityId;
    }

    @Ignore
    public SortToken sortToken = new SortToken();

//    @Override
//    public int describeContents() {
//        return 0;
//    }
//
//    @Override
//    public void writeToParcel(Parcel dest, int flags) {
//        dest.writeLong(groupId);
//        dest.writeString(name);
//        dest.writeLong(createTime);
//        dest.writeString(imageUrl);
//        dest.writeString(count);
//
//        dest.writeByte(openType);
//
//        dest.writeInt(category);
//        dest.writeString(des);
//        dest.writeInt(memberCount);
//        dest.writeString(avatar);
//
//        dest.writeByte(groupType);
//        dest.writeByte(searchType);
//        dest.writeByte(checkType);
//
//        dest.writeString(identityName);
//        dest.writeString(conversationId);
//        dest.writeLong(identityId);
//
//    }
//    
//    @Ignore
//    public static final Parcelable.Creator<Group> CREATOR = new Creator<Group>() {
//        @Override
//        public Group[] newArray(int size) {
//            return new Group[size];
//        }
//
//        @Override
//        public Group createFromParcel(Parcel in) {
//            return new Group(in);
//        }
//    };
//
//    public Group(Parcel in) {
//        groupId = in.readLong();
//        name = in.readString();
//        createTime = in.readLong();
//        imageUrl = in.readString();
//        count = in.readString();
//
//        openType = in.readByte();
//        category = in.readInt();
//        des = in.readString();
//        memberCount = in.readInt();
//        avatar = in.readString();
//
//        groupType = in.readByte();
//        searchType = in.readByte();
//        checkType = in.readByte();
//
//        identityName = in.readString();
//        conversationId = in.readString();
//        identityId = in.readLong();
//    }


    @Override
    public String toString() {
        return "Group{" +
                "groupId=" + groupId +
                ", name='" + name + '\'' +
                ", createTime=" + createTime +
                ", imageUrl='" + imageUrl + '\'' +
                ", count='" + count + '\'' +
                ", openType=" + openType +
                ", category=" + category +
                ", des='" + des + '\'' +
                ", memberCount=" + memberCount +
                ", avatar='" + avatar + '\'' +
                ", groupType=" + groupType +
                ", searchType=" + searchType +
                ", checkType=" + checkType +
                ", identityName='" + identityName + '\'' +
                ", conversationId='" + conversationId + '\'' +
                ", identityId=" + identityId +
                ", sortToken=" + sortToken +
                '}';
    }
}
