package com.one.netease.sourcecode;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private static String TAG = "bit";

    private TextView textView;

    private Handler handler1 = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(@NonNull Message msg) {

            Log.i(TAG, "handler1 receive : " + msg);

//            textView.setText(msg.obj + "  -- " + msg.what);

            startActivity(new Intent(MainActivity.this,PersonalActivity.class));

            return false;
        }

    });

    private Handler handler2 = new Handler() {

        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);

            Log.i(TAG, "handler2 receive : " + msg);


        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.tv);

        test();
    }

    private void test() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Message message = new Message();
//                message.obj = "Net163";
//                message.what = 163;

//                SystemClock.sleep(3000);
//                 handler2.sendMessage(message);
                message.what = 3;
//                handler1.sendMessage(message);

//                new Handler();
                textView.setText("dddd");
                Toast.makeText(MainActivity.this, "dddd", Toast.LENGTH_SHORT).show();
        if (handler1!=null)
                handler1.sendMessageDelayed(message, 3000);



        new Handler();
            }
        }).start();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        handler1.removeMessages(3);
        handler1=null;
        Log.e(TAG, "  onDestroy -- ");
    }
}
