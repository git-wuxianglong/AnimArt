package com.wuxl.animart.view;

import android.animation.ValueAnimator;
import android.graphics.PointF;
import android.view.View;

/**
 * Author:wuxianglong;
 * Time:2016/12/20.
 */
public class BezierListener implements ValueAnimator.AnimatorUpdateListener {

    private View target;

    public BezierListener(View target) {
        this.target = target;
    }

    @Override
    public void onAnimationUpdate(ValueAnimator valueAnimator) {
        //贝塞尔曲线计算出来的x,y值
        PointF pointF = (PointF) valueAnimator.getAnimatedValue();
        //赋值给view
        target.setX(pointF.x);
        target.setY(pointF.y);
        //alpha渐变
        target.setAlpha(1 - valueAnimator.getAnimatedFraction());
    }
}
