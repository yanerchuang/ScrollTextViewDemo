package com.ywj.scrolltextviewdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    private String content = "测试内容测试内容测试内容测试内容测试内容测试内容测试内容测试内容测试内容测试内容";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ScrollTextView scrollTextView1 = findViewById(R.id.scrollTextView1);
        scrollTextView1.setText(content);
        scrollTextView1.setSpeed(-3);
        scrollTextView1.setFPS(100);
        scrollTextView1.startScroll();

        ScrollTextView scrollTextView2 = findViewById(R.id.scrollTextView2);
        scrollTextView2.setText(content);
        scrollTextView2.setSpeed(-3);
        scrollTextView2.startScroll();

        ScrollTextView scrollTextView3 = findViewById(R.id.scrollTextView3);
        scrollTextView3.setText(content);
        scrollTextView3.setSpeed(2);
        scrollTextView3.startScroll();

        SimpleScroolTextView tvMarqueeView = findViewById(R.id.tvMarqueeView);
        tvMarqueeView.setText(content);

    }
}
