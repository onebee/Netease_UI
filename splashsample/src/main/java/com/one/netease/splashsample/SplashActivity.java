package com.one.netease.splashsample;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentActivity;

/**
 * @author diaokaibin@gmail.com on 2020-01-21.
 */
public class SplashActivity extends FragmentActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ParallaxContainer container = findViewById(R.id.parallax_container);

        container.setUp(new int[]{

                R.layout.view_intro_1,
                R.layout.view_intro_2,
                R.layout.view_intro_3,
                R.layout.view_intro_4,
                R.layout.view_intro_5,
                R.layout.view_intro_6,
                R.layout.view_intro_7,
                R.layout.view_login,

        });
    }
}
