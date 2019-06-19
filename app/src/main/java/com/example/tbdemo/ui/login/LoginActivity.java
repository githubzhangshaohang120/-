package com.example.tbdemo.ui.login;

import android.Manifest;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.tbdemo.MainActivity;
import com.example.tbdemo.R;
import com.example.tbdemo.app.App;
import com.example.tbdemo.base.BaseActivity;
import com.example.tbdemo.bean.LoginBean;
import com.example.tbdemo.httpcompoent.DaggerHttpCompoent;
import com.example.tbdemo.module.HttpModule;
import com.example.tbdemo.ui.login.contract.LoginContract;
import com.example.tbdemo.ui.login.presenter.LoginPresenter;
import com.example.tbdemo.ui.register.RegisterActivity;
import com.example.tbdemo.util.SharedPreferencesUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import pub.devrel.easypermissions.EasyPermissions;

public class LoginActivity extends BaseActivity<LoginPresenter> implements LoginContract.View {

    @BindView(R.id.edit_phone)
    EditText mEditPhone;
    @BindView(R.id.edit_pwd)
    EditText mEditPwd;
    @BindView(R.id.login_image_eye)
    ImageView mLoginImageEye;
    @BindView(R.id.box_pass)
    CheckBox mBoxPass;
    @BindView(R.id.tv_register)
    TextView mTvRegister;
    @BindView(R.id.btn_login)
    Button mBtnLogin;
    boolean isShowHidden = false;
    private Unbinder bind;


    @Override
    public int getContentLayout() {
        return R.layout.activity_login;
    }

    @Override
    public void inject() {
        DaggerHttpCompoent.builder()
                .httpModule(new HttpModule())
                .build()
                .inject(this);
    }

    @Override
    public void initView(View view) {

    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bind = ButterKnife.bind(this);
        passchecke();

    }

    public void passchecke(){
        boolean remPas = App.getShared().getBoolean("remPas", true);
        if (remPas){
            mBoxPass.setChecked(true);
            mEditPhone.setText(App.getShared().getString("m",""));
            mEditPwd.setText(App.getShared().getString("p",""));
        }
        if (!EasyPermissions.hasPermissions(this,
                new String[]{Manifest.permission.READ_EXTERNAL_STORAGE,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE})) {
            EasyPermissions.requestPermissions(this,
                    "发布图片", 10,
                    new String[]{Manifest.permission.READ_EXTERNAL_STORAGE,
                            Manifest.permission.WRITE_EXTERNAL_STORAGE});
        }
    }



    @butterknife.OnClick({ R.id.login_image_eye,R.id.box_pass, R.id.tv_register,R.id.btn_login})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.login_image_eye:
                ShowHidden();
                break;
            case R.id.box_pass:
                remPas();
                break;
            case R.id.tv_register:
                speediness();
                break;
            case R.id.btn_login:
                login();
                break;
        }
    }

    public void ShowHidden() {
        if (isShowHidden){

            mEditPwd.setTransformationMethod(PasswordTransformationMethod.getInstance());
            isShowHidden = false;
        }else {

            mEditPwd.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
            isShowHidden = true;
        }
    }

    public void remPas() {




        App.getShared().edit().putBoolean("remPas",mBoxPass.isChecked()).commit();


    }

    /**
     * 重写onRequestPermissionsResult，用于接受请求结果
     *
     * @param requestCode
     * @param permissions
     * @param grantResults
     */
    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String[] permissions, @NonNull int[] grantResults) {

        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        //将请求结果传递EasyPermission库处理
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }

    public void login() {
        if (checkEdit()){
            String phone = mEditPhone.getText().toString();
            String pwd = mEditPwd.getText().toString();

            if (!isMobileNO(phone)){
                showToast("请输入正确的手机号");
                return;
            }

            if (pwd.length() < 3) {
                showToast("不能少于6位字符");
                return;
            }
            if (mBoxPass.isChecked()) {
                App.getShared().edit().putString("m", phone).putString("p", pwd).commit();
            }
            showLoad();
            /**
             * MD5加密
             */
            //mPresenter.login(phone, MD5Utils.md5(pwd));
            mPresenter.login(phone,pwd);

        }
    }
    @Override
    public void loginSuccess(LoginBean loginBean) {
        dismissLoding();
        String status = loginBean.getStatus();
        if (status.equals("0000")){
            SharedPreferencesUtils.setParam(this,"userId",loginBean.getResult().getUserId() + " ");
            SharedPreferencesUtils.setParam(this,"username",loginBean.getResult().getNickName() + " ");
            SharedPreferencesUtils.setParam(this,"iconUrl",loginBean.getResult().getHeadPic() + " ");
            SharedPreferencesUtils.setParam(this,"sessionId",loginBean.getResult().getSessionId() + " ");
            showToast(loginBean.getMessage());
            intent(MainActivity.class);
        }else {
            showToast(loginBean.getMessage());
        }

    }

    public void speediness() {
        intent(RegisterActivity.class);
    }

    public boolean checkEdit(){
        if (mEditPhone.getText().toString().trim().equals("")){
            showToast("用户名不能为空");
        }else if (mEditPwd.getText().toString().trim().equals("")){
            showToast("密码不能为空");
        }else {
            return true;
        }

        return false;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (bind != null) {
            bind.unbind();
            bind = null;
        }
    }
}
