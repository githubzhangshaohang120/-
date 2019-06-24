package com.example.tbdemo.ui.fragment.homefragment.homepresenter;

import com.example.tbdemo.api.CartApi;
import com.example.tbdemo.base.BasePresenter;
import com.example.tbdemo.ui.fragment.homefragment.homecontract.HomeContract;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class HomePresenter extends BasePresenter<HomeContract.View> implements HomeContract.prsenter {


//    private CartApi cartApi;
//
//    @Inject
//    public HomePresenter(CartApi cartApi){
//        this.cartApi = cartApi;
//    }
//
//
//    @Override
//    public void getCart(String userId, String sessionId) {
//        cartApi.getCatagory(userId, sessionId)
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribeOn(Schedulers.io())
//                .subscribe(new Consumer<CartBean>() {
//                    @Override
//                    public void accept(CartBean cartBean) throws Exception {
//                        if (mView !=null){
//                            mView.HomeSuccess(cartBean);
//                        }
//                    }
//                });
//    }


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
