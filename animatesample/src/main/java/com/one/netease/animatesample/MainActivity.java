package com.one.netease.animatesample;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.one.netease.animatesample.animator.LineInterpolator;
import com.one.netease.animatesample.animator.MyObjectAnimator;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = findViewById(R.id.btn);

        final MyObjectAnimator animator = MyObjectAnimator.ofFloat(button,"scaleX",1F,2f);
        animator.setDuration(2000);
        animator.setInterpolator(new LineInterpolator());
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                animator.start();
            }
        });
    }
}
