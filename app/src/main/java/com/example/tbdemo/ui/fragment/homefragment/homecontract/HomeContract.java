package com.example.tbdemo.ui.fragment.homefragment.homecontract;

import com.example.tbdemo.base.BaseContract;
import com.example.tbdemo.bean.CartBean;

public interface HomeContract {
    interface View extends BaseContract.BaseView{
        //void HomeSuccess(CartBean cartBean);
    }

    interface prsenter extends BaseContract.BasePresenter<View>{
        //void getCart(String userId,String sessionId);
    }
}
