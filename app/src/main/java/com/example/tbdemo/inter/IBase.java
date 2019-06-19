package com.example.tbdemo.inter;

import android.os.Bundle;
import android.view.View;

public interface IBase {

    int getContentLayout();
    void inject();
    void initView(View view);
}
