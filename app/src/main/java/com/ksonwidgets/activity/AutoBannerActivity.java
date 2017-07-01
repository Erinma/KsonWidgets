package com.ksonwidgets.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.ksonwidgets.R;
import com.ksonwidgets.widgets.autobanner.AutoBanner;

import java.util.ArrayList;
import java.util.List;

public class AutoBannerActivity extends AppCompatActivity {

    private AutoBanner carouselImagesView;
    private List<String> mImgUrls;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carousel_images);

        mImgUrls = new ArrayList<>();

        mImgUrls.add("http://pic.58pic.com/58pic/13/61/00/61a58PICtPr_1024.jpg");

        carouselImagesView = (AutoBanner) findViewById(R.id.images);

        carouselImagesView.displayADs(mImgUrls);

    }
}
