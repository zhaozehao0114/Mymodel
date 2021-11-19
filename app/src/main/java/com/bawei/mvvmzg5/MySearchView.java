package com.bawei.mvvmzg5;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class MySearchView extends androidx.appcompat.widget.AppCompatEditText {
    private Paint mPaint;
    private RectF rectF;
    int color=Color.BLACK;
    public MySearchView(@NonNull  Context context) {
        super(context);
        initPaint();
    }



    public MySearchView(@NonNull  Context context, @Nullable  AttributeSet attrs) {
        super(context, attrs);

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.MySearchView);

        color = typedArray.getColor(R.styleable.MySearchView_colora, Color.RED);

        initPaint();

    }

    public MySearchView(@NonNull  Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initPaint();
    }
    private void initPaint() {
        mPaint=new Paint();
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(5);
        mPaint.setColor(color);
        setBackground(null);
    }
    private float rx=30.0f;
    private float ry=30.0f;
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        rectF = new RectF(0+15, 0+15, getMeasuredWidth()-15, getMeasuredHeight()-15);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawRoundRect(rectF,rx,ry,mPaint);
    }
}
