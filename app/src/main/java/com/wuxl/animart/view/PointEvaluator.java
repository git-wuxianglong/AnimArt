package com.wuxl.animart.view;

import android.animation.TypeEvaluator;

import com.wuxl.animart.bean.Point;

/**
 * Author:wuxianglong;
 * Time:2016/12/21.
 */
public class PointEvaluator implements TypeEvaluator {

    @Override
    public Object evaluate(float v, Object start, Object end) {
        Point startPoint = (Point) start;
        Point endPoint = (Point) end;
        float x = startPoint.getX() + v * (endPoint.getX() - startPoint.getX());
        float y = startPoint.getY() + v * (endPoint.getY() - startPoint.getY());
        Point point = new Point(x, y);
        return point;
    }
}
