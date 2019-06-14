package com.example.tbdemo.ui.register;

import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.tbdemo.R;
import com.example.tbdemo.base.BaseActivity;
import com.example.tbdemo.bean.RegisterBean;
import com.example.tbdemo.httpcompoent.DaggerHttpCompoent;
import com.example.tbdemo.module.HttpModule;
import com.example.tbdemo.ui.register.contract.RegisterContract;
import com.example.tbdemo.ui.register.presenter.RegisterPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class RegisterActivity extends BaseActivity<RegisterPresenter> implements RegisterContract.View {

    @BindView(R.id.edit_register_phone)
    EditText mEditRegisterPhone;
    @BindView(R.id.edit_register_verification_code)
    EditText mEditRegisterVerificationCode;
    @BindView(R.id.get_register_verification_code)
    TextView mGetRegisterVerificationCode;
    @BindView(R.id.edit_register_pwd)
    EditText mEditRegisterPwd;
    @BindView(R.id.register_image_eye)
    ImageView mRegisterImageEye;
    @BindView(R.id.tv_register_immediately_log_in)
    TextView mTvRegisterImmediatelyLogIn;
    @BindView(R.id.btn_register)
    Button mBtnRegister;
    private static boolean isShowHidden = false;
    private Unbinder bind;
    private String phone;
    private String pwd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bind = ButterKnife.bind(this);
    }

    @Override
    public int getContentLayout() {
        return R.layout.activity_register;
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
    public void RegisterSuccess(RegisterBean registerBean) {
        dismissLoding();
        if (registerBean.getStatus().equals("0000")) {
            showToast(registerBean.getMessage());
            finish();
        } else {
            showToast(registerBean.getMessage());
        }
    }

    @OnClick({R.id.get_register_verification_code, R.id.register_image_eye, R.id.tv_register_immediately_log_in, R.id.btn_register})
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            /**
             * 获取验证码
             */
            case R.id.get_register_verification_code:

                break;
            /**
             * 显示与隐藏
             */
            case R.id.register_image_eye:
                ShowHidden();
                break;
            /**
             * 已经注册，立即登录
             */
            case R.id.tv_register_immediately_log_in:
                this.finish();
                break;
            /**
             * 注册按钮
             */
            case R.id.btn_register:
                if (checkEdit()) {
                    phone = mEditRegisterPhone.getText().toString().trim();
                    pwd = mEditRegisterPwd.getText().toString().trim();

                    if (!isMobileNO(phone)) {
                        showToast("请输入正确的手机号");
                        return;
                    }
                    if (pwd.length() < 6) {
                        showToast("密码不能少于6个字符");
                        return;
                    }

                    /**
                     * 判断验证码
                     */

                }
                showLoad();
                mPresenter.register(phone, pwd);
                break;
        }
    }

    /**
     * 显示与隐藏
     */
    public void ShowHidden() {
        if (isShowHidden) {

            mEditRegisterPwd.setTransformationMethod(PasswordTransformationMethod.getInstance());
            isShowHidden = false;
        } else {

            mEditRegisterPwd.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
            isShowHidden = true;
        }
    }

    public boolean checkEdit() {
        if (mEditRegisterPhone.getText().toString().trim().equals("")) {
            showToast("用户名不能为空");
        } else if (mEditRegisterPwd.getText().toString().trim().equals("")) {
            showToast("密码不能为空");
        } else {
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
