package com.swt.shoppingmall.app;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import com.swt.shoppingmall.R;

public class RootActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_root);
        //两秒钟进入主页面
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                //执行在主线程
                startActivity(new Intent(RootActivity.this, MainActivity.class));
                finish();
            }
        }, 2000);
    }
}
