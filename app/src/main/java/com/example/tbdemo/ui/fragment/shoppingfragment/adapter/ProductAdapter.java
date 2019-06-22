package com.example.tbdemo.ui.fragment.shoppingfragment.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.example.tbdemo.R;
import com.example.tbdemo.bean.CartBean;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ProductAdapter extends XRecyclerView.Adapter<ProductAdapter.MyHolder> {

    private Context context;
    private List<CartBean.Result.Product> shoppingCartList;
    private LayoutInflater inflater;
    private OnItemclickListener onItemclickListener;
    public void setOnItemclickListener(OnItemclickListener onItemclickListener){
        this.onItemclickListener = onItemclickListener;
    }

    public ProductAdapter(Context context, List<CartBean.Result.Product> shoppingCartList) {
        this.context = context;
        this.shoppingCartList = shoppingCartList;
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = inflater.from(context).inflate(R.layout.product_item_layout, viewGroup, false);
        MyHolder myHolder = new MyHolder(view);
        return myHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder myHolder, int i) {
        CartBean.Result.Product product = shoppingCartList.get(i);
        myHolder.name.setText(product.commodityName);
        myHolder.price.setText(product.price);
        RequestOptions options = new RequestOptions();
        options.placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_background)
                .transform(new RoundedCorners(5));
        Glide.with(context).load(product.pic).apply(options).into(myHolder.iv_product);
    }

    @Override
    public int getItemCount() {
        return shoppingCartList != null ? 0 : shoppingCartList.size();
    }

    public class MyHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.checkbox_product)
        CheckBox checkbox_product;
        @BindView(R.id.iv_product)
        ImageView iv_product;
        @BindView(R.id.name)
        TextView name;
        @BindView(R.id.price)
        TextView price;
        @BindView(R.id.minus)
        TextView minus;
        @BindView(R.id.num)
        EditText num;
        @BindView(R.id.add)
        TextView add;

        public MyHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public interface OnItemclickListener{
        void onItemclick(int posion,View itemView,CartBean.Result.Product product);
        void onLongItemclick(int posion,View itemView);

    }

}
