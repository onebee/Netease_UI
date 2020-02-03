package com.one.netease.event_sample;

import android.os.Bundle;
import android.view.View;

import org.greenrobot.eventbus.EventBus;

import androidx.appcompat.app.AppCompatActivity;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
    }

    @Override
    protected void onStart() {
        super.onStart();
    }


    @Override
    protected void onStop() {
        super.onStop();

    }

    public void post(View view) {

        EventBus.getDefault().post("one");
    }
}
