package com.huaan.socialapp;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.library.titlebarlibrary.TitleBar;

public class MainActivity extends BaseActivity {

    private TitleBar mTitleBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        mTitleBar = (TitleBar) findViewById(R.id.titleBar);
        mTitleBar.setOnTitleBarClickListener(new TitleBar.OnTitleBarClickListener() {
            @Override
            public void onLeftBtnClick(View view) {
                Log.i("mTitleBar", "onLeftBtnClick: ");

            }

            @Override
            public void onRightBtnClick(View view) {
                Log.i("mTitleBar", "onRightBtnClick: ");
            }

            @Override
            public void onTitleClick(View view) {
                Log.i("mTitleBar", "onTitleClick: ");
            }
        });
    }
}
