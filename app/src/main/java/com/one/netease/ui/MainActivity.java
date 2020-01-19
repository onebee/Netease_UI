package com.one.netease.ui;

import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;

import com.one.netease.ui.MyView.MyView;
import com.one.netease.ui.MyView.MyViewGroup;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MyViewGroup group = new MyViewGroup(this);

        MyView myView = new MyView(this);


        myView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {


                return false;
            }
        });


        myView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });


    }


    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        return super.dispatchTouchEvent(ev);
    }
}
