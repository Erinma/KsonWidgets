package com.ksonwidgets.widgets;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by kson on 2017/7/2.
 * 练习path
 */

public class PathView extends View {
    private Paint mPaint;
    private Path mPath;
    public PathView(Context context) {
        this(context,null);
    }

    public PathView(Context context, @Nullable AttributeSet attrs) {
        this(context,attrs,0);
    }

    public PathView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        init();
    }

    private void init() {

        mPaint = new Paint();
        mPaint.setColor(Color.RED);// 颜色
        mPaint.setStyle(Paint.Style.STROKE);//设置填充 镂空
        mPaint.setStrokeWidth(2);//画笔宽度
        mPaint.setAntiAlias(true);//消除锯齿

        mPath = new Path();
        mPath.moveTo(100,0);
        mPath.lineTo(0,100);
        mPath.lineTo(200,100);
        mPath.lineTo(100,0);
        mPath.close();

//        mPath.lineTo(100,200);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawPath(mPath,mPaint);
    }
}
