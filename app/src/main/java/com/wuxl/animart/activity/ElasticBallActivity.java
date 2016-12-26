package com.wuxl.animart.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

import com.wuxl.animart.R;
import com.wuxl.animart.view.ElasticBallView;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ElasticBallActivity extends AppCompatActivity {

    @Bind(R.id.changeColor)
    Button changeColor;
    @Bind(R.id.ballView)
    ElasticBallView ballView;

    private int[] colors = new int[]{
            Color.parseColor("#FFFF0000"), Color.parseColor("#FF7CFC00"),
            Color.parseColor("#FF7A378B"), Color.parseColor("#FF436EEE"),
            Color.parseColor("#FF2ACFA2"), Color.parseColor("#FFFFFF00"),
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_elastic_ball);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.changeColor)
    public void onClick() {
        int index = (int) (Math.random() * 6);
        ballView.setColor(colors[index]);
    }
}
