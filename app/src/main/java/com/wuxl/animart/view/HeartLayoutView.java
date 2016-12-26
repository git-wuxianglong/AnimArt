package com.wuxl.animart.view;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.PointF;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.wuxl.animart.R;

import java.util.Random;

/**
 * Author:wuxianglong;
 * Time:2016/12/20.
 * 插值器：
 * AccelerateDecelerateInterpolator 在动画开始与介绍的地方速率改变比较慢，在中间的时候加速
 * AccelerateInterpolator 在动画开始的地方速率改变比较慢，然后开始加速
 * AnticipateInterpolator 开始的时候向后然后向前甩
 * AnticipateOvershootInterpolator 开始的时候向后然后向前甩一定值后返回最后的值
 * BounceInterpolator 动画结束的时候弹起
 * CycleInterpolator 动画循环播放特定的次数，速率改变沿着正弦曲线
 * DecelerateInterpolator 在动画开始的地方快然后慢
 * LinearInterpolator 以常量速率改变
 * OvershootInterpolator 向前甩一定值后再回到原来位置
 */
public class HeartLayoutView extends RelativeLayout {

    //图片大小128x128
    private int heartHeight = 128;
    private int heartWidth = 128;

    //界面大小
    private int layoutHeight;
    private int layoutWidth;

    //心形图片
    private Drawable redColorHeart;
    private Drawable yellowColorHeart;
    private Drawable blueColorHeart;
    private Drawable purpleColorHeart;
    private Drawable pinkColorHeart;
    private Drawable[] drawables;
    private static final int DRAWABLE_COUNT = 5;

    //布局
    private LayoutParams layout;

    /**
     * 插值器
     */
    private Interpolator accelerateDecelerateInterpolator = new AccelerateDecelerateInterpolator();
    private Interpolator accelerateInterpolator = new AccelerateInterpolator();
    private Interpolator decelerateInterpolator = new DecelerateInterpolator();
    private Interpolator linearInterpolator = new LinearInterpolator();
    private Interpolator[] interpolators;
    private static final int INTERPOLATOR_COUNT = 4;

    private Random random;

    public HeartLayoutView(Context context) {
        super(context);
    }

    public HeartLayoutView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public HeartLayoutView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    /**
     * 初始化
     */
    private void init() {
        //设置背景颜色
//        setBackgroundColor(getResources().getColor(R.color.gray));
        //初始化随机数

        random = new Random();

        //位于底部且居中
        layout = new LayoutParams(heartWidth, heartHeight);
        layout.addRule(CENTER_HORIZONTAL, TRUE);
        layout.addRule(ALIGN_PARENT_BOTTOM, TRUE);

        drawables = new Drawable[DRAWABLE_COUNT];
        redColorHeart = getResources().getDrawable(R.mipmap.heart_red);
        yellowColorHeart = getResources().getDrawable(R.mipmap.heart_yellow);
        blueColorHeart = getResources().getDrawable(R.mipmap.heart_blue);
        purpleColorHeart = getResources().getDrawable(R.mipmap.heart_purple);
        pinkColorHeart = getResources().getDrawable(R.mipmap.heart_pink);

        drawables[0] = redColorHeart;
        drawables[1] = yellowColorHeart;
        drawables[2] = blueColorHeart;
        drawables[3] = purpleColorHeart;
        drawables[4] = pinkColorHeart;

        interpolators = new Interpolator[INTERPOLATOR_COUNT];
        interpolators[0] = accelerateDecelerateInterpolator;
        interpolators[1] = accelerateInterpolator;
        interpolators[2] = decelerateInterpolator;
        interpolators[3] = linearInterpolator;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        layoutHeight = getMeasuredHeight();
        layoutWidth = getMeasuredWidth();
    }

    /**
     * 合并两种动画
     *
     * @param target
     */
    private AnimatorSet getAnimator(View target) {
        AnimatorSet set = getEnterAnimtor(target);
        ValueAnimator bezierValueAnimator = getBezierValueAnimator(target);

        AnimatorSet finalSet = new AnimatorSet();
        finalSet.playSequentially(set);
        finalSet.playSequentially(set, bezierValueAnimator);
        finalSet.setInterpolator(interpolators[random.nextInt(INTERPOLATOR_COUNT)]);
        finalSet.setTarget(target);
        return finalSet;
    }

    /**
     * 缩放动画
     *
     * @param target
     * @return
     */
    private AnimatorSet getEnterAnimtor(final View target) {
        ObjectAnimator alpha = ObjectAnimator.ofFloat(target, View.ALPHA, 0.2f, 1f);
        ObjectAnimator scaleX = ObjectAnimator.ofFloat(target, View.SCALE_X, 0.2f, 1f);
        ObjectAnimator scaleY = ObjectAnimator.ofFloat(target, View.SCALE_Y, 0.2f, 1f);
        AnimatorSet enter = new AnimatorSet();
        enter.setDuration(500);
        enter.setInterpolator(new LinearInterpolator());
        enter.playTogether(alpha, scaleX, scaleY);
        enter.setTarget(target);
        return enter;
    }

    /**
     * 获取贝塞尔曲线动画
     *
     * @return
     */
    private ValueAnimator getBezierValueAnimator(View target) {
        BezierEvaluator evaluator = new BezierEvaluator(getPointF(2), getPointF(1));
        ValueAnimator valueAnimator = ValueAnimator.ofObject(evaluator,
                new PointF((layoutWidth - heartWidth) / 2, layoutHeight - heartHeight),
                new PointF(random.nextInt(getWidth()), 0));
        valueAnimator.addUpdateListener(new BezierListener(target));
        valueAnimator.setTarget(target);
        valueAnimator.setDuration(3000);
        return valueAnimator;
    }

    private PointF getPointF(int scale) {
        PointF pointF = new PointF();
        pointF.x = random.nextInt(layoutWidth - 100);
        pointF.y = random.nextInt((layoutHeight - 100) / scale);
        return pointF;
    }

    /**
     * 显示
     */
    public void addFavorHeart() {
        ImageView imageView = new ImageView(getContext());
        imageView.setImageDrawable(drawables[random.nextInt(DRAWABLE_COUNT)]);
        imageView.setLayoutParams(layout);
        addView(imageView);

        AnimatorSet set = getAnimator(imageView);
        set.addListener(new AnimEndListener(imageView));
        set.start();
    }

    /**
     * 动画结束后，移除View
     */
    private class AnimEndListener extends AnimatorListenerAdapter {

        private View target;

        public AnimEndListener(View target) {
            this.target = target;
        }

        @Override
        public void onAnimationEnd(Animator animation) {
            super.onAnimationEnd(animation);
            removeView(target);
        }
    }

}
