package com.example.tbdemo;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.LocalBroadcastManager;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.tbdemo.base.BaseActivity;
import com.example.tbdemo.jpsh.ExampleUtil;
import com.example.tbdemo.ui.fragment.circlefragment.CircleFragment;
import com.example.tbdemo.ui.fragment.homefragment.HomeFragment;
import com.example.tbdemo.ui.fragment.listfragment.ListFragment;
import com.example.tbdemo.ui.fragment.myfragment.MyFragment;
import com.example.tbdemo.ui.fragment.shoppingfragment.ShoppingFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.jpush.android.api.JPushInterface;

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
    public static boolean isForeground = false;
    private MessageReceiver mMessageReceiver;
    public static final String MESSAGE_RECEIVED_ACTION = "com.example.jpushdemo.MESSAGE_RECEIVED_ACTION";
    public static final String KEY_TITLE = "title";
    public static final String KEY_MESSAGE = "message";
    public static final String KEY_EXTRAS = "extras";
    private EditText msgText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        registerMessageReceiver();
        fragmentManager = getSupportFragmentManager();
         homeFragment = new HomeFragment();
         circleFragment = new CircleFragment();
         shoppingFragment = new ShoppingFragment();
         listFragment = new ListFragment();
         myFragment = new MyFragment();
        fragmentManager.beginTransaction().replace(R.id.frame,homeFragment).commit();
        home_btn.setChecked(true);
        group.setOnCheckedChangeListener(this);
        msgText = (EditText)findViewById(R.id.msg_rec);

    }

    private void registerMessageReceiver() {
        mMessageReceiver = new MessageReceiver();
        IntentFilter filter = new IntentFilter();
        filter.setPriority(IntentFilter.SYSTEM_HIGH_PRIORITY);
        filter.addAction(MESSAGE_RECEIVED_ACTION);
        LocalBroadcastManager.getInstance(this).registerReceiver(mMessageReceiver, filter);
    }

    public class MessageReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            try {
                if (MESSAGE_RECEIVED_ACTION.equals(intent.getAction())) {
                    String messge = intent.getStringExtra(KEY_MESSAGE);
                    String extras = intent.getStringExtra(KEY_EXTRAS);
                    StringBuilder showMsg = new StringBuilder();
                    showMsg.append(KEY_MESSAGE + " : " + messge + "\n");
                    if (!ExampleUtil.isEmpty(extras)) {
                        showMsg.append(KEY_EXTRAS + " : " + extras + "\n");
                    }
                    setCostomMsg(showMsg.toString());
                }
            } catch (Exception e){
            }
        }
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

    // 初始化 JPush。如果已经初始化，但没有登录成功，则执行重新登录。
    private void init(){
        JPushInterface.init(getApplicationContext());
    }

    private void setCostomMsg(String msg){
        if (null != msgText) {
            msgText.setText(msg);
            msgText.setVisibility(View.VISIBLE);
        }
    }

    @Override
    protected void onResume() {
        isForeground = true;
        super.onResume();
    }


    @Override
    protected void onPause() {
        isForeground = false;
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        LocalBroadcastManager.getInstance(this).unregisterReceiver(mMessageReceiver);
        super.onDestroy();

    }
}
