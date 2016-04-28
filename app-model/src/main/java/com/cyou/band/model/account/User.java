package com.cyou.band.model.account;

import com.cyou.band.model.BaseModel;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

/**
 * Description:
 * Copyright  : Copyright (c) 2015
 * Company    : 北京畅游天下网络科技有限公司
 * Author     : liujianguang
 * Date       : 2016/1/5 18:45
 */
public class User extends BaseModel {
    //    private static final long serialVersionUID = 1L;
    @Expose
    @SerializedName("userId")
    private String userId;

    @Expose
    @SerializedName("token")
    private String token;

    @Expose
    @SerializedName("avatar")
    private String avatar;

    /**
     * 是否有默认身份  0:没有身份 1:有身份
     */
    @Expose
    @SerializedName("isIdentity")
    private byte isIdentity;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public byte getIsIdentity() {
        return isIdentity;
    }

    public void setIsIdentity(byte isHas) {
        this.isIdentity = isHas;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId='" + userId + '\'' +
                ", token='" + token + '\'' +
                ", avatar='" + avatar + '\'' +
                ", isIdentity=" + isIdentity +
                '}';
    }

    public User() {
        super();
    }

    public void writeToFile(DataOutputStream out) {
        try {
            out.writeUTF(userId);
            out.writeUTF(token);
            out.writeByte(isIdentity);
            out.flush();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public User(DataInputStream in) {
        this();
        try {
            userId = in.readUTF();
            token = in.readUTF();
            isIdentity = in.readByte();
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

//        bookName = in.readString();
//        author = in.readString();
//        publishDate = in.readInt();
    }
}
