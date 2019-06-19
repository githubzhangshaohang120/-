package com.example.tbdemo.api;

import com.example.tbdemo.bean.CartBean;
import com.example.tbdemo.bean.LoginBean;
import com.example.tbdemo.bean.RegisterBean;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;


public interface ApiService {

    @FormUrlEncoded
    @POST("small/user/v1/login")
    Observable<LoginBean> login(@Field("phone") String phone,@Field("pwd") String pwd);

    @FormUrlEncoded
    @POST("small/user/v1/register")
    Observable<RegisterBean> register(@Field("phone")String phone, @Field("pwd")String pwd);
    @FormUrlEncoded
    @POST("small/order/verify/v1/findShoppingCart")
    Observable<CartBean> cart(@Field("userId") String userId,@Field("sessionId") String sessionId);

}
