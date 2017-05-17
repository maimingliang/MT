package com.elk.login.api.service;

import com.elk.base.LoginBeen;
import com.elk.base.WeiXinLoginBean;
import com.elk.login.CodeBean;
import com.elk.login.PhoneNumBindBean;
import com.elk.login.RegisterEntity;
import com.elk.login.WeixinPassWordBindBean;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import rx.Observable;

/**
 * /**
 * 类       名:
 * 说       明:
 * 修 改 记 录:
 * 版 权 所 有:   Copyright © 2017
 * 公       司:   深圳市旅联网络科技有限公司
 * version   0.1
 * date   2017/5/11
 * author   maimingliang
 */


public interface LoginSevice {

    /**
     * 登录
     *
     * @param phoneNo
     * @param passwd
     * @return
     */
    @FormUrlEncoded
    @POST("touristUser/login")
    Observable<LoginBeen> login(
            @Field("phoneNo") String phoneNo,
            @Field("passwd") String passwd
    );

    /**
     * 微信登录
     *
     * @param weChatOpenId
     * @param weChatUnionId
     * @return
     */
    @FormUrlEncoded
    @POST("touristUser/weChatLogin_1_0_0")
    Observable<WeiXinLoginBean> weChatLogin_1_0_0(
            @Field("weChatOpenId") String weChatOpenId,
            @Field("weChatUnionId") String weChatUnionId
    );


    /**
     * 微信绑定手机
     *
     * @param accessToken         y
     * @param weChatOpenId       y
     * @param weChatUnionId  y
     * @param code  n
     * @param phoneNo  N
     * @return
     */
    @FormUrlEncoded
    @POST("touristUser/bindPhoneNo_1_0_0")
    Observable<PhoneNumBindBean> bindPhoneNo_1_0_0(
            @Field("accessToken") String accessToken,
            @Field("weChatOpenId") String weChatOpenId,
            @Field("weChatUnionId") String weChatUnionId,
            @Field("code") String code,
            @Field("phoneNo") String phoneNo
    );

    /**
     * 微信绑定设置密码
     *
     * @param phoneNo
     * @param passwd
     * @return
     */
    @FormUrlEncoded
    @POST("touristUser/setPassword_1_0_0")
    Observable<WeixinPassWordBindBean> setPassword_1_0_0(
            @Field("phoneNo") String phoneNo,
            @Field("passwd") String passwd
    );

    /**
     * 【微信绑定】获取验证码
     *
     * @param phoneNo 手机号
     * @return
     */
    @FormUrlEncoded
    @POST("touristUser/bindGetOtpCode_1_0_0")
    Observable<CodeBean> bindGetOtpCode(
            @Field("phoneNo") String phoneNo
    );

    /**
     * 【注册】获取验证码
     *
     * @param phoneNo 手机号
     * @return
     */
    @FormUrlEncoded
    @POST("touristUser/registerGetOtpCode")
    Observable<CodeBean> registerGetOtpCode(
            @Field("phoneNo") String phoneNo
    );

    /**
     * 【注册过】获取验证码
     *
     * @param phoneNo 手机号
     * @return
     */
    @FormUrlEncoded
    @POST("touristUser/registeredGetOtpCode")
    Observable<CodeBean> registeredGetOtpCode(
            @Field("phoneNo") String phoneNo
    );


    /**
     * 游客注册
     *
     * @param phoneNo
     * @param passwd
     * @return
     */
    @FormUrlEncoded
    @POST("touristUser/register")
    Observable<RegisterEntity> register(
            @Field("phoneNo") String phoneNo,
            @Field("passwd") String passwd,
            @Field("code") String code,
            @Field("accType") int accType
    );

}
