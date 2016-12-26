package com.wuxl.animart.view;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/**
 * Author:wuxianglong;
 * Time:2016/12/23.
 */
public class WaveView extends View {

    private static final float DEFAULT_AMPLITUDE_RADIO = 0.05f;
    private static final float DEFAULT_WATER_LEVEL_RADIO = 0.05f;
    private static final float DEFAULT_WATER_LENGTH_RADIO = 1.0f;
    private static final float DEFAULT_WATER_SHIFT_RADIO = 0.0f;

    public static final int DEFAULT_BEHIND_WAVE_COLOR = Color.parseColor("#28FFFFFF");
    public static final int DEFAULT_FRONT_WAVE_COLOR = Color.parseColor("#3CFFFFFF");

    private boolean showWave;
    private Matrix shaderMatrix;
    private Paint viewPaint;
    private Paint borderPaint;



    public WaveView(Context context) {
        super(context);
    }

    public WaveView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public WaveView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
}
