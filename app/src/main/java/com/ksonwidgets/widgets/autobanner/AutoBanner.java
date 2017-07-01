package com.ksonwidgets.widgets.autobanner;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kson on 2017/6/30.
 */

public class AutoBanner extends FrameLayout {
    private List<String> mImgUrls = new ArrayList<>();

    private List<ImageView> mDotView = new ArrayList<>();
    private Context mContext;

    public AutoBanner(Context context) {
        super(context);
        init(context);
    }

    public AutoBanner(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public AutoBanner(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context){
        this.mContext = context;
    }

    /**
     * 展示广告位
     *
     * @param urls 图片列表数据
     */
    public void displayADs(List<String> urls) {
        FrameLayout layout = new FrameLayout(mContext);
        this.mImgUrls = urls;

        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT,FrameLayout.LayoutParams.WRAP_CONTENT);


        ImageView imageView = new ImageView(mContext);

        imageView.setLayoutParams(params);
        Glide.with(mContext).load(urls.get(0)).into(imageView);


        layout.addView(imageView);

        addView(layout);




    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {

    }
}
