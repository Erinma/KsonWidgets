package com.ksonwidgets.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.ksonwidgets.R;
import com.ksonwidgets.widgets.autobanner.AutoBanner;

import java.util.ArrayList;
import java.util.List;

public class AutoBannerActivity extends AppCompatActivity implements AutoBanner.AutoBannerListener {

    private AutoBanner mAutoBanner;
    private List<String> mImgUrls;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auto_banner);
        setTitle("AutoBanner demo");

        mAutoBanner = (AutoBanner) findViewById(R.id.auto_banner);

        mImgUrls = new ArrayList<>();

        mImgUrls.add("http://pic.58pic.com/58pic/13/61/00/61a58PICtPr_1024.jpg");
        mImgUrls.add("http://www.bz55.com/uploads/allimg/150525/139-150525111037.jpg");
        mImgUrls.add("http://www.bz55.com/uploads/allimg/141226/139-141226142542-50.jpg");


        //不设置setAuto，不会自动轮播，不设置监听，无法点击
        mAutoBanner.load(mImgUrls).setOnAutoBannerListener(this).setAuto(3000).display();


    }

    @Override
    public void onClickListener(int postion) {
        Toast.makeText(AutoBannerActivity.this, "点击了：" + postion + "位置", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onStart() {
        super.onStart();
        mAutoBanner.startAuto();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mAutoBanner.stopAuto();
    }
}
