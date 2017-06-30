package com.ksonwidgets;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapRegionDecoder;
import android.graphics.Rect;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.widget.ImageView;

import com.ksonwidgets.widgets.HdImageView;

import java.io.IOException;
import java.io.InputStream;

public class MainActivity extends AppCompatActivity {
    private HdImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView = (HdImageView) findViewById(R.id.image);

        try {
            InputStream inputStream = getAssets().open("qmsht.jpg");
            imageView.setImageView(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



}
