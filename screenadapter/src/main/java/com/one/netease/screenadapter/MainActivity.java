package com.one.netease.screenadapter;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        Density.setDensity(getApplication(),this);
        setContentView(R.layout.main1);

    }
}
