package com.ksonwidgets.widgets;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapRegionDecoder;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.renderscript.ScriptGroup;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by kson on 2017/6/30.
 * 高清大图加载view
 */

public class HdImageView extends View implements GestureDetector.OnGestureListener {
    private GestureDetector gestureDetector;//手势
    private Rect mRect = new Rect();//绘制的区域

    private BitmapFactory.Options mOptions = new BitmapFactory.Options();
    private int mWidth;//图片宽度
    private int mHeight;//图片高度
    private int mScreeWidht;//屏幕宽度

    private InputStream inputStream;

    public HdImageView(Context context) {
        super(context);
    }

    public HdImageView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public HdImageView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    private void init() {
        setClickable(true);//必须加入，确保各手势事件响应
        mScreeWidht = getResources().getDisplayMetrics().widthPixels;
        gestureDetector = new GestureDetector(getContext(), this);
        initBitmapOptions();
    }

    private void initBitmapOptions() {
        mOptions.inJustDecodeBounds = true;
        BitmapFactory.decodeStream(inputStream, null, mOptions);
        mOptions.inPreferredConfig = Bitmap.Config.RGB_565;
        mWidth = mOptions.outWidth;
        mHeight = mOptions.outHeight;
        mRect.set(0, 0, mScreeWidht, mHeight);
    }

    public void setImageView(InputStream inputStream) {
        this.inputStream = inputStream;

        init();

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        gestureDetector.onTouchEvent(event);
        return super.onTouchEvent(event);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        Bitmap bitmap = regionDecoderBitmap();
        canvas.drawBitmap(bitmap, 0, 0, null);
    }


    private Bitmap regionDecoderBitmap() {
        Bitmap bitmap = null;
        try {
            BitmapRegionDecoder decoder = BitmapRegionDecoder.newInstance(inputStream, false);
            bitmap = decoder.decodeRegion(mRect, mOptions);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return bitmap;

    }

    @Override
    public boolean onDown(MotionEvent e) {

        return false;
    }

    @Override
    public void onShowPress(MotionEvent e) {

    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        return false;
    }


    /**
     * @param e1        The first down motion event that started the   scrolling.
     * @param e2        The move motion event that triggered the current onScroll.
     * @param distanceX The distance along the X axis that has been scrolled   since the last call to onScroll. This is NOT the distance between e1 and e2.
     *                  在上一次（最后一次）调用onScroll方法沿着X轴所滑动的距离。不是e1和e2之间的距离
     * @param distanceY The distance along the Y axis that has been scrolled   since the last call to onScroll. This is NOT the distance between e1 and e2.
     * @return
     */
    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        Log.d("onScroll", "------------------");

        mRect.left += distanceX;
        mRect.right = mRect.left + mScreeWidht;
        mRect.top = 0;
        mRect.bottom = mHeight;
        if (mRect.right > mWidth) {
            mRect.right = mWidth;
            mRect.left = mWidth - mScreeWidht;
        }

        if (mRect.left < 0) {
            mRect.left = 0;
            mRect.right = mScreeWidht;
        }

        invalidate();


        return false;
    }

    @Override
    public void onLongPress(MotionEvent e) {

    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        return false;
    }
}
