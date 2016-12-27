package com.wuxl.animart.view;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.DecelerateInterpolator;

import com.wuxl.animart.bean.Ball;

/**
 * 两个小球旋转动画
 * Author:wuxianglong;
 * Time:2016/12/22.
 */
public class TwoBallView extends View {

    //默认小球最大半径
    private final static int DEFAULT_MAX_RADIUS = 75;
    //默认小球最小半径
    private final static int DEFAULT_MIN_RADIUS = 25;
    //默认两个小球运行轨迹直径距离
    private final static int DEFAULT_DISTANCE = 100;
    //第一个小球颜色
    private final static int DEFAULT_ONE_BALL_COLOR = Color.parseColor("#FF2ACFA2");
    //第二个小球颜色
    private final static int DEFAULT_TWO_BALL_COLOR = Color.parseColor("#FF7A378B");
    //默认动画执行时间
    private final static int DEFAULT_ANIMATOR_DURATION = 1000;
    //球的最大半径
    private float maxRadius = DEFAULT_MAX_RADIUS;
    //球的最小半径
    private float minRadius = DEFAULT_MIN_RADIUS;
    //两球旋转的范围距离
    private int distance = DEFAULT_DISTANCE;
    //动画的时间
    private long duration = DEFAULT_ANIMATOR_DURATION;

    private Ball oneBall;
    private Ball twoBall;

    private float centerX;
    private float centerY;

    private Paint paint;

    private AnimatorSet animatorSet;

    public TwoBallView(Context context) {
        this(context, null);
    }

    public TwoBallView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TwoBallView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        oneBall = new Ball();
        twoBall = new Ball();
        oneBall.setColor(DEFAULT_ONE_BALL_COLOR);
        twoBall.setColor(DEFAULT_TWO_BALL_COLOR);
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        configAnimator();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        centerX = w / 2;
        centerY = h / 2;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        centerX = getWidth() / 2;
        centerY = getWidth() / 2;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (oneBall.getRadius() > twoBall.getRadius()) {
            paint.setColor(twoBall.getColor());
            canvas.drawCircle(twoBall.getCenterX(), centerY, twoBall.getRadius(), paint);
            paint.setColor(oneBall.getColor());
            canvas.drawCircle(oneBall.getCenterX(), centerY, oneBall.getRadius(), paint);
        } else {
            paint.setColor(oneBall.getColor());
            canvas.drawCircle(oneBall.getCenterX(), centerY, oneBall.getRadius(), paint);
            paint.setColor(twoBall.getColor());
            canvas.drawCircle(twoBall.getCenterX(), centerY, twoBall.getRadius(), paint);
        }
    }

    /**
     * 设置动画
     */
    private void configAnimator() {
        //中间半径大小
        float centerRadius = (maxRadius + minRadius) * 0.5f;
        //第一个小球缩放动画，通过改变小球的半径
        //半径变化规律：中间大小->最大->中间大小->最小->中间大小
        ObjectAnimator oneScaleAnimator = ObjectAnimator.ofFloat(oneBall, "radius",
                centerRadius, maxRadius, centerRadius, minRadius, centerRadius);
        //无限循环
        oneScaleAnimator.setRepeatCount(ValueAnimator.INFINITE);
        //第一个小球位移动画，通过改变小球的圆心
        ValueAnimator oneCenterAnimator = ValueAnimator.ofFloat(-1, 0, 1, 0, -1);
        oneCenterAnimator.setRepeatCount(ValueAnimator.INFINITE);
        oneCenterAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                float value = (float) valueAnimator.getAnimatedValue();
                float x = centerX + (distance) * value;
                oneBall.setCenterX(x);
                invalidate();
            }
        });
        //第二个小球缩放动画
        //变化规律：中间大小->最小->中间大小->最大->中间大小
        ObjectAnimator twoScaleAnimator = ObjectAnimator.ofFloat(twoBall, "radius",
                centerRadius, minRadius, centerRadius, maxRadius, centerRadius);
        twoScaleAnimator.setRepeatCount(ValueAnimator.INFINITE);
        //第二个小球位移动画
        ValueAnimator twoCenterAnimator = ValueAnimator.ofFloat(1, 0, -1, 0, 1);
        twoCenterAnimator.setRepeatCount(ValueAnimator.INFINITE);
        twoCenterAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                float value = (float) valueAnimator.getAnimatedValue();
                float x = centerX + (distance) * value;
                twoBall.setCenterX(x);
            }
        });

        //属性动画集合
        animatorSet = new AnimatorSet();
        //四个属性动画一起执行
        animatorSet.playTogether(oneScaleAnimator, oneCenterAnimator, twoScaleAnimator, twoCenterAnimator);
        //动画一次运行时间
        animatorSet.setDuration(DEFAULT_ANIMATOR_DURATION);
        //时间插值器（动画开始最快，结束最慢）
        animatorSet.setInterpolator(new DecelerateInterpolator());
    }

    @Override
    public void setVisibility(int visibility) {
        if (getVisibility() != visibility) {
            super.setVisibility(visibility);
            if (visibility == GONE || visibility == INVISIBLE) {
                stopAnimator();
            } else {
                startAnimator();
            }
        }
    }

    @Override
    protected void onVisibilityChanged(View changedView, int visibility) {
        super.onVisibilityChanged(changedView, visibility);
        if (visibility == GONE || visibility == INVISIBLE)
            stopAnimator();
        else
            startAnimator();
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        startAnimator();
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        stopAnimator();
    }

    /**
     * 开始
     */
    public void startAnimator() {
        if (getVisibility() != VISIBLE)
            return;

        if (animatorSet.isRunning())
            return;

        if (animatorSet != null)
            animatorSet.start();
    }

    /**
     * 结束
     */
    public void stopAnimator() {
        if (animatorSet != null)
            animatorSet.end();
    }
}
