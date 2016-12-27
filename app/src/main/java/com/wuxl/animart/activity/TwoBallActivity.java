package com.wuxl.animart.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.wuxl.animart.R;
import com.wuxl.animart.view.TwoBallView;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class TwoBallActivity extends AppCompatActivity {

    @Bind(R.id.start)
    Button start;
    @Bind(R.id.end)
    Button end;
    @Bind(R.id.ballView)
    TwoBallView ballView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_two_ball);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.start, R.id.end})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.start:
                ballView.startAnimator();
                break;
            case R.id.end:
                ballView.stopAnimator();
                break;
        }
    }
}
