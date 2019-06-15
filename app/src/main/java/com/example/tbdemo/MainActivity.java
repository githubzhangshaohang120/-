package com.example.tbdemo;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.tbdemo.base.BaseActivity;
import com.example.tbdemo.base.BaseFragment;
import com.example.tbdemo.ui.fragment.CircleFragment;
import com.example.tbdemo.ui.fragment.HomeFragment;
import com.example.tbdemo.ui.fragment.ListFragment;
import com.example.tbdemo.ui.fragment.MyFragment;
import com.example.tbdemo.ui.fragment.ShoppingFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity implements RadioGroup.OnCheckedChangeListener {

    @BindView(R.id.frame)
    FrameLayout frame;
    @BindView(R.id.home_btn)
    RadioButton home_btn;
    @BindView(R.id.circle_btn)
    RadioButton circle_btn;
    @BindView(R.id.shaopping_btn)
    RadioButton shaopping_btn;
    @BindView(R.id.list_btn)
    RadioButton list_btn;
    @BindView(R.id.my_btn)
    RadioButton my_btn;
    @BindView(R.id.group)
    RadioGroup group;
    private HomeFragment homeFragment;
    private CircleFragment circleFragment;
    private ShoppingFragment shoppingFragment;
    private ListFragment listFragment;
    private MyFragment myFragment;

    private int position;
    Fragment[] fragments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getContentLayout());
        ButterKnife.bind(this);
    }


    @Override
    public void dismissLoding() {

    }

    @Override
    public int getContentLayout() {
        return R.layout.activity_main;
    }

    @Override
    public void inject() {

    }

    @Override
    public void initView(View view) {

        fragments = new Fragment[]{
                //主页、新闻、图片、视频、个人
                new HomeFragment(),
                new CircleFragment(),
                new ShoppingFragment(),
                new ListFragment(),
                new MyFragment()

        };



        group.setOnCheckedChangeListener(this);
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        //设置默认选中框架页面
        //group.check(R.id.home_btn);
        transaction.add(R.id.home_btn,fragments[0]).commit();

    }


    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId){
            case R.id.home_btn:
                setIndexSelected(0);
                break;
            case R.id.circle_btn:
                setIndexSelected(1);
                break;
            case R.id.shaopping_btn:
                setIndexSelected(2);
                break;
            case R.id.list_btn:
                setIndexSelected(3);
                break;
            case R.id.my_btn:
                setIndexSelected(4);
                break;
        }
    }

    // 设置fragment界面
    private void setIndexSelected(int index) {
        if (position == index){
            return;
        }

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        //隐藏当前的fragment
        transaction.hide(fragments[position]);

        //判断fragment是否已经添加
        if (!fragments[index].isAdded()){
            transaction.add(R.id.home_btn,fragments[index]).show(fragments[index]);
        }else {
            //显示新的fragment
            transaction.show(fragments[index]);
        }

        transaction.commit();
        position =index;
    }



}
