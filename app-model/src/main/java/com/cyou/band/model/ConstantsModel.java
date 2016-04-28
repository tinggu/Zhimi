package com.cyou.band.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

/**
 * Description:
 * Copyright  : Copyright (c) 2015
 * Company    : 北京畅游天下网络科技有限公司
 * Author     : wangjia_bi
 * Date       : 2016/4/15 12:14
 */
public class ConstantsModel {

    public String getPrefixImgUrl() {
        return prefixImgUrl;
    }

    public void setPrefixImgUrl(String prefixImgUrl) {
        this.prefixImgUrl = prefixImgUrl;
    }

    /**
     * 七牛图片前缀
     */
    @Expose
    @SerializedName("prefixImgUrl")
    private String prefixImgUrl;

    public ConstantsModel(DataInputStream in) {

        try {
            prefixImgUrl = in.readUTF();
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void writeToFile(DataOutputStream out) {
        try {
            out.writeUTF(prefixImgUrl);
            out.flush();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
}
