package com.example.tbdemo.ui.fragment.homefragment.homepresenter;

import android.annotation.SuppressLint;

import com.example.tbdemo.api.CartApi;
import com.example.tbdemo.base.BasePresenter;
import com.example.tbdemo.bean.CartBean;
import com.example.tbdemo.ui.fragment.homefragment.homecontract.HomeContract;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class HomePresenter extends BasePresenter<HomeContract.View> implements HomeContract.prsenter {


    private CartApi cartApi;

    @Inject
    public HomePresenter(CartApi cartApi){
        this.cartApi = cartApi;
    }


    @Override
    public void cart(String userId, String sessionId) {
        cartApi.cart(userId, sessionId)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Consumer<CartBean>() {
                    @Override
                    public void accept(CartBean cartBean) throws Exception {
                        if (mView !=null){
                            mView.HomeSuccess(cartBean);
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
