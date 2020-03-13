package com.one.player;

import android.os.Bundle;
import android.view.View;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.one.player.view.BackgroundAnimationRelativeLayout;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import jp.wasabeef.glide.transformations.BlurTransformation;

public class MainActivity extends AppCompatActivity {


    private List<Integer> mMusicDatas = new ArrayList<>();
    private BackgroundAnimationRelativeLayout mBackgroundAnimationRelativeLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mBackgroundAnimationRelativeLayout = findViewById(R.id.rootLayout);

        mMusicDatas.add(R.drawable.ic_music1);
        mMusicDatas.add(R.drawable.ic_music2);
        mMusicDatas.add(R.drawable.ic_music3);


        findViewById(R.id.ivPlayOrPause).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Glide.with(MainActivity.this)
                        .load(R.drawable.ic_music3)
                        .crossFade(500)
                        .bitmapTransform(new BlurTransformation(MainActivity.this, 200, 3))
                        .into(new SimpleTarget<GlideDrawable>() {
                            @Override
                            public void onResourceReady(GlideDrawable resource, GlideAnimation<? super GlideDrawable> glideAnimation) {
                                mBackgroundAnimationRelativeLayout.setForeground(resource);
                            }
                        });
            }
        });
    }
}
