package com.example.tbdemo;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.tbdemo.base.BaseActivity;
import com.example.tbdemo.ui.fragment.circlefragment.CircleFragment;
import com.example.tbdemo.ui.fragment.homefragment.HomeFragment;
import com.example.tbdemo.ui.fragment.listfragment.ListFragment;
import com.example.tbdemo.ui.fragment.myfragment.MyFragment;
import com.example.tbdemo.ui.fragment.shoppingfragment.ShoppingFragment;

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
    private int currentIndex=1;
    private FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        fragmentManager = getSupportFragmentManager();
         homeFragment = new HomeFragment();
         circleFragment = new CircleFragment();
         shoppingFragment = new ShoppingFragment();
         listFragment = new ListFragment();
         myFragment = new MyFragment();
        fragmentManager.beginTransaction().replace(R.id.frame,homeFragment).commit();
        home_btn.setChecked(true);
        group.setOnCheckedChangeListener(this);


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

    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {

        switch (checkedId){
            case R.id.home_btn:
                if (currentIndex ==1 ){
                    return;
                }
                currentIndex = 1;
                fragmentManager.beginTransaction().replace(R.id.frame,homeFragment).commit();
                break;
            case R.id.circle_btn:
                if (currentIndex == 2 ){
                    return;
                }
                currentIndex = 2;
                fragmentManager.beginTransaction().replace(R.id.frame,circleFragment).commit();
                break;
            case R.id.shaopping_btn:
                if (currentIndex ==3 ){
                    return;
                }
                currentIndex = 3;
                fragmentManager.beginTransaction().replace(R.id.frame,shoppingFragment).commit();
                break;
            case R.id.list_btn:
                if (currentIndex ==4 ){
                    return;
                }
                currentIndex = 4;
                fragmentManager.beginTransaction().replace(R.id.frame,listFragment).commit();
                break;
            case R.id.my_btn:
                if (currentIndex ==5 ){
                    return;
                }
                currentIndex = 5;
                fragmentManager.beginTransaction().replace(R.id.frame,myFragment).commit();
                break;
        }
    }


}
