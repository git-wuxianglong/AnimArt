package com.wuxl.animart.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.wuxl.animart.R;

import static android.content.ContentValues.TAG;

/**
 * Author:wuxianglong;
 * Time:2016/12/21.
 */
public class ElasticBallView extends View {

    private float circleX;
    private float circleY;

    private Paint paintCircle;

    public ElasticBallView(Context context) {
        super(context);
    }

    public ElasticBallView(Context context, AttributeSet attrs) {
        super(context, attrs);

        paintCircle = new Paint(Paint.ANTI_ALIAS_FLAG);
        paintCircle.setStrokeWidth(5);
        paintCircle.setStyle(Paint.Style.FILL_AND_STROKE);
        paintCircle.setColor(getResources().getColor(R.color.colorBlue));
    }

    public ElasticBallView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        circleX = getMeasuredWidth() / 2;
        circleY = getMeasuredHeight() / 2;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawCircle(circleX, circleY, 25, paintCircle);
    }
}
