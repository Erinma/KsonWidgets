package com.ksonwidgets.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.ksonwidgets.R;
import com.ksonwidgets.widgets.HdImageView;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author kson
 * @description  加载大图
 */
public class HDImageViewActivity extends AppCompatActivity {
    private HdImageView mHdView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hdimage_view);

        init();
    }

    private void init() {
        setTitle("大图加载");
        mHdView = (HdImageView) findViewById(R.id.image);
        try {
            InputStream inputStream = getAssets().open("qmsht.jpg");
            if (inputStream!=null)
            mHdView.setImageView(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
