package com.example.tbdemo.ui.fragment.homefragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.example.tbdemo.R;
import com.example.tbdemo.base.BaseFragment;
import com.example.tbdemo.bean.CartBean;
import com.example.tbdemo.httpcompoent.DaggerHttpCompoent;
import com.example.tbdemo.module.HttpModule;
import com.example.tbdemo.ui.fragment.homefragment.adapter.ProductAdapter;
import com.example.tbdemo.ui.fragment.homefragment.homecontract.HomeContract;
import com.example.tbdemo.ui.fragment.homefragment.homepresenter.HomePresenter;
import com.example.tbdemo.util.SharedPreferencesUtils;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class HomeFragment extends BaseFragment<HomePresenter> implements HomeContract.View, XRecyclerView.LoadingListener {


    @BindView(R.id.rv_cart)
    XRecyclerView xRecyclerView;
    @BindView(R.id.checkbox)
    CheckBox checkbox;
    @BindView(R.id.totalPrice)
    TextView totalPrice;
    private Unbinder unbinder;
    public ProductAdapter productAdapter;
    List<CartBean.Result.Product> data = new ArrayList<>();

    @Override
    public int getContentLayout() {
        return R.layout.fragment_home;
    }

    @Override
    public void initView(View view) {
        xRecyclerView.setLoadingListener(this);
        xRecyclerView.setLoadingMoreEnabled(true);//加载更多
        xRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        //适配器
        productAdapter = new ProductAdapter(getContext(), data);

        if (productAdapter != null){
            xRecyclerView.setAdapter(productAdapter);
            showToast("成功了居然没显示");
        }else {
            showToast("xRecyclerView,没成功");
        }

    }


    @Override
    public void inject() {
        DaggerHttpCompoent.builder()
                .httpModule(new HttpModule())
                .build()
                .inject(this);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = View.inflate(getActivity(), R.layout.fragment_home, null);
        ButterKnife.bind(this,view);

        String userId = (String) SharedPreferencesUtils.getParam(getActivity(), "userId", "");
        String sessionId = (String) SharedPreferencesUtils.getParam(getActivity(), "sessionId", "");
        //mPresenter.cart("159", "1560906483344159");
        mPresenter.cart(userId,sessionId);

        return view;

    }

    @OnClick({R.id.rv_cart, R.id.checkbox, R.id.totalPrice})
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.rv_cart:

                break;
            case R.id.checkbox:
                break;
            case R.id.totalPrice:
                break;
        }
    }

    @Override
    public void onRefresh() {

    }

    @Override
    public void onLoadMore() {

    }


    @Override
    public void HomeSuccess(CartBean cartBean) {
        String status = cartBean.getStatus();
        if (status.equals("0000")){
            showToast(cartBean.getMessage());
            showToast("霸气");
        }else {
            showToast(cartBean.getMessage());
            showToast("服气");
        }


    }
}
