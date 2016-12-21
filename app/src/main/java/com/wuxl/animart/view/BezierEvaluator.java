package com.wuxl.animart.view;

import android.animation.TypeEvaluator;
import android.graphics.PointF;

/**
 * Author:wuxianglong;
 * Time:2016/12/20.
 */
public class BezierEvaluator implements TypeEvaluator<PointF> {

    private PointF pointF1;
    private PointF pointF2;

    public BezierEvaluator(PointF pointF1, PointF pointF2) {
        this.pointF1 = pointF1;
        this.pointF2 = pointF2;
    }

    @Override
    public PointF evaluate(float v, PointF startValue, PointF endValue) {
        float vLeft = 1.0f - v;
        PointF point = new PointF();

        PointF start = startValue;
        PointF end = endValue;

        point.x = vLeft * vLeft * vLeft * (start.x)
                + 3 * vLeft * vLeft * v * (pointF1.x)
                + 3 * vLeft * v * v * (pointF2.x)
                + v * v * v * (end.x);

        point.y = vLeft * vLeft * vLeft * (start.y)
                + 3 * vLeft * vLeft * v * (pointF1.y)
                + 3 * vLeft * v * v * (pointF2.y)
                + v * v * v * (end.y);

        return point;
    }
}
