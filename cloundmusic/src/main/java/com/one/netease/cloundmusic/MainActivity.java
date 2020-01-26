package com.one.netease.cloundmusic;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.one.netease.cloundmusic.util.StatusBarUtil;
import com.one.netease.cloundmusic.util.UIUtils;

import java.lang.reflect.Field;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        UIUtils.getInstance(this);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        toolbar.setOverflowIcon(ContextCompat.getDrawable(this, R.drawable.more));
        StatusBarUtil.setStateBar(this,toolbar);


//        setToolbar(toolbar);


//        方式1 把状态栏的高度放出来
//        ViewGroup contentFrameLayout = (ViewGroup) findViewById(android.R.id.content);
//        View parentView = contentFrameLayout.getChildAt(0);
//        if (parentView != null) {
//            parentView.setFitsSystemWindows(true);
//        }

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
