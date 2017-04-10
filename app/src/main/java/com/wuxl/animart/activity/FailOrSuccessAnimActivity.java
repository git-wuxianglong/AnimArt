package com.wuxl.animart.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.wuxl.animart.R;
import com.wuxl.animart.view.FailOrSuccessAnimView;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class FailOrSuccessAnimActivity extends AppCompatActivity {

    @Bind(R.id.animView)
    FailOrSuccessAnimView animView;
    @Bind(R.id.btn)
    Button btn;
    @Bind(R.id.btn2)
    Button btn2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fail_or_success_anim);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.btn, R.id.btn2})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn:
                //失败
                new Thread() {
                    @Override
                    public void run() {
                        try {
                            animView.setProgress(0);
                            while (animView.getProgress() < 100) {
                                Thread.sleep(10);
                                animView.setProgress(animView.getProgress() + 1);
                            }
                            animView.finishFail();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }.start();
                break;
            case R.id.btn2:
                //成功
                new Thread() {
                    @Override
                    public void run() {
                        try {
                            animView.setProgress(0);
                            while (animView.getProgress() < 100) {
                                Thread.sleep(10);
                                animView.setProgress(animView.getProgress() + 1);
                            }
                            animView.finishSuccess();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }.start();
                break;
        }
    }
}
