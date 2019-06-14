package com.example.tbdemo.api;

import com.example.tbdemo.bean.LoginBean;
import com.example.tbdemo.bean.RegisterBean;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;


public interface ApiService {

    @FormUrlEncoded
    @POST("user/v1/login")
    Observable<LoginBean> login(@Field("phone") String phone,@Field("pwd") String pwd);

    @FormUrlEncoded
    @POST("user/v1/register")
    Observable<RegisterBean> register(@Field("phone")String phone, @Field("pwd")String pwd);
}
