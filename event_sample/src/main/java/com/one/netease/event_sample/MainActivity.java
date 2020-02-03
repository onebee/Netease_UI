package com.one.netease.event_sample;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = "onebit>>>";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onStart() {
        super.onStart();

    }

    @Override
    protected void onStop() {
        super.onStop();


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().unregister(this);
        }
    }

    @Subscribe
    public void event(String string) {
             Log.i(TAG,"MainActivity receive event:" + string);
    }

    @Subscribe(priority = 10,sticky = true)
    public void event2(String string) {
        Log.i(TAG," MainActivity receive event2 : " + string);
    }

    public void jump(View view) {

        EventBus.getDefault().postSticky("sticky");
        startActivity(new Intent(this, SecondActivity.class));
    }

}
