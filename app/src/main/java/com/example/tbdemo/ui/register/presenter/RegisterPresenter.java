package com.example.tbdemo.ui.register.presenter;

import com.example.tbdemo.api.LoginApi;
import com.example.tbdemo.api.RegisterApi;
import com.example.tbdemo.base.BasePresenter;
import com.example.tbdemo.bean.RegisterBean;
import com.example.tbdemo.ui.register.contract.RegisterContract;

import javax.inject.Inject;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class RegisterPresenter extends BasePresenter<RegisterContract.View> implements RegisterContract.presenter {

    protected RegisterApi registerApi;

    @Inject
    public RegisterPresenter(RegisterApi registerApi){
            this.registerApi = registerApi;
    }

    @Override
    public void register(String phone, String pwd) {
        RegisterApi.register(phone,pwd)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<RegisterBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(RegisterBean registerBean) {
                            if (mView !=null){
                                mView.RegisterSuccess(registerBean);
                            }
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
