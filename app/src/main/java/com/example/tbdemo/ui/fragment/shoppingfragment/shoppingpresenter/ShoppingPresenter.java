package com.example.tbdemo.ui.fragment.shoppingfragment.shoppingpresenter;

import com.example.tbdemo.api.CartApi;
import com.example.tbdemo.base.BasePresenter;
import com.example.tbdemo.bean.CartBean;
import com.example.tbdemo.ui.fragment.shoppingfragment.shoppingcontract.ShoppingContract;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class ShoppingPresenter extends BasePresenter<ShoppingContract.View> implements ShoppingContract.prsenter {


    private CartApi cartApi;

    @Inject
    public ShoppingPresenter(CartApi cartApi){
        this.cartApi = cartApi;
    }


    @Override
    public void getCart(String userId, String sessionId) {
        cartApi.getCatagory(userId, sessionId)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Consumer<CartBean>() {
                    @Override
                    public void accept(CartBean cartBean) throws Exception {
                        if (mView !=null){
                            mView.ShoppingSuccess(cartBean);
                        }
                    }
                });
    }


//    cartApi.cart(uid, sessionid)
//            .observeOn(AndroidSchedulers.mainThread())
//            .subscribeOn(Schedulers.io())
//            .map(new Function<CartBean, String>() {
//        @Override
//        public String apply(CartBean cartBean) throws Exception {
//            return cartBean.getMessage();
//        }
//    }).subscribe(new Consumer<String>() {
//        @Override
//        public void accept(String s) throws Exception {
//            if (mView !=null){
//                mView.HomeSuccess(s);
//            }
//        }
//    });
}
