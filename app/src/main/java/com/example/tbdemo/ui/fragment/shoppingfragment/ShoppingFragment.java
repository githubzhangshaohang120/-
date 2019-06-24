package com.example.tbdemo.ui.fragment.shoppingfragment;

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
import com.example.tbdemo.bean.CartDaoBean;
import com.example.tbdemo.greendao.CartDaoBeanDao;
import com.example.tbdemo.httpcompoent.DaggerHttpCompoent;
import com.example.tbdemo.module.HttpModule;
import com.example.tbdemo.ui.fragment.shoppingfragment.adapter.CartAdapter;
import com.example.tbdemo.ui.fragment.shoppingfragment.shoppingcontract.ShoppingContract;
import com.example.tbdemo.ui.fragment.shoppingfragment.shoppingpresenter.ShoppingPresenter;
import com.example.tbdemo.util.GreenDaoUtils;
import com.google.gson.Gson;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ShoppingFragment extends BaseFragment<ShoppingPresenter> implements ShoppingContract.View, XRecyclerView.LoadingListener, CartAdapter.notifyCart {


    @BindView(R.id.rv_cart)
    XRecyclerView xRecyclerView;
    @BindView(R.id.checkbox)
    CheckBox checkbox;
    @BindView(R.id.totalPrice)
    TextView totalPrice;
    private View view;
    //private ProductAdapter productAdapter;
    private CartAdapter cartAdapter;
    int posion = 1;
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

        //mPresenter.cart(userId,sessionId);
        return view;

    }

    private void initData() {
        //mPresenter.getCart(getUserId(), getSessionId());
        mPresenter.getCart("5139", "15611917392065139");
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
        cartAdapter.setNotifyCart(this);
        xRecyclerView.setAdapter(cartAdapter);

    }


    @Override
    public void onRefresh() {
        posion = 1;
        xRecyclerView.refreshComplete();
    }

    @Override
    public void onLoadMore() {
        posion++;
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
                box();
                break;
            case R.id.totalPrice:
                break;
        }
    }

    private void box() {
        if (checkbox.isChecked()){//全选的时候
            for (CartBean.ResultBean resultBean : cartAdapter.getCartList()){
                resultBean.cartChecked = true;//设置一级选中状态
                for (CartBean.ResultBean.ShoppingCartListBean shoppingCartListBean : resultBean.shoppingCartList){
                    shoppingCartListBean.productChecked = true;//设置二级选中状态
                }
            }
            allPrice(true);
        }else {
            for (CartBean.ResultBean resultBean : cartAdapter.getCartList()){
                resultBean.cartChecked = false;//设置一级选中状态
                for (CartBean.ResultBean.ShoppingCartListBean shoppingCartListBean : resultBean.shoppingCartList){
                    shoppingCartListBean.productChecked = false;//设置二级选中状态
                }
            }
            allPrice(true);
        }
        cartAdapter.notifyDataSetChanged();
    }

    public  void onItemclick(int pos, View itemView, CartBean.ResultBean.ShoppingCartListBean product){

    }

    public  void OnItemLongClick(int pos, View itemView){

    }

    @Override
    public void isCheced(Boolean b) {

    }

    private void allPrice(boolean b) {
        double totalPrices = 0;
        //遍历所有数据，得到价格汇总计算
        for (CartBean.ResultBean result : cartAdapter.getCartList()) {

            for (CartBean.ResultBean.ShoppingCartListBean product : result.shoppingCartList) {

                totalPrices += Double.parseDouble(product.price + "");
            }
        }
        if (!b){//未选中时
            totalPrices = 0;
        }
        totalPrice.setText(totalPrices+"");//选牛时
    }
}
