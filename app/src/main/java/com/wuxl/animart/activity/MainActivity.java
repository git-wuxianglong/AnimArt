package com.wuxl.animart.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.wuxl.animart.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 首页
 */
public class MainActivity extends AppCompatActivity {

    @Bind(R.id.animHeart)
    Button animHeart;
    @Bind(R.id.animElasticBall)
    Button animElasticBall;
    @Bind(R.id.animTwoBall)
    Button animTwoBall;
    @Bind(R.id.animBallMove)
    Button animBallMove;
    @Bind(R.id.animViscosity)
    Button animViscosity;
    @Bind(R.id.animLoadding)
    Button animLoadding;
    @Bind(R.id.animWave)
    Button animWave;
    @Bind(R.id.animLike)
    Button animLike;
    @Bind(R.id.animFailOrSuccess)
    Button animFailOrSuccess;
    @Bind(R.id.animSearch)
    Button animSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.animHeart, R.id.animElasticBall, R.id.animTwoBall, R.id.animBallMove, R.id.animViscosity, R.id.animLoadding, R.id.animWave, R.id.animLike, R.id.animFailOrSuccess, R.id.animSearch})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.animHeart:
                //直播送心动画
                startActivity(new Intent(MainActivity.this, HeartAnimActivity.class));
                break;
            case R.id.animElasticBall:
                //变色弹力球加载动画
                startActivity(new Intent(MainActivity.this, ElasticBallActivity.class));
                break;
            case R.id.animTwoBall:
                //酷炫加载动画
                startActivity(new Intent(MainActivity.this, TwoBallActivity.class));
                break;
            case R.id.animBallMove:
                //小球轨迹运动加载动画
                break;
            case R.id.animViscosity:
                //粘性效果动画
                break;
            case R.id.animLoadding:
                //点跳动加载动画
                break;
            case R.id.animWave:
                //注水效果加载动画
                startActivity(new Intent(MainActivity.this, WaveActivity.class));
                break;
            case R.id.animLike:
                //点赞动画
                break;
            case R.id.animFailOrSuccess:
                //加载成功失败动画
                break;
            case R.id.animSearch:
                //创意搜索框动画
                break;
        }
    }
}
