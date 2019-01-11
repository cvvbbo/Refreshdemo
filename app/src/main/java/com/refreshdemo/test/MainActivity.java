package com.refreshdemo.test;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.refreshdemo.test.Smartrefreashdemo.SmartRefreshActivity;
import com.refreshdemo.test.springviewdemo.SpringviewActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void haha(View view){
        startActivity(new Intent(MainActivity.this, SpringviewActivity.class));
    }

    public void hehe(View view){
        startActivity(new Intent(MainActivity.this, SmartRefreshActivity.class));
    }

}
