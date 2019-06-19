package com.example.tbdemo.api;

public interface Api {

    public final boolean isRelease = false;
    //用户

    //登录接口
    public final String  LoginUrl = isRelease?"http://mobile.bwstudent.com/":"http://172.17.8.100/";//small/user/v1/login
    //注册接口
    public  final String RegisterUrl = isRelease?"http://mobile.bwstudent.com/":"http://172.17.8.100/";//small/user/v1/register

    /**
     * 订单
     */
    //查询购物车
    String  Query_shopping_cartUrl =isRelease?"http://mobile.bwstudent.com/":"http://172.17.8.100/";

}
