package com.example.tbdemo.ui.fragment.homefragment.adapter;

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
import android.widget.Toast;

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
    private List<CartBean.Result.Product>  productList;
    private Context context;

    public ProductAdapter(Context ctx, List<CartBean.Result.Product> cartList) {
        this.productList = cartList;
        this.context = ctx;

    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.product_item_layout,viewGroup,false);
        MyHolder myHolder = new MyHolder(view);
        return myHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final MyHolder myHolder, int i) {
        final CartBean.Result.Product product = productList.get(i);

        myHolder.priceTv.setText(product.price);
        myHolder.nametv.setText(product.commodityName);

        //首选项，配置选项
        RequestOptions options = new RequestOptions();
        options.placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_background)
        .transform(new RoundedCorners(5));//5像素圆角
        Glide.with(context).load(product.pic).apply(options).into(myHolder.iconIv);

    }

    @Override
    public int getItemCount() {
        return productList ==null?0:productList.size();
    }

    public class MyHolder extends XRecyclerView.ViewHolder {
        @BindView(R.id.name)
        TextView nametv;
        @BindView(R.id.price)
        TextView priceTv;
        @BindView(R.id.iv_product)
        ImageView iconIv;
        public MyHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
