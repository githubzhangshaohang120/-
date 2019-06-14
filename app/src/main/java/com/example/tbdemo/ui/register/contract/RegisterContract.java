package com.example.tbdemo.ui.register.contract;

import com.example.tbdemo.base.BaseContract;
import com.example.tbdemo.bean.RegisterBean;

public interface RegisterContract {

    interface View extends BaseContract.BaseView{
        void RegisterSuccess(RegisterBean registerBean);
    }

    interface presenter extends BaseContract.BasePresenter<View>{
        void register(String phone,String pwd);
    }
}
