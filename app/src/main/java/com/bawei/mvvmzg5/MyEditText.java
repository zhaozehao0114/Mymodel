package com.bawei.mvvmzg5;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class MyEditText extends androidx.appcompat.widget.AppCompatEditText {
    Paint paint;
    RectF rectF;
    public MyEditText(@NonNull Context context) {
        super(context);
        initPaint();
    }

    public MyEditText(@NonNull  Context context, @Nullable  AttributeSet attrs) {
        super(context, attrs);
        initPaint();
    }

    public MyEditText(@NonNull  Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initPaint();
    }
   private void initPaint(){
       paint = new Paint();
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(10);
        paint.setColor(Color.BLACK);
        setBackground(null);
   }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        rectF = new RectF();
        rectF.left=0;
        rectF.top=0;
        rectF.right=getMeasuredWidth();
        rectF.bottom=getMeasuredHeight();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawRect(rectF,paint);
    }
}
