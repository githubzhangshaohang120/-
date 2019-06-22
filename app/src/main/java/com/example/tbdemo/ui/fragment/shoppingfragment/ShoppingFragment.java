package com.example.tbdemo.ui.fragment.shoppingfragment;

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
import com.example.tbdemo.bean.CartDaoBean;
import com.example.tbdemo.greendao.CartDaoBeanDao;
import com.example.tbdemo.httpcompoent.DaggerHttpCompoent;
import com.example.tbdemo.module.HttpModule;
import com.example.tbdemo.ui.fragment.shoppingfragment.adapter.CartAdapter;
import com.example.tbdemo.ui.fragment.shoppingfragment.adapter.ProductAdapter;
import com.example.tbdemo.ui.fragment.shoppingfragment.shoppingcontract.ShoppingContract;
import com.example.tbdemo.ui.fragment.shoppingfragment.shoppingpresenter.ShoppingPresenter;
import com.example.tbdemo.util.GreenDaoUtils;
import com.google.gson.Gson;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnItemClick;
import butterknife.OnItemLongClick;

public class ShoppingFragment extends BaseFragment<ShoppingPresenter> implements ShoppingContract.View, XRecyclerView.LoadingListener {


    @BindView(R.id.rv_cart)
    XRecyclerView xRecyclerView;
    @BindView(R.id.checkbox)
    CheckBox checkbox;
    @BindView(R.id.totalPrice)
    TextView totalPrice;
    private View view;
    //private ProductAdapter productAdapter;
    private CartAdapter cartAdapter;

    @Override
    public int getContentLayout() {
        return R.layout.fragment_shopping;
    }

    @Override
    public void initView(View view) {
        xRecyclerView.setLoadingListener(this);
        xRecyclerView.setLoadingMoreEnabled(true);//加载更多
        xRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
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
        View view = View.inflate(getActivity(), R.layout.fragment_shopping, null);
        ButterKnife.bind(this, view);
        initView(view);
        initData();
//        //mPresenter.cart("159", "1560906483344159");
//        mPresenter.cart(userId,sessionId);
        return view;

    }

    private void initData() {
        mPresenter.getCart(getUserId(), getSessionId());
        CartDaoBeanDao cartDaoBeanDao = GreenDaoUtils.getInstance().getDaoSession().getCartDaoBeanDao();
        List<CartDaoBean> list = cartDaoBeanDao.queryBuilder().list();
        if (list !=null && list.size() >0){
            CartDaoBean cartDaoBean = list.get(0);//得到第一条数据
            String json = cartDaoBean.getJson();//得到json结构
            //转换成实体类
            CartBean cartBean = new Gson().fromJson(json, CartBean.class);
            fillData(cartBean);
        }
        //适配器
        //productAdapter = new ProductAdapter(getContext(), data);

//        if (productAdapter != null) {
//            xRecyclerView.setAdapter(productAdapter);
//            showToast("成功了居然没显示");
//        } else {
//            showToast("xRecyclerView,没成功");
//        }
    }

    /**
     * 填充数据
     * @param cartBean
     */
    private void fillData(CartBean cartBean) {
        cartAdapter = new CartAdapter(getContext(),cartBean.result);
        cartAdapter.setNotifyCart(getContext());
        xRecyclerView.setAdapter(cartAdapter);

    }


    @Override
    public void onRefresh() {
        xRecyclerView.refreshComplete();
    }

    @Override
    public void onLoadMore() {
        xRecyclerView.loadMoreComplete();
    }


    @Override
    public void ShoppingSuccess(CartBean cartBean) {
        String status = cartBean.getStatus();
        if (status.equals("0000")) {
            showToast(cartBean.getMessage());
            showToast("霸气");
        } else {
            showToast(cartBean.getMessage());
            showToast("服气");
        }
        //填充数据
        fillData(cartBean);
        //请求成功后，缓存到greedao
        saveData(cartBean);

    }

    private void saveData(CartBean cartBean) {
        String s = new Gson().toJson(cartBean);
        CartDaoBean cartDaoBean = new CartDaoBean();
        cartDaoBean.setJson(s);
        //把返回的json串保存到sqlite
        GreenDaoUtils.getInstance().getDaoSession().getUserBeanDao().insert(cartDaoBean);

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

    public  void onItemclick(int pos, View itemView, CartBean.Result.Product product){

    }

    public  void OnItemLongClick(int pos, View itemView){

    }
}
