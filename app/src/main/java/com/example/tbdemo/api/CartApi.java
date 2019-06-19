package com.example.tbdemo.api;

import com.example.tbdemo.bean.CartBean;
import com.example.tbdemo.bean.RegisterBean;

import java.util.HashMap;

import io.reactivex.Observable;

public class CartApi {

    private static CartApi cartApi;
    private  static ApiService apiService;

    private CartApi(ApiService apiService){
        this.apiService = apiService;
    }
    /**
     * 双重检验锁机制
     * @return
     */
    public static CartApi getCartApi(ApiService apiService){

//        if (cartApi == null){//判空，防止多次创建对象,只适合单线程访问
//            synchronized (CartApi.class){//防止多线程并发导致的数据安全问题
//                if (cartApi == null){//防止多个线程，创建多个实例
//                    cartApi = new CartApi(apiService);//自己创建自己的对象
//                }
//            }
//        }
        if (cartApi == null){
            cartApi = new CartApi(apiService);
        }
        return cartApi;
    }


    public static   Observable<CartBean>  cart(String userId,String sessionId){
       return apiService.cart(userId, sessionId);
    }



}
