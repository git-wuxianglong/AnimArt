package com.wuxl.animart.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.wuxl.animart.R;
import com.wuxl.animart.view.HeartLayoutView;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class HeartAnimActivity extends AppCompatActivity {


    @Bind(R.id.favorHeart)
    HeartLayoutView favorHeart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_heart_anim);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.favorHeart)
    public void onClick() {
        favorHeart.addFavorHeart();
    }
}
