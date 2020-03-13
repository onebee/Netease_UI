package com.one.netease.event_sample;

import android.content.Context;
import android.media.AudioManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.one.netease.EventBus;
import com.one.netease.event_sample.bean.EventBean;
import com.one.netease.library.annotation.Subscribe;
import com.one.netease.library.mode.ThreadMode;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {


    private TextView tv;
    TextView tv1;
    int size = 55;
    public static final String TAG = "onebit>>>";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv = findViewById(R.id.tv);

//        EventBus.getDefault().register(this);
        EventBus.getDefault().register(this);

        tv1 = findViewById(R.id.btn_audio);
        final AudioManager am = (AudioManager) this
                .getSystemService(Context.AUDIO_SERVICE);

        final Toast toast = Toast.makeText(this, "ddd", Toast.LENGTH_SHORT);
        final TextView tv = new TextView(this);
        tv.setText("哈哈哈");
        tv.setTextSize(55);
        toast.setView(tv);


        tv1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean active = am.isMusicActive();
                tv.setTextSize(size - 5);
                size = size - 5;
                toast.show();
            }
        });

//        tv.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//            }
//        });

        findViewById(R.id.btn1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tv.setTextSize(size);
                size = size + 5;
                toast.show();
            }
        });


    }


    @Override
    protected void onStart() {
        super.onStart();

    }

    @Override
    protected void onStop() {
        super.onStop();


    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void getMessage(EventBean bean) {

        tv.setText(bean.name);

        Log.i(TAG, "MainActivity receive thread : " + Thread.currentThread().getName());
        Log.i(TAG, "MainActivity receive  : " + bean.name);

    }

//    @Override
//    protected void onDestroy() {
//        super.onDestroy();
//        if (EventBus.getDefault().isRegistered(this)) {
//            EventBus.getDefault().unregister(this);
//        }
//    }

    //    @Subscribe
//    public void event(String string) {
//             Log.i(TAG,"MainActivity receive event:" + string);
//    }
//
//    @Subscribe(priority = 10,sticky = true,threadMode = ThreadMode.ASYNC)
//    public void event2(String string) {
//        Log.i(TAG," MainActivity receive event2 : " + string);
//    }
//
    public void jump(View view) {


//
//        EventBus.getDefault().postSticky("sticky");
//        startActivity(new Intent(this, SecondActivity.class));
    }

}
