package com.one.netease.event_sample;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.one.netease.EventBus;
import com.one.netease.event_sample.bean.EventBean;

import androidx.appcompat.app.AppCompatActivity;

public class SecondActivity extends AppCompatActivity {

    public static final String TAG = "onebit>>>";

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

//        EventBus.getDefault().post("one");
//        EventBus.getDefault().register(this);


        Log.i(TAG, "SecondActivity : thread " + Thread.currentThread().getName());

        EventBus.getDefault().post(new EventBean("one bit"));

        finish();
    }

//    @Subscribe(sticky = true)
//    public void sticky(String string) {
//        Log.i(TAG,"SecondActivity receive sticky event : " + string);
//    }
//
//    @Override
//    protected void onDestroy() {
//        super.onDestroy();
//        if (EventBus.getDefault().isRegistered(this)) {
//            EventBus.getDefault().unregister(this);
//        }
//    }
}
