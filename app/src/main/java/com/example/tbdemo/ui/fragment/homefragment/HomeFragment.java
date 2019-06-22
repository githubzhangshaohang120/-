package com.example.tbdemo.ui.fragment.homefragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.tbdemo.R;
import com.example.tbdemo.base.BaseFragment;

import butterknife.ButterKnife;

public class HomeFragment extends BaseFragment {




    @Override
    public int getContentLayout() {
        return R.layout.fragment_home;
    }

    @Override
    public void initView(View view) {

    }


    @Override
    public void inject() {
//        DaggerHttpCompoent.builder()
//                .httpModule(new HttpModule())
//                .build()
//                .inject(this);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = View.inflate(getActivity(), R.layout.fragment_home, null);
        ButterKnife.bind(this,view);


//        //mPresenter.cart("159", "1560906483344159");
//        mPresenter.cart(userId,sessionId);
        //mPresenter.cart("","");
        //mPresenter.cart(,);


        return view;

    }


}
