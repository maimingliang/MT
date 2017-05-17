package com.elk.setting.api.service;

import com.elk.base.HttpResult;
import com.elk.setting.AppUpdateBeen;

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


public interface SettingSevice {
    /**
     * 帮助中心
     *
     * @param
     * @return
     */
    @FormUrlEncoded
    @POST("appUpdate/check")
    Observable<AppUpdateBeen> check(
            @Field("appType") int appType,
            @Field("versionNumber") String versionNumber
    );


    /**
     * 注销
     *
     * @param
     * @return
     */
    @FormUrlEncoded
    @POST("touristUser/logout")
    Observable<HttpResult<String>> logout(
            @Field("token") String token

    );

}
