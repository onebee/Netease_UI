package com.one.netease.ui;

import android.os.Bundle;

import com.one.netease.ui.transform.TransformView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new TransformView(this));
    }


}
