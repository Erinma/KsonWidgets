package com.ksonwidgets.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
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

        mAutoBanner = (AutoBanner) findViewById(R.id.auto_banner);

        mImgUrls = new ArrayList<>();

        mImgUrls.add("http://pic.58pic.com/58pic/13/61/00/61a58PICtPr_1024.jpg");
        mImgUrls.add("http://pic.58pic.com/58pic/13/61/00/61a58PICtPr_1024.jpg");
        mImgUrls.add("http://pic.58pic.com/58pic/13/61/00/61a58PICtPr_1024.jpg");
        mImgUrls.add("http://pic.58pic.com/58pic/13/61/00/61a58PICtPr_1024.jpg");


        mAutoBanner.load(mImgUrls).setOnAutoBannerListener(this).display();


    }

    @Override
    public void onClickListener(int postion) {
        Toast.makeText(AutoBannerActivity.this, "点击了：" + postion + "位置", Toast.LENGTH_SHORT).show();
    }
}
