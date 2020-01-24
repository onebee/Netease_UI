package com.one.netease.screensample;

import android.content.Context;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.WindowManager;
import android.widget.TextView;

import com.one.netease.screensample.ui.UIUtils;
import com.one.netease.screensample.ui.ViewCalculateUtil;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    TextView tv3;
    TextView tv4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        UIUtils.getInstance(getApplicationContext());

         tv3=findViewById(R.id.tv3);
         tv4=findViewById(R.id.tv4);


        ViewCalculateUtil.setViewLayoutParam(tv3,1080,50,0,0,0,0);
        ViewCalculateUtil.setViewLayoutParam(tv4,540,50,0,0,0,0);


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
