package com.one.netease.screensample;

import android.content.Context;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    @Override
    protected void onStart() {
        super.onStart();

        DisplayMetrics displayMetrics = new DisplayMetrics();

//        Log.i(">>>","         displayMetrics.heightPixels : " +         displayMetrics.heightPixels);
//        Log.i(">>>","         displayMetrics.widthPixels : " +         displayMetrics.widthPixels);

    }

    @Override
    protected void onResume() {
        super.onResume();

        WindowManager windowManager = (WindowManager) getSystemService(Context.WINDOW_SERVICE);

        DisplayMetrics displayMetrics = new DisplayMetrics();

        windowManager.getDefaultDisplay().getMetrics(displayMetrics);

        Log.i(">>>","displayMetrics.heightPixels : " +   displayMetrics.heightPixels);
        Log.i(">>>","displayMetrics.widthPixels : " +  displayMetrics.widthPixels);

    }

    @Override
    protected void onStop() {
        super.onStop();


    }
}
