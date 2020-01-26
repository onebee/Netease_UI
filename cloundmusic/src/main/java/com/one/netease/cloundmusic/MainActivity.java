package com.one.netease.cloundmusic;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.one.netease.cloundmusic.util.StatusBarUtil;
import com.one.netease.cloundmusic.util.UIUtils;

import java.lang.reflect.Field;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class MainActivity extends AppCompatActivity {

    public final static String IMAGE_URL_MEDIUM = "http://p3.music.126.net/iRbTMHGfy-grDtx7o2T_dA==/109951164009413034.jpg?param=400y400";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        UIUtils.getInstance(this);
        setContentView(R.layout.activity_main);
//        Toolbar toolbar = findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);

//        toolbar.setOverflowIcon(ContextCompat.getDrawable(this, R.drawable.more));
//        StatusBarUtil.setStateBar(this,toolbar);


//        setToolbar(toolbar);


//        方式1 把状态栏的高度放出来
//        ViewGroup contentFrameLayout = (ViewGroup) findViewById(android.R.id.content);
//        View parentView = contentFrameLayout.getChildAt(0);
//        if (parentView != null) {
//            parentView.setFitsSystemWindows(true);
//        }

        initView();
        notifyData();


    }

    private void postImage() {
//        Glide.with(this)
//                .load(IMAGE_URL_MEDIUM)
//                .listener(new RequestListener<String, GlideDrawable>() {
//                    @Override
//                    public boolean onException(Exception e, String model, Target<GlideDrawable> target, boolean isFirstResource) {
//                        Log.i("tuch", "onException: "+e);
//                        return false;
//                    }
//                    @Override
//                    public boolean onResourceReady(GlideDrawable resource, String model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
//                        Log.i("tuch", "onResourceReady: ");
//                        return false;
//                    }
//                }).override(400, 400)
//                .into(header_image_item);
//
//        // "14":模糊度；"3":图片缩放3倍后再进行模糊
//        Glide.with(this)
//                .load(IMAGE_URL_MEDIUM)
//                .error(R.drawable.stackblur_default)
//                .placeholder(R.drawable.stackblur_default)
//                .crossFade(500)
//                .bitmapTransform(new BlurTransformation(this, 200, 3))
//                .into(new SimpleTarget<GlideDrawable>() {
//                    @Override
//                    public void onResourceReady(GlideDrawable resource, GlideAnimation<? super GlideDrawable> glideAnimation) {
//                        lv_header_contail.setBackground(resource);
//                    }
//                });
//        Glide.with(this).load(IMAGE_URL_MEDIUM)
//                .error(R.drawable.stackblur_default)
//                .bitmapTransform(new BlurTransformation(this, 250, 6))// 设置高斯模糊
//                .listener(new RequestListener<String, GlideDrawable>() {//监听加载状态
//                    @Override
//                    public boolean onException(Exception e, String model, Target<GlideDrawable> target, boolean isFirstResource) {
//                        return false;
//                    }
//                    @Override
//                    public boolean onResourceReady(GlideDrawable resource, String model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
//                        toolbar.setBackgroundColor(Color.TRANSPARENT);
//                        toolbar_bg.setImageAlpha(0);
//                        toolbar_bg.setVisibility(View.VISIBLE);
//                        return false;
//                    }
//                }).into(toolbar_bg);
    }


    private void notifyData() {
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(this);
        mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        music_recycler.setLayoutManager(mLayoutManager);
        // 需加，不然滑动不流畅
        music_recycler.setNestedScrollingEnabled(false);
        music_recycler.setHasFixedSize(false);
        final MusicAdapter adapter = new MusicAdapter(this);
        adapter.notifyDataSetChanged();
        music_recycler.setAdapter(adapter);
    }

    //
    Toolbar toolbar;
    ImageView toolbar_bg;
    ImageView header_bg;
    RecyclerView music_recycler;
    LinearLayout lv_header_contail;
    ImageView header_music_log;
    ImageView header_image_item;
    MyNestedScrollView myNestedScrollView;
    private void initView() {
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setOverflowIcon(ContextCompat.getDrawable(this, R.drawable.more));


        toolbar_bg = findViewById(R.id.toolbar_bg);
        header_bg = findViewById(R.id.header_bg);
        music_recycler = findViewById(R.id.music_recycler);
        myNestedScrollView = findViewById(R.id.nsv_scrollview);
        header_music_log = findViewById(R.id.header_music_log);
        LinearLayout lv_header_detail = findViewById(R.id.lv_header_detail);
        RelativeLayout rv_header_container = findViewById(R.id.rv_header_container);
        lv_header_contail = findViewById(R.id.lv_header_contail);
        header_image_item = findViewById(R.id.header_image_item);

//        ViewCalculateUtil.setViewLayoutParam(toolbar, 1080, 164, 0, 0, 0, 0);
//        ViewCalculateUtil.setViewLinearLayoutParam(rv_header_container,1080,770,164,0,0,0);
//        ViewCalculateUtil.setViewLayoutParam(toolbar,1080, 164, 0, 0, 0, 0);
//        ViewCalculateUtil.setViewLayoutParam(toolbar_bg,1080,164+UIUtils.getInstance().getSystemBarHeight(this),0,0,0,0);
//        ViewCalculateUtil.setViewLayoutParam(header_bg, 1080, 850, 0, 0, 0, 0);
//        ViewCalculateUtil.setViewLayoutParam(lv_header_detail, 1080, 380, 72, 0, 52, 0);
//        ViewCalculateUtil.setViewLinearLayoutParam(header_image_item,380,380);
//        ViewCalculateUtil.setViewLayoutParam(header_music_log,60,60,59,0,52,0);

        StatusBarUtil.setStateBar(this, toolbar);



    }

    /**
     * 通过反射暴露出Toolbar 未暴露的方法
     * @param toolbar
     */
    private void setToolbar(Toolbar toolbar) {
        //获取成员变量
        Field f = null;
        try {
            f = toolbar.getClass().getDeclaredField("mSubtitleTextView");

            //设置可访问
            f.setAccessible(true);
            //获取到mSubtitleTextView的实例
            //这里使用final是为了方便下面在匿名内部类里使用
            //传入的是toolbar实例
            final TextView tv = (TextView) f.get(toolbar);
            //为mSubtitleTextView设置省略号显示在开头部位
            tv.setEllipsize(TextUtils.TruncateAt.START);
            tv.setPadding(200,0,0,0);
            //设置监听器
            tv.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    //创建对话框并根据输入的路径跳转，代码较长，省略。
                    Toast.makeText(MainActivity.this, "hahah", Toast.LENGTH_SHORT).show();
                }
            });
            //同上
            tv.setOnLongClickListener(new View.OnLongClickListener() {
                public boolean onLongClick(View v) {
                    return true;
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_music, menu);
        return true;
    }

}
