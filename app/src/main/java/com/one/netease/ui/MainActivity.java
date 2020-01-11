package com.one.netease.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.one.netease.ui.xfermode.XfermodesActivity;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void goTo(View view) {
        startActivity(new Intent(this, XfermodesActivity.class));
    }
}
