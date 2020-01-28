package com.one.netease.customokhttp;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.one.netease.okhttp.Call2;
import com.one.netease.okhttp.Callback2;
import com.one.netease.okhttp.OkHttpClient2;
import com.one.netease.okhttp.Request2;
import com.one.netease.okhttp.Response2;

import java.io.IOException;

import androidx.appcompat.app.AppCompatActivity;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    public static final String PATH = "https://wanandroid.com/wxarticle/chapters/json";
    public static final String TAG = "okhttp";
    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv = findViewById(R.id.tv);

    }

    public void okhttp(View view) {

        OkHttpClient okHttpClient = new OkHttpClient.Builder().build();
        final Request request = new Request.Builder().url(PATH).build();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e(TAG, e.getMessage());

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                Log.i(TAG, "response : " + response.body().string());

            }
        });


    }

    public void myOkhttp(View view) {

        OkHttpClient2 okHttpClient2 = new OkHttpClient2.Builder().build();

        final Request2 request2 = new Request2.Builder().url(PATH).build();

        Call2 call2 = okHttpClient2.newCall(request2);

        call2.enqueue(new Callback2() {
            @Override
            public void onFailure(Call2 call, IOException e) {
                Log.i(TAG, "myokhttp 请求失败" + e.getMessage());
            }

            @Override
            public void onResponse(Call2 call, Response2 response) throws IOException {
                Log.i(TAG, "myokhttp 请求成功: " + response.string());
            }
        });
    }
}
