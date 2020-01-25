package com.one.netease.screenadapter;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

/**
 * @author diaokaibin@gmail.com on 2020-01-25.
 */
public class BaseActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 方式一
        Density.setDensity(getApplication(), this);

    }
}
