package com.ksonwidgets;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.ksonwidgets.activity.AutoBannerActivity;
import com.ksonwidgets.activity.HDImageViewActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
    }

    private void initViews() {
    }


    public void btn1(View view){
        startActivity(new Intent(this,HDImageViewActivity.class));

    }

    public void btn2(View view){
        startActivity(new Intent(this,AutoBannerActivity.class));

    }


}
