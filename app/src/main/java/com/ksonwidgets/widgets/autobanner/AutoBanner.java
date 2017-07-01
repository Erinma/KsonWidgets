package com.ksonwidgets.widgets.autobanner;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.ksonwidgets.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kson on 2017/6/30.
 */

public class AutoBanner extends FrameLayout {

    private Context mContext;
    private int mIndicatorWidth, mIndicatorHeight, mIndicatorMargin;
    private List<String> mImgUrls;
    private List<View> mImageViews;
    private List<ImageView> mIndicatorViews;
    private LinearLayout mIndicatorLayout;
    private ViewPager mViewPager;
    private AutoBannerListener mAutoBannerListener;
    private int count;

    public AutoBanner(Context context) {
        this(context, null);
    }

    public AutoBanner(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public AutoBanner(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        this.mContext = context;
        mImageViews = new ArrayList<>();
        mImgUrls = new ArrayList<>();
        mIndicatorViews = new ArrayList<>();
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.AutoBanner);
        mIndicatorWidth = typedArray.getDimensionPixelSize(R.styleable.AutoBanner_indicator_width, 30);
        mIndicatorHeight = typedArray.getDimensionPixelSize(R.styleable.AutoBanner_indicator_height, 30);
        mIndicatorMargin = typedArray.getDimensionPixelSize(R.styleable.AutoBanner_indicator_margin, 10);

        typedArray.recycle();

        View view = LayoutInflater.from(context).inflate(R.layout.auto_banner_layout, this, true);
        mViewPager = (ViewPager) view.findViewById(R.id.banner_viewpager);
        mIndicatorLayout = (LinearLayout) view.findViewById(R.id.circleIndicator);


    }

    /**
     * 设置图片源
     *
     * @param imgs
     * @return
     */
    public AutoBanner load(List<String> imgs) {
        this.mImgUrls = imgs;
        this.count = mImgUrls.size();
        return this;
    }

    /**
     * 加载和显示banner
     *
     * @return
     */
    public AutoBanner display() {
        setImages(mImgUrls);
        setIndicators();
        return this;
    }


    public AutoBanner setAnimation() {

        return this;

    }


    private void setImages(List<String> mImgUrls) {


    }

    private void setIndicators() {

        mIndicatorViews.clear();
        mIndicatorLayout.removeAllViews();
        if (count < 2) {//一个view时不绘制指示器
            return;
        }
        for (int i = 0; i < count; i++) {

            ImageView imageView = new ImageView(mContext);
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(mIndicatorWidth, mIndicatorHeight);
            layoutParams.leftMargin = mIndicatorMargin;
            layoutParams.rightMargin = mIndicatorMargin;
            imageView.setImageResource(R.drawable.indicator_unselect);

            mIndicatorViews.add(imageView);

            mIndicatorLayout.addView(imageView, layoutParams);


        }


    }

    class ViewPagerAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return 0;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return false;
        }
    }


    public AutoBanner setOnAutoBannerListener(AutoBannerListener listener) {

        this.mAutoBannerListener = listener;

        return this;

    }


    public interface AutoBannerListener {
        void onClickListener(int postion);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {

        super.onLayout(changed, l, t, r, b);

    }
}
