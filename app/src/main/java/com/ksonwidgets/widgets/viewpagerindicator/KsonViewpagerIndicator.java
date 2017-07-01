package com.ksonwidgets.widgets.viewpagerindicator;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.CornerPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.drawable.GradientDrawable;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ksonwidgets.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kson on 2017/7/1.
 */

public class KsonViewpagerIndicator extends LinearLayout implements ViewPager.OnPageChangeListener {
    private List<String> mTitles;
    private ViewPager mViewPager;
    private int count;
    private Context context;
    private List<View> views;
    private TableOnclickListener tableClickListener;
    private int visibleCount = 3;
    private int screenWidth;
    private ImageView dotView;

    /**
     * 标题正常时的颜色
     */
    private static final int COLOR_TEXT_NORMAL = 0x77FFFFFF;
    /**
     * 标题选中时的颜色
     */
    private static final int COLOR_TEXT_HIGHLIGHTCOLOR = 0xFFFFFFFF;


    //三角形相关
    /**
     * 初始时，三角形指示器的偏移量
     */
    private int mInitTranslationX;
    /**
     * 手指滑动时的偏移量
     */
    private float mTranslationX;
    /**
     * 绘制三角形的画笔
     */
    private Paint mPaint;
    /**
     * path构成一个三角形
     */
    private Path mPath;
    /**
     * 三角形的宽度
     */
    private int mTriangleWidth;
    /**
     * 三角形的高度
     */
    private int mTriangleHeight;
    /**
     * 三角形的宽度为单个Tab的1/6
     */
    private static final float RADIO_TRIANGEL = 1.0f / 6;

    public KsonViewpagerIndicator(Context context) {
        this(context, null);
    }

    public KsonViewpagerIndicator(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public KsonViewpagerIndicator(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    /**
     * 初始化相关参数
     *
     * @param context
     */
    private void init(Context context, AttributeSet attr) {
        this.context = context;

        mTitles = new ArrayList<>();
        views = new ArrayList<>();


        screenWidth = getResources().getDisplayMetrics().widthPixels;


        TypedArray typeArray = context.obtainStyledAttributes(attr, R.styleable.ViewPagerIndicator);
        visibleCount = typeArray.getInt(R.styleable.ViewPagerIndicator_item_count, visibleCount);

        typeArray.recycle();

        // 初始化画笔
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setColor(Color.RED);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setPathEffect(new CornerPathEffect(3));

    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mTriangleWidth = (int) (w / visibleCount * RADIO_TRIANGEL);// 1/6 of
        // width

        // 初始化三角形
        initTriangle();

        // 初始时的偏移量
        mInitTranslationX = getWidth() / visibleCount / 2 - mTriangleWidth
                / 2;
    }

    /**
     * 初始化三角形指示器
     */
    private void initTriangle() {
        mPath = new Path();

        mTriangleHeight = (int) (mTriangleWidth / 2 / Math.sqrt(2));
        mPath.moveTo(0, 0);
        mPath.lineTo(mTriangleWidth, 0);
        mPath.lineTo(mTriangleWidth / 2, -mTriangleHeight);
        mPath.close();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.save();
        // 画笔平移到正确的位置
        canvas.translate(mInitTranslationX + mTranslationX, getHeight());
        canvas.drawPath(mPath, mPaint);
        canvas.restore();
    }

    public KsonViewpagerIndicator setTableTitles(List<String> titles) {
        this.mTitles = titles;
        count = titles.size();

        setBackgroundColor(0x90000000);

        LinearLayout.LayoutParams param = new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        param.width = screenWidth / visibleCount;
        for (int i = 0; i < count; i++) {

            TextView textView = new TextView(context);
            textView.setTextColor(COLOR_TEXT_NORMAL);
            textView.setGravity(Gravity.CENTER);
            textView.setPadding(50, 20, 50, 20);
            textView.setText(mTitles.get(i));

            final int finalI = i;
            textView.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {

                    tableClickListener.onTableOnclicker(finalI);
                    setTextViewColor(finalI);
                    mViewPager.setCurrentItem(finalI);

                }
            });
            addView(textView, param);

            views.add(textView);


        }
        dotView = new ImageView(context);
        dotView.setImageResource(R.drawable.indicator_selected);
        addView(dotView);

        return this;

    }

    public KsonViewpagerIndicator bindViewPager(ViewPager viewPager) {

        this.mViewPager = viewPager;
        mViewPager.addOnPageChangeListener(this);

        return this;
    }


    /**
     * 监听viewpager滚动
     *
     * @param position
     * @param positionOffset       容器移动的百分比
     * @param positionOffsetPixels 容器移动的像素数
     */
    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        int tabWidth = screenWidth / visibleCount;
// 不断改变偏移量，invalidate
        mTranslationX = getWidth() / visibleCount * (position + positionOffset);
        // 容器滚动，当移动到倒数最后一个的时候，开始滚动
        if (positionOffset > 0 && position >= (visibleCount - 2)
                && getChildCount() > visibleCount) {
            if (visibleCount == 1) {
                scrollTo(
                        position * tabWidth + (int) (tabWidth * positionOffset), 0);
            } else {
                scrollTo((position - (visibleCount - 2)) * tabWidth//一共第几个长度加上实时的偏移量
                        + (int) (tabWidth * positionOffset), 0);
            }
        }

        invalidate();
    }

    @Override
    public void onPageSelected(int position) {

        setTextViewColor(position);

    }

    private void setTextViewColor(int position) {

        for (int i = 0; i < getChildCount(); i++) {
            View view = getChildAt(i);

            if (i == position) {

                if (view instanceof TextView) {
                    ((TextView) view).setTextColor(COLOR_TEXT_HIGHLIGHTCOLOR);
                }

            } else {
                if (view instanceof TextView) {
                    ((TextView) view).setTextColor(COLOR_TEXT_NORMAL);
                }
            }

        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    public void setTableClickListener(TableOnclickListener listener) {
        this.tableClickListener = listener;

    }

    public interface TableOnclickListener {
        void onTableOnclicker(int position);
    }
}
