package com.example.tbdemo.util;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.tbdemo.R;
import com.example.tbdemo.app.App;

/**
 *
 */
public class GlideUtils {

    public static void showImg(String url, ImageView view){
        RequestOptions options = new RequestOptions();
        options.error(R.mipmap.ic_launcher);
        Glide.with(App.context).load(url).into(view);
    }
}
