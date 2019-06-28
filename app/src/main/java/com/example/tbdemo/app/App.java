package com.example.tbdemo.app;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;

import com.example.tbdemo.greendao.DaoMaster;
import com.example.tbdemo.greendao.DaoSession;
import com.example.tbdemo.util.GreenDaoUtils;

import org.greenrobot.greendao.database.Database;

import cn.jpush.android.api.JPushInterface;
import cn.jpush.android.helper.Logger;


public class App extends Application {

    private static final String TAG = "JIGUANG-Example";
    private static SharedPreferences sharedPreferences;
//    private static Context context;
//    private static DaoSession daoSession;
//    private DaoMaster.DevOpenHelper openHelper;
//    private SQLiteDatabase db;
//    private DaoMaster daoMaster;
    public static Context context;

    @Override
    public void onCreate() {
        Logger.d(TAG, "[ExampleApplication] onCreate");
        super.onCreate();
        context = this;
        shared();
        //initgreendao();
        GreenDaoUtils.getInstance().init();//初始化
        JPushInterface.setDebugMode(true);// 设置开启日志,发布时请关闭日志
        JPushInterface.init(this);    		// 初始化 JPush
    }



//    private void initgreendao() {
//        openHelper = new DaoMaster.DevOpenHelper(this,"contig.db");
//        db = openHelper.getWritableDatabase();
//        daoMaster = new DaoMaster(db);
//        daoSession = daoMaster.newSession();
//    }
//
//    public  static  DaoSession getDaoSession(){
//        return daoSession;
//    }
//
//    public SQLiteDatabase getDb(){
//        return db;
//    }
    private void shared() {
            sharedPreferences = context.getSharedPreferences("config.xml", Context.MODE_PRIVATE);
    }

    public static SharedPreferences getShared(){
        return sharedPreferences;
    }




}
