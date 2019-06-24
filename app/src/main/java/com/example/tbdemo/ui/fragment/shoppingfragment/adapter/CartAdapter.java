package com.example.tbdemo.ui.fragment.shoppingfragment.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.example.tbdemo.R;
import com.example.tbdemo.bean.CartBean;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CartAdapter extends XRecyclerView.Adapter<CartAdapter.MyHolder> {

    private Context context;
    private List<CartBean.ResultBean> list;
    private LayoutInflater inflater;
    private notifyCart notifyCart;

    public CartAdapter(Context context, List<CartBean.ResultBean> result) {
        this.context = context;
        this.list = result;
    }


    public void setNotifyCart(CartAdapter.notifyCart notifyCart) {
        this.notifyCart = notifyCart;
    }

    public List<CartBean.ResultBean> getCartList() {
        return list;
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = inflater.from(context).inflate(R.layout.cart_item_layout, viewGroup, false);
        MyHolder myHolder = new MyHolder(view);
        return myHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final MyHolder myHolder, int i) {
        CartBean.ResultBean result = list.get(i);

        if (result.cartChecked) {
            myHolder.checkbox_cart.setChecked(true);
        } else {//未选中
            myHolder.checkbox_cart.setChecked(false);
        }
        myHolder.tv_name.setText(result.categoryName);


        ProductAdapter productAdapter = new ProductAdapter(context, result.shoppingCartList);
        myHolder.rv_product.setLayoutManager(new LinearLayoutManager(context));
        myHolder.rv_product.setAdapter(productAdapter);
        myHolder.checkbox_cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                notifyCart.isCheced(myHolder.checkbox_cart.isChecked());
            }
        });
    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }


    public class MyHolder extends XRecyclerView.ViewHolder {
        @BindView(R.id.checkbox_cart)
        CheckBox checkbox_cart;
        @BindView(R.id.tv_name)
        TextView tv_name;
        @BindView(R.id.rv_product)
        XRecyclerView rv_product;

        public MyHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public interface notifyCart {
        void isCheced(Boolean b);
    }

}
