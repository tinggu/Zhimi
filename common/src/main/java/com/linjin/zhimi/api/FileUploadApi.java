package com.linjin.zhimi.api;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

/**
 * Description:
 * Copyright  : Copyright (c) 2015
 * Company    : 北京畅游天下网络科技有限公司
 * Author     : tinggu
 * Date       : 2016/5/19 10:39
 */
public interface FileUploadApi {
    /**
     * 上传一张图片
     *
     * @param description
     * @param imgs
     * @return
     */
    @Multipart
    @POST("/upload")
    Call<String> uploadImage(@Part("fileName") String description,
                             @Part("file\"; filename=\"image.png\"") RequestBody imgs);

    /**
     * 上传三张图片
     *
     * @param description
     * @param imgs
     * @param imgs1
     * @param imgs3
     * @return
     */
    @Multipart
    @POST("/upload")
    Call<String> uploadImage(@Part("fileName") String description,
                             @Part("file\"; filename=\"image.png\"") RequestBody imgs,
                             @Part("file\"; filename=\"image.png\"") RequestBody imgs1,
                             @Part("file\"; filename=\"image.png\"") RequestBody imgs3);
}
