package com.one.handlersamploe;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.util.Log;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {


    private TextView tv;

//    Message message = new Message();
//                 message.what = 3;

    private Handler handler1 = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(@NonNull Message msg) {

            if (msg.obj.equals("打开新界面")) {
                Intent intent = new Intent(MainActivity.this, MainActivity2.class);
                startActivity(intent);
            }
            return false;
        }
    });


    private Handler handler2 = new Handler() {

        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);


            tv.setText(msg.obj.toString());
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv = findViewById(R.id.tv);
        new Thread(new Runnable() {
            @Override
            public void run() {


//                 message.obj = "网易";
//                 handler2.sendMessage(message);

//                try {
//                    Thread.sleep(3000);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }

//                SystemClock.sleep(3000);
//                message.what = 3;
//                message.obj = "打开新界面";
//                handler1.sendMessage(message);
//                handler1.sendMessageDelayed(message,3000);


                SystemClock.sleep(1000);
                tv.setText("hande");
//                Toast.makeText(MainActivity.this, "hhhhhaaa", Toast.LENGTH_SHORT).show();
            }
        }).start();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.e("-----","  onDestory -------------");
//        handler1.removeMessages(3);
//        message.recycle();
    }
}
