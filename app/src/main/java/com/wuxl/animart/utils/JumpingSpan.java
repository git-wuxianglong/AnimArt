package com.wuxl.animart.utils;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.text.style.ReplacementSpan;

/**
 * Author:wuxianglong;
 * Time:2017/4/10.
 */
public class JumpingSpan extends ReplacementSpan {

    @Override
    public int getSize(Paint paint, CharSequence text, int start, int end, Paint.FontMetricsInt fontMetricsInt) {
        return (int) paint.measureText(text, start, end);
    }

    @Override
    public void draw(Canvas canvas, CharSequence text, int start, int end, float x, int top, int y, int bottom, Paint paint) {
        float translationX = 0;
        float translationY = 0;
        canvas.drawText(text, start, end, x + translationX, y + translationY, paint);
    }
}
