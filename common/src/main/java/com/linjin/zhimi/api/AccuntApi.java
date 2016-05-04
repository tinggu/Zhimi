package com.linjin.zhimi.api;

import com.cyou.zhimi.model.BaseModel;
import com.cyou.zhimi.model.account.User;
import com.cyou.zhimi.model.account.UserModel;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Description:
 * Copyright  : Copyright (c) 2015
 * Company    : 北京畅游天下网络科技有限公司
 * Author     : liujianguang
 * Date       : 2016/1/4
 **/
public interface AccuntApi {

    /**
     * 用户登陆
     *
     * @param mobileNum 手机号码
     * @param password  用户密码
     * @return
     */
    @FormUrlEncoded
    @POST("user/login")
    Observable<UserModel> login(@Field("mobileNum") String mobileNum,
                           @Field("password") String password,
                           @Field("devicetoken") String devicetoken
    );

    /**
     * 创建用户
     *
     * @param code      手机验证码
     * @param mobileNum 手机号码
     * @param password  用户密码
     * @return
     */
    @FormUrlEncoded
    @POST("/createUser")
    Observable<UserModel> register(@Field("code") String code,
                              @Field("mobileNum") String mobileNum,
                              @Field("password") String password
    );


    byte TYPE_REGISTER = 1;
    byte TYPE_FIND_PASSWORD = 2;

    /**
     * 获取验证码
     *
     * @param mobileNum 手机号码
     * @param type      类型 1：注册2：找回密码
     * @return
     */
    @FormUrlEncoded
    @POST("/getCheckCode")
    Observable<BaseModel> getCheckCode(@Field("mobileNum") String mobileNum,
                                       @Field("type") byte type);


    @POST("user/regsms")
    Observable<BaseModel> checkCode(@Field("appkey") String appkey,
                                    @Field("phone") String phone,
                                    @Field("zone") String zone,
                                    @Field("code") String code);

    /**
     * 找回密码
     *
     * @param mobileNum 手机号码
     * @param password  用户密码
     * @param code      手机验证码
     * @return
     */
    @FormUrlEncoded
    @POST("/findPassword")
    Observable<UserModel> findPassword(@Field("mobileNum") String mobileNum,
                                       @Field("newPassword") String password,
                                       @Field("code") String code);

    /**
     * 修改密码
     *
     * @param oldPassword 原密码
     * @param newPassword 新密码
     * @return
     */
    @FormUrlEncoded
    @POST("/changePassword")
    Observable<UserModel> changePassword(@Field("oldPassword") String oldPassword,
                                    @Field("newPassword") String newPassword);
}
