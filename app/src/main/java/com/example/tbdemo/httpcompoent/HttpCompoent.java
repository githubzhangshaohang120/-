package com.example.tbdemo.httpcompoent;

import com.example.tbdemo.api.LoginApi;
import com.example.tbdemo.module.HttpModule;
import com.example.tbdemo.ui.fragment.homefragment.HomeFragment;
import com.example.tbdemo.ui.fragment.shoppingfragment.ShoppingFragment;
import com.example.tbdemo.ui.login.LoginActivity;
import com.example.tbdemo.ui.register.RegisterActivity;

import dagger.Component;

@Component(modules = HttpModule.class)
public interface HttpCompoent {
    void inject(LoginActivity loginActivity);
    void inject(RegisterActivity registerActivity);
    //void inject(HomeFragment homeFragment);
    void inject(ShoppingFragment shoppingFragment);
}
