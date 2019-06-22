package com.example.tbdemo.ui.fragment.shoppingfragment.shoppingcontract;

import com.example.tbdemo.base.BaseContract;
import com.example.tbdemo.bean.CartBean;

public interface ShoppingContract {
    interface View extends BaseContract.BaseView{
        void ShoppingSuccess(CartBean cartBean);
    }

    interface prsenter extends BaseContract.BasePresenter<View>{
        void getCart(String userId, String sessionId);
    }
}
