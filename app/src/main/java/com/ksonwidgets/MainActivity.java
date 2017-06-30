package com.ksonwidgets;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.ksonwidgets.activity.HDImageViewActivity;

public class MainActivity extends AppCompatActivity {
    private Button mBtn1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
    }

    private void initViews() {
        mBtn1 = (Button) findViewById(R.id.button1);
    }


    public void btn1(View view){
        startActivity(new Intent(this,HDImageViewActivity.class));

    }


}
