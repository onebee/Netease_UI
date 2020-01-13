package com.one.netease.ui;

import android.os.Bundle;

import com.one.netease.ui.path.PathMeasureView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new PathMeasureView(this));
    }


}
