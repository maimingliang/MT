package com.elk.tourist.api.service;


import com.elk.base.HttpResult;
import com.elk.tourist.bean.MineBean;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import rx.Observable;

/**
 * 存放关于用户的一些api
 * Created by jess on 8/5/16 12:05
 * contact with jess.yan.effort@gmail.com
 */
public interface TouristUserService {


    @FormUrlEncoded
    @POST("touristUser/my_1_0_0")
    Observable<MineBean> my_1_0_0(
            @Field("id") String id,
            @Field("token") String token
    );




}
